/**
 * @author IDK
 */

package com.model;

import java.util.ArrayList;

/**
 * The {@code Database} class provides static methods to retrieve lists of users, songs, and lessons.
 * This class simulates a simple in-memory database using ArrayLists.
 */
public class Database {

    /**
     * Retrieves a list of users from the database.
     * 
     * @return An {@code ArrayList} of {@code User} objects.
     */
    public static ArrayList<User> getUsers() {
        return new ArrayList<User>();
    }

    /**
     * Retrieves a list of songs from the database.
     * 
     * @return An {@code ArrayList} of {@code Song} objects.
     */
    public static ArrayList<Song> getSongs() {
        return new ArrayList<Song>();
    }

    /**
     * Retrieves a list of lessons from the database.
     * 
     * @return An {@code ArrayList} of {@code Lesson} objects.
     */
    public static ArrayList<Lesson> getLessons() {
        return new ArrayList<Lesson>();
    }
}
