/**
 * @author liamnp
 */

 package com.model;

 import java.util.ArrayList;
 
 /**
  * Singleton class representing a collection of songs.
  * Provides methods to add, retrieve, and search for songs based on keywords.
  * It ensures that only one instance of the song list is maintained throughout the application.
  */
 public class Songlist {
     private static Songlist songList;
     private static ArrayList<Song> songs;
 
     /**
      * Private constructor to prevent external instantiation.
      * Initializes the song list using the DataLoader to fetch songs from the data source.
      */
     private Songlist() {
         songs = DataLoader.getSongs(); // Load songs from DataLoader
     }
 
     /**
      * Returns the singleton instance of Songlist.
      * If the instance doesn't exist, it creates one.
      *
      * @return The single instance of Songlist.
      */
     public static Songlist getInstance() {
         if (songList == null) {
             songList = new Songlist();
         }
         return songList;
     }
 
     /**
      * Adds a new song to the song list with the specified details.
      *
      * @param title        The title of the song.
      * @param artist       The artist of the song.
      * @param runLengthMin The duration of the song in minutes.
      * @param runLengthSec The duration of the song in seconds.
      * @param tempo        The tempo of the song in beats per minute (BPM).
      * @param genres       A list of genres associated with the song.
      * @param difficulty   The difficulty level of the song.
      * @param measures     A list of measures representing the song's musical structure.
      */
     public void addSong(String title, String artist, int runLengthMin, int runLengthSec,
                         int tempo, ArrayList<Genre> genres, Difficulty difficulty,
                         ArrayList<Measure> measures) {
         Song newSong = new Song(title, artist, runLengthMin, runLengthSec, tempo, genres, difficulty, measures);
         songs.add(newSong);
     }
 
     /**
      * Retrieves a song by its exact title (case-insensitive).
      *
      * @param title The title of the song to search for.
      * @return The matching song if found, or {@code null} if no song with the title exists.
      */
     public static Song getSong(String title) {
         for (Song song : songs) {
             if (song.getTitle().equalsIgnoreCase(title)) {
                 return song;
             }
         }
         return null; // Return null if no song found
     }
 
     /**
      * Retrieves a list of songs whose titles or artists contain the specified keyword (case-insensitive).
      *
      * @param keyWord The keyword used for the search.
      * @return An {@code ArrayList} of songs matching the search criteria. Returns an empty list if no matches are found.
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
 
     public void displaySongs(ArrayList<Song> songs) {
         if (songs != null) {
             for (Song song : songs) {
                 System.out.println(song);
             }
         }
     }
 
     public static void addSong (Song song) {
         songs.add(song);
     }
 
     /**
      * Returns the complete list of songs available in the system.
      *
      * @return An {@code ArrayList} containing all songs.
      */
     public ArrayList<Song> getSongs() {
         return songs;
     }
 
     /**
      * Saves the current list of songs to the data storage using the DataWriter.
      * It provides a confirmation message upon successful saving.
      */
     public static void saveSongs() {
         DataWriter.saveSongs(songs);
         System.out.println("Songs saved successfully!");
     }
 }