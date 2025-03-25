/**
 * @author (name)
 */

package com.model;

import java.util.ArrayList;

/**
 * The {@code NormalUser} class represents a normal user in the system with points, streaks, rankings, 
 * and a list of published songs. It provides methods to manage user attributes, such as points, streaks, 
 * and ranking, as well as to publish songs.
 * 
 * <p>The class includes the following attributes:
 * <ul>
 *   <li>{@link #points} - The points accumulated by the user.</li>
 *   <li>{@link #streak} - The current streak of the user, such as consecutive days of activity.</li>
 *   <li>{@link #ranking} - The user's ranking in the system.</li>
 *   <li>{@link #publishedSongs} - A list of songs published by the user.</li>
 * </ul>
 * 
 * <p>The class also provides methods to manage the user's profile:
 * <ul>
 *   <li>{@link #getPoints()} - Get the user's current points.</li>
 *   <li>{@link #setPoints(int)} - Set the user's points.</li>
 *   <li>{@link #getStreak()} - Get the user's current streak.</li>
 *   <li>{@link #setStreak(int)} - Set the user's streak.</li>
 *   <li>{@link #getRanking()} - Get the user's current ranking.</li>
 *   <li>{@link #setRanking(int)} - Set the user's ranking.</li>
 *   <li>{@link #getPublishedSongs()} - Get the list of songs published by the user.</li>
 *   <li>{@link #publishSong(Song)} - Publish a new song for the user.</li>
 * </ul>
 */
public class NormalUser {
    private int points;
    private int streak;
    private int ranking;
    private ArrayList<Song> publishedSongs;

    /**
     * Default constructor for the NormalUser class.
     * 
     * Initializes a NormalUser with:
     * <ul>
     *   <li>0 points</li>
     *   <li>0 streak</li>
     *   <li>0 ranking</li>
     *   <li>an empty list of published songs</li>
     * </ul>
     */
    public NormalUser(){
        this.points = 0;
        this.streak = 0;
        this.ranking = 0;
        this.publishedSongs = new ArrayList<>();
    }

    /**
     * Gets the points of the user.
     * 
     * @return The current points of the user.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points for the user.
     * 
     * @param points The points to set for the user.
     */
    public void setPoints(int points){
        this.points = points;
    }

    /**
     * Gets the current streak of the user.
     * 
     * @return The current streak of the user.
     */
    public int getStreak() {
        return streak;
    }

    /**
     * Sets the streak for the user.
     * 
     * @param streak The streak to set for the user.
     */
    public void setStreak(int streak){
        this.streak = streak;
    }

    /**
     * Gets the ranking of the user.
     * 
     * @return The current ranking of the user.
     */
    public int getRanking(){
        return ranking;
    }

    /**
     * Sets the ranking for the user.
     * 
     * @param ranking The ranking to set for the user.
     */
    public void setRanking(int ranking){
        this.ranking = ranking;
    }

    /**
     * Gets the list of songs published by the user.
     * 
     * @return A list of {@link Song} objects that have been published by the user.
     */
    public ArrayList<Song> getPublishedSongs(){
        return publishedSongs;
    }

    /**
     * Publishes a new song for the user.
     * 
     * If the song is valid (not null), the song is added to the list of published songs and a 
     * success message is printed. If the song is invalid, an error message is displayed.
     * 
     * @param song The song to be published.
     */
    public void publishSong(Song song) {
        if (song != null) {
            publishedSongs.add(song);
            System.out.println("Song '" + song.getTitle() + "' has been published.");
        } else {
            System.out.println("Invalid song. Cannot publish.");
        }
    }
}
