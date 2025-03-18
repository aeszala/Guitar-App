package com.model;

import java.util.ArrayList;

public class Measure {
    private int timeSignatureTop;
    private int timeSignatureBottom;
    private ArrayList<Sound> notes;

    public Measure(int timeSignatureTop, int timeSignatureBottom, ArrayList<Sound> notes) {
        this.timeSignatureTop = timeSignatureTop;
        this.timeSignatureBottom = timeSignatureBottom;
        this.notes = new ArrayList<>();
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
}
