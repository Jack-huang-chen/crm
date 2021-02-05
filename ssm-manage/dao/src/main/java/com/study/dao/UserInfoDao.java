package com.study.dao;

import com.study.domain.UserInfo;

public interface UserInfoDao {
    UserInfo findByName(String s);
}
