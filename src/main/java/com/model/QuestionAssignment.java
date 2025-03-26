/**
 * @author (name)
 */

package com.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * The {@code QuestionAssignment} class represents an assignment consisting of multiple-choice questions.
 * It extends the {@code Assignment} class and adds functionality specific to handling multiple-choice questions.
 * 
 * <p>This class provides methods for adding questions, answering questions, displaying questions, and calculating the score based on the correctness of the answers.</p>
 */
public class QuestionAssignment extends Assignment {
    private ArrayList<Question> questions;

    /**
     * Constructor for loading existing QuestionAssignments (with grade & completion status).
     * 
     * @param title The title of the assignment.
     * @param grade The grade received for the assignment.
     * @param teacherComment The teacher's comment on the assignment.
     * @param studentComment The student's comment on the assignment.
     * @param dueDate The due date of the assignment.
     * @param complete The completion status of the assignment.
     * @param questions A list of questions in the assignment.
     */
    public QuestionAssignment(String title, double grade, String teacherComment, String studentComment,
            Date dueDate, boolean complete, ArrayList<Question> questions) {
        super(title, grade, teacherComment, studentComment, dueDate, complete);
        this.questions = questions;
    }

    /**
     * Constructor for creating a new QuestionAssignment (without grade yet).
     * 
     * @param title The title of the assignment.
     * @param teacherComment The teacher's comment on the assignment.
     * @param dueDate The due date of the assignment.
     * @param questions A list of questions in the assignment.
     */
    public QuestionAssignment(String title, String teacherComment, Date dueDate, ArrayList<Question> questions) {
        super(title, teacherComment, dueDate);
        this.questions = questions;
    }

    /**
     * Adds a new question to the assignment.
     * 
     * @param q The question to be added.
     */
    public void addQuestion(Question q) {
        questions.add(q);
    }

    /**
     * Sets the user's answer to a specific question in the assignment.
     * 
     * @param questionIndex The index of the question to be answered (0-based).
     * @param answer The answer chosen by the user (index of the choice).
     */
    public void answerQuestion(int questionIndex, int answer) {
        if (questionIndex >= 0 && questionIndex < questions.size()) {
            questions.get(questionIndex).setUserChoice(answer);
        } else {
            System.out.println("Invalid question index.");
        }
    }

    /**
     * Displays all the questions and their choices in the assignment.
     */
    public void displayQuestions() {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Question " + (i + 1) + ":");
            questions.get(i).displayQuestion();
            System.out.println();
        }
    }

    /**
     * Calculates the score of the assignment based on the correctness of the answers.
     * 
     * @return The score of the assignment as a percentage (0-100).
     */
    public int getScore() {
        int correctCount = 0;
        for (Question q : questions) {
            if (q.isCorrect()) {
                correctCount++;
            }
        }
        return correctCount * 100 / questions.size(); // Return percentage score
    }
}
