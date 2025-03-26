package com.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {
    private double grade;
    private ArrayList<Assignment> assignments;
    private ArrayList<Assignment> completedAssignments;
    private ArrayList<Lesson> assignedLessons;
    private Teacher teacher;

    // Existing student constructor
    public Student(UUID id, String username, String password, String email, String name,
            ArrayList<Song> favoriteSongs, ArrayList<Song> completedSongs,
            ArrayList<Lesson> completedLessons, ArrayList<Song> mySongs,
            String securityQuestion, String securityAnswer,
            double grade, ArrayList<Lesson> assignedLessons, Teacher teacher) {
        super(id, username, password, email, name, favoriteSongs, completedSongs,
                completedLessons, mySongs, securityQuestion, securityAnswer);
        this.grade = grade;
        this.assignedLessons = assignedLessons;
        this.teacher = teacher;
    }

    // New student constructor
    public Student(String username, String password, String email, String name,
            String securityQuestion, String securityAnswer, Teacher teacher) {
        super(username, password, email, name, securityQuestion, securityAnswer);
        this.grade = 0.0; // Default grade
        this.assignedLessons = new ArrayList<>();
        this.teacher = teacher;
    }

    public void openAssignment(Assignment assignment) {
        if (assignments.contains(assignment)) {
            System.out.println("Opening " + assignment.getTitle());
        } else {
            System.out.println("Assignment not found.");
        }
    }

    public boolean turnIn(Assignment assignment) {
        if (assignments.contains(assignment)) {
            assignments.remove(assignment);
            completedAssignments.add(assignment);
            System.out.println("Turned in " + assignment.getTitle());
        } else {
            System.out.println("Assignment not found");
        }
        return false;
    }

    public void comment(Assignment assignment, String comment) {
        if (assignments.contains(assignment) || completedAssignments.contains(assignment)) {
            assignment.addComment(comment, comment);
            System.out.println("Comment added.");
        } else {
            System.out.println("Assignment not found");
        }
    }

    public void viewGrades(ArrayList<Assignment> assignments) {
        if (assignments.isEmpty() || assignments == null) {
            System.out.println("No graded assignments or lessons");
        } else {
            System.out.println("Assignment Grades: ");
            for (Assignment assignment : completedAssignments) {
                System.out.println(assignment.getTitle() + ": " + assignment.getGrade());
            }
            System.out.println("\nLesson Progress: ");
            for (Lesson lesson : completedLessons) {
                System.out.println(lesson.getTitle() + ": " + lesson.getProgress());
            }
        }

    }

    public double getGrade() {
        calculateGrade();
        return grade;
    }

    public double calculateGrade() {
        double tempGrade = 0;
        int numOfAssignments = 0;
        for (Assignment assignment : completedAssignments) {
            tempGrade += assignment.getGrade();
            numOfAssignments++;
        }
        for (Lesson lesson : completedLessons) {
            tempGrade += lesson.getProgress();
            numOfAssignments++;
        }
        if (numOfAssignments != 0)
            return tempGrade / numOfAssignments;
        return -1;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public ArrayList<Assignment> getCompleteAssignments() {
        return completedAssignments;
    }

    public ArrayList<Lesson> getAssignedLessons() {
        return assignedLessons;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setAssignment(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setCompletedAssignments(ArrayList<Assignment> completedAssignments) {
        this.completedAssignments = completedAssignments;
    }

    public void setAssignedLessons(ArrayList<Lesson> lessons) {
        this.assignedLessons = lessons;
    }

    public void assignLesson(Lesson lesson) {
        assignedLessons.add(lesson);
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }
}