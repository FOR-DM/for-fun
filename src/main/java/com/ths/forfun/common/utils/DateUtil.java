package com.ths.forfun.common.utils;

import org.apache.commons.lang.time.DateFormatUtils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtil extends org.apache.commons.lang.time.DateUtils {

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到上年年份字符串 格式（yyyy）
     */
    public static String getLastYear() {
        Date years = addYears(new Date(), -1);
        return formatDate(years, "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getLastMonth() {
        Date months = addMonths(new Date(), -1);
        return formatDate(months, "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     * <p>
     * param date
     * return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     * <p>
     * param date
     * return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     * <p>
     * param date
     * return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * <p>
     * param timeMillis
     * return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     * <p>
     * param before
     * param after
     * return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 得到某年某周的第一天
     * <p>
     * param year
     * param week
     * return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     * <p>
     * param year
     * param week
     * return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     * <p>
     * param date
     * return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        // Sunday
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     * <p>
     * param date
     * return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        // Saturday
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的前一周最后一天
     * <p>
     * param date
     * return
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }

    /**
     * 返回指定日期的月的第一天
     * <p>
     * param date
     * return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        return calendar.getTime();
    }

    /**
     * Description:获取指定日期年的第一天
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1);
        return calendar.getTime();
    }

    /**
     * Description:获取指定日期年的最后一天
     */
    public static Date getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 31);
        return calendar.getTime();
    }

    /**
     * Description:获取指定日期年的第一天
     */
    public static Date getFirstDayOfLastYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR) - 1, Calendar.JANUARY, 1);
        return calendar.getTime();
    }

    /**
     * Description:获取指定日期年的最后一天
     */
    public static Date getLastDayOfLastYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR) - 1, Calendar.DECEMBER, 31);
        return calendar.getTime();
    }

    /**
     * 返回指定年月的月的第一天
     * <p>
     * param year
     * param month
     * return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的最后一天
     * <p>
     * param date
     * return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定年月的月的最后一天
     * <p>
     * param year
     * param month
     * return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * Description:返回指定日期的上个月的第一天
     */
    public static Date getFirstDayOfLastMonth(Date date) {
        Date addMonths = addMonths(date, -1);
        return setDays(addMonths, 1);
    }

    /**
     * 返回指定日期的上个月的最后一天
     * <p>
     * param date
     * return
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的季的第一天
     * <p>
     * param date
     * return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的第一天
     * <p>
     * param year
     * param quarter
     * return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month;
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回当前日期的季的最后一天
     * <p>
     * param date
     * return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的最后一天
     * <p>
     * param year
     * param quarter
     * return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month;
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回当前日期的季的第一天
     * <p>
     * param date
     * return
     */
    public static Date getFirstDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfLastQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定日期的上一季的最后一天
     * <p>
     * param date
     * return
     */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的上一季的最后一天
     * <p>
     * param year
     * param quarter
     * return
     */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = 0;
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定年季的上一季的第一天
     * <p>
     * param year
     * param quarter
     * return
     */
    public static Date getFirstDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = 0;
        if (quarter == 1) {
            month = 12 - 3;
        } else if (quarter == 2) {
            month = 3 - 3;
        } else if (quarter == 3) {
            month = 6 - 3;
        } else if (quarter == 4) {
            month = 9 - 3;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季度
     * <p>
     * param date
     * return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    /**
     * Description:获取当前上一季度
     */
    public static int getLastQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3;
    }


    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    /**
     * 获取指定日期的星期
     */
    public static String getDateWeek(Date date) {
        return formatDate(date, "E");
    }


    /**
     * 获取某年某周的星期一
     * <p>
     * param y
     * param w
     * return
     */
    public static Date getBeginDate(Integer y, Integer w) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(y, 0, 1, 0, 0, 0);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int days = w * 7 - (8 - week);
        calendar.add(Calendar.DATE, days - 1);
        Date beginDate = calendar.getTime();
        return beginDate;
    }

    /**
     * 获取某年某周的星期天
     * <p>
     * param y
     * param w
     * return
     */
    public static Date getEndDate(Integer y, Integer w) {
        Date beginDate = getBeginDate(y, w);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.add(Calendar.DATE, 6);
        Date endDate = calendar.getTime();
        return endDate;
    }

    /**
     * Description:获取进行比较的日期整型数字
     */
    public static int getDateInt(String date) {
        return Integer.parseInt(DateFormatUtils.format(DateUtil.parseDate(date), "yyyyMMdd"));
    }

    /**
     * Description:获取进行比较的日期整型数字
     */
    public static int getDateInt(Date date) {
        return Integer.parseInt(DateFormatUtils.format(date, "yyyyMMdd"));
    }

    /**
     * 日期转换为 webservice UTC日期
     *
     * @param date 要转换的 util 日期
     */
    public static XMLGregorianCalendar dateToWsXML(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        XMLGregorianCalendar xmlGregorianCalendar = null;
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlGregorianCalendar;
    }

    /**
     * webservice UTC 日期转换为 util 日期
     *
     * @param gc webservice 封装的 XMLGregorianCalendar 日期类型
     */
    public static Date wsXMLToDate(XMLGregorianCalendar gc) {
        GregorianCalendar gregorianCalendar = gc.toGregorianCalendar();
        return gregorianCalendar.getTime();
    }

    /**
     * 将格式为 Thu Jun 15 16:29:24 CST 2017 的字符串格式化为业务中需要的格式日期字符串
     *
     * @param localeEnglishDate 待转换的格式为 Thu Jun 15 16:29:24 CST 2017 的日期字符串
     * @param pattern           业务中需要的日期格式
     */
    public static String localeEnglishDateToFormatDate(String localeEnglishDate, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(localeEnglishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return DateFormatUtils.format(parse, pattern);
    }

    /**
     * 传入某个时间段，判断当前是否属于当天的这个时间段
     * @param sourceDate 待判断的时间
     * @param startTimeStr 开始时间段 如："12:00"
     * @param endTimeStr 结束时间段 如："16:00"
     * @return true/false
     */
    public static Boolean isBelongSomeTime(Date sourceDate, String startTimeStr, String endTimeStr) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date beginTime = null;
        Date endTime = null;
        try {
            beginTime = df.parse(startTimeStr);
            endTime = df.parse(endTimeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return belongCalendar(sourceDate, beginTime, endTime);
    }

    private static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        int beginHour = beginTime.getHours();
        int beginMinute = beginTime.getMinutes();

        int endHour = endTime.getHours();
        int endMinute = endTime.getMinutes();

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(new Date());
        begin.set(Calendar.HOUR_OF_DAY,beginHour);
        begin.set(Calendar.MINUTE,beginMinute);

        Calendar end = Calendar.getInstance();
        end.setTime(new Date());
        end.set(Calendar.HOUR_OF_DAY,endHour);
        end.set(Calendar.MINUTE,endMinute);

        return date.after(begin) && date.before(end);
    }

    /**
     * 获取指定日期时间戳
     * @param past
     * @return
     */
    public static String getDateStr(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date day = calendar.getTime();
        String result = String.valueOf(day.getTime());
        return result;
    }
}
