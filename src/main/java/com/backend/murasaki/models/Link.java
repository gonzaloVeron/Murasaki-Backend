package com.backend.murasaki.models;

import javax.persistence.*;

@Table(name = "links")
@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;

    @Column
    private String url;

    public Link(){

    }

    public Link(int id, String title, String url){
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public Link(String title, String url){
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
