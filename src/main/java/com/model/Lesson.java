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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic){
        this.topic = topic;
    }

    public ArrayList<Song> getSongs(){
        return songs;
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }

    public void addAssignment(Assignment assignment){
        assignments.add(assignment);
    }

    public boolean isComplete(){
        return complete;
    }

    public void complete(){
        this.complete = true;
    }

    public UUID getId() {
        return id;
    }
    
    
}
