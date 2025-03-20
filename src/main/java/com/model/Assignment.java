package com.model;

import org.json.simple.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Assignment {
    private String title;
    private double grade;
    private String teacherComment;
    private String studentComment;
    private Date dueDate;
    private boolean complete;

// Constructor
public Assignment(String title, double grade, String teacherComment, String studentComment, Date dueDate, boolean complete) {
    this.title = title;
    this.grade = grade;
    this.teacherComment = teacherComment;
    this.studentComment = studentComment;
    this.dueDate = dueDate;
    this.complete = complete;
}

public Assignment(String title, String teacherComment, Date dueDate) {
    this.title = title;
    this.grade = -1;  // no grade yet
    this.teacherComment = teacherComment;
    this.studentComment = null;
    this.dueDate = dueDate;
    this.complete = false;
}

public JSONObject toJson() {
    JSONObject assignmentObject = new JSONObject();
    assignmentObject.put("grade", grade);
    assignmentObject.put("teacherComment", teacherComment);
    assignmentObject.put("studentComment", studentComment);

    // Format the date to a readable string
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    assignmentObject.put("dueDate", dateFormat.format(dueDate));

    assignmentObject.put("complete", complete);

    return assignmentObject;
}

public void complete()
{
    this.complete = true;
}

public double getGrade()
{
    return grade;
}

public void setGrade(double grade)
{
    this.grade = grade;
}

public String getTeacherComment()
{
    return teacherComment;
}

public void setTeacherComment(String teacherComment)
{
    this.teacherComment = teacherComment;
}

public String getStudentComment()
{
    return studentComment;
}

public void setStudentComment(String studentComment)
{
    this.studentComment = studentComment;
}

public Date getDueDate()
{
    return dueDate;
}

public void setDueDate(Date dueDate)
{
    this.dueDate = dueDate;
}

public String getTitle(){
    return title;
}

public void setTitle(String title){
    this.title = title;
}

public boolean isComplete()
{
    return complete;
}

public void setComplete(boolean complete)
{
    this.complete = complete;
}


public void addComment(String comment, String role) {
    if ("teacher".equalsIgnoreCase(role)) {
        this.teacherComment = (teacherComment == null || teacherComment.isEmpty()) ? comment : teacherComment + " | " + comment;
    } else if ("student".equalsIgnoreCase(role)) {
        this.studentComment = (studentComment == null || studentComment.isEmpty()) ? comment : studentComment + " | " + comment;
    } else {
        System.out.println("Invalid role. Please use 'teacher' or 'student'.");
    }
}

@Override
public String toString() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); // Format the due date

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
