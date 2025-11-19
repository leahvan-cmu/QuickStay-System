package Model;

public class User {
  private String userID;
  private String name;
  private String phone;
  private String email;
  private String password;

  public User(String userID, String name, String phone, String email, String password) {
    this.userID = userID;
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.password = password;
  }

  // Getters & Setters

  public String getUserID() {
    return userID;  }
  public void setUserID(String userID) {
    this.userID = userID;  }

  public String getName() {
    return name;  }
  public void setName(String name) {
    this.name = name;  }

  public String getPhone() {
    return phone;  }
  public void setPhone(String phone) {
    this.phone = phone;  }

  public String getEmail() {
    return email;  }
  public void setEmail(String email) {
    this.email = email;  }

  public String getPassword() {
    return password;  }
  public void setPassword(String password) {
    this.password;  }

}
