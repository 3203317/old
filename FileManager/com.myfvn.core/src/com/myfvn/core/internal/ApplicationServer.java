package com.myfvn.core.internal;

import java.util.logging.Logger;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class ApplicationServer {

	final Logger _logger = Logger.getLogger(ApplicationServer.class.getName());

	public enum LoginMode {
		REMOTE, LOCAL
	}

	private static ApplicationServer _INSTANCE = new ApplicationServer();

	public static ApplicationServer getDefault() {
		return _INSTANCE;
	}

	private boolean _running = false;

	public boolean isRunning() {
		return this._running;
	}

	private void setRunning(boolean $running) {
		this._running = $running;
	}

	public void startup() {
		if (this.isRunning()) {
			return;
		}

		this.setRunning(true);
	}

	public void shutdown() {

	}

	private LoginMode _loginMode = LoginMode.REMOTE;

	public LoginMode getLoginMode() {
		return _loginMode;
	}

	public void setLoginMode(LoginMode $loginMode) {
		_loginMode = $loginMode;
	}
}
