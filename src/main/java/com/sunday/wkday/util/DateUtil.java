package com.sunday.wkday.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class DateUtil {
    private static final ThreadLocal<SimpleDateFormat> DATE_TIME = initSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final ThreadLocal<SimpleDateFormat> DATE = initSimpleDateFormat("yyyy-MM-dd");
    private static final ThreadLocal<SimpleDateFormat> DATE_SIMPLE = initSimpleDateFormat("MM-dd");
    private static final ThreadLocal<SimpleDateFormat> TIME = initSimpleDateFormat("HH:mm");

    private static ThreadLocal<SimpleDateFormat> initSimpleDateFormat(String pattern) {
        return ThreadLocal.withInitial(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            return simpleDateFormat;
        });
    }

    /**
     * 格式化日期
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime(Date date) {
        return DATE_TIME.get().format(date);
    }

    /**
     * 格式化日期
     * @param date
     * @return yyyy-MM-dd
     */
    public static String getDate(Date date) {
        return DATE.get().format(date);
    }

    /**
     * 格式化日期
     * @param date
     * @return MM-dd
     */
    public static String getSimpleDate(Date date) {
        return DATE_SIMPLE.get().format(date);
    }

    /**
     * 格式化日期
     * @param date
     * @return
     */
    public static String getSimpleTime(Date date) {
        return TIME.get().format(date);
    }

    public static Date parseDate(String dateStr) {
        try {
            return DATE.get().parse(dateStr);
        } catch (Exception e) {
            log.error("parseDate:" + dateStr, e);
            return null;
        }
    }

    public static Date addMonths(Date date, int amount) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);

        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date date = parseDate("2020-2-1");
        Date date1 = DateUtil.addMonths(date, 1);
        System.out.println(getDateTime(date1));
        System.out.println(Integer.parseInt("09"));
        int hour = Integer.parseInt("2020-02-01".substring(8));
        System.out.println(hour);
        System.out.println(getSimpleTime(new Date()));
    }
}
