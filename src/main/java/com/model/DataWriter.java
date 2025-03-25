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
 * The DataWriter class provides methods to save lessons, users, and songs to JSON files.
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
     * Main method that demonstrates the functionality of saving lessons, users, and songs.
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
            Assignment assignment1 = new Assignment("Music Practice", 95.5, "Good job!", "It was challenging.", dueDate, true);
            Assignment assignment2 = new Assignment("Sheet Project", 85.0, "Needs improvement.", "I learned a lot.", dueDate2, false);

            // Creating Assignment List
            ArrayList<Assignment> assignments = new ArrayList<>();
            assignments.add(assignment1);
            assignments.add(assignment2);

            // Creating Songs with Genres
            ArrayList<Genre> genres1 = new ArrayList<>();
            genres1.add(Genre.ROCK);
            genres1.add(Genre.POP);

            ArrayList<Genre> genres2 = new ArrayList<>();
            genres2.add(Genre.JAZZ);
            genres2.add(Genre.CLASSICAL);

            // Create Reviews
            ArrayList<Review> reviews1 = new ArrayList<>();
            reviews1.add(new Review(4.5, "Great song!", "John Doe"));
            reviews1.add(new Review(3.8, "Pretty good.", "Jane Smith"));
            
            ArrayList<Review> reviews2 = new ArrayList<>();
            reviews2.add(new Review(5.0, "Perfect!", "Bob Williams"));
            reviews2.add(new Review(1.8, "Bad.", "Mary Watson"));

            // Create Notes and Chords
            ArrayList<Note> notes1 = new ArrayList<>();
            notes1.add(new Note("C", 1.0, 440.0, 3, 5, "note"));
            notes1.add(new Note("D", 0.5, 466.16, 4, 7, "note"));

            Chord chord1 = new Chord("Major", notes1, "chord");

            ArrayList<Measure> measures1 = new ArrayList<>();
            measures1.add(new Measure(4, 4, new ArrayList<>(notes1)));
            measures1.add(new Measure(4, 4, new ArrayList<>(notes1)));

            ArrayList<Measure> measures2 = new ArrayList<>();
            measures2.add(new Measure(3, 4, new ArrayList<>(notes1)));
            measures2.add(new Measure(3, 4, new ArrayList<>(notes1)));
            
            // Creating Songs
            Song song1 = new Song(UUID.randomUUID(), "title1", "artist1", 5, 32, 112, 5.0, reviews1, false, genres1, Difficulty.BEGINNER, measures1, true);
            Song song2 = new Song(UUID.randomUUID(), "title2", "artist2", 12, 25, 7, 0.5, reviews2, true, genres2, Difficulty.ADVANCED, measures2, false);
            
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
            User user = new User("username1", "password1", "email1", "name1", "Question1", "Answer1");
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
