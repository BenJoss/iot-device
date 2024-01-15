package com.huafen.device.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtil {
	
	public static List<String> workOrderList = new ArrayList<String>();
	
	static {
        // 添加星期几到集合中  
		workOrderList.add(getChineseWeekday(Calendar.MONDAY));  
		workOrderList.add(getChineseWeekday(Calendar.TUESDAY));  
		workOrderList.add(getChineseWeekday(Calendar.WEDNESDAY));  
		workOrderList.add(getChineseWeekday(Calendar.THURSDAY));  
		workOrderList.add(getChineseWeekday(Calendar.FRIDAY));  
		workOrderList.add(getChineseWeekday(Calendar.SATURDAY));  
		workOrderList.add(getChineseWeekday(Calendar.SUNDAY));  
	}

	public final static String  TIME_UNIX="~";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static SimpleDateFormat sdfHour = new SimpleDateFormat("yyyy-MM-dd HH");
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
	
	private static DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	
	public final static int[]  MONTHS =new int[] {1,2,3,4,5,6,7,8,9,10,11,12};
	
	public final static int  MONTHS_MAX = 12;
	
	public static DateTimeFormatter getDateTimeFormatter() {
		return formatter;
	}
	
	public static DateTimeFormatter getDateTimeFormatterDay() {
		return formatterDay;
	}
	
	public static String getCallDate() {
		return sdf.format(new Date());
	};
	
	public static String getDateBySecond(Date date) {
		return sdf.format(date);
	};
	
	public static Date getDateByStr(String timeStr) throws ParseException {
		return sdf.parse(timeStr);
	};
	
	public static String getPublicHour(Date date) {
		return sdfHour.format(date);
	};
	
	public static Long getCurrentTime(int year, int month, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, dayOfMonth);
		 // 将Calendar的时间设置为当天的00:00:00  
        calendar.set(Calendar.HOUR_OF_DAY, 0); 
        calendar.set(Calendar.MINUTE, 0);  
        calendar.set(Calendar.SECOND, 0);  
        calendar.set(Calendar.MILLISECOND, 0);  
		return calendar.getTimeInMillis();
	}
	
	public static String longToDate(long time) {
		DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time),ZoneId.systemDefault()));
	}
	
	public static  boolean isTimeInRange(String currentTime, String startTime, String endTime) {  
	    DateTimeFormatter formatter = DateUtil.getDateTimeFormatter();  
        LocalDateTime currentTimeObj = LocalDateTime.parse(currentTime, formatter);  
        LocalDateTime startTimeObj = LocalDateTime.parse(startTime, formatter);  
        LocalDateTime endTimeObj = LocalDateTime.parse(endTime, formatter);  
        // 将时间截断到分钟级别  
        currentTimeObj = currentTimeObj.truncatedTo(ChronoUnit.MINUTES);  
        startTimeObj = startTimeObj.truncatedTo(ChronoUnit.MINUTES);  
        endTimeObj = endTimeObj.truncatedTo(ChronoUnit.MINUTES);  
  
        return currentTimeObj.isAfter(startTimeObj) && currentTimeObj.isBefore(endTimeObj) || currentTimeObj.isEqual(startTimeObj) || currentTimeObj.isEqual(endTimeObj);  
    }
	
	public static  boolean isTimeInCurrentHour(String  pstartTime) {
		 // 获取当前时间  
         LocalDateTime now = LocalDateTime.now();  
		 DateTimeFormatter formatter = DateUtil.getDateTimeFormatter();  
		 LocalDateTime publishTimeObj = LocalDateTime.parse(pstartTime, formatter);  
		 if (now.truncatedTo(ChronoUnit.HOURS).equals(publishTimeObj.truncatedTo(ChronoUnit.HOURS))) {
			 return true;
		}else {
			return false;
		}
	}
	
	/**  
     * 获取中文星期几  
     *  
     * @param weekdayNum 星期几的数字表示，1表示星期一，7表示星期日  
     * @return 中文星期几  
     */  
    private static String getChineseWeekday(int weekdayNum) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_WEEK, weekdayNum);  
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.CHINA);  
        return dateFormat.format(calendar.getTime());  
    }  
}
