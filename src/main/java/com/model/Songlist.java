package com.model;

import java.util.ArrayList;

public class Songlist {
    private static Songlist songList;
    private ArrayList<Song> songs;

    private Songlist() {
        songs = new ArrayList<>();
    }

    public static Songlist getInstance() {
        if (songList == null) {
            songList = new Songlist();
        }
        return songList;
    }

    public void addSongs(String title, String artist, int runLengthMin, int runLengthSec, int tempo,
                        ArrayList<Genre> genres, Difficulty difficulty, ArrayList<Measure> measures) {
        Song newSong = new Song(title, artist, runLengthMin, runLengthSec, tempo, genres, difficulty, measures);
        songs.add(newSong);
        System.out.println("DEBUG: Added song - " + title);
    }

    public Song getSong(String title) {
        System.out.println("DEBUG: Searching for song - " + title);
        for (Song song : songs) {
            System.out.println("DEBUG: Checking song: " + song.getTitle());
            if (song.getTitle().equalsIgnoreCase(title)) {
                System.out.println("DEBUG: Found song - " + song.getTitle());
                return song;
            }
        }
        System.out.println("DEBUG: Song not found.");
        return null;
    }

    public void addMoonlightSonata() {
        System.out.println("DEBUG: Adding Moonlight Sonata...");
    
        ArrayList<Measure> measures = new ArrayList<>();
    
        // Measure 1 - Signature arpeggio pattern
        ArrayList<Sound> measure1Notes = new ArrayList<>();
        measure1Notes.add(new Note("C#", 1.0, 277.18, 3, 1, "note")); // Bass note
        measure1Notes.add(new Note("G#", 1.0, 415.30, 4, 2, "note"));
        measure1Notes.add(new Note("C#", 1.0, 554.37, 5, 3, "note"));
        measure1Notes.add(new Note("E", 1.0, 659.25, 5, 4, "note"));
        measures.add(new Measure(4, 4, measure1Notes));
    
        // Measure 2 - Repeating the arpeggio with slight variation
        ArrayList<Sound> measure2Notes = new ArrayList<>();
        measure2Notes.add(new Note("D#", 1.0, 311.13, 3, 1, "note"));
        measure2Notes.add(new Note("A#", 1.0, 466.16, 4, 2, "note"));
        measure2Notes.add(new Note("D#", 1.0, 622.25, 5, 3, "note"));
        measure2Notes.add(new Note("F#", 1.0, 739.99, 5, 4, "note"));
        measures.add(new Measure(4, 4, measure2Notes));
    
        // Measure 3 - Similar progression continues
        ArrayList<Sound> measure3Notes = new ArrayList<>();
        measure3Notes.add(new Note("C#", 1.0, 277.18, 3, 1, "note"));
        measure3Notes.add(new Note("G#", 1.0, 415.30, 4, 2, "note"));
        measure3Notes.add(new Note("C#", 1.0, 554.37, 5, 3, "note"));
        measure3Notes.add(new Note("E", 1.0, 659.25, 5, 4, "note"));
        measures.add(new Measure(4, 4, measure3Notes));
    
        // Measure 4 - Introducing the melody
        ArrayList<Sound> measure4Notes = new ArrayList<>();
        measure4Notes.add(new Note("B", 1.0, 246.94, 3, 1, "note"));
        measure4Notes.add(new Note("G#", 1.0, 415.30, 4, 2, "note"));
        measure4Notes.add(new Note("B", 1.0, 493.88, 5, 3, "note"));
        measure4Notes.add(new Note("E", 1.0, 659.25, 5, 4, "note"));
        measures.add(new Measure(4, 4, measure4Notes));
    
        // Continue adding more measures to complete the full piece...
        for (int i = 0; i < 8; i++) {
            measures.add(measures.get(i % 4)); // Repeat pattern to simulate more measures
        }
    
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(Genre.CLASSICAL);
    
        addSongs("Moonlight Sonata", "Ludwig van Beethoven", 6, 30, 60, genres, Difficulty.INTERMEDIATE, measures);
        System.out.println("DEBUG: Moonlight Sonata added successfully.");
    }
}