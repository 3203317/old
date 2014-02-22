package com.myfvn.core.persist.event;

import net.foreworld.utils.rcp.core.persist.events.IUserListener;
import net.foreworld.utils.rcp.core.persist.events.UserEvent;
import net.foreworld.utils.rcp.core.persist.events.runnables.UserEventRunnable;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class UserEventManager {

	private UserEventRunnable _runnable;

	private UserEventManager() {
		_runnable = new UserEventRunnable();
	}

	private static UserEventManager _INSTANCE = new UserEventManager();

	public static UserEventManager getDefault() {
		return _INSTANCE;
	}

	public void addUserEventListener(IUserListener $listener) {
		this._runnable.addListener($listener);
	}

	public void removeUserEventListener(IUserListener $listener) {
		this._runnable.removeListener($listener);
	}

	public void fireEvents(UserEvent $event) {
		this._runnable.setModelEvent($event);
		this._runnable.run();
	}

}
