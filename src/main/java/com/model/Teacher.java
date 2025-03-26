package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
public class Teacher extends User{
    ArrayList<Student> students;
    public Assignment assignment;

    // Existing teacher constructor
        public Teacher(UUID id, String username, String password, String email, String name, 
                   ArrayList<Song> favoriteSongs, ArrayList<Song> completedSongs, 
                   ArrayList<Lesson> completedLessons, ArrayList<Song> mySongs, 
                   String securityQuestion, String securityAnswer, 
                   ArrayList<Student> students) {
            super(id, username, password, email, name, favoriteSongs, completedSongs, 
              completedLessons, mySongs, securityQuestion, securityAnswer);
            this.students = students;
    }

    // New teacher constructor
    public Teacher(String username, String password, String email, String name, 
                   String securityQuestion, String securityAnswer) {
        super(username, password, email, name, securityQuestion, securityAnswer);
        this.students = new ArrayList<>();
    }

    // assign to one student
    public void assignPlay(String title, String comment, int tempo, Song song, Date dueDate, Student student){
        student.addAssignment(new PlayAssignment(title, comment, dueDate, song, tempo));
    }

    // assign to all students
    public void assignPlay(String title, String comment, int tempo, Song song, Date dueDate) {
        for (Student student : students)
            student.addAssignment(new PlayAssignment(title, comment, dueDate, song, tempo));
    }

    public void assignCreate(String instructions, int dueDate, Student student){
        assignment.add(new CAssignment("Create", instructions, dueDate, student));
    }

    public void assignCreate(String instructions, int dueDate){
        for (Student student : students) {
            assignCreate(instructions, dueDate, student);
        }
    }

    public void assignQuestion(String question, ArrayList<String> answerChoices, String answer, int dueDate, Student student){
        assignment.add(new Assignment("Question", question, dueDate, student));
    }

    public void assignQuestion(String question, ArrayList<String> answerChoices, String answer, int dueDate){
        for (Student student : students) {
            assignQuestion(question, answerChoices, answer, dueDate, student);
        }
    }

    public void assignLesson(Lesson lesson, Student student, int dueDate){
        assignment.add(new Assignment("Lesson", lesson.getTitle(), dueDate, student));
    }

    public void assignGrade(Assignment assignment, Student student, double Grade){
        assignment.setGrade(Grade);
    }

    public void editAssignment(Assignment assignment, String title){
        assignment.setTitle();
    }

    public void comment(Assignment assignment, Student student, String comment, String role){
        assignment.addComment(comment, role);
    }

    public void getStudents(ArrayList<Student> students){
        this.students = students;
    }

    
}
