package com.app.surveymonkey.questions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestionDTO  {

    private String prompt;
    private String type;
    private List<String> choicesList = new ArrayList<>();
    private Integer min;
    private Integer max;

    private Map<String, Object> jsonObj;


    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getChoicesList() {
        return choicesList;
    }

    public void setChoicesList(List<String> choices) {
        this.choicesList = choices;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Map<String, Object> getJsonObj() {
        return jsonObj;
    }

    public void setJsonObj(Map<String, Object> jsonObj) {
        this.jsonObj = jsonObj;
    }

}
