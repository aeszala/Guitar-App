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

    public User(UUID id, String username, String password, String email, String name,
        ArrayList<Song> favoriteSongs, ArrayList<Song> completedSongs,
        ArrayList<Lesson> completedLessons, ArrayList<Song> mySongs,
        String securityQuestion, String securityAnswer) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.favoriteSongs = favoriteSongs;
        this.completedSongs = completedSongs;
        this.completedLessons = completedLessons;
        this.mySongs = mySongs;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public boolean login(){
        return false;
    }

    public void logOut(){

    }

    public void favoriteSong(Song Song){

    }

    public void completeSong(Song song){
        
    }



    
    
}
