package com.app.surveymonkey.repositories;

import com.app.surveymonkey.survey.Survey;
import org.springframework.data.repository.CrudRepository;
public interface SurveyRepo extends CrudRepository <Survey, Long>{


}
