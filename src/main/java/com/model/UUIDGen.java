package com.model;

import java.util.UUID;

/**
 * A simple utility class for generating and printing a random UUID.
 * UUIDs (Universally Unique Identifiers) are typically used for uniquely identifying objects in applications.
 */
public class UUIDGen {

    /**
     * The main method that generates and prints a random UUID to the console.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        // Generate and print a random UUID
        System.out.println(UUID.randomUUID());
    }
}
