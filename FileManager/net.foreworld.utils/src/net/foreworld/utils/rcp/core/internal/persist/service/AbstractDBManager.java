package net.foreworld.utils.rcp.core.internal.persist.service;

import net.foreworld.utils.rcp.core.persist.service.PersistenceException;
import net.foreworld.utils.rcp.core.utils.AbstractLongOperationMonitor;

/**
 * 
 * @author huangxin
 * @email huangxin@foreworld.net
 * 
 */
public abstract class AbstractDBManager {

	/**
	 * 
	 * @return
	 */
	public static AbstractDBManager getDefault() {
		return null;
	}

	/**
	 * 
	 * @param $listener
	 */
	public abstract void addEntityStoreListener(IDatabaseListener $listener);

	/**
	 * 
	 * @param $monitor
	 * @param $emergency
	 * @param $forrestore
	 * @throws PersistenceException
	 */
	public abstract void startup(AbstractLongOperationMonitor $monitor,
			boolean $emergency, boolean $forrestore)
			throws PersistenceException;

	/**
	 * 
	 * @param $monitor
	 * @param $emergency
	 * @param $forrestore
	 * @throws PersistenceException
	 */
	public abstract void createDB(AbstractLongOperationMonitor $monitor,
			boolean $emergency, boolean $forrestore)
			throws PersistenceException;

	/**
	 * 
	 * @throws PersistenceException
	 */
	public abstract void shutdown() throws PersistenceException;

}
