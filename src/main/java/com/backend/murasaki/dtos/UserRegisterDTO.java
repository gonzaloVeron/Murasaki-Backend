package com.backend.murasaki.dtos;

public class UserRegisterDTO {

    private String email;

    private String password;

    private int teacher_id;

    public UserRegisterDTO(String email, String password, int teacher_id){
        this.email = email;
        this.password = password;
        this.teacher_id = teacher_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

}
