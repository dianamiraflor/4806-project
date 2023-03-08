package com.app.surveymonkey.survey;

import com.app.surveymonkey.questions.MultipleChoiceQuestion;
import com.app.surveymonkey.questions.RangeQuestion;
import com.app.surveymonkey.questions.TextQuestion;
import com.app.surveymonkey.surveyor.Surveyor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SurveyTest {
    private Surveyor surveyor;
    private Survey survey;
    private MultipleChoiceQuestion mcq1;
    private RangeQuestion rq1;
    private TextQuestion tq1;

    @BeforeEach
    void setUp() {
        surveyor = new Surveyor();
        surveyor.setId(1);
        surveyor.setName("Diana");
        surveyor.setUsername("dianamiraflor");
        surveyor.setPassword("pass123");

        mcq1 = new MultipleChoiceQuestion();
        mcq1.setId(1);
        mcq1.setQuestionText("Who did you purchase these products for?");
        mcq1.setLimit(3);
        mcq1.addChoice("Self");
        mcq1.addChoice("Friend");
        mcq1.addChoice("Family member");

        rq1 = new RangeQuestion();
        rq1.setId(1);
        rq1.setQuestionText("How satisfied are you with the product?");
        rq1.setMin(1);
        rq1.setMax(3);
        rq1.createRangeList();

        tq1 = new TextQuestion();
        tq1.setId(1);
        tq1.setQuestionText("Please provide any additional comments or feedback");

        survey = new Survey();
        survey.setId(1);
        survey.setSurveyName("Product Questionnaire");
        survey.addQuestion(mcq1);
        survey.addQuestion(rq1);
        survey.addQuestion(tq1);

        surveyor.addSurvey(survey);
    }

    @Test
    public void displaySurvey() {
        System.out.println(survey.toString());
    }

    @Test
    public void testSurvey() {
        long expectedId = 1;
        int expectedSize = 3;
        String expectedName = "Product Questionnaire";

        assertEquals(expectedId, survey.getId());
        assertEquals(expectedName, survey.getSurveyName());
        assertEquals(expectedSize, survey.getQuestions().size());
    }
    @Test
    public void testMCQ() {
        String testAnswer = "Self";
        mcq1.setAnswer(testAnswer);

        assertEquals(testAnswer, mcq1.getAnswer());
    }

    @Test
    public void testRQ() {
        Integer testAnswer = 2;
        rq1.setAnswer(testAnswer);

        assertEquals(testAnswer, rq1.getAnswer());
    }

    @Test
    public void testTQ() {
        String testAnswer = "No additional comments.";
        tq1.setAnswer(testAnswer);

        assertEquals(testAnswer, tq1.getAnswer());
    }

    @Test
    public void testSurveyor() {
        int expectedSize = 1;

        assertEquals(expectedSize, surveyor.getSurveyList().size());
    }
}