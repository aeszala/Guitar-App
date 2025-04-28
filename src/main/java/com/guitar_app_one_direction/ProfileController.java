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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProfileController implements Initializable {

    @FXML
    private VBox vboxFavorites;

    @FXML
    private VBox vboxCompleted;

    @FXML
    private VBox vboxMySongs;

    @FXML
    private Button favoriteSongsBtn;

    @FXML
    private Button completedSongsBtn;

    @FXML
    private Button mySongsBtn;

    @FXML
    private Label nameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    private MusicAppFACADE facade;
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();
    }

    @FXML
    public void initialize() {
        User currentUser = App.getCurrentUser();

        if (currentUser != null) {
            nameLabel.setText(currentUser.getName());
            usernameLabel.setText(currentUser.getUsername());
            emailLabel.setText(currentUser.getEmail());
        } else {
            System.out.println("No user is logged in!");
        }
    }


    @FXML
    private void goToMySongs() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("mySongs.fxml"));
        Parent root = loader.load();
        MySongsController controller = loader.getController();
        controller.setUser(currentUser); // Pass the current user
        App.setRoot(root);
    }

    @FXML
    private void goToSetSecurityQuestion() throws IOException {
        App.setRoot("setSecurity");
    }

    @FXML
    private void goToFavoriteSongs() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("favorites.fxml"));
        Parent root = loader.load();
        FavoritesController controller = loader.getController();
        controller.setUser(currentUser); // Pass the current user
        App.setRoot(root);
    }

    @FXML
    private void goToCompletedSongs() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("completed.fxml"));
        Parent root = loader.load();
        CompletedController controller = loader.getController();
        controller.setUser(currentUser); // Pass the current user
        App.setRoot(root);
    }

    @FXML
    private void goToPrimary() throws IOException {
        App.setRoot("primary");
    }

    private void loadSongs() {
        if (currentUser == null) {
            return;
        }

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

    public void setUser(User user) {
        this.currentUser = user;
        if (user != null) {
            nameLabel.setText(user.getName());
            usernameLabel.setText(user.getUsername());
            emailLabel.setText(user.getEmail());
            loadSongs();
        }
    }
}
