package com.myfvn.ui.dialogs.emailaccount;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.myfvn.ui.dialogs.emailaccount.messages";//$NON-NLS-1$

	public static String CreateEmailAccountWizard_NEW_EMAILACCOUNT;
	public static String CreateEmailAccountWizard_EMAILACCOUNT;
	public static String CreateEmailAccountWizard_EMAIL;
	public static String CreateEmailAccountWizard_EMAILSERVER;
	public static String CreateEmailAccountWizard_EMAILLOGIN;
	public static String CreateEmailAccountWizard_EMAILOK;
	
	public static String EmailNameDefinitionPage_CREATE_EMAILACCOUNT;
	
	public static String EmailDefinitionPage_CREATE_EMAILACCOUNT;
	
	public static String EmailLoginDefinitionPage_CREATE_EMAILACCOUNT;
	
	
	private Messages() {}
	
	static{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	
}
