package com.model;

import java.util.ArrayList;

public class Question{
    private String question;
    ArrayList<String> choices;
    private int answer;
    private int userChoice;
    private boolean Correctness;

    public boolean isCorrect(){
        return Correctness;
    }
}