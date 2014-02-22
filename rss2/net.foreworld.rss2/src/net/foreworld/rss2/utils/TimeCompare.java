package net.foreworld.rss2.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @version ����ʱ�䣺Oct 10, 2008 2:44:19 PM
 * ʱ��Ƚ�
 */
public class TimeCompare {
	//�ж�ʱ��date1�Ƿ���ʱ��date2֮ǰ
	//ʱ���ʽ 2005-4-21 16:16:34
	public static boolean isDateBefore(String date1,String date2){
		DateFormat df = DateFormat.getDateTimeInstance();
		try {
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			return false;
		}
	}

	//�жϵ�ǰʱ���Ƿ���ʱ��date2֮ǰ
	//ʱ���ʽ 2005-4-21 16:16:34
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
