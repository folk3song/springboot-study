package com.springboot.chapter4.jdbc;

import com.springboot.chapter4.pojo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserService {
    public static int insertUser()
    {

        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("user_name_1");
        user.setNote("note_1");
        Connection conn = null;
        int result = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev_spring_boot?serverTimezone=UTC&useSSL=false","root","123456");
            conn.setAutoCommit(false);
            result = userDao.insertUser(conn,user);
            conn.commit();
        }catch (Exception e)
        {
            try{
                conn.rollback();
            }catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            if(conn != null)
            {
                try{
                    conn.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return  result;

    }

    public static void main(String[] args) {
        System.out.println(insertUser());
    }
}
