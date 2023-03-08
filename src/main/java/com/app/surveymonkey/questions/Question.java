package com.app.surveymonkey.questions;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table
public class Question implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String questionText;


    protected String QType;

    public Question() {
        this.questionText = null;
    }

    // ----------------- GETTERS & SETTERS -------------------

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String question) {
        this.questionText = question;
    }

    public String getQType() {
        return QType;
    }

    public void setQType(String QType) {
        this.QType = QType;
    }

    public String toString() {
        String questionString = "Question: " + getQuestionText() + " \n";
        return questionString;
    }
}
