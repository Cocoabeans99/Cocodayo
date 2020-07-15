package com.coco.Dao;


import java.text.ParseException;
import java.util.Calendar;

public class GetDateArray {
    public static long[] get() throws ParseException {
        long[] intarray = new long[120];
        for (long array:intarray){
            array = 0;
        }
        int year_now = Calendar.getInstance().get(Calendar.YEAR);
        int month_now = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int i = 0;
        int year = 2016;
        int month = 9;
        for (i=0; i < 120; i++){
            if((year < year_now) || (year == year_now && month <= month_now)){
                intarray[i] = DateToSecond.mktime(year + "-" + month +"-15 00:00:00");
                month++;
                if(month > 12){
                    month = 1;
                    year++;
                }
            }else{
                break;
            }
        }
        return intarray;
    }
}
