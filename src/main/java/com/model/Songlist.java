/**
 * @author (name)
 */

package com.model;

import java.util.ArrayList;

/**
 * The {@code Songlist} class represents a singleton collection of songs.
 * It provides methods to add, retrieve, and search for songs.
 */
public class Songlist {
    private static Songlist songList;
    private ArrayList<Song> songs;

    /**
     * Private constructor for the singleton class. 
     * Initializes the song list by loading songs using DataLoader.
     */
    private Songlist() {
        songs = DataLoader.getSongs();
    }

    /**
     * Provides access to the singleton instance of the Songlist class.
     *
     * @return the singleton instance of Songlist
     */
    public static Songlist getInstance() {
        if (songList == null) {
            songList = new Songlist();
        }
        return songList;
    }

    /**
     * Adds a new song to the song list using the provided parameters.
     *
     * @param title       the title of the song
     * @param artist      the artist of the song
     * @param runLengthMin the song's runtime in minutes
     * @param runLengthSec the song's runtime in seconds
     * @param tempo       the tempo of the song in BPM
     * @param genres      the genres associated with the song
     * @param difficulty  the difficulty level of the song
     * @param measures    the measures representing the song's composition
     */
    public void addSongs(String title, String artist, int runLengthMin, int runLengthSec, int tempo,
                          ArrayList<Genre> genres, Difficulty difficulty, ArrayList<Measure> measures) {
        Song newSong = new Song(title, artist, runLengthMin, runLengthSec, tempo, genres, difficulty, measures);
        songs.add(newSong);
        System.out.println("DEBUG: Added song - " + title);
    }

    /**
     * Retrieves all songs from the song list.
     *
     * @return an ArrayList containing all songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Searches for songs in the song list based on a keyword.
     * The keyword is matched against both the song title and artist's name (case-insensitive).
     *
     * @param keyword the search keyword
     * @return an ArrayList of songs that match the search criteria
     */
    public ArrayList<Song> getSongs(String keyword) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getTitle().toLowerCase().contains(keyword.toLowerCase()) || 
                song.getArtist().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(song);
            }
        }
        return result;
    }

    /**
     * Saves the current state of the song list.
     * This method can be further implemented to persist data using DataWriter.
     */
    public void saveSongs() {
        System.out.println("Saving songs...");
    }

    /**
     * Retrieves a specific song by its title.
     * The search is case-insensitive.
     *
     * @param title the title of the song to search for
     * @return the matching Song object, or null if no song is found
     */
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

    /**
     * Adds a sample representation of Beethoven's "Moonlight Sonata" to the song list.
     * The song is generated with repeating patterns to simulate its structure.
     */
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

        // Measure 2 - Slight variation in the arpeggio
        ArrayList<Sound> measure2Notes = new ArrayList<>();
        measure2Notes.add(new Note("D#", 1.0, 311.13, 3, 1, "note"));
        measure2Notes.add(new Note("A#", 1.0, 466.16, 4, 2, "note"));
        measure2Notes.add(new Note("D#", 1.0, 622.25, 5, 3, "note"));
        measure2Notes.add(new Note("F#", 1.0, 739.99, 5, 4, "note"));
        measures.add(new Measure(4, 4, measure2Notes));

        // Measure 3 - Return to original arpeggio pattern
        ArrayList<Sound> measure3Notes = new ArrayList<>();
        measure3Notes.add(new Note("C#", 1.0, 277.18, 3, 1, "note"));
        measure3Notes.add(new Note("G#", 1.0, 415.30, 4, 2, "note"));
        measure3Notes.add(new Note("C#", 1.0, 554.37, 5, 3, "note"));
        measure3Notes.add(new Note("E", 1.0, 659.25, 5, 4, "note"));
        measures.add(new Measure(4, 4, measure3Notes));

        // Measure 4 - Introduction of the melody
        ArrayList<Sound> measure4Notes = new ArrayList<>();
        measure4Notes.add(new Note("B", 1.0, 246.94, 3, 1, "note"));
        measure4Notes.add(new Note("G#", 1.0, 415.30, 4, 2, "note"));
        measure4Notes.add(new Note("B", 1.0, 493.88, 5, 3, "note"));
        measure4Notes.add(new Note("E", 1.0, 659.25, 5, 4, "note"));
        measures.add(new Measure(4, 4, measure4Notes));

        // Continue the pattern for more measures
        for (int i = 0; i < 8; i++) {
            measures.add(measures.get(i % 4)); // Repeating pattern
        }

        // Add genres and difficulty
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(Genre.CLASSICAL);

        addSongs("Moonlight Sonata", "Ludwig van Beethoven", 6, 30, 60, genres, Difficulty.INTERMEDIATE, measures);
        System.out.println("DEBUG: Moonlight Sonata added successfully.");
    }
}
