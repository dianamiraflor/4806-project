package com.app.surveymonkey.questions;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class RangeQuestion extends Question {

    @ElementCollection
    private List<Integer> rangeList;
    private int answer;

    private int min;
    private int max;

    public RangeQuestion() {
        this.rangeList = new ArrayList<>();
        this.min = 0;
        this.max = 0;
        this.answer = 0;
        this.QType = "RQ";
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
    public List<Integer> getRangeList() {
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
        String questionString = "Question: " + super.getQuestionText() + " \n";
        questionString = questionString + rangeList;

        return questionString;
    }
}
