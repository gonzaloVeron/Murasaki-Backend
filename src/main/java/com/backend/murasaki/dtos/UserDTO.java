package com.backend.murasaki.dtos;

public class UserDTO {

    private String email;

    private String name;


    public UserDTO(String email, String teacherName){
        this.email = email;
        this.name = teacherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
