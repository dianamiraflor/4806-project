package com.app.surveymonkey.controller;

import com.app.surveymonkey.repositories.SurveyorRepo;
import com.app.surveymonkey.survey.Survey;
import com.app.surveymonkey.surveyor.Surveyor;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class SurveyorController {

    @Autowired private SurveyorRepo surveyorRepo;

    Logger logger = LoggerFactory.getLogger(SurveyorController.class);

    @GetMapping("/login")
    public String loadLogin(Model model) {
        model.addAttribute("surveyor", new Surveyor());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("username") @NotNull String username, @ModelAttribute("password") @NotNull String password,
                        @ModelAttribute Surveyor surveyor, Model model){
        if (username == null | password == null) {
            return "badlogin";
        }

       Surveyor tempSurveyor = surveyorRepo.findByUsername(username);
       if(tempSurveyor.getPassword().equals(password)){
           model.addAttribute("surveyor", tempSurveyor);
           return "surveyor-homepage";
       } else {
          return "badlogin";
       }
    }

    @GetMapping("/signup")
    public String signup(Model model){
       model.addAttribute("surveyor", new Surveyor());
       return "signup";
    }

    @PostMapping(path = "/signup")
    public String createSurveyor(@ModelAttribute Surveyor surveyor, Model model){
        surveyorRepo.save(surveyor);
        model.addAttribute("surveyor", surveyor);
        return "surveyor-homepage";
    }

    @GetMapping("/surveyorHome")
    public String surveyorHome(@ModelAttribute Surveyor surveyor, Model model){
        model.addAttribute("surveyor", surveyor);
        return "surveyor-homepage";
    }

    @GetMapping("/users")
    public String users(Model model){
       model.addAttribute("users", surveyorRepo.findAll());
       return "users";
    }

    @GetMapping("/viewCreatedSurveys")
    public String viewCreatedSurveys(Model model, int id) {
        // LOGIC FOR FINDING SURVEYOR'S CREATED SURVEYS
        Surveyor surveyor = surveyorRepo.findById(id);
        Iterable<Survey> createdSurveys = surveyor.getSurveyList(); // Might be incompatible types with HTML?

        model.addAttribute("surveys", createdSurveys);
        return "view-created-surveys"; // Return Surveyor version of view-surveys.html but with options to close + open surveys.
    }

    @GetMapping("/accountInfo/{surveyorID}")
    public String viewAccountInfo(@PathVariable("surveyorID") String surveyorID, Model model) {
        int surveyor_id = Integer.parseInt(surveyorID);

        Surveyor surveyor = surveyorRepo.findById(surveyor_id);
        model.addAttribute("surveyor", surveyor);
        return "surveyor-account-info";
    }


}
