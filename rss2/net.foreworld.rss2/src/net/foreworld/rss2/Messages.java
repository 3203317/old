package net.foreworld.rss2;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author ����
 * @Aug 6, 2008 8:49:59 PM
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "Message_Zh_CN";
	
	public static String ChannelNavigator_Name;
	public static String FavoritesNavigator_Name;
	
	/**************************************************************/
	public static String AddChannelAction_Text;
	
	/**************************************************************/
	/**
	 * ȥ��final Ȼ���ڰ�Message_Zh_CN.properties��FOREWORLD_UPDATE_SITEǰ���#ȥ���Ϳ�������
	 */
	public static final String FOREWORLD_UPDATE_SITE = "http://www.foreworld.net/site/";
	
	static{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
