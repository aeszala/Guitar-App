package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Song {
  private UUID id;
  private String title;
  private String artist;
  private int runLengthMin;
  private int runLengthSec;
  ArrayList<Sound> notes;
  private int Tempo;
  private double rating;
  ArrayList<Review> reviews;
  private boolean metronomeOn;
  ArrayList<Genre> genres;
  private Difficulty difficulty;
  ArrayList<Measure> measures;
  private boolean completed;

  public void playSong(){

  }

  public void changeTempo(int Tempo){

  }

  public void displayTab(){

  }

  public void displaySheet(){

  }
  
}
