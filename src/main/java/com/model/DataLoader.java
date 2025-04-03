package com.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * DataLoader class reads from a json file and converts json objects to objects used
 * within the code
 * @author aeszala
 */
public class DataLoader extends DataConstants {
    private static ArrayList<Song> songCache = Songlist.getInstance().getSongs();
    private static ArrayList<Lesson> lessonCache = LessonList.getInstance().getLessons();

    /**
     * reads a users.json file and creates objects from it
     * @return an ArrayList of Users
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        BufferedReader reader = getReaderFromFile(USER_FILE_NAME, USER_FILE_NAME_JSON);
        try {
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
                ArrayList<Song> favoriteSongs = getSongsFromUUIDs(favoriteSongsIDs);

                // fill completedSong list
                JSONArray completedSongsIDs = (JSONArray)personJSON.get(USER_COMPLETED_SONGS);
                ArrayList<Song> completedSongs = getSongsFromUUIDs(completedSongsIDs);

                // fill completedLessons list
                JSONArray completedLessonsIDs = (JSONArray)personJSON.get(USER_COMPLETED_LESSONS);
                ArrayList<Lesson> completedLessons = getLessonsFromUUIDs(completedLessonsIDs);

                // fill mySongs list
                JSONArray mySongsIDs = (JSONArray)personJSON.get(USER_MY_SONGS);
                ArrayList<Song> mySongs = getSongsFromUUIDs(mySongsIDs);
			
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

    /**
     * reads a songs.json file and creates objects from it
     * @return an Arraylist of Songs
     */
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
                Genre genre;
                for (int j = 0; j < genresJSON.size(); j++) {
                    genre = Genre.valueOf(genresJSON.get(j).toString().toUpperCase());
                    if (genre != null)
                        genres.add(genre);
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
    
    /**
     * reads a Lesson.json file and creates objects from it
     * @return an ArrayList of Lessons
     */
    public static ArrayList<Lesson> getLessons() {
        ArrayList<Lesson> lessons = new ArrayList<>();
        try {
            FileReader reader = new FileReader(LESSON_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray lessonsJSON = (JSONArray) parser.parse(reader);
    
            for (int i = 0; i < lessonsJSON.size(); i++) {
                JSONObject lessonJSON = (JSONObject)lessonsJSON.get(i);
                String title = (String) lessonJSON.get(LESSON_TITLE);
                UUID id = UUID.fromString((String) lessonJSON.get(LESSON_ID));
                String topic = (String) lessonJSON.get(LESSON_TOPIC);
                double progress = ((Number) lessonJSON.get(LESSON_PROGRESS)).doubleValue();
                boolean complete = (boolean) lessonJSON.get(LESSON_COMPLETE);
    
                // fill Songs list
                JSONArray songsIDs = (JSONArray)lessonJSON.get(LESSON_SONGS);
                ArrayList<Song> songs = new ArrayList<Song>();
                for (int j = 0; j < songsIDs.size(); j++) {
                    songs.add(findSongById(UUID.fromString(songsIDs.get(j).toString())));
                }

                // Fill assignments list
                JSONArray assignmentsJSON = (JSONArray)lessonJSON.get(LESSON_ASSIGNMENTS);
                ArrayList<Assignment> assignments = new ArrayList<Assignment>();
                for (int j = 0; j < assignmentsJSON.size(); j++) {
                    assignments.add(createAssignment(assignmentsJSON.get(j).toString()));
                }
                
                lessons.add(new Lesson(title, id, songs, topic, assignments, progress, complete));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessons;
    }

    /**
     * returns Song objects based on related UUID's
     * @param songUUIDs a JSONArray containing songUUIDs to be searched
     * @return corresponding Song objects which match the given UUIDs
     */
    private static ArrayList<Song> getSongsFromUUIDs(JSONArray songUUIDs) {
        ArrayList<Song> songs = new ArrayList<>();
        if (songUUIDs != null) {
            for (int i = 0; i < songUUIDs.size(); i++) {
                Song song = findSongById(UUID.fromString((String) songUUIDs.get(i)));
                if (song != null) {
                    songs.add(song);
                }
            }
        }
        return songs;
    }
    
    /**
     * returns Lesson objects based on related UUID's
     * @param lessonUUIDs a JSONArray containing lessonUUIDs to be searched
     * @return corresponding Lesson objects which match the given UUIDs
     */
    private static ArrayList<Lesson> getLessonsFromUUIDs(JSONArray lessonUUIDs) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (Object uuid : lessonUUIDs) {
            Lesson lesson = findLessonById(UUID.fromString((String) uuid));
            if (lesson != null)
                lessons.add(lesson);
        }
        return lessons;
    }
    
    /**
     * searches for a Song which matches the given UUID arguent to return
     * @param id the id of the song that is to be returned
     * @return the song which matched the given UUID
     */
    private static Song findSongById(UUID id) {
        if (songCache == null) {
            songCache = Songlist.getInstance().getSongs();
        }
        for (Song song : songCache) {
            if (song.getId().equals(id))
                return song;
        }
        return null;
    }
    
    /**
     * searches for a Lesson which matches the given UUID arguent to return
     * @param id the id of the lesson that is to be returned
     * @return the lesson which matched the given UUID
     */
    private static Lesson findLessonById(UUID id) {
        if (lessonCache == null) {
            lessonCache = DataLoader.getLessons();
        }
        for (Lesson lesson : lessonCache) {
            if (lesson.getId().equals(id))
                return lesson;
        }
        return null;
    }

    /**
     * reads the review section of the json file and constructs a review object
     * @param reviewString a string containing all the info of the reviews
     * @return a review constructed from the given info
     */
    private static Review createReview(String reviewString) {
        // Define regex patterns to extract the values
        String ratingPattern = "\"rating\":(\\d+)";
        String commentPattern = "\"comment\":\"([^\"]+)\"";
        String authorPattern = "\"author\":\"([^\"]+)\"";

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

        // Extract author
        Matcher authorMatcher = Pattern.compile(authorPattern).matcher(reviewString);
        if (authorMatcher.find()) {
            values.add(authorMatcher.group(1)); // Add the author value
        }

        // create review object
        Review review = new Review(Double.parseDouble(values.get(0)), values.get(1), values.get(2));
        return review;
    }

    /**
     * reads the measure section of the json file and constructs a measure object
     * @param jsonString a string containing all the info of the measures
     * @return a measure constructed from the given info
     */
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
    
            if (soundsJSON != null && !soundsJSON.isEmpty()) {
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
    
                            chordNotes.add(new Note(noteType, length, pitch, stringNumber, fret));
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
                        Note newNote = new Note(type, length, pitch, stringNumber, fret);
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

    /**
     * reads the assignment section of the json file and constructs an assignment object
     * @param jsonString a string containing all the info of the assignments
     * @return an assignment constructed from the given info
     */
    private static Assignment createAssignment(String jsonString) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject assigmentJSON = (JSONObject) parser.parse(jsonString);
    
            String title = (String)assigmentJSON.get(ASSIGNMENT_TITLE);
            double grade;
            if (assigmentJSON.get(ASSIGNMENT_GRADE) == null )
                grade = -1; // no grade
            else
                grade = ((Number) assigmentJSON.get(ASSIGNMENT_GRADE)).doubleValue();
            String teacherComment = (String)assigmentJSON.get(ASSIGNMENT_TEACHER_COMMENT);
            String studentComment = (String)assigmentJSON.get(ASSIGNMENT_STUDENT_COMMENT);
            String dateString = (String)assigmentJSON.get(ASSIGNMENT_DUE_DATE);
            Date dueDate = createDate(dateString);
            boolean complete = (boolean)assigmentJSON.get(ASSIGNMENT_COMPLETE);
    
            // Create and return the Assignment object
            return new Assignment(title, grade, teacherComment, studentComment, dueDate, complete);

        } catch (org.json.simple.parser.ParseException e) {
                    e.printStackTrace();
                    return null;
                }
    }    

    /**
     * converts the date sting to a date object
     * @param dateString a string formatted date
     * @return the date as a Date object
     */
    private static Date createDate(String dateString) {
        String[] formats = {"yyyy-MM-dd", "yyyy/MM/dd"}; // Allowed formats

        for (String format : formats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setLenient(false); // Prevents invalid dates like 2024-02-30
                Date date = sdf.parse(dateString);
                return date; // Converts to Date object
            } catch (ParseException ignored) {
                // Try next format
            }
        }
        return new Date();
    }

	private static String getFileWritingPath(String PATH_NAME, String JUNIT_PATH_NAME) {
		try {
			if(isJUnitTest()){
				URI url = DataLoader.class.getResource(JUNIT_PATH_NAME).toURI();
				return url.getPath();
			} else {
				return PATH_NAME;
			}
		} catch(Exception e){
			System.out.println("Difficulty getting resource path");
			return "";
		}
	}

	private static BufferedReader getReaderFromFile(String fileName, String jsonFileName){
		try {
			if(isJUnitTest()){
				FileReader reader = new FileReader(fileName);
				return new BufferedReader(reader);
				// InputStream inputStream = DataLoader.class.getResourceAsStream(jsonFileName);
				// InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				// return new BufferedReader(inputStreamReader);
			} else {
				FileReader reader = new FileReader(fileName);
				return new BufferedReader(reader);
			}
		} catch(Exception e){
			System.out.println("Can't load");
			return null;
		}
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