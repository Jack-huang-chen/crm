package com.study.service.imp;

import com.study.dao.GoodsDao;
import com.study.dao.SaleDao;
import com.study.domain.Goods;
import com.study.domain.Sale;
import com.study.exception.NumMaxException;
import com.study.service.BuyService;

public class BuyServiceImp implements BuyService {
    private SaleDao saleDao;
    private GoodsDao goodsDao;

    public SaleDao getSaleDao() {
        return saleDao;
    }

    public void setSaleDao(SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    public GoodsDao getGoodsDao() {
        return goodsDao;
    }

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public void buy(Integer id, Integer num) {
        Sale sale = new Sale();
        sale.setGid(id);
        sale.setNum(num);
        saleDao.insertSale(sale);

        //查库存
        Goods goods = goodsDao.selectGoods(id);
        if (goods == null){
            throw new  NullPointerException(id + "该商品不存在");

        }
        if (goods.getAmount() < num){
            throw new NumMaxException("库存不足");
        }
        Goods goods1 = new Goods();
        goods1.setId(id);
        goods1.setAmount(num);
        int count = goodsDao.updateGoods(goods1);
    }
}
