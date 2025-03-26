package com.model;

import java.util.*;

public class GuitarNoteMapper {
    // Standard guitar tuning (EADGBE)
    private static final String[] STANDARD_TUNING = {"E", "A", "D", "G", "B", "E"};
    private static final int FRETS = 12; // First 12 frets (one octave)
    
    // Note frequencies for octave 4
    private static final Map<String, Double> noteFrequencies = new HashMap<>();
    static {
        noteFrequencies.put("C", 261.63);
        noteFrequencies.put("C#", 277.18);
        noteFrequencies.put("D", 293.66);
        noteFrequencies.put("D#", 311.13);
        noteFrequencies.put("E", 329.63);
        noteFrequencies.put("F", 349.23);
        noteFrequencies.put("F#", 369.99);
        noteFrequencies.put("G", 392.0);
        noteFrequencies.put("G#", 415.3);
        noteFrequencies.put("A", 440.0);
        noteFrequencies.put("A#", 466.16);
        noteFrequencies.put("B", 493.88);
    }
    
    /**
     * Gets all possible string/fret positions for a given note
     * @param note The musical note (e.g., "A", "C#")
     * @return List of string/fret positions where this note appears
     */
    public static List<GuitarPosition> getPositions(String note) {
        List<GuitarPosition> positions = new ArrayList<>();
        String targetNote = note.toUpperCase();
        
        for (int string = 0; string < STANDARD_TUNING.length; string++) {
            String openNote = STANDARD_TUNING[string];
            int semitones = getSemitonesBetween(openNote, targetNote);
            
            if (semitones >= 0 && semitones <= FRETS) {
                positions.add(new GuitarPosition(string + 1, semitones));
            }
        }
        
        return positions;
    }
    
    /**
     * Gets the string number (1-6) for the first position of a note
     * @param note The musical note
     * @return String number (1-6) or -1 if note not found
     */
    public static int getString(String note) {
        GuitarPosition pos = getFirstPosition(note);
        return pos != null ? pos.getString() : -1;
    }
    
    /**
     * Gets the fret number (0-12) for the first position of a note
     * @param note The musical note
     * @return Fret number (0-12) or -1 if note not found
     */
    public static int getFret(String note) {
        GuitarPosition pos = getFirstPosition(note);
        return pos != null ? pos.getFret() : -1;
    }
    
    /**
     * Gets the first (most common) position for a note
     * @param note The musical note
     * @return The most accessible position for this note
     */
    public static GuitarPosition getFirstPosition(String note) {
        List<GuitarPosition> positions = getPositions(note);
        return positions.isEmpty() ? null : positions.get(0);
    }
    
    // Helper method to calculate semitones between two notes
    private static int getSemitonesBetween(String startNote, String endNote) {
        List<String> noteOrder = Arrays.asList(
            "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"
        );
        
        int startIdx = noteOrder.indexOf(startNote);
        int endIdx = noteOrder.indexOf(endNote);
        
        if (startIdx == -1 || endIdx == -1) return -1;
        
        return endIdx >= startIdx ? endIdx - startIdx : (12 - startIdx) + endIdx;
    }
    
    /**
     * Data class to represent string/fret positions with getters
     */
    public static class GuitarPosition {
        private final int string; // 1-6 (1=E high, 6=E low)
        private final int fret;   // 0-12 (0=open string)
        
        public GuitarPosition(int string, int fret) {
            this.string = string;
            this.fret = fret;
        }
        
        public int getString() {
            return string;
        }
        
        public int getFret() {
            return fret;
        }
        
        @Override
        public String toString() {
            return "String " + string + ", Fret " + fret;
        }
    }
    
    // Original frequency method
    public static Double getFrequency(String note) {
        return noteFrequencies.get(note.toUpperCase());
    }
}