package com.app.surveymonkey.controller;

import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.questions.QuestionDTO;
import com.app.surveymonkey.questions.QuestionType;
import com.app.surveymonkey.repositories.QuestionRepo;
import com.app.surveymonkey.repositories.ResponseRepo;
import com.app.surveymonkey.repositories.SurveyRepo;
import com.app.surveymonkey.repositories.SurveyorRepo;
import com.app.surveymonkey.responses.Response;
import com.app.surveymonkey.survey.Survey;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    Logger logger = LoggerFactory.getLogger(SurveyController.class);

    @GetMapping("/newSurvey")
    public String newSurvey(Model model){
        logger.info("New survey initialized");
        model.addAttribute("survey", new Survey());
        return "survey-initialize";
    }
    @PostMapping("/newSurvey")
    public String startAdding(@ModelAttribute Survey survey){
        logger.info("Starting to add questions");
        surveyRepo.save(survey);
        int id = surveyRepo.findTopByOrderByIdDesc().getId();
        return ("redirect:/surveys/"+id);
    }

    @GetMapping("/surveys/{surveyID}")
    public String addQuestion(@PathVariable("surveyID") int surveyID, Model model){
        Survey survey = surveyRepo.findById(surveyID);
        model.addAttribute("survey",survey);
        model.addAttribute("id",surveyID);
        return "question-create";
    }

    @PostMapping("/surveys/create/{surveyID}")
    public @ResponseBody String newQuestion(
            @RequestBody QuestionDTO questionDTO,
            @PathVariable("surveyID") int surveyID){

        JSONObject response = new JSONObject();

        logger.info("Adding new question");
        Survey survey = surveyRepo.findById(surveyID);

        Question question = new Question();
        String text = questionDTO.getPrompt();
        String type = questionDTO.getType();
        logger.warn(text);
        logger.warn(type);

        question.setText(questionDTO.getPrompt());
        QuestionType questionType = null;

        if (type.equals("CHOICE")) {
            questionType = QuestionType.CHOICE;
            question.setType(questionType);

            for (String c : questionDTO.getChoicesList()) {
                logger.info(c);
            }

            question.setChoices(questionDTO.getChoicesList());

            response.put("text", question.getText());
            response.put("type", question.getType());
            response.put("choices", question.getChoices());

            logger.info(question.getText());
            logger.info(question.getType().name());

            for (String c: question.getChoices()) {
                logger.info(c);
            }
        }
        if (type.equals("RANGE")) {
            logger.info("HERE");
            questionType = QuestionType.RANGE;
            question.setType(questionType);
            question.setRangeMin(questionDTO.getMin());
            question.setRangeMax(questionDTO.getMax());

            response.put("text", question.getText());
            response.put("type", question.getType());
            response.put("min", question.getRangeMin());
            response.put("max", question.getRangeMax());

            logger.info(question.getText());
            logger.info(question.getType().name());
            logger.info(Integer.toString(question.getRangeMin()));
            logger.info(Integer.toString(question.getRangeMax()));
        }
        if (type.equals("TEXT")){
            questionType = QuestionType.TEXT;
            question.setType(questionType);

            response.put("text", question.getText());
            response.put("type", question.getType());

            logger.info(question.getText());
            logger.info(question.getType().name());

        }

        questionRepo.save(question);
        survey.addQuestion(question);
        surveyRepo.save(survey);

        // return questionDTO;
        return response.toString();
    }

    @GetMapping("/surveys/create/view/{surveyID}")
    public String viewCreatedQuestions(@PathVariable("surveyID") int surveyID, Model model){
        Survey survey = surveyRepo.findById(surveyID);
        model.addAttribute("survey", survey);
        List<QuestionType> Types = Arrays.asList(QuestionType.values());
        model.addAttribute("Types", Types);
        return "survey-view-questions";
    }

    @GetMapping("/surveys/{surveyID}/delete/{questionID}")
    public String deleteQuestion(@PathVariable("surveyID") int surveyID,@PathVariable("questionID") int questionID, Model model){
        Question tempQ = questionRepo.findById(questionID);
        Survey survey = surveyRepo.findById(surveyID);
        survey.removeQuestion(tempQ);
        surveyRepo.save(survey);
        questionRepo.delete(tempQ);
        model.addAttribute("survey",survey);
        model.addAttribute("id",surveyID);
        model.addAttribute("NewQuestion", new Question());
        return "survey-view-questions";
    }


    @GetMapping("/savesurvey/{surveyId}")
    public String saveSurvey(@PathVariable("surveyId") int surveyId, Model model) {
        Survey survey = surveyRepo.findById(surveyId);
        model.addAttribute("survey", survey);
        survey.setOpen(true);
        surveyRepo.save(survey);
        return "survey-created";
    }


    @GetMapping("/viewsurvey/{surveyId}")
    public String viewSurvey(@PathVariable("surveyId") int surveyId, Model model) {
        Survey survey = surveyRepo.findById(surveyId);
        model.addAttribute("survey", survey);
        List<QuestionType> Types = Arrays.asList(QuestionType.values());
        model.addAttribute("Types", Types);
        return "survey-view";
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