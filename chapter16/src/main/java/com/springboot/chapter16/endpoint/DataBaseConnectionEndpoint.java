package com.springboot.chapter16.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(
        id = "dbcheck",
        enableByDefault = true
)
public class DataBaseConnectionEndpoint {

    private  static final String DRIVER = "com.mysql.jdbc.Driver";
    @Value("${spring.datasource.url}")
    private String url = null;
    @Value("${spring.datasource.username}")
    private String username = null;
    @Value("${spring.datasource.password}")
    private String password = null;

    @ReadOperation
    public Map<String,Object> test()
    {
        Connection conn = null;
        Map<String,Object> msgMap = new HashMap<>();
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(url,username,password);
            msgMap.put("success",true);
            msgMap.put("message","测试数据库连接成功");
        }catch (Exception e)
        {
            msgMap.put("success","false");
            msgMap.put("message",e.getMessage());
        }finally {
          if (conn != null)
          {
              try {
                  conn.close();
              }catch (SQLException e)
              {
                  e.printStackTrace();
              }
          }
        }
        return  msgMap;
    }
}
