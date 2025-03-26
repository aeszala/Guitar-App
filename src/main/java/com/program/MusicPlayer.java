package com.program;

import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;
import com.model.*;

public class MusicPlayer {
    private Player player;

    public MusicPlayer() {
        player = new Player();
    }

    public void playSong(String songTitle) {
        // Retrieve the Songlist instance
        Songlist songlist = Songlist.getInstance();

        // Get the song by its title using the getSong method
        Song song = songlist.getSong(songTitle);

        if (song == null) {
            System.out.println("Song not found: " + songTitle);
            return;
        }

        System.out.println("Playing: " + song.getTitle() + " by " + song.getArtist());

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

    public static void main(String[] args) {
        // Get the Songlist instance
        Songlist songlist = Songlist.getInstance();

        // Verify if a specific song exists in the list (e.g., "Song Title")
        String songTitle = "I Won't Back Down"; // Change this to the actual song title you want to play
        Song song = songlist.getSong(songTitle);
        if (song != null) {
            System.out.println("Successfully found " + songTitle);
        } else {
            System.out.println("Failed to find " + songTitle);
        }

        // Play the song
        MusicPlayer player = new MusicPlayer();
        player.playSong(songTitle);
    }
}
