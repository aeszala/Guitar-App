package com.model;

import java.util.ArrayList;

public class Measure {
    private int timeSignatureTop;
    private int timeSignatureBottom;
    private ArrayList<Sound> notes;

    public Measure(int timeSignatureTop, int timeSignatureBottom, ArrayList<Sound> notes) {
        this.timeSignatureTop = timeSignatureTop;
        this.timeSignatureBottom = timeSignatureBottom;
        this.notes = notes;
    }

    public void display(){
        
    } 

    public void play() {

    }
}
