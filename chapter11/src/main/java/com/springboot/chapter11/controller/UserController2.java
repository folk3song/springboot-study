package com.springboot.chapter11.controller;

import com.springboot.chapter11.UserService.UserService;
import com.springboot.chapter11.enumeration.SexEnum;
import com.springboot.chapter11.exception.NotFoundException;
import com.springboot.chapter11.pojo.User;
import com.springboot.chapter11.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController2 {
    @Autowired
    UserService userService = null;
    @PostMapping(value = "/user2/entity")
    public ResponseEntity<UserVo> insertUserEntity(@RequestBody UserVo userVo)
    {
        User user = this.changeToPo(userVo);
        userService.insertUser(user);
        UserVo result = this.changeToVo(user);
        HttpHeaders headers = new HttpHeaders();
        String success = (result == null || result.getId()==null)?"false":"true";
        headers.add("success",success);
        return new ResponseEntity<UserVo>(result,headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "/user2/annotation")
    @ResponseStatus(HttpStatus.CREATED)
    public UserVo insertUserAnnotation(@RequestBody UserVo userVo)
    {
        User user = this.changeToPo(userVo);
        userService.insertUser(user);
        UserVo result = this.changeToVo(user);
        return result;
    }

    @GetMapping(value = "/user/exp/{id}",
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserVo getUserForExp(@PathVariable("id") Long id)
    {
        User user = userService.getUser(id);
        if(user == null )
        {
            throw new NotFoundException(1L,"找不到id为"+id+"的用户");
        }

        UserVo userVo = changeToVo(user);
        return userVo;
    }


    private User changeToPo(UserVo userVo)
    {
        User user = new User();
        user.setId(userVo.getId());
        user.setUserName(userVo.getUserName());
        user.setSex(SexEnum.getEnumById(userVo.getSexCode()));
        user.setNote(userVo.getNote());
        return user;
    }

    private UserVo changeToVo(User user)
    {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUserName(user.getUserName());
        userVo.setSexCode(user.getSex().getId());
        userVo.setSexName(user.getSex().getName());
        userVo.setNote(user.getNote());
        return userVo;
    }
}
