package com.study.com.workbench.service.Imp;

import com.study.com.settings.domain.*;
import com.study.com.utils.DateTimeUtil;
import com.study.com.utils.SqlSessionUtil;
import com.study.com.utils.UUIDUtil;
import com.study.com.workbench.dao.*;
import com.study.com.workbench.domain.Activity;
import com.study.com.workbench.service.ClueService;

import java.util.List;

public class ClueServiceImpl implements ClueService {
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);
    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    private ContactsDao contactsDao = SqlSessionUtil.getSqlSession().getMapper(ContactsDao.class);
    private ClueRemarkDao clueRemarkDao= SqlSessionUtil.getSqlSession().getMapper(ClueRemarkDao.class);
    private ContactsRemarkDao contactsRemarkDao= SqlSessionUtil.getSqlSession().getMapper(ContactsRemarkDao.class);
    private CustomerRemarkDao customerRemarkDao = SqlSessionUtil.getSqlSession().getMapper(CustomerRemarkDao.class);
    private ContactsActivityRelationDao contactsActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ContactsActivityRelationDao.class);
    private TranDao tranDao = SqlSessionUtil.getSqlSession().getMapper(TranDao.class);
    private TranHistoryDao tranHistoryDao = SqlSessionUtil.getSqlSession().getMapper(TranHistoryDao.class);
    @Override
    public boolean save(Clue clue) {
        boolean flag = true;
        int count = clueDao.save(clue);
        if (count!=1){
            flag = false;
        }
        return flag;
    }

    @Override
    public Clue detail(String id) {
        Clue clue = clueDao.detail(id);
        return clue;
    }

    @Override
    public List<Activity> activityById(String id) {
        List<Activity> activityList = clueDao.activityById(id);
        return activityList;
    }

    @Override
    public boolean deleteById(String id) {
        boolean flag = true;
        int count = clueDao.deleteById(id);
        if (count != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean bund(String[] aid, String cid) {
        boolean flag = true;
        int count = 0;
        for (String a:aid
             ) {
            String id = UUIDUtil.getUUID();
            ClueActivityRelation car = new ClueActivityRelation();
            car.setId(id);
            car.setActivityId(a);
            car.setClueId(cid);
            count += clueActivityRelationDao.bund(car);



        }
        if (count != aid.length){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean convert(String clueId, Tran tran) {
        String createTime = DateTimeUtil.getSysTime();
        boolean flag = true;
        //1.通过线索id获取对象
        Clue clue = clueDao.getClue(clueId);
        //2.通过线索对象提取客户信息，当该客户不存在的时候，新建客户（根据公司的名称精确匹配，判断该客户是否存在！）
        String company = clue.getCompany();
        Customer customer = customerDao.getCustomerByName(company);
        if (customer == null){
            customer = new Customer();

            customer.setId(UUIDUtil.getUUID());
            customer.setWebsite(clue.getWebsite());
            customer.setPhone(clue.getPhone());
            customer.setOwner(clue.getOwner());
            customer.setNextContactTime(clue.getNextContactTime());
            customer.setName(clue.getCompany());
            customer.setDescription(clue.getDescription());
            customer.setCreateTime(clue.getCreateTime());
            customer.setCreateBy(clue.getCreateBy());
            customer.setContactSummary(clue.getContactSummary());
            customer.setAddress(clue.getAddress());
            int count1 = customerDao.save(customer);
            if (count1 != 1){
                flag = false;
            }


        }
        //3.通过线索对象提取联系人信息，保存联系人
        Contacts contacts = new Contacts();
        contacts.setOwner(clue.getOwner());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setMphone(clue.getMphone());
        contacts.setJob(clue.getJob());
        contacts.setId(UUIDUtil.getUUID());
        contacts.setFullname(clue.getFullname());
        contacts.setEmail(clue.getEmail());
        contacts.setDescription(clue.getDescription());
        contacts.setCustomerId(customer.getId());
        contacts.setSource(clue.getSource());
        contacts.setCreateTime(createTime);
        contacts.setCreateBy(clue.getCreateBy());
        contacts.setAppellation(clue.getAppellation());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setAddress(clue.getAddress());
        int count2 = contactsDao.save(contacts);
        if (count2 != 1){
            flag = false;
        }
        //4.线索备注转换到客户备注以及联系人备注
        List<ClueRemark> clueRemarkList = clueRemarkDao.getclueRemarkByClueId(clueId);
        for (ClueRemark c:clueRemarkList
             ) {
            ContactsRemark contactsRemark = new ContactsRemark();
            contactsRemark.setNoteContent(c.getNoteContent());
            contactsRemark.setEditFlag("0");
            contactsRemark.setCreateTime(createTime);
            contactsRemark.setCreateBy(clue.getCreateBy());
            contactsRemark.setContactsId(contacts.getId());
            contactsRemark.setId(UUIDUtil.getUUID());
            int count4 = contactsRemarkDao.save(contactsRemark);
            if (count4 != 1){
                flag = false;
            }
            CustomerRemark customerRemark = new CustomerRemark();
            customerRemark.setNoteContent(c.getNoteContent());
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setEditFlag("0");
            customerRemark.setCustomerId(customer.getId());
            customerRemark.setCreateTime(createTime);
            customerRemark.setCreateBy(clue.getCreateBy());
            int count5 = customerRemarkDao.save(customerRemark);
            if (count5 != 1){
                flag = false;
            }




        }
        //5.“线索和市场活动”的关系转换到“联系人和市场活动”的关系
        List<ClueActivityRelation> clueActivityRelations = clueActivityRelationDao.getAcrByClueId(clueId);
        for (ClueActivityRelation c:clueActivityRelations
             ) {
            ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityId(c.getActivityId());
            contactsActivityRelation.setContactsId(contacts.getId());
            int count6 = contactsActivityRelationDao.save(contactsActivityRelation);
            if (count6 != 1){
                flag = false;
            }
        }
        //6.如果有创建交易需求，创建一条交易
        if (tran != null){
            tran.setSource(clue.getSource());
            tran.setOwner(clue.getOwner());
            tran.setDescription(clue.getDescription());
            tran.setNextContactTime(clue.getNextContactTime());
            tran.setCustomerId(customer.getId());
            tran.setContactsId(contacts.getId());
            tran.setContactSummary(clue.getContactSummary());
            int count7 = tranDao.save(tran);
            if (count7 != 1){
                flag = false;
            }
            //7.如果创建了交易，则创建一条该交易下的交易历史
            TranHistory tranHistory = new TranHistory();
            tranHistory.setTranId(tran.getId());
            tranHistory.setStage(tran.getStage());
            tranHistory.setMoney(tran.getMoney());
            tranHistory.setExpectedDate(tran.getExpectedDate());
            tranHistory.setCreateTime(createTime);
            tranHistory.setCreateBy(tran.getCreateBy());
            tranHistory.setId(UUIDUtil.getUUID());
            int count8 = tranHistoryDao.save(tranHistory);
            if (count8 != 1){
                flag = false;
            }



        }

        return flag;
    }


}
