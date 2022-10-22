package com.backend.murasaki.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.type.DateTime;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Link> links;

    public Lesson(){

    }

    public Lesson(int id, Date date, int lessonNumber, String content, String homework, List<Link> links){
        this.id = id;
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.content = content;
        this.homework = homework;
        this.links = links;
    }

    public Lesson(Date date, int lessonNumber, String content, String homework, List<Link> links){
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.content = content;
        this.homework = homework;
        this.links = links;
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
