package com.program;

import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;
import com.model.*;

/**
 * The MusicPlayer class is responsible for playing songs using the JFugue library.
 * It retrieves songs from the Songlist and constructs musical patterns to be played.
 */
public class MusicPlayer {
    private static Player player;

    /**
     * Constructs a MusicPlayer object and initializes the JFugue Player.
     */
    public MusicPlayer() {
        player = new Player();
    }

    /**
     * Plays a song by its title.
     *
     * @param songTitle The title of the song to play.
     */
    public static void playSong(String songTitle) {
        if (player == null)
            player = new Player();
        // Retrieve the Songlist instance
        Songlist songlist = Songlist.getInstance();

        // Get the song by its title using the getSong method
        Song song = songlist.getSong(songTitle);

        // If the song is not found, print an error message
        if (song == null) {
            System.out.println("Song not found: " + songTitle);
            return;
        }

        // Display the song information
        System.out.println("Playing: " + song.getTitle() + " by " + song.getArtist());

        // Create a Pattern to represent the song's musical structure
        Pattern pattern = new Pattern("I[Guitar] "); // Set instrument to Guitar

        // Loop through the song's measures and add the sounds to the pattern
        for (Measure measure : song.getMeasures()) {
            // Assuming you have a method like getNotes() in Measure
            for (Sound sound : measure.getNotes()) {  // Change getNotes() to match your actual method
                if (sound instanceof Note) {
                    Note note = (Note) sound;
                    // Add the note directly to the pattern (JFugue automatically handles the notation)
                    pattern.add(note.getType() + "/" + note.getLength() + " "); // Example: "C#/1.0"
                } else if (sound instanceof Chord) {
                    Chord chord = (Chord) sound;
                    StringBuilder chordPattern = new StringBuilder("[");

                    // Add each note of the chord to the pattern
                    for (Note note : chord.getNotes()) {
                        chordPattern.append(note.getType()).append(" ");
                    }
                    chordPattern.append("]/").append(chord.getNotes().get(0).getLength()).append(" ");
                    pattern.add(chordPattern.toString()); // Add the chord to the pattern
                }
            }
        }

        // Play the song using JFugue player
        player.play(pattern);
    }

    /**
     * Main method for testing the MusicPlayer functionality.
     */
    public static void main(String[] args) {
        // Create an instance of MusicPlayer
        MusicPlayer player = new MusicPlayer();

        // Define a song title to search and play
        String songTitle = "Free Fallin"; // Replace this with the actual song title you want to play

        // Play the song
        player.playSong(songTitle);
    }
}