package com.model;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Measure {
    private int timeSignatureTop;
    private int timeSignatureBottom;
    private ArrayList<Sound> notes;

    public Measure(int timeSignatureTop, int timeSignatureBottom, ArrayList<Sound> notes) {
        this.timeSignatureTop = timeSignatureTop;
        this.timeSignatureBottom = timeSignatureBottom;
        this.notes = notes;
    }

    public JSONObject toJson() {
        JSONObject measureObject = new JSONObject();
        measureObject.put("timeSignatureTop", timeSignatureTop);
        measureObject.put("timeSignatureBottom", timeSignatureBottom);

        // Convert notes to JSON
        JSONArray noteArray = new JSONArray();
        for (Sound sound : notes) {
            noteArray.add(sound.toJson());
        }
        measureObject.put("notes", noteArray);

        return measureObject;
    }

    public void display(){
        System.out.println("Time Signature: " + timeSignatureTop + "/" + timeSignatureBottom);
        System.out.println("Notes: " + notes);
    } 

    public int getTimeSignatureTop(){
        return timeSignatureTop;
    }

    public int getTimeSignatureBottom(){
        return timeSignatureBottom;
    }

    public void setTimeSignatureTop(int timeSignatureTop){
        this.timeSignatureTop = timeSignatureTop;
    }

    public ArrayList<Sound> getNotes(){
        return notes;
    }

    public void setNotes (ArrayList<Sound> notes){
        this.notes = notes;
    }

    public void play() {

    }

    @Override
    public String toString() {
        return "Measure{" +
                "Time Signature: " + timeSignatureTop + "/" + timeSignatureBottom +
                ", Notes: " + getNoteTypes() +
                '}';
    }
    
    // Helper method to get the type of each sound in the notes list
    private String getNoteTypes() {
        if (notes == null || notes.isEmpty()) return "No Notes";
        StringBuilder types = new StringBuilder("[");
        for (Sound sound : notes) {
            types.append(sound.getType()).append(", ");
        }
        return types.substring(0, types.length() - 2) + "]"; // Remove last comma and space
    }
    
}
