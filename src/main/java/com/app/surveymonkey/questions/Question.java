package com.app.surveymonkey.questions;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Question implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String question;

    public Question() {
        this.question = null;
    }

    // ----------------- GETTERS & SETTERS -------------------

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String toString() {
        String questionString = "Question: " + getQuestion() + " \n";
        return questionString;
    }
}
