package com.guitar_app_one_direction;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class NotePopupController {
    @FXML private ComboBox<String> soundTypeComboBox;
    @FXML private ComboBox<String> soundLengthComboBox;
    @FXML private ComboBox<String> stringComboBox;
    @FXML private ComboBox<String> fretComboBox;

    @FXML
    public void initialize() {
        // sound types
        soundTypeComboBox.getItems().addAll(
            "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"
        );
        soundTypeComboBox.setValue("A"); // Default selection

        // sound lengths
        soundLengthComboBox.getItems().addAll(
            "♩ Quarter", "♪ Eighth", "𝅗 Half", "𝅝 Whole", "𝅘𝅥 16th"
        );
        soundLengthComboBox.setValue("♩ Quarter");

        // strings (1-6)
        stringComboBox.getItems().addAll("1", "2", "3", "4", "5", "6");
        stringComboBox.setValue("1");

        // frets (0-24)
        for (int i = 0; i <= 24; i++) {
            fretComboBox.getItems().add(String.valueOf(i));
        }
        fretComboBox.setValue("0");
    }
}