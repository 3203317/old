package net.foreworld.utils;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class UUIDUtil {

	public static final String create() {
		return java.util.UUID.randomUUID().toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(create());
	}

}
