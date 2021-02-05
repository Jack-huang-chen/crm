package com.study.dao;

import com.study.domain.Traveller;

import java.util.List;

public interface TravellerDao {
    List<Traveller> findById(String ordersId);
}
