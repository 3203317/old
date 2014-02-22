package com.myfvn.core.persist.service;

import net.foreworld.utils.rcp.core.persist.service.PersistenceException;
import net.foreworld.utils.rcp.core.utils.AbstractLongOperationMonitor;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public abstract class AbstractPersistenceService implements IPersistenceService {

	public void shutdown(boolean $emergency) throws PersistenceException {

	}

	public void startup(AbstractLongOperationMonitor $monitor,
			boolean $emergency, boolean $forRestore) {

	}

}
