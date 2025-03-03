package com.model;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
			JSONParser parser = new JSONParser();	
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);

            for (int i = 0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				UUID id = UUID.fromString((String)personJSON.get(USER_ID));
				String username = (String)personJSON.get(USER_USERNAME);
                String password = (String)personJSON.get(USER_PASSWORD);
                String email = (String)personJSON.get(USER_EMAIL);
                String name = (String)personJSON.get(USER_NAME);
                String securityQuestion = (String)personJSON.get(USER_SECURITY_QUESTION);
                String securityAnswer = (String)personJSON.get(USER_SECURITY_ANSWER);

                ArrayList<Song> favoriteSongs = getSongsFromUUIDs((JSONArray)personJSON.get(USER_FAVORITE_SONGS));
                ArrayList<Song> completedSongs = getSongsFromUUIDs((JSONArray)personJSON.get(USER_COMPLETED_SONGS));
                ArrayList<Lesson> completedLessons = getLessonsFromUUIDs((JSONArray)personJSON.get(USER_COMPLETED_LESSONS));
                ArrayList<Song> mySongs = getSongsFromUUIDs((JSONArray)personJSON.get(USER_MY_SONGS));
			
				users.add(new User(id, username, password, email, name,
                favoriteSongs, completedSongs, completedLessons, mySongs,
                securityQuestion, securityAnswer));
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }  

    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<>();
    
        try {
            FileReader reader = new FileReader(SONG_FILE_NAME); // Corrected from USER_FILE_NAME
            JSONParser parser = new JSONParser();
            JSONArray songsJSON = (JSONArray) parser.parse(reader);
    
            for (Object obj : songsJSON) {
                JSONObject songJSON = (JSONObject) obj;
                UUID id = UUID.fromString((String) songJSON.get(SONG_ID));
                String title = (String) songJSON.get(SONG_TITLE);
                String artist = (String) songJSON.get(SONG_ARTIST);
                int runLengthMin = ((Long) songJSON.get(SONG_RUN_LENGTH_MIN)).intValue();
                int runLengthSec = ((Long) songJSON.get(SONG_RUN_LENGTH_SEC)).intValue();
                int tempo = ((Long) songJSON.get(SONG_TEMPO)).intValue();
                double rating = (double) songJSON.get(SONG_RATING);
                boolean metronomeOn = (boolean) songJSON.get(SONG_METRONOME_ON);
                Difficulty difficulty = Difficulty.valueOf((String) songJSON.get(SONG_DIFFICULTY)); // Convert String to Enum
                boolean completed = (boolean) songJSON.get(SONG_COMPLETED);
    
                // Parsing Reviews
                ArrayList<Review> reviews = getReviewsFromJSON((JSONArray) songJSON.get(SONG_REVIEWS));
    
                // Parsing Genres (Assuming it's stored as an array of strings)
                ArrayList<Genre> genres = getGenresFromJSON((JSONArray) songJSON.get(SONG_GENRES));
    
                // Parsing Measures
                ArrayList<Measure> measures = getMeasuresFromJSON((JSONArray) songJSON.get(SONG_MEASURES));
    
                songs.add(new Song(id, title, artist, runLengthMin, runLengthSec,
                        tempo, rating, reviews, metronomeOn, genres, difficulty,
                        measures, completed));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }
    

    private static User findUserById(UUID id) {
        for (User user : getUsers()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    private static ArrayList<Song> getSongsFromUUIDs(JSONArray songUUIDs) {
        ArrayList<Song> songs = new ArrayList<>();
        for (Object uuid : songUUIDs) {
            songs.add(findSongById(UUID.fromString((String) uuid)));
        }
        return songs;
    }
    
    private static ArrayList<Lesson> getLessonsFromUUIDs(JSONArray lessonUUIDs) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (Object uuid : lessonUUIDs) {
            lessons.add(findLessonById(UUID.fromString((String) uuid)));
        }
        return lessons;
    }
    
    private static Song findSongById(UUID id) {
        for (Song song : getSongs()) {
            if (song.getId().equals(id))
                return song;
        }
        return null;
    }
    
    private static Lesson findLessonById(UUID id) {
        for (Lesson lesson : getLessons()) {
            if (lesson.getId().equals(id))
                return lesson;
        }
        return null;
    }

    private static ArrayList<Review> getReviewsFromJSON(JSONArray reviewsJSON) {
        ArrayList<Review> reviews = new ArrayList<>();
        if (reviewsJSON != null) {
            for (Object obj : reviewsJSON) {
                JSONObject reviewJSON = (JSONObject) obj;
                int rating = ((Long) reviewJSON.get(REVIEW_RATING)).intValue();
                String comment = (String) reviewJSON.get(REVIEW_COMMENT);
                UUID id = UUID.fromString((String) reviewJSON.get(REVIEW_AUTHOR));
                User author = findUserById(id);
                reviews.add(new Review(rating, comment, author));
            }
        }
        return reviews;
    }
    
    private static ArrayList<Genre> getGenresFromJSON(JSONArray genresJSON) {
        ArrayList<Genre> genres = new ArrayList<>();
        if (genresJSON != null) {
            for (Object obj : genresJSON) {
                genres.add(Genre.valueOf((String) obj)); // Convert String to Enum
            }
        }
        return genres;
    }

    private static ArrayList<Measure> getMeasuresFromJSON(JSONArray measuresJSON) {
        ArrayList<Measure> measures = new ArrayList<>();
        if (measuresJSON != null) {
            for (Object obj : measuresJSON) {
                JSONObject measureJSON = (JSONObject) obj;
                int timeSignatureTop = ((Long) measureJSON.get(MEASURE_TIME_SIGNATURE_TOP)).intValue();
                int timeSignatureBottom = ((Long) measureJSON.get(MEASURE_TIME_SIGNATURE_BOTTOM)).intValue();
    
                // Parse Notes inside Measure
                ArrayList<Sound> notes = getNotesFromJSON((JSONArray) measureJSON.get(MEASURE_NOTES));
    
                measures.add(new Measure(timeSignatureTop, timeSignatureBottom, notes));
            }
        }
        return measures;
    }

    private static ArrayList<Sound> getNotesFromJSON(JSONArray notesJSON) {
        ArrayList<Sound> notes = new ArrayList<>();
        if (notesJSON != null) {
            for (Object obj : notesJSON) {
                JSONObject noteJSON = (JSONObject) obj;
                String type = (String) noteJSON.get(NOTE_TYPE);
                double length = (double) noteJSON.get(NOTE_LENGTH);
                double pitch = (double) noteJSON.get(NOTE_PITCH);
                int stringNumber = ((Long) noteJSON.get(NOTE_STRING)).intValue();
                String fret = (String) noteJSON.get(NOTE_FRET);
                notes.add(new Note(type, length, pitch, stringNumber, fret));
            }
        }
        return notes;
    }
    
    
    
}
