
package com.app.surveymonkey.survey;

import com.app.surveymonkey.questions.Question;
import com.app.surveymonkey.surveyor.Surveyor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SurveyTest {
    private Surveyor surveyor;
    private Survey survey;
    private Question mcq1;
    private Question rq1;
    private Question tq1;

    @BeforeEach
    void setUp() {
        surveyor = new Surveyor();
        surveyor.setName("Diana");
        surveyor.setUsername("Test");
        surveyor.setPassword("Test123");


        survey = new Survey();
        survey.setId(1);
        survey.setSurveyName("SurveyTest");

        mcq1 = new Question();
        mcq1.setText("MCQ_Test");
        mcq1.setType(Question.QuestionType.CHOICE);
        List<String> choices_list = new ArrayList<>();
        choices_list.add("Choice1");
        choices_list.add("Choice2");
        choices_list.add("Choice3");
        mcq1.setChoices(choices_list);
        mcq1.setSurvey(survey);

        rq1 = new Question();
        rq1.setText("RQ_Test");
        rq1.setType(Question.QuestionType.RANGE);
        rq1.setRangeMin(1);
        rq1.setRangeMax(3);
        rq1.setSurvey(survey);

        tq1 = new Question();
        tq1.setText("TQ_Test");
        tq1.setType(Question.QuestionType.TEXT);
        tq1.setSurvey(survey);

        survey.addQuestion(mcq1);
        survey.addQuestion(rq1);
        survey.addQuestion(tq1);

        survey.setOpen(true);

        surveyor.addSurvey(survey);

    }

    @Test
    public void testSurvey() {
        long expectedId = 1;
        int expectedSize = 3;
        String expectedName = "SurveyTest";

        assertEquals(expectedId, survey.getId());
        assertEquals(expectedName, survey.getSurveyName());
        assertEquals(expectedSize, survey.getQuestions().size());
    }
    @Test
    public void testCreatedMCQ() {
        Question.QuestionType type = mcq1.getType();
        String text = mcq1.getText();
        List<String> choices = mcq1.getChoices();

        assertEquals(Question.QuestionType.CHOICE, type);
        assertEquals("MCQ_Test", text);
        assertEquals(3, choices.size());
    }

    @Test
    public void testCreatedRQ() {
        Question.QuestionType type = rq1.getType();
        String text = rq1.getText();
        Integer min = rq1.getRangeMin();
        Integer max = rq1.getRangeMax();

        assertEquals(Question.QuestionType.RANGE, type);
        assertEquals("RQ_Test", text);
        assertEquals(1, min);
        assertEquals(3, max);
    }

    @Test
    public void testTQ() {
        Question.QuestionType type = tq1.getType();
        String text = tq1.getText();

        assertEquals(Question.QuestionType.TEXT, type);
        assertEquals("TQ_Test", text);
    }

    @Test
    public void testSurveyor() {
        int expectedSize = 1;

        assertEquals(expectedSize, surveyor.getSurveyList().size());
    }
    }

