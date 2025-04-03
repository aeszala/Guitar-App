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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

// for each method have 4-5 tests

/**
 * @author aeszala
 */
public class DataLoaderTest {

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


    // GetSongsFromUUIDs test
    @Test
    public void testGetSongsFromUUIDsValidSongsValid() {
        assertTrue(true);
    }

    @Test
    public void testGetSongsFromUUIDsInvalidSongsOnly() {

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

}
