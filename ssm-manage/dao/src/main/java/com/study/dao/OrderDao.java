package com.study.dao;

import com.study.domain.Orders;

import java.util.List;

public interface OrderDao {
    List<Orders>  findAll();

    Orders findById(String ordersId);
}
