package com.backend.murasaki.models;

import com.backend.murasaki.dtos.TeacherDTO;

import javax.persistence.*;
import java.util.List;

@Table(name = "teachers")
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "teacherAssigned", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Student> students;

    public Teacher(){

    }

    public Teacher(int id, String name, List<Student> students){
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public Teacher(String name, List<Student> students){
        this.name = name;
        this.students = students;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public TeacherDTO toDTO(){
        TeacherDTO dto = new TeacherDTO();
        dto.setName(this.name);
        return dto;
    }

}
