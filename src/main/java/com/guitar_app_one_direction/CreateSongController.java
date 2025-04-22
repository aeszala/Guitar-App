package com.guitar_app_one_direction;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert.AlertType;

import com.model.*;

import java.util.ArrayList;

public class CreateSongController {

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtArtist;

    @FXML
    private TextField txtMinutes;

    @FXML
    private TextField txtSeconds;

    @FXML
    private TextField txtTempo;

    @FXML
    private ComboBox<Difficulty> comboDifficulty;

    @FXML
    private ListView<Genre> genreList;

    @FXML
    private Button btnCreate;

    private final Songlist songlist = Songlist.getInstance();

    @FXML
    private void initialize() {
        // Load enum values into ComboBox and ListView
        comboDifficulty.setItems(FXCollections.observableArrayList(Difficulty.values()));
        genreList.setItems(FXCollections.observableArrayList(Genre.values()));
        genreList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        btnCreate.setOnAction(e -> handleCreateSong());
    }

    private void handleCreateSong() {
        try {
            String title = txtTitle.getText().trim();
            String artist = txtArtist.getText().trim();
            int minutes = Integer.parseInt(txtMinutes.getText().trim());
            int seconds = Integer.parseInt(txtSeconds.getText().trim());
            int tempo = Integer.parseInt(txtTempo.getText().trim());
            Difficulty difficulty = comboDifficulty.getValue();
            ArrayList<Genre> selectedGenres = new ArrayList<>(genreList.getSelectionModel().getSelectedItems());

            if (title.isEmpty() || artist.isEmpty() || difficulty == null || selectedGenres.isEmpty()) {
                showAlert("Error", "Please fill out all fields and select difficulty and genres.");
                return;
            }

            // No measures at creation; start empty
            ArrayList<Measure> measures = new ArrayList<>();

            songlist.addSong(title, artist, minutes, seconds, tempo, selectedGenres, difficulty, measures);
            showAlert("Success", "Song created successfully!");

            clearForm();

        } catch (NumberFormatException ex) {
            showAlert("Input Error", "Minutes, seconds, and tempo must be numbers.");
        }
    }

    private void clearForm() {
        txtTitle.clear();
        txtArtist.clear();
        txtMinutes.clear();
        txtSeconds.clear();
        txtTempo.clear();
        comboDifficulty.getSelectionModel().clearSelection();
        genreList.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
