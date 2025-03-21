package com.model;

import org.json.simple.JSONObject;

public class Sound {
    private String type;

    // Constructor to initialize Sound type
    public Sound(String type) {
        setType(type);
    }

    public Sound() {}

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
