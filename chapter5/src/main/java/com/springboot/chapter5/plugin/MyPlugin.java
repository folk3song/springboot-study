package com.springboot.chapter5.plugin;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class,Integer.class}
        )

})

public class MyPlugin implements Interceptor {

    Properties properties = null;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("插件拦截方法。。。。。。。。。");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }



    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
