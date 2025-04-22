package com.guitar_app_one_direction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.guitar_app_one_direction.App;
import com.model.*;

/**
 * Controller for the Primary Screen (Login screen)
 */
public class PrimaryController implements Initializable {
    
    @FXML
    private TextField txt_username;
    
    @FXML
    private TextField txt_password;
    
    @FXML
    private Label lbl_error;

    /**
     * Login button click event handler.
     * @param event The mouse event triggered by the login button.
     * @throws IOException If the FXML loading fails.
     */
    @FXML
    private void btnLoginClicked(MouseEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();

        MusicAppFACADE library = new MusicAppFACADE();

        if (!library.login(username, password)) {
            lbl_error.setText("Invalid login credentials.");
            return;
        }

        // Get the logged-in user
        User user = library.getUser();

        // Load home.fxml and pass the user
        FXMLLoader loader = new FXMLLoader(App.class.getResource("home.fxml"));
        Parent root = loader.load();

        HomeController homeController = loader.getController();
        homeController.setUser(user);

        // Set the root to the home screen
        App.getScene().setRoot(root);
    }

    /**
     * Create Account button click event handler.
     * @param event The mouse event triggered by the create account button.
     * @throws IOException If the FXML loading fails.
     */
    @FXML
    private void btnCreateAccountClicked(MouseEvent event) throws IOException {
        // For now, just print something to indicate the button was clicked
        System.out.println("Create Account button clicked");

        // Optional: route to a signup screen later
        // App.setRoot("signup");
    }

    /**
     * Back button click event handler.
     * @param event The mouse event triggered by the back button.
     * @throws IOException If the FXML loading fails.
     */
    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic (if any) can be added here
    }
}
