package com.myfvn.core.internal.persist.dao;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.myfvn.core.internal.persist.dao.messages";//$NON-NLS-1$

	public static String UserDAOImpl_USER_LOGIN_FAIL;
	public static String UserDAOImpl_USER_LOGIN_FAIL_NETWORK_ERROR;

	private Messages() {
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
