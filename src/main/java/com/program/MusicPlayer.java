package com.program;

import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;
import java.util.ArrayList;
import com.model.*;

public class MusicPlayer {
    private Player player;

    public MusicPlayer() {
        player = new Player();
    }

    public void playSong(String songTitle) {
        Songlist songlist = Songlist.getInstance();
        Song song = songlist.getSong(songTitle);

        if (song == null) {
            System.out.println("Song not found: " + songTitle);
            return;
        }

        System.out.println("Playing: " + song.getTitle() + " by " + song.getArtist());

        Pattern pattern = new Pattern("I[Guitar] "); // Set instrument to Guitar

        for (Measure measure : song.getMeasures()) {
            for (Sound sound : measure.getNotes()) {
                if (sound instanceof Note) {
                    Note note = (Note) sound;
                    pattern.add(note.getType() + "/" + note.getLength() + " ");
                } else if (sound instanceof Chord) {
                    Chord chord = (Chord) sound;
                    StringBuilder chordPattern = new StringBuilder("[");
                    for (Note note : chord.getNotes()) {
                        chordPattern.append(note.getType()).append(" ");
                    }
                    chordPattern.append("]/").append(chord.getNotes().get(0).getLength()).append(" ");
                    pattern.add(chordPattern.toString());
                }
            }
        }

        player.play(pattern); 

        
    }
    
    public static void main(String[] args) {
        // Get the Songlist instance
        Songlist songlist = Songlist.getInstance();
    
        // Add Moonlight Sonata to the song list
        songlist.addMoonlightSonata();
    
        // Verify if the song is in the list
        Song song = songlist.getSong("Moonlight Sonata");
        if (song != null) {
            System.out.println(" Successfully added Moonlight Sonata!");
        } else {
            System.out.println(" Failed to add Moonlight Sonata.");
        }
    
        // Play the song
        MusicPlayer player = new MusicPlayer();
        player.playSong("Moonlight Sonata");
    }

    

}
