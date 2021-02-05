package com.study.com.settings.service.Imp;

import com.study.com.settings.dao.DicTypeDao;
import com.study.com.settings.dao.DicValueDao;
import com.study.com.settings.domain.DicType;
import com.study.com.settings.domain.DicValue;
import com.study.com.settings.service.DicService;
import com.study.com.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicServiceImpl implements DicService{
    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

    @Override
    public Map<String, List> getAll() {
        Map<String,List> map = new HashMap<>();
        List<DicType> typeList = dicTypeDao.getType();
        //获得code
        for (DicType dicType:typeList
             ) {
            String code = dicType.getCode();
            List<DicValue> dicValues = dicValueDao.getValue(code);
            map.put(code,dicValues);


        }
        return map;



    }
}
