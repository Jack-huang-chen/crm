package com.study.service.imp;

import com.study.dao.GoodsDao;
import com.study.domain.Goods;
import com.study.service.LoginService;
import org.springframework.stereotype.Component;

public class LoginServiceImp implements LoginService {

   private GoodsDao goodsDao;

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public int login(Goods goods) {
        int count = goodsDao.login(goods);
        return count;
    }
}
