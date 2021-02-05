package com.study.service;

import com.study.domain.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface OrderService {

    List<Orders> findAll(int page,int size);

    Orders findById(String ordersId);
}
