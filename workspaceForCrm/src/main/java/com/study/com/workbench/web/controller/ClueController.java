package com.study.com.workbench.web.controller;

import com.study.com.settings.domain.Clue;
import com.study.com.settings.domain.Tran;
import com.study.com.settings.domain.User;
import com.study.com.settings.service.Imp.UserServiceImp;
import com.study.com.settings.service.UserService;
import com.study.com.utils.DateTimeUtil;
import com.study.com.utils.PrintJson;
import com.study.com.utils.ServiceFactory;
import com.study.com.utils.UUIDUtil;
import com.study.com.workbench.domain.Activity;
import com.study.com.workbench.service.ActivityService;
import com.study.com.workbench.service.ClueService;
import com.study.com.workbench.service.Imp.ActivityServiceImpl;
import com.study.com.workbench.service.Imp.ClueServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClueController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println(path);
        if ("/workbench/clue/getUser.do".equals(path)) {
            UserService userService = (UserService) ServiceFactory.getService(new UserServiceImp());
            List<User> userList = userService.getUserList();
            PrintJson.printJsonObj(response,userList);

            //ActivityService a = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        }else if ("/workbench/clue/save.do".equals(path)){
            save(request,response);
        }else if ("/workbench/clue/detail.do".equals(path)){
            detail(request,response);
        }else if ("/workbench/clue/showActivity.do".equals(path)){
            activityById(request,response);

        }else if ("/workbench/clue/deleteById.do".equals(path)){
            deleteById(request,response);
        }else if ("/workbench/clue/searchActivityById.do".equals(path)){
            searchActivityById(request,response);
        }else if ("/workbench/clue/bund.do".equals(path)){
            bund(request,response);
        }else if("/workbench/clue/openActivity.do".equals(path)){
            openActivity(request,response);
        }else if ("/workbench/clue/createConvert.do".equals(path)){
            createConvert(request,response);
        }

    }

    private void createConvert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        String clueId = request.getParameter("clueId");
        Tran tran = null;
        if ("a".equals(a)){
            tran = new Tran();
            String name = request.getParameter("name");
            String activityId = request.getParameter("activityId");
            String money = request.getParameter("money");
            String expectedDate = request.getParameter("expectedDate");
            String stage = request.getParameter("stage");
            String id = UUIDUtil.getUUID();
            String createTime = DateTimeUtil.getSysTime();
            String createBy =  ((User)request.getSession().getAttribute("user")).getName();
            tran.setId(id);
            tran.setStage(stage);
            tran.setName(name);
            tran.setMoney(money);
            tran.setExpectedDate(expectedDate);
            tran.setCreateBy(createBy);
            tran.setActivityId(activityId);
            tran.setCreateTime(createTime);
        }
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = clueService.convert(clueId,tran);
        if (flag){
            response.sendRedirect(request.getContextPath()+"/workbench/clue/index.jsp");
        }

    }

    private void openActivity(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> activityList = activityService.openActivity(id,name);
        PrintJson.printJsonObj(response,activityList);


    }

    private void bund(HttpServletRequest request, HttpServletResponse response) {
        String aid[] = request.getParameterValues("aid");
        String cid = request.getParameter("cid");
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = clueService.bund(aid,cid);
        PrintJson.printJsonFlag(response,flag);

    }

    private void searchActivityById(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("aname");
        String id = request.getParameter("id");
        Map<String,String> map = new HashMap<>();
        System.out.println(name);
        map.put("name",name);
        map.put("id",id);
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> activityList = activityService.searchActivityById(map);
        PrintJson.printJsonObj(response,activityList);

    }

    private void deleteById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = clueService.deleteById(id);
        PrintJson.printJsonFlag(response,flag);
    }

    private void activityById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        List<Activity> activityList = clueService.activityById(id);
        PrintJson.printJsonObj(response,activityList);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        Clue clue = clueService.detail(id);
        request.setAttribute("clue",clue);
        request.getRequestDispatcher("/workbench/clue/detail.jsp").forward(request,response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        //取餐
        String id = UUIDUtil.getUUID();
        String fullname = request.getParameter("fullname");
        String appellation = request.getParameter("appellation");

        String owner = request.getParameter("owner");
        String company = request.getParameter("company");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String website = request.getParameter("website");
        String mphone = request.getParameter("mphone");
        String state = request.getParameter("state");
        String source = request.getParameter("source");
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        String createTime = DateTimeUtil.getSysTime();
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        String address = request.getParameter("address");
        Clue clue = new Clue();
        clue.setAddress(address);
        clue.setWebsite(website);
        clue.setState(state);
        clue.setSource(source);
        clue.setPhone(phone);
        clue.setOwner(owner);
        clue.setNextContactTime(nextContactTime);
        clue.setMphone(mphone);
        clue.setJob(job);
        clue.setId(id);
        clue.setFullname(fullname);
        clue.setEmail(email);
        clue.setDescription(description);
        clue.setCreateTime(createTime);
        clue.setCreateBy(createBy);
        clue.setContactSummary(contactSummary);
        clue.setCompany(company);
        clue.setAppellation(appellation);
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = clueService.save(clue);
        PrintJson.printJsonFlag(response,flag);
    }
}
