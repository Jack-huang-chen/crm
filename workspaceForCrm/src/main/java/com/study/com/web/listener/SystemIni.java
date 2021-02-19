package com.study.com.web.listener;

import com.study.com.settings.service.DicService;
import com.study.com.settings.service.Imp.DicServiceImpl;
import com.study.com.utils.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

public class SystemIni implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("hahaha");
        ServletContext application = event.getServletContext();
        //service层
        DicService dicService = (DicService) ServiceFactory.getService(new DicServiceImpl());
        Map<String, List> map = dicService.getAll();
        Set<String> stringSet = map.keySet();
        for (String key:stringSet
             ) {
            System.out.println(key);
            List list = map.get(key);
            application.setAttribute(key,list);

        }
        //
        Map<String,String> pmap = new HashMap<>();
        ResourceBundle rb = ResourceBundle.getBundle("Stage2Possibility");
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()){

            //取出value
            String key = keys.nextElement();
            String value = rb.getString(key);
           pmap.put(key,value);
        }

        application.setAttribute("pmap",pmap);



    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("结束");

    }
}
