package com.model;
import java.util.ArrayList;

public class Chord extends Sound{
  private String type;
  private ArrayList<Note> notes;

  //Constructor
  public Chord (String type, ArrayList<Note> notes)
  {
    this.type = type;
    this.notes = notes;
  }

  public void convertToTab()
  {
    //implementation
  } 

  public void play()
  {
    //implementation for playing the chord
  }

  public String getType()
  {
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

