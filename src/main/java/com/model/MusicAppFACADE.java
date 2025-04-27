/**
 * @author Everyone
 */

package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.program.MusicPlayer;

import java.text.SimpleDateFormat;

/**
 * The {@code MusicAppFACADE} class acts as a facade for the Music App, providing an interface 
 * to interact with users, students, teachers, songs, and assignments. It abstracts the internal 
 * operations of the system and provides high-level methods to manage accounts, songs, assignments, 
 * and user actions.
 * 
 * <p>The class includes:
 * <ul>
 *   <li>{@link #findSongs(String)} - Search for songs based on a keyword.</li>
 *   <li>{@link #createStudentAccount(String, String, String, String, Teacher, String, String)} - Create a student account.</li>
 *   <li>{@link #createTeacherAccount(String, String, String, String, String, String)} - Create a teacher account.</li>
 *   <li>{@link #createAccount(String, String, String, String, String, String)} - Create a generic user account.</li>
 *   <li>{@link #login(String, String)} - Handle user login.</li>
 *   <li>{@link #logOut()} - Handle user logout.</li>
 *   <li>{@link #getFavoriteSongs(ArrayList)} - Retrieve the list of favorite songs for the current user.</li>
 *   <li>{@link #getMySongs(ArrayList)} - Retrieve the list of songs owned by the current user.</li>
 *   <li>{@link #addSong(String, String, int, int, String, int, Measure)} - Add a new song to the user's collection.</li>
 *   <li>{@link #editSongTitle(String)} - Edit the title of a song.</li>
 *   <li>{@link #reviewSong(Song, int, String, Assignment)} - Review a song with a rating and comment.</li>
 *   <li>{@link #addGrade()} - Method for adding grades (currently not implemented).</li>
 *   <li>{@link #viewGrade(Assignment)} - View the grade for a specific assignment.</li>
 *   <li>{@link #turnIn(Assignment)} - Submit an assignment.</li>
 *   <li>{@link #turnIn(Lesson)} - Submit a lesson.</li>
 *   <li>{@link #addPlayAssignment(String, String, Date, Song, int)} - Assign a play assignment to a student.</li>
 *   <li>{@link #addQuestionAssignment(String, String, Date, ArrayList)} - Assign a question-based assignment to a student.</li>
 *   <li>{@link #addComposeAssignment(String, String, Date, Song, int, String)} - Assign a composition assignment to a student.</li>
 *   <li>{@link #viewLesson()} - Method for viewing lessons (currently not implemented).</li>
 *   <li>{@link #viewAssignments()} - Method for viewing assignments (currently not implemented).</li>
 * </ul>
 */
public class MusicAppFACADE {
    private static MusicAppFACADE instance;
    private static User user;
    private Student student;
    private Teacher teacher;
    private static Song song;
    private Assignment assignment;
    private UserList userList = UserList.getInstance();
    private Songlist songList = Songlist.getInstance();
    private ArrayList<Song> songs = new ArrayList<Song>();
    private Measure measure;

    /**
     * Constructor for MusicAppFACADE.
     * 
     * This constructor currently does nothing as the object is intended to be initialized with 
     * data through various methods.
     */
    private void MusicAppFACADE() {
    }

    public static synchronized MusicAppFACADE getInstance() {
        if (instance == null) {
            instance = new MusicAppFACADE();
        }
        return instance;
    }

    public String getProfilePicturePath() {
        return user != null 
            ? user.getProfilePicturePath() 
            : "/images/default_profile.png"; // Default path in resources
    }

    public static boolean setUser(User u) {
        if (u != null) {
            user = u;
            return true;
        }
        return false;
    }

    public static boolean setSong(Song s) {
        if (s != null) {
            song = s;
            return true;
        }
        return false;
    }

    /**
     * Finds songs based on a keyword.
     * 
     * @param keyword The keyword used to search for songs.
     * @return A list of {@link Song} objects that match the keyword.
     */
    public boolean findSongs(String keyword) {
        if (songList == null)
            songList = Songlist.getInstance();
        songs = songList.getSongs(keyword);
        return (!songs.isEmpty());
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public Song getSong() {
        return song;
    }

    public boolean displaySongs() {
        if (songs.isEmpty() || songs == null)
            songs = Songlist.getInstance().getSongs();
        int i = 1;
        for (Song song : songs) {
            System.out.println(i + ". " + song.getTitle() + " by " + song.getArtist());
            i++;
        }
        return (!songs.isEmpty());
    }

    public boolean saveSong() {
        if (measure != null)
            saveMeasure();
        Songlist.addSong(song);
        Songlist.saveSongs();
        return (Songlist.getSong(song.getTitle())!=null);
    }

    public void save() {
        if (measure != null)
        saveMeasure();
        Songlist.saveSongs();
        UserList.saveUsers();
    }

    public void createMeasure() {
        if (measure != null)
            saveMeasure();
        measure = new Measure();
        System.out.println("Measure created.");
    }

    public boolean saveMeasure() {
        song.addMeasure(measure);
        System.out.println("Measure added to song!");
        return (song.getMeasures().contains(measure));
    }

    public void addNote(String type, double length, double pitch, int string, int fret) {
        measure.addSound(new Note(type, length, pitch, string, fret));
    }

    /**
     * Creates a student account with the provided details.
     * 
     * @param name             The name of the student.
     * @param username         The username for the student account.
     * @param password         The password for the student account.
     * @param email            The email address of the student.
     * @param teacher          The teacher associated with the student.
     * @param securityQuestion The security question for account recovery.
     * @param securityAnswer   The answer to the security question.
     */
    public void createStudentAccount(String name, String username, String password, String email, Teacher teacher, String securityQuestion, String securityAnswer) {
        student = new Student(username, password, email, name, securityQuestion, securityAnswer, teacher);
    }

    /**
     * Creates a teacher account with the provided details.
     * 
     * @param name             The name of the teacher.
     * @param username         The username for the teacher account.
     * @param password         The password for the teacher account.
     * @param email            The email address of the teacher.
     * @param securityQuestion The security question for account recovery.
     * @param securityAnswer   The answer to the security question.
     */
    public void createTeacherAccount(String name, String username, String password, String email, String securityQuestion, String securityAnswer) {
        teacher = new Teacher(username, password, email, name, securityQuestion, securityAnswer);
    }

    /**
     * Creates a generic user account.
     * 
     * @param name             The name of the user.
     * @param username         The username for the account.
     * @param password         The password for the account.
     * @param email            The email address of the user.
     * @param securityQuestion The security question for account recovery.
     * @param securityAnswer   The answer to the security question.
     */
    public boolean createAccount(String name, String username, String password, String email, String securityQuestion, String securityAnswer) {
        if (userList == null)
            userList = UserList.getInstance();
        if (userList.addUser(username, password, email, username, securityQuestion, securityAnswer)) {
            user = UserList.getUser(username);
            return true;
        }
        return false;
    }

    /**
     * Logs in the user with the provided username and password.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public boolean login(String username, String password) {
        if (User.isMatch(username, password)) {
            user = UserList.getUser(username);
            System.out.println("Login Successful!");
            return true;
        } else {
            System.out.println("Username or password incorrect.");
            return false;
        }
    }

    public User getUser() {
        return user;
    }

    public void printTabSheet(String songTitle) {
        if (songList == null)
            songList = Songlist.getInstance();
        Song song = songList.getSong(songTitle);
        song.printAndSaveSheetMusic();
    }

    /**
     * Logs out the current user.
     */
    public void logOut() {
        user = null;
        Songlist.saveSongs();
        UserList.saveUsers();
        LessonList.saveLessons();
        System.out.println("Logout successful!");
    }

    public void createSong(String title) {
        song = new Song(title, user.getName());
    }

    public void createSong(String title, String author) {
        song = new Song(title, author);
    }

    public String getLoggedInName() {
        if (user != null) {
            return user.getName();
        } else {
            return "Guest";
        }
    }

    // public ArrayList<Song> getCompletedSongs(ArrayList<Song> completedSongs) {
    //     return user.getCompletedSongs();
    // }

    public List<Song> getCompletedSongs(User user) {
        return user.getCompletedSongs();
    }


    /**
     * Retrieves the favorite songs of the current user.
     * 
     * @param favoriteSongs The list to store the favorite songs.
     */
    // public ArrayList<Song> getFavoriteSongs(ArrayList<Song> favoriteSongs) {
    //     return user.getFavoriteSongs();
    // }

    public List<Song> getFavoriteSongs(User user) {
        return user.getFavoriteSongs();
    }


    /**
     * Retrieves the songs owned by the current user.
     * 
     * @param mySongs The list to store the user's songs.
     */
    // public ArrayList<Song> getMySongs(ArrayList<Song> mySongs) {
    //     return user.getMySongs();
    // }

    
    public List<Song> getMySongs(User user) {
        return user.getMySongs();
    }

    /**
     * Adds a new song to the song collection.
     * 
     * @param title       The title of the song.
     * @param artist      The artist of the song.
     * @param runLengthMin The length of the song in minutes.
     * @param runLengthSec The length of the song in seconds.
     * @param lyrics      The lyrics of the song.
     * @param tempo       The tempo of the song.
     * @param measure     The measure of the song.
     */
    public boolean addSong(String title, String artist, int runLengthMin, int runLengthSec,int tempo, ArrayList<Genre> genres, Difficulty difficulty,
    ArrayList<Measure> measures) {
        song = new Song(title, artist, runLengthMin, runLengthSec, tempo, genres, difficulty, measures);
        songList.addSong(song);
        return (songList.getSong(title)!=null);
    }

    /**
     * Edits the title of an existing song.
     * 
     * @param title The new title for the song.
     */
    public void editSongTitle(String title) {
        song.setTitle(title);
    }

    /**
     * Allows the user to review a song with a rating and comment.
     * 
     * @param song      The song being reviewed.
     * @param rating    The rating of the song (out of 5).
     * @param comment   The review comment.
     * @param assignment The assignment associated with the review.
     */
    public void reviewSong(Song song, int rating, String comment, Assignment assignment) {
        song.addReview(new Review(rating, comment, user.getName()));
    }

    /**
     * View the grade for a specific assignment.
     * 
     * @param assignment The assignment whose grade needs to be viewed.
     */
    public void viewGrade(Assignment assignment) {
        assignment.getGrade();
    }

    /**
     * Submit an assignment.
     * 
     * @param assignment The assignment to submit.
     */
    public void turnIn(Assignment assignment) {
        assignment.complete();
    }

    /**
     * Submit a lesson.
     * 
     * @param lesson The lesson to submit.
     */
    public void turnIn(Lesson lesson) {
        lesson.complete();
    }

    /**
     * Adds a play assignment for a student.
     * 
     * @param title          The title of the play assignment.
     * @param teacherComment The comment from the teacher about the assignment.
     * @param dueDate        The due date of the assignment.
     * @param song           The song associated with the assignment.
     * @param tempo          The tempo for the assignment.
     */
    public void addPlayAssignment(String title, String teacherComment, Date dueDate, Song song, int tempo) {
        student.addAssignment(new PlayAssignment(title, teacherComment, dueDate, song, tempo));
    }

    /**
     * Adds a question-based assignment for a student.
     * 
     * @param title          The title of the question assignment.
     * @param teacherComment The comment from the teacher about the assignment.
     * @param dueDate        The due date of the assignment.
     * @param questions      The list of questions for the assignment.
     */
    public void addQuestionAssignment(String title, String teacherComment, Date dueDate, ArrayList<Question> questions) {
        student.addAssignment(new QuestionAssignment(title, teacherComment, dueDate, questions));
    }

    /**
     * Adds a composition assignment for a student.
     * 
     * @param title          The title of the composition assignment.
     * @param teacherComment The comment from the teacher about the assignment.
     * @param dueDate        The due date of the assignment.
     * @param song           The song associated with the assignment.
     * @param tempo          The tempo for the assignment.
     * @param instructions   The instructions for the composition assignment.
     */
    public void addComposeAssignment(String title, String teacherComment, Date dueDate, Song song, int tempo, String instructions) {
        student.addAssignment(new ComposeAssignment(title, teacherComment, dueDate, song, tempo, instructions));
    }

    public void printSheetMusic(String songTitle, String fileName) {
        Song printSong = Songlist.getSong(songTitle);
    }
    
    public Songlist getSonglistInstance() {
        return Songlist.getInstance();
    }

    public UserList getUserListInstance() {
        return UserList.getInstance();
    }

    public void playSong(String title) {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.playSong(title);
    }
    
    public void viewLesson(){
        
    }

    /**
     * Displays the assignments for the current student.
     */
    public void viewAssignments() {
        // Method implementation is missing
    }

    public String getUserName() {
        if (user != null)
            return user.getName();
        return "John Doe";
    }

    /**
 * Plays the given Song using the MusicPlayer.
 *
 * @param song The Song object to play.
 */
public void play(Song song) {
    if (song == null) {
        System.out.println("No song to play.");
        return;
    }

    MusicPlayer musicPlayer = new MusicPlayer();
    musicPlayer.play(song);
}


}