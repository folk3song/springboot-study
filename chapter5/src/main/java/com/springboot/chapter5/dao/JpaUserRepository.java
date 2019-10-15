package com.springboot.chapter5.dao;



import com.springboot.chapter5.pojo.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserRepository  extends JpaRepository<User1,Long> {
    @Query("from user where user_name like concat('%',?1,'%') and note like concat('%',?2,'%') ")
    public List<User1> findUsers(String userName,String note);

    List<User1> findByUserNameLike(String userName);

    User1 getUserById(Long id);

    List<User1> findByUserNameLikeOrNoteLike(String userName,String note);

}
