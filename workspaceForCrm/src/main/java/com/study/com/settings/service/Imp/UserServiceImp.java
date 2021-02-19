package com.study.com.settings.service.Imp;

import com.study.com.exception.LoginException;
import com.study.com.settings.dao.UserDao;
import com.study.com.settings.domain.User;
import com.study.com.settings.service.UserService;
import com.study.com.utils.DateTimeUtil;
import com.study.com.utils.ServiceFactory;
import com.study.com.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImp implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        //判断用户存在
        Map<String,String> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userDao.login(map);
        if (user == null){

            throw new LoginException("账号密码错误");
        }
        //程序执行到此行，说明密码账号zhengque


        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        //比较系统时间和失效时间
        if(expireTime.compareTo(currentTime) > 0){
            throw new LoginException("账号过期");

        }
        //判断锁定状态
        String lockState = user.getLockState();
        if ("0".equals(lockState)){
            throw new LoginException("账号已锁定");
        }

        //判断ip地址
        String allowIps = user.getAllowIps();
        if (!allowIps.contains(ip)){
            throw new LoginException("ip地址错误");
        }

        return user;

    }

    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.getUserList();
        return userList;
    }


}
