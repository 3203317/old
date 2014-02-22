package net.foreworld.utils;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class AsciiUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AsciiUtil __au = new AsciiUtil();

		__au.test1();

	}

	private void test1() {
		String ssStr1 = "123abcdABCDh ";
		for (int i = 0; i < ssStr1.length(); i++) {
			System.out.println(" <br> " + ssStr1.substring(i, i + 1) + "--> asc== " + (int) ssStr1.charAt(i));
			System.out.println(" <br> " + (int) ssStr1.charAt(i) + "--> asc== " + (char) ((int) ssStr1.charAt(i)));
		}

	}

}
