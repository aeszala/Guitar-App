package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import java.util.ArrayList;
import java.util.UUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

// ...

// for each method have 4-5 tests

/**
 * @author aeszala
 */
public class DataLoaderTest {
/*
    @BeforeClass
    public void oneTimeSetup() {

    }

    @AfterClass
    public void oneTimeTearDown() {

    }

    @Before
    public void setup() {
        // runs before each test
    }

    @After
    public void tearDown() {
        // runs after each test
    }
 */
    @Test
    public void testingTest() {
        assertTrue(true);
    }

    @Test
    public void testGetSongsFromUUIDsValid() {
        
        ArrayList<User> users1 = DataLoader.getUsers();
        ArrayList<User> users2 = new ArrayList<User>();
        User user1 = new User(UUID.fromString("6c7a0a9d-8f50-4c15-b84f-acbaa9e3db55"), "ffredrickson", "password1", "email1", "Felecia Fredrickson", new ArrayList<Song>(),
        new ArrayList<Song>(), new ArrayList<Lesson>(), new ArrayList<Song>(), "Question1", "Answer1");
        User user2 = new User(UUID.fromString("2d1bd126-b967-4494-9e1f-ab266f938361"), "FFred", "I-love-dogs342", "ffredrickson@gmail.com", "FFred", new ArrayList<Song>(),
        new ArrayList<Song>(), new ArrayList<Lesson>(), new ArrayList<Song>(), "What was the name of your first dog?", "Fluffy");
        users2.add(user1);
        users2.add(user2);
        assertEquals(users1, users2);
    }

    @Test
    public void testGetSongsFromUUIDsInvalidName() {
        ArrayList<User> users1 = DataLoader.getUsers();
        ArrayList<User> users2 = new ArrayList<User>();
        User user1 = new User(UUID.fromString("6c7a0a9d-8f50-4c15-b84f-acbaa9e3db55"), "username", "password", "email", "name", new ArrayList<Song>(),
        new ArrayList<Song>(), new ArrayList<Lesson>(), new ArrayList<Song>(), "secQue", "secAns");
        User user2 = new User(UUID.fromString("2d1bd126-b967-4494-9e1f-ab266f938361"), "username", "password", "email", "name", new ArrayList<Song>(),
        new ArrayList<Song>(), new ArrayList<Lesson>(), new ArrayList<Song>(), "secQue", "secAns");
        users2.add(user1);
        users2.add(user2);
        assertEquals(users1, users2);
    }
/*
    // GetSongsFromUUIDs test
    @Test
    public void testGetSongsFromUUIDsValidSongsValid() {
        //ArrayList<User> users = DataLoader.getUsers();
        assertTrue(true);
    }

    @Test
    public void testGetSongsFromUUIDsInvalidSongsOnly() {
        assertTrue(true);
    }

    @Test
    public void testGetSongsFromUUIDsSomeValid() {

    }

    @Test
    public void testGetSongsFromUUIDsEmptyList() {

    }

    @Test
    public void testGetSongsFromUUIDsContainsNull() {

    }

    //getLessonsFromUUIDs tests
    @Test
    public void testGetLessonsFromUUIDsValidSongsValid() {

    }

    @Test
    public void testGetLessonsFromUUIDsInvalidSongsOnly() {

    }

    @Test
    public void testGetLessonsFromUUIDsSomeValid() {

    }

    @Test
    public void testGetLessonsFromUUIDsEmptyList() {

    }

    @Test
    public void testGetLessonsFromUUIDsContainsNull() {

    }


    //findSongById tests
    @Test
    void testFindSongByIdValidSong() {

    }

    @Test
    void testFindSongByIdInvalidSong() {

    }

    @Test
    void testFindSongByIdNull() {

    }

    @Test
    void testFindSongById() {

    }


    // findLessonById tests
    @Test
    void testFindLessonById() {

    }

    // createReview tests
    @Test
    void testCreateReviewValidString() {

    }

    @Test
    void testCreateReviewInvalidFormat() {

    }

    @Test
    void testCreateReviewEmptyString() {

    }

    @Test
    void testCreateReviewNullString() {

    }
    @Test
    void testCreateReviewMissingContent() {

    }

    // createMeasure tests
    @Test
    void testCreateMeasureValidString() {

    }

    @Test
    void testCreateMeasureInvalidFormat() {

    }

    @Test
    void testCreateMeasureEmptyString() {

    }

    @Test
    void testCreateMeasureNullString() {

    }
    @Test
    void testCreateMeasureMissingContent() {

    }

    // createAssignment tests
    @Test
    void testCreateAssignmentValidString() {

    }

    @Test
    void testCreateAssignmentInvalidFormat() {

    }

    @Test
    void testCreateAssignmentEmptyString() {

    }

    @Test
    void testCreatsAssignmentNullString() {

    }
    @Test
    void testCreateAssignmentMissingContent() {

    }

    // createDate tests
    @Test
    void testCreateDateValidString() {

    }

    @Test
    void testCreateDateInvalidFormat() {

    }

    @Test
    void testCreateDateEmptyString() {

    }

    @Test
    void testCreatsDateNullString() {

    }
    @Test
    void testCreateDateMissingContent() {

    }
 */
}
