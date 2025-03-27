/**
 * @author Liam
 */

package com.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * The {@code PlayAssignment} class represents a play-based assignment where a student is required to 
 * play a song at a certain tempo. This class tracks the song, tempo, played notes, and the accuracy 
 * of the student's performance.
 * 
 * <p>It extends from the {@link Assignment} class and adds specific properties related to a play assignment,
 * such as the song, tempo, played notes, and the total number of notes in the song.</p>
 */
public class PlayAssignment extends Assignment {
    private Song song;
    private int tempo;
    private ArrayList<Sound> playedNotes;
    private int totalNotes;

    /**
     * Constructor for loading existing PlayAssignments (with grade & completion status).
     * 
     * @param title The title of the assignment.
     * @param grade The grade received for the assignment.
     * @param teacherComment Comments from the teacher regarding the assignment.
     * @param studentComment Comments from the student about the assignment.
     * @param dueDate The due date of the assignment.
     * @param complete Indicates whether the assignment is completed.
     * @param song The song to be played in the assignment.
     * @param tempo The tempo at which the song should be played.
     * @param playedNotes The list of notes played by the student.
     * @param totalNotes The total number of notes in the song.
     */
    public PlayAssignment(String title, double grade, String teacherComment, String studentComment,
            Date dueDate, boolean complete, Song song, int tempo,
            ArrayList<Sound> playedNotes, int totalNotes) {
        super(title, grade, teacherComment, studentComment, dueDate, complete);
        this.song = song;
        this.tempo = tempo;
        this.playedNotes = playedNotes;
        this.totalNotes = totalNotes;
    }

    /**
     * Constructor for new PlayAssignments (no grade yet).
     * 
     * @param title The title of the assignment.
     * @param teacherComment Comments from the teacher regarding the assignment.
     * @param dueDate The due date of the assignment.
     * @param song The song to be played in the assignment.
     * @param tempo The tempo at which the song should be played.
     */
    public PlayAssignment(String title, String teacherComment, Date dueDate, Song song, int tempo) {
        super(title, teacherComment, dueDate);
        this.song = song;
        this.tempo = tempo;
        this.playedNotes = new ArrayList<>(); // Starts empty
        this.totalNotes = 0;
        ArrayList<Measure> measures = song.getMeasures();
        if (measures != null) {
            for (Measure measure : measures) {
                this.totalNotes += measure.getNotes().size();
            }
        }
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    /**
     * Gets the tempo at which the song should be played.
     * 
     * @return The tempo of the assignment.
     */
    public int getTempo() {
        return tempo;
    }

    /**
     * Sets the tempo at which the song should be played.
     * 
     * @param tempo The tempo to set for the assignment.
     */
    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    /**
     * Gets the list of played notes.
     * 
     * @return The list of played notes.
     */
    public ArrayList<Sound> getPlayedNotes() {
        return playedNotes;
    }

    /**
     * Sets the list of played notes.
     * 
     * @param playedNotes The list of notes that have been played by the student.
     */
    public void setPlayedNotes(ArrayList<Sound> playedNotes) {
        this.playedNotes = playedNotes;
    }

    /**
     * Gets the total number of notes in the song.
     * 
     * @return The total number of notes in the song.
     */
    public int getTotalNotes() {
        return totalNotes;
    }

    /**
     * Sets the total number of notes in the song.
     * 
     * @param totalNotes The total number of notes to set.
     */
    public void setTotalNotes(int totalNotes) {
        this.totalNotes = totalNotes;
    }

    /**
     * Calculates the accuracy of the student's performance as a percentage.
     * 
     * @return The accuracy as a percentage of correctly played notes.
     */
    public double getAccuracy() {
        if (song == null || playedNotes.isEmpty()) {
            return 0.0; // No song or no notes played → 0% accuracy
        }

        ArrayList<Sound> realNotes = extractNotesFromMeasures(song.getMeasures()); // Extract correct notes
        int correctCount = 0;
        int minSize = Math.min(realNotes.size(), playedNotes.size()); // Prevents out-of-bounds

        for (int i = 0; i < minSize; i++) {
            if (playedNotes.get(i).equals(realNotes.get(i))) {
                correctCount++; // Count correct notes
            }
        }
        setGrade(((double) correctCount / totalNotes) * 100);
        return ((double) correctCount / totalNotes) * 100; // Convert to percentage
    }

    /**
     * Helper method to extract all notes from song measures.
     * 
     * @param measures The measures from which to extract notes.
     * @return A list of notes from all measures.
     */
    private ArrayList<Sound> extractNotesFromMeasures(ArrayList<Measure> measures) {
        ArrayList<Sound> notes = new ArrayList<>();
        for (Measure measure : measures) {
            notes.addAll(measure.getNotes()); // Assuming Measure has getNotes()
        }
        return notes;
    }

    /**
     * Adds a single note to the list of played notes.
     * 
     * @param note The note to add to the played notes.
     */
    public void addPlayedNote(Sound note) {
        if (playedNotes == null) {
            playedNotes = new ArrayList<>();
        }
        playedNotes.add(note);
    }

    /**
     * Removes a specific note from the list of played notes.
     * 
     * @param note The note to remove from the played notes.
     */
    public void removePlayedNote(Sound note) {
        if (playedNotes != null) {
            playedNotes.remove(note);
        }
    }

    /**
     * Clears all played notes from the list.
     */
    public void clearPlayedNotes() {
        if (playedNotes != null) {
            playedNotes.clear();
        }
    }

    /**
     * Gets the number of played notes.
     * 
     * @return The count of played notes.
     */
    public int getPlayedNotesCount() {
        return playedNotes != null ? playedNotes.size() : 0;
    }
}
