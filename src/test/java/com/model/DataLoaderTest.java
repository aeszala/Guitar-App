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
public class DataLoaderTest {
    private ArrayList<User> userList;
    private ArrayList<User> userListTest;
    private ArrayList<Song> songList;
    private ArrayList<Song> songListTest;
    private ArrayList<Lesson> lessonList;
    private ArrayList<Lesson> lessonListTest;

    @Before
    public void setupUser() {
        userList = new ArrayList<>();
        songList = new ArrayList<>();
        lessonList = new ArrayList<>();
        DataWriter.saveUsers(userList);
        DataWriter.saveSongs(songList);
        DataWriter.saveLessons(lessonList);
    }

    @After
    public void tearDown() {
        userList.clear();
        songList.clear();
        lessonList.clear();
        DataWriter.saveUsers(userList);
        DataWriter.saveSongs(songList);
        DataWriter.saveLessons(lessonList);
    }

    @Test
    public void testingTest() {
        assertTrue(true);
    }

    // Tests for getUsers method
    @Test
    public void testLoadingZeroUsers() {
        userListTest = DataLoader.getUsers();
        assertEquals(userList, userListTest);
    }

    @Test
    public void testLoadingOneUser() {
        userList.add(new User(UUID.fromString("6149dd93-1fe0-47e1-ae4a-fbce60258bbf"), "asmith", "password1", 
        "asmith@example.com", "Amy Smith", new ArrayList<Song>(), new ArrayList<Song>(), new ArrayList<Lesson>(), 
        new ArrayList<Song>(), "Question1", "Answer1"));
        DataWriter.saveUsers(userList);
        userListTest = DataLoader.getUsers();
        assertEquals(userList, userListTest);
    }

    @Test
    public void testLoadingFiveUsers() {
        userList.add(new User(UUID.fromString("6149dd93-1fe0-47e1-ae4a-fbce60258bbf"), "asmith", "password1", 
        "asmith@example.com", "Amy Smith", new ArrayList<Song>(), new ArrayList<Song>(), new ArrayList<Lesson>(), 
        new ArrayList<Song>(), "Question1", "Answer1"));
        userList.add(new User(UUID.fromString("520ca5c0-839e-4c9f-a3f9-550c3ac0f028"), "bsmith", "password1", 
        "asmith@example.com", "Amy Smith", new ArrayList<Song>(), new ArrayList<Song>(), new ArrayList<Lesson>(), 
        new ArrayList<Song>(), "Question1", "Answer1"));
        userList.add(new User(UUID.fromString("a35ac2bf-22ff-45f6-815b-1597c605931e"), "csmith", "password1", 
        "asmith@example.com", "Amy Smith", new ArrayList<Song>(), new ArrayList<Song>(), new ArrayList<Lesson>(), 
        new ArrayList<Song>(), "Question1", "Answer1"));
        userList.add(new User(UUID.fromString("f16be192-34e7-4e05-8eed-bf02e5e41414"), "dsmith", "password1", 
        "asmith@example.com", "Amy Smith", new ArrayList<Song>(), new ArrayList<Song>(), new ArrayList<Lesson>(), 
        new ArrayList<Song>(), "Question1", "Answer1"));
        userList.add(new User(UUID.fromString("ea28098a-5922-4899-b70e-a8b0424d9962"), "esmith", "password1", 
        "asmith@example.com", "Amy Smith", new ArrayList<Song>(), new ArrayList<Song>(), new ArrayList<Lesson>(), 
        new ArrayList<Song>(), "Question1", "Answer1"));
        DataWriter.saveUsers(userList);
        userListTest = DataLoader.getUsers();
        assertEquals(userList, userListTest);
    }

    @Test
    public void testLoadingBlankUsersFile() {
        TestHelper.createBlankFile(DataConstants.USER_FILE_NAME);
        userListTest = DataLoader.getUsers();
        assertEquals(new ArrayList<User>(), userListTest);
    }

    @Test
    public void testLoadingCorruptedUsersFile() {
        userList.add(new User(UUID.fromString("6149dd93-1fe0-47e1-ae4a-fbce60258bbf"), "asmith", "password1", 
        "asmith@example.com", "Amy Smith", new ArrayList<Song>(), new ArrayList<Song>(), new ArrayList<Lesson>(), 
        new ArrayList<Song>(), "Question1", "Answer1"));
        DataWriter.saveUsers(userList);
        TestHelper.corruptFile(DataConstants.USER_FILE_NAME);
        userListTest = DataLoader.getUsers();
        assertEquals(new ArrayList<User>(), userListTest);
    }

    @Test
    public void testMissingInfoInJSONUsers() {
        userList.add(new User(UUID.fromString("6149dd93-1fe0-47e1-ae4a-fbce60258bbf"), "asmith", "password1", 
        "asmith@example.com", "Amy Smith", new ArrayList<Song>(), new ArrayList<Song>(), new ArrayList<Lesson>(), 
        new ArrayList<Song>(), "Question1", "Answer1"));
        userList.add(new User(UUID.fromString("520ca5c0-839e-4c9f-a3f9-550c3ac0f028"), "bsmith", "password1", 
        "asmith@example.com", "Amy Smith", new ArrayList<Song>(), new ArrayList<Song>(), new ArrayList<Lesson>(), 
        new ArrayList<Song>(), "Question1", "Answer1"));
        DataWriter.saveUsers(userList);
        TestHelper.writeIncompleteUsersFile(DataConstants.USER_FILE_NAME);  // remove attributes of first element
        userListTest = DataLoader.getUsers();
        userList.remove(0);
        assertEquals(userList, userListTest);
    }

    // Tests for getSongs method
    @Test
    public void testLoadingZeroSongs() {
        songListTest = DataLoader.getSongs();
        assertEquals(songList, songListTest);
    }

    @Test
    public void testLoadingOneSong() {
        songList.add(new Song("Title1", "Artist1"));
        DataWriter.saveSongs(songList);
        songListTest = DataLoader.getSongs();
        assertEquals(songList, songListTest);
    }

    @Test
    public void testLoadingFiveSongs() {
        songList.add(new Song("Title1", "Artist1"));
        songList.add(new Song("Title2", "Artist2"));
        songList.add(new Song("Title3", "Artist3"));
        songList.add(new Song("Title4", "Artist4"));
        songList.add(new Song("Title5", "Artist5"));
        DataWriter.saveUsers(userList);
        userListTest = DataLoader.getUsers();
        assertEquals(userList, userListTest);
    }

    @Test
    public void testLoadingBlankSongsFile() {
        TestHelper.createBlankFile(DataConstants.SONG_FILE_NAME);
        songListTest = DataLoader.getSongs();
        assertEquals(new ArrayList<Song>(), songListTest);
    }

    @Test
    public void testLoadingCorruptedSongsFile() {
        songList.add(new Song("Song1", "Artist1"));
        DataWriter.saveSongs(songList);
        TestHelper.corruptFile(DataConstants.SONG_FILE_NAME);
        songListTest = DataLoader.getSongs();
        assertEquals(new ArrayList<Song>(), songListTest);
    }

    @Test
    public void testMissingInfoInJSONSongs() {
        songList.add(new Song("Song1", "Artist1"));
        songList.add(new Song("Song2", "Artist2"));
        DataWriter.saveSongs(songList);
        TestHelper.writeIncompleteSongsFile(DataConstants.SONG_FILE_NAME);
        songListTest = DataLoader.getSongs();
        songList.remove(0);
        assertEquals(songList, songListTest);
    }

    // Tests for getLessons method
    @Test
    public void testLoadingZeroLessons() {
        lessonListTest = DataLoader.getLessons();
        assertEquals(lessonList, lessonListTest);
    }

    @Test
    public void testLoadingOneLesson() {
        lessonList.add(new Lesson("Title1", new ArrayList<Song>(), "Topic1", new ArrayList<Assignment>()));
        DataWriter.saveLessons(lessonList);
        lessonListTest = DataLoader.getLessons();
        assertEquals(lessonList, lessonListTest);
    }

    @Test
    public void testLoadingFiveLessons() {
        lessonList.add(new Lesson("Title1", new ArrayList<Song>(), "Topic1", new ArrayList<Assignment>()));
        lessonList.add(new Lesson("Title2", new ArrayList<Song>(), "Topic2", new ArrayList<Assignment>()));
        lessonList.add(new Lesson("Title3", new ArrayList<Song>(), "Topic3", new ArrayList<Assignment>()));
        lessonList.add(new Lesson("Title4", new ArrayList<Song>(), "Topic4", new ArrayList<Assignment>()));
        lessonList.add(new Lesson("Title5", new ArrayList<Song>(), "Topic5", new ArrayList<Assignment>()));
        DataWriter.saveLessons(lessonList);
        lessonListTest = DataLoader.getLessons();
        assertEquals(lessonList, lessonListTest);
    }

    @Test
    public void testLoadingBlankLessonsFile() {
        TestHelper.createBlankFile(DataConstants.LESSON_FILE_NAME);
        lessonListTest = DataLoader.getLessons();
        assertEquals(new ArrayList<Lesson>(), lessonListTest);
    }

    @Test
    public void testLoadingCorruptedLessonsFile() {
        lessonList.add(new Lesson("Title1", new ArrayList<Song>(), "Topic1", new ArrayList<Assignment>()));
        DataWriter.saveLessons(lessonList);
        TestHelper.corruptFile(DataConstants.LESSON_FILE_NAME);
        lessonListTest = DataLoader.getLessons();
        assertEquals(new ArrayList<Lesson>(), lessonListTest);
    }

    @Test
    public void testMissingInfoInJSONLessons() {
        lessonList.add(new Lesson("Title1", new ArrayList<Song>(), "Topic1", new ArrayList<Assignment>()));
        lessonList.add(new Lesson("Title2", new ArrayList<Song>(), "Topic2", new ArrayList<Assignment>()));
        DataWriter.saveLessons(lessonList);
        TestHelper.writeIncompleteLessonsFile(DataConstants.LESSON_FILE_NAME);
        lessonListTest = DataLoader.getLessons();
        lessonList.remove(0);
        assertEquals(lessonList, lessonListTest);
    }

}
