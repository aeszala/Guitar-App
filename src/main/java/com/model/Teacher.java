package com.model;

import java.util.ArrayList;
public class Teacher {
    ArrayList<Student> students;
    ArrayList<Assignment> assignment;

    public Teacher(){
        this.students = new ArrayList<>();
        this.assignment = new ArrayList<>();
    }

    public void assignPlay(Song song, int dueDate, Student student){
        assignment.add(new Assignment("Play", song.getTitle(), dueDate, student));
    }

    public void assignPlay(Song song, int dueDate){
        for (Student student : students){
            assignPlay(song, dueDate, student);
        }
    }

    public void assignCreate(String instructions, int dueDate, Student student){
        assignment.add(new Assignment("Create", instructions, dueDate, student));
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

    public void editAssignment(Assignment assignment){

    }

    public void comment(Assignment assignment, Student student, String comment, String role){
        assignment.addComment(comment, role);
    }



    
}
