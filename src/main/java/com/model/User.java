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

    public void user(){

    }

    public boolean login(){
        return login;
    }

    public void logOut(){

    }

    public void favoriteSong(Song Song){

    }

    public void completeSong(Song song){
        
    }



    
    
}
