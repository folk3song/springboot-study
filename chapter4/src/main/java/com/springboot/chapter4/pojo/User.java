package com.springboot.chapter4.pojo;

public class User {
    private  Long id;
    private  String username;
    private  String note;

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
