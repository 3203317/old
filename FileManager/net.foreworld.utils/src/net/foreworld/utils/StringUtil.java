package net.foreworld.utils;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class StringUtil {

	/**
	 * 字符转换为10进制
	 * 
	 * @param $val
	 * @return
	 */
	public static int toAscii(char $val) {
		return (int) $val;
	}

	/**
	 * 字符转换为16进制
	 * 
	 * @param $val
	 * @return
	 */
	public static String toHex(char $val) {
		return Integer.toHexString(toAscii($val));
	}

	/**
	 * 字符串转换为10进制 例： 传入 hn 程序取h 转换 104
	 * 
	 * @param $val
	 *            取字符串的首位
	 * @return
	 */
	public static int toAscii(String $val) {
		return (int) $val.charAt(0);
	}

	/**
	 * 字符串转为16进制 例： 传入nhn 程序取h 转换 6e
	 * 
	 * @param $val
	 *            取字符串首位
	 * @return
	 */
	public static String toHex(String $val) {
		return Integer.toHexString(toAscii($val));
	}

	/**
	 * 字符串转16进制
	 * 
	 * @param $val
	 * @return 空格分割的16进制
	 */
	public static String toHexs(String $val) {

		String __result = "";

		for (int __i = 0, __j = ($val == null ? 0 : $val.length()); __i < __j; __i++) {

			__result += toHex($val.charAt(__i)) + " ";
		}

		return __result.trim();
	}

	/**
	 * 左补位
	 * 
	 * @param $val
	 * @param $valLen
	 *            传递的字符串长度
	 * @param $coverStr
	 *            要补位的字符串
	 * @param $length
	 *            返回字符串结果总长
	 * @return
	 */
	public static String leftCover(String $val, int $valLen, String $coverStr, int $length) {

		while ($valLen < $length) {
			StringBuffer __sb = new StringBuffer();
			__sb.append($coverStr).append($val);

			$val = __sb.toString();
			$valLen = $val.length();
		}
		return $val;
	}

	/**
	 * 右补位
	 * 
	 * @param $val
	 * @param $valLen
	 *            传递的字符串长度
	 * @param $coverStr
	 *            要补位的字符串
	 * @param $length
	 *            返回字符串结果总长
	 * @return
	 */
	public static String rightCover(String $val, int $valLen, String $coverStr, int $length) {

		while ($valLen < $length) {
			StringBuffer __sb = new StringBuffer();
			__sb.append($val).append($coverStr);

			$val = __sb.toString();
			$valLen = $val.length();
		}
		return $val;
	}

	/**
	 * Returns TRUE in case the given String has a value that is not "" or
	 * <code>NULL</code>.
	 * 
	 * @param $str
	 *            The String to check
	 * @return boolean TRUE in case the String has an value not "" or
	 *         <code>NULL</code>.
	 */
	public static boolean isSet(String $str) {
		return ($str != null && $str.length() > 0);
	}

	/**
	 * Checks whether the given String is of the Format "R,G,B" with each of the
	 * components being an parseable Integer.
	 * 
	 * @param $rgb
	 *            The String to check for a Valid RGB Value.
	 * @return <code>TRUE</code> if the given String is a valid RGB Value.
	 */
	public static boolean isValidRGB(String $rgb) {
		if ($rgb == null)
			return true;

		String __split[] = $rgb.split(","); //$NON-NLS-1$
		if (__split.length != 3)
			return false;

		try {
			Integer.parseInt(__split[0]);
			Integer.parseInt(__split[1]);
			Integer.parseInt(__split[2]);
		} catch (NumberFormatException $ex) {
			return false;
		}

		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String d = Integer.toHexString(1111);

		String dd = leftCover(d, d.length(), "0", 4);

		// System.out.println(dd);
		// System.out.println(dd.substring(0, 2) + " " + dd.substring(2, 4));

		// System.out.println(toAscii("nhx") +" "+ toHex("nhx"));
		//
		//
		// System.out.println(toHexs("hnxy1").toUpperCase());
		// // System.out.println(toHexs("hnxy4772").toUpperCase());
		//
		// String s4 = "232";
		//
		// // System.out.println(addZeroForNum(s4, 2));
		//
		// System.out
		// .println(rightCover("68 6E 78 79 34 37 37 32 ", 8, "20 ", 60));

		dd = leftCover(Integer.toHexString(1400), 2, "0", 4);

		System.out.println(dd.substring(0, 2) + " " + dd.substring(2, 4));
	}

}
