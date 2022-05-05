package com.example.demo.controller;

import com.example.demo.model.entity.User;
import com.example.demo.model.vo.UserVo;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 添加用户
     * @param user
     * @throws Exception
     */
    @PostMapping("")
    public void save(@RequestBody User user) throws Exception {
        userService.save(user);
    }

    /**
     * 登录
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public UserVo login(@RequestBody User user) throws Exception{
        return userService.login(user);
    }

    /**
     * 修改用户密码
     * @param userId
     * @param password
     * @param newPassword
     * @throws Exception
     */
    @PostMapping("/update/{userId}/{password}/{newPassword}")
    public void updateUserPassword(@PathVariable("userId") String userId,
       @PathVariable("password") String password, @PathVariable("newPassword") String newPassword) throws Exception {
        userService.updatePassword(userId,password,newPassword);
    }

    /**
     * 修改用户昵称
     * @param userId
     * @param nickname
     */
    @PostMapping("/update/{userId}/{nickname}")
    public void login(@PathVariable("userId") String userId, @PathVariable("nickname") String nickname) {
        userService.updateNickName(userId, nickname);
    }
}
