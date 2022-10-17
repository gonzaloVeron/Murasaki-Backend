package com.backend.murasaki.dtos;

import com.backend.murasaki.models.User;

public class UserLoggedDTO {

    private User user;

    private String teacherName;

    private String token;

    public UserLoggedDTO(User user, String teacherName, String token){
        this.user = user;
        this.teacherName = teacherName;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
