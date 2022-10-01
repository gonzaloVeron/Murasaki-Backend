package com.backend.murasaki.dtos;

import java.util.Date;

public class StudentDTOout {

    private int id;

    private String name;

    private int jlptLevel;

    private TeacherDTO teacherDTO;

    private Date schedule;

    public StudentDTOout(int id, String name, int jlptLevel, TeacherDTO teacherDTO, Date schedule) {
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

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


