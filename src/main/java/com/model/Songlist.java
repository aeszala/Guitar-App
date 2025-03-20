package com.model;

import java.util.ArrayList;

public class Songlist {
    private static Songlist songList;
    private ArrayList<Song> songs;

    private Songlist() {
        songs = DataLoader.getSongs();
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
    }

    public ArrayList<Song> getSongs() {
        return songs;
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

    public Song getSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().toLowerCase().equals(title.toLowerCase()))
                return song;
        }
        return null;
    }
}
