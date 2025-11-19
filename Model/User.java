package Model;

public class User {
  private String username;
  private String email;
  private String currency;
  private String password;

  // Constructor
  public User(String username, String email, String currency, String password) {
    this.username = username;
    this.email = email;
    this.currency = currency;
    this.password = password;
  }

  // Getters & Setters
  public String getName() {
    return username;
  }

  public void setName(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }
  public void setUsername(String usernmae) {
    this.username = username;
  }

}
