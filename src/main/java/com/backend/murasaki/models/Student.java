package com.backend.murasaki.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Table(name = "students")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private int jlptLevel;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonIgnore
    private Teacher teacherAsigned;

    @OneToMany(mappedBy = "studentAsigned", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HomeWork> homeWorks;

    public Student(){

    }

    public Student(int id, String name, int jlptLevel, Teacher teacherAsigned){
        this.id = id;
        this.name = name;
        this.jlptLevel = jlptLevel;
        this.teacherAsigned = teacherAsigned;
    }

    public Student(String name, int jlptLevel, Teacher teacherAsigned){
        this.name = name;
        this.jlptLevel = jlptLevel;
        this.teacherAsigned = teacherAsigned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJlptLevel() {
        return jlptLevel;
    }

    public void setJlptLevel(int jlptLevel) {
        this.jlptLevel = jlptLevel;
    }

    public Teacher getTeacherAsigned() {
        return teacherAsigned;
    }

    public void setTeacherAsigned(Teacher teacherAsigned) {
        this.teacherAsigned = teacherAsigned;
    }

    public List<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(List<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }

    public void addHomeWork(HomeWork homeWork){
        this.homeWorks.add(homeWork);
    }

}
