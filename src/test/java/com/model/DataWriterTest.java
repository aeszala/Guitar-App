package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataWriterTest {
    private ArrayList<User> userList;

    @Before
    public void setupUser() {
        userList = new ArrayList<>();
        DataWriter.saveUsers(userList);
    }

    @After
    public void tearDownUser() {
        userList.clear();
        DataWriter.saveUsers(userList);
    }

    @Test
    public void testingTest() {
        assertTrue(true);
    }

    @Test
    public void testWritingZeroUsers() {
        userList = DataLoader.getUsers();
        assertEquals(0, userList.size());
    }

    @Test
    public void testWritingOneUser() {
        userList.add(new User("asmith", "password1", "asmith@example.com", "Amy Smith", "Question", "Answer"));
        DataWriter.saveUsers(userList);
        
        userList = DataLoader.getUsers();
        assertEquals("asmith", DataLoader.getUsers().get(0).getUsername());
    }

    @Test
    public void testWritingFiveUsers() {
        userList.add(new User("asmith", "password1", "asmith@example.com", "Amy Smith", "Question1", "Answer1"));
        userList.add(new User("bsmith", "password2", "bsmith@example.com", "Bob Smith", "Question2", "Answer2"));
        userList.add(new User("csmith", "password3", "csmith@example.com", "Charlie Smith", "Question3", "Answer3"));
        userList.add(new User("dsmith", "password4", "dsmith@example.com", "David Smith", "Question4", "Answer4"));
        userList.add(new User("esmith", "password5", "esmith@example.com", "Emily Smith", "Question5", "Answer5"));
        
        DataWriter.saveUsers(userList);
        userList = DataLoader.getUsers();
        
        assertEquals(5, userList.size());
        assertEquals("esmith", DataLoader.getUsers().get(4).getUsername());
    }

    @Test
    public void testWritingEmptyUser() {
        userList.add(new User("", "", "", "", "", ""));
        DataWriter.saveUsers(userList);
        
        userList = DataLoader.getUsers();
        assertEquals("", DataLoader.getUsers().get(0).getUsername());
    }

    @Test
    public void testWritingNullUser() {
        User nullUser = new User(null, null, null, null, null, null);
        userList.add(nullUser);
        
        DataWriter.saveUsers(userList);
        userList = DataLoader.getUsers();

        assertNull(userList.get(0).getUsername());
    }

    @Test
    public void testWriteUsersToFile() {
        userList.add(new User("testuser", "password123", "testuser@example.com", "Test User", "Test Question", "Test Answer"));
        DataWriter.saveUsers(userList);

        // Check if the file exists after writing
        File file = new File("src\\main\\java\\com\\data\\json\\users.json");
        assertTrue("File should exist after writing users", file.exists());
    }

    private ArrayList<Song> songList;

    @Before
    public void setupSong() {
        songList = new ArrayList<>();
        DataWriter.saveSongs(songList);
    }

    @After
    public void tearDownSong() {
        songList.clear();
        DataWriter.saveSongs(songList);
    }

    @Test
    public void testWritingZeroSongs() {
        songList = DataLoader.getSongs();
        assertEquals(0, songList.size());
    }

    @Test
    public void testWritingOneSong() {
        songList.add(new Song("title1", "artist1"));
        DataWriter.saveSongs(songList);
        
        songList = DataLoader.getSongs();
        assertEquals("title1", DataLoader.getSongs().get(0).getTitle());
    }

    @Test
    public void testWritingFiveSongs() {
        songList.add(new Song("title1", "artist1"));
        songList.add(new Song("title2", "artist2"));
        songList.add(new Song("title3", "artist3"));
        songList.add(new Song("title4", "artist4"));
        songList.add(new Song("title5", "artist5"));
        
        DataWriter.saveSongs(songList);
        songList = DataLoader.getSongs();
        
        assertEquals(5, songList.size());
        assertEquals("title5", DataLoader.getSongs().get(4).getTitle());
    }

    @Test
    public void testWritingEmptySong() {
        songList.add(new Song("", ""));
        DataWriter.saveSongs(songList);
        
        songList = DataLoader.getSongs();
        assertEquals("", DataLoader.getSongs().get(0).getTitle());
    }

    @Test
    public void testWritingNullSong() {
        Song nullSong = new Song(null, null);
        songList.add(nullSong);
        
        DataWriter.saveSongs(songList);
        songList = DataLoader.getSongs();

        assertNull(songList.get(0).getTitle());
    }

    @Test
    public void testWriteSongsToFile() {
        songList.add(new Song("testTitle", "testArtist"));
        DataWriter.saveSongs(songList);

        // Check if the song file exists after writing
        File file = new File("src\\main\\java\\com\\data\\json\\songs.json");
        assertTrue("File should exist after writing songs", file.exists());
    }

    private ArrayList<Lesson> lessonList;

    @Before
    public void setupLesson() {
        lessonList = new ArrayList<>();
        DataWriter.saveLessons(lessonList);
    }

    @After
    public void tearDownLesson() {
        lessonList.clear();
        DataWriter.saveLessons(lessonList);
    }

    @Test
    public void testWritingZeroLessons() {
        lessonList = DataLoader.getLessons();
        assertEquals(0, lessonList.size());
    }

    @Test
    public void testWritingOneLesson() {
        ArrayList<Song> songList = new ArrayList<>();
        ArrayList<Assignment> assignmentList = new ArrayList<>();

        lessonList.add(new Lesson("lesson1", songList, "topic1", assignmentList));
        DataWriter.saveLessons(lessonList);

        lessonList = DataLoader.getLessons();
        assertEquals("lesson1", lessonList.get(0).getTitle());
    }

    @Test
    public void testWritingFiveLessons() {
        ArrayList<Song> songList = new ArrayList<>();
        ArrayList<Assignment> assignmentList = new ArrayList<>();

        lessonList.add(new Lesson("lesson1", songList, "topic1", assignmentList));
        lessonList.add(new Lesson("lesson2", songList, "topic2", assignmentList));
        lessonList.add(new Lesson("lesson3", songList, "topic3", assignmentList));
        lessonList.add(new Lesson("lesson4", songList, "topic4", assignmentList));
        lessonList.add(new Lesson("lesson5", songList, "topic5", assignmentList));

        DataWriter.saveLessons(lessonList);
        lessonList = DataLoader.getLessons();

        assertEquals(5, lessonList.size());
        assertEquals("lesson5", lessonList.get(4).getTitle());
    }

    @Test
    public void testWritingEmptyLesson() {
        lessonList.add(new Lesson("", new ArrayList<Song>(), "", new ArrayList<Assignment>()));
        DataWriter.saveLessons(lessonList);

        lessonList = DataLoader.getLessons();
        assertEquals("", lessonList.get(0).getTitle());  // Expect "null", not ""
    }

    @Test
    public void testWritingNullLesson() {
        lessonList.add(new Lesson(null, null, null, null));
        DataWriter.saveLessons(lessonList);

        lessonList = DataLoader.getLessons();
        assertNull(lessonList.get(0).getTitle());
    }

    @Test
    public void testWriteLessonsToFile() {
        ArrayList<Song> songList = new ArrayList<>();
        ArrayList<Assignment> assignmentList = new ArrayList<>();
    
        lessonList.add(new Lesson("testLesson", songList, "testTopic", assignmentList));
        DataWriter.saveLessons(lessonList);

        // Check if the lesson file exists after writing
        File file = new File("src\\main\\java\\com\\data\\json\\Lesson.json");
        assertTrue("File should exist after writing lessons", file.exists());
    }
}
