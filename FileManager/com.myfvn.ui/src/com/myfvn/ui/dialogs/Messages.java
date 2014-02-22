package com.myfvn.ui.dialogs;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.myfvn.ui.dialogs.messages";//$NON-NLS-1$

	public static String LoginDialog_MONITOR_VALIDATE;
	public static String LoginDialog_MONITOR_VALIDATE_DESC;
	public static String LoginDialog_USERNAME;
	public static String LoginDialog_PASSWORD;
	public static String LoginDialog_REMEMBER_PASSWORD;
	public static String LoginDialog_AUTO_LOGIN;
	public static String LoginDialog_REGISTER;
	public static String LoginDialog_WELCOME;
	public static String LoginDialog_INPUT;
	public static String LoginDialog_TITLE;
	public static String LoginDialog_FAILURE;
	public static String LoginDialog_OK_TEXT;
	public static String LoginDialog_CANCEL_TEXT;

	public static String ManageSetsDialog_MANAGE_SETS;
	public static String ManageSetsDialog_SETS;
	public static String ManageSetsDialog_SELECT_SET;
	public static String ManageSetsDialog_NEW;
	public static String ManageSetsDialog_EDIT;
	public static String ManageSetsDialog_DELETE;
	public static String ManageSetsDialog_SET_CONTENT;
	public static String ManageSetsDialog_1_FILE;
	public static String ManageSetsDialog_N_FILES;
	public static String ManageSetsDialog_1_ATTACHMENT;
	public static String ManageSetsDialog_N_ATTACHMENTS;
	public static String ManageSetsDialog_EMPTY_SET;
	public static String ManageSetsDialog_DELETE_LAST_SET_ERROR;

	public static String ConfirmDialog_DELETE;
	public static String ConfirmDialog_NEVER_ASK_AGAIN;

	public static String CustomizeToolbarDialog_CUSTOMIZE_TOOLBAR;
	public static String CustomizeToolbarDialog_USE_MOUSE_INFO;
	public static String CustomizeToolbarDialog_SHOW;
	public static String CustomizeToolbarDialog_RESTORE_DEFAULTS;
	public static String CustomizeToolbarDialog_MOVE_DOWN;
	public static String CustomizeToolbarDialog_MOVE_UP;
	public static String CustomizeToolbarDialog_REMOVE;
	public static String CustomizeToolbarDialog_ADD;
	public static String CustomizeToolbarDialog_VISIBLE_ITEMS;
	public static String CustomizeToolbarDialog_DIALOG_INFO;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
