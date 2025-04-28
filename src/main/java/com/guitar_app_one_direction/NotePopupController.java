package com.guitar_app_one_direction;

import com.model.MusicAppFACADE;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NotePopupController {
    @FXML private ComboBox<String> soundTypeComboBox;
    @FXML private ComboBox<String> soundLengthComboBox;
    @FXML private ComboBox<String> stringComboBox;
    @FXML private ComboBox<String> fretComboBox;
    @FXML private Button addNoteButton;
    @FXML private Button cancelButton;
    
    private CreateSongController createSongController;
    
    // Map length symbols to their numeric values
    private final double[] lengthValues = {1.0, 0.5, 2.0, 4.0, 0.25};
    private final String[] lengthSymbols = {"♩ Quarter", "♪ Eighth", "𝅗 Half", "𝅝 Whole", "𝅘𝅥 16th"};

    @FXML
    public void initialize() {
        // Initialize sound types
        soundTypeComboBox.getItems().addAll(
            "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"
        );
        soundTypeComboBox.setValue("A");

        // Initialize sound lengths
        soundLengthComboBox.getItems().addAll(lengthSymbols);
        soundLengthComboBox.setValue("♩ Quarter");

        // Initialize strings (1-6)
        stringComboBox.getItems().addAll("1", "2", "3", "4", "5", "6");
        stringComboBox.setValue("1");

        // Initialize frets (0-24)
        for (int i = 0; i <= 24; i++) {
            fretComboBox.getItems().add(String.valueOf(i));
        }
        fretComboBox.setValue("0");
        
        // Set button actions
        addNoteButton.setOnAction(this::handleAddNote);
        cancelButton.setOnAction(this::handleCancel);
    }
    
    public void setCreateSongController(CreateSongController controller) {
        this.createSongController = controller;
    }
    
    @FXML
    private void handleAddNote(ActionEvent event) {
        try {
            String type = soundTypeComboBox.getValue();
            MusicAppFACADE facade =  MusicAppFACADE.getInstance();
            // Get the index of the selected length to map to its numeric value
            int lengthIndex = soundLengthComboBox.getItems().indexOf(soundLengthComboBox.getValue());
            double length = lengthValues[lengthIndex];
            
            int string = Integer.parseInt(stringComboBox.getValue());
            int fret = Integer.parseInt(fretComboBox.getValue());
            double pitch = calculatePitch(string, fret);
            
            facade.addNote(type, length, pitch, string, fret);
            
            System.out.println("added Note");
            // Close the popup
            App.closeCurrentPopup();
            // CreateSongController.update();
        } catch (Exception e) {
            e.printStackTrace();
            // Show error alert if needed
        }
    }
    
    @FXML
    private void handleCancel(ActionEvent event) {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }
    
    // Calculate pitch based on string and fret
    private double calculatePitch(int string, int fret) {
        // Base frequencies for each open string (E2, A2, D3, G3, B3, E4)
        double[] baseFrequencies = {82.41, 110.00, 146.83, 196.00, 246.94, 329.63};
        
        // Get the base frequency for the selected string
        double baseFrequency = baseFrequencies[string - 1];
        
        // Calculate the frequency based on fret position
        return baseFrequency * Math.pow(2, fret / 12.0);
    }
}