package com.model;

import java.util.ArrayList;

public class Songlist {
    private static Songlist songList;
    private static ArrayList<Song> songs;

    // Private constructor for the singleton class. Initializes the song list by loading songs using DataLoader.
    private Songlist() {
        songs = DataLoader.getSongs(); // Assumes DataLoader.getSongs() returns the song list from JSON.
        System.out.println("DEBUG: Loaded songs: " + songs.size());
    }

    // Provides access to the singleton instance of the Songlist class.
    public static Songlist getInstance() {
        if (songList == null) {
            songList = new Songlist();
        }
        return songList;
    }

    // Retrieves all songs from the song list.
    public ArrayList<Song> getSongs() {
        return songs;
    }

    // Retrieves a specific song by its title (case-insensitive).
    public Song getSong(String title) {
        System.out.println("DEBUG: Searching for song - " + title);
        for (Song song : songs) {
            System.out.println("DEBUG: Checking song - " + song.getTitle());
            if (song.getTitle().equalsIgnoreCase(title)) {
                System.out.println("DEBUG: Found song - " + song.getTitle());
                return song;
            }
        }
        System.out.println("DEBUG: Song not found.");
        return null;
    }
}
