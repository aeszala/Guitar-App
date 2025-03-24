package com.model;
import java.util.ArrayList;

import org.jfugue.player.Player;

import javafx.scene.control.Tab;

public class Chord extends Sound {
  private String type;
  private ArrayList<Note> notes;
  public String soundType;

  //Constructor
  public Chord (String type, String soundType)
  {
    this.type = type;
    this.notes = new ArrayList<>();
    this.soundType = "chord";
  }

  //Constructor
  public Chord (String type, ArrayList<Note> notes, String soundType)
  {
    this.type = type;
    this.notes = notes;
    this.soundType = "chord";
  }

  public Tab convertToTab()
  {
    StringBuilder tabRepresentation = new StringBuilder("Chord: " + type + " | ");
        for (Note note : notes) {
            tabRepresentation.append("[").append(note.getString()).append(":").append(note.getFret()).append("] ");
        }
        return new Tab(tabRepresentation.toString());
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

}

