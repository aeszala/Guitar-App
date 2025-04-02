/**
 * @author Liam
 */

 package com.model;

 import java.util.ArrayList;
 
 public class UserList {
     // Singleton instance of UserList
     private static UserList userList = null;
     // List to store users
     private static ArrayList<User> users;
 
     // Private constructor to initialize the users list from data loader
     private UserList() {
         users = DataLoader.getUsers(); // Load users from data
     }
 
     // Singleton pattern: Get the instance of UserList
     public static UserList getInstance() {
         if (userList == null) {
             userList = new UserList(); // Create new instance if it doesn't exist
         }
         return userList;
     }
 
     // Return the list of users
     public ArrayList<User> getUsers() {
         return users;
     }
 
     // Add a new user to the list, ensuring no duplicates
     public boolean addUser(String username, String password, String email, String name, String securityQuestion, String securityAnswer) {
         // Check if the username already exists
         if (getUser(username) != null) {
             System.out.println("Username " + username + " already exists.");
             return false; // Return false if username is taken
         }
 
         // Create a new user and add to the list
         User newUser = new User(username, password, email, name, securityQuestion, securityAnswer);
         users.add(newUser);
         System.out.println("User " + username + " added successfully.");
         return true; // Return true after adding the user
     }
 
     // Retrieve a user by their username
     public static User getUser(String username) {
         for (User user : users) {
             if (user.getUsername().equalsIgnoreCase(username)) {
                 return user; // Return the user if found
             }
         }
         return null; // Return null if no matching user is found
     }
 
     // Edit an existing user's details
     public void editUser(User user, String username, String name, String password, String email) {
         if (user != null) {
             user.setUsername(username)
             user.setName(name)
             user.setPassword(password)
             user.setEmail(email)
                 System.out.println("User details updated successfully.");
             }
         } else {
             System.out.println("User not found."); // Print error if user doesn't exist
         }
     }
 
     // Check if the given password matches the user's stored password
     public boolean isMatch(User user, String password) {
         return user != null && user.login(user.getUsername(), password); // Return true if passwords match
     }
 
     // Save the list of users using the DataWriter utility
     public static void saveUsers() {
         DataWriter.saveUsers(users); // Save users to data storage
         System.out.println("Users saved successfully!");
     }
 
     // Display the details of all users in the list
     public void displayUsers() {
         for (User user : users) {
             System.out.println(user); // Print each user's details
         }
     }
 }
 
