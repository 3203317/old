package net.newcapec.sca.util;

import java.util.UUID;
/**
*
*类描述：uuid主键生成机制
*@author: 赵鹏飞
*@date： 日期：2012-9-12 时间：下午03:19:15
*@version 1.0
*修改人：
*修改时间：
*修改备注：
*/
public class UUIDGenerator {
	 public UUIDGenerator() {
	    }
	 /**
	     * 获得一个UUID
	     * @return String UUID
	     */
	    public static String getUUID(){
	        String s = UUID.randomUUID().toString();
	      //去掉“-”符号
	        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
	    }
	    /**
	     * 获得指定数目的UUID
	     * @param number int 需要获得的UUID数量
	     * @return String[] UUID数组
	     */
	    public static String[] getUUID(int number){
	        if(number < 1){
	            return null;
	        }
	        String[] ss = new String[number];
	        for(int i=0;i<number;i++){
	            ss[i] = getUUID();
	        }
	        return ss;
	    }
}
