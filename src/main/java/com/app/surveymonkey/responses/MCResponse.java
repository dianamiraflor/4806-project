package com.app.surveymonkey.responses;

import com.app.surveymonkey.questions.Question;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class MCResponse extends Response{

    private String answer;

    public MCResponse() {
        this.QType = "MCQ";
    }

    public MCResponse(String answer, Question question) {
        this.answer = answer;
        this.question = question;
        this.QType = "MCQ";
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return this.answer;
    }
}
