/**
 * @author (name)
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
        // Initialize song list and player
        Songlist songlist = Songlist.getInstance();
        songlist.addMoonlightSonata();  // Ensures the song is added

        // Create a MusicPlayer instance
        MusicPlayer player = new MusicPlayer();

        // Play the song directly by its title
        player.playSong("Moonlight Sonata");

        // Logging in as a user scenario
        MusicAppFACADE app = new MusicAppFACADE();
        app.login("username1", "password1");

        // Creating new account scenario
        app.createAccount("John", "John2004", "ILoveCats123", "John2004@gmail.com", 
                           "What was the name of your first cat?", "Muffin");
    }
}
