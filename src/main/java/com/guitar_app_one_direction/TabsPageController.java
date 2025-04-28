package com.guitar_app_one_direction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TabsPageController {

    @FXML private Label songTitle;
    @FXML private ScrollPane tabsScrollPane;
    @FXML private Button saveToTxtBtn;
    @FXML private AnchorPane tabsContentPane;
    
    private TextArea tabsTextArea;
    
    @FXML
    public void initialize() {
        // First check if tabsContentPane was properly injected
        if (tabsContentPane == null) {
            System.err.println("Error: tabsContentPane is null! Check FXML file.");
            return;
        }
        
        // Initialize the text area for tabs display
        tabsTextArea = new TextArea();
        tabsTextArea.setWrapText(true);
        tabsTextArea.setEditable(false);
        tabsTextArea.setStyle("-fx-control-inner-background: #203434; -fx-text-fill: white; -fx-font-size: 14px;");
        
        // Add the text area to the scroll pane's content
        tabsContentPane.getChildren().add(tabsTextArea);
        AnchorPane.setTopAnchor(tabsTextArea, 0.0);
        AnchorPane.setBottomAnchor(tabsTextArea, 0.0);
        AnchorPane.setLeftAnchor(tabsTextArea, 0.0);
        AnchorPane.setRightAnchor(tabsTextArea, 0.0);
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void saveTabsToTxt(ActionEvent event) {
        if (tabsTextArea == null || tabsTextArea.getText().isEmpty()) {
            System.out.println("No tabs content to save");
            return;
        }
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Tabs As");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        fileChooser.setInitialFileName("guitar_tabs.txt");
        
        Stage stage = (Stage) saveToTxtBtn.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Song: " + songTitle.getText() + "\n\n");
                writer.write(tabsTextArea.getText());
                writer.flush();
                System.out.println("Tabs saved successfully to: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error saving tabs: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    public void setTabsContent(String title, String tabs) {
        if (songTitle != null) {
            songTitle.setText(title);
        }
        if (tabsTextArea != null) {
            tabsTextArea.setText(tabs);
        }
    }
}