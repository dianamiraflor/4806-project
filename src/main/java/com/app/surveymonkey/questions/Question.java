package com.app.surveymonkey.questions;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import com.app.surveymonkey.survey.Survey;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table
public class Question implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "Question text cannot be null")
    private String questionText;

    protected String QType;

    @ManyToOne(cascade =  CascadeType.ALL)
    private Survey survey;


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

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Survey getSurvey() {
        return this.survey;
    }

    public String toString() {
        String questionString = "Question: " + getQuestionText() + " \n";
        return questionString;
    }
}
