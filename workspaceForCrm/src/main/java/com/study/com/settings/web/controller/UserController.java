package com.study.com.settings.web.controller;

import com.mysql.cj.Session;
import com.study.com.settings.domain.User;
import com.study.com.settings.service.Imp.UserServiceImp;
import com.study.com.settings.service.UserService;
import com.study.com.utils.MD5Util;
import com.study.com.utils.PrintJson;
import com.study.com.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();
        System.out.println(path);
        if ("/settings/user/save.do".equals(path)){

            //获取参数
            login(request,response);

        }


    }

    public void login(HttpServletRequest request, HttpServletResponse response) {


        String loginAct = request.getParameter("loginAct");
        String logigPwd = request.getParameter("loginPwd");
        //将密码转为明文
        logigPwd = MD5Util.getMD5(logigPwd);
        //获得ip地址
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImp());


        try {
            User user = us.login(loginAct,logigPwd,ip);
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            PrintJson.printJsonFlag(response,true);



        }catch (Exception e){
            e.getStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);


        }



    }

}
