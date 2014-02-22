package com.myfvn.ui;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class ApplicationContext {

	private ApplicationContext() {
	}

	private static ApplicationContext _CONTEXT = new ApplicationContext();

	public static ApplicationContext getDefault() {
		return _CONTEXT;
	}

	private String _user_uuid;

	public void setUser_uuid(String $user_uuid) {
		this._user_uuid = $user_uuid;
	}

	public String getUser_uuid() {
		return this._user_uuid;
	}

}
