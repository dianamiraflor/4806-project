package com.app.surveymonkey.questions;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class TextQuestion extends Question {
    private String answer;

    public TextQuestion() {
        this.answer = null;
        this.QType="TQ";
    }

    // ----------------- GETTERS & SETTERS -------------------

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString(){
        String questionString = "Question: " + super.getQuestionText() + " \n";
        return questionString;
    }
}