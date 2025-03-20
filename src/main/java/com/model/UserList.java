package com.model;

import java.util.ArrayList;

public class UserList {
    private static UserList userList = null;
    private ArrayList<User> users;

    private UserList() {
        users = DataLoader.getUsers();
    }

    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean addUser(String username, String password, String email, String name, String securityQuestion, String securityAnswer) {
        if (getUser(username) != null) {
            System.out.println("Username already exists.");
            return false;
        }

        User newUser = new User(username, password, email, name, securityQuestion, securityAnswer);
        users.add(newUser);
        System.out.println("User " + username + " added successfully.");
        return true;
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null; 
    }

    public void editUser(User user, String username, String name, String password, String email) {
        if (user != null) {
            user = getUser(user.getUsername()); 
            if (user != null) {
                user = new User(username, password, email, name, user.getSecurityQuestion(), user.getSecurityAnswer());
                System.out.println("User details updated successfully.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public boolean isMatch(User user, String password) {
        return user != null && user.login(user.getUsername(), password);
    }

    public void saveUsers() {
        System.out.println("Users saved successfully. (Implement file/database storage here)");
    }

    public void displayUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}
