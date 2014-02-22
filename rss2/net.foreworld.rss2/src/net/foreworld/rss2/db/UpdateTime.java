package net.foreworld.rss2.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 10, 2008 5:44:34 PM
 */
public class UpdateTime {
	//���� Ϊjob���õ�
	private static int baseNum = 60000;
	
	public static List getData(){
		List list = new ArrayList();
		
        HashMap map = new HashMap();
        map.put("time", Integer.toString(baseNum * 15));
        map.put("desc", "15����");
        list.add(map);
		
        map = new HashMap();
        map.put("time", Integer.toString(baseNum * 30));
        map.put("desc", "30����");
        list.add(map);
		
        map = new HashMap();
        map.put("time", Integer.toString(baseNum * 60));
        map.put("desc", "1Сʱ");
        list.add(map);
		
        map = new HashMap();
        map.put("time", Integer.toString(baseNum * 120));
        map.put("desc", "2Сʱ");
        list.add(map);
		
        map = new HashMap();
        map.put("time", Integer.toString(baseNum * 240));
        map.put("desc", "4Сʱ");
        list.add(map);
		
        map = new HashMap();
        map.put("time", Integer.toString(baseNum * 360));
        map.put("desc", "6Сʱ");
        list.add(map);
		
        map = new HashMap();
        map.put("time", Integer.toString(baseNum * 720));
        map.put("desc", "12Сʱ");
        list.add(map);
		
        map = new HashMap();
        map.put("time", Integer.toString(baseNum * 1440));
        map.put("desc", "1��");
        list.add(map);
        
		return list;
	}
	
	/**
	 * ��ȡ����ʱ��
	 * @param i
	 * @return
	 */
	public static int getUpdateTime(int i){
		switch(i){
		case 0:
			i = 15;
			break;
		case 1:
			i = 30;
			break;
		case 2:
			i = 60;
			break;
		case 3:
			i = 120;
			break;
		case 4:
			i = 240;
			break;
		case 5:
			i = 360;
			break;
		case 6:
			i = 720;
			break;
		case 7:
			i = 1440;
			break;
		}
		return baseNum * i;
	}
	
	/**
	 * ��ȡ��ѡ�����
	 * @param value
	 * @return
	 */
	public static int getSelectionIndex(int value){
		switch(value / baseNum){
		case 15:
			value = 0;
			break;
		case 30:
			value = 1;
			break;
		case 60:
			value = 2;
			break;
		case 120:
			value = 3;
			break;
		case 240:
			value = 4;
			break;
		case 360:
			value = 5;
			break;
		case 720:
			value = 6;
			break;
		case 1440:
			value = 7;
			break;
		}
		return value;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List getData2(){
		List list = new ArrayList();
		
        HashMap map = new HashMap();
        map.put("time", "0");
        map.put("desc", "15����");
        list.add(map);
		
        map = new HashMap();
        map.put("time", "1");
        map.put("desc", "30����");
        list.add(map);
		
        map = new HashMap();
        map.put("time", "2");
        map.put("desc", "1Сʱ");
        list.add(map);
		
        map = new HashMap();
        map.put("time", "3");
        map.put("desc", "2Сʱ");
        list.add(map);
		
        map = new HashMap();
        map.put("time", "4");
        map.put("desc", "4Сʱ");
        list.add(map);
		
        map = new HashMap();
        map.put("time", "5");
        map.put("desc", "6Сʱ");
        list.add(map);
		
        map = new HashMap();
        map.put("time", "6");
        map.put("desc", "12Сʱ");
        list.add(map);
		
        map = new HashMap();
        map.put("time", "7");
        map.put("desc", "1��");
        list.add(map);
        
		return list;
	}
}
