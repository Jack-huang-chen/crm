package com.study.com.workbench.service.Imp;

import com.study.com.settings.domain.Customer;
import com.study.com.settings.domain.Tran;
import com.study.com.settings.domain.TranHistory;
import com.study.com.utils.SqlSessionUtil;
import com.study.com.utils.UUIDUtil;
import com.study.com.workbench.dao.CustomerDao;
import com.study.com.workbench.dao.TranDao;
import com.study.com.workbench.dao.TranHistoryDao;
import com.study.com.workbench.service.TranService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranServiceImpl implements TranService {
    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    private TranDao tranDao = SqlSessionUtil.getSqlSession().getMapper(TranDao.class);
    private TranHistoryDao tranHistoryDao = SqlSessionUtil.getSqlSession().getMapper(TranHistoryDao.class);
    @Override
    public boolean tranSave(Tran tran, String customerName) {
        boolean flag = true;
        //判断客户存在不
        Customer customer = customerDao.getCustomerByName(customerName);
        //不存在就新建一个
        if (customer == null) {
            customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setOwner(tran.getOwner());
            customer.setNextContactTime(tran.getNextContactTime());
            customer.setName(tran.getName());
            customer.setDescription(tran.getDescription());
            customer.setCreateTime(tran.getCreateTime());
            customer.setCreateBy(tran.getCreateBy());
            customer.setContactSummary(tran.getContactSummary());
            int count1 = customerDao.save(customer);
            if (count1 != 1){
                return false;
            }
        }
        //添加交易记录
        tran.setCustomerId(customer.getId());
        int count2 = tranDao.save(tran);
        if (count2 != 1){
            return false;
        }
        //添加交易历史
        TranHistory tranHistory = new TranHistory();
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setTranId(tran.getId());
        tranHistory.setStage(tran.getStage());
        tranHistory.setCreateTime(tran.getCreateTime());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setCreateBy(tran.getCreateBy());
        int count3 = tranHistoryDao.save(tranHistory);
        if (count3 != 1){
            flag = false;
        }





        return flag;
    }

    @Override
    public Tran detail(String id) {
        Tran tran = tranDao.detail(id);
        return tran;
    }

    @Override
    public List<TranHistory> showHistory(String tranId) {
        List<TranHistory> tranHistoryList = tranHistoryDao.showHistory(tranId);
        return tranHistoryList;
    }

    @Override
    public boolean changeStage(Tran tran) {
        boolean flag = true;
        int count = tranDao.changeStage(tran);
        if (count != 1){
            flag = false;
        }
        TranHistory tranHistory = new TranHistory();
        tranHistory.setPossibility(tran.getPossibility());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setCreateBy(tran.getEditBy());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setCreateTime(tran.getEditTime());
        tranHistory.setStage(tran.getStage());
        tranHistory.setTranId(tran.getId());
        tranHistory.setId(UUIDUtil.getUUID());
        int count2 = tranHistoryDao.save(tranHistory);
        if (count2 != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getCharts() {
        int total = tranDao.getTotal();
        List<Map<String,Object>> dataList = tranDao.getCharts();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("dataList",dataList);
        return map;
    }
}
