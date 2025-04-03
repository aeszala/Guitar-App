/**
 * @author Abbey
 */

package com.model;

import java.io.File;

/**
 * The {@code DataConstants} class defines constant values used for managing 
 * JSON data storage and retrieval in the application. 
 * 
 * This includes constants for file paths and JSON keys related to users, songs, 
 * lessons, assignments, measures, reviews, and notes.
 * 
 * <p>
 * All constants are protected and intended for use by other classes in the 
 * {@code com.model} package that need to read or write JSON data.
 * </p>
 */
public abstract class DataConstants {

    // ========================== User Class Constants ==========================

    /** Path to the JSON file storing user data. */
    protected static final String USER_FILE_NAME = "src/main/java/com/data/json/users.json";
    protected static final String USER_FILE_NAME_JSON = "src/test/java/com/data/json/users.json";

    /** JSON key for the user ID. */
    protected static final String USER_ID = "id";

    /** JSON key for the username. */
    protected static final String USER_USERNAME = "username";

    /** JSON key for the user password. */
    protected static final String USER_PASSWORD = "password";

    /** JSON key for the user email. */
    protected static final String USER_EMAIL = "email";

    /** JSON key for the user name. */
    protected static final String USER_NAME = "name";

    /** JSON key for the user's favorite songs. */
    protected static final String USER_FAVORITE_SONGS = "favoriteSongs";

    /** JSON key for the user's completed songs. */
    protected static final String USER_COMPLETED_SONGS = "completedSongs";

    /** JSON key for the user's completed lessons. */
    protected static final String USER_COMPLETED_LESSONS = "completedLessons";

    /** JSON key for the user's own created songs. */
    protected static final String USER_MY_SONGS = "mySongs";

    /** JSON key for the user's security question. */
    protected static final String USER_SECURITY_QUESTION = "securityQuestion";

    /** JSON key for the user's security answer. */
    protected static final String USER_SECURITY_ANSWER = "securityAnswer";

    // ========================== Song Class Constants ==========================

    /** Path to the JSON file storing song data. */
    protected static final String SONG_FILE_NAME = "src/main/java/com/data/json/songs.json";
    protected static final String SONG_FILE_NAME_JSON = "src/test/java/com/data/json/songs.json";

    /** JSON key for the song ID. */
    protected static final String SONG_ID = "id";

    /** JSON key for the song title. */
    protected static final String SONG_TITLE = "title";

    /** JSON key for the song artist. */
    protected static final String SONG_ARTIST = "artist";

    /** JSON key for the song runtime in minutes. */
    protected static final String SONG_RUN_LENGTH_MIN = "runLengthMin";

    /** JSON key for the song runtime in seconds. */
    protected static final String SONG_RUN_LENGTH_SEC = "runLengthSec";

    /** JSON key for the song's tempo (beats per minute). */
    protected static final String SONG_TEMPO = "tempo";

    /** JSON key for the song's rating. */
    protected static final String SONG_RATING = "rating";

    /** JSON key for the list of reviews for a song. */
    protected static final String SONG_REVIEWS = "reviews";

    /** JSON key indicating if the metronome is enabled. */
    protected static final String SONG_METRONOME_ON = "metronomeOn";

    /** JSON key for the song's genres. */
    protected static final String SONG_GENRES = "genres";

    /** JSON key for the song's difficulty level. */
    protected static final String SONG_DIFFICULTY = "difficulty";

    /** JSON key for the song's measures. */
    protected static final String SONG_MEASURES = "measures";

    /** JSON key indicating if the song is completed. */
    protected static final String SONG_COMPLETED = "completed";

    // ========================== Measure Class Constants ==========================

    /** JSON key for the top number of a measure's time signature. */
    protected static final String MEASURE_TIME_SIGNATURE_TOP = "timeSignatureTop";

    /** JSON key for the bottom number of a measure's time signature. */
    protected static final String MEASURE_TIME_SIGNATURE_BOTTOM = "timeSignatureBottom";

    /** JSON key for the list of notes in a measure. */
    protected static final String MEASURE_NOTES = "notes";

    // ========================== Review Class Constants ==========================

    /** JSON key for the review rating. */
    protected static final String REVIEW_RATING = "rating";

    /** JSON key for the review comment. */
    protected static final String REVIEW_COMMENT = "comment";

    /** JSON key for the ID of the review author. */
    protected static final String REVIEW_AUTHOR = "id";

    // ========================== Note Class Constants ==========================

    /** JSON key for the note type (e.g., quarter note, half note). */
    protected static final String NOTE_TYPE = "type";

    /** JSON key for the note length. */
    protected static final String NOTE_LENGTH = "length";

    /** JSON key for the note's pitch. */
    protected static final String NOTE_PITCH = "pitch";

    /** JSON key for the string number (for string instruments). */
    protected static final String NOTE_STRING = "string";

    /** JSON key for the fret number (for string instruments). */
    protected static final String NOTE_FRET = "fret";

    // ========================== Lesson Class Constants ==========================

    /** Path to the JSON file storing lesson data. */
    protected static final String LESSON_FILE_NAME = "src/main/java/com/data/json/Lesson.json";
    protected static final String LESSON_FILE_NAME_JSON = "src/test/java/com/data/json/Lesson.json";

    /** JSON key for the lesson title. */
    protected static final String LESSON_TITLE = "title";

    /** JSON key for the lesson ID. */
    protected static final String LESSON_ID = "id";

    /** JSON key for the list of songs in the lesson. */
    protected static final String LESSON_SONGS = "songs";

    /** JSON key for the lesson topic. */
    protected static final String LESSON_TOPIC = "topic";

    /** JSON key for the assignments associated with the lesson. */
    protected static final String LESSON_ASSIGNMENTS = "assignments";

    /** JSON key for the progress percentage of the lesson. */
    protected static final String LESSON_PROGRESS = "progress";

    /** JSON key indicating whether the lesson is complete. */
    protected static final String LESSON_COMPLETE = "complete";

    // ========================== Assignment Class Constants ==========================

    /** JSON key for the assignment title. */
    protected static final String ASSIGNMENT_TITLE = "title";

    /** JSON key for the assignment grade. */
    protected static final String ASSIGNMENT_GRADE = "grade";

    /** JSON key for the teacher's comment on the assignment. */
    protected static final String ASSIGNMENT_TEACHER_COMMENT = "teacherComment";

    /** JSON key for the student's comment on the assignment. */
    protected static final String ASSIGNMENT_STUDENT_COMMENT = "studentComment";

    /** JSON key for the assignment due date. */
    protected static final String ASSIGNMENT_DUE_DATE = "dueDate";

    /** JSON key indicating whether the assignment is complete. */
    protected static final String ASSIGNMENT_COMPLETE = "complete";

    public static boolean isJUnitTest() {  
		for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
		  if (element.getClassName().startsWith("org.junit.")) {
			return true;
		  }           
		}
		return false;
	  }

}
