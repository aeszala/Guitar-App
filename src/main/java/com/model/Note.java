package com.model;

import javafx.scene.control.Tab;

public class Note extends Sound {
    private String type;
    private double length;
    private double pitch;
    private int string;
    private String fret;
    public String soundType;
    
    public Note(String type, double length, double pitch, int string, String fret, String soundType) {
        this.type = type;
        this.length = length;
        this.pitch = pitch;
        this.string = string;
        this.fret = fret;
        this.soundType = "note";
    }

    public Tab convertToTab(){
        return new Tab();
    }

    public void play(){
        System.out.println("Playing note: " + type + " on string " + string + " at fret " + fret +
                " with pitch " + pitch + "Hz and length " + length);
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public double getLength() {
        return length;
    }
    public void setPitch(Double pitch){
        this.pitch = pitch;
    }

    public int getString() { 
        return string; 
    }
    public void setString(int string) { 
        this.string = string; 
    }

    public String getFret() { 
        return fret; 
    }
    public void setFret(String fret) {
         this.fret = fret; 
    }

    @Override
    public String toString() {
        
    }
}
