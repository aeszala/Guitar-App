/**
 * @author liamnp
 */

package com.model;

import java.util.ArrayList;

/**
 * The {@code Question} class represents a multiple-choice question that includes the 
 * question text, a list of answer choices, and the correct answer.
 * 
 * <p>This class also tracks the user's selected answer and provides methods to check if 
 * the user's choice is correct and to display the question along with its choices.</p>
 */
public class Question {
    private String question;
    private ArrayList<String> choices;
    private int answer;
    private int userChoice;

    /**
     * Constructor for creating a new multiple-choice question.
     * 
     * @param question The question text.
     * @param choices A list of choices for the answer.
     * @param answer The index (1-based) of the correct answer in the choices list.
     */
    public Question(String question, ArrayList<String> choices, int answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
        this.userChoice = -1; // Default to no choice selected
    }

    /**
     * Gets the question text.
     * 
     * @return The question text.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets the list of answer choices.
     * 
     * @return The list of choices for the question.
     */
    public ArrayList<String> getChoices() {
        return choices;
    }

    /**
     * Gets the correct answer's index (1-based).
     * 
     * @return The index of the correct answer.
     */
    public int getAnswer() {
        return answer;
    }

    /**
     * Gets the user's selected answer.
     * 
     * @return The user's selected answer (index of choice).
     */
    public int getUserChoice() {
        return userChoice;
    }

    /**
     * Sets the user's selected answer.
     * 
     * @param choice The index (1-based) of the user's selected answer.
     */
    public void setUserChoice(int choice) {
        this.userChoice = choice;
    }

    /**
     * Checks if the user's choice is correct.
     * 
     * @return {@code true} if the user's choice matches the correct answer, {@code false} otherwise.
     */
    public boolean isCorrect() {
        return userChoice == answer;
    }

    /**
     * Displays the question and its choices.
     */
    public void displayQuestion() {
        System.out.println(question);
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i));
        }
    }
}
