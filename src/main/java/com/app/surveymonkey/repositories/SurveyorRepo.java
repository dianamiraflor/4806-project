package com.app.surveymonkey.repositories;

import com.app.surveymonkey.surveyor.Surveyor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyorRepo extends CrudRepository <Surveyor, Integer>{

    Surveyor findByUsername(String username);
    Surveyor findById(int id);

}
