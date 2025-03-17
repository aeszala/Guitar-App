package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Lesson {
    private UUID id;
    private ArrayList<Song> songs;
    private String topic;
    private ArrayList<Assignment> assignments;
    private double progress;
    private boolean complete;

    public Lesson(UUID id, ArrayList<Song> songs, String topic,
    ArrayList<Assignment> assignments, double progress, boolean complete) {
        this.id = id;
        this.songs = songs;
        this.topic = topic;
        this.assignments = assignments;
        this.progress = progress;
        this.complete = complete;
    }

    public void complete(){

    }

    public UUID getId() {
        return id;
    }
    
}
