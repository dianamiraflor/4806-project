package com.app.surveymonkey.repositories;

import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.responses.Response;
import org.springframework.data.repository.CrudRepository;
public interface ResponseRepo extends CrudRepository <Response, Integer>{

    Question findById(int id);

}
