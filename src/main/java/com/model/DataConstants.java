package com.model;

public abstract class DataConstants {
    // user class constants
    protected static final String USER_FILE_NAME = "data/users.json";
    protected static final String USER_ID = "id";
    protected static final String USER_USERNAME = "username";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_NAME = "name";
    protected static final String USER_FAVORITE_SONGS = "favoriteSongs";
    protected static final String USER_COMPLETED_SONGS = "completedSongs";
    protected static final String USER_COMPLETED_LESSONS = "completedLessons";
    protected static final String USER_MY_SONGS = "mySongs";
    protected static final String USER_SECURITY_QUESTION = "securityQuestion";
    protected static final String USER_SECURITY_ANSWER = "securityAnswer";

    // song class constants
    protected static final String SONG_FILE_NAME = "data/songs.json";
    protected static final String SONG_ID = "id";
    protected static final String SONG_TITLE = "title";
    protected static final String SONG_ARTIST = "artist";
    protected static final String SONG_RUN_LENGTH_MIN = "runLengthMin";
    protected static final String SONG_RUN_LENGTH_SEC = "runLengthSec";
    protected static final String SONG_TEMPO = "tempo";
    protected static final String SONG_RATING = "rating";
    protected static final String SONG_REVIEWS = "reviews";
    protected static final String SONG_METRONOME_ON = "metronomeOn";
    protected static final String SONG_GENRES = "genres";
    protected static final String SONG_DIFFICULTY = "difficulty";
    protected static final String SONG_MEASURES = "measures";
    protected static final String SONG_COMPLETED = "completed";
    // measure class constants (used when reading songs file)
    protected static final String MEASURE_TIME_SIGNATURE_TOP = "timeSignatureTop";
    protected static final String MEASURE_TIME_SIGNATURE_BOTTOM = "timeSignatureBottom";
    protected static final String MEASURE_NOTES = "notes";
    // review class constants
    protected static final String REVIEW_RATING = "rating";
    protected static final String REVIEW_COMMENT = "comment";
    protected static final String REVIEW_AUTHOR = "id";
    // note class constants
    protected static final String NOTE_TYPE = "type";
    protected static final String NOTE_LENGTH = "length";
    protected static final String NOTE_PITCH = "pitch";
    protected static final String NOTE_STRING = "string";
    protected static final String NOTE_FRET = "fret";

    // lesson class constants
    protected static final String LESSON_FILE_NAME = "data/Lessons.json";
    protected static final String LESSON_ID = "id";
    protected static final String LESSON_SONGS = "songs";
    protected static final String LESSON_TOPIC = "topic";
    protected static final String LESSON_ASSIGNMENTS = "assignments";
    protected static final String LESSON_PROGRESS = "progress";
    protected static final String LESSON_COMPLETE = "complete";
    // assignment class constants (used when reading lessons file)
    protected static final String ASSIGNMENT_GRADE = "grade";
    protected static final String ASSIGNMENT_TEACHER_COMMENT = "teacherComment";
    protected static final String ASSIGNMENT_STUDENT_COMMENT = "studentComment";
    protected static final String ASSIGNMENT_DUE_DATE = "dueDate";
    protected static final String ASSIGNMENT_COMPLETE = "complete";
}