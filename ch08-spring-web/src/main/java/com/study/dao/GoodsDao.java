package com.study.dao;

import com.study.domain.Goods;

public interface GoodsDao {
    public Goods selectGoods(int id);
    public int updateGoods(Goods goods);
    public int login(Goods goods);
}
