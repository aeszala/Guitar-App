package com.model;

import com.program.MusicPlayer;

public class Driver {
    public static void main(String[] args) {
        // Initialize song list and player
        Songlist songlist = Songlist.getInstance();
        // songlist.addMoonlightSonata();  // Ensures the song is added

        // // Create a MusicPlayer instance
        // MusicPlayer player = new MusicPlayer();

        // // Play the song directly by its title
        // player.playSong("Moonlight Sonata");

        // Logging in as a user scenario
        MusicAppFACADE app = new MusicAppFACADE();
        app.login("username1", "password1");

        // Creating new account scenario
        app.createAccount("John", "John2004", "ILoveCats123", "John2004@gmail.com", "What was the name of your first cat?", "Muffin");
    }
}
