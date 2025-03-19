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

            // Creating Assignments
            Assignment assignment1 = new Assignment("Music Practice", 95.5, "Good job!", "It was challenging.", dueDate, true);
            Assignment assignment2 = new Assignment("Sheet Project", 85.0, "Needs improvement.", "I learned a lot.", dueDate, false);

            // Creating Assignment List
            ArrayList<Assignment> assignments = new ArrayList<>();
            assignments.add(assignment1);
            assignments.add(assignment2);

            // Creating Songs
            Song song1 = new Song("TITLE1", "ARTIST1", 33, 44, 5, new ArrayList<Genre>(), Difficulty.BEGINNER, new ArrayList<Measure>());
            Song song2 = new Song(UUID.randomUUID(), "title2", "artist2", 12, 25, 7, 0.5, new ArrayList<Review>(), true, new ArrayList<Genre>(), Difficulty.ADVANCED, new ArrayList<Measure>(), false);

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



/*
package com.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DataWriter {

    public static void saveUsers(List<User> users) {
        JSONArray userList = new JSONArray();

        for (User user : users) {
            JSONObject userObject = new JSONObject();
            userObject.put("id", user.getId().toString());
            userObject.put("username", user.getUsername());
            userObject.put("password", user.getPassword());
            userObject.put("email", user.getEmail());
            userObject.put("name", user.getName());

            // Convert Song and Lesson objects to UUIDs
            userObject.put("favoriteSongs", convertUUIDListToJSON(user.getFavoriteSongs().stream().map(Song::getId).collect(Collectors.toList())
            ));
            userObject.put("completedSongs", convertUUIDListToJSON(user.getCompletedSongs().stream().map(Song::getId).collect(Collectors.toList())
            ));
            userObject.put("completedLessons", convertUUIDListToJSON(user.getCompletedLessons().stream().map(Lesson::getId).collect(Collectors.toList())
            ));
            
            // Convert mySongs to UUID Comma Separated String
            userObject.put("mySongs", convertUUIDListToCommaString(user.getMySongs().stream().map(Song::getId).collect(Collectors.toList())
            ));
            
            userObject.put("securityQuestion", user.getSecurityQuestion());
            userObject.put("securityAnswer", user.getSecurityAnswer());
            
            userList.add(userObject);
        }

        writeToFile("src\\main\\java\\com\\data\\json\\users.json", userList);
    }

    public static void saveSongs(List<Song> songs) {
        JSONArray songList = new JSONArray();

        for (Song song : songs) {
            JSONObject songObject = new JSONObject();
            songObject.put("id", song.getId().toString());
            songObject.put("title", song.getTitle());
            songObject.put("artist", song.getArtist());
            songObject.put("runLengthMin", song.getRunLengthMin());
            songObject.put("runLengthSec", song.getRunLengthSec());
            songObject.put("tempo", song.getTempo());
            songObject.put("rating", song.getRating());
            songObject.put("metronomeOn", song.isMetronomeOn());
            songObject.put("difficulty", song.getDifficulty().toString());
            
            // Serialize Reviews
            JSONArray reviewArray = new JSONArray();
            for (Review review : song.getReviews()) {
                reviewArray.add(review.toJson());
            }
            songObject.put("reviews", reviewArray);

            // Serialize Genres
            JSONArray genreArray = new JSONArray();
            for (Genre genre : song.getGenres()) {
                genreArray.add(genre.toString());
            }
            songObject.put("genres", genreArray);

            // Serialize Measures
            JSONArray measureArray = new JSONArray();
            for (Measure measure : song.getMeasures()) {
                measureArray.add(measure.toJson());
            }
            songObject.put("measures", measureArray);

            songObject.put("completed", song.isCompleted());
            songList.add(songObject);
        }

        writeToFile("src\\main\\java\\com\\data\\json\\songs.json", songList);
    }

    public static void saveLessons(List<Lesson> lessons) {
        JSONArray lessonList = new JSONArray();

        for (Lesson lesson : lessons) {
            JSONObject lessonObject = new JSONObject();
            lessonObject.put("id", lesson.getId().toString());
            lessonObject.put("topic", lesson.getTopic());
            lessonObject.put("progress", lesson.getProgress());
            lessonObject.put("complete", lesson.isComplete());

            // Serialize Songs as UUIDs
            lessonObject.put("songs", convertUUIDListToJSON(lesson.getSongs().stream().map(Song::getId).collect(Collectors.toList())
            ));

            // Serialize Assignments
            JSONArray assignmentArray = new JSONArray();
            for (Assignment assignment : lesson.getAssignments()) {
                assignmentArray.add(assignment.toJson());
            }
            lessonObject.put("assignments", assignmentArray);

            lessonList.add(lessonObject);
        }

        writeToFile("src\\main\\java\\com\\data\\json\\Lesson.json", lessonList);
    }

    private static JSONArray convertUUIDListToJSON(List<UUID> uuids) {
        JSONArray jsonArray = new JSONArray();
        for (UUID id : uuids) {
            jsonArray.add(id.toString());
        }
        return jsonArray;
    }

    private static String convertUUIDListToCommaString(List<UUID> uuids) {
        StringBuilder sb = new StringBuilder();
        for (UUID id : uuids) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(id.toString());
        }
        return sb.toString();
    }

    private static void writeToFile(String fileName, JSONArray data) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(data.toJSONString());
            file.flush();
            System.out.println(fileName + " written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create lists for users, songs, and lessons
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
    
        // Example User
        User user = new User("username1", "password1", "email1", "name1", "Question1", "Answer1");
        users.add(user);
    
        // Example Song
        Song song = new Song("title2", "Myself", 1, 2, 0, new ArrayList<>(), Difficulty.BEGINNER, new ArrayList<>());
        songs.add(song);
    
        // Example Lesson
        Lesson lesson = new Lesson(new ArrayList<>(), "topic3", new ArrayList<>());
        lessons.add(lesson);
    
        // Save data to JSON
        DataWriter.saveUsers(users);
        DataWriter.saveSongs(songs);
        DataWriter.saveLessons(lessons);
    
        System.out.println("Data saved to JSON files.");
    }
}
*/



/*
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
    public static void saveUsers(List<User> users) {
        JSONArray userList = new JSONArray();
        for (User user : users) {
            userList.add(user.toJson());
        }
        writeToFile(USER_FILE_NAME, userList);
    }

    public static void saveSongs(List<Song> songs) {
        JSONArray songList = new JSONArray();
    
        for (Song song : songs) {
            JSONObject songObject = new JSONObject();
            songObject.put("id", song.getId().toString());
            songObject.put("title", song.getTitle());
            songObject.put("artist", song.getArtist());
            songObject.put("runLengthMin", song.getRunLengthMin());
            songObject.put("runLengthSec", song.getRunLengthSec());
            songObject.put("tempo", song.getTempo());
            songObject.put("rating", song.getRating());
            songObject.put("metronomeOn", song.isMetronomeOn());
            songObject.put("difficulty", song.getDifficulty().toString());
            songObject.put("completed", song.isCompleted());
    
            // Serialize Reviews
            JSONArray reviewArray = new JSONArray();
            for (Review review : song.getReviews()) {
                JSONObject reviewObject = new JSONObject();
                reviewObject.put("rating", review.getRating());
                reviewObject.put("author", review.getAuthor());
                reviewObject.put("comment", review.getComment());
                reviewArray.add(reviewObject);
            }
            songObject.put("reviews", reviewArray);
    
            // Serialize Genres
            JSONArray genreArray = new JSONArray();
            for (Genre genre : song.getGenres()) {
                genreArray.add(genre.toString());
            }
            songObject.put("genres", genreArray);
    
            // Serialize Measures and Notes
            JSONArray measureArray = new JSONArray();
            for (Measure measure : song.getMeasures()) {
                JSONObject measureObject = new JSONObject();
                measureObject.put("timeSignatureTop", measure.getTimeSignatureTop());
                measureObject.put("timeSignatureBottom", measure.getTimeSignatureBottom());
    
                JSONArray noteArray = new JSONArray();
                for (Sound sound : measure.getNotes()) {
                    noteArray.add(sound.toJson()); // Use polymorphism to call correct toJson()
                }
                measureObject.put("notes", noteArray);
                measureArray.add(measureObject);
            }
            songObject.put("measures", measureArray);
    
            songList.add(songObject);
        }
    
        writeToFile("songs.json", songList);
    }
    

    // Method to write lessons to the JSON file
    public static void saveLessons(List<Lesson> lessons) {
        JSONArray lessonList = new JSONArray();
        for (Lesson lesson : lessons) {
            lessonList.add(lesson.toJson());
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
        // Create user, song, and lesson lists
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

        // Sample test data
        User user = new User("username.test1", "password.test1", "email1", "name1", "Question1", "Answer1");
        users.add(user);
        DataWriter.saveUsers(users);

        Song song = new Song("title2.test", "Myself.test", 1, 2, 0, new ArrayList<Genre>(), Difficulty.BEGINNER, new ArrayList<Measure>());
        songs.add(song);
        DataWriter.saveSongs(songs);

        Lesson lesson = new Lesson(new ArrayList<Song>(), "topic3.test", new ArrayList<Assignment>());
        lessons.add(lesson);
        DataWriter.saveLessons(lessons); 
    }
}
*/



/* import java.io.File;
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
    
            JSONArray favoriteSongsArray = new JSONArray();
            for (Song song : user.getFavoriteSongs()) {
                favoriteSongsArray.add(song.toJson());
            }
            userObject.put(USER_FAVORITE_SONGS, favoriteSongsArray);
    
            JSONArray completedSongsArray = new JSONArray();
            for (Song song : user.getCompletedSongs()) {
                completedSongsArray.add(song.toJson());
            }
            userObject.put(USER_COMPLETED_SONGS, completedSongsArray);
    
            JSONArray completedLessonsArray = new JSONArray();
            for (Lesson lesson : user.getCompletedLessons()) {
                completedLessonsArray.add(lesson.toJson());
            }
            userObject.put(USER_COMPLETED_LESSONS, completedLessonsArray);
    
            JSONArray mySongsArray = new JSONArray();
            for (Song song : user.getMySongs()) {
                mySongsArray.add(song.toJson());
            }
            userObject.put(USER_MY_SONGS, mySongsArray);
    
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
    */