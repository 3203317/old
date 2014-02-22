package com.myfvn.core.internal.persist.service;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.myfvn.core.internal.persist.service.messages";//$NON-NLS-1$

	public static String DBManager_SQLMAP_RESOURCE_IS_NOT_EXISTS;

	private Messages() {
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
