package com.model;

import javafx.scene.control.Tab;

public class Note extends Sound {
    private String type;
    private double length;
    private double pitch;
    private int stringNumber;
    private String fret;

    public Note(String type, double length, double pitch, int stringNumber, String fret) {
        this.type = type;
        this.length = length;
        this.pitch = pitch;
        this.stringNumber = stringNumber;
        this.fret = fret;
    }

    public Tab convertToTab(){
        return new Tab();
    }
  
}
