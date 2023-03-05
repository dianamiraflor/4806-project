package com.app.surveymonkey.surveyor;

import com.app.surveymonkey.survey.Survey;

import java.util.Collection;
import java.util.HashSet;

public class Surveyor {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Collection<Survey> surveyList;

    public Surveyor() {
        this.surveyList = new HashSet();
    }

    public Surveyor(long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.surveyList = new HashSet();
    }

    public void addSurvey(Survey survey) {
        if (survey != null) {
            surveyList.add(survey);
        }
    }

    // ----------------- GETTERS & SETTERS -------------------

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Survey> getSurveyList() {
        return this.surveyList;
    }

}
