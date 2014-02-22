package net.foreworld.utils;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class ByteUtil {

	/**
	 * byte数组转换16进制字符串<br/>
	 * 结果: 29 29 A3 00 2F 00 00 00 00 68 6E 78 79
	 * 
	 * @param $val
	 * @return
	 */
	public static String toHexString(byte[] $val) {

		String __result = "";

		for (int __i = 0, __j = ($val == null ? 0 : $val.length); __i < __j; __i++) {
			String ___hex = Integer.toHexString($val[__i] & 0xFF);

			if (___hex.length() == 1)
				___hex = "0" + ___hex;

			__result = __result + ___hex.toUpperCase();
		}

		return __result;
	}

	/**
	 * byte数组转换16进制字符串 根据第二个参数截取 返回包括68之前的字符串<br/>
	 * 例如: 29 29 A3 00 2F 00 00 00 68 68 6E 78 79<br/>
	 * 第二个参数传递 68<br/>
	 * 结果: 29 29 A3 00 2F 00 00 00 68<br/>
	 * 没有找到substring 则返回空
	 * 
	 * @param $val
	 * @param $substring
	 * @return
	 */
	public static String toHexString(byte[] $val, String $substring) {

		String __result = "";

		for (int __i = 0, __j = ($val == null ? 0 : $val.length); __i < __j; __i++) {
			String ___hex = Integer.toHexString($val[__i] & 0xFF);

			if (___hex.length() == 1)
				___hex = "0" + ___hex;

			__result = __result + ___hex.toUpperCase();
		}

		__result = __result.substring(0, (__result.indexOf($substring) == -1 ? 0 : __result.indexOf($substring) + $substring.length()));

		return __result;
	}

}
