package com.guitar_app_one_direction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import com.model.Song;
import com.model.Difficulty;
import com.model.Genre;

public class SongCardController {

    @FXML
    private BorderPane songCardBorderPane;  // Optional, if you want to access the whole card

    @FXML
    private ImageView thumbnailImageView;

    @FXML
    private Label titleLabel;

    @FXML
    private Label artistLabel;

    @FXML
    private Label difficultyLabel;

    @FXML
    private Label genreLabel;

    public void setSongData(Song song) {
        // Set text content
        titleLabel.setText(song.getTitle());
        artistLabel.setText("By " + song.getArtist());

        // Set difficulty as string
        difficultyLabel.setText(song.getDifficulty().toString());

        // Set genre(s)
        StringBuilder genreText = new StringBuilder();
        for (Genre genre : song.getGenres()) {
            genreText.append(genre.name()).append(" ");
        }
        genreLabel.setText(genreText.toString().trim());

        // Set difficulty badge color
        String difficultyColor = getDifficultyColor(song.getDifficulty());
        difficultyLabel.setStyle(difficultyLabel.getStyle() + "; -fx-background-color: " + difficultyColor + ";");

        // Set genre badge color
        String genreColor = getGenreColor(song.getGenres());
        genreLabel.setStyle(genreLabel.getStyle() + "; -fx-background-color: " + genreColor + ";");

        // Load thumbnail image (handle null or missing)
        if (song.getImagePath() != null && !song.getImagePath().isEmpty()) {
            try {
                Image img = new Image(song.getImagePath(), true);  // 'true' = background loading
                thumbnailImageView.setImage(img);
            } catch (Exception e) {
                System.err.println("Error loading image for song: " + song.getTitle());
                e.printStackTrace();
            }
        }
    }

    private String getDifficultyColor(Difficulty difficulty) {
        switch (difficulty) {
            case BEGINNER:
                return "green";
            case INTERMEDIATE:
                return "orange";
            case ADVANCED:
                return "red";
            default:
                return "gray";
        }
    }

    private String getGenreColor(java.util.List<Genre> genres) {
        if (genres.contains(Genre.ROCK)) {
            return "blue";
        } else if (genres.contains(Genre.CLASSICAL)) {
            return "teal";
        } else if (genres.contains(Genre.POP)) {
            return "purple";
        } else {
            return "gray";
        }
    }
}
