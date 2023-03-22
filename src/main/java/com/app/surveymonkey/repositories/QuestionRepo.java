package com.app.surveymonkey.repositories;

import com.app.surveymonkey.questions.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends CrudRepository <Question, Integer>{

    Question findById(int id);

}
