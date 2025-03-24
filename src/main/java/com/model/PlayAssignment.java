package com.model;

import java.util.ArrayList;
import java.util.Date;

public class PlayAssignment extends Assignment {
    private Song song;
    private int tempo;
    private ArrayList<Sound> playedNotes;
    private int totalNotes;

    // Constructor for loading existing PlayAssignments (with grade & completion
    // status)
    public PlayAssignment(String title, double grade, String teacherComment, String studentComment,
            Date dueDate, boolean complete, Song song, int tempo,
            ArrayList<Sound> playedNotes, int totalNotes) {
        super(title, grade, teacherComment, studentComment, dueDate, complete);
        this.song = song;
        this.tempo = tempo;
        this.playedNotes = playedNotes;
        this.totalNotes = totalNotes;
    }

    // Constructor for new PlayAssignments (no grade yet)
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

    // Getter and Setter for tempo
    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    // Getter and Setter for playedNotes
    public ArrayList<Sound> getPlayedNotes() {
        return playedNotes;
    }

    public void setPlayedNotes(ArrayList<Sound> playedNotes) {
        this.playedNotes = playedNotes;
    }

    // Getter and Setter for totalNotes
    public int getTotalNotes() {
        return totalNotes;
    }

    public void setTotalNotes(int totalNotes) {
        this.totalNotes = totalNotes;
    }

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

    // Helper method to extract all notes from song measures
    private ArrayList<Sound> extractNotesFromMeasures(ArrayList<Measure> measures) {
        ArrayList<Sound> notes = new ArrayList<>();
        for (Measure measure : measures) {
            notes.addAll(measure.getNotes()); // Assuming Measure has getNotes()
        }
        return notes;
    }

    // Add a single note to playedNotes
    public void addPlayedNote(Sound note) {
        if (playedNotes == null) {
            playedNotes = new ArrayList<>();
        }
        playedNotes.add(note);
    }

    // Remove a specific note from playedNotes
    public void removePlayedNote(Sound note) {
        if (playedNotes != null) {
            playedNotes.remove(note);
        }
    }

    // Clear all played notes
    public void clearPlayedNotes() {
        if (playedNotes != null) {
            playedNotes.clear();
        }
    }

    // Get the number of played notes
    public int getPlayedNotesCount() {
        return playedNotes != null ? playedNotes.size() : 0;
    }

}