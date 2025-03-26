/**
 * @author (name)
 */

package com.model;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jfugue.player.Player;
import javafx.scene.control.Tab;

/**
 * The {@code Chord} class represents a musical chord consisting of multiple notes.
 * It extends the {@code Sound} class and provides functionality for chord management,
 * playback, and conversion to a tablature representation.
 */
public class Chord extends Sound {
  private String type;
  private ArrayList<Note> notes;
  public String soundType;

  /**
   * Constructs a {@code Chord} object with the specified type and sound type.
   * The notes list is initialized as empty.
   *
   * @param type       the type of the chord (e.g., major, minor)
   * @param soundType  the sound type (typically "chord")
   */
  public Chord(String type, String soundType) {
    this.type = type;
    this.notes = new ArrayList<>();
    this.soundType = "chord";
  }

  /**
   * Constructs a {@code Chord} object with the specified type, notes, and sound type.
   *
   * @param type       the type of the chord
   * @param notes      an ArrayList of {@code Note} objects representing the chord's notes
   * @param soundType  the sound type (typically "chord")
   */
  public Chord(String type, ArrayList<Note> notes, String soundType) {
    this.type = type;
    this.notes = notes;
    this.soundType = "chord";
  }

  /**
   * Converts the chord to a guitar tablature (tab) representation.
   *
   * @return a {@code Tab} object displaying the chord's notes in a tab-like format
   */
  public Tab convertToTab() {
    StringBuilder tabRepresentation = new StringBuilder("Chord: " + type + " | ");
    for (Note note : notes) {
      tabRepresentation.append("[").append(note.getString()).append(":").append(note.getFret()).append("] ");
    }
    return new Tab(tabRepresentation.toString());
  }

  /**
   * Plays the chord using the JFugue music player.
   * Each note in the chord is played simultaneously.
   */
  public void play() {
    Player player = new Player();
    StringBuilder chord = new StringBuilder("[");

    for (int i = 0; i < notes.size(); i++) {
      chord.append(notes.get(i).toString());
      if (i < notes.size() - 1) {
        chord.append(" "); // Add space between notes, but not at the end
      }
    }

    chord.append("]");
    player.play(chord.toString());
  }

  /**
   * Converts the chord into a JSON object for data serialization.
   *
   * @return a {@code JSONObject} representing the chord's properties and notes
   */
  public JSONObject toJson() {
    JSONObject chordObject = new JSONObject();
    chordObject.put("type", getType());
    chordObject.put("soundType", getSoundType());

    // Convert notes to JSON
    JSONArray noteArray = new JSONArray();
    for (Note note : notes) {
      noteArray.add(note.toJson());
    }
    chordObject.put("notes", noteArray);

    return chordObject;
  }

  /**
   * Gets the type of the chord (e.g., major, minor).
   *
   * @return the chord type as a String
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of the chord (e.g., major, minor).
   *
   * @param type the new chord type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the list of notes in the chord.
   *
   * @return an ArrayList of {@code Note} objects representing the chord's notes
   */
  public ArrayList<Note> getNotes() {
    return notes;
  }

  /**
   * Sets the notes of the chord using an ArrayList of {@code Note} objects.
   *
   * @param notes an ArrayList of {@code Note} objects to define the chord
   */
  public void setNotes(ArrayList<Note> notes) {
    this.notes = notes;
  }

  /**
   * Gets the sound type of the chord.
   *
   * @return the sound type as a String (typically "chord")
   */
  public String getSoundType() {
    return soundType;
  }

  /**
   * Sets the sound type of the chord. This is typically set to "chord."
   *
   * @param type the sound type (ignored as it defaults to "chord")
   */
  public void setSoundType(String type) {
    this.soundType = "chord";
  }
}
