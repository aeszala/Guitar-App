package com.model;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Locale;
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

                // fill favoriteSong list
                JSONArray favoriteSongsIDs = (JSONArray)personJSON.get(USER_FAVORITE_SONGS);
                ArrayList<Song> favoriteSongs = new ArrayList<Song>();
                for (int j = 0; j < favoriteSongsIDs.size(); j++) {
                    // favoriteSongs.add(findSongById(UUID.fromString(favoriteSongsIDs.get(j).toString())));
                }

                // fill completedSong list
                JSONArray completedSongsIDs = (JSONArray)personJSON.get(USER_COMPLETED_SONGS);
                ArrayList<Song> completedSongs = new ArrayList<Song>();
                for (int j = 0; j < completedSongsIDs.size(); j++) {
                    //completedSongs.add(findSongById(UUID.fromString(completedSongsIDs.get(j).toString())));
                }

                // fill completedLessons list
                JSONArray completedLessonsIDs = (JSONArray)personJSON.get(USER_COMPLETED_LESSONS);
                ArrayList<Lesson> completedLessons = new ArrayList<Lesson>();
                for (int j = 0; j < completedLessonsIDs.size(); j++) {
                    //completedLessons.add(findSongById(UUID.fromString(completedLessonsIDs.get(j).toString())));
                }

                // fill mySongs list
                // JSONArray mySongsIDs = (JSONArray)personJSON.get(USER_MY_SONGS);
                ArrayList<Song> mySongs = new ArrayList<Song>();
                // for (int j = 0; j < mySongsIDs.size(); j++) {
                //     //mySongs.add(findSongById(UUID.fromString(mySongsIDs.get(j).toString())));
                // }
			
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
            FileReader reader = new FileReader(SONG_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray songsJSON = (JSONArray) parser.parse(reader);
    
            for (int i = 0; i < songsJSON.size(); i++) {
                JSONObject songJSON = (JSONObject)songsJSON.get(i);
                UUID id = UUID.fromString((String) songJSON.get(SONG_ID));
                String title = (String) songJSON.get(SONG_TITLE);
                String artist = (String) songJSON.get(SONG_ARTIST);
                int runLengthMin = ((Long) songJSON.get(SONG_RUN_LENGTH_MIN)).intValue();
                int runLengthSec = ((Long) songJSON.get(SONG_RUN_LENGTH_SEC)).intValue();
                int tempo = ((Long) songJSON.get(SONG_TEMPO)).intValue();
                double rating = ((Number) songJSON.get(SONG_RATING)).doubleValue();
                boolean metronomeOn = (boolean) songJSON.get(SONG_METRONOME_ON);
                String difficultyString = (String) songJSON.get(SONG_DIFFICULTY);
                Difficulty difficulty = Difficulty.valueOf(difficultyString.toUpperCase()); // Convert String to Enum
                if (difficulty == null)
                    difficulty = Difficulty.INTERMEDIATE;
                boolean completed = (boolean) songJSON.get(SONG_COMPLETED);
    
                // fill Reviews list
                JSONArray reviewsJSON = (JSONArray)songJSON.get(SONG_REVIEWS);
                ArrayList<Review> reviews = new ArrayList<Review>();
                for (int j = 0; j < reviewsJSON.size(); j++) {
                    reviews.add(createReview(reviewsJSON.get(j).toString()));
                }
    
                // Fill Genres list
                JSONArray genresJSON = (JSONArray)songJSON.get(SONG_GENRES);
                ArrayList<Genre> genres = new ArrayList<Genre>();
                for (int j = 0; j < genresJSON.size(); j++) {
                    genres.add(getGenre(genresJSON.get(j).toString()));
                }
    
                // Fill Measures list
                JSONArray measuresJSON = (JSONArray)songJSON.get(SONG_MEASURES);
                ArrayList<Measure> measures = new ArrayList<Measure>();
                for (int j = 0; j < measuresJSON.size(); j++) {
                    measures.add(createMeasure(measuresJSON.get(j).toString()));
                }
    
                songs.add(new Song(id, title, artist, runLengthMin, runLengthSec,
                        tempo, rating, reviews, metronomeOn, genres, difficulty,
                        measures, completed));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }
    
    public static ArrayList<Lesson> getLessons() {
        ArrayList<Lesson> lessons = new ArrayList<>();
    
        try {
            FileReader reader = new FileReader(LESSON_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray lessonsJSON = (JSONArray) parser.parse(reader);
    
            for (int i = 0; i < lessonsJSON.size(); i++) {
                JSONObject lessonJSON = (JSONObject)lessonsJSON.get(i);
                UUID id = UUID.fromString((String) lessonJSON.get(LESSON_ID));
                String topic = (String) lessonJSON.get(LESSON_TOPIC);
                double progress = ((Number) lessonJSON.get(LESSON_PROGRESS)).doubleValue();
                boolean complete = (boolean) lessonJSON.get(LESSON_COMPLETE);
    
                // fill Songs list
                JSONArray songsIDs = (JSONArray)lessonJSON.get(LESSON_SONGS);
                ArrayList<Song> songs = new ArrayList<Song>();
                for (int j = 0; j < songsIDs.size(); j++) {
                    // songs.add(findSongById(UUID.fromString(songsIDs.get(j).toString())));
                }

                // Fill assignments list
                JSONArray assignmentsJSON = (JSONArray)lessonJSON.get(LESSON_ASSIGNMENTS);
                ArrayList<Assignment> assignments = new ArrayList<Assignment>();
                for (int j = 0; j < assignmentsJSON.size(); j++) {
                    assignments.add(createAssignment(assignmentsJSON.get(j).toString()));
                }
                
                lessons.add(new Lesson(id, songs, topic, assignments, progress, complete));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessons;
    }

    private static User findUserById(UUID id) {
        for (User user : getUsers()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return new User("JohnDoe", "ABC123", "123@email.com", "John Doe", "What's your first pet's name?", "Spot");
    }

    private static ArrayList<Song> getSongsFromUUIDs(JSONArray songUUIDs) {
        ArrayList<Song> songs = new ArrayList<Song>();
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
                notes.add(new Note(type, length, pitch, stringNumber, fret, "note"));
                // edit this because sound could be a note or a chord !!!!
            }
        }
        return notes;
    }

    private static ArrayList<Assignment> getAssignmentsFromJSON(JSONArray assignmentsJSON) {
        ArrayList<Assignment> assignments = new ArrayList<>();
        if (assignmentsJSON != null) {
            for (Object obj : assignmentsJSON) {
                JSONObject assignmentJSON = (JSONObject) obj;
                double grade = (double) assignmentJSON.get(ASSIGNMENT_GRADE);
                String teacherComment = (String) assignmentJSON.get(ASSIGNMENT_TEACHER_COMMENT);
                String studentComment = (String) assignmentJSON.get(ASSIGNMENT_STUDENT_COMMENT);
                Date dueDate = toDate((String) assignmentJSON.get(ASSIGNMENT_DUE_DATE)); // may need to read as string first
                boolean complete = (boolean) assignmentJSON.get(ASSIGNMENT_COMPLETE);

                assignments.add(new Assignment(grade, teacherComment, studentComment, dueDate, complete));
            }
        }
        return assignments;
    }

    private static Date toDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            return formatter.parse(date);  // Parse the string into a Date object
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // Return null in case of an error
        }
    }

    private static Review createReview(String reviewString) {
        // Define regex patterns to extract the values
        String ratingPattern = "\"rating\":(\\d+)";
        String commentPattern = "\"comment\":\"([^\"]+)\"";
        String idPattern = "\"id\":\"([^\"]+)\"";

        // Create a list to store the extracted values
        List<String> values = new ArrayList<>();

        // Extract rating
        Matcher ratingMatcher = Pattern.compile(ratingPattern).matcher(reviewString);
        if (ratingMatcher.find()) {
            values.add(ratingMatcher.group(1)); // Add the rating value
        }

        // Extract comment
        Matcher commentMatcher = Pattern.compile(commentPattern).matcher(reviewString);
        if (commentMatcher.find()) {
            values.add(commentMatcher.group(1)); // Add the comment value
        }

        // Extract id
        Matcher idMatcher = Pattern.compile(idPattern).matcher(reviewString);
        if (idMatcher.find()) {
            values.add(idMatcher.group(1)); // Add the id value
        }

        // create review object
        User author = findUserById(UUID.fromString(values.get(2).toString()));
        Review review = new Review(Double.parseDouble(values.get(0)), values.get(1), author);
        return review;
    }

    private static Measure createMeasure(String jsonString) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject measureJSON = (JSONObject) parser.parse(jsonString);
    
            // Extract time signature
            int timeSignatureTop = ((Long) measureJSON.get(MEASURE_TIME_SIGNATURE_TOP)).intValue();
            int timeSignatureBottom = ((Long) measureJSON.get(MEASURE_TIME_SIGNATURE_BOTTOM)).intValue();
    
            // Extract sounds array
            JSONArray soundsJSON = (JSONArray) measureJSON.get(MEASURE_NOTES);
            ArrayList<Sound> sounds = new ArrayList<>();
    
            if (soundsJSON != null) {
                for (Object obj : soundsJSON) {
                    JSONObject soundJSON = (JSONObject) obj;
                    // Check if it's a Chord (has a "notes" array)
                    if (soundJSON.containsKey("notes")) {
                        String type = (String) soundJSON.get("type");
                        JSONArray chordNotesJSON = (JSONArray) soundJSON.get("notes");
                        ArrayList<Note> chordNotes = new ArrayList<>();
    
                        for (Object noteObj : chordNotesJSON) {
                            JSONObject noteJSON = (JSONObject) noteObj;
                            String noteType = (String) noteJSON.get("type");
                            double length = ((Number) noteJSON.get("length")).doubleValue();
                            double pitch = ((Number) noteJSON.get("pitch")).doubleValue();
                            int stringNumber = ((Long) noteJSON.get("string")).intValue();
                            int fret = ((Long) noteJSON.get("fret")).intValue();
    
                            chordNotes.add(new Note(noteType, length, pitch, stringNumber, fret, "note"));
                        }
                        // Create a Chord object and add to sounds list
                        sounds.add(new Chord(type, chordNotes, "chord"));

                    } else {
                        // It's a Note
                        String type = (String) soundJSON.get("type");
                        double length = ((Number) soundJSON.get("length")).doubleValue();
                        double pitch = ((Number) soundJSON.get("pitch")).doubleValue();
                        int stringNumber = ((Long) soundJSON.get("string")).intValue();
                        int fret = ((Long) soundJSON.get("fret")).intValue();
                        Note newNote = new Note(type, length, pitch, stringNumber, fret, "note");
                        sounds.add(newNote);
                    }
                }
            }
    
            // Create and return the Measure object
            return new Measure(timeSignatureTop, timeSignatureBottom, sounds);

        } catch (org.json.simple.parser.ParseException e) {
                    e.printStackTrace();
                    return null;
                }
    }    

    private static Assignment createAssignment(String jsonString) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject assigmentJSON = (JSONObject) parser.parse(jsonString);
    
            double grade;
            if (assigmentJSON.get(ASSIGNMENT_GRADE) == null )
                grade = 0;
            else
                grade = ((Number) assigmentJSON.get(ASSIGNMENT_GRADE)).doubleValue();
            String teacherComment = (String)assigmentJSON.get(ASSIGNMENT_TEACHER_COMMENT);
            String studentComment = (String)assigmentJSON.get(ASSIGNMENT_STUDENT_COMMENT);
            Date date = new Date();     // Match FileWriter format later
            boolean complete = (boolean)assigmentJSON.get(ASSIGNMENT_COMPLETE);

            // Extract sounds array
            JSONArray soundsJSON = (JSONArray) assigmentJSON.get(MEASURE_NOTES);
            ArrayList<Sound> sounds = new ArrayList<>();
    
            // Create and return the Assignment object
            return new Assignment(grade, teacherComment, studentComment, date, complete);

        } catch (org.json.simple.parser.ParseException e) {
                    e.printStackTrace();
                    return null;
                }
    }    

    private static Genre getGenre(String genreString) {
        Genre genre = null;
        switch (genreString) {
            case "ROCK":
                genre = Genre.ROCK;
                break;
            case "POP":
                genre = Genre.POP;
                break;
            case "COUNTRY":
                genre = Genre.COUNTRY;
                break;
            case "HIPHOP":
                genre = Genre.HIPHOP;
                break;
            case "ELECTRONIC":
                genre = Genre.ELECTRONIC;
                break;
            case "JAZZ":
                genre = Genre.JAZZ;
                break;
            case "CLASSICAL":
                genre = Genre.CLASSICAL;
                break;
            case "SOUL":
                genre = Genre.SOUL;
                break;
            case "INDIE":
                genre = Genre.INDIE;
                break;
        }
        return genre;
    }

    public static void main(String[] args) {
        ArrayList<User> users = DataLoader.getUsers();
        ArrayList<Song> songs = DataLoader.getSongs();
        ArrayList<Lesson> lessons = DataLoader.getLessons();

        System.out.println("Songs:");
        for (Song song : songs) {
            System.out.println(song);
        }

        System.out.println("\nUsers:");
        for(User user : users) {
            System.out.println(user);
        }

        System.out.println("\nLessons:");
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
        
    }

}