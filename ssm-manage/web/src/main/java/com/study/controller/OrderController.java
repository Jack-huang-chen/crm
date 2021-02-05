package com.study.controller;


import com.github.pagehelper.PageInfo;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.study.domain.Orders;
import com.study.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
   // @RequestMapping("/findAll.do")
    /**public ModelAndView findAll(){
        List<Orders> ordersList = orderService.findAll();
        ModelAndView ma = new ModelAndView();
        ma.addObject("ordersList",ordersList);
        ma.setViewName("orders-list");

        return ma;
    }**/
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1")Integer page

    ,@RequestParam(name = "size",defaultValue = "4")Integer size) {

        List<Orders> ordersList = orderService.findAll(page,size);
       ModelAndView ma = new ModelAndView();
        PageInfo pageInfo = new PageInfo(ordersList);
        ma.addObject("pageInfo",pageInfo);
        ma.setViewName("orders-page-list");

        return ma;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView ma = new ModelAndView();
        Orders orders = orderService.findById(id);
        ma.addObject("orders",orders);
        ma.setViewName("orders-show");
        return ma;

    }

}
