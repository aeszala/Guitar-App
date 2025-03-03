package com.model;

import java.util.ArrayList;
import java.util.UUID;

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

    // Constructor
    public Song(UUID id, String title, String artist, int runLengthMin, int runLengthSec, int tempo, 
                double rating, ArrayList<Review> reviews, boolean metronomeOn, 
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

    // Method to play the song (stub)
    public void playSong() {
        System.out.println("Playing song: " + title);
    }

    // Method to change the tempo
    public void changeTempo(int tempo) {
        this.tempo = tempo;
        System.out.println("Tempo changed to: " + tempo);
    }

    // Method to display the song as tab (stub)
    public ArrayList<Sound> displayTab() {
        return new ArrayList<>(); // Placeholder return
    }

    // Method to display the sheet music (stub)
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
}
