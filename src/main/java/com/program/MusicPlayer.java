﻿package com.program;

import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;
import com.model.*;

/**
 * The MusicPlayer class is responsible for playing songs using the JFugue library.
 * It retrieves songs from the Songlist and constructs musical patterns to be played.
 */
public class MusicPlayer {
    private Player player;

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
    public void playSong(String songTitle) {
        Song song = Songlist.getInstance().getSong(songTitle);
        if (song == null) {
            System.out.println("Song not found: " + songTitle);
            return;
        }
        play(song); // Delegate to the new method
    }

    /**
     * Plays a given Song object using JFugue.
     *
     * @param song The Song object to play.
     */
    public void play(Song song) {
        if (song == null) {
            System.out.println("No song to play.");
            return;
        }

        System.out.println("Playing: " + song.getTitle() + " by " + song.getArtist());
        System.out.println("Tempo: " + song.getTempo() + " BPM");

        Pattern pattern = new Pattern("I[Guitar] T" + song.getTempo() + " "); // Set instrument and tempo

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

    /**
     * Main method for testing the MusicPlayer functionality.
     */
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        String songTitle = "Mary Jane's Last Dance"; // Example
        player.playSong(songTitle); // or player.play(song);
    }
}
