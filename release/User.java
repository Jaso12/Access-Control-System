package release;

import release.requests.RequestReader;

public class User {
  // ----------------------------------Attribute section------------------------------------------- \\
  private final String name;
  private final String credential;
  private final Group group;

  // ------------------------------------Method section--------------------------------------------- \\
  public User(String name, String credential, Group group) {
    this.name = name;
    this.credential = credential;
    this.group = group;
  }

  public String getCredential() {
    return credential;
  }
  public String getName() { return name; }

  public boolean authorize(RequestReader request) { return group.authorize(request); }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
