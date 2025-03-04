package com.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    // Method to write users to the JSON file
    public static void writeUsers(List<User> users) {
        JSONArray userList = new JSONArray();
        for (User user : users) {
            JSONObject userObject = new JSONObject();
            userObject.put(USER_ID, user.getId());
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
//    public static void writeSongs(List<Song> songs) {
//        JSONArray songList = new JSONArray();
//        for (Song song : songs) {
//            JSONObject songObject = new JSONObject();
//            songObject.put(SONG_ID, song.getId());
//            songObject.put(SONG_TITLE, song.getTitle());
//            songObject.put(SONG_ARTIST, song.getArtist());
//            songObject.put(SONG_RUN_LENGTH_MIN, song.getRunLengthMin());
//            songObject.put(SONG_RUN_LENGTH_SEC, song.getRunLengthSec());
//            songObject.put(SONG_TEMPO, song.getTempo());
//            songObject.put(SONG_RATING, song.getRating());
//            songObject.put(SONG_REVIEWS, song.getReviews());
//            songObject.put(SONG_METRONOME_ON, song.isMetronomeOn());
//            songObject.put(SONG_GENRES, song.getGenres());
//            songObject.put(SONG_DIFFICULTY, song.getDifficulty());
//            songObject.put(SONG_MEASURES, song.getMeasures());
//            songObject.put(SONG_COMPLETED, song.isCompleted());
//
//            songList.add(songObject);
//        }
//        writeToFile(SONG_FILE_NAME, songList);
//    }

    // Method to write lessons to the JSON file
//    public static void writeLessons(List<Lesson> lessons) {
//        JSONArray lessonList = new JSONArray();
//        for (Lesson lesson : lessons) {
//            JSONObject lessonObject = new JSONObject();
//            lessonObject.put(LESSON_ID, lesson.getId());
//            lessonObject.put(LESSON_SONGS, lesson.getSongs());
//            lessonObject.put(LESSON_TOPIC, lesson.getTopic());
//            lessonObject.put(LESSON_ASSIGNMENTS, lesson.getAssignments());
//            lessonObject.put(LESSON_PROGRESS, lesson.getProgress());
//            lessonObject.put(LESSON_COMPLETE, lesson.isComplete());
//
//            lessonList.add(lessonObject);
//        }
//        writeToFile(LESSON_FILE_NAME, lessonList);
//    }

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
      // Creates user list
      ArrayList<User> users = new ArrayList<User>();
      // Define folder name
      String folderName = "UserData";
      // Create folder if it doesn't exist
      File folder = new File(folderName);
      if (!folder.exists()) {
          if (folder.mkdir()) {
              System.out.println("Folder created: " + folderName);
          } else {
              System.out.println("Failed to create folder.");
              return;
          }
      }
      User user = new User(null, null, null, null, null, null);
      DataWriter.writeUsers(users);

    }
}