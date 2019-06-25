package com.vicente.vicenteboot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2017/1/5 8:59
 *
 * @author ChenZhiqiang
 */
public class DateUtil {

    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间戳转时间
     * @param time
     * @return
     */
    public static String timestampToDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
        String dateTime = simpleDateFormat.format(new Date(time));
        return dateTime;
    }


}