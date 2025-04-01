package com.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserListTest {

    private UserList userList;

    @Before
    public void setUp() {
        userList = UserList.getInstance();
        userList.getUsers().clear(); 
    }

    @Test
    public void testSingletonInstance() {
        UserList instance1 = UserList.getInstance();
        UserList instance2 = UserList.getInstance();
        assertSame("Instances should be the same", instance1, instance2);
    }

    @Test
    public void testAddUserSuccess() {
        boolean added = userList.addUser("john123", "password", "john@example.com", "John Doe", "Favorite color?", "Blue");
        assertTrue("User should be added successfully", added);
        assertEquals(1, userList.getUsers().size());
    }

    @Test
    public void testAddUserDuplicate() {
        userList.addUser("john123", "password", "john@example.com", "John Doe", "Q", "A");
        boolean added = userList.addUser("john123", "pass2", "john2@example.com", "Johnny", "Q", "A");
        assertFalse("Duplicate username should not be allowed", added);
        assertEquals(1, userList.getUsers().size());
    }

    @Test
    public void testGetUserFound() {
        userList.addUser("jane123", "pass", "jane@example.com", "Jane", "Q", "A");
        User user = UserList.getUser("Jane123");
        assertNotNull(user);
        assertEquals("jane123", user.getUsername());
    }

    @Test
    public void testGetUserNotFound() {
        User user = UserList.getUser("ghost");
        assertNull(user);
    }

    @Test
    public void testIsMatchCorrectPassword() {
        userList.addUser("bob", "secret", "bob@x.com", "Bob", "Q", "A");
        User user = UserList.getUser("bob");
        assertTrue(userList.isMatch(user, "secret"));
    }

    @Test
    public void testIsMatchWrongPassword() {
        userList.addUser("bob", "secret", "bob@x.com", "Bob", "Q", "A");
        User user = UserList.getUser("bob");
        assertFalse(userList.isMatch(user, "wrongpass"));
    }

    @Test
    public void testEditUser() {
        userList.addUser("tom", "pass", "tom@x.com", "Tom", "Q", "A");
        User user = UserList.getUser("tom");
        userList.editUser(user, "tommy", "Tommy Lee", "newpass", "tommy@x.com");
        
        User updated = UserList.getUser("tommy");
        assertNotNull(updated);
        assertEquals("tommy", updated.getUsername());
        assertEquals("Tommy Lee", updated.getName());
        assertEquals("tommy@x.com", updated.getEmail());
    }

    @Test
    public void testEditUserNull() {
        // This should not throw and should print "User not found."
        userList.editUser(null, "u", "n", "p", "e");
    }

    @Test
    public void testSaveUsersRuns() {
        userList.addUser("savedUser", "1234", "saved@example.com", "Savey", "Q", "A");
        try {
            UserList.saveUsers();
        } catch (Exception e) {
            fail("saveUsers should not throw exceptions");
        }
    }
}
