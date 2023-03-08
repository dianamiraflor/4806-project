package com.app.surveymonkey.survey;

import static org.assertj.core.api.Assertions.assertThat;

import com.app.surveymonkey.controller.SurveyController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private SurveyController surveyController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(surveyController).isNotNull();
    }
}