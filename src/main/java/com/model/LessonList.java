/**
 * @author liamnp
 */

package com.model;

import java.util.ArrayList;

/**
 * The {@code LessonList} class represents a collection of lessons in the application.
 * It provides functionality to retrieve lessons, add new lessons, and search for lessons 
 * based on a keyword. This class follows the Singleton pattern to ensure only one 
 * instance of the lesson list exists throughout the application.
 * 
 * <p> The class includes:
 * <ul>
 *   <li>{@link #lessons} - A list of {@link Lesson} objects.</li>
 *   <li>{@link #getLessons()} - Retrieves the list of lessons.</li>
 *   <li>{@link #addLesson(Lesson)} - Adds a lesson to the list.</li>
 *   <li>{@link #getLesson(String)} - Retrieves lessons that match a search keyword.</li>
 * </ul>
 */
public class LessonList {
    private static LessonList lessonListInstance = null;
    private ArrayList<Lesson> lessons;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the lessons list by loading data from {@link DataLoader}.
     */
    private LessonList(){
        lessons = DataLoader.getLessons();
    }

    /**
     * Gets the single instance of the {@code LessonList} class.
     * If the instance does not exist, it is created.
     * 
     * @return The singleton instance of the {@code LessonList}.
     */
    public static LessonList getInstance(){
        if (lessonListInstance == null){
            lessonListInstance = new LessonList();
        }
        return lessonListInstance;
    }

    /**
     * Gets the list of all lessons.
     * 
     * @return A list of {@link Lesson} objects.
     */
    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    /**
     * Adds a new lesson to the list of lessons.
     * 
     * @param lesson The {@link Lesson} to be added.
     */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    /**
     * Searches for lessons that match the given keyword in their topic.
     * The search is case-insensitive and checks if the topic contains the keyword.
     * 
     * @param keyword The keyword to search for in the lesson topics.
     * @return A list of {@link Lesson} objects that match the keyword.
     */
    public ArrayList<Lesson> getLesson(String keyword) {
        ArrayList<Lesson> result = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getTopic().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(lesson);
            }
        }
        return result;
    }
}
