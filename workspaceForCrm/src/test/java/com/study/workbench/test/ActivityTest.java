package com.study.workbench.test;

import com.study.com.utils.ServiceFactory;
import com.study.com.utils.UUIDUtil;
import com.study.com.workbench.domain.Activity;
import com.study.com.workbench.service.ActivityService;
import com.study.com.workbench.service.Imp.ActivityServiceImpl;
import org.junit.Assert;
import org.junit.Test;

public class ActivityTest {
    ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

    @Test
    //测试市场活动保存方法
    public void testSave(){
        Activity activity = new Activity();
        activity.setId(UUIDUtil.getUUID());
        activity.setName("好好学习");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = activityService.save(activity);
        Assert.assertEquals(flag,true);
    }
    @Test
    //测试市场活动删除方法
    public void  testDelete(){
        String a[] = {"16e6e5aa82cc4eff9337d95d8b8f448b"};
        boolean flag = activityService.delete(a);
        Assert.assertEquals(flag,true);
    }
    @Test
    //测试市场活动查询方法
    public void testEdit(){

        activityService.edit("03657e0794a0478b910dc3340e06355b");

    }
}
