package com.study.com.workbench.web.controller;

import com.study.com.settings.domain.Customer;
import com.study.com.settings.domain.Tran;
import com.study.com.settings.domain.TranHistory;
import com.study.com.settings.domain.User;
import com.study.com.settings.service.Imp.UserServiceImp;
import com.study.com.settings.service.UserService;
import com.study.com.utils.DateTimeUtil;
import com.study.com.utils.PrintJson;
import com.study.com.utils.ServiceFactory;
import com.study.com.utils.UUIDUtil;
import com.study.com.workbench.service.CustomerService;
import com.study.com.workbench.service.Imp.ClueServiceImpl;
import com.study.com.workbench.service.Imp.CustomerServiceIpl;
import com.study.com.workbench.service.Imp.TranServiceImpl;
import com.study.com.workbench.service.TranService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println(path);
    if ("/workbench/transaction/add.do".equals(path)){
        add(request,response);
    }else if ("/workbench/transaction/getCustomerName.do".equals(path)){
        getCustomerName(request,response);
    }else if ("/workbench/transaction/tranSave.do".equals(path)){
        tranSave(request,response);
    }else if ("/workbench/transaction/detail.do".equals(path)){
        detail(request,response);


    }else if ("/workbench/transaction/showHistory.do".equals(path)){
        showHistory(request,response);
    }else if ("/workbench/transaction/changeStage.do".equals(path)){
        changeStage(request,response);

    }else if("/workbench/transaction/getCharts.do".equals(path)){
        getCharts(request,response);
    }





    }

    private void getCharts(HttpServletRequest request, HttpServletResponse response) {
          TranService tranService = (TranService) ServiceFactory.getService(new TranServiceImpl());
          Map<String,Object> map = tranService.getCharts();
          PrintJson.printJsonObj(response,map);

    }

    private void changeStage(HttpServletRequest request, HttpServletResponse response) {
        String stage = request.getParameter("stage");
        String tranId = request.getParameter("tranId");
        String expectedDate = request.getParameter("expectedDate");
        String money = request.getParameter("money");
        String editTime = DateTimeUtil.getSysTime();
        String editBy =  ((User)request.getSession().getAttribute("user")).getName();
        Map<String,String> pmap = (Map<String, String>) request.getServletContext().getAttribute("pmap");
        String possibility = pmap.get(stage);

        Tran tran = new Tran();
        tran.setStage(stage);
        tran.setPossibility(possibility);
        tran.setId(tranId);
        tran.setMoney(money);
        tran.setExpectedDate(expectedDate);
        tran.setEditTime(editTime);
        tran.setEditBy(editBy);
        TranService tranService = (TranService) ServiceFactory.getService(new TranServiceImpl());
        boolean flag = tranService.changeStage(tran);
        Map map = new HashMap();
        map.put("success",flag);
        map.put("tran",tran);
        PrintJson.printJsonObj(response,map);
    }

    private void showHistory(HttpServletRequest request, HttpServletResponse response) {
        String tranId = request.getParameter("tranId");
        TranService tranService = (TranService) ServiceFactory.getService(new TranServiceImpl());
        List<TranHistory> tranHistoryList = tranService.showHistory(tranId);
        Map<String,String> pmap = (Map<String, String>) request.getServletContext().getAttribute("pmap");
        for (TranHistory t:tranHistoryList
             ) {
            String stage = t.getStage();
            String possibility = pmap.get(stage);
            t.setPossibility(possibility);

        }
        PrintJson.printJsonObj(response,tranHistoryList);

    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String id = request.getParameter("id");
    TranService tranService = (TranService) ServiceFactory.getService(new TranServiceImpl());
    Tran tran = tranService.detail(id);
    String stage = tran.getStage();
    Map<String,String> map = (Map<String, String>) request.getServletContext().getAttribute("pmap");
    String possibility = map.get(stage);
    tran.setPossibility(possibility);
    request.setAttribute("tran",tran);
    request.getRequestDispatcher("/workbench/transaction/detail.jsp").forward(request,response);



    }

    private void tranSave(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String money = request.getParameter("money");
        String name = request.getParameter("name");
        String expectedDate = request.getParameter("expectedDate");
        String customerName = request.getParameter("customerName");
        String stage = request.getParameter("stage");
        String type = request.getParameter("type");
        String source = request.getParameter("source");
        String activityId = request.getParameter("activityId");
        String contactsId = request.getParameter("contactsId");
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        String createTime = DateTimeUtil.getSysTime();
        String createBy =  ((User)request.getSession().getAttribute("user")).getName();
        Tran tran = new Tran();
        tran.setType(type);
        tran.setStage(stage);
        tran.setName(name);
        tran.setMoney(money);
        tran.setExpectedDate(expectedDate);
        tran.setCreateBy(createBy);
        tran.setActivityId(activityId);
        tran.setCreateTime(createTime);
        tran.setId(id);
        tran.setSource(source);
        tran.setOwner(owner);
        tran.setDescription(description);
        tran.setNextContactTime(nextContactTime);
        tran.setContactsId(contactsId);
        tran.setContactSummary(contactSummary);
        TranService tranService = (TranService) ServiceFactory.getService(new TranServiceImpl());
        boolean flag = tranService.tranSave(tran,customerName);
        response.sendRedirect(request.getContextPath() + "/workbench/transaction/index.jsp");

    }

    private void getCustomerName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        CustomerService customerService = (CustomerService) ServiceFactory.getService(new CustomerServiceIpl());
        List<Customer> customerList = customerService.getCustomerName(name);
        PrintJson.printJsonObj(response,customerList);

    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImp());
        List<User> userList = userService.getUserList();
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("/workbench/transaction/save.jsp").forward(request,response);


    }

}
