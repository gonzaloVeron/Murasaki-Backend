package com.backend.murasaki.dtos;

import java.util.Date;

public class LessonDTO {

    private Date date;

    private int lessonNumber;

    private String content;

    private String homework;

    public LessonDTO(){

    }

    public LessonDTO(Date date, int lessonNumber, String content, String homework){
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.content = content;
        this.homework = homework;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

}
