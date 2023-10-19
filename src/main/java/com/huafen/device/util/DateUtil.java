package com.huafen.device.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public final static String  TIME_UNIX="~";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public final static int[]  MONTHS =new int[] {1,2,3,4,5,6,7,8,9,10,11,12};
	
	public static String getCallDate() {
		return sdf.format(new Date());
	};
	
	
	public static Long getCurrentTime(int year, int month, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, dayOfMonth, 0,0,0);
		return calendar.getTimeInMillis();
	}
	
	public static  String getNextMonth() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) +1+1;
		StringBuilder  mothBuilder = new StringBuilder();
		mothBuilder.append(year);
		mothBuilder.append(month);
		return mothBuilder.toString();
	}
}
