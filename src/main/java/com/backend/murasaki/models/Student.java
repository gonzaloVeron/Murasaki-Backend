package com.backend.murasaki.models;

import com.backend.murasaki.dtos.StudentDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "students")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private int age;

    //@ManyToMany
    //@JoinTable(
    //    name = "student_interest",
    //    joinColumns = @JoinColumn(name = "student_id"),
    //    inverseJoinColumns = @JoinColumn(name = "interest_id")
    //)

    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ManyToMany
    @JoinTable(
        name = "student_interest",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private Set<Interest> interests;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    @Column
    private String priorKnowledge;

    @Column
    private int tel;

    @Column
    private String email;

    @Column
    private String emailTutor;

    @Column
    private int jlptLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonIgnore
    private Teacher teacherAssigned;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    public Student(){

    }

    public Student(int id, String name, int jlptLevel, Teacher teacherAssigned, String priorKnowledge, int age, int tel, String email, String emailTutor, Set<Interest> interests, List<Lesson> lessons, List<Schedule> schedules){
        this.id = id;
        this.name = name;
        this.jlptLevel = jlptLevel;
        this.teacherAssigned = teacherAssigned;
        this.priorKnowledge = priorKnowledge;
        this.age = age;
        this.tel = tel;
        this.email = email;
        this.emailTutor = emailTutor;
        this.interests = interests;
        this.lessons = lessons;
        this.schedules = schedules;
    }

    public Student(String name, int jlptLevel, Teacher teacherAssigned, String priorKnowledge, int age, int tel, String email, String emailTutor, Set<Interest> interests, List<Lesson> lessons, List<Schedule> schedules){
        this.name = name;
        this.jlptLevel = jlptLevel;
        this.teacherAssigned = teacherAssigned;
        this.priorKnowledge = priorKnowledge;
        this.age = age;
        this.tel = tel;
        this.email = email;
        this.emailTutor = emailTutor;
        this.interests = interests;
        this.lessons = lessons;
        this.schedules = schedules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Teacher getTeacherAssigned() {
        return teacherAssigned;
    }

    public void setTeacherAssigned(Teacher teacherAssigned) {
        this.teacherAssigned = teacherAssigned;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPriorKnowledge() {
        return priorKnowledge;
    }

    public void setPriorKnowledge(String priorKnowledge) {
        this.priorKnowledge = priorKnowledge;
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

    public void addLesson(Lesson lesson){
        this.lessons.add(lesson);
    }

    public void addInterest(Interest interest){
        this.interests.add(interest);
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

    public StudentDTO toDTO(){
        return new StudentDTO(this.name, this.jlptLevel, this.teacherAssigned.getId(), this.priorKnowledge, this.age, this.tel, this.email, this.emailTutor, this.interests, this.lessons, this.schedules);
    }

    public void deleteLesson(Lesson lesson){
        this.lessons.remove(lesson);
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

}
