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
        if (pitch >= 466.16) return 0; // A#4 (High F)
        if (pitch >= 440.00) return 1; // A4 (D)
        if (pitch >= 293.66) return 2; // D
        if (pitch >= 246.94) return 3; // B
        if (pitch >= 196.00) return 4; // G
        if (pitch >= 164.81) return 5; // E
        if (pitch >= 130.81) return 6; // C
        return -1; // Out of range
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
        int position = 0; // Controls horizontal placement
    
        for (Sound sound : measure.getNotes()) {
            if (sound instanceof Note) {
                Note note = (Note) sound;
                System.out.println("Note pitch: " + note.getPitch() + " → Staff index: " + getStaffLineIndex(note.getPitch()));
                System.out.println("Note length: " + note.getLength() + " → Symbol: " + getNoteSymbol(note.getLength()));
                int index = getStaffLineIndex(note.getPitch());
                String noteSymbol = getNoteSymbol(note.getLength());
                int spacing = (int) (note.getLength() * 5);
    
                for (int i = 0; i < staff.length; i++) {
                    if (i == index) {
                        staff[i] += noteSymbol;
                    } else {
                        staff[i] += " ";
                    }
                }
                position += spacing; // Move forward for next note
    
            } else if (sound instanceof Chord) {
                Chord chord = (Chord) sound;
                for (Note note : chord.getNotes()) {
                    int index = getStaffLineIndex(note.getPitch());
                    String noteSymbol = getNoteSymbol(note.getLength());
    
                    staff[index] += noteSymbol; // Stack chord notes on top of each other
                }
                position += 5; // Default spacing for chords
            }
        }
    
        return formatStaff(staff);
    }

    // Formats staff for display
    private static String formatStaff(String[] staff) {
        StringBuilder sheetMusic = new StringBuilder();
        for (String line : staff) {
            sheetMusic.append(line).append("\n");
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
