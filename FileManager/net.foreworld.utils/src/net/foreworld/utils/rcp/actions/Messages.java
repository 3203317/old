package net.foreworld.utils.rcp.actions;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "net.foreworld.utils.rcp.actions.messages";//$NON-NLS-1$

	public static String BaseActionFactory_SHOW_KEY_ASSIST;
	
	
	private Messages() {}
	
	static{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
