package com.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        if (pitch >= 329.63) return 0; // F
        if (pitch >= 293.66) return 1; // D
        if (pitch >= 246.94) return 2; // B
        if (pitch >= 196.00) return 3; // G
        if (pitch >= 164.81) return 4; // E
        if (pitch >= 130.81) return 5; // C
        return -1; // Out of range
    }

    // Assigns note symbols based on duration
    private static String getNoteSymbol(double length) {
        if (length >= 1) return "_"; // Whole note
        if (length >= 0.5) return "○"; // Half note
        if (length >= 0.25) return "●"; // Quarter note
        return "♪"; // Eighth note
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
