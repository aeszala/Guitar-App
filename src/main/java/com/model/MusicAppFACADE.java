package com.model;

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
    
   public void MusicApp(){

   }

   public void findSongs(String keyword){

   }
   
    public void createStudentAccount(String name, String username, String password, String email, Teacher teacher, String securityQuestion, String securityAnswer){
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSecurityQuestion(securityQuestion);
        user.setSecurityAnswer(securityAnswer);
        student.getTeacher();
   }

   public void createTeacherAccount(String name, String username, String password, String email, String securityQuestion, String securityAnswer){
        teacher = new Teacher();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSecurityQuestion(securityQuestion);
        user.setSecurityAnswer(securityAnswer);
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
        song.setLyrics(Lyrics);
        song.setTempo(tempo);
    }

    public void editSongTitle(String title){
        song.setTitle(title);
    }

    public void reviewSong(Song song, int rating, String comment, Assignment assignment){
        song.getTitle();
        song.setRating(rating);
        teacher.comment(assignment, student, comment, comment);
        student.comment(assignment, comment);
    }

    public void addGrade(){

    }

    public void viewGrade(Assignment assignment){
        assignment.getGrade();
    }

    public void turnIn(){

    }

    public void addPlayAssignment(String Title, double grade, Date dueDate, boolean Complete){
        assignment.setTitle(Title);
        assignment.setGrade(grade);
        assignment.setDueDate(dueDate);
        assignment.complete();
    }

    public void addQuestionAssignment(String Title, double grade, Date dueDate, boolean Complete){
        assignment.setTitle(Title);
        assignment.setGrade(grade);
        assignment.setDueDate(dueDate);
        assignment.complete();
    }

    public void addComposeAssignment(String Title, double grade, Date dueDate, boolean Complete){
        assignment.setTitle(Title);
        assignment.setGrade(grade);
        assignment.setDueDate(dueDate);
        assignment.complete();
    }

    public void viewLesson(){

    }

    public void viewAssignments(){

    }
}
