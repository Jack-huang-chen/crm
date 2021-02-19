package com.study.com.workbench.service;

import com.study.com.vo.Pagination;
import com.study.com.workbench.domain.Activity;
import com.study.com.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    public boolean save(Activity activity);

    Pagination<Activity> pageList(Map<String, Object> map);

    boolean delete(String[] para);

    Map<String, Object> edit(String id);

    boolean update(Activity activity);

    Activity detail(String id);

    List<ActivityRemark> getActivityRemark(String activityId);

    boolean deleteRemarkById(String id);

    boolean saveRemark(ActivityRemark ar);

    boolean updateRemark(ActivityRemark activityRemark);

    List<Activity> searchActivityById(Map<String, String> map);

    List<Activity> openActivity(String id, String name);
}
