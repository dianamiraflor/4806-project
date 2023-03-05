package com.app.surveymonkey.questions;

import java.util.Collection;
import java.util.HashSet;

public class MultipleChoiceQuestion extends Question {
    private Collection<String> choices;
    private int limit;
    private String answer;
    public MultipleChoiceQuestion() {
        this.choices = new HashSet();
        this.limit = 0;
        this.answer = null;
    }

    // ----------------- GETTERS & SETTERS -------------------

    public Collection<String> getChoices() {
        return this.choices;
    }

    public void addChoice(String choice) {
        if (choice != null && choices.size() < getLimit()) {
            choices.add(choice);
        }
    }

    public int getLimit(){
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        String questionString = "Question: " + super.getQuestion() + " \n";

        for (String choice : choices) {
            questionString = questionString + choice + "\n";
        }

        return questionString;
    }
}
