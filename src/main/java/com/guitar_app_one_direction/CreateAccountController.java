package com.guitar_app_one_direction;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import com.model.User;
import com.model.UserList;

public class CreateAccountController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSecurityQuestion;

    @FXML
    private TextField txtSecurityAnswer;

    @FXML
    private Button btnCreate;

    private final UserList userList = UserList.getInstance();

    @FXML
    private void initialize() {
        btnCreate.setOnAction(e -> handleCreateAccount());
    }

    private void handleCreateAccount() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String email = txtEmail.getText().trim();
        String name = txtName.getText().trim();
        String securityQuestion = txtSecurityQuestion.getText().trim();
        String securityAnswer = txtSecurityAnswer.getText().trim();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || name.isEmpty() ||
                securityQuestion.isEmpty() || securityAnswer.isEmpty()) {
            showAlert(AlertType.ERROR, "Please fill in all fields.");
            return;
        }

        boolean success = userList.addUser(username, password, email, name, securityQuestion, securityAnswer);

        if (success) {
            UserList.saveUsers();
            showAlert(AlertType.INFORMATION, "Account created successfully!");
            clearForm();
        } else {
            showAlert(AlertType.WARNING, "Username already exists. Please choose another.");
        }
    }

    private void clearForm() {
        txtUsername.clear();
        txtPassword.clear();
        txtEmail.clear();
        txtName.clear();
        txtSecurityQuestion.clear();
        txtSecurityAnswer.clear();
    }

    private void showAlert(AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Account Creation");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
