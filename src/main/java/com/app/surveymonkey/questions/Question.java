package com.app.surveymonkey.questions;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public class Question implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String question;

    public String getQType() {
        return Qtype;
    }

    public void setQType(String Qtype) {
        this.Qtype = Qtype;
    }

    protected String Qtype;

    public Question() {
        this.question = null;
    }

    // ----------------- GETTERS & SETTERS -------------------

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String toString() {
        String questionString = "Question: " + getQuestion() + " \n";
        return questionString;
    }
}
