package com.backend.murasaki.dtos;

public class SearchStudentByDTO {

    private Integer level;

    private String teacherName;

    public SearchStudentByDTO(){

    }

    public SearchStudentByDTO(Integer level, String teacherName) {
        this.level = level;
        this.teacherName = teacherName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
