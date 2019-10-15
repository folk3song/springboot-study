package com.springboot.chapter5.pojo;

import com.springboot.chapter5.converter.SexConverter;
import com.springboot.chapter5.enumeration.SexEnum;

import javax.persistence.*;

@Entity(name = "user")
@Table(name = "t_user")
public class User1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "user_name")
    private String userName = null;

    private String note = null;

    @Convert(converter = SexConverter.class)
    private SexEnum sex = null;

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
