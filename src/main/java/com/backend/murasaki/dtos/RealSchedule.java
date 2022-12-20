package com.backend.murasaki.dtos;

import java.util.List;

public class RealSchedule {

    private String time;

    private List<String> studentNames;

    public RealSchedule(String time, List<String> studentNames){
        this.time = time;
        this.studentNames = studentNames;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(List<String> studentNames) {
        this.studentNames = studentNames;
    }

}
