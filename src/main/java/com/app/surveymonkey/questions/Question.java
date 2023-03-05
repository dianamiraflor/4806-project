package com.app.surveymonkey.questions;

public class Question {
    private Long id;
    private String question;

    public Question() {
        this.question = null;
    }

    // ----------------- GETTERS & SETTERS -------------------

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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
