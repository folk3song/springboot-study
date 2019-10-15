package com.springboot.chapter16.health;

import org.omg.CORBA.TIMEOUT;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class WwwHealthIndicator extends AbstractHealthIndicator {

    private final  static String BAIDU_HOST = "www.baidu.com";
    private final static int TIME_OUT = 3000;


    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        boolean status = ping();
        if(status)
        {
            builder.withDetail("message","当前服务器可以访问万维网。。").up();
        }else {
            builder.withDetail("message","当前无法访问万维网").outOfService();
        }
    }


    private boolean ping()throws Exception
    {
        try{
            return InetAddress.getByName(BAIDU_HOST).isReachable(TIME_OUT);

        }catch (Exception ex)
        {
            return false;
        }
    }
}
