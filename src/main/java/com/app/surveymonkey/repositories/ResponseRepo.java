package com.app.surveymonkey.repositories;

import com.app.surveymonkey.responses.Response;
import org.springframework.data.repository.CrudRepository;
public interface ResponseRepo extends CrudRepository <Response, Integer>{

    Response findById(int id);

}
