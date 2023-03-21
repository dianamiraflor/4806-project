package com.app.surveymonkey.responses;
import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.questions.RangeQuestion;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class RangeResponse extends Response {
    private int answer;

    public RangeResponse(){
        this.QType = "RQ";
    }

    public RangeResponse(int answer, Question question) {
        this.answer = answer;
        this.question = question;
        this.QType = "RQ";
    }

    public int getAnswer() {
        return this.answer;
    }

    public void setAnswer(int answer) {
        this.answer =  answer;
    }

    @Override
    public String toString() {
        String response = QType + ": " + question.toString() + "\n";
        response = response + "Answer: " + answer;
        return response;
    }
}
