/**
 * @author Liamnp
 */

package com.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The {@code Lesson} class represents a lesson in the application.
 * It includes details such as the lesson's title, associated songs, 
 * topic, assignments, progress, and completion status.
 * 
 * <p> Each lesson contains:
 * <ul>
 *   <li>{@link #title} - The title of the lesson.</li>
 *   <li>{@link #id} - A unique identifier for the lesson.</li>
 *   <li>{@link #songs} - A list of {@link Song} objects associated with the lesson.</li>
 *   <li>{@link #topic} - The topic covered in the lesson.</li>
 *   <li>{@link #assignments} - A list of {@link Assignment} objects associated with the lesson.</li>
 *   <li>{@link #progress} - A progress percentage representing completion.</li>
 *   <li>{@link #complete} - A boolean indicating if the lesson is complete.</li>
 * </ul>
 */
public class Lesson {
    public String title;
    private UUID id;
    private ArrayList<Song> songs;
    private String topic;
    private ArrayList<Assignment> assignments;
    private double progress;
    private boolean complete;

    /**
     * Constructs a new {@code Lesson} with the specified title, songs, topic, and assignments.
     * Initializes the lesson with 0 progress and incomplete status.
     * 
     * @param title The title of the lesson.
     * @param songs A list of songs associated with the lesson.
     * @param topic The topic of the lesson.
     * @param assignments A list of assignments for the lesson.
     */
    public Lesson(String title, ArrayList<Song> songs, String topic, ArrayList<Assignment> assignments) {
        this.title = title;
        this.id = UUID.randomUUID();
        this.songs = songs;
        this.topic = topic;
        this.assignments = assignments;
        this.progress = 0;
        this.complete = false;
    }

    /**
     * Constructs an existing {@code Lesson} with the specified parameters, including progress and completion status.
     * 
     * @param title The title of the lesson.
     * @param id The unique identifier for the lesson.
     * @param songs A list of songs associated with the lesson.
     * @param topic The topic of the lesson.
     * @param assignments A list of assignments for the lesson.
     * @param progress The progress percentage of the lesson.
     * @param complete The completion status of the lesson.
     */
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

    /**
     * Converts this {@code Lesson} object to its JSON representation.
     * 
     * @return A {@code JSONObject} containing the lesson's data.
     */
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

    /**
     * Gets the topic of the lesson.
     * 
     * @return The topic of the lesson.
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the topic of the lesson.
     * 
     * @param topic The new topic of the lesson.
     */
    public void setTopic(String topic){
        this.topic = topic;
    }

    /**
     * Gets the list of songs associated with the lesson.
     * 
     * @return A list of {@link Song} objects.
     */
    public ArrayList<Song> getSongs(){
        return songs;
    }

    /**
     * Adds a song to the list of songs for the lesson.
     * 
     * @param song The song to be added.
     */
    public void addSong(Song song){
        songs.add(song);
    }

    /**
     * Adds an assignment to the list of assignments for the lesson.
     * 
     * @param assignment The assignment to be added.
     */
    public void addAssignment(Assignment assignment){
        assignments.add(assignment);
    }

    /**
     * Marks the lesson as complete.
     */
    public void complete(){
        this.complete = true;
    }

    /**
     * Gets the unique identifier of the lesson.
     * 
     * @return The UUID of the lesson.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the list of assignments associated with the lesson.
     * 
     * @return A list of {@link Assignment} objects.
     */
    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Gets the progress percentage of the lesson.
     * 
     * @return The progress of the lesson.
     */
    public double getProgress() {
        return progress;
    }

    /**
     * Sets the progress percentage of the lesson.
     * 
     * @param progress The new progress of the lesson.
     */
    public void setProgress(double progress) {
        this.progress = progress;
    }

    /**
     * Checks if the lesson is complete.
     * 
     * @return {@code true} if the lesson is complete, {@code false} otherwise.
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * Gets the title of the lesson.
     * 
     * @return The title of the lesson.
     */
    public String getTitle(){
        return title;
    }

    /**
     * Sets the title of the lesson.
     * 
     * @param title The new title of the lesson.
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Returns a string representation of the lesson, including its title, id, topic, 
     * progress, completion status, song titles, and assignments.
     * 
     * @return A string representation of the lesson.
     */
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

    /**
     * Helper method to get song titles as a comma-separated list.
     * 
     * @return A string of song titles.
     */
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
