package com.guitar_app_one_direction;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.model.MusicAppFACADE;
import com.model.Song;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileController implements Initializable {

    @FXML
    private VBox vboxFavorites;

    @FXML
    private VBox vboxCompleted;

    @FXML
    private VBox vboxMySongs;

    @FXML
    private Button btnFavorites;

    @FXML
    private Button btnCompleted;

    @FXML
    private Button btnMySongs;

    private MusicAppFACADE facade;
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();

        // Set up button actions
        btnFavorites.setOnAction(e -> openFXML("favorites.fxml"));
        btnCompleted.setOnAction(e -> openFXML("completed.fxml"));
        btnMySongs.setOnAction(e -> openFXML("mySongs.fxml"));

        loadSongs();
    }

    private void loadSongs() {
        List<Song> favorites = facade.getFavoriteSongs(currentUser);
        for (Song song : favorites) {
            Label songLabel = new Label(song.getTitle());
            vboxFavorites.getChildren().add(songLabel);
        }

        List<Song> completed = facade.getCompletedSongs(currentUser);
        for (Song song : completed) {
            Label songLabel = new Label(song.getTitle());
            vboxCompleted.getChildren().add(songLabel);
        }

        List<Song> mySongs = facade.getMySongs(currentUser);
        for (Song song : mySongs) {
            Label songLabel = new Label(song.getTitle());
            vboxMySongs.getChildren().add(songLabel);
        }
    }

    private void openFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Object controller = loader.getController();

            if (fxmlFile.equals("favorites.fxml") && controller instanceof FavoritesController) {
                ((FavoritesController) controller).setUser(currentUser);
            } else if (fxmlFile.equals("completed.fxml") && controller instanceof CompletedController) {
                ((CompletedController) controller).setUser(currentUser);
            } else if (fxmlFile.equals("mySongs.fxml") && controller instanceof MySongsController) {
                ((MySongsController) controller).setUser(currentUser);
            }

            Stage stage = (Stage) btnFavorites.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUser(User user) {
        this.currentUser = user;
    }
}
