package com.app.surveymonkey;

import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.repositories.QuestionRepo;
import com.app.surveymonkey.repositories.SurveyRepo;
import com.app.surveymonkey.repositories.SurveyorRepo;
import com.app.surveymonkey.survey.Survey;
import com.app.surveymonkey.surveyor.Surveyor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SurveyMonkeyApplication {
	public static void main(String[] args) {
		SpringApplication.run(SurveyMonkeyApplication.class, args);
	}


	@Bean
	public CommandLineRunner test(SurveyRepo surveyRepo, QuestionRepo questionRepo, SurveyorRepo surveyorRepo) {
		return(args) -> {
			Surveyor surveyor = new Surveyor();

			surveyor.setName("Diana");
			surveyor.setUsername("Test");
			surveyor.setPassword("Test");

			Survey survey = new Survey();

			survey.setSurveyName("SurveyTest");
			survey.setOpen(true);

			Question mcq1 = new Question();

			mcq1.setText("MCQ_Test");
			mcq1.setType(Question.QuestionType.CHOICE);
			List<String> choices = new ArrayList<>();
			choices.add("Choice1");
			choices.add("Choice2");
			choices.add("Choice3");
			mcq1.setChoices(choices);
			mcq1.setSurvey(survey);

			Question rq1 = new Question();

			rq1.setText("RQ_Test");
			rq1.setType(Question.QuestionType.RANGE);
			rq1.setRangeMin(1);
			rq1.setRangeMax(3);
			rq1.setSurvey(survey);

			Question tq1 = new Question();

			tq1.setText("TQ_Test");
			tq1.setType(Question.QuestionType.TEXT);
			tq1.setSurvey(survey);

			survey.addQuestion(mcq1);
			survey.addQuestion(rq1);
			survey.addQuestion(tq1);


			surveyor.addSurvey(survey);

			surveyRepo.save(survey);
		};
	}
}