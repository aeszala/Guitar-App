﻿package com.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.UUID;
import java.util.ArrayList;

public class User {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String name;
    ArrayList<Song> favoriteSongs;
    ArrayList<Song> completedSongs;
    ArrayList<Lesson> completedLessons;
    ArrayList<Song> mySongs;
    public String securityQuestion;
    public String securityAnswer;
    public boolean login;

    public User(UUID id, String username, String password, String email, String name, 
                ArrayList<Song> favoriteSongs, ArrayList<Song> completedSongs, 
                ArrayList<Lesson> completedLessons, ArrayList<Song> mySongs, 
                String securityQuestion, String securityAnswer){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.favoriteSongs = favoriteSongs;
        this.completedSongs = completedSongs;
        this.completedLessons = completedLessons;
        this.mySongs = mySongs;
    }

    public User(String username, String password, String email, String name, String securityQuestion, String securityAnswer){
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.favoriteSongs = new ArrayList<>();
        this.completedSongs = new ArrayList<>();
        this.completedLessons = new ArrayList<>();
        this.mySongs = new ArrayList<>();
    }

    public JSONObject toJson() {
    JSONObject userObject = new JSONObject();
    userObject.put("id", this.id.toString());
    userObject.put("username", this.username);
    userObject.put("password", this.password);
    userObject.put("email", this.email);
    userObject.put("name", this.name);
    userObject.put("securityQuestion", this.securityQuestion);
    userObject.put("securityAnswer", this.securityAnswer);
    
    JSONArray favoriteSongsArray = new JSONArray();
    if (favoriteSongs != null) {
        for (Song song : favoriteSongs) {
            favoriteSongsArray.add(song.toJson());
        }
    }
    userObject.put("favoriteSongs", favoriteSongsArray);
        
    JSONArray completedSongsArray = new JSONArray();
    if (completedSongs != null) {
        for (Song song : completedSongs) {
            completedSongsArray.add(song.toJson());
        }
    }
    userObject.put("completedSongs", completedSongsArray);
        
    JSONArray completedLessonsArray = new JSONArray();
    if (completedLessons != null) {
        for (Lesson lesson : completedLessons) {
            completedLessonsArray.add(lesson.toJson());
        }
    }
    userObject.put("completedLessons", completedLessonsArray);
        
    JSONArray mySongsArray = new JSONArray();
    if (mySongs != null) {
        for (Song song : mySongs) {
            mySongsArray.add(song.toJson());
        }
    }
    userObject.put("mySongs", mySongsArray);
        
        return userObject;
    }

    public boolean login(){
        return login;
    }

    public void logOut(){
        System.out.println("User " + username + " has logged out.");
    }

    public void favoriteSong(Song Song){
        if (!favoriteSongs.contains(Song)){
            favoriteSongs.add(Song);
            System.out.println(Song.getTitle() + " added to favorites.");;
        }
    }

    public void completeSong(Song song){
        if (!completedSongs.contains(song)) {
            completedSongs.add(song);
            System.out.println(song.getTitle() + " marked as completed.");
        }
    }
    
    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    public ArrayList<Song> getCompletedSongs() {
        return completedSongs;
    }

    public ArrayList<Lesson> getCompletedLessons() {
        return completedLessons;
    }

    public ArrayList<Song> getMySongs() {
        return mySongs;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer(){
        return securityAnswer;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSecurityQuestion(String securityQuestion){
        this.securityQuestion = securityQuestion;
    }

    public void setSecurityAnswer(String securityAnswer){
        this.securityAnswer = securityAnswer;
    }

    public void setMyfavoritesongs(ArrayList<Song> favoriteSongs){
        this.favoriteSongs = favoriteSongs;
    }

    


    
    public String toString(){
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", favoriteSongs=" + favoriteSongs.size() +
                ", completedSongs=" + completedSongs.size() +
                ", completedLessons=" + completedLessons.size() +
                ", mySongs=" + mySongs.size() +
                '}';
    }

    public boolean login(String username2, String password2) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}