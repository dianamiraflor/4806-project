package com.app.surveymonkey.repositories;

import com.app.surveymonkey.survey.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepo extends CrudRepository <Survey, Integer>{

    Survey findById(int id);
    Survey findTopByOrderByIdDesc();

    Iterable<Survey> findAllByOpenTrue();


}
