package net.foreworld.utils.rcp.core.persist.events.runnables;

import net.foreworld.utils.rcp.core.persist.events.IUserListener;
import net.foreworld.utils.rcp.core.persist.events.UserEvent;
import net.foreworld.utils.rcp.core.utils.AbstractLoggingSafeRunnable;

import org.eclipse.core.runtime.SafeRunner;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class UserEventRunnable extends AbstractEventRunnable<IUserListener, UserEvent> {

	public void run() {
		final UserEvent __event = (UserEvent) this.getModelEvent();

		for (final IUserListener __listener : this._listeners) {
			SafeRunner.run(new AbstractLoggingSafeRunnable() {
				public void run() throws Exception {
					switch (__event.getUserEventType()) {
					case LOGIN_SUCCESS:
						__listener.loginSuccess(__event);
						break;
					case LOGIN_FAIL:
						__listener.loginFail(__event);
						break;
					case LOGIN_FAIL_NETWORK_ERROR:
						__listener.loginFail_Network_Error(__event);
						break;
					case LOGIN_FAIL_NETWORK:
						__listener.loginFail_Network(__event);
						break;
					}
				}
			});
		}
	}
}
