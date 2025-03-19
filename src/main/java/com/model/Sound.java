package com.model;

import org.json.simple.JSONObject;

public class Sound {
    private String type;

    public void play(){
        
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject toJson() {
        JSONObject soundObject = new JSONObject();
        soundObject.put("type", type);
        return soundObject;
    }
  
}
