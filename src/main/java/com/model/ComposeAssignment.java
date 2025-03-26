/**
 * @author (name)
 */

package com.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a composition-based assignment that includes musical instructions, tempo,
 * and a list of notes to be played.
 */
public class ComposeAssignment extends Assignment {

    private String instructions;
    private int tempo;
    private ArrayList<Sound> notes;

    /**
     * Constructs a ComposeAssignment with an existing grade and completion status.
     * 
     * @param title           The title of the assignment.
     * @param grade           The grade received for the assignment.
     * @param teacherComment  The teacher's comments on the assignment.
     * @param studentComment  The student's comments on the assignment.
     * @param dueDate         The due date of the assignment.
     * @param complete        Whether the assignment is complete.
     * @param tempo           The tempo of the composition.
     * @param instructions    The instructions for the composition.
     * @param notes           The list of musical notes.
     */
    public ComposeAssignment(String title, double grade, String teacherComment, String studentComment,
                             Date dueDate, boolean complete, int tempo, String instructions,
                             ArrayList<Sound> notes) {
        super(title, grade, teacherComment, studentComment, dueDate, complete);
        this.instructions = instructions;
        this.tempo = tempo;
        this.notes = notes;
    }

    /**
     * Constructs a new ComposeAssignment without an initial grade.
     * 
     * @param title           The title of the assignment.
     * @param teacherComment  The teacher's comments on the assignment.
     * @param dueDate         The due date of the assignment.
     * @param song            The song associated with the assignment.
     * @param tempo           The tempo of the composition.
     * @param instructions    The instructions for the composition.
     */
    public ComposeAssignment(String title, String teacherComment, Date dueDate, Song song,
                             int tempo, String instructions) {
        super(title, teacherComment, dueDate);
        this.instructions = instructions;
        this.tempo = tempo;
        this.notes = new ArrayList<>(); // Starts empty
    }

    /**
     * Gets the composition instructions.
     * 
     * @return The instructions as a string.
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Gets the tempo of the composition.
     * 
     * @return The tempo in beats per minute (BPM).
     */
    public int getTempo() {
        return tempo;
    }

    /**
     * Gets the list of musical notes in the composition.
     * 
     * @return An ArrayList of Sound objects.
     */
    public ArrayList<Sound> getNotes() {
        return notes;
    }

    /**
     * Sets the composition instructions.
     * 
     * @param instructions The new instructions as a string.
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * Sets the tempo of the composition.
     * 
     * @param tempo The new tempo in beats per minute (BPM).
     */
    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    /**
     * Sets the list of musical notes in the composition.
     * 
     * @param notes An ArrayList of Sound objects representing the musical notes.
     */
    public void setNotes(ArrayList<Sound> notes) {
        this.notes = notes;
    }

    /**
     * Adds a single note to the composition.
     * 
     * @param note The Sound object representing the musical note to be added.
     */
    public void addNote(Sound note) {
        notes.add(note);
    }
}
