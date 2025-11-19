package Service;

public class UserService {
  private ObservableList<User> users;

  public User createUser(String username, String password, String email, String currency) {
    User user = new User(username, password, currency, email);
    users.add(user);
    return user;
  }

  public List<User> getAllUsers() {
    return users;
  }

  public User getUserByID(String userID) {
    for(User user : users) {
      if(user.getUserByID().equals(username)) {
        return user;
      }
    }
    return;
  }

  public boolean updateUser(String username, String password, String currency, String email) {
    User user = getUserByID(username);
    if(user != null) {
      user.setPassword(password);
      user.setPhone(phone);
      user.setCurrency(currency);
      return true;
    }
    return false;
  }

  public boolean deleteUser(String username) {
    User user = getUserByID(username);
    if(user != null) {
      users.remove(user);
      return true;
    }
    return false;
  }

  public boolean changePassword(String username, String oldPassword, String newPassword) {
    User user = getUserByID(username);
    if(user != null && user.getPassword().equals(oldPassword) {
      user.setPassword(newPassword);
      return true;
    }
    return false;
  }

}



