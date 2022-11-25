package com.backend.murasaki.dtos;

public class LinkDTO {

    private Integer id;

    private String title;

    private String url;

    public LinkDTO(){

    }

    public LinkDTO(String title, String url){
        this.title = title;
        this.url = url;
    }

    public LinkDTO(String title, String url, Integer id){
        this.title = title;
        this.url = url;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
