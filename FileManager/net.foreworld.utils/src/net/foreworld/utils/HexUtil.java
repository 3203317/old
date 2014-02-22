package net.foreworld.utils;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class HexUtil {

	public static void main(String[] args) {

		String __val = "A3";

		byte __b = toByte(__val);

		System.out.println(__b);
		System.out.println(Integer.toHexString(__b));
	}

	/**
	 * 16进制转byte<br/>
	 * 字符串类型 如 "A3" 或 "0xA3"<br/>
	 * 一般的字符串类型的不能正确的转换成byte
	 * 
	 * @param $val
	 * @return byte
	 */
	public static byte toByte(String $val) {

		if ($val.indexOf("0x") == -1)
			$val = "0x" + $val;

		return Integer.decode($val).byteValue();
	}

}
