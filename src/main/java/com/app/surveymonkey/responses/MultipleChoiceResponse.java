package com.app.surveymonkey.responses;

import com.app.surveymonkey.questions.Question;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class MultipleChoiceResponse extends Response{

    private String answer;

    public MultipleChoiceResponse() {
        this.QType = "MCQ";
    }

    public MultipleChoiceResponse(String answer, Question question) {
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

    @Override
    public String toString() {
        String response = QType + ": " + question.toString() + "\n";
        response = response + "Answer: " + answer;
        return response;
    }
}
