package com.springboot.chapter5.pojo1;

import com.springboot.chapter5.enumeration.SexEnum;
import org.apache.ibatis.type.Alias;

@Alias(value = "user")
public class User {
    private Long id = null;
    private String userName = null;
    private String note = null;
    private SexEnum sex = null;
    public User(){}

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getNote() {
        return note;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
}
