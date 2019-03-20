package com.demo.adminsystem.core.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 16:49
 * @version: V1.0
 * @detail: 日期常用转换
 **/
public class DateUtil {
    private static Log log = LogFactory.getLog(DateUtil.class);
    private static String defaultDatePattern = null;
    private static String timePattern = "HH:mm";
    public static final String TS_FORMAT = DateUtil.getDatePattern() + " HH:mm:ss.S";
    private static Calendar cale = Calendar.getInstance();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //~ Methods ================================================================

    public DateUtil() {
    }

    /**
     * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
     */
    public static String getDateTime() {
        try {
            return sdf2.format(cale.getTime());
        } catch (Exception e) {
            log.debug("DateUtil.getDateTime():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回
     */
    public static String getDate() {
        try {
            return sdf.format(cale.getTime());
        } catch (Exception e) {
            log.debug("DateUtil.getDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前时间，以格式为：HH:mm:ss的日期字符串形式返回
     */
    public static String getTime() {
        String temp = "";
        try {
            temp += sdf1.format(cale.getTime());
            return temp;
        } catch (Exception e) {
            log.debug("DateUtil.getTime():" + e.getMessage());
            return "";
        }
    }


    /**
     * 统计时开始日期的默认值,
     * 今年的开始时间
     */
    public static String getStartDate() {
        try {
            return getYear() + "-01-01";
        } catch (Exception e) {
            log.debug("DateUtil.getStartDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 统计时结束日期的默认值
     */
    public static String getEndDate() {
        try {
            return getDate();
        } catch (Exception e) {
            log.debug("DateUtil.getEndDate():" + e.getMessage());
            return "";
        }
    }


    /**
     * 获得服务器当前日期的年份
     */
    public static String getYear() {
        try {
            //返回的int型，需要字符串转换
            return String.valueOf(cale.get(Calendar.YEAR));
        } catch (Exception e) {
            log.debug("DateUtil.getYear():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期的月份
     */
    public static String getMonth() {
        try {
            //一个数字格式，非常好
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.applyPattern("00");
            return df.format((cale.get(Calendar.MONTH) + 1));
            //return String.valueOf(cale.get(Calendar.MONTH) + 1);
        } catch (Exception e) {
            log.debug("DateUtil.getMonth():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器在当前月中天数
     */
    public static String getDay() {
        try {
            return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            log.debug("DateUtil.getDay():" + e.getMessage());
            return "";
        }
    }

    /**
     * 比较两个日期相差的天数,
     * 第一个日期要比第二个日期要晚
     */
    public static int getMargin(String date1, String date2) {
        int margin;
        try {
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = sdf.parse(date1, pos);
            Date dt2 = sdf.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (int) (l / (24 * 60 * 60 * 1000));
            return margin;
        } catch (Exception e) {
            log.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }


    /**
     * 比较两个日期相差的天数，格式不一样
     * 第一个日期要比第二个日期要晚
     */
    public static double getDoubleMargin(String date1, String date2) {
        double margin;
        try {
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = sdf2.parse(date1, pos);
            Date dt2 = sdf2.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (l / (24 * 60 * 60 * 1000.00));
            return margin;
        } catch (Exception e) {
            log.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }


    /**
     * 比较两个日期相差的月数
     */
    public static int getMonthMargin(String date1, String date2) {
        int margin;
        try {
            margin = (Integer.parseInt(date2.substring(0, 4)) - Integer.parseInt(date1.substring(0, 4))) * 12;
            margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0", "-")) - Integer.parseInt(date1.substring(4, 7).replaceAll("-0", "-")));
            return margin;
        } catch (Exception e) {
            log.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 返回日期加X天后的日期
     */
    public static String addDay(String date, int i) {
        try {
            GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.DATE, i);
            return sdf.format(gCal.getTime());
        } catch (Exception e) {
            log.debug("DateUtil.addDay():" + e.toString());
            return getDate();
        }
    }

    /**
     * 返回日期加X月后的日期
     */
    public static String addMonth(String date, int i) {
        try {
            GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.MONTH, i);
            return sdf.format(gCal.getTime());
        } catch (Exception e) {
            log.debug("DateUtil.addMonth():" + e.toString());
            return getDate();
        }
    }

    /**
     * 返回日期加X年后的日期
     */
    public static String addYear(String date, int i) {
        try {
            GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.YEAR, i);
            return sdf.format(gCal.getTime());
        } catch (Exception e) {
            log.debug("DateUtil.addYear():" + e.toString());
            return "";
        }
    }

    public static int getWeekInDayWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 返回某年某月中的最大天
     */
    public static int getMaxDay(String year, String month) {
        int day = 0;
        try {
            int iyear = Integer.parseInt(year);
            int imonth = Integer.parseInt(month);
            if (imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7 || imonth == 8 || imonth == 10 || imonth == 12) {
                day = 31;
            } else if (imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11) {
                day = 30;
            } else if ((0 == (iyear % 4)) && (0 != (iyear % 100)) || (0 == (iyear % 400))) {
                day = 29;
            } else {
                day = 28;
            }
            return day;
        } catch (Exception e) {
            log.debug("DateUtil.getMonthDay():" + e.toString());
            return 1;
        }
    }


    /**
     * 格式化日期
     */
    @SuppressWarnings("static-access")
    public String rollDate(String orgDate, int Type, int Span) {
        try {
            String temp = "";
            int iyear, imonth, iday;
            int iPos = 0;
            char seperater = '-';
            if (orgDate == null || orgDate.length() < 6) {
                return "";
            }

            iPos = orgDate.indexOf(seperater);
            if (iPos > 0) {
                iyear = Integer.parseInt(orgDate.substring(0, iPos));
                temp = orgDate.substring(iPos + 1);
            } else {
                iyear = Integer.parseInt(orgDate.substring(0, 4));
                temp = orgDate.substring(4);
            }

            iPos = temp.indexOf(seperater);
            if (iPos > 0) {
                imonth = Integer.parseInt(temp.substring(0, iPos));
                temp = temp.substring(iPos + 1);
            } else {
                imonth = Integer.parseInt(temp.substring(0, 2));
                temp = temp.substring(2);
            }

            imonth--;
            if (imonth < 0 || imonth > 11) {
                imonth = 0;
            }

            iday = Integer.parseInt(temp);
            if (iday < 1 || iday > 31)
                iday = 1;

            Calendar orgcale = Calendar.getInstance();
            orgcale.set(iyear, imonth, iday);
            temp = this.rollDate(orgcale, Type, Span);
            return temp;
        } catch (Exception e) {
            return "";
        }
    }

    public static String rollDate(Calendar cal, int Type, int Span) {
        try {
            String temp = "";
            Calendar rolcale;
            rolcale = cal;
            rolcale.add(Type, Span);
            temp = sdf.format(rolcale.getTime());
            return temp;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回默认的日期格式
     */
    public static synchronized String getDatePattern() {
        defaultDatePattern = "yyyy-MM-dd";
        return defaultDatePattern;
    }

    /**
     * 将指定日期按默认格式进行格式代化成字符串后输出如：yyyy-MM-dd
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }


    /**
     * 取得给定日期的时间字符串，格式为当前默认时间格式
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * 取得当前时间的Calendar日历对象
     */
    public Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));
        return cal;
    }

    /**
     * 将日期类转换成指定格式的字符串形式
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    public static final String getDateTime(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * 将指定的日期转换成默认格式的字符串形式
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }


    /**
     * 将日期字符串按指定格式转换成日期类型
     *
     * @param aMask   指定的日期格式，如:yyyy-MM-dd
     * @param strDate 待转换的日期字符串
     */

    public static final Date convertStringToDate(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                    + aMask + "'");
        }
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            log.error("ParseException: " + pe);
        }
        return (date);
    }

    /**
     * 将日期字符串按默认格式转换成日期类型
     */
    public static Date convertStringToDate(String strDate) {
        Date aDate = null;
        if (log.isDebugEnabled()) {
            log.debug("converting date with pattern: " + getDatePattern());
        }
        aDate = convertStringToDate(getDatePattern(), strDate);

        return aDate;
    }

    public static Date convertStringToDateTime(String strDate) {
        Date aDate = null;
        if (log.isDebugEnabled()) {
            log.debug("converting date with pattern: " + getDatePattern());
        }
        aDate = convertStringToDate("yyyy-MM-dd HH:mm:ss", strDate);

        return aDate;
    }

    /**
     * 返回一个JAVA简单类型的日期字符串
     */
    public static String getSimpleDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat();
        String NDateTime = formatter.format(new Date());
        return NDateTime;
    }

    /**
     * 将两个字符串格式的日期进行比较
     *
     * @param last 要比较的第一个日期字符串
     * @param now  要比较的第二个日期格式字符串
     * @return true(last 在now 日期之前), false(last 在now 日期之后)
     */
    public static boolean compareTo(String last, String now) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            Date temp1 = formatter.parse(last);
            Date temp2 = formatter.parse(now);
            if (temp1.after(temp2))
                return false;
            else if (temp1.before(temp2))
                return true;
        } catch (ParseException e) {
            log.debug(e.getMessage());
        }
        return false;
    }

    protected Object convertToDate(Class type, Object value) {
        DateFormat df = new SimpleDateFormat(TS_FORMAT);
        if (value instanceof String) {
            try {
                if (StringUtils.isEmpty(value.toString())) {
                    return null;
                }
                return df.parse((String) value);
            } catch (Exception pe) {
            }
        }
        return null;
    }


    /**
     * 为查询日期添加最小时间
     *
     * @param param 目标类型
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addStartTime(Date param) {
        Date date = param;
        try {
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            return date;
        } catch (Exception ex) {
            return date;
        }
    }


    /**
     * 为查询日期添加最大时间
     *
     * @param param 目标类型
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addEndTime(Date param) {
        Date date = param;
        try {
            date.setHours(23);
            date.setMinutes(59);
            date.setSeconds(0);
            return date;
        } catch (Exception ex) {
            return date;
        }
    }


    /**
     * 返回系统现在年份中指定月份的天数
     *
     * @param month 月份
     * @return 指定月的总天数
     */
    @SuppressWarnings("deprecation")
    public static String getMonthLastDay(int month) {
        Date date = new Date();
        int[][] day = {{0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
        int year = date.getYear() + 1900;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return day[1][month] + "";
        } else {
            return day[0][month] + "";
        }
    }

    /**
     * 返回指定年份中指定月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 指定月的总天数
     */
    public static String getMonthLastDay(int year, int month) {
        int[][] day = {{0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return day[1][month] + "";
        } else {
            return day[0][month] + "";
        }
    }

    /**
     * 取得当前时间的日戳
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getTimestamp() {
        Date date = new Date();
        String timestamp = "" + (date.getYear() + 1900) + date.getMonth() + date.getDate() + date.getMinutes() + date.getSeconds() + date.getTime();
        return timestamp;
    }

    /**
     * 取得指定时间的日戳
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getTimestamp(Date date) {
        String timestamp = "" + (date.getYear() + 1900) + date.getMonth() + date.getDate() + date.getMinutes() + date.getSeconds() + date.getTime();
        return timestamp;
    }

    public static List<Map<String, Date>> getDayFromTwoDays(Date start, Date end) {
        if (end.getTime() <= start.getTime()) return null;
        List<Map<String, Date>> dateList = new ArrayList<>();
        String startStr = DateUtil.getDateTime("yyyy-MM-dd", start);
        String endStr = DateUtil.getDateTime("yyyy-MM-dd", end);

        if (startStr.equals(endStr)) {
            Map<String, Date> map = new HashMap<>();
            map.put("start", start);
            map.put("end", end);
            dateList.add(map);
            return dateList;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);


        String now = startStr;
        int i = 1;
        do {
            Map<String, Date> map = new HashMap<>();
            map.put("start", startStr.equals(now) ? start : (convertStringToDateTime(now + " 00:00:00")));
            map.put("end", convertStringToDateTime(now + " 23:59:59"));
            dateList.add(map);
            calendar.set(year, month, day + i);
            now = DateUtil.getDate(calendar.getTime());
            i += 1;
        } while (!now.equals(endStr));

        Map<String, Date> map = new HashMap<>();
        map.put("start", convertStringToDateTime(now + " 00:00:00"));
        map.put("end", end);
        dateList.add(map);
        return dateList;
    }

    public static List<String> getDayListFormTowDays(Date start, Date end) {
        if (end.getTime() <= start.getTime()) return null;
        List<String> dateList = new ArrayList<>();
        String startStr = DateUtil.getDateTime("yyyy-MM-dd", start);
        String endStr = DateUtil.getDateTime("yyyy-MM-dd", end);

        if (startStr.equals(endStr)) {
            dateList.add(startStr);
            return dateList;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);


        String now = startStr;
        int i = 1;
        do {

            calendar.set(year, month, day + i);
            now = DateUtil.getDate(calendar.getTime());
            i += 1;
            dateList.add(now);
        } while (!now.equals(endStr));
        return dateList;
    }

    public void test() {
        System.out.println(DateUtil.getDate());//获取日期格式为2010-08-12
        System.out.println(DateUtil.getDateTime());//获取日期格式为2010-08-12 18:08:21
        System.out.println(DateUtil.getTime());//获取日期格式为18:08:21
        System.out.println(DateUtil.getYear());//获取当前时间年份2010
        System.out.println(DateUtil.getMonth());//获取当年时间月份08
        System.out.println(DateUtil.getStartDate());//获取2010-01-01
        System.out.println(DateUtil.getEndDate());//2010-08-12
        System.out.println(DateUtil.getDay());//获得服务器在当前月中已经过了的天数12
        System.out.println(DateUtil.getMargin("2010-05-02", "2010-04-01"));//比较两个日期相差的天数
        System.out.println(DateUtil.getDoubleMargin("2010-05-07 23:22:11", "2010-04-01 01:33:33"));
    }

    public static void main(String[] args) {
        long t = convertStringToDateTime("2018-02-09 00:00:00").getTime();
        System.out.println(t);

    }
}
