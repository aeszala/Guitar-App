/**
 * @author Nolan Banks
 */

package com.model;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * The DataWriter class provides methods to save lessons, users, and songs to
 * JSON files.
 */
public class DataWriter {
    private static final String LESSONS_FILE = "src\\main\\java\\com\\data\\json\\Lesson.json";
    private static final String USERS_FILE = "src\\main\\java\\com\\data\\json\\users.json";
    private static final String SONGS_FILE = "src\\main\\java\\com\\data\\json\\songs.json";

    /**
     * Saves a list of lessons to a JSON file.
     * 
     * @param lessons The list of lessons to save.
     */
    public static void saveLessons(ArrayList<Lesson> lessons) {
        JSONArray lessonArray = new JSONArray();
        for (Lesson lesson : lessons) {
            lessonArray.add(lesson.toJson());
        }
        writeToFile(LESSONS_FILE, lessonArray);
    }

    /**
     * Saves a list of users to a JSON file.
     * 
     * @param users The list of users to save.
     */
    public static void saveUsers(ArrayList<User> users) {
        JSONArray userArray = new JSONArray();
        for (User user : users) {
            userArray.add(user.toJson());
        }
        writeToFile(USERS_FILE, userArray);
    }

    /**
     * Saves a list of songs to a JSON file.
     * 
     * @param songs The list of songs to save.
     */
    public static void saveSongs(ArrayList<Song> songs) {
        JSONArray songArray = new JSONArray();
        for (Song song : songs) {
            songArray.add(song.toJson());
        }
        writeToFile(SONGS_FILE, songArray);
    }

    /**
     * Writes a JSON array to a file.
     * 
     * @param filename  The file path where the JSON data should be written.
     * @param jsonArray The JSON array containing data to be written.
     */
    private static void writeToFile(String filename, JSONArray jsonArray) {
        try (FileWriter file = new FileWriter(filename)) {
            JSONObject formattedJson = new JSONObject();
            formattedJson.put("data", jsonArray);
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method that demonstrates the functionality of saving lessons, users, and
     * songs.
     * It creates sample data and writes it to JSON files.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            // Creating Date object for assignment due dates
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dueDate = sdf.parse("2025-03-20");
            Date dueDate2 = sdf.parse("2025-12-31");

            // Creating Assignments
            Assignment assignment1 = new Assignment("Music Practice", 95.5, "Good job!", "It was challenging.", dueDate,
                    true);
            Assignment assignment2 = new Assignment("Sheet Project", 85.0, "Needs improvement.", "I learned a lot.",
                    dueDate2, false);

            // Creating Assignment List
            ArrayList<Assignment> assignments = new ArrayList<>();
            assignments.add(assignment1);
            assignments.add(assignment2);

            // Creating Songs with Genres
            ArrayList<Genre> genres1 = new ArrayList<>();
            genres1.add(Genre.ROCK);

            ArrayList<Genre> genres2 = new ArrayList<>();
            genres2.add(Genre.ROCK);

            // Create Reviews
            ArrayList<Review> reviews1 = new ArrayList<>();
            reviews1.add(new Review(5.0, "Classic and easy to play!", "GuitarFan69"));

            ArrayList<Review> reviews2 = new ArrayList<>();
            reviews2.add(new Review(4.5, "Iconic riff, fun to play!", "StratMaster"));

            // Notes and Chords for Song 1 - "I Won't Back Down"
            ArrayList<Note> notes1 = new ArrayList<>();
            notes1.add(new Note("G", 1.0, 98.0, 6, 3, "chord"));
            notes1.add(new Note("D", 1.0, 146.8, 5, 5, "chord"));
            notes1.add(new Note("C", 1.0, 130.8, 5, 3, "chord"));

            ArrayList<Note> notes2 = new ArrayList<>();
            notes2.add(new Note("G", 0.5, 82.41, 6, 3, "note"));
            notes2.add(new Note("A", 0.5, 110.0, 5, 0, "note"));
            notes2.add(new Note("D", 0.5, 146.8, 4, 2, "note"));
            notes2.add(new Note("G", 0.5, 98.0, 6, 3, "note"));

            ArrayList<Note> notes3 = new ArrayList<>();
            notes3.add(new Note("D", 0.5, 146.8, 5, 5, "note"));
            notes3.add(new Note("C", 0.5, 130.8, 4, 3, "note"));
            notes3.add(new Note("G", 0.5, 82.41, 6, 3, "note"));
            notes3.add(new Note("A", 0.5, 110.0, 5, 0, "note"));

            ArrayList<Note> notes4 = new ArrayList<>();
            notes4.add(new Note("G", 1.0, 98.0, 6, 3, "chord"));
            notes4.add(new Note("D", 1.0, 146.8, 5, 5, "chord"));

            ArrayList<Note> notes5 = new ArrayList<>();
            notes5.add(new Note("D", 1.0, 146.8, 4, 2, "chord"));
            notes5.add(new Note("A", 1.0, 196.0, 3, 2, "chord"));
            notes5.add(new Note("C", 1.0, 220.0, 2, 3, "chord"));

            // Measures for Song 1
            ArrayList<Measure> measures1 = new ArrayList<>();
            measures1.add(new Measure(4, 4, new ArrayList<>(notes1)));
            measures1.add(new Measure(4, 4, new ArrayList<>(notes2)));
            measures1.add(new Measure(4, 4, new ArrayList<>(notes3)));
            measures1.add(new Measure(4, 4, new ArrayList<>(notes4)));
            measures1.add(new Measure(4, 4, new ArrayList<>(notes5)));

            // Notes for Song 2 - "Mary Jane's Last Dance"
            ArrayList<Note> notes6 = new ArrayList<>();
            notes6.add(new Note("A", 1.0, 110.0, 5, 0, "note"));
            notes6.add(new Note("D", 1.0, 146.8, 4, 2, "note"));
            notes6.add(new Note("A", 1.0, 196.0, 3, 2, "note"));
            notes6.add(new Note("C", 1.0, 220.0, 2, 3, "note"));

            ArrayList<Note> notes7 = new ArrayList<>();
            notes7.add(new Note("D", 1.0, 146.8, 4, 2, "note"));
            notes7.add(new Note("A", 1.0, 196.0, 3, 2, "note"));
            notes7.add(new Note("A", 1.0, 110.0, 5, 0, "note"));

            // Measures for Song 2
            ArrayList<Measure> measures2 = new ArrayList<>();
            measures2.add(new Measure(4, 4, new ArrayList<>(notes6)));
            measures2.add(new Measure(4, 4, new ArrayList<>(notes7)));

            Chord chord1 = new Chord("Major", notes1, "chord");

            // Creating Songs
            Song song1 = new Song(UUID.randomUUID(), "I Won't Back Down", "Tom Petty", 2, 59, 115, 4.8, reviews1, false, genres1, Difficulty.BEGINNER, measures1, true);
            Song song2 = new Song(UUID.randomUUID(), "Mary Jane's Last Dance", "Tom Petty and the Heartbreakers", 4, 33, 87, 4.7, reviews2, false, genres2, Difficulty.INTERMEDIATE, measures2, true);

            // Creating Song List
            ArrayList<Song> songs = new ArrayList<>();
            songs.add(song1);
            songs.add(song2);

            // Creating Lesson with Songs and Assignments
            Lesson lesson = new Lesson("Lesson_Title", songs, "Music", assignments);
            ArrayList<Lesson> lessons = new ArrayList<>();
            lessons.add(lesson);

            // Saving Data to JSON Files
            saveLessons(lessons);
            saveSongs(songs);

            // Example User
            User user = new User("ffredrickson", "password1", "email1", "name1", "Question1", "Answer1");
            user.getFavoriteSongs().add(song1);
            user.getCompletedSongs().add(song2);
            user.getCompletedLessons().add(lesson);
            user.getMySongs().add(song1);

            ArrayList<User> users = new ArrayList<>();
            users.add(user);

            // Save users to JSON
            saveUsers(users);

            System.out.println("Users saved to users.json");
            System.out.println("Lessons and Songs saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
