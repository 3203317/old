package net.foreworld.utils.rcp.core.persist.events;

import java.util.EventListener;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IEntityListener extends EventListener {

	/**
	 * 
	 * @param $event
	 */
	public void entityChanged(EntityChangedEvent $event);
}
