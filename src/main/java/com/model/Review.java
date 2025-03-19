package com.model;

public class Review {
  private Double rating;
  private String author;
  private String comment;

  public Review(double rating, String comment, String author){
    this.rating = rating;
    this.comment = comment;
    this.author = author;
  }

  public double getRating(){
    return rating;
  }

  public String getAuthor(){
    return author;
  }

  public String getComment(){
    return comment;
  }

  public void setRating(double rating){
    this.rating = rating;
  }

  public void setAuthor(String author){
    this.author = author;
  }

  public void setComment(String comment){
    this.comment = comment;
  }

  public String toString() {
    return "Review{" +
            "rating=" + rating +
            ", author=" + author +  
            ", comment='" + comment + '\'' +
            '}';
  }
}
