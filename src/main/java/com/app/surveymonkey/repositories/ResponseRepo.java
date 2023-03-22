package com.app.surveymonkey.repositories;

import com.app.surveymonkey.responses.Response;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepo extends CrudRepository <Response, Integer>{

    Response findById(int id);

}
