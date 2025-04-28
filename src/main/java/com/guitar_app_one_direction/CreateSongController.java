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
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.model.*;
import java.io.IOException;
import java.util.ArrayList;

public class CreateSongController {

    @FXML private TextField SongTitleField;
    @FXML private TextField tempoTextField;
    @FXML private ComboBox<Difficulty> comboDifficulty;
    @FXML private ComboBox<Genre> comboGenre;
    @FXML private VBox measureContainer;
    @FXML private Button uploadSongBtn;
    @FXML private Button playSongBtn;
    @FXML private Button createTabsBtn;
    @FXML private ImageView profilePicture;
    @FXML private Button addMeasureButton;

    private MusicAppFACADE facade = MusicAppFACADE.getInstance();
    private ArrayList<HBox> measureBoxes = new ArrayList<>();
    private int currentEditableMeasureIndex = 0;

    @FXML
    public void initialize() {
        // Initialize combo boxes
        comboDifficulty.setItems(FXCollections.observableArrayList(Difficulty.values()));
        comboGenre.setItems(FXCollections.observableArrayList(Genre.values()));
        
        // Create first measure
        addMeasure(new ActionEvent());
    }

    @FXML
    void addMeasure(ActionEvent event) {
        // Don't allow new measure if current one is empty
        if (measureBoxes.size() > 0 && measureBoxes.get(currentEditableMeasureIndex).getChildren().isEmpty()) {
            showAlert("Error", "Current measure is empty. Add at least one note before creating a new measure.");
            return;
        }

        // Remove add button from previous measures
        measureBoxes.forEach(box -> {
            if (!box.getChildren().isEmpty() && box.getChildren().get(box.getChildren().size() - 1) instanceof Button) {
                box.getChildren().remove(box.getChildren().size() - 1);
            }
        });

        // Create measure label
        Label measureLabel = new Label("Measure " + (measureBoxes.size() + 1));
        measureLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 20;");

        // Create measure container
        HBox newMeasure = new HBox(5);
        newMeasure.setStyle("-fx-background-color: #1D3435; -fx-pref-height: 60; -fx-padding: 5;");
        
        // Add note button
        Button addNoteBtn = createAddNoteButton();
        newMeasure.getChildren().add(addNoteBtn);

        measureContainer.getChildren().addAll(measureLabel, newMeasure);
        measureBoxes.add(newMeasure);
        currentEditableMeasureIndex = measureBoxes.size() - 1;
        
        // Create song if first measure
        if (measureBoxes.size() == 1) {
            facade.createSong(SongTitleField.getText().trim());
            facade.createMeasure();
        } else {
            facade.createMeasure();
        }
    }

    private Button createAddNoteButton() {
        Button btn = new Button("+");
        btn.setStyle("-fx-font-size: 20; -fx-background-color: white; -fx-text-fill: #bd522c;");
        btn.setPrefSize(50, 50);
        btn.setOnAction(this::handleAddNote);
        return btn;
    }

    @FXML
    private void handleAddNote(ActionEvent event) {
        try {
            App.showPopup("addNotePopup");
            
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not open note popup. File not found.");
        } catch (NullPointerException e) {
            e.printStackTrace();
            showAlert("Error", "NotePopup.fxml not found in expected location.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred.");
        }
    }

    public void addNote(String type, double length, double pitch, int string, int fret) {
        HBox currentMeasure = measureBoxes.get(currentEditableMeasureIndex);
        
        // Remove add button if at max notes (6)
        if (currentMeasure.getChildren().size() >= 6) {
            currentMeasure.getChildren().remove(currentMeasure.getChildren().size() - 1);
        }
        
        // Add note display
        Label noteLabel = new Label(type);
        noteLabel.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-font-size: 16;");
        noteLabel.setPrefSize(50, 50);
        currentMeasure.getChildren().add(noteLabel);
        
        // Add note to facade
        facade.addNote(type, length, pitch, string, fret);
        
        // Add new add button if under limit
        if (currentMeasure.getChildren().size() < 6) {
            Button addNoteBtn = createAddNoteButton();
            currentMeasure.getChildren().add(addNoteBtn);
        }
    }

    @FXML
    void uploadSong(ActionEvent event) {
        try {
            String title = SongTitleField.getText().trim();
            String tempoText = tempoTextField.getText().trim();
            Difficulty difficulty = comboDifficulty.getValue();
            Genre genre = comboGenre.getValue();
            int tempo = Integer.parseInt(tempoText);

            if (title.isEmpty() || tempo < 5 || tempo > 300 || difficulty == null || genre == null) {
                showAlert("Error", "Please fill out all fields and select difficulty and genre.");
                return;
            }

            facade.saveMeasure();
            
            ArrayList<Genre> genres = new ArrayList<>();
            genres.add(genre);
            
            facade.getSong().setArtist(facade.getUser().getName());
            facade.getSong().setTempo(tempo);
            facade.getSong().setDifficulty(difficulty);
            facade.getSong().setGenres(genres);
            
            if (facade.saveSong()) {
                showAlert("Success", "Song saved successfully!");
                clearForm();
            } else {
                showAlert("Error", "Failed to save song.");
            }
            
        } catch (NumberFormatException ex) {
            showAlert("Input Error", "Tempo must be a number.");
        }
    }

    @FXML
    void playCreateSong(ActionEvent event) {
        try {
            String tempoText = tempoTextField.getText().trim();
            int tempo = Integer.parseInt(tempoText);
            if (facade.getSong() != null && !facade.getSong().getMeasures().isEmpty() && tempo >=5 && tempo <= 300) {
                facade.getSong().setTempo(tempo);
                facade.play(facade.getSong());
            } else {
                showAlert("Error", "No song to play. Please create a song first.");
            }
        } catch (Exception e) {
            showAlert("Error", "Tempo must be within range 5-300.");
        }
    }

    @FXML
    void goToTabsForCreateSong(ActionEvent event) throws IOException {
        if (facade.getSong() != null && !facade.getSong().getMeasures().isEmpty()) {
            facade.printTabSheet(facade.getSong().getTitle());
            App.setRoot("tabsPage");
        } else {
            showAlert("Error", "No song to create tabs for. Please create a song first.");
        }
    }

    @FXML
    void goToProfile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("profile.fxml"));
        Parent root = loader.load();
        ProfileController profileController = loader.getController();
        profileController.setUser(facade.getUser());

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
        measureContainer.getChildren().clear();
        measureBoxes.clear();
        currentEditableMeasureIndex = 0;
        addMeasure(new ActionEvent());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}