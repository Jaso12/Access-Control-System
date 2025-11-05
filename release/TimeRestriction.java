package release;

import release.requests.RequestReader;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.List;

public class TimeRestriction extends Restriction {
    // ----------------------------------Attribute section------------------------------------------- \\
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    /* Design decision --> using a support class WorkHour as a storage for weekly schedules, between which
    *  hours of the day have access? */
    private List<WorkHour> workHours = new ArrayList<>();

    // ------------------------------------Method section--------------------------------------------- \\
    public TimeRestriction(LocalDateTime startDate, LocalDateTime endDate, Restriction restriction) {
        super(restriction);
        this.endDate = endDate;
        this.startDate = startDate;
    }
    public TimeRestriction(LocalDateTime startDate,LocalDateTime endDate){
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public void addWorkHours(DayOfWeek day, LocalTime startHour, LocalTime endHour) {
        workHours.add(new WorkHour(day,startHour,endHour));
    }

    @Override
    public boolean authorize(RequestReader request) {
        DayOfWeek day = request.getNow().getDayOfWeek();
        boolean isWithinDateRange = !request.getNow().isBefore(startDate) && !request.getNow().isAfter(endDate);

        if (isWithinDateRange) {
            for (WorkHour workHour : workHours) {
                if (workHour.getDay() == day &&
                        !request.getNow().toLocalTime().isBefore(workHour.getStartHour()) &&
                        !request.getNow().toLocalTime().isAfter(workHour.getEndHour())) {
                    return super.authorize(request);
                }
            }
        }

        request.addReason("Not an authorized time for " + DirectoryGroups.findUserByCredential(request.getCredential()).getName());
        return false;
    }
}
