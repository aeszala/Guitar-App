package com.guitar_app_one_direction;

import com.model.User;
import com.model.UserList;

public class SetSecurityController {

    private UserList userList;

    public SetSecurityController() {
        userList = UserList.getInstance();
    }

    /**
     * Sets the security question and answer for a given username.
     * 
     * @param username         The user's username.
     * @param securityQuestion The security question to set.
     * @param securityAnswer   The security answer to set.
     * @return true if updated successfully, false if user not found.
     */
    public boolean setSecurityDetails(String username, String securityQuestion, String securityAnswer) {
        User user = UserList.getUser(username);
        if (user != null) {
            user.setSecurityQuestion(securityQuestion);
            user.setSecurityAnswer(securityAnswer);
            System.out.println("Security details set for " + username);
            UserList.saveUsers(); // Persist changes
            return true;
        } else {
            System.out.println("User not found: " + username);
            return false;
        }
    }
}
