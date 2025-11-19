package Service;

import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UserService {
    private ObservableList<User> users;

    public UserService() {
        this.users = FXCollections.observableArrayList();
    }

    // Create a new user
    public User createUser(String username, String password, String currency, String email) {
        User user = new User(username, password, currency, email);
        users.add(user);
        return user;
    }

    // Get all users
    public List<User> getAllUsers() {
        return users;
    }

    // Get user by ID (username)
    public User getUserByID(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // Update user details
    public boolean updateUser(String username, String password, String currency, String email) {
        User user = getUserByID(username);
        if (user != null) {
            user.setPassword(password);
            user.setCurrency(currency);
            user.setEmail(email);
            return true;
        }
        return false;
    }

    // Delete user
    public boolean deleteUser(String username) {
        User user = getUserByID(username);
        if (user != null) {
            users.remove(user);
            return true;
        }
        return false;
    }

    // Change password
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = getUserByID(username);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }
}
