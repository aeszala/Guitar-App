package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import java.util.ArrayList;
import java.util.UUID;

// for each method have 4-5 tests

/**
 * @author aeszala
 */
public class MusicAppTest {
    private MusicAppFACADE app;
    private ArrayList<User> validUserList = new ArrayList<>();
    private ArrayList<Song> validSongList = new ArrayList<>();

    @Before
    public void setupUser() {
        app = new MusicAppFACADE();
        validUserList.add(new User("Tom", "abc123", "Tom@email.com", "Tom Smalls", "What's your favorite dog breed?", "Golden doodle"));
        validUserList.add(new User("AbbeyS", "Meow", "Abbey@email.com", "Abbey Szala", "What's your favorite cat?", "My cat"));
        DataWriter.saveUsers(validUserList);
        validSongList.add(new Song("Requiem", "Mozart"));
        validSongList.add(new Song("alison", "slowdive"));
        DataWriter.saveSongs(validSongList);
    }

    @After
    public void tearDown() {
        DataWriter.saveUsers(new ArrayList<User>());
        DataWriter.saveSongs(new ArrayList<Song>());
    }

    // login tests
    @Test
    public void testValidLogin() {
        assertTrue(app.login("Tom", "abc123"));
    }

    @Test
    public void testInvalidUsername() {
        assertFalse(app.login("Tommy", "abc123"));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(app.login("AbbeyS", "Pizza"));
    }

    @Test
    public void testPasswordWrongCapitalization() {
        assertFalse(app.login("AbbeyS", "MEOW"));
    }

    @Test
    public void testSendNullArguments() {
        assertFalse(app.login(null, null));
    }

    // createAccount tests
    @Test
    public void testCreateValidAccount() {
        assertTrue(app.createAccount("Obi", "ObiWan", "Thats-my-cats-name", "obi@email.com", "meow?", "meow"));
    }

    @Test
    public void testCreateAccountInvalidEmail() {
        assertFalse(app.createAccount("Obi", "ObiWan", "Thats-my-cats-name", "obimail", "meow?", "meow"));
    }

    @Test
    public void testCreateAccountExistingUsername() {
        assertFalse(app.createAccount("Obi", "AbbeyS", "Thats-my-cats-name", "obimail", "meow?", "meow"));
    }

    @Test
    public void testCreateAccountWithNullArguments() {
        assertFalse(app.createAccount(null, null, null, null, null, null));
    }

    @Test
    public void testCreateAccountWithEmptyStringArguments() {
        assertFalse(app.createAccount("", "", "", "", "", ""));
    }

    // addSong tests
    @Test
    public void testAddValidSong() {
        assertTrue(app.addSong("Another Space Song", "Failure", 3, 28, 130, new ArrayList<>(), Difficulty.INTERMEDIATE, new ArrayList<>()));
    }

    @Test
    public void testAddInvalidTimeSong() {
        assertFalse(app.addSong("Another Space Song", "Failure", -3, 80, 130, new ArrayList<>(), Difficulty.INTERMEDIATE, new ArrayList<>()));
    }

    // saveMeasure Tests
    @Test
    public void testSaveMeasureOneValidNote() {
        app.createSong("title", "author");
        app.createMeasure();
        app.addNote("C5", 1.0, 523.25, 1, 3);
        assertTrue(app.saveMeasure());
    }

    @Test
    public void testSaveMeasureFiveValidNotes() {
        app.createSong("title", "author");
        app.createMeasure();
        app.addNote("C5", 1.0, 523.25, 1, 3);
        app.addNote("C5", 2.0, 523.25, 2, 3);
        app.addNote("C5", 3.0, 523.25, 3, 3);
        app.addNote("C5", 4.0, 523.25, 4, 3);
        app.addNote("C5", 5.0, 523.25, 5, 3);
        assertTrue(app.saveMeasure());
    }

    @Test
    public void testSaveMeasureOneInvalidNote() {
        app.createSong("title", "author");
        app.createMeasure();
        app.addNote("C5", -1.0, -523.25, 1000, 3);  // invalid
        app.addNote("D5", 0.5, 587.33, 2, 5);   // valid
        assertFalse(app.saveMeasure());
    }

    @Test
    public void testSaveMeasureAllInvalidNotes() {
        app.createSong("title", "author");
        app.createMeasure();
        app.addNote("C5", -1.0, -523.25, 1000, 3);
        app.addNote("D5", -0.5, 587.33, -2, 5);
        assertFalse(app.saveMeasure());
    }
}
