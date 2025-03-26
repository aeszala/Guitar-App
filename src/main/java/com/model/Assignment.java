/**
 * @author Liamnp
 */

package com.model;

import org.json.simple.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Assignment class represents an assignment with details such as title, grade, comments, due date, and completion status.
 */
public class Assignment {

    private String title;
    private String type;
    private Student student;
    private double grade;
    private String teacherComment;
    private String studentComment;
    private Date dueDate;
    private boolean complete;

    /**
     * Constructs an Assignment with all attributes.
     * 
     * @param title          The title of the assignment.
     * @param grade          The grade received for the assignment.
     * @param teacherComment The comment from the teacher regarding the assignment.
     * @param studentComment The comment from the student regarding the assignment.
     * @param dueDate        The due date of the assignment.
     * @param complete       Whether the assignment is complete or not.
     */
    public Assignment(String title, double grade, String teacherComment, String studentComment, Date dueDate, boolean complete) {
        this.title = title;
        this.grade = grade;
        this.teacherComment = teacherComment;
        this.studentComment = studentComment;
        this.dueDate = dueDate;
        this.complete = complete;
    }

    /**
     * Constructs an Assignment with a title, teacher comment, and due date. Grade and student comment are not provided.
     * 
     * @param title          The title of the assignment.
     * @param teacherComment The teacher's comment for the assignment.
     * @param dueDate        The due date of the assignment.
     */
    public Assignment(String title, String teacherComment, Date dueDate) {
        this.title = title;
        this.grade = -1; // No grade yet
        this.teacherComment = teacherComment;
        this.studentComment = null;
        this.dueDate = dueDate;
        this.complete = false;
    }

    /**
     * Converts the assignment data to a JSON object.
     * 
     * @return A JSONObject representing the assignment.
     */
    public JSONObject toJson() {
        JSONObject assignmentObject = new JSONObject();
        assignmentObject.put("grade", grade);
        assignmentObject.put("teacherComment", teacherComment);
        assignmentObject.put("studentComment", studentComment);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assignmentObject.put("dueDate", dateFormat.format(dueDate));

        assignmentObject.put("complete", complete);

        return assignmentObject;
    }

    /**
     * Marks the assignment as complete.
     */
    public void complete() {
        this.complete = true;
    }

    /**
     * Gets the grade of the assignment.
     * 
     * @return The grade of the assignment.
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Sets the grade of the assignment.
     * 
     * @param grade The grade to assign to the assignment.
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Gets the teacher's comment on the assignment.
     * 
     * @return The teacher's comment.
     */
    public String getTeacherComment() {
        return teacherComment;
    }

    /**
     * Sets the teacher's comment for the assignment.
     * 
     * @param teacherComment The teacher's comment to be set.
     */
    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    /**
     * Gets the student's comment on the assignment.
     * 
     * @return The student's comment.
     */
    public String getStudentComment() {
        return studentComment;
    }

    /**
     * Sets the student's comment for the assignment.
     * 
     * @param studentComment The student's comment to be set.
     */
    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    /**
     * Gets the due date of the assignment.
     * 
     * @return The due date of the assignment.
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the assignment.
     * 
     * @param dueDate The new due date to be set.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the title of the assignment.
     * 
     * @return The title of the assignment.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the assignment.
     * 
     * @param title The new title to be set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Checks if the assignment is complete.
     * 
     * @return true if the assignment is complete, false otherwise.
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * Sets the completion status of the assignment.
     * 
     * @param complete The completion status to be set.
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    /**
     * Adds a comment to the assignment. The comment can be from either a teacher or a student.
     * 
     * @param comment The comment to be added.
     * @param role    The role of the person adding the comment. It must be "teacher" or "student".
     */
    public void addComment(String comment, String role) {
        if ("teacher".equalsIgnoreCase(role)) {
            this.teacherComment = (teacherComment == null || teacherComment.isEmpty()) 
                    ? comment 
                    : teacherComment + " | " + comment;
        } else if ("student".equalsIgnoreCase(role)) {
            this.studentComment = (studentComment == null || studentComment.isEmpty()) 
                    ? comment 
                    : studentComment + " | " + comment;
        } else {
            System.out.println("Invalid role. Please use 'teacher' or 'student'.");
        }
    }

    public void Assignment(String title, String type, Date dueDate, Student student){
        this.title = title;
        this.type = type;
        this.dueDate = dueDate;
        this.student = student;
    }

    /**
     * Returns a string representation of the assignment.
     * 
     * @return A formatted string containing the assignment details.
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        return "Assignment {" +
                "Title='" + title + '\'' +
                ", Grade=" + (grade >= 0 ? grade : "No grade") +
                ", Teacher Comment='" + (teacherComment != null ? teacherComment : "None") + '\'' +
                ", Student Comment='" + (studentComment != null ? studentComment : "None") + '\'' +
                ", Due Date=" + (dueDate != null ? dateFormat.format(dueDate) : "No due date") +
                ", Complete=" + complete +
                '}';
    }
}
