package com.myfvn.core.persist.service;

import net.foreworld.utils.rcp.core.persist.service.PersistenceException;
import net.foreworld.utils.rcp.core.utils.AbstractLongOperationMonitor;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IPersistenceService {

	/**
	 * 
	 * @param $monitor
	 * @param $emergency
	 * @param $forRestore
	 */
	void startup(AbstractLongOperationMonitor $monitor, boolean $emergency, boolean $forRestore);

	/**
	 * 
	 * @param $emergency
	 * @throws PersistenceException
	 */
	void shutdown(boolean $emergency) throws PersistenceException;
}
