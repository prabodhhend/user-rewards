package org.acko.smartlife.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author prabodh.hend
 */
@Slf4j
public class DateTimeUtil {

    private static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getYYYYMMddHHmmss(Date date) {
        String dateStr = null;
        try {
            dateStr = yyyyMMddHHmmss.format(date);
        } catch (Exception e) {
            log.error("Unable to format date", e);
            throw new RuntimeException("Cannot parse date");
        }
        return dateStr;
    }

    public static Date getTodayMorning() {
        Calendar calStart = new GregorianCalendar();
        calStart.setTime(new Date());
        calStart.set(Calendar.HOUR_OF_DAY, 0);
        calStart.set(Calendar.MINUTE, 0);
        calStart.set(Calendar.SECOND, 0);
        calStart.set(Calendar.MILLISECOND, 0);
        Date date = calStart.getTime();
        log.info("date morning: {}", date);
        log.info("date morning: {}", date.toString());
        return date;
    }

    public static Date getTodayNight() {
        Calendar calStart = new GregorianCalendar();
        calStart.setTime(new Date());
        calStart.set(Calendar.HOUR_OF_DAY, 23);
        calStart.set(Calendar.MINUTE, 59);
        calStart.set(Calendar.SECOND, 59);
        calStart.set(Calendar.MILLISECOND, 59);
        Date date = calStart.getTime();
        log.info("date night: {}", date);
        log.info("date night: {}", date.toString());

        return date;
    }
}
