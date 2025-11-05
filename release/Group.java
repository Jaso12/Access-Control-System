package release;

import release.requests.RequestReader;
import java.util.ArrayList;
import java.util.List;

/* We conceived the role-based system with a group associations for shared privileges. You can
*  operate at group-level but also at user-level thanks to a bidirectional relationship 1-N & 1-1.
*  A group holds several users in it and a user is part of a group. By doing so, we can chain methods
*  like the authorize one without depending on the group dependencies.  */
public class  Group {
  // ----------------------------------Attribute section------------------------------------------- \\
  private String name = "blank";
  private Restriction restrictions = new Restriction();
  private List<User> group_users = new ArrayList<>();

  // ------------------------------------Method section--------------------------------------------- \\
  public Group() { /* blank */ }
  public Group(String name) { this.name = name; }
  public Group(String name, Restriction restriction) {
    this.restrictions = restriction;
    this.name = name;
  }

  public void addUser(User user) { group_users.add(user); }
  public List<User> getUsers() { return group_users; }

  /* Relies on the constraints chain to check whether the group fulfills the authorization. More about it in Restriction. */
  public boolean authorize(RequestReader request) {
    if(name.equals("blank")) {
      return false;
    }

    return restrictions.authorize(request);
  }
}
