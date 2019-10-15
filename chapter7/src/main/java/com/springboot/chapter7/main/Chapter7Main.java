//package com.springboot.chapter7.main;
//
//import com.springboot.chapter7.config.RedisConfig;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.SessionCallback;
//
//public class Chapter7Main {
//
//    public void useRedisCallback(RedisTemplate redisTemplate)
//    {
//        redisTemplate.execute(new RedisCallback() {
//            @Override
//            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                redisConnection.set("key1".getBytes(),"value1".getBytes());
//                redisConnection.hSet("hash".getBytes(),"field".getBytes(),"hvalue".getBytes());
//                return null;
//            }
//        });
//    }
//
//    public void userSessionCallback(RedisTemplate redisTemplate)
//    {
//        redisTemplate.execute(new SessionCallback() {
//            @Override
//            public Object execute(RedisOperations redisOperations) throws DataAccessException {
//                redisOperations.opsForValue().set("key1","value1");
//                redisOperations.opsForHash().put("hash","field","hvalue");
//                return null;
//            }
//        });
//    }
//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
//        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
////        redisTemplate.opsForValue().set("key1","value1");
////        redisTemplate.opsForHash().put("hash","field","hvalue");
//    }
//}
