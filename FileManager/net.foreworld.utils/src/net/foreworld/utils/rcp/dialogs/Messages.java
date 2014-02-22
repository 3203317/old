package net.foreworld.utils.rcp.dialogs;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "net.foreworld.utils.rcp.dialogs.messages";//$NON-NLS-1$
	

	public static String CustomWizardDialog_CANCEL_TEXT;
	public static String CustomWizardDialog_OK_TEXT;
	public static String CustomWizardDialog_FINISH_TEXT;
	public static String CustomWizardDialog_BACK_TEXT;
	public static String CustomWizardDialog_NEXT_TEXT;

	public static String CustomTitleAreaDialog_CANCEL_TEXT;
	public static String CustomTitleAreaDialog_OK_TEXT;
	
	
	
	static{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
}
