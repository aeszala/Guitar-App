package com.model;

import org.json.simple.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Assignment {
    private double grade;
    private String teacherComment;
    private String studentComment;
    private Date dueDate;
    private boolean complete;

// Constructor
public Assignment(double grade, String teacherComment, String studentComment, Date dueDate, boolean complete) {
    this.grade = grade;
    this.teacherComment = teacherComment;
    this.studentComment = studentComment;
    this.dueDate = dueDate;
    this.complete = complete;
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

public double grade()
{
    return grade;
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

public boolean isComplete()
{
    return complete;
}

public void setComplete(boolean complete)
{
    this.complete = complete;
}

}
