package com.guitar_app_one_direction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.model.MusicAppFACADE;
import com.model.Song;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TabsPageController {

    @FXML private Label songTitle;
    @FXML private ScrollPane tabsScrollPane;
    @FXML private Button saveToTxtBtn;
    @FXML private AnchorPane tabsContentPane;

    @FXML private TextArea tabsTextArea; // Add this to your FXML
    
    @FXML
    public void initialize() {

        System.out.println("Initializing tabs page...");
    
        tabsTextArea.setFont(Font.font("Monospaced", 14));

            Song currentSong = App.getCurrentSong();
            if (currentSong != null) {
                System.out.println("Song not null, setting formatted tabs.");
                tabsTextArea.setText("currentSong.getFormattedTabs()");
                System.out.println(currentSong.getFormattedTabs());
            }
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void saveTabsToTxt(ActionEvent event) {
        System.out.println("tabs button pressed");
         Song currentSong = App.getCurrentSong();
         MusicAppFACADE facade = new MusicAppFACADE();
         facade.printTabSheet(currentSong.getTitle());
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