/**
 * @author Tre
 */

package com.model;

/**
 * The {@code Difficulty} enum represents the different difficulty levels
 * that can be assigned to songs, lessons, or assignments in the application.
 * 
 * <p> The available difficulty levels are:
 * <ul>
 *   <li>{@link #BEGINNER} - Suitable for users who are new to the subject.</li>
 *   <li>{@link #INTERMEDIATE} - Designed for users with some experience.</li>
 *   <li>{@link #ADVANCED} - Intended for users with significant experience and skill.</li>
 * </ul>
 */
public enum Difficulty {

    /** Represents a beginner-level difficulty. */
    BEGINNER,

    /** Represents an intermediate-level difficulty. */
    INTERMEDIATE,

    /** Represents an advanced-level difficulty. */
    ADVANCED;
}
