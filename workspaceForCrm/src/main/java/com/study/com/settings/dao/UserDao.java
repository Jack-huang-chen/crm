package com.study.com.settings.dao;

import com.study.com.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {


    User login(Map<String, String> map);

    List<User> getUserList();
}
