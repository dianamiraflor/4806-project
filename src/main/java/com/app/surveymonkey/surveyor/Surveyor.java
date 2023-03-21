package com.app.surveymonkey.surveyor;

import com.app.surveymonkey.survey.Survey;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.HashSet;

@Entity
public class Surveyor {

    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "Surveyor name cannot be null")
    private String name;

    @NotNull(message = "Surveyor username cannot be null")
    private String username;

    @NotNull(message = "Surveyor password cannot be null")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Survey> surveyList;

    public Surveyor() {
        this.surveyList = new HashSet();
    }

    public Surveyor(int id, String name, String username, String password) {
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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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
