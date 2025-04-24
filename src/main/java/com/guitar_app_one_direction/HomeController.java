package com.guitar_app_one_direction;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.model.MusicAppFACADE;
import com.model.Song;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {

    @FXML
    private Label welcomeLabel;

    @FXML
    private VBox vboxFavorites;

    @FXML
    private VBox vboxCompleted;

    @FXML
    private VBox vboxMySongs;

    private MusicAppFACADE facade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();

        // Get current logged-in user
        String username = facade.getLoggedInUsername();
        welcomeLabel.setText("Welcome, " + username + "!");

    }

    @FXML
    private void goToProfile() throws IOException {
        System.out.println("Profile clicked!");
        // TODO: Implement navigation to the profile screen
        App.setRoot("profile");
    }

    @FXML
    private void goToSearch(ActionEvent event) throws IOException{
        System.out.println("Search clicked");
        App.setRoot("search");
    }

    @FXML
    private void goToCreateSong(ActionEvent event) throws IOException {
        System.out.println("Create Song clicked");
        App.setRoot("createSong");
}


    public void setUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUser'");
    }
}
