package com.guitar_app_one_direction;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.model.*;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

public class SearchController {

    @FXML
    private TextField txtKeyword;

    @FXML
    private Button btnSearch;

    @FXML
    private ListView<String> listResults;

    private final Songlist songlist = Songlist.getInstance();

    @FXML
    private void initialize() {
        // btnSearch.setOnAction(e -> handleSearch());
    }

    @FXML
    private void goToProfile() throws IOException{
        // Logic for navigating to the profile page
        System.out.println("Navigating to profile...");
        App.setRoot("profile");
}


    private void handleSearch() {
        String keyword = txtKeyword.getText().trim();
        ObservableList<String> songDisplayList = FXCollections.observableArrayList();

        if (!keyword.isEmpty()) {
            ArrayList<Song> matchedSongs = songlist.getSongs(keyword);
            for (Song song : matchedSongs) {
                songDisplayList.add(formatSong(song));
            }
        }

        listResults.setItems(songDisplayList);
    }

    private String formatSong(Song song) {
        return song.getTitle() + " by " + song.getArtist() + " [" +
               song.getDifficulty() + ", " + song.getTempo() + " BPM]";
    }
}
