package net.foreworld.utils.rcp.core.persist.events;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IUserListener extends IEntityListener {

	/**
	 * 
	 * @param $event
	 */
	void loginSuccess(UserEvent $event);

	/**
	 * 
	 * @param $event
	 */
	void loginFail(UserEvent $event);

	/**
	 * 
	 * @param $event
	 */
	void loginFail_Network_Error(UserEvent $event);

	/**
	 * 
	 * @param $event
	 */
	void loginFail_Network(UserEvent $event);
}
