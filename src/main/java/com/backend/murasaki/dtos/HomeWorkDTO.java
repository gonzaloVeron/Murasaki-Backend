package com.backend.murasaki.dtos;

import java.util.Map;

public class HomeWorkDTO {

    private String title;

    private String description;

    private Map<String, Boolean> multipleChoise;

    public HomeWorkDTO() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Boolean> getMultipleChoise() {
        return multipleChoise;
    }

    public void setMultipleChoise(Map<String, Boolean> multipleChoise) {
        this.multipleChoise = multipleChoise;
    }
    
}
