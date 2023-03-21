package com.app.surveymonkey.controller;

import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.repositories.QuestionRepo;
import com.app.surveymonkey.repositories.SurveyRepo;
import com.app.surveymonkey.repositories.SurveyorRepo;
import com.app.surveymonkey.survey.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class SurveyController {

    @Autowired
    private SurveyRepo surveyRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private SurveyorRepo surveyorRepo;

    @GetMapping("/newSurvey")
    public String newSurvey(Model model) {
        model.addAttribute("survey", new Survey());
        return "create-survey";
    }

    @PostMapping("/survey/create")
    public String createSurvey(@ModelAttribute @Validated Survey survey) {
        survey.setOpen(true);
        survey.getQuestions().forEach(question -> question.setSurvey(survey));
        surveyRepo.save(survey);
        return "redirect:/viewsurvey/" + survey.getId();
    }

    @GetMapping("/viewsurvey/{surveyId}")
    public String viewSurvey(@PathVariable("surveyId") int surveyId, Model model) {
        Survey survey = surveyRepo.findById(surveyId);
        model.addAttribute("survey", survey);
        model.addAttribute("NewQuestion", new Question());
        return "survey-view";
    }

    @PostMapping("/surveys/{surveyId}/addquestion")
    public String addQuestion(@PathVariable("surveyId") int surveyId, @ModelAttribute @Validated Question question) {
        Survey survey = surveyRepo.findById(surveyId);
        question.setSurvey(survey);
        questionRepo.save(question);
        return "redirect:/viewsurvey/" + surveyId;
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