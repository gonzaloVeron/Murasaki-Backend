package com.backend.murasaki.dtos;

import java.util.Date;

public class StudentDTO {

    private String name;

    private int jlptLevel;

    private int teacherAsignedId;

    private String priorKnowledge;

    private int age;

    public StudentDTO(String name, int jlptLevel, int teacherAsignedId, String priorKnowledge, int age){
        this.name = name;
        this.jlptLevel = jlptLevel;
        this.teacherAsignedId = teacherAsignedId;
        this.priorKnowledge = priorKnowledge;
        this.age = age;
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

    public int getTeacherAsignedId() {
        return teacherAsignedId;
    }

    public void setTeacherAsignedId(int teacherAsignedId) {
        this.teacherAsignedId = teacherAsignedId;
    }

    public String getPriorKnowledge() {
        return priorKnowledge;
    }

    public void setPriorKnowledge(String priorKnowledge) {
        this.priorKnowledge = priorKnowledge;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
