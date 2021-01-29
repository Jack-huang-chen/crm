package com.study.com.workbench.dao;

import com.study.com.workbench.domain.Activity;
import com.study.com.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityDao {

    int save(Activity activity);

    int getTotal();

    List<Activity> getActivity(Map<String, Object> map);

    int delete(String[] para);

    Activity edit(String id);

    int update(Activity activity);

    Activity detail(String id);


    List<Activity> searchActivityById(Map<String, String> map);

    List<Activity> openActivity(Map<String, String> map);
}
