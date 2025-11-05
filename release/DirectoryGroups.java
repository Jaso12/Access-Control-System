package release;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.DayOfWeek;

public final class DirectoryGroups {
  // ----------------------------------Attribute section------------------------------------------- \\
  private static ArrayList<Group> groups;

  // ------------------------------------Method section--------------------------------------------- \\
  public static void makeGroups() {
    // Making each restriction for the groups
    List<Space> allSpaces = ((Partition) DirectoryAreas.getRoot()).getSubspaces();
    List<Space> employeeSpaces = new ArrayList<>(allSpaces);
    employeeSpaces.remove((Space) DirectoryAreas.findAreaById("parking"));

    // Employee restrictions:
    Restriction employeeRestriction = new SpaceRestriction(employeeSpaces);

    TimeRestriction employeeTimeRestriction = new TimeRestriction(LocalDateTime.of(2024,9,1,0,0),LocalDateTime.of(2025,3,1,23,59));
    employeeRestriction.setRestriction(employeeTimeRestriction);

    for (DayOfWeek day : Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY))
      employeeTimeRestriction.addWorkHours(day, LocalTime.of(9, 0),LocalTime.of(17, 0));

    Restriction employeeActionRestriction= new ActionRestriction(Arrays.asList(Actions.UNLOCK_SHORTLY, Actions.OPEN, Actions.CLOSE));
    employeeTimeRestriction.setRestriction(employeeActionRestriction);

    // Manager restrictions;
    Restriction managerRestriction= new SpaceRestriction(allSpaces);

    TimeRestriction managerTimeRestriction = new TimeRestriction(LocalDateTime.of(2024,9,1,0,0),LocalDateTime.of(2025,3,1,23,59));
    managerRestriction.setRestriction(managerTimeRestriction);

    for (DayOfWeek day : Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY))
      managerTimeRestriction.addWorkHours(day, LocalTime.of(0, 0),LocalTime.of(23, 59));

    // Group creation, adding their respective restriction previously declared
    Group admin = new Group("Administrators");
    Group manager = new Group("Managers", managerRestriction);
    Group employee = new Group("employees", employeeRestriction);
    Group blank = new Group();

    // Create all users;
    // Users without privileges: (blank)
    blank.addUser(new User("Bernat", "12345", blank));
    blank.addUser(new User("Blai", "77532", blank));

    // Users that are employees: (low-privileged)
    employee.addUser(new User("Ernest", "74984", employee));
    employee.addUser(new User("Eulalia", "43295", employee));

    // Users that are managers: (medium-privileged)
    manager.addUser(new User("Manel", "95783", manager));
    manager.addUser(new User("Marta", "05827", manager));

    // Users that are administrators: (high-privileged)
    admin.addUser(new User("Ana", "11343", admin));

    groups = new ArrayList<>(Arrays.asList(admin, manager, employee, blank));
  }

  public static User findUserByCredential(String credential) {
    for (Group group : groups) {
      for (User user : group.getUsers())
        if (user.getCredential().equals(credential)) {
          return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }
}
