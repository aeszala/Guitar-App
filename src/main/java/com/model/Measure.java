package com.model;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Measure {
    private int timeSignatureTop;
    private int timeSignatureBottom;
    private ArrayList<Sound> notes;

    // existing measure constructor
    public Measure(int timeSignatureTop, int timeSignatureBottom, ArrayList<Sound> notes) {
        this.timeSignatureTop = timeSignatureTop;
        this.timeSignatureBottom = timeSignatureBottom;
        this.notes = notes;
    }

    //default constructor
    public Measure() {
        this.timeSignatureTop = 4;
        this.timeSignatureBottom = 4;
        this.notes = new ArrayList<Sound>();
    }

    public JSONObject toJson() {
        JSONObject measureObject = new JSONObject();
        measureObject.put("timeSignatureTop", timeSignatureTop);
        measureObject.put("timeSignatureBottom", timeSignatureBottom);

    // Convert notes to JSON
    JSONArray noteArray = new JSONArray();
        for (Sound sound : notes) {
            if (sound instanceof Note) {
                noteArray.add(((Note) sound).toJson());
            }   else if (sound instanceof Chord) {
                noteArray.add(((Chord) sound).toJson());
            }
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

    public String getTimeSignature() {
        return timeSignatureTop + "/" + timeSignatureBottom;
    }

    public void setTimeSignatureTop(int timeSignatureTop) {
        this.timeSignatureTop = timeSignatureTop;
    }

    public void setTimeSignatureBottom(int timeSignatureBottom) {
        this.timeSignatureBottom = timeSignatureBottom;
    }

    public void setTimeSignature(int timeSignatureTop, int timeSignatureBottom) {
        setTimeSignatureTop(timeSignatureTop);
        setTimeSignatureBottom(timeSignatureBottom);
    }

    public ArrayList<Sound> getNotes(){
        return notes;
    }

    public void setNotes (ArrayList<Sound> notes){
        this.notes = notes;
    }

    public void addNote (Sound note) {
        notes.add(note);
    }

    public void play() {
        for (Sound note : notes) {
            note.play();  // Assuming Sound class has a play() method
        }
    }

    public String toSheetMusic() {
        return SheetMusicGenerator.convertMeasureToSheet(this);
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
