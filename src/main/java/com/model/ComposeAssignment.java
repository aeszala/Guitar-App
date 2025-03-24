package com.model;

import java.util.ArrayList;
import java.util.Date;

public class ComposeAssignment extends Assignment {

    private String instructions;
    private int tempo;
    private ArrayList<Sound> notes;

    // Constructor for loading existing PlayAssignments (with grade & completion
    // status)
    public ComposeAssignment(String title, double grade, String teacherComment, String studentComment,
            Date dueDate, boolean complete, int tempo, String instructions,
            ArrayList<Sound> notes) {
        super(title, grade, teacherComment, studentComment, dueDate, complete);
        this.instructions = instructions;
        this.tempo = tempo;
        this.notes = notes;
    }

    // Constructor for new PlayAssignments (no grade yet)
    public ComposeAssignment(String title, String teacherComment, Date dueDate, Song song,
            int tempo, String instructions) {
        super(title, teacherComment, dueDate);
        this.instructions = instructions;
        this.tempo = tempo;
        this.notes = new ArrayList<>(); // Starts empty
    }

    public String getInstructions() {
        return instructions;
    }

    public int getTempo() {
        return tempo;
    }

    public ArrayList<Sound> getNotes() {
        return notes;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void setNotes(ArrayList<Sound> notes) {
        this.notes = notes;
    }

    public void addNote(Sound note) {
        notes.add(note);
    }
}
