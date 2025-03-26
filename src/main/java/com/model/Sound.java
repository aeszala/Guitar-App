package com.model;

import org.json.simple.JSONObject;

public abstract class Sound {
    protected String type;
    protected double length;

    // Constructor to initialize Sound type
    public Sound(String type, double length ) {
        this.type = type;
        this.length = length;
    }

    public Sound() {}

    public abstract void addToTab(String[] tabLines, int position);
    
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void play() {
        // Implement sound playback logic here
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equalsIgnoreCase("chord"))
            this.type = "chord";
        else
            this.type = "note";
    }

    public JSONObject toJson() {
        JSONObject soundObject = new JSONObject();
        soundObject.put("type", type != null ? type : "UNKNOWN"); // Handle null case
        return soundObject;
    }

    @Override
    public String toString() {
        return "Sound{" + "type='" + type + '\'' + '}';
    }
}
