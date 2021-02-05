package com.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BimCountServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name1 = request.getParameter("name");
        String height1 = request.getParameter("h");
        String wight1 = request.getParameter("w");
        float h = Float.valueOf(height1);
        float w = Float.valueOf(wight1);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("健康");
        System.out.println("it is ok");
    }
}
