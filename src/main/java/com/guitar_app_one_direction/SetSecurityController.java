package com.guitar_app_one_direction;

import java.io.IOException;

import com.model.User;
import com.model.UserList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SetSecurityController {

    @FXML
    private TextField securityQuestionField;

    @FXML
    private TextField securityAnswerField;

    private UserList userList;

    public SetSecurityController() {
        userList = UserList.getInstance();
    }

    /**
     * Handles the "Done" button click event.
     */
    @FXML
    private void handleSubmit(ActionEvent event) throws IOException{
        String securityQuestion = securityQuestionField.getText();
        String securityAnswer = securityAnswerField.getText();
        
        if (securityQuestion.isEmpty() || securityAnswer.isEmpty()) {
            System.out.println("Security question or answer is empty!");
            return;
        }

        String currentUsername = App.getCurrentUsername(); // Make sure you have a way to get the current logged-in username
        if (currentUsername != null) {
            boolean success = setSecurityDetails(currentUsername, securityQuestion, securityAnswer);
            if (success) {
                System.out.println("Security details updated successfully!");
                App.setRoot("profile");
            } else {
                System.out.println("Failed to update security details.");
            }
        } else {
            System.out.println("No user is currently logged in.");
        }
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
