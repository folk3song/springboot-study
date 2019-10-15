package com.springboot.chapter6.service.impl;

import com.springboot.chapter6.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Service
public class JdbcServiceImpl implements JdbcService {

    @Autowired
    @Qualifier("myDataSource")
    private DataSource dataSource = null;

    @Override
    public int inserUser(String userName, String note) {
        Connection conn = null;
        int result = 0;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            PreparedStatement ps = conn.prepareStatement("insert into t_user(user_name,note) values(?,?)");
            ps.setString(1,userName);
            ps.setString(2,note);
            result = ps.executeUpdate();
            conn.commit();
        }catch (Exception e)
        {
            if(conn!=null)
            {
                try {
                    conn.rollback();
                }catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            try {
                if(conn !=null && !conn.isClosed())
                {
                    conn.close();
                }
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
}
