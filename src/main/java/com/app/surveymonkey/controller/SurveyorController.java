package com.app.surveymonkey.controller;

import com.app.surveymonkey.repositories.SurveyorRepo;
import com.app.surveymonkey.survey.Survey;
import com.app.surveymonkey.surveyor.Surveyor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class SurveyorController {

    @Autowired private SurveyorRepo surveyorRepo;

   @PostMapping("/login")
   public String login(@ModelAttribute String username, @ModelAttribute String password){
       Surveyor tempSurveyor = surveyorRepo.findByUsername(username);
       if(tempSurveyor.getPassword()!=password){
           return "badlogin";
       }else {
           return "survey-homepage";
       }
   }

    @GetMapping("/signup")
    public String signup(Model model){
       model.addAttribute("surveyor", new Surveyor());
       return "signup";
    }

    @PostMapping(path = "/signup")
    public String createSurveyor(@ModelAttribute Surveyor surveyor){
        surveyorRepo.save(surveyor);
        return ("redirect:/surveyorHome");

    }

    @GetMapping("/surveyorHome")
    public String surveyorHome(){
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
        return "view-surveys"; // Return same HTML template for available surveys
    }

    @GetMapping("/accountInfo")
    public String viewAccountInfo(Model model, int id) {
        Surveyor surveyor = surveyorRepo.findById(id);

        model.addAttribute("surveyor", surveyor);
        return "surveyor-account-info";
    }


}
