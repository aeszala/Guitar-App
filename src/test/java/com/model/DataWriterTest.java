package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class DataWriterTest {
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
    void testWritingZeroUsers() {
        userList = DataLoader.getUsers();
        assertEquals(0, userList.size());
    }

    @Test
    void testWritingOneUser() {
        userList.add(new User("asmith", "password1", "asmith@example.com", "Amy Smith", "Question", "Answer"));
        DataWriter.saveUsers(userList);
        assertEquals("asmith", DataLoader.getUsers().get(0).getUsername());
    }

    @Test
    void testWritingFiveUsers() {
        userList.add(new User("asmith", "password1", "asmith@example.com", "Amy Smith", "Question1", "Answer1"));
        userList.add(new User("bsmith", "password2", "bsmith@example.com", "Bob Smith", "Question2", "Answer2"));
        userList.add(new User("csmith", "password3", "csmith@example.com", "Charlie Smith", "Question3", "Answer3"));
        userList.add(new User("dsmith", "password4", "dsmith@example.com", "David Smith", "Question4", "Answer4"));
        userList.add(new User("esmith", "password5", "esmith@example.com", "Emily Smith", "Question5", "Answer5"));
        DataWriter.saveUsers(userList);
        assertEquals("esmith", DataLoader.getUsers().get(4).getUsername());
    }

    @Test
    void testWritingEmptyUser() {
        userList.add(new User("", "", "", "", "", ""));
        DataWriter.saveUsers(userList);
        assertEquals("", DataLoader.getUsers().get(0).getUsername());
    }

    @Test
    void testWritingNullUser() {
        User nullUser = new User(null, null, null, null, null, null);
        userList.add(nullUser);
        DataWriter.saveUsers(userList);
        assertNull(DataLoader.getUsers().get(0).getUsername());
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
    void testWritingZeroSongs() {
        songList = DataLoader.getSongs();
        assertEquals(0, songList.size());
    }

    @Test
    void testWritingOneSong() {
        songList.add(new Song("title1", "artist1"));
        DataWriter.saveSongs(songList);
        assertEquals("asmith", DataLoader.getSongs().get(0).getTitle());
    }

    @Test
    void testWritingFiveSongs() {
        songList.add(new Song("title1", "artist1"));
        songList.add(new Song("title2", "artist2"));
        songList.add(new Song("title3", "artist3"));
        songList.add(new Song("title4", "artist4"));
        songList.add(new Song("title5", "artist5"));
        DataWriter.saveSongs(songList);
        assertEquals("title5", DataLoader.getSongs().get(4).getTitle());
    }

    @Test
    void testWritingEmptySong() {
        songList.add(new Song("", ""));
        DataWriter.saveSongs(songList);
        assertEquals("", DataLoader.getSongs().get(0).getTitle());
    }

    @Test
    void testWritingNullSong() {
        Song nullSong = new Song(null, null);
        songList.add(nullSong);
        DataWriter.saveSongs(songList);
        assertNull(DataLoader.getSongs().get(0).getTitle());
    }
}
