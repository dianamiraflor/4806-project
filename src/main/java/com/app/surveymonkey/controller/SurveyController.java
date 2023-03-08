package com.app.surveymonkey.controller;


import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.repositories.QuestionRepo;
import com.app.surveymonkey.repositories.SurveyRepo;

import com.app.surveymonkey.survey.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@Controller
public class SurveyController {

    @Autowired SurveyRepo surveyrepo;
    @Autowired QuestionRepo questionRepo;



    @GetMapping("/newSurvey")
    public String newSurvey(Model model){
        model.addAttribute("survey", new Survey());
        return "survey-initialize";
    }
    @PostMapping("/newSurvey")
    public String startAdding(@ModelAttribute Survey survey){
        surveyrepo.save(survey);
        int id = surveyrepo.findTopByOrderByIdDesc().getId();
        return ("redirect:/surveys/"+id);
    }



    @GetMapping("/surveys/{surveyID}")
    public String addQuestion(@PathVariable("surveyID") int surveyID, Model model){
        Survey survey = surveyrepo.findById(surveyID);
        model.addAttribute("survey",survey);
        model.addAttribute("id",surveyID);
        model.addAttribute("NewQuestion", new Question());
    return "survey-create";
    }


    @PostMapping("/surveys/{surveyID}")
    public String newQuestion(@ModelAttribute Question Questions, @PathVariable("surveyID") int surveyID, Model model){
        questionRepo.save(Questions);
        Survey survey = surveyrepo.findById(surveyID);
        survey.addQuestion(Questions);
        surveyrepo.save(survey);
        model.addAttribute("survey",survey);
        model.addAttribute("id",surveyID);
        model.addAttribute("NewQuestion", new Question());
        return "survey-create";
    }

    @GetMapping("/surveys/{surveyID}/delete/{questionID}")
    public String deleteQuestion(@PathVariable("surveyID") int surveyID,@PathVariable("questionID") int questionID, Model model){
        Question tempQ = questionRepo.findById(questionID);
        Survey survey = surveyrepo.findById(surveyID);
        survey.removeQuestion(tempQ);
        surveyrepo.save(survey);
        questionRepo.delete(tempQ);
        model.addAttribute("survey",survey);
        model.addAttribute("id",surveyID);
        model.addAttribute("NewQuestion", new Question());
        return "survey-create";
    }

    @PostMapping("/savesurvey/{surveyId}")
    public String saveSurvey(@PathVariable("surveyId") int surveyId) {
        Survey survey =surveyrepo.findById(surveyId);
        survey.setOpen(true);
    return "redirect:/viewsurvey/"+surveyId;
    }

    @GetMapping("/viewsurvey/{surveyId}")
    public String viewSurvey(@PathVariable("surveyId") int surveyId, Model model){
        Survey survey = surveyrepo.findById(surveyId);
        model.addAttribute("survey", survey);
        return "survey-view";
    }

}
