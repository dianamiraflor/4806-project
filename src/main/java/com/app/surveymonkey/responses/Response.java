package com.app.surveymonkey.responses;

import com.app.surveymonkey.questions.Question;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table
public class Response {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    protected String QType;

    @ManyToOne(cascade =  CascadeType.ALL)
    @NotNull
    protected Question question;

    public Response() {
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question)  {
        this.question = question;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQType(String QType) {
        this.QType = QType;
    }

    public String getQType() {
        return this.QType;
    }

    public String toString() {
        String response = QType + ": " + question.toString() + "\n";
        return response;
    }
}
