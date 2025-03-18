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

    public Lesson(ArrayList<Song> songs, String topic, ArrayList<Assignment> assignments) {
        this.id = UUID.randomUUID();
        this.songs = songs;
        this.topic = topic;
        this.assignments = assignments;
        this.progress = 0;
        this.complete = false;
    }

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

    public void addAssignment(Assignment assignment){
        assignments.add(assignment);
    }

    public void complete(){
        this.complete = true;
    }

    public UUID getId() {
        return id;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public double getProgress() {
        return progress;
    }

    public boolean isComplete() {
        return complete;
    }



    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", progress=" + progress +
                ", complete=" + complete +
                ", songs=" + getSongTitles() +
                ", assignments=" + assignments +
                '}';
}

    // Helper method to get song titles as a list
    private String getSongTitles() {
        if (songs == null || songs.isEmpty())
            return "No Songs";
        StringBuilder titles = new StringBuilder("[");
        for (Song song : songs) {
            titles.append(song.getTitle()).append(", ");
        }
        return titles.substring(0, titles.length() - 2) + "]"; // Remove last comma and space
}
    
}
