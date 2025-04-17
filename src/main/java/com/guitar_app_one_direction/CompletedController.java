package com.guitar_app_one_direction;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.model.MusicAppFACADE;
import com.model.Song;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class CompletedController implements Initializable {

    @FXML
    private ListView<String> completedListView;  // ListView to display completed songs

    private MusicAppFACADE facade;
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();  // Initialize the facade to interact with music data
    }

    public void setUser(User user) {
        this.currentUser = user;  // Set the current user
        loadCompletedSongs();  // Load the completed songs for the user
    }

    private void loadCompletedSongs() {
        if (currentUser == null) return;  // If no user is set, do nothing

        // Fetch the list of completed songs for the current user
        List<Song> completedSongs = facade.getCompletedSongs(currentUser);

        // Clear the existing items in the ListView
        completedListView.getItems().clear();

        // Add each song's title to the ListView
        for (Song song : completedSongs) {
            completedListView.getItems().add(song.getTitle());  // Add song title to the ListView
        }
    }
}
