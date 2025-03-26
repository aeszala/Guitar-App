/**
 * @author (name)
 */

package com.model;

import org.json.simple.JSONObject;

/**
 * The {@code Review} class represents a review with a rating, author, and comment.
 * It can be used for reviewing various items (such as songs, albums, etc.).
 * This class also supports serialization to JSON and provides a string representation.
 */
public class Review {
    private Double rating;  // Rating for the review, between 0 and 5
    private String author;  // Author of the review
    private String comment; // Comment provided in the review

    /**
     * Constructs a new Review with the given rating, comment, and author.
     * 
     * @param rating The rating of the review (typically between 0 and 5).
     * @param comment The comment of the review.
     * @param author The name of the author who wrote the review.
     */
    public Review(double rating, String comment, String author) {
        this.rating = rating;
        this.comment = comment;
        this.author = author;
    }

    /**
     * Gets the rating of the review.
     * 
     * @return The rating (double).
     */
    public double getRating() {
        return rating;
    }

    /**
     * Gets the author of the review.
     * 
     * @return The author's name.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the comment in the review.
     * 
     * @return The comment text.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the rating of the review.
     * 
     * @param rating The rating to set (double).
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Sets the author of the review.
     * 
     * @param author The author's name to set.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the comment of the review.
     * 
     * @param comment The comment to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Converts the review to a JSON object.
     * 
     * @return A {@code JSONObject} representation of the review.
     */
    public JSONObject toJson() {
        JSONObject reviewObject = new JSONObject();
        reviewObject.put("rating", rating);
        reviewObject.put("author", author);
        reviewObject.put("comment", comment);
        return reviewObject;
    }

    /**
     * Provides a string representation of the review.
     * 
     * @return A string representing the review.
     */
    @Override
    public String toString() {
        return "Review{" +
               "rating=" + rating +
               ", author='" + author + '\'' +  
               ", comment='" + comment + '\'' +
               '}';
    }
}
