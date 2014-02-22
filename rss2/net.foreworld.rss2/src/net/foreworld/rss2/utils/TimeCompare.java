package net.foreworld.rss2.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @version 创建时间：Oct 10, 2008 2:44:19 PM
 * 时间比较
 */
public class TimeCompare {
	//判断时间date1是否在时间date2之前
	//时间格式 2005-4-21 16:16:34
	public static boolean isDateBefore(String date1,String date2){
		DateFormat df = DateFormat.getDateTimeInstance();
		try {
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			return false;
		}
	}

	//判断当前时间是否在时间date2之前
	//时间格式 2005-4-21 16:16:34
	public static boolean isDateBefore(String date2){
		Date date1 = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();
		try {
			return date1.before(df.parse(date2));
		} catch (ParseException e) {
			return false;
		}
	}
}
