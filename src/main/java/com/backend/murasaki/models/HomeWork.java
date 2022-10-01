package com.backend.murasaki.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Map;

@Table(name = "homeworks")
@Entity
public class HomeWork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    //@ManyToOne
    //@JoinColumn(name = "homework_id", nullable = false)
    //@JsonIgnore
    //private Student studentAsigned;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "multipleChoises", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    @MapKeyColumn(name = "option", length = 200)
    @Column(name = "response")
    private Map<String, Boolean> multipleChoise;

    public HomeWork(){

    }

    public HomeWork(int id, String title, String description, Map<String, Boolean> multipleChoise, Student studentAsigned){
        this.id = id;
        this.title = title;
        this.description = description;
        this.multipleChoise = multipleChoise;
        //this.studentAsigned = studentAsigned;
    }

    public HomeWork(String title, String description, Map<String, Boolean> multipleChoise, Student studentAsigned){
        this.title = title;
        this.description = description;
        this.multipleChoise = multipleChoise;
        //this.studentAsigned = studentAsigned;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //public Student getStudentAsigned() {
    //    return studentAsigned;
    //}

    //public void setStudentAsigned(Student studentAsigned) {
    //    this.studentAsigned = studentAsigned;
    //}
}
