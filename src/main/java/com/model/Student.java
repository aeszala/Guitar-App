package com.model;

import java.security.PublicKey;
import java.util.ArrayList;

public class Student {
    private double grade;
    ArrayList<Assignment> assignments;
    ArrayList<Assignment> completedAssignments;
    ArrayList<Lesson> lessons;
    private Teacher teacher;
    private boolean turnedIn

    public void openAssignment(Assignment assignment){

    }

    public boolean turnIn(Assignment assignment){
        return turnedIn;
    }

    public void comment(Assignment assignment, String comment){

    }

    public double viewGrades(ArrayList<Assignment> assignments){
        return grade;
    }
}
