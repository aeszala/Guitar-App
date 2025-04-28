package com.guitar_app_one_direction;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import javafx.util.Callback;
import com.model.MusicAppFACADE;
import com.model.Song;
import com.model.User;

public class SearchController {

    @FXML
    private TextField songSearchField;

    @FXML
    private ListView<Song> songsListView;

    @FXML
    private ImageView profilePicture;

    private MusicAppFACADE facade;

    public void initialize() {
        // Initialize the facade
        facade = MusicAppFACADE.getInstance();

        // Set up custom cell factory for the ListView
        songsListView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
            @Override
            public ListCell<Song> call(ListView<Song> listView) {
                return new ListCell<Song>() {
                    @Override
                    protected void updateItem(Song song, boolean empty) {
                        super.updateItem(song, empty);

                        if (empty || song == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Create the custom layout for each song
                            VBox songBox = new VBox(5);
                            songBox.setStyle(
                                    "-fx-padding: 10; -fx-background-color: rgba(0,0,0,0.2); -fx-background-radius: 5;");

                            // Song title
                            Label titleLabel = new Label(song.getTitle());
                            titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

                            // Artist name
                            Label artistLabel = new Label("By " + song.getArtist());
                            artistLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #cccccc;");

                            // Difficulty and genre
                            HBox detailsBox = new HBox(10);
                            Label difficultyLabel = new Label(song.getDifficulty().toString());
                            difficultyLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #aaaaaa;");

                            // Assuming song has getGenres() method that returns ArrayList<Genre>
                            String genresString = song.getGenres().toString().replaceAll("[\\[\\]]", "");
                            Label genreLabel = new Label(genresString);
                            genreLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #aaaaaa;");

                            detailsBox.getChildren().addAll(difficultyLabel, genreLabel);
                            songBox.getChildren().addAll(titleLabel, artistLabel, detailsBox);
                            setGraphic(songBox);
                        }
                    }
                };
            }
        });

        // Handle song selection
        // Updated selection listener
        songsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    // Set the current song via App class
                    App.setCurrentSong(newSelection);

                    // Navigate to song page
                    App.setRoot("Song");
                } catch (Exception e) {
                    e.printStackTrace();
                    // Optionally show error alert to user
                    // showAlert("Navigation Error", "Could not open song details");
                }
            }
        });
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        String keyword = songSearchField.getText().trim();
        if (!keyword.isEmpty()) {
            // Using the facade's findSongs method which returns boolean
            boolean foundSongs = facade.findSongs(keyword);
            if (foundSongs) {
                // Get the songs from facade's getSongs() method
                ArrayList<Song> foundSongsList = facade.getSongs();
                ObservableList<Song> observableSongs = FXCollections.observableArrayList(foundSongsList);
                songsListView.setItems(observableSongs);
            } else {
                // Clear the list if no songs found
                songsListView.setItems(FXCollections.emptyObservableList());
            }
        }
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

    private void switchToSongPage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("song.fxml"));
            Stage stage = (Stage) songsListView.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}