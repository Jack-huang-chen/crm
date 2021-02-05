package com.study.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String dateToString(Date date , String patt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        String date1 = simpleDateFormat.format(date);
        return date1;

    }
    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        Date date = simpleDateFormat.parse(str);
        return date;
    }
}
