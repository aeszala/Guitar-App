package com.guitar_app_one_direction;

import com.model.User;
import com.model.UserList;

public class SecurityController {

    private UserList userList;

    public SecurityController() {
        userList = UserList.getInstance();
    }

    /**
     * Verifies the provided answer to a user's security question.
     * 
     * @param username       The user's username.
     * @param answerProvided The answer given by the user.
     * @return true if the answer matches, false otherwise.
     */
    public boolean verifySecurityAnswer(String username, String answerProvided) {
        User user = UserList.getUser(username);
        if (user != null) {
            String correctAnswer = user.getSecurityAnswer();
            if (correctAnswer.equalsIgnoreCase(answerProvided.trim())) {
                System.out.println("Security answer verified for " + username);
                return true;
            } else {
                System.out.println("Incorrect security answer for " + username);
            }
        } else {
            System.out.println("User not found: " + username);
        }
        return false;
    }

    /**
     * Gets the security question for a given user.
     * 
     * @param username The user's username.
     * @return The security question or null if user not found.
     */
    public String getSecurityQuestion(String username) {
        User user = UserList.getUser(username);
        return (user != null) ? user.getSecurityQuestion() : null;
    }
}
