package com.model;

import java.util.ArrayList;

public class Question{
    private String question;
    ArrayList<String> choices;
    private int answer;
    private int userChoice;
    
    public Question(String question, ArrayList<String> choices, int answer){
        this.question = question;
        this.choices = choices;
        this.answer = answer;
        this.userChoice = -1;
    }

    public String getQuestion(){
        return question;
    }

    public ArrayList<String> getChoices(){
        return choices;
    }

    public int getAnswer(){
        return answer;
    }

    public int getUserChoice(){
        return userChoice;
    }

    public void setUserChoice(int choice) {
        this.userChoice = choice;
    }

    public boolean isCorrect() {
        return userChoice == answer;
    }

    public void displayQuestion() {
        System.out.println(question);
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i));
        }
    }
}