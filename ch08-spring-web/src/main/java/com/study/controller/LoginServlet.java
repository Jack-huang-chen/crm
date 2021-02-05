package com.study.controller;

import com.study.domain.Goods;
import com.study.service.BuyService;
import com.study.service.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String amount = request.getParameter("amount");
        String price = request.getParameter("price");
        Integer amount1 = Integer.valueOf(amount);
        Integer price1 = Integer.valueOf(price);
        Goods goods = new Goods();
        goods.setAmount(amount1);
        goods.setName(name);
        goods.setPrice(price1);
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        LoginService loginService = (LoginService) ac.getBean("loginServiceImp");
        loginService.login(goods);
        request.getRequestDispatcher("/result.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
