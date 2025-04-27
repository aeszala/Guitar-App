package com.guitar_app_one_direction;

import java.io.IOException;
import com.model.Song;
import com.model.Songlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SongController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField artistField;

    // @FXML
    // private TextField runtimeLabel;

    @FXML
    private Button addButton;

    @FXML
    private Button playButton;

    // @FXML
    // private ListView<Song> songListView;

    private Songlist songlist;
    private ObservableList<Song> observableSongs;

    private Song currentSong; // To store the current song to be displayed on the page

    @FXML
    public void initialize() {
        songlist = Songlist.getInstance();
        // observableSongs = FXCollections.observableArrayList(songlist.getSongs());
        // songListView.setItems(observableSongs);

        // Optional: show just titles
        // songListView.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
        //     @Override
        //     protected void updateItem(Song song, boolean empty) {
        //         super.updateItem(song, empty);
        //         if (empty || song == null || song.getTitle() == null) {
        //             setText(null);
        //         } else {
        //             setText(song.getTitle() + " - " + song.getArtist());
        //         }
        //     }
        // });
    }

    @FXML
    private void handleAddSong(MouseEvent event) {
        String title = titleField.getText().trim();
        String artist = artistField.getText().trim();

        if (!title.isEmpty() && !artist.isEmpty()) {
            Song newSong = new Song(title, artist);
            Songlist.addSong(newSong);
            observableSongs.add(newSong);

            // Clear fields after adding
            titleField.clear();
            artistField.clear();
        }
    }

    // Method to set the selected song when moving to this page
    public void setSong(Song song) {
        currentSong = song;
        System.out.println("Song set: " + song.getTitle() + " by " + song.getArtist());
        titleField.setText(song.getTitle());
        artistField.setText(song.getArtist());
        // runtimeLabel.setText("Runtime: " + song.getRunLengthMin() + ":" + song.getRunLengthSec());
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
