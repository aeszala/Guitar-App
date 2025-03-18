package com.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    // Method to write users to the JSON file
    public static void saveUsers(List<User> users) {
        JSONArray userList = new JSONArray();
        for (User user : users) {
            JSONObject userObject = new JSONObject();
            userObject.put(USER_ID, user.getId().toString());
            userObject.put(USER_USERNAME, user.getUsername());
            userObject.put(USER_PASSWORD, user.getPassword());
            userObject.put(USER_EMAIL, user.getEmail());
            userObject.put(USER_NAME, user.getName());
            userObject.put(USER_FAVORITE_SONGS, user.getFavoriteSongs());
            userObject.put(USER_COMPLETED_SONGS, user.getCompletedSongs());
            userObject.put(USER_COMPLETED_LESSONS, user.getCompletedLessons());
            userObject.put(USER_MY_SONGS, user.getMySongs());
            userObject.put(USER_SECURITY_QUESTION, user.getSecurityQuestion());
            userObject.put(USER_SECURITY_ANSWER, user.getSecurityAnswer());

            userList.add(userObject);
        }
        writeToFile(USER_FILE_NAME, userList);
    }

     // Method to write songs to the JSON file
    public static void saveSongs(List<Song> songs) {
        JSONArray songList = new JSONArray();
        for (Song song : songs) {
            JSONObject songObject = new JSONObject();
            songObject.put(SONG_ID, song.getId().toString());
            songObject.put(SONG_TITLE, song.getTitle());
            songObject.put(SONG_ARTIST, song.getArtist());
            songObject.put(SONG_RUN_LENGTH_MIN, song.getRunLengthMin());
            songObject.put(SONG_RUN_LENGTH_SEC, song.getRunLengthSec());
            songObject.put(SONG_TEMPO, song.getTempo());
            songObject.put(SONG_RATING, song.getRating());
            songObject.put(SONG_REVIEWS, song.getReviews());
            songObject.put(SONG_METRONOME_ON, song.isMetronomeOn());
            songObject.put(SONG_GENRES, song.getGenres());
            songObject.put(SONG_DIFFICULTY, song.getDifficulty().toString());
            songObject.put(SONG_MEASURES, song.getMeasures());
            songObject.put(SONG_COMPLETED, song.isCompleted());
//            songObject.put(SONG_SOUND_TYPE, song.getSoundType());


            songList.add(songObject);
        }
        writeToFile(SONG_FILE_NAME, songList);
    }

     // Method to write lessons to the JSON file
    public static void saveLessons(List<Lesson> lessons) {
        JSONArray lessonList = new JSONArray();
        for (Lesson lesson : lessons) {
            JSONObject lessonObject = new JSONObject();
            lessonObject.put(LESSON_ID, lesson.getId().toString());
            lessonObject.put(LESSON_SONGS, lesson.getSongs());
            lessonObject.put(LESSON_TOPIC, lesson.getTopic());
            lessonObject.put(LESSON_ASSIGNMENTS, lesson.getAssignments());
            lessonObject.put(LESSON_PROGRESS, lesson.getProgress());
            lessonObject.put(LESSON_COMPLETE, lesson.isComplete());

            lessonList.add(lessonObject);
        }
        writeToFile(LESSON_FILE_NAME, lessonList);
    }

    // Helper method to write JSON data to a file
    private static void writeToFile(String fileName, JSONArray data) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(data.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Creates user, song, and lesson list
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Song> songs = new ArrayList<>();
        ArrayList<Lesson> lessons = new ArrayList<>();


        // Define folder name
        String folderName = "LessonData";
        File folder = new File(folderName);

        // Create folder if it doesn't exist
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created: " + folderName);
            } else {
                System.out.println("Failed to create folder.");
                return;
            }
        }

        // Define file name inside the folder
        String fileName = folderName + "/lessons.txt";
        File file = new File(fileName);

        // Create file if it doesn't exist
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

        User user = new User("username1", "password1", "email1", "name1", "Question1", "Answer1");
        users.add(user);
        DataWriter.saveUsers(users);
        Song song = new Song("title2", "Myself", 1, 2, 0, new ArrayList<Genre>(), Difficulty.BEGINNER, new ArrayList<Measure>());
        songs.add(song);
        DataWriter.saveSongs(songs);
        Lesson lesson = new Lesson(new ArrayList<Song>(), "topic3", new ArrayList<Assignment>());
        lessons.add(lesson);
        DataWriter.saveLessons(lessons);
    }
}