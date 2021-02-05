package com.study.com.workbench.service.Imp;

import com.study.com.settings.dao.UserDao;
import com.study.com.settings.domain.User;
import com.study.com.utils.SqlSessionUtil;
import com.study.com.vo.Pagination;
import com.study.com.workbench.dao.ActivityDao;
import com.study.com.workbench.dao.ActivityRemarkDao;
import com.study.com.workbench.domain.Activity;
import com.study.com.workbench.domain.ActivityRemark;
import com.study.com.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
   private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);//事务层要执行增删改查
   private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
   private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
   @Override
   public boolean save(Activity activity) {
       boolean flag = true;
       int count = activityDao.save(activity);
       if (count != 1){
           flag = false;
       }
       return flag;
   }

   @Override
   public Pagination<Activity> pageList(Map<String, Object> map) {
       //取得total

       int total = activityDao.getTotal();
       List<Activity> activities = activityDao.getActivity(map);
       Pagination<Activity> pagination = new Pagination<>();
       pagination.setDateList(activities);
       pagination.setTotal(total);
       return pagination;
   }

   @Override
   public boolean delete(String[] para) {
       boolean flag = true;
       //查询出要删除备注总数量
       int count1 = activityRemarkDao.selectById(para);
       //删除
       int count2 = activityRemarkDao.deleteById(para);
       if (count1 != count2){
           flag = false;

       }
       int count3 = activityDao.delete(para);
       if (count3 != para.length){
           flag = false;
       }



       return flag;
   }

   @Override
   public Map<String, Object> edit(String id) {
       //查询会员
       List<User> uList = userDao.getUserList();
       //查询市场活动
       Activity a = activityDao.edit(id);
       Map<String,Object> map = new HashMap<>();
       map.put("uList",uList);
       map.put("a",a);
       return map;
   }

   @Override
   public boolean update(Activity activity) {
       boolean flag = true;
       int count = activityDao.update(activity);
       if (count != 1){
           flag = false;
       }
       return flag;

   }

    @Override
    public Activity detail(String id) {
       Activity activity = activityDao.detail(id);
        return activity;
    }

    @Override
    public List<ActivityRemark> getActivityRemark(String activityId) {
       List<ActivityRemark> activityRemark = activityRemarkDao.getActivityRemark(activityId);
        return activityRemark;
    }

    @Override
    public boolean deleteRemarkById(String id) {
       boolean flag = true;
       int count = activityRemarkDao.deleteRemarkById(id);
       if (count != 1){
           flag = false;
       }
        return flag;
    }

    @Override
    public boolean saveRemark(ActivityRemark ar) {
        Boolean flag = true;
        int count = activityRemarkDao.saveRemark(ar);
        if (count != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean updateRemark(ActivityRemark activityRemark) {
       boolean flag = true;
       int count = activityRemarkDao.updateRemark(activityRemark);
       if (count != 1){
           flag = false;
       }
        return flag;
    }

    @Override
    public List<Activity> searchActivityById(Map<String, String> map) {
       List<Activity> activityList = activityDao.searchActivityById(map);
        return activityList;
    }

    @Override
    public List<Activity> openActivity(String id, String name) {
       Map<String,String> map = new HashMap<>();
       map.put("id",id);
       map.put("name",name);
       List<Activity> activityList =  activityDao.openActivity(map);
       return activityList;
    }


}
