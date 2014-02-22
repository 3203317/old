package net.foreworld.rss2;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author 黄鑫
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
	 * 去掉final 然后在把Message_Zh_CN.properties中FOREWORLD_UPDATE_SITE前面的#去掉就可以用了
	 */
	public static final String FOREWORLD_UPDATE_SITE = "http://www.foreworld.net/site/";
	
	static{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
