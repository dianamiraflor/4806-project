package com.app.surveymonkey.questions;

import jakarta.persistence.*;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class MultipleChoiceQuestion extends Question {

    @ElementCollection
    private List<String> choices;
    private int choices_limit;
    private String answer;
    public MultipleChoiceQuestion() {
        this.choices_limit = 0;
        this.answer = null;
        this.choices = new ArrayList<>();
        this.QType = "MCQ";
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
        return this.choices_limit;
    }

    public void setLimit(int limit) {
        this.choices_limit = limit;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        String questionString = "Question: " + super.getQuestionText() + " \n";

        for (String choice : choices) {
            questionString = questionString + choice + "\n";
        }

        return questionString;
    }
}
