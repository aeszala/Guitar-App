package com.model;

import java.util.ArrayList;

public class MusicAppFACADE {
    private User user;
    private Student student;
    private Teacher teacher;
    private Song song;
    
   public void createStudentAccount(String name, String username, String password, String email, Teacher teacher, String securityQuestion, String securityAnswer){
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSecurityQuestion(securityQuestion);
        user.setSecurityAnswer(securityAnswer);
        student.getTeacher();
   }

   public void createTeacherAccount(String name, String username, String password, String email, Teacher teacher, String securityQuestion, String securityAnswer, ArrayList<Student> students){
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSecurityQuestion(securityQuestion);
        user.setSecurityAnswer(securityAnswer);
        teacher.getStudents(students);
   }

   public void createAccount(String name, String username, String password, String email, Teacher teacher, String securityQuestion, String securityAnswer, ArrayList<Student> students){
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSecurityQuestion(securityQuestion);
        user.setSecurityAnswer(securityAnswer);
    }

    public void login(String username, String password, String securityQuestion, String SecurityAnswer){
        user.getUsername();
        user.getPassword();
        user.getSecurityQuestion();
        user.getSecurityAnswer();
    }

    public void addSong(String title, String artist, int runLengthMin, int runLengthSec, String Lyrics, int tempo, Measure measure){
        song.setTitle(title);
        song.setArtist(artist);
        song.setRunLengthMin(runLengthMin);
        song.setRunLengthSec(runLengthSec);
        song.setLyrics(Lyrics);
        song.setTempo(tempo);
    }

    public void reviewSong(Song song, int rating, String comment, Assignment assignment){
        song.getTitle();
        song.setRating(rating);
        teacher.comment(assignment, student, comment, comment);
        student.comment(assignment, comment);
    }

    public void viewGrade(Assignment assignment){
        assignment.getGrade();
    }

    


}
