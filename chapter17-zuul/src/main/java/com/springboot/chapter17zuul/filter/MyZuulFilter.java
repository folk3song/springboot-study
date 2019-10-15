package com.springboot.chapter17zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

@Component
public class MyZuulFilter extends ZuulFilter {
    @Autowired
    private StringRedisTemplate redisTemplate = null;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();

        String serialNumber = req.getParameter("serialNumber");

        //有参数才启动过滤器
        return !StringUtils.isEmpty(serialNumber);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String serialNumber = req.getParameter("serialNumber");
        String reqCode = req.getParameter("verificationCode");

        String verifCode = redisTemplate.opsForValue().get(serialNumber);

        if(verifCode == null || !verifCode.equals(reqCode))
        {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8.getType());
            ctx.setResponseBody("{'success':false,}"+"'message':'Verification Code Error'");
        }
        return null;
    }
}
