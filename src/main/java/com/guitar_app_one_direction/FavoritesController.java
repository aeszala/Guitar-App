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

public class FavoritesController implements Initializable {

    @FXML
    private ListView<String> favoritesListView;  // ListView to display favorite songs

    private MusicAppFACADE facade;
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();  // Initialize the facade to interact with music data
    }

    public void setUser(User user) {
        this.currentUser = user;  // Set the current user
        loadFavoriteSongs();  // Load the favorite songs for the current user
    }

    private void loadFavoriteSongs() {
        if (currentUser == null) return;  // If no user is set, do nothing

        // Fetch the list of favorite songs for the current user
        List<Song> favoriteSongs = facade.getFavoriteSongs(currentUser);

        // Clear the existing items in the ListView
        favoritesListView.getItems().clear();

        // Add each song's title to the ListView
        for (Song song : favoriteSongs) {
            favoritesListView.getItems().add(song.getTitle());  // Add song title to the ListView
        }
    }
}
