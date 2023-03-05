package com.app.surveymonkey.questions;

public class TextQuestion extends Question {
    private String answer;

    public TextQuestion() {
        this.answer = null;
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
        String questionString = "Question: " + super.getQuestion() + " \n";
        return questionString;
    }
}
