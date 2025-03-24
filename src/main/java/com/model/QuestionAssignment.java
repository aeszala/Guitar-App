package com.model;

import java.util.ArrayList;
import java.util.Date;

public class QuestionAssignment extends Assignment {
    ArrayList<Question> questions;

    // Constructor for loading existing QuestionAssignments
    public QuestionAssignment(String title, double grade, String teacherComment, String studentComment,
            Date dueDate, boolean complete, ArrayList<Question> questions) {
        super(title, grade, teacherComment, studentComment, dueDate, complete);
        this.questions = questions;
    }

    // Constructor for new QuestionAssignments
    public QuestionAssignment(String title, String teacherComment, Date dueDate, ArrayList<Question> questions) {
        super(title, teacherComment, dueDate);
        this.questions = questions;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public void answerQuestion(int questionIndex, int answer) {
        if (questionIndex >= 0 && questionIndex < questions.size()) {
            questions.get(questionIndex).setUserChoice(answer);
        } else {
            System.out.println("Invalid question index.");
        }
    }

    public void displayQuestions() {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Question " + (i + 1) + ":");
            questions.get(i).displayQuestion();
            System.out.println();
        }
    }

    public int getScore() {
        int correctCount = 0;
        for (Question q : questions) {
            if (q.isCorrect()) {
                correctCount++;
            }
        }
        return correctCount / questions.size() * 100;
    }
}