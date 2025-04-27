﻿package com.guitar_app_one_direction;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HomeController implements Initializable {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button createSongBtn;

    @FXML
    private Button findSongBtn;

    @FXML
    private ImageView profilePicture;

    private MusicAppFACADE facade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();

        // Get current logged-in user
        String name = facade.getLoggedInName();
        welcomeLabel.setText("Welcome, " + name + "!");

    }

    @FXML
    private void goToProfile(MouseEvent event) throws IOException {
        System.out.println("Profile clicked");

        FXMLLoader loader = new FXMLLoader(App.class.getResource("profile.fxml"));
        Parent root = loader.load();

        ProfileController profileController = loader.getController();

        // Get the logged in user
        MusicAppFACADE facade = new MusicAppFACADE();
        User currentUser = facade.getUser();

        profileController.setUser(currentUser);

        App.setRoot(root);
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
