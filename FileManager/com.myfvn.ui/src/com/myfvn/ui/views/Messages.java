package com.myfvn.ui.views;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.myfvn.ui.views.messages";//$NON-NLS-1$

	public static String FileNavigatorView_NEW;
	public static String FileNavigatorView_NEW_FILE;
	public static String FileNavigatorView_NEW_EMAIL;
	public static String FileNavigatorView_NEW_FOLDER;
	public static String FileNavigatorView_NEW_EMAILACCOUNT;
	public static String FileNavigatorView_FILTER_ELEMENTS;
	public static String FileNavigatorView_SHOW_ALL;
	public static String FileNavigatorView_SHOW_MAIL;
	public static String FileNavigatorView_SHOW_ATTACHMENT;
	public static String FileNavigatorView_COLLAPSE_ALL;
	public static String FileNavigatorView_LINK_WITH_EDITOR;
	public static String FileNavigatorView_FIND;
	public static String FileNavigatorView_ALWAYS_SHOW;
	public static String FileNavigatorView_BEGIN_WHEN_TYPING;
	public static String FileNavigatorView_SHOW_FAVICONS;
	public static String FileNavigatorView_SHARING;
	public static String FileNavigatorView_HELPER;
	public static String FileNavigatorView_COMPARE_WITH;
	public static String FileNavigatorView_REPLACE_WITH;
	public static String FileNavigatorView_LATEST_FROM_REPOSITORY;
	public static String FileNavigatorView_LOCAL_HISTORY;
	public static String FileNavigatorView_REMOTE_HISTORY;
	public static String FileNavigatorView_UPDATE;
	public static String FileNavigatorView_COMMIT;
	public static String FileNavigatorView_COPY;
	public static String FileNavigatorView_EXPORT;
	public static String FileNavigatorView_CLEANUP;
	public static String FileNavigatorView_SHOW_HISTORY;
	public static String FileNavigatorView_SHOW_LOCAL_HISTORY;
	public static String FileNavigatorView_CONFIGURE;
	public static String FileNavigatorView_OPEN_THE_FILE_PATH;
	public static String FileNavigatorView_REFRESH;

	public static String FileNavigatorSearchBar_FIND_BY_FILE_NAME;
	public static String FileNavigatorSearchBar_FIND_BY_CREATETIME;
	public static String FileNavigatorSearchBar_MANAGE_SETS;
	public static String FileNavigatorSearchBar_PREVIOUS_SET;
	public static String FileNavigatorSearchBar_NEXT_SET;
	public static String FileNavigatorSearchBar_CLEAR;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
