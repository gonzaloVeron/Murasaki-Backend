package com.backend.murasaki.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


import java.util.List;

@Table(name = "interests")
@Entity
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String icon;

    public Interest(){

    }

    public Interest(String name, String icon){
        this.name = name;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
