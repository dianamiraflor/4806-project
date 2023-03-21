package com.app.surveymonkey.responses;
import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.questions.RangeQuestion;

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
        return answer;
    }

    public boolean setAnswer(int answer) {
        RangeQuestion r = (RangeQuestion) question;
        if(answer < r.getMin() || answer > r.getMax() ) {
            return false;
        }else {
            this.answer = answer;
            return true;
        }

    }
}
