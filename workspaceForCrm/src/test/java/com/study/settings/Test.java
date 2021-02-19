package com.study.settings;

import com.study.com.utils.DateTimeUtil;
import com.study.com.utils.MD5Util;

public class Test {
    public static void main(String[] args) {
        String s = "2020-12 -10 10:10:10";
        String time = DateTimeUtil.getSysTime();
        System.out.println(time);
        int a = time.compareTo(s);
        System.out.println(a);
        String pa ="123";
        pa = MD5Util.getMD5(pa);
        System.out.println(pa);
    }
}
