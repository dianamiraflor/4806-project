package com.app.surveymonkey.responses;

import com.app.surveymonkey.questions.Question;

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
