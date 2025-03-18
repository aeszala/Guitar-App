package com.model;

import java.util.ArrayList;

public class QuestionAssignment {
    ArrayList<Question> questions;

    public QuestionAssignment() {
        this.questions = new ArrayList<>();
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
        return correctCount;
    }
}