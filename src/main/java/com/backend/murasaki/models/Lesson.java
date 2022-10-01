package com.backend.murasaki.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.type.DateTime;

import javax.persistence.*;
import java.util.Date;

@Table(name = "lessons")
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column
    private Date date;

    @Column
    private int lessonNumber;

    @Column
    private String content;

    @Column
    private String homework;

    public Lesson(){

    }

    public Lesson(int id, Date date, int lessonNumber, String content, String homework){
        this.id = id;
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.content = content;
        this.homework = homework;
    }

    public Lesson(Date date, int lessonNumber, String content, String homework){
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.content = content;
        this.homework = homework;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
