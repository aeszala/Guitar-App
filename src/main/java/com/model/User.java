package com.model;

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

    public User(String username2, String password2, String email2, String name2, String securityQuestion2,
            String securityAnswer2) {
        //TODO Auto-generated constructor stub
    }

    public void user(String username, String password, String email, String name, String securityQuestion, String securityAnswer){
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