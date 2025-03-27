/**
 * @author (name)
 */

package com.model;

import org.json.simple.JSONObject;

/**
 * The {@code Note} class represents a musical note with properties such as type, length, pitch,
 * string, and fret (for string instruments). It provides methods to manipulate the note's attributes, 
 * convert the note to a tab representation, play the note, and convert the note to JSON.
 * 
 * <p>This class is a subclass of {@link Sound} and specializes in notes, defining additional properties 
 * specific to a musical note on a string instrument.
 */
public class Note extends Sound {
    private String type;
    private double length;
    private double pitch;
    private int string;
    private int fret;
    public String soundType = "note";
    
    /**
     * Constructor to create a {@code Note} object with the specified properties.
     * 
     * @param type The type of the note (e.g., "quarter", "eighth").
     * @param length The length of the note (duration).
     * @param pitch The pitch of the note in Hertz.
     * @param string The string number on the instrument (e.g., 1 for the highest string).
     * @param fret The fret number on the instrument.
     * @param soundType The type of sound, which is always set to "note" for this class.
     */
    public Note(String type, double length, double pitch, int string, int fret) {
        super(type, length);
        this.pitch = pitch;
        this.string = string;
        this.fret = fret;
    }

    /**
     * Plays the note by printing details of the note to the console.
     */
    public void play(){
        System.out.println("Playing note: " + type + " on string " + string + " at fret " + fret +
                " with pitch " + pitch + "Hz and length " + length);
    }

    /**
     * Gets the type of the note.
     * 
     * @return The type of the note (e.g., "quarter", "eighth").
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the note.
     * 
     * @param type The type to set for the note (e.g., "quarter", "eighth").
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the length of the note.
     * 
     * @return The length of the note (duration).
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets the length of the note.
     * 
     * @param length The length (duration) to set for the note.
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Gets the pitch of the note in Hertz.
     * 
     * @return The pitch of the note in Hertz.
     */
    public double getPitch() {
        return pitch;
    }

    /**
     * Sets the pitch of the note.
     * 
     * @param pitch The pitch to set for the note in Hertz.
     */
    public void setPitch(Double pitch){
        this.pitch = pitch;
    }

    /**
     * Gets the string number on the instrument for the note.
     * 
     * @return The string number (e.g., 1 for the highest string).
     */
    public int getString() { 
        return string; 
    }

    /**
     * Sets the string number on the instrument for the note.
     * 
     * @param string The string number to set (e.g., 1 for the highest string).
     */
    public void setString(int string) { 
        this.string = string; 
    }

    /**
     * Gets the fret number on the instrument for the note.
     * 
     * @return The fret number.
     */
    public int getFret() { 
        return fret; 
    }

    /**
     * Sets the fret number on the instrument for the note.
     * 
     * @param fret The fret number to set.
     */
    public void setFret(int fret) {
         this.fret = fret; 
    }
    
    /**
     * Gets the type of sound (which is always "note" for this class).
     * 
     * @return The sound type, which is "note".
     */
    public String getSoundType() {
        return soundType;
    }
    
    /**
     * Converts the note to a JSON object.
     * 
     * @return A {@link JSONObject} representing the note's properties.
     */
    @Override
    public JSONObject toJson() {
        JSONObject noteObject = new JSONObject();
        noteObject.put("type", getType());
        noteObject.put("soundType", getSoundType());
        noteObject.put("length", getLength());
        noteObject.put("pitch", getPitch());
        noteObject.put("string", getString());
        noteObject.put("fret", getFret());
        return noteObject;
    }

    /**
     * Returns a string representation of the note object.
     * 
     * @return A string representing the note's attributes.
     */
    @Override
    public String toString() {
        return "Note {" +
                "Type='" + type + '\'' +
                ", Length=" + length +
                ", Pitch=" + pitch +
                ", String=" + string +
                ", Fret=" + fret +
                ", SoundType='" + soundType + '\'' +
                '}';
    }
}
