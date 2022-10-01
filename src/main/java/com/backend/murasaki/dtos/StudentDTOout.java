package com.backend.murasaki.dtos;

public class StudentDTOout {

    private int id;

    private String name;

    private int jlptLevel;

    private TeacherDTO teacherDTO;

    public StudentDTOout(int id, String name, int jlptLevel, TeacherDTO teacherDTO) {
        this.id = id;
        this.name = name;
        this.jlptLevel = jlptLevel;
        this.teacherDTO = teacherDTO;
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

    public TeacherDTO getTeacherDTO() {
        return teacherDTO;
    }

    public void setTeacherDTO(TeacherDTO teacherDTO) {
        this.teacherDTO = teacherDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


