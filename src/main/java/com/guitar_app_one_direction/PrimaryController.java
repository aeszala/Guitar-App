/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package com.guitar_app_one_direction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.guitar_app_one_direction.App;
import com.model.*;

/**
 *
 * @author portia
 */
public class PrimaryController implements Initializable {
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Label lbl_error;

    @FXML
    private void btnLoginClicked(MouseEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();

        MusicAppFACADE library = new MusicAppFACADE();

        if (!library.login(username, password)) {
            lbl_error.setText("Invalid login credentials.");
            return;
        }

        App.setRoot("home");
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}