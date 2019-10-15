package com.springboot.chapter5.pojo;

import com.springboot.chapter5.enumeration.SexEnum;

public class User {
    private Long id = null;
    private String userName = null;
    private SexEnum sex = null;
    private String note = null;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public String getNote() {
        return note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
