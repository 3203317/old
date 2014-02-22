package net.foreworld.utils.rcp.core.internal.persist.service;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IDatabaseListener {

	/**
	 * 
	 * @param $event
	 */
	public void databaseOpened(DatabaseEvent $event);

	/**
	 * 
	 * @param $event
	 */
	public void databaseClosed(DatabaseEvent $event);
}
