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

    public void addSongs(String title, String artist, int runLengthMin, int runLengthSec, double rating, ArrayList<Genre> genre) {
        Song newSong = new Song(title, artist, runLengthMin, runLengthSec, rating, genre);
        songs.add(newSong);
    }

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

    public void saveSongs() {
        System.out.println("Saving songs...");
    }
}
