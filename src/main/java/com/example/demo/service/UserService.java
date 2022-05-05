package com.example.demo.service;

import com.example.demo.model.entity.User;
import com.example.demo.model.vo.UserVo;

public interface UserService {

    void save(User user) throws Exception;

    UserVo login(User user) throws Exception;

    void updatePassword(String userId, String password, String newPassword) throws Exception;

    void updateNickName(String userId, String nickname);
}
