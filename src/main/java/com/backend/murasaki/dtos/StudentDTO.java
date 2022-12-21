package com.backend.murasaki.dtos;

import com.backend.murasaki.models.Interest;
import com.backend.murasaki.models.Lesson;
import com.backend.murasaki.models.Schedule;

import java.util.List;
import java.util.Set;

public class StudentDTO {

    private String name;

    private int jlptLevel;

    private int teacherAsignedId;

    private String priorKnowledge;

    private int tel;

    private String email;

    private String emailTutor;

    private int age;

    private Set<Interest> interests;

    private List<Lesson> lessons;

    private List<Schedule> schedules;

    private boolean status;

    public StudentDTO(String name, int jlptLevel, int teacherAsignedId, String priorKnowledge, int age, int tel, String email, String emailTutor, Set<Interest> interests, List<Lesson> lessons, List<Schedule> schedules, boolean status){
        this.name = name;
        this.jlptLevel = jlptLevel;
        this.teacherAsignedId = teacherAsignedId;
        this.priorKnowledge = priorKnowledge;
        this.age = age;
        this.tel = tel;
        this.email = email;
        this.emailTutor = emailTutor;
        this.interests = interests;
        this.lessons = lessons;
        this.schedules = schedules;
        this.status = status;
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

    public String getPriorKnowledge() {
        return priorKnowledge;
    }

    public void setPriorKnowledge(String priorKnowledge) {
        this.priorKnowledge = priorKnowledge;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailTutor() {
        return emailTutor;
    }

    public void setEmailTutor(String emailTutor) {
        this.emailTutor = emailTutor;
    }

    public Set<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public boolean getStatus(){
        return this.status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

}
