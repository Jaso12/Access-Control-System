package release;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class WorkHour {
    // ----------------------------------Attribute section------------------------------------------- \\
    private DayOfWeek day;
    private LocalTime startHour;
    private LocalTime endHour;

    // ------------------------------------Method section--------------------------------------------- \\
    public WorkHour(DayOfWeek day,LocalTime startHour,LocalTime endHour) {
        this.day = day;
        this.endHour = endHour;
        this.startHour = startHour;
    }

    public DayOfWeek getDay() { return day; }
    public LocalTime getStartHour() { return startHour; }
    public LocalTime getEndHour() { return  endHour; }
}