package com.springboot.chapter14.handler;

import com.springboot.chapter14.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.chapter14.pojo.User;
import com.springboot.chapter14.vo.UserVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class User2Handler {
    @Autowired
    private UserRepository userRepository;
    public Mono<ServerResponse> getUser(ServerRequest request)
    {

        String idStr = request.pathVariable("id");
        Long id = Long.valueOf(idStr);
        Mono<UserVo> userVoMono = userRepository.findById(id).map(u->translate(u));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userVoMono,UserVo.class);

    }

    public Mono<ServerResponse> insertUser(ServerRequest request)
    {
        Mono<User> userMonoParam = request.bodyToMono(User.class);
        Mono<UserVo> userVoMono = userMonoParam.cache()
                .flatMap(user -> userRepository.save(user)
                        .map(this::translate));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userVoMono,UserVo.class);
    }

    public Mono<ServerResponse> updateUser(ServerRequest request)
    {
        Mono<User> userMonoParam = request.bodyToMono(User.class);
        Mono<UserVo> userVoMono = userMonoParam.cache()
                .flatMap(user -> userRepository.save(user)
                        .map(this::translate));

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userVoMono,UserVo.class);

    }
    public Mono<ServerResponse> deleteUser(ServerRequest request)
    {
        String idStr = request.pathVariable("id");
        Long id  = Long.valueOf(idStr);
        Mono<Void> monoVoid = userRepository.deleteById(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(monoVoid,Void.class);


    }

    public Mono<ServerResponse> findUsers(ServerRequest request)
    {
        String userName = request.pathVariable("userName");
        String note = request.pathVariable("note");
        Flux<UserVo> userVoFlux = userRepository.findByUserNameLikeAndNoteLike(userName,note)
                .map(this::translate);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userVoFlux,UserVo.class);

    }
    public Mono<ServerResponse> updateUserName(ServerRequest request)
    {
        String idStr = request.headers().header("id").get(0);
        Long id = Long.valueOf(idStr);
        String userName = request.headers().header("userName").get(0);

        Mono<User> userMono = userRepository.findById(id);
        User user = userMono.block();
        user.setUserName(userName);
        Mono<UserVo> result = userRepository.save(user).map(this::translate);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(result,UserVo.class);

    }

    private UserVo translate(User user)
    {
        UserVo userVo = new UserVo();
        userVo.setUserName(user.getUserName());
//        userVo.setSexCode(user.getSex().getCode());
//        userVo.setSexName(user.getSex().getName());
        userVo.setNote(user.getNote());
        userVo.setId(user.getId());
        return userVo;
    }

}
