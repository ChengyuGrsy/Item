package com.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

public class DateUtil {
    public static String dateFormat(Date date){
        String result=DateFormat.getDateInstance().format(date);
        return result;
    }

    /**
     * YYYY MM dd hh mm ss
     * 年 月 日 时 分 秒
     * @param date
     * @param format
     * @return
     */
    public static String dateFormat(Date date,String format){
        DateFormat dateFormat=new SimpleDateFormat(format);
        String result=dateFormat.format(date);
        return result;
    }

    public static String dateMinusBySeconds(Date start,Date end){
        BigDecimal unit=new BigDecimal(1000);
        long time=end.getTime()-start.getTime();
        BigDecimal result=new BigDecimal(time);
        result=result.divide(unit);
        return result.toString();
    }
    public synchronized static void main(String[] args) throws InterruptedException {
        System.out.println(dateFormat(new Date(),"YYYY MM dd hh mm ss"));

        Date start=new Date();
        sleep(10000);
        Date end=new Date();
        System.out.println(dateMinusBySeconds(start,end));
    }
}
