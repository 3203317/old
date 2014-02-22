package com.myfvn.ui.actions;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.myfvn.ui.actions.messages";//$NON-NLS-1$

	public static String FVNActionFactory_OPEN_HOMEPAGE;
	public static String FVNActionFactory_FIND;
	public static String FVNActionFactory_SEARCH;
	public static String FVNActionFactory_MINIMIZE_SHELL;

	public static String FindUpdatesAction_UPDATE_SEARCH;
	public static String FindUpdatesAction_WARNING_SEARCH_FAILED;
	public static String FindUpdatesAction_REASON;
	public static String FindUpdatesAction_CHECK_UPDATES;

	public static String OpenInNewTabAction_OPEN_IN_NEW_TAB;
	public static String OpenInNewTabAction_OPEN_ALL_IN_NEW_TABS;

	public static String SearchFilesAction_SEARCH_FILES;

	public static String DeleteTypesAction_CONFIRM_DELETE;
	public static String DeleteTypesAction_NO_UNDO;
	public static String DeleteTypesAction_CONFIRM_DELETE_EMAILBOX;
	public static String DeleteTypesAction_CONFIRM_DELETE_ELEMENTS;

	private Messages() {
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
