package com.study.com.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //过滤post
        servletRequest.setCharacterEncoding("UTF-8");
        //响应
        servletResponse.setContentType("application/json;charset=utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
