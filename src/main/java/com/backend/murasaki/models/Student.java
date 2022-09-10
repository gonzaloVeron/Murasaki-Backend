package com.backend.murasaki.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

}
