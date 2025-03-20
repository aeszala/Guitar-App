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

public class DataWriter {
    private static final String LESSONS_FILE = "src\\main\\java\\com\\data\\json\\Lesson.json";
    private static final String USERS_FILE = "src\\main\\java\\com\\data\\json\\users.json";
    private static final String SONGS_FILE = "src\\main\\java\\com\\data\\json\\songs.json";

    public static void saveLessons(ArrayList<Lesson> lessons) {
        JSONArray lessonArray = new JSONArray();
        for (Lesson lesson : lessons) {
            lessonArray.add(lesson.toJson());
        }
        writeToFile(LESSONS_FILE, lessonArray);
    }

    public static void saveUsers(ArrayList<User> users) {
        JSONArray userArray = new JSONArray();
        for (User user : users) {
            userArray.add(user.toJson());
        }
        writeToFile(USERS_FILE, userArray);
    }

    public static void saveSongs(ArrayList<Song> songs) {
        JSONArray songArray = new JSONArray();
        for (Song song : songs) {
            songArray.add(song.toJson());
        }
        writeToFile(SONGS_FILE, songArray);
    }

    private static void writeToFile(String filename, JSONArray jsonArray) {
        try (FileWriter file = new FileWriter(filename)) {
            //Formatting
            JSONObject formattedJson = new JSONObject();
            formattedJson.put("data", jsonArray);
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

            // Create Measures
            ArrayList<Sound> notes = new ArrayList<>();
            notes.add(new Sound("C", 1));
            notes.add(new Sound("D", 2));

            ArrayList<Measure> measures1 = new ArrayList<>();
            measures1.add(new Measure(4, 4, notes));

            ArrayList<Measure> measures2 = new ArrayList<>();
            measures2.add(new Measure(3, 4, notes));
            
            // Creating Songs
            Song song1 = new Song("TITLE1", "ARTIST1", 33, 44, 5, genres1, Difficulty.BEGINNER, new ArrayList<Measure>());
            Song song2 = new Song(UUID.randomUUID(), "title2", "artist2", 12, 25, 7, 0.5, new ArrayList<Review>(), true, genres2, Difficulty.ADVANCED, new ArrayList<Measure>(), false);
            
//            song1.getMeasures().add();

            // Creating Song List
            ArrayList<Song> songs = new ArrayList<>();
            songs.add(song1);
            songs.add(song2);

            // Creating Lesson with Songs and Assignments
            Lesson lesson = new Lesson(songs, "Music", assignments);
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
