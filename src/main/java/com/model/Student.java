/**
 * @author Liam
 */

package com.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The Student class represents a student user, extending from the User class.
 * It manages student-specific data, including assignments, lessons, grades, and teacher association.
 */
public class Student extends User {

    private double grade;
    private ArrayList<Assignment> assignments;
    private ArrayList<Assignment> completedAssignments;
    private ArrayList<Lesson> assignedLessons;
    private Teacher teacher;

    /**
     * Constructor for an existing student with a specified ID.
     *
     * @param id                 Unique identifier for the student.
     * @param username           The student's username.
     * @param password           The student's password.
     * @param email              The student's email address.
     * @param name               The student's name.
     * @param favoriteSongs      List of the student's favorite songs.
     * @param completedSongs     List of songs the student has completed.
     * @param completedLessons   List of lessons the student has completed.
     * @param mySongs            List of songs created by the student.
     * @param securityQuestion   Security question for account recovery.
     * @param securityAnswer     Answer to the security question.
     * @param grade              The student's current grade.
     * @param assignedLessons    List of lessons assigned to the student.
     * @param teacher            The teacher responsible for the student.
     */
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

    /**
     * Constructor for creating a new student with basic information.
     * 
     * @param username          The student's username.
     * @param password          The student's password.
     * @param email             The student's email address.
     * @param name              The student's name.
     * @param securityQuestion  Security question for account recovery.
     * @param securityAnswer    Answer to the security question.
     * @param teacher           The teacher responsible for the student.
     */
    public Student(String username, String password, String email, String name,
                    String securityQuestion, String securityAnswer, Teacher teacher) {
        super(username, password, email, name, securityQuestion, securityAnswer);
        this.grade = 0.0;
        this.assignedLessons = new ArrayList<>();
        this.teacher = teacher;
    }

    /**
     * Opens an assignment if it exists in the student's assignment list.
     *
     * @param assignment The assignment to open.
     */
    public void openAssignment(Assignment assignment) {
        if (assignments.contains(assignment)) {
            System.out.println("Opening " + assignment.getTitle());
        } else {
            System.out.println("Assignment not found.");
        }
    }

    /**
     * Turns in an assignment, moving it from assignments to completed assignments.
     *
     * @param assignment The assignment to turn in.
     * @return True if the assignment was successfully turned in, false otherwise.
     */
    public boolean turnIn(Assignment assignment) {
        if (assignments.contains(assignment)) {
            assignments.remove(assignment);
            completedAssignments.add(assignment);
            System.out.println("Turned in " + assignment.getTitle());
            return true;
        } else {
            System.out.println("Assignment not found.");
            return false;
        }
    }

    /**
     * Adds a comment to an assignment if it exists.
     *
     * @param assignment The assignment to comment on.
     * @param comment    The comment to add.
     */
    public void comment(Assignment assignment, String comment) {
        if (assignments.contains(assignment) || completedAssignments.contains(assignment)) {
            assignment.addComment(comment, comment);
            System.out.println("Comment added.");
        } else {
            System.out.println("Assignment not found.");
        }
    }

    /**
     * Displays the student's grades for assignments and progress for lessons.
     *
     * @param assignments The list of assignments to view grades for.
     */
    public void viewGrades(ArrayList<Assignment> assignments) {
        if (assignments == null || assignments.isEmpty()) {
            System.out.println("No graded assignments or lessons.");
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

    /**
     * Returns the student's overall grade by calculating the average grade
     * from assignments and lesson progress.
     *
     * @return The student's calculated grade.
     */
    public double getGrade() {
        calculateGrade();
        return grade;
    }

    /**
     * Calculates the average grade based on completed assignments and lessons.
     *
     * @return The calculated grade, or -1 if no assignments or lessons are completed.
     */
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

    /**
     * Returns the list of assignments for the student.
     *
     * @return The list of assignments.
     */
    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Returns the list of completed assignments for the student.
     *
     * @return The list of completed assignments.
     */
    public ArrayList<Assignment> getCompleteAssignments() {
        return completedAssignments;
    }

    /**
     * Returns the list of assigned lessons for the student.
     *
     * @return The list of assigned lessons.
     */
    public ArrayList<Lesson> getAssignedLessons() {
        return assignedLessons;
    }

    /**
     * Returns the teacher assigned to the student.
     *
     * @return The student's teacher.
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Sets the student's grade.
     *
     * @param grade The new grade to set.
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Sets the student's assignments.
     *
     * @param assignments The list of assignments to set.
     */
    public void setAssignment(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    /**
     * Sets the student's completed assignments.
     *
     * @param completedAssignments The list of completed assignments to set.
     */
    public void setCompletedAssignments(ArrayList<Assignment> completedAssignments) {
        this.completedAssignments = completedAssignments;
    }

    /**
     * Sets the student's assigned lessons.
     *
     * @param lessons The list of lessons to set.
     */
    public void setAssignedLessons(ArrayList<Lesson> lessons) {
        this.assignedLessons = lessons;
    }

    /**
     * Assigns a new lesson to the student.
     *
     * @param lesson The lesson to assign.
     */
    public void assignLesson(Lesson lesson) {
        assignedLessons.add(lesson);
    }

    /**
     * Adds a new assignment to the student's assignment list.
     *
     * @param assignment The assignment to add.
     */
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }
}
