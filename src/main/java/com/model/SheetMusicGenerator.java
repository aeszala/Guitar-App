package com.model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SheetMusicGenerator {
    private static final int STAFF_WIDTH = 40;
    private static final String[] STAFF_LINES = {
        "F |", "D |", "B |", "G |", "E |", "C |"
    };

    // Converts a single note into ASCII sheet music format
    public static String convertNotesToSheet(ArrayList<Note> notes) {
        String[] staff = STAFF_LINES.clone(); // Clone to prevent modifying original

        for (Note note : notes) {
            int index = getStaffLineIndex(note.getPitch());
            String noteSymbol = getNoteSymbol(note.getLength());
            int spacing = (int) (note.getLength() * 5); // Adjust spacing dynamically

            for (int i = 0; i < staff.length; i++) {
                if (i == index) {
                    staff[i] += noteSymbol + " ".repeat(spacing);
                } else {
                    staff[i] += " ".repeat(spacing + 1);
                }
            }
        }
        return formatStaff(staff);
    }

    // Converts a chord into ASCII sheet music format
    public static String convertChordToSheet(Chord chord) {
        String[] staff = STAFF_LINES.clone();

        for (Note note : chord.getNotes()) {
            int index = getStaffLineIndex(note.getPitch());
            String noteSymbol = getNoteSymbol(note.getLength());
            staff[index] += noteSymbol; // Stack notes in same column
        }

        // Add spacing for alignment
        for (int i = 0; i < staff.length; i++) {
            staff[i] += "   ";
        }
        return formatStaff(staff);
    }

    // Maps pitch to correct staff line
    private static int getStaffLineIndex(double pitch) {
        if (pitch >= 466.16) return 0; // F
        if (pitch >= 293.66) return 1; // D
        if (pitch >= 246.94) return 2; // B
        if (pitch >= 196.00) return 3; // G
        if (pitch >= 164.81) return 4; // E
        if (pitch >= 130.81) return 5; // C
    
        return 5; // Default to lowest staff line (C) 
    }
    

    // Assigns note symbols based on duration
    private static String getNoteSymbol(double length) {
        if (length >= 1.0) return "_";  // Whole note
        if (length >= 0.5) return "○";  // Half note
        if (length >= 0.25) return "●"; // Quarter note
        if (length >= 0.125) return "♪"; // Eighth note
        return "X";  // Default for unexpected cases
    }
    
    public static String convertMeasureToSheet(Measure measure) {
        String[] staff = STAFF_LINES.clone();
    
        for (Sound sound : measure.getNotes()) {
            if (sound instanceof Note) {
                Note note = (Note) sound;
                placeNoteOnStaff(note, staff);
            } else if (sound instanceof Chord) {
                Chord chord = (Chord) sound;
                for (Note note : chord.getNotes()) {
                    placeNoteOnStaff(note, staff); // Stack chord notes vertically
                }
            }
        }
    
        return formatStaff(staff);
    }
    
    private static void placeNoteOnStaff(Note note, String[] staff) {
        int index = getStaffLineIndex(note.getPitch()); // Get correct staff line
        String noteSymbol = getNoteSymbol(note.getLength()); // Get note symbol
        System.out.println("Placing note: " + note.getPitch() + " at index " + index);
        if (index != -1) {
            // Ensure each note is added in a column, not side-by-side
            staff[index] = staff[index] + " " + noteSymbol;
        } else {
            System.out.println("Warning: Note pitch " + note.getPitch() + " is out of range!");
        }
    }
    

    private static String formatStaff(String[] staff) {
        StringBuilder sheetMusic = new StringBuilder();
        
        for (String line : staff) {
            sheetMusic.append(String.format("%-20s", line)).append("\n"); // Align spacing
        }
        
        return sheetMusic.toString();
    }
    

    // Writes sheet music to a .txt file
    public static void writeSheetToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
