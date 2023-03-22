/*
package com.app.surveymonkey;

import com.app.surveymonkey.controller.SurveyController;
import com.app.surveymonkey.repositories.QuestionRepo;
import com.app.surveymonkey.repositories.ResponseRepo;
import com.app.surveymonkey.repositories.SurveyRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest()
@ContextConfiguration(classes = SurveyController.class)
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SurveyRepo surveyRepo;

    @MockBean
    QuestionRepo questionRepo;

    @MockBean
    SurveyRepo surveyorRepo;
    @MockBean
    ResponseRepo responseRepo;

    @Test
    void testHomePage() throws Exception {
        this.mockMvc
                .perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("HomePage"));

    }

    @Test
    void shouldPostSurvey() throws Exception {
        // POST SURVEY - CREATES NEW SURVEY (INIT)
        this.mockMvc
                .perform(get("/newSurvey"))
                .andExpect(status().isOk())
                .andExpect(view().name("survey-create"))
                .andExpect(model().attributeExists("survey"));

        // CREATE TEST FOR survey-create
    }

}
 */