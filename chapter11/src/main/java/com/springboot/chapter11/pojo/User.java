package com.springboot.chapter11.pojo;


import com.springboot.chapter11.enumeration.SexEnum;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("user")
@SuppressWarnings("uncheck")
public class User implements Serializable {
    private Long id;
    private String userName;
    private SexEnum sex = null;
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

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
