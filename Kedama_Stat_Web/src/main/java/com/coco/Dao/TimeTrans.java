package com.coco.Dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTrans {
    public static String secondToTime(long second) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(second));
    }
}
