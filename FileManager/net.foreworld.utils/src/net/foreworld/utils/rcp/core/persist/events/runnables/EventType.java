package net.foreworld.utils.rcp.core.persist.events.runnables;

/**
 * 
 * @author huangxin
 * @email huangxin@foreworld.net
 * 
 */
public enum EventType {

	/**
	 * Event type fired when an item is added to persistence layer for the first
	 * time.
	 */
	PERSIST,

	/**
	 * Event type fired when an item is removed from the persistence layer.
	 */
	REMOVE,

	/**
	 * Event type fired when an item is updated in the persistence layer.
	 */
	UPDATE
}
