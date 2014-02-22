package com.myfvn.ui.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.myfvn.ui.preferences.messages";//$NON-NLS-1$

	public static String SharingPreferencesPage_AVAILABLE_COMMUNITIES;
	public static String SharingPreferencesPage_SELECT_ALL;
	public static String SharingPreferencesPage_MOVE_DOWN;
	public static String SharingPreferencesPage_MOVE_UP;
	public static String SharingPreferencesPage_DESELECT_ALL;
	public static String SharingPreferencesPage_COMMUNITY_INFO;
	public static String SharingPreferencesPage_SELECT_COMMUNITY;

	private Messages() {
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
