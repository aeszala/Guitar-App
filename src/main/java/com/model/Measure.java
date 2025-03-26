/**
 * @author (name)
 */

package com.model;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The {@code Measure} class represents a musical measure, which includes a time signature
 * and a collection of notes (or sounds) within that measure. It provides methods for managing
 * notes, time signature, and converting the measure to JSON format.
 * 
 * <p>The class includes:
 * <ul>
 *   <li>{@link #timeSignatureTop} - The top number of the time signature (e.g., 4 in 4/4).</li>
 *   <li>{@link #timeSignatureBottom} - The bottom number of the time signature (e.g., 4 in 4/4).</li>
 *   <li>{@link #notes} - A list of {@link Sound} objects (Notes or Chords) in the measure.</li>
 *   <li>{@link #toJson()} - Converts the measure into a JSON representation.</li>
 *   <li>{@link #display()} - Prints the measure's time signature and notes.</li>
 *   <li>{@link #play()} - Plays the notes in the measure.</li>
 *   <li>{@link #toSheetMusic()} - Converts the measure to sheet music format.</li>
 * </ul>
 */
public class Measure {
    private int timeSignatureTop;
    private int timeSignatureBottom;
    private ArrayList<Sound> notes;

    /**
     * Constructs a {@code Measure} with a specified time signature and list of notes.
     * 
     * @param timeSignatureTop   The top number of the time signature (e.g., 4 in 4/4).
     * @param timeSignatureBottom The bottom number of the time signature (e.g., 4 in 4/4).
     * @param notes The list of {@link Sound} objects (Notes or Chords) in the measure.
     */
    public Measure(int timeSignatureTop, int timeSignatureBottom, ArrayList<Sound> notes) {
        this.timeSignatureTop = timeSignatureTop;
        this.timeSignatureBottom = timeSignatureBottom;
        this.notes = notes;
    }

    /**
     * Constructs a {@code Measure} with a default time signature of 4/4 and an empty list of notes.
     */
    public Measure() {
        this.timeSignatureTop = 4;
        this.timeSignatureBottom = 4;
        this.notes = new ArrayList<Sound>();
    }

    /**
     * Converts the measure to its JSON representation.
     * 
     * @return A {@link JSONObject} representing the measure, including time signature and notes.
     */
    public JSONObject toJson() {
        JSONObject measureObject = new JSONObject();
        measureObject.put("timeSignatureTop", timeSignatureTop);
        measureObject.put("timeSignatureBottom", timeSignatureBottom);

        // Convert notes to JSON
        JSONArray noteArray = new JSONArray();
        for (Sound sound : notes) {
            if (sound instanceof Note) {
                noteArray.add(((Note) sound).toJson());
            } else if (sound instanceof Chord) {
                noteArray.add(((Chord) sound).toJson());
            }
        }
        measureObject.put("notes", noteArray);

        return measureObject;
    }

    /**
     * Displays the time signature and notes of the measure to the console.
     */
    public void display() {
        System.out.println("Time Signature: " + timeSignatureTop + "/" + timeSignatureBottom);
        System.out.println("Notes: " + notes);
    }

    /**
     * Gets the top number of the time signature.
     * 
     * @return The top number of the time signature.
     */
    public int getTimeSignatureTop() {
        return timeSignatureTop;
    }

    /**
     * Gets the bottom number of the time signature.
     * 
     * @return The bottom number of the time signature.
     */
    public int getTimeSignatureBottom() {
        return timeSignatureBottom;
    }

    /**
     * Gets the time signature as a string (e.g., "4/4").
     * 
     * @return The time signature as a string.
     */
    public String getTimeSignature() {
        return timeSignatureTop + "/" + timeSignatureBottom;
    }

    /**
     * Sets the top number of the time signature.
     * 
     * @param timeSignatureTop The new top number for the time signature.
     */
    public void setTimeSignatureTop(int timeSignatureTop) {
        this.timeSignatureTop = timeSignatureTop;
    }

    /**
     * Sets the bottom number of the time signature.
     * 
     * @param timeSignatureBottom The new bottom number for the time signature.
     */
    public void setTimeSignatureBottom(int timeSignatureBottom) {
        this.timeSignatureBottom = timeSignatureBottom;
    }

    /**
     * Sets both the top and bottom numbers of the time signature.
     * 
     * @param timeSignatureTop    The new top number for the time signature.
     * @param timeSignatureBottom The new bottom number for the time signature.
     */
    public void setTimeSignature(int timeSignatureTop, int timeSignatureBottom) {
        setTimeSignatureTop(timeSignatureTop);
        setTimeSignatureBottom(timeSignatureBottom);
    }

    /**
     * Gets the list of notes (or sounds) in the measure.
     * 
     * @return An {@link ArrayList} of {@link Sound} objects.
     */
    public ArrayList<Sound> getNotes() {
        return notes;
    }

    /**
     * Sets the list of notes for the measure.
     * 
     * @param notes An {@link ArrayList} of {@link Sound} objects to set for the measure.
     */
    public void setNotes(ArrayList<Sound> notes) {
        this.notes = notes;
    }

    /**
     * Adds a note (or sound) to the measure.
     * 
     * @param note A {@link Sound} object (Note or Chord) to add to the measure.
     */
    public void addNote(Sound note) {
        notes.add(note);
    }

    /**
     * Plays all the notes in the measure.
     * This assumes that the {@link Sound} class has a {@code play()} method.
     */
    public void play() {
        for (Sound note : notes) {
            note.play();  // Assuming Sound class has a play() method
        }
    }

    /**
     * Converts the measure to sheet music format using the {@link SheetMusicGenerator}.
     * 
     * @return A string representing the measure in sheet music format.
     */
    public String toSheetMusic() {
        return SheetMusicGenerator.convertMeasureToSheet(this);
    }

    /**
     * Returns a string representation of the measure, including the time signature and the types of notes.
     * 
     * @return A string describing the measure, including the time signature and note types.
     */
    @Override
    public String toString() {
        return "Measure{" +
                "Time Signature: " + timeSignatureTop + "/" + timeSignatureBottom +
                ", Notes: " + getNoteTypes() +
                '}';
    }

    /**
     * Helper method to get the type of each sound (note or chord) in the notes list.
     * 
     * @return A string of note types, e.g., ["Note, Chord"].
     */
    private String getNoteTypes() {
        if (notes == null || notes.isEmpty()) return "No Notes";
        StringBuilder types = new StringBuilder("[");
        for (Sound sound : notes) {
            types.append(sound.getType()).append(", ");
        }
        return types.substring(0, types.length() - 2) + "]"; // Remove last comma and space
    }
}
