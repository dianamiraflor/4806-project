package com.app.surveymonkey.controller;

import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.repositories.QuestionRepo;
import com.app.surveymonkey.repositories.SurveyRepo;
import com.app.surveymonkey.repositories.SurveyorRepo;
import com.app.surveymonkey.survey.Survey;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

@Controller
public class SurveyController {

    @Autowired
    private SurveyRepo surveyRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private SurveyorRepo surveyorRepo;

    @PostMapping("/surveys/{surveyId}")
    public String createQuestions(@PathVariable("surveyId") int surveyId, @ModelAttribute @Validated Survey survey, HttpServletRequest request) {
        Survey dbSurvey = surveyRepo.findById(surveyId);
        Collection<Question> questions = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("questions[")) {
                String[] questionData = request.getParameterValues(paramName);
                String text = questionData[0];
                Question.QuestionType type = Question.QuestionType.valueOf(questionData[1]);
                Question question = new Question(text, type);
                if (type == Question.QuestionType.CHOICE) {
                    for (int i = 2; i < questionData.length; i++) {
                        String choiceText = questionData[i];
                        if (!choiceText.isEmpty()) {
                            question.addChoice(choiceText);
                        }
                    }
                } else if (type == Question.QuestionType.RANGE) {
                    String rangeMinStr = questionData[2];
                    String rangeMaxStr = questionData[3];
                    if (!rangeMinStr.isEmpty() && !rangeMaxStr.isEmpty()) {
                        int rangeMin = Integer.parseInt(rangeMinStr);
                        int rangeMax = Integer.parseInt(rangeMaxStr);
                        question.setRangeMin(rangeMin);
                        question.setRangeMax(rangeMax);
                    }
                }
                questions.add(question);
            }
        }
        dbSurvey.addQuestions(questions);
        for (Question question : dbSurvey.getQuestions()) {
            question.setSurvey(dbSurvey);
            questionRepo.save(question);
        }
        surveyRepo.save(dbSurvey);
        return "redirect:/viewsurvey/" + surveyId;
    }

    @GetMapping("/newSurvey")
    public String newSurvey(Model model) {
        model.addAttribute("survey", new Survey());
        return "create-survey";
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
