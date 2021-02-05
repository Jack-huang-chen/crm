package com.study.com.workbench.web.controller;

import com.study.com.settings.domain.User;
import com.study.com.settings.service.Imp.UserServiceImp;
import com.study.com.settings.service.UserService;
import com.study.com.utils.*;
import com.study.com.vo.Pagination;
import com.study.com.workbench.dao.ActivityDao;
import com.study.com.workbench.domain.Activity;
import com.study.com.workbench.domain.ActivityRemark;
import com.study.com.workbench.service.ActivityService;
import com.study.com.workbench.service.Imp.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();
        System.out.println(path);
        if ("/workbench/activity/getUserList.do".equals(path)){
            //ActivityService a = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());
            UserService us = (UserService) ServiceFactory.getService(new UserServiceImp());
            List<User> userList = us.getUserList();
            PrintJson.printJsonObj(response,userList);

        }else if("/workbench/activity/save.do".equals(path)){
            String id = UUIDUtil.getUUID();

            String owner = request.getParameter("owner");
            String name  =  request.getParameter("name");
            String startDate = request.getParameter("startDate");

            String endDate  = request.getParameter("endDate") ;
            String cost = request.getParameter("cost");        ;
            String description = request.getParameter("description")   ;
            String createTime = DateTimeUtil.getSysTime();
            String createBy =  ((User)request.getSession().getAttribute("user")).getName();
            //放再对象中传过去
            Activity activity = new Activity();
            activity.setId(id);
            activity.setOwner(owner);
            activity.setStartDate(startDate);
            activity.setName(name);
            activity.setEndDate(endDate);
            activity.setCost(cost);
            activity.setDescription(description);
            activity.setCreateTime(createTime);
            activity.setCreateBy(createBy);
            ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
            boolean flag = activityService.save(activity);
            PrintJson.printJsonFlag(response,flag);


        }else if("/workbench/activity/pageList.do".equals(path)){
            pageList(request,response);
        }else if ("/workbench/activity/delete.do".equals(path)){
            delete(request,response);
        }else if ("/workbench/activity/edit.do".equals(path)){
            edit(request,response);
        }else if ("/workbench/activity/update.do".equals(path)){
            update(request,response);
        }else if("/workbench/activity/detail.do".equals(path)){
            detail(request,response);
        }else if("/workbench/activity/getActivityRemark.do".equals(path)){
            getActivityRemark(request,response);

        }else if ("/workbench/activity/deleteById.do".equals(path)){
            deleteRemarkById(request,response);
        }else if("/workbench/activity/saveRemark.do".equals(path)){
            saveRemark(request,response);
        }else if ("/workbench/activity/updateRemark.do".equals(path)){
            updateRemark(request,response);
        }


    }

    private void updateRemark(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String content = request.getParameter("noteContent");
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "1";
        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setNoteContent(content);
        activityRemark.setEditFlag(editFlag);
        activityRemark.setEditBy(editBy);
        activityRemark.setEditTime(editTime);
        activityRemark.setId(id);
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = activityService.updateRemark(activityRemark);
        Map<String,Object> map = new HashMap<>();
        map.put("ar",activityRemark);
        map.put("success",flag);
        PrintJson.printJsonObj(response,map);
    }

    private void saveRemark(HttpServletRequest request, HttpServletResponse response) {
        String activityId = request.getParameter("id");
        String noteContent = request.getParameter("noteContent");
        String id = UUIDUtil.getUUID();

        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        ActivityRemark ar = new ActivityRemark();
        ar.setId(id);
        ar.setActivityId(activityId);
        ar.setCreateBy(createBy);
        ar.setCreateTime(createTime);
        ar.setNoteContent(noteContent);
        ar.setEditFlag("0");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = activityService.saveRemark(ar);
        Map<String,Object> map = new HashMap<>();
        map.put("success",flag);
        map.put("ar",ar);
        PrintJson.printJsonObj(response,map);

    }

    private void deleteRemarkById(HttpServletRequest request, HttpServletResponse response) {
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        String id = request.getParameter("id");
        boolean flag = activityService.deleteRemarkById(id);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getActivityRemark(HttpServletRequest request, HttpServletResponse response) {
       String activityId = request.getParameter("activityId");
       ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
       List<ActivityRemark> activityRemark = activityService.getActivityRemark(activityId);
       PrintJson.printJsonObj(response,activityRemark);




    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        String id = request.getParameter("id");
        Activity a = activityService.detail(id);
        request.setAttribute("a",a);
        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request,response);

    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("hahaha");
        String id = request.getParameter("id");

        String owner = request.getParameter("owner");
        String name  =  request.getParameter("name");
        String startDate = request.getParameter("startDate");

        String endDate  = request.getParameter("endDate") ;
        String cost = request.getParameter("cost");        ;
        String description = request.getParameter("description")   ;
        String editTime = DateTimeUtil.getSysTime();
        String edittBy =  ((User)request.getSession().getAttribute("user")).getName();
        //放再对象中传过去
        Activity activity = new Activity();
        activity.setId(id);
        activity.setOwner(owner);
        activity.setStartDate(startDate);
        activity.setName(name);
        activity.setEndDate(endDate);
        activity.setCost(cost);
        activity.setDescription(description);
        activity.setEditTime(editTime);
        activity.setEditBy(edittBy);
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = activityService.update(activity);
        PrintJson.printJsonFlag(response,flag);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Map<String,Object> map = activityService.edit(id);
        PrintJson.printJsonObj(response,map);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        //接收参数
        String para[] = request.getParameterValues("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = activityService.delete(para);
        PrintJson.printJsonFlag(response,flag);

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        //接收参数
        String pageNoStr = request.getParameter("pageNo");
        String pageSizeStr = request.getParameter("pageSize");
        String name = request.getParameter("name");
        String owner= request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        int pageSize = Integer.valueOf(pageSizeStr);
        int pageNo = Integer.valueOf(pageNoStr);
        int skipCount = (pageNo - 1)*pageSize;
        Map<String,Object> map  = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Pagination<Activity> vo = activityService.pageList(map);
        PrintJson.printJsonObj(response,vo);


    }


}
