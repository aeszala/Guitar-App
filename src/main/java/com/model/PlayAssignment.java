package com.model;

import java.util.ArrayList;

public class PlayAssignment {
    ArrayList<Song> songs;
    private int Tempo;
    ArrayList<Sound> playedNotes;
    ArrayList<Sound> correctNotes;
    private int totalNotes;
    private double accuracy;

    public double getAccuracy(){
        return accuracy;
    }

    
}