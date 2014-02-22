package net.newcapec.sca.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
*
*类描述：
*@author: 赵鹏飞
*@date： 日期：2012-9-13 时间：上午08:40:17
*@version 1.0
*修改人：
*修改时间：
*修改备注：
*/
public class DateUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String invalidDate(String invalidMinute) throws NumberFormatException, IOException{
		Date date = new Date();
		Config config = new Config();
		int invalidTime ;
		if(invalidMinute == null || "".equals(invalidMinute)){
			invalidTime = 60*Integer.parseInt(config.getByKey("invalidtime"));
		}else{
			invalidTime = Integer.parseInt(invalidMinute)*60;
		}
		long time =  (date.getTime()/1000)+invalidTime;
		date.setTime(time*1000);
		return sdf.format(date);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		DateUtil dateHelper = new DateUtil();
		String time = dateHelper.invalidDate("");
		System.out.println(time);
	}
}
