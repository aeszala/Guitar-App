package com.model;

public class Review {
  private Double rating;
  private User author;
  private String comment;

  public Review(double rating, String comment, User author){
    this.rating = rating;
    this.comment = comment;
    this.author = author;
  }

  public double getRating(){
    return rating;
  }

  public User getAuthor(){
    return author;
  }

  public String getComment(){
    return comment;
  }

  public void setRating(double rating){
    this.rating = rating;
  }

  public void setAuthor(User author){
    this.author = author;
  }

  public void setComment(String comment){
    this.comment = comment;
  }

  public String toString() {
    return "Review{" +
            "rating=" + rating +
            ", author=" + author.getName() +  
            ", comment='" + comment + '\'' +
            '}';
  }
}
