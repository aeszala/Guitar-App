package com.model;

import com.program.MusicPlayer;

public class Driver {
    public static void main(String[] args) {
        // Initialize song list and player
        Songlist songlist = Songlist.getInstance();
        songlist.addMoonlightSonata();  // Ensures the song is added

        // Create a MusicPlayer instance
        MusicPlayer player = new MusicPlayer();

        // Play the song directly by its title
        player.playSong("Moonlight Sonata");
    }
}
