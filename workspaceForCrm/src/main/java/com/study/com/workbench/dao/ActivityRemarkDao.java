package com.study.com.workbench.dao;

import com.study.com.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {




    int selectById(String[] para);

    int deleteById(String[] para);

    List<ActivityRemark> getActivityRemark(String activityId);

    int deleteRemarkById(String id);

    int saveRemark(ActivityRemark ar);

    int updateRemark(ActivityRemark activityRemark);
}
