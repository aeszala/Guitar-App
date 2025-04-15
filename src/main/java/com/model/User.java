/**
 * @author Liam
 */

package com.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code User} class represents a user in the system.
 * It includes properties such as id, username, password, email, name, and
 * various collections
 * representing songs and lessons the user has interacted with (favorite songs,
 * completed songs, etc.).
 */
public class User {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String name;
    ArrayList<Song> favoriteSongs;
    ArrayList<Song> completedSongs;
    ArrayList<Lesson> completedLessons;
    ArrayList<Song> mySongs;
    public String securityQuestion;
    public String securityAnswer;
    public boolean login;
    private string profilePicture;

    /**
     * Constructor for creating an existing user with all attributes initialized.
     * 
     * @param id               The unique identifier for the user.
     * @param username         The username of the user.
     * @param password         The password of the user.
     * @param email            The email of the user.
     * @param name             The full name of the user.
     * @param favoriteSongs    List of favorite songs of the user.
     * @param completedSongs   List of completed songs by the user.
     * @param completedLessons List of completed lessons by the user.
     * @param mySongs          List of songs created or owned by the user.
     * @param securityQuestion The user's security question for account recovery.
     * @param securityAnswer   The user's answer to the security question.
     */
    public User(UUID id, String username, String password, String email, String name,
            ArrayList<Song> favoriteSongs, ArrayList<Song> completedSongs,
            ArrayList<Lesson> completedLessons, ArrayList<Song> mySongs,
            String securityQuestion, String securityAnswer) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.favoriteSongs = favoriteSongs;
        this.completedSongs = completedSongs;
        this.completedLessons = completedLessons;
        this.mySongs = mySongs;
        this.profilePicture = "/images/default_profile.png";
    }

    /**
     * Constructor for creating a new user with basic attributes initialized.
     * This constructor generates a random ID for the user.
     * 
     * @param username         The username of the user.
     * @param password         The password of the user.
     * @param email            The email of the user.
     * @param name             The full name of the user.
     * @param securityQuestion The user's security question for account recovery.
     * @param securityAnswer   The user's answer to the security question.
     */
    public User(String username, String password, String email, String name, String securityQuestion,
            String securityAnswer) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.favoriteSongs = new ArrayList<>();
        this.completedSongs = new ArrayList<>();
        this.completedLessons = new ArrayList<>();
        this.mySongs = new ArrayList<>();
    }

    /**
     * Converts the user object to a JSON object for serialization.
     * 
     * @return A JSON representation of the user with attributes like id, username,
     *         password, etc.
     */
    public JSONObject toJson() {
        JSONObject userObject = new JSONObject();
        userObject.put("id", this.id.toString());
        userObject.put("username", this.username);
        userObject.put("password", this.password);
        userObject.put("email", this.email);
        userObject.put("name", this.name);
        userObject.put("securityQuestion", this.securityQuestion);
        userObject.put("securityAnswer", this.securityAnswer);

        // Serialize only the Song IDs instead of full objects
        JSONArray favoriteSongsArray = new JSONArray();
        for (Song song : this.favoriteSongs) {
            favoriteSongsArray.add(song.getId().toString());
        }
        userObject.put("favoriteSongs", favoriteSongsArray);

        JSONArray completedSongsArray = new JSONArray();
        for (Song song : this.completedSongs) {
            completedSongsArray.add(song.getId().toString());
        }
        userObject.put("completedSongs", completedSongsArray);

        JSONArray mySongsArray = new JSONArray();
        for (Song song : this.mySongs) {
            mySongsArray.add(song.getId().toString());
        }
        userObject.put("mySongs", mySongsArray);

        JSONArray completedLessonsArray = new JSONArray();
        for (Lesson lesson : this.completedLessons) {
            completedLessonsArray.add(lesson.getId().toString());
        }
        userObject.put("completedLessons", completedLessonsArray);

        return userObject;
    }

    /**
     * Returns the login status of the user.
     * 
     * @return The login status (true or false).
     */
    public boolean login() {
        return login;
    }

    /**
     * Logs the user out and prints a logout message.
     */
    public void logOut() {
        System.out.println("User " + username + " has logged out.");
    }

    /**
     * Adds a song to the user's list of favorite songs if not already present.
     * 
     * @param song The song to add to the favorites list.
     */
    public void favoriteSong(Song Song) {
        if (!favoriteSongs.contains(Song)) {
            favoriteSongs.add(Song);
            System.out.println(Song.getTitle() + " added to favorites.");
        }
    }

    /**
     * Marks a song as completed if it hasn't been completed already.
     * 
     * @param song The song to mark as completed.
     */
    public void completeSong(Song song) {
        if (!completedSongs.contains(song)) {
            completedSongs.add(song);
            System.out.println(song.getTitle() + " marked as completed.");
        }
    }

    /**
     * Getters for various user attributes (id, username, password, etc.).
     */
    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getProfilePicturePath() {
        return profilePicture;
    }
}

    public ArrayList<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    public ArrayList<Song> getCompletedSongs() {
        return completedSongs;
    }

    public ArrayList<Lesson> getCompletedLessons() {
        return completedLessons;
    }

    public ArrayList<Song> getMySongs() {
        return mySongs;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    /**
     * Setters for various user attributes (username, password, email, etc.).
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public void setMyfavoritesongs(ArrayList<Song> favoriteSongs) {
        this.favoriteSongs = favoriteSongs;
    }

    /**
     * Returns a string representation of the user object, showing key attributes.
     * 
     * @return A string describing the user with their details.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", favoriteSongs=" + formatSongList(favoriteSongs) +
                ", completedSongs=" + formatSongList(completedSongs) +
                ", completedLessons=" + formatLessonList(completedLessons) +
                ", mySongs=" + formatSongList(mySongs) +
                '}';
    }

    /**
     * Helper method to format a list of Songs as a string.
     * 
     * @param songs The list of songs to format.
     * @return A formatted string representation of the list of songs.
     */
    private String formatSongList(ArrayList<Song> songs) {
        if (songs == null || songs.isEmpty())
            return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (Song song : songs) {
            sb.append(song.getTitle()).append(", ");
        }
        return sb.substring(0, sb.length() - 2) + "]"; // Remove last comma & space
    }

    /**
     * Helper method to format a list of Lessons as a string.
     * 
     * @param lessons The list of lessons to format.
     * @return A formatted string representation of the list of lessons.
     */
    private String formatLessonList(ArrayList<Lesson> lessons) {
        if (lessons == null || lessons.isEmpty())
            return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (Lesson lesson : lessons) {
            sb.append(lesson.getTitle()).append(", ");
        }
        return sb.substring(0, sb.length() - 2) + "]"; // Remove last comma & space
    }

    /**
     * Method to log in with a username and password (not implemented).
     * 
     * @param username2 The username to log in with.
     * @param password2 The password to log in with.
     * @return Throws an UnsupportedOperationException as it is not yet implemented.
     */
    public boolean login(String username2, String password2) {
        return this.username.equals(username2) && this.password.equals(password2);
    }

    /**
     * Checks if the provided username and password match a user in the UserList.
     * 
     * @param usernameString The username to check.
     * @param passwordString The password to check.
     * @return True if the username and password match, false otherwise.
     */
    public boolean isMatch(String usernameString, String passwordString) {
        UserList.getInstance();
        User user = UserList.getUser(usernameString);
        if (user != null && user.getPassword().equals(passwordString))
            return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(name, user.name) &&
                Objects.equals(securityQuestion, user.securityQuestion) &&
                Objects.equals(securityAnswer, user.securityAnswer) &&
                Objects.equals(favoriteSongs, user.favoriteSongs) &&
                Objects.equals(completedSongs, user.completedSongs) &&
                Objects.equals(completedLessons, user.completedLessons) &&
                Objects.equals(mySongs, user.mySongs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, name, securityQuestion, securityAnswer,
                favoriteSongs, completedSongs, completedLessons, mySongs);
    }

}
