package com.app.surveymonkey.survey;

import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.responses.Response;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

@Entity
public class Survey implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }


    private Boolean open;

    @NotNull(message = "Survey name cannot be null")
    private String surveyName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Question> questions;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Response> responses;

    public Survey() {
        this.questions = new HashSet();
        this.responses = new HashSet();
        this.open = false;
    }

    public Survey(int id) {
        this.questions = new HashSet();
        this.responses = new HashSet();
        this.id = id;
        this.open=false;
    }

    public void addQuestion(Question question) {
        if (question != null) {
            questions.add(question);
        }
    }
    
    public void addResponse(Response response) {
        if(response != null) {
            
            responses.add(response);
        }
    }


    public void removeQuestion(Question question) {
        Iterator<Question> questionIterator = questions.iterator();
        while (questionIterator.hasNext()) {
            if (questionIterator.next().getId() == question.getId()) {
                questionIterator.remove();
            }
        }
    }

    // ----------------- GETTERS & SETTERS -------------------
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurveyName() {
        return this.surveyName;
    }

    public void setSurveyName(String name) {
        this.surveyName = name;
    }

    public Collection<Question> getQuestions() {
        return this.questions;
    }

    public Collection<Response> getResponses(){
        return this.responses;
    }

    @Override
    public String toString() {
        String surveyString = "Survey ID: " + id + "\n" + "Survey Name: " + surveyName + "\n";
        for (Question question : questions) {
            surveyString = surveyString + question.toString() + "\n\n";
        }
        return surveyString;
    }

}
