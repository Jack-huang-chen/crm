package com.study.service.imp;

import com.github.pagehelper.PageHelper;
import com.study.dao.IProductDao;
import com.study.dao.MemberDao;
import com.study.dao.OrderDao;
import com.study.dao.TravellerDao;
import com.study.domain.Member;
import com.study.domain.Orders;
import com.study.domain.Product;
import com.study.domain.Traveller;
import com.study.service.OrderService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private IProductDao iProductDao;
    @Autowired
    private TravellerDao travellerDao;
    @Autowired
    private MemberDao memberDao;

    @Override
    public List<Orders> findAll(int page,int size) {
        //先查出所有订单


        PageHelper.startPage(page, size);
        List<Orders> ordersList = orderDao.findAll();
        for (Orders o:ordersList
             ) {

            Product product = iProductDao.findById(o.getProductId());
            o.setProduct(product);

        }


        return ordersList;
    }

    @Override
    public Orders findById(String ordersId) {
        //先查出orders的memberid
        Orders orders = orderDao.findById(ordersId);
        Member member = memberDao.findById(orders.getMemberId());
        //再查product
        Product product = iProductDao.findById(orders.getProductId());
        //最后查traveller
        List<Traveller> travellerList = travellerDao.findById(ordersId);
        orders.setProduct(product);
        orders.setMember(member);
        orders.setTravellers(travellerList);
        return orders;
    }
}
