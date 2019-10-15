package com.springboot.chapter12.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@SuppressWarnings("unckecked")
@Alias("user")
public class DatabaseUser implements Serializable {
    private Long id;
    private String userName;
    private String pwd;
    private String available;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
