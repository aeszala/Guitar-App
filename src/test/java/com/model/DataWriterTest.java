package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.experimental.theories.suppliers.TestedOn;

class DataWriterTest {
    private ArrayList<User> userList;

    @Before
    public void setup() {
        userList = new ArrayList<>();
        DataWriter.saveUsers(userList);
    }

    @After
    public void tearDown() {
        userList.clear();
        DataWriter.saveUsers(userList);
    }

    @org.junit.Test
    void testWritingZeroUsers() {
        userList = DataLoader.getUsers();
        assertEquals(0, userList.size());
    }

    @org.junit.Test
    void testWritingOneUser() {
        userList.add(new User("asmith", "password1", "asmith@example.com", "Amy Smith", "Question", "Answer"));
        DataWriter.saveUsers(userList);
        assertEquals("asmith", DataLoader.getUsers().get(0).getUsername());
    }

    @org.junit.Test
    void testWritingFiveUsers() {
        userList.add(new User("asmith", "password1", "asmith@example.com", "Amy Smith", "Question1", "Answer1"));
        userList.add(new User("bsmith", "password2", "bsmith@example.com", "Bob Smith", "Question2", "Answer2"));
        userList.add(new User("csmith", "password3", "csmith@example.com", "Charlie Smith", "Question3", "Answer3"));
        userList.add(new User("dsmith", "password4", "dsmith@example.com", "David Smith", "Question4", "Answer4"));
        userList.add(new User("esmith", "password5", "esmith@example.com", "Emily Smith", "Question5", "Answer5"));
        DataWriter.saveUsers(userList);
        assertEquals("esmith", DataLoader.getUsers().get(4).getUsername());
    }

    @org.junit.Test
    void testWritingEmptyUser() {
        userList.add(new User("", "", "", "", "", ""));
        DataWriter.saveUsers(userList);
        assertEquals("", DataLoader.getUsers().get(0).getUsername());
    }

    @org.junit.Test
    void testWritingNullUser() {
        User nullUser = new User(null, null, null, null, null, null);
        userList.add(nullUser);
        DataWriter.saveUsers(userList);
        assertNull(DataLoader.getUsers().get(0).getUsername());
    }
}
