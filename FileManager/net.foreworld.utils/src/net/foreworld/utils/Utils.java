package net.foreworld.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Utils {

	/**
	 * 冒泡算出最后的日期
	 * 
	 * @param $map
	 * @return 返回主键UUID
	 */
	public static String lastDateTime(HashMap<String, String> $map) {

		String __last_key = "";
		String __last_value = "";

		for (Map.Entry<String, String> ___map : $map.entrySet()) {
			if ("".equals(__last_key)) {
				__last_key = ___map.getKey();
				__last_value = ___map.getValue();
			} else {
				boolean ____isLast = DateUtil.isDateBefore(__last_value, ___map.getValue());

				if (____isLast) {
					__last_key = ___map.getKey();
					__last_value = ___map.getValue();
				}
			}
		}

		return __last_key;
	}
}
