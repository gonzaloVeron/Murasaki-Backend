package com.backend.murasaki.models;

import javax.persistence.*;

@Table(name = "schedules")
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String day;

    @Column
    private String time;

    public Schedule(){

    }

    public Schedule(int id, String day, String time){
        this.id = id;
        this.day = day;
        this.time = time;
    }

    public Schedule(String day, String time){
        this.day = day;
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
