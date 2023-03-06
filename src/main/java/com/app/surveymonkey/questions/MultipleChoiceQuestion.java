package com.app.surveymonkey.questions;

import jakarta.persistence.*;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@DiscriminatorValue(value = "MCQ")
public class MultipleChoiceQuestion extends Question {

    @ElementCollection
    private List<String> choices;
    private int limit;
    private String answer;
    public MultipleChoiceQuestion() {
        this.limit = 0;
        this.answer = null;
        this.choices = new ArrayList<>();
    }

    // ----------------- GETTERS & SETTERS -------------------
    public List<String> getChoices() {
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
