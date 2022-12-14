package com.backend.murasaki.dtos;

import java.util.Date;
import java.util.List;

public class LessonDTO {

    private Date date;

    private int lessonNumber;

    private String content;

    private String homework;

    private List<LinkDTO> linkDTOS;

    private String title;

    public LessonDTO(){

    }

    public LessonDTO(Date date, int lessonNumber, String content, String homework, List<LinkDTO> linkDTOS, String title){
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.content = content;
        this.homework = homework;
        this.linkDTOS = linkDTOS;
        this.title = title;
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

    public List<LinkDTO> getLinkDTOS() {
        return linkDTOS;
    }

    public void setLinkDTOS(List<LinkDTO> linkDTOS) {
        this.linkDTOS = linkDTOS;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
