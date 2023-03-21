package com.app.surveymonkey.questions;

import jakarta.persistence.*;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class MultipleChoiceQuestion extends Question {

    @ElementCollection
    private List<String> choices;

    @Min(2)
    private int choices_limit;

    public MultipleChoiceQuestion() {
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

    @Override
    public String toString() {
        String questionString = "Question: " + super.getQuestionText() + " \n";

        for (String choice : choices) {
            questionString = questionString + choice + "\n";
        }

        return questionString;
    }
}
