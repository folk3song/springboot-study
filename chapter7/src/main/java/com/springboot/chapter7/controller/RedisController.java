package com.springboot.chapter7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.awt.font.NumericShaper;
import java.util.*;

@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @RequestMapping("/stringAndHash")
    @ResponseBody
    public Map<String,Object> testStringAndHash()
    {
        redisTemplate.opsForValue().set("key1","value1");
        redisTemplate.opsForValue().set("int_key","1");

        stringRedisTemplate.opsForValue().set("int","1");
        stringRedisTemplate.opsForValue().increment("int",1);

        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");
        Map<String,String> hash = new HashMap<String,String>();
        hash.put("field1","value1");
        hash.put("field2","value2");

        stringRedisTemplate.opsForHash().putAll("hash",hash);
        stringRedisTemplate.opsForHash().put("hash","field3","value3");

        BoundHashOperations hashOperations = stringRedisTemplate.boundHashOps("hash");
        hashOperations.delete("field1","field2");
        hashOperations.put("field4","value5");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        return map;


    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> testList()
    {
        stringRedisTemplate.opsForList().leftPushAll(
                "list1","v2","v4","v6","v8","v10"
        );
        stringRedisTemplate.opsForList().rightPushAll(
                "list2","v1","v2","v3","v4","v5","v6"
        );

        BoundListOperations listOps = stringRedisTemplate.boundListOps("list2");

        Object result1 = listOps.rightPop();
        Object result2 = listOps.index(1);

        listOps.leftPush("v0");

        Long size = listOps.size();

        List elements = listOps.range(0,size-2);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        return map;
    }

    @RequestMapping("/set")
    @ResponseBody
    public Map<String,Object> testSet()
    {
        stringRedisTemplate.opsForSet().add("set1","v1","v1","v2","v3","v4","v5");
        stringRedisTemplate.opsForSet().add("set2","v2","v4","v6","v8");

        BoundSetOperations setOps = stringRedisTemplate.boundSetOps("set1");
        setOps.add("v6","v7");
        setOps.remove("v1","v7");

        Set set1 = setOps.members();

        Long size = setOps.size();
        Set inter = setOps.intersect("set2");
        setOps.intersectAndStore("set2","inter");
        Set diff = setOps.diff("set2");
        setOps.diffAndStore("set2","diff");
        Set union = setOps.union("set2");
        setOps.unionAndStore("set2","union");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        return map;
    }

    @RequestMapping("zset")
    @ResponseBody
    public Map<String,Object> testZset()
    {
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();
        for (int i = 1;i<=9;i++)
        {
            double score = i * 0.1;
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<String>("value"+i,score);
            typedTupleSet.add(typedTuple);
        }

        stringRedisTemplate.opsForZSet().add("zset1",typedTupleSet);
        BoundZSetOperations<String,String> zsetOps = stringRedisTemplate.boundZSetOps("zset1");
        zsetOps.add("value10",0.26);
        Set<String> setRange = zsetOps.range(1,6);
        Set<String> setScore = zsetOps.rangeByScore(0.2,0.6);

        Set<ZSetOperations.TypedTuple<String>> rangeSet = zsetOps.rangeWithScores(1,6);
        Set<ZSetOperations.TypedTuple<String>> scoreSet = zsetOps.rangeByScoreWithScores(1,6);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        return map;

    }

    @RequestMapping("/multi")
    @ResponseBody
    public Map<String,Object> testMulti()
    {
        redisTemplate.opsForValue().set("key1","value1");
        List list = (List) redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch("key1");
                operations.multi();
                //operations.opsForValue().increment("key1",1);
                Object value2 = operations.opsForValue().get("key2");
                System.out.println("命令在队列所以value 为null【"+value2+"】");
                operations.opsForValue().set("key3","value3");
                Object value3 = operations.opsForValue().get("key3");
                System.out.println("命令在队列所以value 为null【"+value3+"】");
                return operations.exec();
            }
        });
        System.out.println(list);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        return map;
    }

    @RequestMapping("pipleline")
    @ResponseBody
    public Map<String,Object> testPipeline()
    {
        Long start = System.currentTimeMillis();
        List list = redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for(int i = 1;i<100000;i++)
                {
                    operations.opsForValue().set("pipeline_"+i,"value_"+i);
                    String value = (String) operations.opsForValue().get("pipeline_"+i);
                    if(i == 10000)
                    {
                        System.out.println("命令只是进入队列所以值为空【"+value+"】");
                    }
                }
                return null;
            }
        });
        Long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end - start)+"毫秒");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        return map;
    }

    @RequestMapping("/lua")
    @ResponseBody
    public Map<String,Object> testLua()
    {
        DefaultRedisScript<String> rs = new DefaultRedisScript<>();
        rs.setScriptText("return 'Hello Redis'");
        rs.setResultType(String.class);
        RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
        String str = (String) redisTemplate.execute(rs,stringRedisSerializer,stringRedisSerializer,null);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        return map;
    }
}
