package com.myfvn.ui;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.myfvn.ui.messages";//$NON-NLS-1$

	public static String ApplicationActionBarAdvisor_FILE;
	public static String ApplicationActionBarAdvisor_EDIT;
	public static String ApplicationActionBarAdvisor_VIEW;
	public static String ApplicationActionBarAdvisor_GO;
	public static String ApplicationActionBarAdvisor_MAILS;
	public static String ApplicationActionBarAdvisor_TOOLS;
	public static String ApplicationActionBarAdvisor_WINDOW;
	public static String ApplicationActionBarAdvisor_HELP;
	public static String ApplicationActionBarAdvisor_LAYOUT;
	public static String ApplicationActionBarAdvisor_CLASSIC_LAYOUT;
	public static String ApplicationActionBarAdvisor_VERTICAL_LAYOUT;
	public static String ApplicationActionBarAdvisor_ZOOM;
	public static String ApplicationActionBarAdvisor_ZOOM_IN;
	public static String ApplicationActionBarAdvisor_ZOOM_OUT;
	public static String ApplicationActionBarAdvisor_RESET;
	public static String ApplicationActionBarAdvisor_TOOLBARS;
	public static String ApplicationActionBarAdvisor_TOOLBAR;
	public static String ApplicationActionBarAdvisor_STATUS;
	public static String ApplicationActionBarAdvisor_LOCK_TOOLBAR;
	public static String ApplicationActionBarAdvisor_CUSTOMIZE_TOOLBAR;
	public static String ApplicationActionBarAdvisor_FULL_SCREEN;
	public static String ApplicationActionBarAdvisor_HIDE_TOOLBAR;
	public static String ApplicationActionBarAdvisor_CONFIGURE;
	public static String ApplicationActionBarAdvisor_OPEN_IN_NEW_TAB;
	public static String ApplicationActionBarAdvisor_OPEN_ALL_IN_NEW_TABS;

	public static String ApplicationWorkbenchWindowAdvisor_EXIT_TITLE;
	public static String ApplicationWorkbenchWindowAdvisor_EXIT_CONTENT;

	public static String HookSysTray_TITLE;

	public static String StatusLineUpdater_ITEM_SELECTED;
	public static String StatusLineUpdater_N_ITEMS_SELECTED;
	public static String StatusLineUpdater_N_EMAIL;
	public static String StatusLineUpdater_N_EMAILS;
	public static String StatusLineUpdater_N_ATTACHMENT;
	public static String StatusLineUpdater_N_ATTACHMENTS;

	public static String FvnUI_TITLE;
	public static String FvnUI_CLASSIC_LAYOUT;
	public static String FvnUI_VERTICAL_LAYOUT;

	public static String ApplicationActionBarAdvisor_FILTER_ELEMENTS;
	public static String ApplicationActionBarAdvisor_SHOW_ALL;
	public static String ApplicationActionBarAdvisor_SHOW_MAIL;
	public static String ApplicationActionBarAdvisor_SHOW_ATTACHMENT;

	private Messages() {
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
