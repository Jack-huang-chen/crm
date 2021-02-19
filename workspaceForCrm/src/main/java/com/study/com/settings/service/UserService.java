package com.study.com.settings.service;

import com.study.com.exception.LoginException;
import com.study.com.settings.domain.User;

import java.util.List;

public interface UserService {
    public User login(String loginAct,String loginPwd,String ip) throws LoginException;

    List<User> getUserList();
}
