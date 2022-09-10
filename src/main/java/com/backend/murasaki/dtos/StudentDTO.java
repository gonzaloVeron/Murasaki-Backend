package com.backend.murasaki.dtos;

public class StudentDTO {

    private String name;

    private int jlptLevel;

    private int teacherAsignedId;

    public StudentDTO(String name, int jlptLevel, int teacherAsignedId){
        this.name = name;
        this.jlptLevel = jlptLevel;
        this.teacherAsignedId = teacherAsignedId;
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

}
