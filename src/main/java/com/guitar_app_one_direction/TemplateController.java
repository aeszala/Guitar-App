package com.guitar_app_one_direction;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.util.Stack;

public class TemplateController {
    @FXML
    private StackPane contentPane;
    
    private Stack<Node> pageHistory = new Stack<>();
    
    public void setContent(String fxmlPath) {
        try {
            // Load the new content
            Node content = FXMLLoader.load(App.class.getResource(fxmlPath + ".fxml"));
            
            // Add current page to history (if there is one)
            if (!contentPane.getChildren().isEmpty()) {
                pageHistory.push(contentPane.getChildren().get(0));
            }
            
            // Clear and set new content
            contentPane.getChildren().clear();
            contentPane.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleBackButton() {
        if (!pageHistory.isEmpty()) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(pageHistory.pop());
        }
    }
    
    @FXML
    private void handleHomeButton() {
        try {
            App.setContent("primary");
            pageHistory.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToProfile() {
        try {
            App.setContent("profile"); // or whatever the FXML file for the profile screen is
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToHome() {
        handleHomeButton();
    }
}