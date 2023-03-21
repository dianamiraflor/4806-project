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

import java.util.Optional;

@Controller
public class SurveyController {

    @Autowired
    private SurveyRepo surveyRepo;

    @Autowired
    private QuestionRepo QuestionRepo;

    @Autowired
    private SurveyorRepo surveyorRepo;

    @GetMapping("/newSurvey")
    public String newSurvey(Model model) {
        model.addAttribute("survey", new Survey());
        return "survey-initialize";
    }

    @PostMapping("/newSurvey")
    public String startAdding(@ModelAttribute @Validated Survey survey) {
        survey.setOpen(true);
        surveyRepo.save(survey);
        return "redirect:/surveys/" + survey.getId();
    }

    @GetMapping("/surveys/{surveyID}")
    public String addQuestion(@PathVariable("surveyID") int surveyID, Model model) {
        Survey survey = surveyRepo.findById(surveyID);
        model.addAttribute("survey", survey);
        model.addAttribute("id", surveyID);
        model.addAttribute("NewQuestion", new Question());
        return "survey-create";
    }

    @PostMapping("/surveys/{surveyID}")
    public String newQuestion(@ModelAttribute @Validated Question question, @PathVariable("surveyID") int surveyID, Model model) {
        Survey survey = surveyRepo.findById(surveyID);
        survey.addQuestion(question);
        surveyRepo.save(survey);
        model.addAttribute("survey", survey);
        model.addAttribute("id", surveyID);
        model.addAttribute("NewQuestion", new Question());
        return "survey-create";
    }

    @GetMapping("/surveys/{surveyID}/delete/{questionID}")
    public String deleteQuestion(@PathVariable("surveyID") int surveyID, @PathVariable("questionID") int questionID, Model model) {
        QuestionRepo questionRepo = new QuestionRepo() {
            @Override
            public <S extends Question> S save(S entity) {
                return null;
            }

            @Override
            public <S extends Question> Iterable<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public Optional<Question> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public Iterable<Question> findAll() {
                return null;
            }

            @Override
            public Iterable<Question> findAllById(Iterable<Integer> integers) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Question entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> integers) {

            }

            @Override
            public void deleteAll(Iterable<? extends Question> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public Question findById(int id) {
                return null;
            }
        };
        Question question = questionRepo.findById(questionID);
        Survey survey = surveyRepo.findById(surveyID);
        survey.removeQuestion(question);
        surveyRepo.save(survey);
        questionRepo.delete(question);
        model.addAttribute("survey", survey);
        model.addAttribute("id", surveyID);
        model.addAttribute("NewQuestion", new Question());
        return "survey-create";
    }

    @PostMapping("/savesurvey")
    public String saveSurvey(@ModelAttribute @Validated Survey survey) {
        surveyRepo.save(survey);
        return "survey-initialize";
    }

    @GetMapping("/viewsurvey/{surveyId}")
    public String viewSurvey(@PathVariable("surveyId") int surveyId, Model model) {
        Survey survey = surveyRepo.findById(surveyId);
        model.addAttribute("survey", survey);
        return "survey-view";
    }
    @GetMapping("/home")
    public String homePage() {
        return "HomePage";
    }

    @GetMapping("/availableSurveys")
    public String availableSurveys(Model model){
        Iterable<Survey> surveys = surveyRepo.findAll();
        model.addAttribute("surveys", surveys);
        return "view-surveys";
    }
}
