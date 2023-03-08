package com.app.surveymonkey.controller;

import com.app.surveymonkey.repositories.SurveyorRepo;
import com.app.surveymonkey.surveyor.Surveyor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class SurveyorController {

    @Autowired private SurveyorRepo surveyorRepo;


   @GetMapping("/login")
   public String loadlogin(){
       return "login";
   }

   @PostMapping("/login")
   public String login(@ModelAttribute String username, @ModelAttribute String password){
       Surveyor tempSurveyor = surveyorRepo.findByUsername(username);
       if(tempSurveyor.getPassword()!=password){
           return "badlogin";
       }else {
           return "index";
       }
       }
    @GetMapping("/signup")
    public String signup(Model model){
       model.addAttribute("surveyor", new Surveyor());
        return "signup";
    }

    @GetMapping("/users")
    public String users(Model model){
       model.addAttribute("users", surveyorRepo.findAll());
       return "users";
    }


    @PostMapping(path = "/signup")
    public String createSurveyor(@ModelAttribute Surveyor surveyor){
       surveyorRepo.save(surveyor);
       return "users";

    }



}
