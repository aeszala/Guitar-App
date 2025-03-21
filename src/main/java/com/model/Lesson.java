package com.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.UUID;

public class Lesson {
    public String title;
    private UUID id;
    private ArrayList<Song> songs;
    private String topic;
    private ArrayList<Assignment> assignments;
    private double progress;
    private boolean complete;

    // new Lesson Constructor
    public Lesson(String title, ArrayList<Song> songs, String topic, ArrayList<Assignment> assignments) {
        this.title = title;
        this.id = UUID.randomUUID();
        this.songs = songs;
        this.topic = topic;
        this.assignments = assignments;
        this.progress = 0;
        this.complete = false;
    }

    // existing Lesson Constructor
    public Lesson(String title, UUID id, ArrayList<Song> songs, String topic,
    ArrayList<Assignment> assignments, double progress, boolean complete) {
        this.title = title;
        this.id = id;
        this.songs = songs;
        this.topic = topic;
        this.assignments = assignments;
        this.progress = progress;
        this.complete = complete;
    }

    public JSONObject toJson() {
        JSONObject lessonObject = new JSONObject();
        lessonObject.put("title", title);
        lessonObject.put("id", id.toString());
        lessonObject.put("topic", topic);
        lessonObject.put("progress", progress);
        lessonObject.put("complete", complete);

        // Store only SongIDs
        JSONArray songIdsArray = new JSONArray();
        for (Song song : this.songs) {
            songIdsArray.add(song.getId().toString());
        }
        lessonObject.put("songs", songIdsArray);

        // Convert assignments to JSON
        JSONArray assignmentArray = new JSONArray();
        for (Assignment assignment : this.assignments) {
            assignmentArray.add(assignment.toJson());
        }
        lessonObject.put("assignments", assignmentArray);

        return lessonObject;
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

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public boolean isComplete() {
        return complete;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
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
