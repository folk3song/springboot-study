package com.springboot.chapter17user1.pojo;




import java.io.Serializable;

@SuppressWarnings("unchecked")
public class UserPo implements Serializable {
    private Long id ;
    private String userName;
    private int level;
    private String note;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
