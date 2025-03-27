/**
 * @author Liam
 */

 package com.model;

 import org.json.simple.JSONObject;
 
 /**
  * The {@code Sound} class represents an abstract concept of a musical sound.
  * It contains properties such as type, length, and string number. Classes that
  * extend Sound include specific implementations like Note and Chord.
  */
 public abstract class Sound {
 
     /**
      * The type of sound (e.g., "note" or "chord").
      */
     protected String type;
 
     /**
      * The length of the sound in beats.
      */
     protected double length;
 
     /**
      * The string number associated with the sound, typically for guitar notation (1 to 6).
      */
     protected int string;
 
     /**
      * Constructs a Sound with a specified type and length.
      * 
      * @param type   The type of sound, e.g., "note" or "chord".
      * @param length The duration of the sound in beats.
      */
     public Sound(String type, double length) {
         this.type = type;
         this.length = length;
     }
 
     /**
      * Default constructor for creating a Sound without specified properties.
      */
     public Sound() {}
 
     /**
      * Gets the length of the sound.
      * 
      * @return The duration of the sound in beats.
      */
     public double getLength() {
         return length;
     }
 
     /**
      * Gets the string number on which the sound is played.
      * 
      * @return The string number, typically ranging from 1 to 6 for a guitar.
      */
     public int getString() {
         return string;
     } 
 
     /**
      * Sets the length of the sound.
      * 
      * @param length The duration of the sound in beats.
      */
     public void setLength(double length) {
         this.length = length;
     }
 
     /**
      * Plays the sound. This method should be overridden in derived classes
      * to provide specific playback behavior.
      */
     public void play() {
         // Implement sound playback logic here
     }
 
     /**
      * Gets the type of sound.
      * 
      * @return The type of sound (e.g., "note" or "chord").
      */
     public String getType() {
         return type;
     }
 
     /**
      * Sets the type of sound to either "note" or "chord".
      * 
      * @param type The type of sound. Must be "note" or "chord" (case-insensitive).
      */
     public void setType(String type) {
         if (type.equalsIgnoreCase("chord"))
             this.type = "chord";
         else
             this.type = "note";
     }
 
     /**
      * Converts the sound object to a JSON representation using JSONObject.
      * 
      * @return A JSON object representing the sound with its type.
      */
     public JSONObject toJson() {
         JSONObject soundObject = new JSONObject();
         soundObject.put("type", type != null ? type : "UNKNOWN"); // Handle null case
         return soundObject;
     }
 
     /**
      * Returns a string representation of the sound object, including its type.
      * 
      * @return A string representation of the sound.
      */
     @Override
     public String toString() {
         return "Sound{" + "type='" + type + '\'' + '}';
     }
 }
 