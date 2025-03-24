package com.model;

import org.json.simple.JSONObject;

import javafx.scene.control.Tab;

public class Note extends Sound {
    private String type;
    private double length;
    private double pitch;
    private int string;
    private int fret;
    public String soundType;
    
    public Note(String type, double length, double pitch, int string, int fret, String soundType) {
        this.type = type;
        this.length = length;
        this.pitch = pitch;
        this.string = string;
        this.fret = fret;
        this.soundType = "note";
    }

    public String toTab() {
        return "String: " + string + " | Fret: " + fret + " (" + type + ", " + length + ")";
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

    public void setLength(double length) {
        this.length = length;
    }

    public double getPitch() {
        return pitch;
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

    public int getFret() { 
        return fret; 
    }

    public void setFret(int fret) {
         this.fret = fret; 
    }
    
    public String getSoundType() {
        return soundType;
    }
    
    @Override
    public JSONObject toJson() {
        JSONObject noteObject = new JSONObject();
        noteObject.put("type", getType());
        noteObject.put("soundType", getSoundType());
        noteObject.put("length", getLength());
        noteObject.put("pitch", getPitch());
        noteObject.put("string", getString());
        noteObject.put("fret", getFret());
        return noteObject;
    }

    @Override
    public String toString() {
        return "Note {" +
                "Type='" + type + '\'' +
                ", Length=" + length +
                ", Pitch=" + pitch +
                ", String=" + string +
                ", Fret=" + fret +
                ", SoundType='" + soundType + '\'' +
                '}';
    }
    
}
