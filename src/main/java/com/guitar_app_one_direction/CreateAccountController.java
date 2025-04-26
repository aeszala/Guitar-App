package com.guitar_app_one_direction;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import com.model.User;
import com.model.UserList;

public class CreateAccountController {

    @FXML private TextField txt_username;
    @FXML private TextField txt_password;
    @FXML private TextField txt_password2;
    @FXML private TextField txt_email;
    @FXML private TextField txt_name;
    @FXML private Label MelodexTxt;
    @FXML private Label lbl_error;
    @FXML private AnchorPane login_pane;
    @FXML private Button signUpBackBtn;
    @FXML private Button signUpButton;

    private final UserList userList = UserList.getInstance();

    @FXML
    private void initialize() {
        System.out.println("CreateAccountController loaded!");
    }

    @FXML
    void signUpClicked(ActionEvent event) {
        System.out.println("Sign Up button clicked!");
        handleCreateAccount();
    }

    private void handleCreateAccount() {
        try {
            String username = txt_username.getText().trim();
            String password = txt_password.getText().trim();
            String confirmPassword = txt_password2.getText().trim();
            String email = txt_email.getText().trim();
            String name = txt_name.getText().trim();

            // Validate fields
            if (username.isEmpty() || password.isEmpty() || email.isEmpty() || name.isEmpty()) {
                showAlert(AlertType.ERROR, "Please fill in all fields.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                showAlert(AlertType.ERROR, "Passwords do not match!");
                return;
            }

            // Create account
            boolean success = userList.addUser(username, password, email, name);
            if (success) {
                UserList.saveUsers();
                showAlert(AlertType.INFORMATION, "Account created successfully!");
                App.setRoot("home"); // Navigate to home screen
            } else {
                showAlert(AlertType.WARNING, "Username already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error: " + e.getMessage());
        }
    }

    @FXML
    void goToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    private void showAlert(AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Account Creation");
        alert.setContentText(message);
        alert.showAndWait();
    }
}