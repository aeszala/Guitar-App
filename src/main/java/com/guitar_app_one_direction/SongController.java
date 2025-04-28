package com.guitar_app_one_direction;

import java.io.IOException;
import com.model.Song;
import com.model.Songlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SongController {

    @FXML
    private ImageView albumImage;

    @FXML
    private Label artistLabel;

    @FXML
    private Button createTabsButton;

    @FXML
    private Button playSongButton;

    @FXML
    private Label ratingLabel;

    @FXML
    private VBox reviewContainer;

    @FXML
    private Label runtimeLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label titleLabel;

    private Song currentSong; // To store the current song to be displayed on the page

@FXML
public void initialize() {
    try {
        // Get the current song from App
        Song currentSong = App.getCurrentSong();
        
        if (currentSong != null) {
            // Set basic song info
            titleLabel.setText(currentSong.getTitle());
            artistLabel.setText(currentSong.getArtist());
            
            // // Set album image (with fallback to default)
            // try {
            //     if (currentSong.getImagePath() != null && !currentSong.getImagePath().isEmpty()) {
            //         albumImage.setImage(new Image(currentSong.getImagePath()));
            //     } else {
            //         albumImage.setImage(new Image(getClass().getResourceAsStream("/images/chooseImage.png")));
            //     }
            // } catch (Exception e) {
            //     albumImage.setImage(new Image(getClass().getResourceAsStream("/images/chooseImage.png")));
            // }
            
            // Set runtime (convert seconds to MM:SS format)
            int seconds = currentSong.getRunLengthSec();
            int minutes = currentSong.getRunLengthMin();
            runtimeLabel.setText(String.format("%d:%02d", minutes, seconds));
            
            // Set rating (convert numeric rating to stars)
            double rating = (currentSong.getRating());
            ratingLabel.setText("★".repeat((int)rating) + "☆".repeat(5 - (int)rating));
            
            // Set completion status
            statusLabel.setText(currentSong.isCompleted() ? "✓ Complete" : "ⓧ Not Complete");
            statusLabel.setStyle(currentSong.isCompleted() 
                ? statusLabel.getStyle() + "-fx-background-color: #4CAF50;" 
                : statusLabel.getStyle() + "-fx-background-color: #D95032;");
            
        } else {
            // Handle case where no song is selected
            titleLabel.setText("No Song Selected");
            artistLabel.setText("");
            runtimeLabel.setText("0:00");
            ratingLabel.setText("☆☆☆☆☆");
            statusLabel.setText("ⓧ No Song");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        // Fallback display if something goes wrong
        titleLabel.setText("Error Loading Song");
        artistLabel.setText("");
        runtimeLabel.setText("0:00");
        ratingLabel.setText("☆☆☆☆☆");
        statusLabel.setText("ⓧ Error");
    }
}

    @FXML
    private void playSong() {
        if (currentSong != null) {
            currentSong.playSong();
        } else {
            System.out.println("No song selected");
        }
    }

    @FXML
    private void goToTabPage() throws IOException {
        App.setRoot("tabsPage");
        System.out.println("Going to tab creation page...");
    }

    @FXML
    private void handlePlaySong(MouseEvent event) {
        if (currentSong != null) {
            currentSong.playSong();
        } else {
            System.out.println("No song selected");
        }
    }
}