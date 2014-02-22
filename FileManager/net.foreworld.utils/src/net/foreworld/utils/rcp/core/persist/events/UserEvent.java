package net.foreworld.utils.rcp.core.persist.events;

import net.foreworld.utils.rcp.core.persist.events.runnables.UserEventType;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class UserEvent extends AbstractModelEvent {

	private UserEventType _type;

	public void setUserEventType(UserEventType $type) {
		_type = $type;
	}

	public UserEventType getUserEventType() {
		return _type;
	}

	private String _user_uuid;

	public String getUser_uuid() {
		return _user_uuid;
	}

	public void setUser_uuid(String $user_uuid) {
		this._user_uuid = $user_uuid;
	}

}
