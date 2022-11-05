package com.backend.murasaki.dtos;

public class StudentDTOout {

    private int id;

    private String name;

    private int jlptLevel;

    private String email;

    private int tel;

    private TeacherDTO teacherDTO;

    public StudentDTOout(int id, String name, int jlptLevel, String email, int tel, TeacherDTO teacherDTO) {
        this.id = id;
        this.name = name;
        this.jlptLevel = jlptLevel;
        this.email = email;
        this.tel = tel;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

}


