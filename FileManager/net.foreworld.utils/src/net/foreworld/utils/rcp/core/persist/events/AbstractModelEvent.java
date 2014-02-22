package net.foreworld.utils.rcp.core.persist.events;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public abstract class AbstractModelEvent {

	private String _message;

	public String getMessage() {
		return this._message;
	}

	public void setMessage(String $message) {
		this._message = $message;
	}

}
