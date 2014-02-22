package net.foreworld.rss2.utils;

import java.net.URL;

/**
 * �ַ�������������
 * 
 * @author ���
 * 
 */
public class StringUtils {

	/**
	 * ����ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		try{
			return (str == null || "".equals(str) || str.matches("\\s+"));// str.trim().equals("")
		}
		catch(Exception e){
		}
		return true;
		// ||
	}

	/**
	 * ��url�ַ����л�ȡ��ȷ��url������ȷʱ����""
	 * 
	 * @param url
	 * @return
	 */
	public static String getURLString(String url) {
		String ret = url.trim();
		if (StringUtils.isEmpty(url)) {
			return "";
		}

		int end = ret.indexOf(' ');
		if (end > 1) {
			ret = ret.substring(0, end);
		}
		try {
			URL u = new URL(ret);
			ret = String.valueOf(u);
		} catch (Exception e) {
//			logger.info(url + "����һ��URL��ַ");
			ret = "";
		}
		return ret;
	}

	/**
	 * ��ȡurl�ַ������ַ���������suffixies�е�ĳ��Ԫ�ؽ��������򷵻�Ϊ""
	 * 
	 * @param url
	 * @param suffixies
	 * @return
	 */
	public static String getURLString(String url, String[] suffixies) {
		String ret = getURLString(url);
		boolean match = false;
		if (suffixies != null) {
			for (int i = 0; i < suffixies.length; i++) {
				String suffix = suffixies[i];
				if (ret.endsWith(suffix)) {
					match = true;
					break;
				}
			}
		}
		if (!match)
			ret = "";
		return ret;
	}
}