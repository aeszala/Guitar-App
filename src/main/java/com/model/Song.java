/**
 * @author liamnp
 */

package com.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import org.jfugue.player.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The {@code Song} class represents a song with various attributes such as title, artist, 
 * tempo, difficulty, and reviews. It also contains methods for playing the song, 
 * changing tempo, and generating JSON representations.
 */
public class Song {
    // Attributes
    private UUID id;
    private String title;
    private String artist;
    private int runLengthMin;
    private int runLengthSec;
    private int tempo;
    private double rating;
    private ArrayList<Review> reviews;
    private boolean metronomeOn;
    private ArrayList<Genre> genres;
    private Difficulty difficulty;
    private ArrayList<Measure> measures;
    private boolean completed;

    /**
     * Constructor for new songs.
     * 
     * @param title The title of the song.
     * @param artist The artist of the song.
     * @param runLengthMin The song length in minutes.
     * @param runLengthSec The song length in seconds.
     * @param tempo The tempo of the song in beats per minute (BPM).
     * @param genres A list of genres associated with the song.
     * @param difficulty The difficulty level of the song.
     * @param measures A list of measures in the song.
     */
    public Song(String title, String artist, int runLengthMin, int runLengthSec, 
                int tempo, ArrayList<Genre> genres, Difficulty difficulty, 
                ArrayList<Measure> measures) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.artist = artist;
        this.runLengthMin = runLengthMin;
        this.runLengthSec = runLengthSec;
        this.tempo = tempo;
        this.rating = 0;
        this.reviews = new ArrayList<>();
        this.metronomeOn = false;
        this.genres = genres;
        this.difficulty = difficulty;
        this.measures = measures;
        this.completed = false;
    }

    /**
     * Constructor for existing songs.
     * 
     * @param id The unique identifier of the song.
     * @param title The title of the song.
     * @param artist The artist of the song.
     * @param runLengthMin The song length in minutes.
     * @param runLengthSec The song length in seconds.
     * @param tempo The tempo of the song in beats per minute (BPM).
     * @param rating The rating of the song.
     * @param reviews A list of reviews for the song.
     * @param metronomeOn Whether the metronome is on.
     * @param genres A list of genres associated with the song.
     * @param difficulty The difficulty level of the song.
     * @param measures A list of measures in the song.
     * @param completed Whether the song is completed.
     */
    public Song(UUID id, String title, String artist, int runLengthMin, int runLengthSec, 
                int tempo, double rating, ArrayList<Review> reviews, boolean metronomeOn, 
                ArrayList<Genre> genres, Difficulty difficulty, ArrayList<Measure> measures, 
                boolean completed) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.runLengthMin = runLengthMin;
        this.runLengthSec = runLengthSec;
        this.tempo = tempo;
        this.rating = rating;
        this.reviews = reviews;
        this.metronomeOn = metronomeOn;
        this.genres = genres;
        this.difficulty = difficulty;
        this.measures = measures;
        this.completed = completed;
    }

    /**
     * Constructor to initialize song by UUID.
     * 
     * @param id The unique identifier of the song.
     */
    public Song(UUID id) {
        this.id = id;
    }

    // Methods
    public void playSong() {
        System.out.println("Playing song: " + title);
    }

    public void changeTempo(int tempo) {
        this.tempo = tempo;
        System.out.println("Tempo changed to: " + tempo);
    }

    /**
     * Displays the song as a musical tab (stub method).
     * 
     * @return An empty list of Sound objects as a placeholder.
     */
    public ArrayList<Sound> displayTab() {
        return new ArrayList<>(); // Placeholder return
    }

    /**
     * Displays the song as sheet music (stub method).
     * 
     * @return An empty list of Sound objects as a placeholder.
     */
    public ArrayList<Sound> displaySheet() {
        return new ArrayList<>(); // Placeholder return
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getRunLengthMin() {
        return runLengthMin;
    }

    public void setRunLengthMin(int runLengthMin) {
        this.runLengthMin = runLengthMin;
    }

    public int getRunLengthSec() {
        return runLengthSec;
    }

    public void setRunLengthSec(int runLengthSec) {
        this.runLengthSec = runLengthSec;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public boolean isMetronomeOn() {
        return metronomeOn;
    }

    public void setMetronomeOn(boolean metronomeOn) {
        this.metronomeOn = metronomeOn;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(ArrayList<Measure> measures) {
        this.measures = measures;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public static void add(Song newSong) {
        // Placeholder method for adding a song.
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    public void printAndSaveSheetMusic() {
        String filename = title + "_sheet.txt";  // Generate filename
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        
        for (int i = 0; i < measures.size(); i++) {  
            char[][] formattedArray = measures.get(i).getFormattedMeasure();  // Get the measure

            // Print & Write the measure
            writer.write("Measure " + (i + 1) + ":\n");
            System.out.println("Measure " + (i + 1) + ":");
            
            for (char[] row : formattedArray) {
                String line = new String(row);
                writer.write(line + "\n");  // Write to file
                System.out.println(line);  // Print to console
            }

            writer.write("\n");  // Add spacing between measures
            System.out.println();
        }   

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Plays the song using a player, looping through each measure.
     * 
     * @param player The player object used for playing the song.
     */
    public void play(Player player) {
        System.out.println("Playing song: " + title + " by " + artist);
        System.out.println("Tempo: " + tempo + " BPM");

        for (Measure measure : measures) {
            measure.play(); // Calls the play method for each measure
        }

        System.out.println("Song playback finished.");
    }

    // toString method
    @Override
    public String toString() {
        if (this.title == null || this.difficulty == null) {
            return "Song{" +
                   "id=" + id +
                   '}';
        } else {
            return "Song{" +
                   "id=" + id +
                   ", title='" + title + '\'' +
                   ", artist='" + artist + '\'' +
                   ", runLengthMin=" + runLengthMin +
                   ", runLengthSec=" + runLengthSec +
                   ", tempo=" + tempo +
                   ", rating=" + rating +
                   ", reviews=" + reviews +
                   ", metronomeOn=" + metronomeOn +
                   ", genres=" + genres +
                   ", difficulty=" + difficulty +
                   ", measures=" + measures +
                   ", completed=" + completed +
                   '}';
        }
    }

    /**
     * Converts the song object into a JSON representation.
     * 
     * @return A JSON object containing the song's data.
     */
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id.toString());
        jsonObject.put("title", title);
        jsonObject.put("artist", artist);
        jsonObject.put("runLengthMin", runLengthMin);
        jsonObject.put("runLengthSec", runLengthSec);
        jsonObject.put("tempo", tempo);
        jsonObject.put("rating", rating);
        jsonObject.put("metronomeOn", metronomeOn);
        jsonObject.put("difficulty", difficulty.toString());
        jsonObject.put("completed", completed);

        // Convert Review Array
        JSONArray reviewArray = new JSONArray();
        for (Review review : reviews) {
            reviewArray.add(review.toJson());
        }
        jsonObject.put("reviews", reviewArray);    

        // Convert Genre Array
        JSONArray genreArray = new JSONArray();
        for (Genre genre : genres) {
            genreArray.add(genre.name());
        }
        jsonObject.put("genres", genreArray);

        // Convert Measure Array
        JSONArray measureArray = new JSONArray();
        for (Measure measure : measures) {
            measureArray.add(measure.toJson());
        }
        jsonObject.put("measures", measureArray);

        return jsonObject;
    }
}
