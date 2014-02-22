package net.foreworld.rss2.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 
 * @author ����
 * @Jan 15, 2008 9:58:48 PM
 */
/**
 * <p>Title: ����ת������</p>
 * <p>Description: �����¹��ܷ�������ʹ��</p>
 * function 1:dateintstr(StrDate) :���ַ���תΪ���ڲ���ʼ��������
 * StrDate:���ڸ�ʽ���ַ���.
 * function 2:dateint() : ��ʼ����ǰ���ڵĸ�����
 * function 3:getnoncetime() : ȡ��ǰϵͳʱ�䲢��ȷ������
 * function 4:getymd() :ȡ��ǰϵͳʱ���������
 * function 5:getTime() :ȡ����ǰϵͳ��ʱ����
 * function 6:getFormatDate(Format) :��ʽ����ǰϵͳʱ��
 * Format��֧�����¸�ʽ:
 * yyyy-MM-dd��ʽ:������
 *
 * yyyy/MM/dd��ʽ:������
 *
 * yyyyMMdd��ʽ:������
 *
 * yyyy.mm.dd��ʽ:������
 *
 * yyyy-MM��ʽ:����
 *
 * yyyy/mm��ʽ:����
 *
 * yyyymm��ʽ:����
 *
 * yyyy.mm��ʽ:����
 *
 * mm-dd��ʽ:����
 *
 * mm/dd��ʽ:����
 *
 * mmdd��ʽ:����
 *
 * mm.dd��ʽ:����
 *
 * yyyymmddhhmiss��ʽ:��ǰ������ ʱ����
 *
 * yyyy-mm-dd hh:mi:ss��ʽ:��ǰ������ ʱ����
 *
 * yyyy/mm/dd hh:mi:ss��ʽ:��ǰ������ ʱ����
 *
 * yyyy.mm.dd hh:mi:ss��ʽ:��ǰ������ ʱ����
 *
 * hh:mi:ss��ʽ:��ǰʱ����
 *
 * yyyy��ʽ:��ǰ���
 *
 * mm��ʽ:��ǰ�·�
 *
 * dd��ʽ:���µĵ�ǰ����һ��.
 * function 7:getFormatDateS(Format,StrDate) :��ʽ����ǰϵͳʱ��
 * Format ��֧�����¸�ʽ:���ϣ�
 * StrDate:�����ַ�����
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * ���ߣ�WRL
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
   //***--------------------��ʼ���ַ���תΪ����----------------------
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
   //***--------------------��ʼ����ǰʱ��-----------------------
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
    //***-----------------ȡ����ǰʱ��---------------------------
    public String getnoncetime() {
    dateint(); //��ʼ��ʱ��ؼ�.
    sNowTime = yyyy + "-" + Smm + "-" + Sdd + " " + Shh + ":" + Smi + ":" +
    Sss +
    "." + ms;
    return sNowTime;
    }
    //***--------------------ȡ����ǰʱ���������------------------
    public String getymd() {
    dateint(); //��ʼ��ʱ��ؼ�.
    sNowTime = yyyy + "-" + Smm + "-" + Sdd;
    return sNowTime;
    }
   //***--------------------ȡ����ǰ���㼸�ּ���-------------------
    public String getTime() {
    dateint(); //��ʼ��ʱ��ؼ�.
    sNowTime = Shh + ":" + Smi + ":" + Sss;
    return sNowTime;
    }
    //***����ʽ���ص�ǰʱ������ⲿ��--------------------------------
    public String getFormatDate(String Format) {
    dateint(); //��ʼ��ʱ��ؼ�.
    Format = Format.trim().toLowerCase();
   //******�����ո�ʽ----------------------------------------------
    if (Format.equals("yyyy-mm-dd")) {
    //yyyy-MM-dd��ʽ.
    return yyyy + "-" + Smm + "-" + Sdd;
    }
    if (Format.equals("yyyy/mm/dd")) {
    //yyyy/MM/dd��ʽ.
    return yyyy + "/" + Smm + "/" + Sdd;
    }
    if (Format.equals("yyyymmdd")) {
    //yyyyMMdd��ʽ.
    return yyyy + Smm + Sdd;
    }
    if (Format.equals("yyyy.mm.dd")) {
    //yyyy.mm.dd��ʽ.
    return yyyy + "." + Smm + "." + Sdd;
    }
   //******���¸�ʽ----------------------------------------------
    if (Format.equals("yyyy-mm")) {
    //yyyy-MM��ʽ.
    return yyyy + "-" + Smm;
    }
    if (Format.equals("yyyy/mm")) {
    //yyyy/mm��ʽ.
    return yyyy + "/" + Smm;
    }
    if (Format.equals("yyyymm")) {
    //yyyymm��ʽ.
    return yyyy + Smm;
    }
    if (Format.equals("yyyy.mm")) {
    //yyyy.mm��ʽ.
    return yyyy + "." + Smm;
    }
   //******���ո�ʽ----------------------------------------------
    if (Format.equals("mm-dd")) {
    //mm-dd��ʽ.
    return Smm + "-" + Sdd;
    }
    if (Format.equals("mm/dd")) {
    //mm/dd��ʽ.
    return Smm + "/" + Sdd;
    }
    if (Format.equals("mmdd")) {
    //mmdd��ʽ.
    return Smm + Sdd;
    }
    if (Format.equals("mm.dd")) {
    //mm.dd��ʽ.
    return Smm + "." + Sdd;
    }
   //******������ʱ�����ʽ-------------------------------------------
    if (Format.equals("yyyymmddhhmiss")) {
    //yyyymmddhhmiss��ʽ.
    return yyyy + Smm + Sdd + Shh + Smi + Sss;
    }
    if (Format.equals("yyyy-mm-dd hh:mi:ss")) {
    //yyyy-mm-dd hh:mi:ss��ʽ.
    return yyyy + "-" + Smm + "-" + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
    if (Format.equals("yyyy/mm/dd hh:mi:ss")) {
    //yyyy/mm/dd hh:mi:ss��ʽ.
    return yyyy + "/" + Smm + "/" + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
    if (Format.equals("yyyy.mm.dd hh:mi:ss")) {
    //yyyy.mm.dd hh:mi:ss��ʽ.
    return yyyy + "." + Smm + "." + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
   //******ʱ�����ʽ----------------------------------------------
    if (Format.equals("hh:mi:ss")) {
    //hh:mi:ss��ʽ.
    return Shh + ":" + Smi + ":" + Sss;
    }
   //*****����ȡ����»��ո�ʽ----------------------------------------------
    if (Format.equals("yyyy")) {
    //yyyy��ʽ.
    return String.valueOf(yyyy);
    }
    if (Format.equals("mm")) {
    //mm��ʽ.
    return String.valueOf(Smm);
    }
    if (Format.equals("dd")) {
    //dd��ʽ.
    return String.valueOf(Sdd);
    }
    return "��ĸ�ʽ����!";
    }
   //***���ַ������ڰ���ʽ�������ⲿ��--------------------------------
    public String getFormatDateS(String Format, String StrDate) {
    	if(StrDate.trim().equals("")) return "";
    dateintStr(StrDate); //��ʼ��ʱ��ؼ�.
    Format = Format.trim().toLowerCase();
   //******�����ո�ʽ----------------------------------------------
    if (Format.equals("yyyy-mm-dd")) {
    //yyyy-MM-dd��ʽ.
    return yyyy + "-" + Smm + "-" + Sdd;
    }
    if (Format.equals("yyyy/mm/dd")) {
    //yyyy/MM/dd��ʽ.
    return yyyy + "/" + Smm + "/" + Sdd;
    }
    if (Format.equals("yyyymmdd")) {
    //yyyyMMdd��ʽ.
    return yyyy + Smm + Sdd;
    }
    if (Format.equals("yyyy.mm.dd")) {
    //yyyy.mm.dd��ʽ.
    return yyyy + "." + Smm + "." + Sdd;
    }
   //******���¸�ʽ----------------------------------------------
    if (Format.equals("yyyy-mm")) {
    //yyyy-MM��ʽ.
    return yyyy + "-" + Smm;
    }
    if (Format.equals("yyyy/mm")) {
    //yyyy/mm��ʽ.
    return yyyy + "/" + Smm;
    }
    if (Format.equals("yyyymm")) {
    //yyyymm��ʽ.
    return yyyy + Smm;
    }
    if (Format.equals("yyyy.mm")) {
    //yyyy.mm��ʽ.
    return yyyy + "." + Smm;
    }
   //******���ո�ʽ----------------------------------------------
    if (Format.equals("mm-dd")) {
    //mm-dd��ʽ.
    return Smm + "-" + Sdd;
    }
    if (Format.equals("mm/dd")) {
    //mm/dd��ʽ.
    return Smm + "/" + Sdd;
    }
    if (Format.equals("mmdd")) {
    //mmdd��ʽ.
    return Smm + Sdd;
    }
    if (Format.equals("mm.dd")) {
    //mm.dd��ʽ.
    return Smm + "." + Sdd;
    }
   //******������ʱ�����ʽ-------------------------------------------
    if (Format.equals("yyyymmddhhmiss")) {
    //yyyymmddhhmiss��ʽ.
    return yyyy + Smm + Sdd + Shh + Smi + Sss;
    }
    if (Format.equals("yyyy-mm-dd hh:mi:ss")) {
    //yyyy-mm-dd hh:mi:ss��ʽ.
    return yyyy + "-" + Smm + "-" + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
    if (Format.equals("yyyy/mm/dd hh:mi:ss")) {
    //yyyy/mm/dd hh:mi:ss��ʽ.
    return yyyy + "/" + Smm + "/" + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
    if (Format.equals("yyyy.mm.dd hh:mi:ss")) {
    //yyyy.mm.dd hh:mi:ss��ʽ.
    return yyyy + "." + Smm + "." + Sdd + " " + Shh + ":" + Smi + ":" + Sss;
    }
   //******ʱ�����ʽ----------------------------------------------
    if (Format.equals("hh:mi:ss")) {
    //hh:mi:ss��ʽ.
    return Shh + ":" + Smi + ":" + Sss;
    }
   //*****����ȡ����»��ո�ʽ----------------------------------------------
    if (Format.equals("yyyy")) {
    //yyyy��ʽ.
    return String.valueOf(yyyy);
    }
    if (Format.equals("mm")) {
    //mm��ʽ.
    return String.valueOf(Smm);
    }
    if (Format.equals("dd")) {
    //mm��ʽ.
    return String.valueOf(Sdd);
    }
    return "��ĸ�ʽ����!";
    }
    
    /**
     * �ж�ʱ��date1�Ƿ���ʱ��date2֮ǰ 
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
	 * �жϵ�ǰʱ���Ƿ���ʱ��date2֮ǰ 
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
