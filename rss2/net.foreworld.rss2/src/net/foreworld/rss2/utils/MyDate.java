package net.foreworld.rss2.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 
 * @author 黄鑫
 * @Jan 15, 2008 9:58:48 PM
 */
/**
 * <p>Title: 日期转换函数</p>
 * <p>Description: 有以下功能方法可以使用</p>
 * function 1:dateintstr(StrDate) :把字符串转为日期并初始化各部份
 * StrDate:日期格式的字符串.
 * function 2:dateint() : 初始化当前日期的各部份
 * function 3:getnoncetime() : 取当前系统时间并精确到毫秒
 * function 4:getymd() :取当前系统时间的年月日
 * function 5:getTime() :取出当前系统的时分秒
 * function 6:getFormatDate(Format) :格式化当前系统时间
 * Format：支持以下格式:
 * yyyy-MM-dd格式:年月日
 *
 * yyyy/MM/dd格式:年月日
 *
 * yyyyMMdd格式:年月日
 *
 * yyyy.mm.dd格式:年月日
 *
 * yyyy-MM格式:年月
 *
 * yyyy/mm格式:年月
 *
 * yyyymm格式:年月
 *
 * yyyy.mm格式:年月
 *
 * mm-dd格式:月日
 *
 * mm/dd格式:月日
 *
 * mmdd格式:月日
 *
 * mm.dd格式:月日
 *
 * yyyymmddhhmiss格式:当前年月日 时分秒
 *
 * yyyy-mm-dd hh:mi:ss格式:当前年月日 时分秒
 *
 * yyyy/mm/dd hh:mi:ss格式:当前年月日 时分秒
 *
 * yyyy.mm.dd hh:mi:ss格式:当前年月日 时分秒
 *
 * hh:mi:ss格式:当前时分秒
 *
 * yyyy格式:当前年份
 *
 * mm格式:当前月份
 *
 * dd格式:当月的当前的哪一天.
 * function 7:getFormatDateS(Format,StrDate) :格式化当前系统时间
 * Format ：支持以下格式:以上；
 * StrDate:日期字符串。
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * 作者：WRL
 */

public class MyDate {
	int yyyy = 0;
    int mm = 0;
    int dd = 0;
    int hh = 0;
    int mi = 0;
    int ss = 0;
    int ms = 0;
    String Smm = "", Sdd = "", Shh = "", Smi = "", Sss = "";
    String sNowTime = "";
    public MyDate() {
    }
   //*** Date Tdate =new Date("2005/06/7");
   //***--------------------初始化字符串转为日期----------------------
    private void dateintStr(String StrDate) {
    StrDate = StrDate.replace('-','/');
    StrDate = StrDate.replace('.','/');
    Date Tdate = new Date(StrDate);
    Calendar NowTime = Calendar.getInstance();
    NowTime.setTime(Tdate);
    yyyy = NowTime.get(NowTime.YEAR);
    mm = NowTime.get(NowTime.MONTH) + 1;
    dd = NowTime.get(NowTime.DAY_OF_MONTH);
    hh = NowTime.get(NowTime.HOUR_OF_DAY);
    mi = NowTime.get(NowTime.MINUTE);
    ss = NowTime.get(NowTime.SECOND);
    ms = NowTime.get(NowTime.MILLISECOND);
    Smm = (mm < 10) ? "0" + mm : String.valueOf(mm);
    Sdd = (dd < 10) ? "0" + dd : String.valueOf(dd);
    Shh = (hh < 10) ? "0" + hh : String.valueOf(hh);
    Smi = (mi < 10) ? "0" + mi : String.valueOf(mi);
    Sss = (ss < 10) ? "0" + ss : String.valueOf(ss);
    }
   //***--------------------初始化当前时间-----------------------
    private void dateint() {
    Calendar NowTime = Calendar.getInstance();
    yyyy = NowTime.get(NowTime.YEAR);
    mm = NowTime.get(NowTime.MONTH) + 1;
    dd = NowTime.get(NowTime.DAY_OF_MONTH);
    hh = NowTime.get(NowTime.HOUR_OF_DAY);
    mi = NowTime.get(NowTime.MINUTE);
    ss = NowTime.get(NowTime.SECOND);
    ms = NowTime.get(NowTime.MILLISECOND);
    Smm = (mm < 10) ? "0" + mm : String.valueOf(mm);
    Sdd = (dd < 10) ? "0" + dd : String.valueOf(dd);
    Shh = (hh < 10) ? "0" + hh : String.valueOf(hh);
    Smi = (mi < 10) ? "0" + mi : String.valueOf(mi);
    Sss = (ss < 10) ? "0" + ss : String.valueOf(ss);
    }
    //***-----------------取出当前时间---------------------------
    public String getnoncetime() {
    dateint(); //初始化时间控件.
    sNowTime = yyyy + "-" + Smm + "-" + Sdd + " " + Shh + ":" + Smi + ":" +
    Sss +
    "." + ms;
    return sNowTime;
    }
    //***--------------------取出当前时间的年月日------------------
    public String getymd() {
    dateint(); //初始化时间控件.
    sNowTime = yyyy + "-" + Smm + "-" + Sdd;
    return sNowTime;
    }
   //***--------------------取出当前几点几分几秒-------------------
    public String getTime() {
    dateint(); //初始化时间控件.
    sNowTime = Shh + ":" + Smi + ":" + Sss;
    return sNowTime;
    }
    //***按格式返回当前时间的任意部份--------------------------------
    public String getFormatDate(String Format) {
    dateint(); //初始化时间控件.
    Format = Format.trim().toLowerCase();
   //******年月日格式----------------------------------------------
    if (Format.equals("yyyy-mm-dd")) {
    //yyyy-MM-dd格式.
    return yyyy + "-" + Smm + "-" + Sdd;
    }
    if (Format.equals("yyyy/mm/dd")) {
    //yyyy/MM/dd格式.
    return yyyy + "/" + Smm + "/" + Sdd;
    }
    if (Format.equals("yyyymmdd")) {
    //yyyyMMdd格式.
    return yyyy + Smm + Sdd;
    }
    if (Format.equals("yyyy.mm.dd")) {
    //yyyy.mm.dd格式.
    return yyyy + "." + Smm + "." + Sdd;
    }
   //******年月格式----------------------------------------------
    if (Format.equals("yyyy-mm")) {
    //yyyy-MM格式.
    return yyyy + "-" + Smm;
    }
    if (Format.equals("yyyy/mm")) {
    //yyyy/mm格式.
    return yyyy + "/" + Smm;
    }
    if (Format.equals("yyyymm")) {
    //yyyymm格式.
    return yyyy + Smm;
    }
    if (Format.equals("yyyy.mm")) {
    //yyyy.mm格式.
    return yyyy + "." + Smm;
    }
   //******月日格式----------------------------------------------
    if (Format.equals("mm-dd")) {
    //mm-dd格式.
    return Smm + "-" + Sdd;
    }
    if (Format.equals("mm/dd")) {
    //mm/dd格式.
    return Smm + "/" + Sdd;
    }
    if (Format.equals("mmdd")) {
    //mmdd格式.
    return Smm + Sdd;
    }
    if (Format.equals("mm.dd")) {
    //mm.dd格式.
    return Smm + "." + Sdd;
    }
   //******年月日时分秒格式-------------------------------------------
    if (Format.equals("yyyymmddhhmiss")) {
    //yyyymmddhhmiss格式.
    return yyyy + Smm + Sdd + Shh + Smi + Sss;
    }
    if (Format.equals("yyyy-mm-dd hh:mi:ss")) {
    //yyyy-mm-dd hh:mi:ss格式.
    return yyyy + "-" + Smm + "-" + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
    if (Format.equals("yyyy/mm/dd hh:mi:ss")) {
    //yyyy/mm/dd hh:mi:ss格式.
    return yyyy + "/" + Smm + "/" + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
    if (Format.equals("yyyy.mm.dd hh:mi:ss")) {
    //yyyy.mm.dd hh:mi:ss格式.
    return yyyy + "." + Smm + "." + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
   //******时分秒格式----------------------------------------------
    if (Format.equals("hh:mi:ss")) {
    //hh:mi:ss格式.
    return Shh + ":" + Smi + ":" + Sss;
    }
   //*****单独取年或月或日格式----------------------------------------------
    if (Format.equals("yyyy")) {
    //yyyy格式.
    return String.valueOf(yyyy);
    }
    if (Format.equals("mm")) {
    //mm格式.
    return String.valueOf(Smm);
    }
    if (Format.equals("dd")) {
    //dd格式.
    return String.valueOf(Sdd);
    }
    return "你的格式不对!";
    }
   //***将字符串日期按格式返回任意部份--------------------------------
    public String getFormatDateS(String Format, String StrDate) {
    	if(StrDate.trim().equals("")) return "";
    dateintStr(StrDate); //初始化时间控件.
    Format = Format.trim().toLowerCase();
   //******年月日格式----------------------------------------------
    if (Format.equals("yyyy-mm-dd")) {
    //yyyy-MM-dd格式.
    return yyyy + "-" + Smm + "-" + Sdd;
    }
    if (Format.equals("yyyy/mm/dd")) {
    //yyyy/MM/dd格式.
    return yyyy + "/" + Smm + "/" + Sdd;
    }
    if (Format.equals("yyyymmdd")) {
    //yyyyMMdd格式.
    return yyyy + Smm + Sdd;
    }
    if (Format.equals("yyyy.mm.dd")) {
    //yyyy.mm.dd格式.
    return yyyy + "." + Smm + "." + Sdd;
    }
   //******年月格式----------------------------------------------
    if (Format.equals("yyyy-mm")) {
    //yyyy-MM格式.
    return yyyy + "-" + Smm;
    }
    if (Format.equals("yyyy/mm")) {
    //yyyy/mm格式.
    return yyyy + "/" + Smm;
    }
    if (Format.equals("yyyymm")) {
    //yyyymm格式.
    return yyyy + Smm;
    }
    if (Format.equals("yyyy.mm")) {
    //yyyy.mm格式.
    return yyyy + "." + Smm;
    }
   //******月日格式----------------------------------------------
    if (Format.equals("mm-dd")) {
    //mm-dd格式.
    return Smm + "-" + Sdd;
    }
    if (Format.equals("mm/dd")) {
    //mm/dd格式.
    return Smm + "/" + Sdd;
    }
    if (Format.equals("mmdd")) {
    //mmdd格式.
    return Smm + Sdd;
    }
    if (Format.equals("mm.dd")) {
    //mm.dd格式.
    return Smm + "." + Sdd;
    }
   //******年月日时分秒格式-------------------------------------------
    if (Format.equals("yyyymmddhhmiss")) {
    //yyyymmddhhmiss格式.
    return yyyy + Smm + Sdd + Shh + Smi + Sss;
    }
    if (Format.equals("yyyy-mm-dd hh:mi:ss")) {
    //yyyy-mm-dd hh:mi:ss格式.
    return yyyy + "-" + Smm + "-" + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
    if (Format.equals("yyyy/mm/dd hh:mi:ss")) {
    //yyyy/mm/dd hh:mi:ss格式.
    return yyyy + "/" + Smm + "/" + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
    if (Format.equals("yyyy.mm.dd hh:mi:ss")) {
    //yyyy.mm.dd hh:mi:ss格式.
    return yyyy + "." + Smm + "." + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
   //******时分秒格式----------------------------------------------
    if (Format.equals("hh:mi:ss")) {
    //hh:mi:ss格式.
    return Shh + ":" + Smi + ":" + Sss;
    }
   //*****单独取年或月或日格式----------------------------------------------
    if (Format.equals("yyyy")) {
    //yyyy格式.
    return String.valueOf(yyyy);
    }
    if (Format.equals("mm")) {
    //mm格式.
    return String.valueOf(Smm);
    }
    if (Format.equals("dd")) {
    //mm格式.
    return String.valueOf(Sdd);
    }
    return "你的格式不对!";
    }
    
    /**
     * 判断时间date1是否在时间date2之前 
     * @param date1
     * @param date2
     * @return
     */
	public static boolean isDateBefore(String date1,String date2){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	} 
	
	/**
	 * 判断当前时间是否在时间date2之前 
	 * @param date2
	 * @return
	 */
	public static boolean isDateBefore(String date2){ 
		Date date1 = new Date(); 
		DateFormat df = DateFormat.getDateTimeInstance(); 
		try {
			return date1.before(df.parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		} 
	}
}
