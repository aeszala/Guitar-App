package com.model;

import java.security.PublicKey;
import java.util.ArrayList;

public class Student {
    private double grade;
    private ArrayList<Assignment> assignments;
    private ArrayList<Assignment> completedAssignments;
    private ArrayList<Lesson> lessons;
    private Teacher teacher;
    private boolean turnedIn;
    private int totalGrade;

    public Student(double grade, ArrayList<Assignment> assignments, ArrayList<Assignment> completedAssignments, ArrayList<Lesson> lessons, Teacher teacher){
        this.grade = grade;
        this.assignments = new ArrayList<>();
        this.completedAssignments = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.teacher = teacher;
    }

    public void openAssignment(Assignment assignment){
        if(assignments.contains(assignment)){
            System.out.println("Opening " + assignment.getTitle());
        }
        else {
            System.out.println("Assignment not found.");
        }
    }

    public boolean turnIn(Assignment assignment){
        if (assignments.contains(assignment)){
            assignments.remove(assignment);
            completedAssignments.add(assignment);
            System.out.println("Turned in " + assignment.getTitle());
        }
        else{
            System.out.println("Assignment not found");
        }
        return false;
    }

    public boolean turnedIn(Assignment assignment){
        return turnedIn;
    }

    public void comment(Assignment assignment, String comment){
        if (assignments.contains(assignment) || completedAssignments.contains(assignment) ){
            assignment.addComment(comment, comment);
            System.out.println("Comment added.");
        }
        else{
            System.out.println("Assignment not found");
        }
    }

    public double viewGrades(ArrayList<Assignment> assignments){
        if (assignments.isEmpty()){
            System.out.println("No graded assignments");
            return 0.0;
        }
        this.grade = totalGrade / assignments.size();
        return grade;
    }

    public double getGrade(){
        return grade;
    }

    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }

    public ArrayList<Assignment> getCompleteAssignments(){
        return completedAssignments;
    }

    public ArrayList<Lesson> getLessons(){
        return lessons;
    }

    public Teacher getTeacher(){
        return teacher;
    }

    public void setGrade(double grade){
        this.grade = grade;
    }

    public void setAssignment(ArrayList<Assignment> assignments){
        this.assignments = assignments;
    }

    public void setCompletedAssignments(ArrayList<Assignment> completedAssignments){
        this.completedAssignments = completedAssignments;
    }

    public void setLesson(ArrayList<Lesson> lessons){
        this.lessons = lessons;
    }
}