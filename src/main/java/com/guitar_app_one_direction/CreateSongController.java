package com.guitar_app_one_direction;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.model.*;

import java.io.IOException;
import java.util.ArrayList;

public class CreateSongController {

    @FXML
    private TextField SongTitleField;

    @FXML
    private TextField tempoTextField;

    @FXML
    private ComboBox<Difficulty> comboDifficulty;

    @FXML
    private ComboBox<Genre> comboGenre;

    @FXML
    private VBox measureContainer;

    @FXML
    private Button uploadSongBtn;

    @FXML
    private Button playSongBtn;

    @FXML
    private Button createTabsBtn;

    @FXML
    private Button addMeasureButton;

    @FXML
    private ImageView profilePicture;

    private final Songlist songlist = Songlist.getInstance();

    private final MusicAppFACADE facade = MusicAppFACADE.getInstance();


    private int measureCount = 2; // already 2 measures in FXML

    @FXML
    private void initialize() {
        comboDifficulty.setItems(FXCollections.observableArrayList(Difficulty.values()));
        comboGenre.setItems(FXCollections.observableArrayList(Genre.values()));
    }

    @FXML
    private void addMeasure() {
        measureCount++;

        Label measureLabel = new Label("Measure " + measureCount);
        measureLabel.setStyle("-fx-text-fill: white; -fx-font-style: bold;");
        measureLabel.setFont(new javafx.scene.text.Font("System Bold", 20));

        HBox newMeasure = new HBox();
        newMeasure.setPrefHeight(60);
        newMeasure.setPrefWidth(368);
        newMeasure.setStyle("-fx-background-color: #1D3435; -fx-pref-height: 60;");

        Button addNoteBtn = new Button("➕");
        addNoteBtn.setPrefHeight(59);
        addNoteBtn.setPrefWidth(59);
        addNoteBtn.setStyle("-fx-font-size: 25; -fx-border-color: #1D3435; -fx-background-color: white; -fx-border-width: 2;");
        addNoteBtn.setTextFill(javafx.scene.paint.Color.valueOf("#bd522c"));
        addNoteBtn.setOnAction(e -> openNotePopup());

        newMeasure.getChildren().add(addNoteBtn);

        measureContainer.getChildren().addAll(measureLabel, newMeasure);
    }

    @FXML
    private void uploadSong() {
        try {
            String title = SongTitleField.getText().trim();
            String tempoText = tempoTextField.getText().trim();
            Difficulty difficulty = comboDifficulty.getValue();
            Genre genre = comboGenre.getValue();

            if (title.isEmpty() || tempoText.isEmpty() || difficulty == null || genre == null) {
                showAlert("Error", "Please fill out all fields and select difficulty and genre.");
                return;
            }

            int tempo = Integer.parseInt(tempoText);

            ArrayList<Measure> measures = new ArrayList<>(); // Assume user will add notes later

            ArrayList<Genre> genres = new ArrayList<>();
            genres.add(genre);

            songlist.addSong(title, "Unknown Artist", 0, 0, tempo, genres, difficulty, measures);
            showAlert("Success", "Song uploaded successfully!");
            clearForm();

        } catch (NumberFormatException ex) {
            showAlert("Input Error", "Tempo must be a number.");
        }
    }

    @FXML
    private void playCreateSong() {
        showAlert("Play Song", "Pretending to play the created song 🎸");
    }

    @FXML
    private void goToTabsForCreateSong() throws IOException {
        App.setRoot("tabsPage");
    }

    @FXML
    private void openNotePopup() {
        showAlert("Add Note", "Opening note popup (not implemented yet).");
    }

    @FXML
    private void goToProfile(MouseEvent event) throws IOException {
        System.out.println("Profile clicked");

        FXMLLoader loader = new FXMLLoader(App.class.getResource("profile.fxml"));
        Parent root = loader.load();

        ProfileController profileController = loader.getController();

        // Get the logged in user
        User currentUser = facade.getUser();

        profileController.setUser(currentUser);

        App.setRoot(root);
    }

    private void clearForm() {
        SongTitleField.clear();
        tempoTextField.clear();
        comboDifficulty.getSelectionModel().clearSelection();
        comboGenre.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
