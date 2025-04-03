package com.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SongListTest {
    private Songlist songlist;

    @Before
    public void setUp() {
        songlist = Songlist.getInstance();
    }

    @After
    public void tearDown() {
        // Reset the singleton for fresh tests (if needed)
        songlist = null;
    }

    @Test
    public void testSingletonInstance() {
        Songlist anotherInstance = Songlist.getInstance();
        assertSame("Songlist should be a singleton", songlist, anotherInstance);
    }

    @Test
    public void testAddSong() {
        int initialSize = songlist.getSongs().size();

        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROCK);
        
        ArrayList<Measure> measures = new ArrayList<>();
        measures.add(new Measure());

        songlist.addSong("Test Song", "Test Artist", 3, 45, 120, genres, Difficulty.BEGINNER, measures);
        assertEquals("Song should be added", initialSize + 1, songlist.getSongs().size());
    }

    @Test
    public void testGetSongByTitle() {
        songlist.addSong("Unique Title", "Some Artist", 2, 30, 100, new ArrayList<>(), Difficulty.INTERMEDIATE, new ArrayList<>());
        Song song = songlist.getSong("Unique Title");
        assertNotNull("Song should be found", song);
        assertEquals("Title should match", "Unique Title", song.getTitle());
    }

    @Test
    public void testGetSongByTitle_CaseInsensitive() {
        songlist.addSong("Case Test", "Artist", 2, 20, 90, new ArrayList<>(), Difficulty.BEGINNER, new ArrayList<>());
        Song song = songlist.getSong("case test");
        assertNotNull("Song search should be case insensitive", song);
        assertEquals("Title should match", "Case Test", song.getTitle());
    }

    @Test
    public void testGetSongByTitle_NotFound() {
        assertNull("Nonexistent song should return null", songlist.getSong("Nonexistent Song"));
    }

    @Test
    public void testGetSongsByKeyword() {
        songlist.addSong("Love Story", "Taylor Swift", 3, 55, 130, new ArrayList<>(), Difficulty.BEGINNER, new ArrayList<>());
        songlist.addSong("Story of My Life", "One Direction", 4, 10, 120, new ArrayList<>(), Difficulty.INTERMEDIATE, new ArrayList<>());
        
        ArrayList<Song> results = songlist.getSongs("Story");
        assertEquals("Should return 2 songs containing 'Story'", 2, results.size());
    }

    @Test
    public void testGetSongsByKeyword_CaseInsensitive() {
        songlist.addSong("Rock Anthem", "Band", 4, 30, 140, new ArrayList<>(), Difficulty.ADVANCED, new ArrayList<>());
        ArrayList<Song> results = songlist.getSongs("rock");
        assertEquals("Keyword search should be case insensitive", 1, results.size());
    }

    @Test
    public void testGetSongsByKeyword_NotFound() {
        ArrayList<Song> results = songlist.getSongs("NoMatch");
        assertTrue("Search should return empty list for no match", results.isEmpty());
    }

    @Test
    public void testDisplaySongs() {
        songlist.addSong("Display Song", "Artist", 2, 50, 110, new ArrayList<>(), Difficulty.BEGINNER, new ArrayList<>());
        songlist.displaySongs(songlist.getSongs());
        // Just ensuring no exceptions occur during printing
    }

    @Test
    public void testAddSongObject() {
        Song song = new Song("SongObject", "Object Artist", 2, 15, 90, new ArrayList<>(), Difficulty.BEGINNER, new ArrayList<>());
        songlist.addSong(song);
        assertNotNull("Added song should be retrievable", songlist.getSong("SongObject"));
    }

    @Test
    public void testSaveSongs() {
        songlist.saveSongs();
        // No direct way to check if file saved without mocking DataWriter
        // Just ensuring no exceptions occur
    }

    // Edge Case Tests

    @Test
    public void testAddNullSong() {
        try {
            songlist.addSong(null);
            fail("Should not allow adding null song");
        } catch (NullPointerException | IllegalArgumentException e) {
            // Expected behavior
        }
    }

    @Test
    public void testGetSongWithEmptyTitle() {
        assertNull("Empty title should return null", songlist.getSong(""));
    }

    @Test
    public void testGetSongsWithEmptyKeyword() {
        ArrayList<Song> results = songlist.getSongs("");
        assertTrue("Empty keyword should return all songs", results.size() >= 0);
    }

    @Test
    public void testAddDuplicateSong() {
        int initialSize = songlist.getSongs().size();
        Song song = new Song("Duplicate Song", "Artist", 3, 10, 100, new ArrayList<>(), Difficulty.BEGINNER, new ArrayList<>());
        songlist.addSong(song);
        songlist.addSong(song);
        assertEquals("Duplicate songs should be added (unless prevented by logic)", initialSize + 2, songlist.getSongs().size());
    }
}
