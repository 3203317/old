package com.myfvn.core.internal.persist.service;

import java.util.logging.Logger;

import net.foreworld.utils.rcp.core.persist.service.PersistenceException;
import net.foreworld.utils.rcp.core.utils.AbstractLongOperationMonitor;

import com.myfvn.core.Fvn.StartLevel;
import com.myfvn.core.internal.InternalFvn;
import com.myfvn.core.persist.service.AbstractPersistenceService;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class PersistenceServiceImpl extends AbstractPersistenceService {
	final Logger _logger = Logger.getLogger(PersistenceServiceImpl.class.getName());

	public void startup(AbstractLongOperationMonitor $monitor, boolean $emergency, boolean $forRestore) {
		super.startup($monitor, $emergency, $forRestore);

		/** Startup DB */
		DBManager.getDefault().startup($monitor, $emergency, $forRestore);
		InternalFvn.getDefault().setStartLevel(StartLevel.DB_OPENED);

		/** Startup Model Search (not in case of emergency) */
		if ($emergency) {
			InternalFvn.getDefault().setStartLevel(StartLevel.SEARCH_INDEX_OPENED);
		}
	}

	public void shutdown(boolean $emergency) throws PersistenceException {
		try {
			DBManager.getDefault().shutdown();
		} catch (Exception $ex) {
			_logger.warning($ex.getMessage());
		}
	}
}
