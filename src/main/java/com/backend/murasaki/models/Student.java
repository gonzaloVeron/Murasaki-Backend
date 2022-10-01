package com.backend.murasaki.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
        name = "student_interest",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private List<Interest> interests;

    @Column
    private String priorKnowledge;

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

    public Student(int id, String name, int jlptLevel, Teacher teacherAssigned, String priorKnowledge, int age){
        this.id = id;
        this.name = name;
        this.jlptLevel = jlptLevel;
        this.teacherAssigned = teacherAssigned;
        this.priorKnowledge = priorKnowledge;
        this.age = age;
    }

    public Student(String name, int jlptLevel, Teacher teacherAssigned, String priorKnowledge, int age){
        this.name = name;
        this.jlptLevel = jlptLevel;
        this.teacherAssigned = teacherAssigned;
        this.priorKnowledge = priorKnowledge;
        this.age = age;
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

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
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

}
