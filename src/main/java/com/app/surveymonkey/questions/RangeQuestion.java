package com.app.surveymonkey.questions;

import java.util.Collection;
import java.util.HashSet;

public class RangeQuestion extends Question {
    private Collection<Integer> rangeList;
    private int answer;

    private int min;
    private int max;

    public RangeQuestion() {

        this.rangeList = new HashSet();
        this.min = 0;
        this.max = 0;
        this.answer = 0;
    }

    // ----------------- GETTERS & SETTERS -------------------
    public int getMin() {
        return this.min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    public Collection<Integer> getRangeList() {
        return this.rangeList;
    }

    public void createRangeList() {
        if (min > 0 && min < max) {
            for (int i = min; i <= max; i++) {
                rangeList.add(i);
            }
        }
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        String questionString = "Question: " + super.getQuestion() + " \n";
        questionString = questionString + rangeList;

        return questionString;
    }
}
