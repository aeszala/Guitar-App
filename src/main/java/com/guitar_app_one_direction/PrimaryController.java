package com.guitar_app_one_direction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import com.guitar_app_one_direction.App;
import com.model.*;

public class PrimaryController implements Initializable {
    
    @FXML
    private TextField txt_username;
    
    @FXML
    private TextField txt_password;
    
    @FXML
    private Label lbl_error;

    @FXML
    private AnchorPane login_pane;

    // Define the setContent method to change content dynamically
    public void setContent(String fxml) throws IOException {
        // Load the new FXML file
        Parent newContent = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
        
        // You can set this content in a specific layout container
        // Example, assuming login_pane is your layout pane in primary.fxml:
        login_pane.getChildren().setAll(newContent);
    }

    @FXML
    void btnLoginClicked(ActionEvent event) throws IOException{
        System.out.println("In login");
        String username = txt_username.getText();
        String password = txt_password.getText();

        MusicAppFACADE library = new MusicAppFACADE();

        if (!library.login(username, password)) {
            lbl_error.setText("Invalid login credentials.");
            return;
        }

        User user = library.getUser();

        App.setRoot("home");
    }

    @FXML
    private void btnCreateAccountClicked(MouseEvent event) throws IOException {
        System.out.println("Create Account button clicked");
        // App.setRoot("signup");
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void goToSecurity(MouseEvent event) throws IOException {
        // Handle the event when the "Go to Security" button is clicked
        System.out.println("Going to Security screen...");
        
        // Optional: Route to the security screen
        // App.setRoot("security");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic
    }
}
