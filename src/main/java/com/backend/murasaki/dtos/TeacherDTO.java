package com.backend.murasaki.dtos;

public class TeacherDTO {

    private String name;

    public TeacherDTO(){

    }
    public TeacherDTO(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

}
