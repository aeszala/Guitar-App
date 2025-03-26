package com.model;

import java.util.ArrayList;

/**
 * Singleton class representing a list of songs.
 * Provides methods to add and retrieve songs based on keyword searches.
 */
public class Songlist {
    private static Songlist songList;
    private ArrayList<Song> songs;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the song list.
     */
    private Songlist() {
        songs = DataLoader.getSongs(); // Load songs from DataLoader
    }

    /**
     * Returns the singleton instance of SongList.
     * @return The single instance of SongList.
     */
    public static Songlist getInstance() {
        if (songList == null) {
            songList = new Songlist();
        }
        return songList;
    }

    /**
     * Adds a new song to the list.
     *
     * @param title        The title of the song.
     * @param artist       The artist of the song.
     * @param runLengthMin The song length in minutes.
     * @param runLengthSec The song length in seconds.
     * @param tempo        The tempo of the song in beats per minute (BPM).
     * @param genres       A list of genres associated with the song.
     * @param difficulty   The difficulty level of the song.
     * @param measures     A list of measures in the song.
     */
    public void addSong(String title, String artist, int runLengthMin, int runLengthSec,
                        int tempo, ArrayList<Genre> genres, Difficulty difficulty,
                        ArrayList<Measure> measures) {
        Song newSong = new Song(title, artist, runLengthMin, runLengthSec, tempo, genres, difficulty, measures);
        songs.add(newSong);
    }

    /**
     * Retrieves a song by its title.
     * 
     * @param title The title of the song to retrieve.
     * @return The song matching the title, or null if not found.
     */
    public Song getSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null; // Return null if no song found
    }

    /**
     * Retrieves a list of songs that match the given keyword in the title or artist.
     * 
     * @param keyWord The keyword to search for.
     * @return A list of matching songs.
     */
    public ArrayList<Song> getSongs(String keyWord) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getTitle().toLowerCase().contains(keyWord.toLowerCase()) ||
                song.getArtist().toLowerCase().contains(keyWord.toLowerCase())) {
                result.add(song);
            }
        }
        return result;
    }

    /**
     * Returns the full list of songs.
     * @return The list of songs.
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }
}
