package com.model;

import java.util.ArrayList;

public class ComposeAssignment {

    private String instructions;
    private int tempo;
    private ArrayList<Sound> notes;

    //Constuctor
    public ComposeAssignment(String instructions, int tempo, ArrayList<Sound> notes) {
        this.instructions = instructions;
        this.tempo = tempo;
        this.notes = notes;
    }

    public String getInstructions()
    {
        return instructions;
    } 

    public int getTempo()
    {
        return tempo;
    }

    public ArrayList<Sound> getNotes()
    {
        return notes;
    }

    public void setInstructions(String instructions)
    {
        this.instructions = instructions;
    } 

    public void setTempo(int tempo)
    {
        this.tempo = tempo;
    }

    public void setNotes(ArrayList<Sound> notes) 
    {
        this.notes = notes;
    }
}
  

