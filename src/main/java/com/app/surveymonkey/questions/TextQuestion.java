package com.app.surveymonkey.questions;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class TextQuestion extends Question {

    public TextQuestion() {
        this.QType="TQ";
    }

    // ----------------- GETTERS & SETTERS -------------------

    @Override
    public String toString(){
        String questionString = "Question: " + super.getQuestionText() + " \n";
        return questionString;
    }
}
