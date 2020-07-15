package coco;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToSecond {
    public static long mktime(String date) throws ParseException {
        // date是yyyy-MM-dd HH:mm:ss格式的String类型的时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = simpleDateFormat.parse(date);
        long timeStamp = time.getTime();
        return timeStamp;
    }
}
