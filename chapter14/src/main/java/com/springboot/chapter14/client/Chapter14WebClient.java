package com.springboot.chapter14.client;

import com.springboot.chapter14.enumeration.SexEnum;
import com.springboot.chapter14.pojo.User;
import com.springboot.chapter14.vo.UserVo;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Chapter14WebClient {
    public static void main(String[] args) {
        WebClient client = WebClient.create("http://localhost:8080");
        //insertUser2(client);

        User newUser = new User();
        newUser.setId(21L);
        newUser.setNote("note_new");
        newUser.setUserName(null);
        newUser.setSex(SexEnum.MALE);
        insertUser(client,newUser);
//        getUser(client,2L);
//        User updUser = new User();
//        updUser.setId(1L);
//        updUser.setNote("note_update");
//        updUser.setUserName("user_name_update");
//        updUser.setSex(SexEnum.FEMALE);
//        updateUser(client,updUser);
//
//        findUsers(client,"user","note");
//
//        deleteUser(client,2L);
    }

    private static void insertUser(WebClient client,User newUser)
    {
        Mono<UserVo> userVoMono = client.post().uri("/user3")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.just(newUser),User.class)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve().bodyToMono(UserVo.class);

        UserVo user = userVoMono.block();
        System.out.println("用户名称"+user.getUserName());

    }

    private static void getUser(WebClient client,Long id)
    {
        Mono<UserVo> userMono = client.get()
                .uri("/user/{id}",id)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToMono(UserVo.class);

        UserVo user = userMono.block();
        System.out.println("用户名称"+user.getUserName());

    }

    public static  void getUser2(WebClient client,Long id)
    {
        Mono<UserVo> userMono = client.get().uri("/user/{id}",id)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve().onStatus(
                        status->status.is4xxClientError() || status.is5xxServerError(),
                        response -> Mono.empty()
                ).bodyToMono(UserVo.class);
        UserVo user = userMono.block();
        if (user != null)
        {
            System.out.println("用户名称"+user.getUserName());
        }else {
            System.out.println("服务器没有返回编号为"+id+"的用户");
        }
    }

    private static void updateUser(WebClient client,User updUser)
    {
        Mono<UserVo> userMono = client.put().uri("/user")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.just(updUser),User.class)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToMono(UserVo.class);
        UserVo user = userMono.block();
        System.out.println("用户名称"+user.getUserName());
    }

    public static void updateUserName(WebClient client,Long id,String userName)
    {
        Mono<UserVo> monoUserVo = client.put().uri("/user/name",userName)
                .header("id",id+"")
                .header("userName",userName)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .onStatus(
                        status->status.is4xxClientError() || status.is5xxServerError(),
                        response -> Mono.empty()
                ).bodyToMono(UserVo.class);
        UserVo userVo = monoUserVo.block();

        if(userVo != null)
        {
            System.out.println("获取用户名称为"+userVo.getUserName());
        }else {
            System.out.println("获取的用户名编号是"+id+"失败");
        }
    }

    private static void findUsers(WebClient client,String userName,String note)
    {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userName",userName);
        paramMap.put("note",note);
        Flux<UserVo> userFlux = client.get().uri("/user/{userName}/{note}",paramMap)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(UserVo.class);

        Iterator<UserVo> iterator = userFlux.toIterable().iterator();
        while (iterator.hasNext())
        {
            UserVo item = iterator.next();
            System.out.println("用户名称"+item.getUserName());
        }
    }

    private static void deleteUser(WebClient client,Long id)
    {
        Mono<Void> result = client.delete().uri("/user/{id}",id)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToMono(Void.class);

        Void voidResult = result.block();
        System.out.println(voidResult);
    }

    private static void insertUser2(WebClient client)
    {
        Mono<UserVo> userMono = client.post().uri("/user2/{user}","3-convert-0-note")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToMono(UserVo.class);
        UserVo userVo = userMono.block();
        System.out.println("用户名称："+userVo.getUserName());

    }
}
