package com.study.dao;

import com.study.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    List<Product> findAll();

    void productSave(Product product);


    Product findById(String productId);
}
