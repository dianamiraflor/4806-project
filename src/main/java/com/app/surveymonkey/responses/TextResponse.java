package com.app.surveymonkey.responses;

import com.app.surveymonkey.questions.Question;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class TextResponse extends Response {
    private String answer;

    public TextResponse(){
        this.QType = "TQ";
    }

    public TextResponse(String answer, Question question) {
        this.answer = answer;
        this.question = question;
        this.QType = "TQ";
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
