﻿package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MusicAppFACADE {
    private User user;
    private Student student;
    private Teacher teacher;
    private Song song;
    private Assignment assignment;
    private UserList userList = UserList.getInstance();
    private Songlist songList = Songlist.getInstance();
    
   public void MusicApp(){

   }

   public ArrayList<Song> findSongs(String keyword){
    if (songList == null)
        songList = Songlist.getInstance();
    return songList.getSongs(keyword);
   }
   
    public void createStudentAccount(String name, String username, String password, String email, Teacher teacher, String securityQuestion, String securityAnswer){
        student = new Student(username, password, email, name, securityQuestion, securityAnswer, teacher);
   }

   public void createTeacherAccount(String name, String username, String password, String email, String securityQuestion, String securityAnswer){
        teacher = new Teacher(username, password, email, name, securityQuestion, securityAnswer);
   }

   public void createAccount(String name, String username, String password, String email, String securityQuestion, String securityAnswer){
        if (userList == null)
            userList = UserList.getInstance();
        if (userList.addUser(username, password, email, username, securityQuestion, securityAnswer))
            user = UserList.getUser(username);
    }

    public void login(String username, String password){
        User tempUser = new User("", "", "", "", "", "");
        if (tempUser.isMatch(username, password)) {
            user = UserList.getUser(username);
            System.out.println("Login Successful!");
        } else {
            System.out.println("Username or password incorrect.");
        }
    }

    public void logOut(){
        user = null;
        System.out.println("Logout successful!");
    }

    public void getFavoriteSongs(ArrayList<Song> favoriteSongs){
        user.getFavoriteSongs();
    }

    public void getMySongs(ArrayList<Song> mySongs){
        user.getMySongs();
    }

    public void addSong(String title, String artist, int runLengthMin, int runLengthSec, String Lyrics, int tempo, Measure measure){
        song.setTitle(title);
        song.setArtist(artist);
        song.setRunLengthMin(runLengthMin);
        song.setRunLengthSec(runLengthSec);
        song.setTempo(tempo);
    }

    public void editSongTitle(String title){
        song.setTitle(title);
    }

    public void reviewSong(Song song, int rating, String comment, Assignment assignment) {
        song.addReview(new Review(rating, comment, user.getName()));
    }

    public void addGrade(){
        
    }

    public void viewGrade(Assignment assignment){
        assignment.getGrade();
    }

    public void turnIn(Assignment assignment){
        assignment.complete();
    }

    public void turnIn(Lesson lesson){
        lesson.complete();
    }

    public void addPlayAssignment(String title, String teacherComment, Date dueDate, Song song, int tempo){
        student.addAssignment(new PlayAssignment(title, teacherComment, dueDate, song, tempo));
    }

    public void addQuestionAssignment(String title, String teacherComment, Date dueDate,
                                        ArrayList<Question> questions){
        student.addAssignment(new QuestionAssignment(title, teacherComment, dueDate, questions));
    }

    public void addComposeAssignment(String title, String teacherComment, Date dueDate, Song song,
                                    int tempo, String instructions){
        student.addAssignment(new ComposeAssignment(title, teacherComment, dueDate, song, tempo, instructions));
    }

    public void viewLesson(){

    }

    public void viewAssignments(){

    }
}
