package com.study.service.imp;

import com.study.dao.IProductDao;
import com.study.domain.Product;
import com.study.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class IProductServiceImp implements IProductService {
    @Autowired
    private IProductDao iProductDao;
    @Override
    public List<Product> findAll() {
        return null;
    }
}
