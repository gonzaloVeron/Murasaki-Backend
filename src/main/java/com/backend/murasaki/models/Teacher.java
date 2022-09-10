package com.backend.murasaki.models;

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

    @OneToMany(mappedBy = "teacherAsigned", cascade = CascadeType.ALL, orphanRemoval = true)
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
}
