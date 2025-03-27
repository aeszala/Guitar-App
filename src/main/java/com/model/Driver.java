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
        MusicAppFACADE app = new MusicAppFACADE();
        // Initialize song list and player
        // Songlist songlist = app.getSonglistInstance();
        // songlist.addMoonlightSonata();  // Ensures the song is added

        // Play the song directly by its title
        // app.playSong("I Won't Back Down");

        // Logging in as a user scenario
        app.login("username1", "password1");

        // Creating new account scenario
        app.createAccount("John", "John2004", "ILoveCats123", "John2004@gmail.com", "What was the name of your first cat?", "Muffin");
    
        // convert music to sheet music
        app.printSheetMusic("I Won't Back Down");
        
        // adds song "horses journey"
        app.addSong( "A horses journey", "Fellicia", 0, 0, null, 0, null);

    }
}
