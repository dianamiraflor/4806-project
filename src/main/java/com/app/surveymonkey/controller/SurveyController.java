package com.app.surveymonkey.controller;

import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.repositories.QuestionRepo;
import com.app.surveymonkey.repositories.ResponseRepo;
import com.app.surveymonkey.repositories.SurveyRepo;
import com.app.surveymonkey.repositories.SurveyorRepo;
import com.app.surveymonkey.responses.Response;
import com.app.surveymonkey.survey.Survey;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class SurveyController {

    @Autowired
    private SurveyRepo surveyRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private SurveyorRepo surveyorRepo;

    @Autowired
    private ResponseRepo responseRepo;

    @GetMapping("/newSurvey")
    public String newSurvey(Model model) {
        model.addAttribute("survey", new Survey());
        return "survey-create";
    }

    @PostMapping("/newSurvey")
    public String createSurvey(@ModelAttribute @Validated Survey survey) {
        survey.setOpen(true);
        survey.getQuestions().forEach(question -> question.setSurvey(survey));
        surveyRepo.save(survey);

        Survey newsurv = surveyRepo.findTopByOrderByIdDesc();

        return "redirect:/viewsurvey/" + newsurv.getId();
    }


    @GetMapping("/viewsurvey/{surveyId}")
    public String viewSurvey(@PathVariable("surveyId") int surveyId, Model model) {
        Survey survey = surveyRepo.findById(surveyId);
        model.addAttribute("survey", survey);
        List<Question.QuestionType> Types = Arrays.asList(Question.QuestionType.values());
        model.addAttribute("Types", Types);
        return "survey-view";
    }

    @PostMapping("/surveys/{surveyId}/addquestion")
    public String addQuestion(@PathVariable("surveyId") int surveyId, @ModelAttribute @Validated Question question) {
        Survey survey = surveyRepo.findById(surveyId);
        question.setSurvey(survey);
        questionRepo.save(question);
        return "redirect:/viewsurvey/" + surveyId;
    }

    @GetMapping("/surveys/fill/{surveyId}")
    public String fillSurvey(@PathVariable("surveyId") int surveyId, Model model){
        Survey survey = surveyRepo.findById(surveyId);

            Response responses = new Response();
            for (int i = 0; i < survey.getQuestions().size(); i++) {
                responses.addAnswer(" ");
            }
            responses.setSurvey(survey);
            model.addAttribute("survey", survey);
            model.addAttribute("responses", responses);
        if(survey.getOpen()) {
            return "survey-fill";
        }else{
            return "survey-closed";
        }
    }

    @PostMapping("/submit-survey/{surveyId}")
    public String submitsurvey(@PathVariable("surveyId") int surveyId,@ModelAttribute Response responses){
        Survey survey=surveyRepo.findById(surveyId);
        responses.setSurvey(survey);
        responses.setEndTime(LocalDateTime.now());
        survey.addResponse(responses);
        surveyRepo.save(survey);
        return "redirect:/viewsurvey/"+ surveyId;
    }

    @GetMapping("/surveys/results/{surveyId}")
    public String getresults(@PathVariable("surveyId") int surveyId,Model model){
        Survey survey=surveyRepo.findById(surveyId);
        model.addAttribute("survey",survey);
        return("survey-result");
    }

    @GetMapping("/surveys/close/{surveyId}")
    public String closeSurvey(@PathVariable("surveyId") int surveyId,Model model){
        Survey survey=surveyRepo.findById(surveyId);
        model.addAttribute("survey",survey);
        survey.setOpen(false);
        surveyRepo.save(survey);
        return("survey-closed");
    }




    @GetMapping("/home")
    public String homePage() {
        return "HomePage";
    }

    @GetMapping("/availableSurveys")
    public String availableSurveys(Model model) {
        Iterable<Survey> surveys = surveyRepo.findAll();
        model.addAttribute("surveys", surveys);
        return "view-surveys";
    }
}