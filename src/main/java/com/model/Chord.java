package com.model;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.jfugue.player.Player;

import javafx.scene.control.Tab;

public class Chord extends Sound {
  private String type;
  private ArrayList<Note> notes;
  public String soundType;

  //Constructor
  public Chord (String type)
  {
    this.type = type;
    this.notes = new ArrayList<>();
    this.soundType = "chord";
  }

  // Constructor
  public Chord (String type, ArrayList<Note> notes, String soundType)
  {
    this.type = type;
    this.notes = notes;
    this.soundType = "chord";
  }

  public void play() {
    Player player = new Player();
    StringBuilder chord = new StringBuilder("[");

    for (int i = 0; i < notes.size(); i++) {
        chord.append(notes.get(i).toString());
        if (i < notes.size() - 1) {
            chord.append(" "); // Add space between notes, but not at the end
        }
    }

    chord.append("]"); // Close the chord notation
    player.play(chord.toString());
}

  public void addNote(Note note) {
    notes.add(note);
}

@Override
  public void addToTab(String[] tabLines, int position) {
    // Add chord name above the tab
    if (position == 0) {
        StringBuilder sb = new StringBuilder(tabLines[0]);
        while (sb.length() < position) {
            sb.append("-");
        }
        sb.append(type);
        while (sb.length() < 20) { // Fixed width for measure
            sb.append("-");
        }
        tabLines[0] = sb.toString();
    }
    
    // Add the chord notes
    for (Note note : notes) {
        note.addToTab(tabLines, position);
    }
}

private String insertAtPosition(String original, int position, String text) {
  if (original.length() <= position) {
      return original + text;
  }
  return original.substring(0, position) + text + original.substring(position + text.length());
}

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

  public String getType(){
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public ArrayList<Note> getNotes()
  {
    return notes;
  }

public void setNotes(ArrayList<Note> notes) 
  {
   this.notes = notes;
  }

  public String getSoundType(){
    return soundType;
  }

  public void setSoundType(String type)
  {
    this.soundType = "chord";
  }

}

