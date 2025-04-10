/**
 * @author aeszala
 */

package com.model;

import com.program.MusicPlayer;

/**
 * The {@code Driver} class serves as the entry point for the application.
 * It demonstrates the initialization and interaction with the music player,
 * song management, and user account operations using the {@code MusicAppFACADE}.
 * 
 * <p> Key operations include:
 * <ul>
 *   <li>Initializing the song list and adding a song.</li>
 *   <li>Playing a song using the {@code MusicPlayer}.</li>
 *   <li>Logging in as an existing user.</li>
 *   <li>Creating a new user account.</li>
 * </ul>
 */
public class Driver {

    /**
     * The main method to start the application and demonstrate functionality.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        MusicAppFACADE app = new MusicAppFACADE();
        // Initialize song list and player

        // Scenario 1
        app.createAccount("Fred Fredrickson", "FFredrickson", "I-love-dogs342", "ffredrickson@gmail.com", "What was the name of your first dog?", "Fluffy");
        app.createAccount("Fred Fredrickson", "FFred", "I-love-dogs342", "ffredrickson@gmail.com", "What was the name of your first dog?", "Fluffy");
        app.logOut();
        app.login("FFred", "I-love-dogs342");

        // Scenario 2
        app.findSongs("Tom Petty");
        app.displaySongs();
        app.login("FFredrickson", "password1");
        app.playSong("Free Fallin");
        app.printTabSheet("I will back down");

        // Scenario 3
        app.login("FFredrickson", "password1");
        app.createSong("A Horse's Journey");
        app.createMeasure();
        app.addNote("C5", 1.0, 523.25, 1, 3);
        app.addNote("D5", 0.5, 587.33, 2, 5);
        app.addNote("E5", 0.25, 659.25, 3, 7);
        app.createMeasure();
        app.addNote("G4", 1.0, 392.00, 4, 0);
        app.addNote("A4", 0.5, 440.00, 5, 2);
        app.addNote("B4", 0.25, 493.88, 6, 4);
        app.saveSong();
        app.save();
        app.playSong("A Horse's Journey");
        app.logOut();

        // Scenario 4
        app.login("FFred", "I-love-dogs342");
        app.findSongs("A Horse's Journey");
        app.playSong("A Horse's Journey");

    }
}
