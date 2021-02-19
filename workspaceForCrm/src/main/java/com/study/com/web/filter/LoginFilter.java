package com.study.com.web.filter;

import com.study.com.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest  = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("user");
        if (user != null){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else {
            String path = httpServletRequest.getServletPath();
            if ("/login.jsp".equals(path)||"/settings/user/save.do".equals(path)){
                filterChain.doFilter(httpServletRequest,httpServletResponse);
                return;

            }
            httpServletResponse.sendRedirect("/crm/login.jsp");//重定向绝对路径
        }
    }
}
