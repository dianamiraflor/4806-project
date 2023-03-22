package com.app.surveymonkey.responses;



import com.app.surveymonkey.survey.Survey;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
    @ElementCollection
    private List<String> answers = new ArrayList<String>();

    public Response(Survey survey){
        this.startTime = LocalDateTime.now();
        this.survey=survey;
    }

    public Response() {
        this.startTime = LocalDateTime.now();
    }


    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public Survey getSurvey(){
        return survey;
    }

    public void setSurvey(Survey survey){
        this.survey = survey;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void addAnswer(String answer){
        answers.add(answer);
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
    public void setEndTime(LocalDateTime time){
        this.endTime = time;
    }


}
