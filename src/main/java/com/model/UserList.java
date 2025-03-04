import java.util.ArrayList;

public class UserList {
    // Singleton instance
    private static UserList userList = null;
    private ArrayList<User> users;

    // Private constructor to prevent direct instantiation
    private UserList() {
        users = new ArrayList<>();
    }

    // Singleton getInstance method
    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    // Add a new user
    public boolean addUser(String username, String password, String email, String name, String securityQuestion, String securityAnswer) {
        // Ensure username is unique
        if (getUser(username) != null) {
            System.out.println("Username already exists.");
            return false;
        }

        User newUser = new User(username, password, email, name, securityQuestion, securityAnswer);
        users.add(newUser);
        System.out.println("User " + username + " added successfully.");
        return true;
    }

    // Retrieve a user by username
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null; // User not found
    }

    // Edit user details
    public void editUser(User user, String username, String name, String password, String email) {
        if (user != null) {
            user = getUser(user.getUsername()); // Get the existing user object
            if (user != null) {
                user = new User(username, password, email, name, user.getSecurityQuestion(), user.getSecurityAnswer());
                System.out.println("User details updated successfully.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    // Check if the given password matches the stored user's password
    public boolean isMatch(User user, String password) {
        return user != null && user.login(user.getUsername(), password);
    }

    // Save users (this could be extended to save to a file or database)
    public void saveUsers() {
        System.out.println("Users saved successfully. (Implement file/database storage here)");
    }

    // Display all users (for testing purposes)
    public void displayUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}
