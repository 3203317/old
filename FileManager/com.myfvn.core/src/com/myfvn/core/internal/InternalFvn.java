package com.myfvn.core.internal;

import java.util.logging.Logger;

import net.foreworld.utils.rcp.core.utils.AbstractLongOperationMonitor;

import com.myfvn.core.Fvn.StartLevel;
import com.myfvn.core.internal.persist.service.PreferenceServiceImpl;
import com.myfvn.core.persist.service.IPersistenceService;
import com.myfvn.core.persist.service.IPreferenceService;
import com.myfvn.core.utils.ExtensionUtils;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class InternalFvn {

	final Logger _logger = Logger.getLogger(InternalFvn.class.getName());

	private static final InternalFvn _INSTANCE = new InternalFvn();

	private volatile boolean _shuttingDown;

	private volatile IPersistenceService _persistenceService;
	private volatile IPreferenceService _preferencesService;

	private volatile StartLevel _startLevel = StartLevel.NOT_STARTED;

	private volatile boolean _started;

	/** Extension Point: Persistence Service */
	private static final String PERSISTENCE_SERVICE_EXTENSION_POINT = "com.myfvn.core.PersistenceService"; //$NON-NLS-1$

	public static InternalFvn getDefault() {
		return _INSTANCE;
	}

	public boolean isStarted() {
		return _started;
	}

	private InternalFvn() {
	}

	public void startup(AbstractLongOperationMonitor $monitor, boolean $emergency, boolean $forRestore) {
		/* Increment Start Level */
		if (_startLevel == StartLevel.NOT_STARTED) {
			_startLevel = StartLevel.STARTING;
		}

		/* Create Persistence Service */
		if (this._persistenceService == null) {
			this._persistenceService = this.loadPersistenceService();
		}

		/* Persistence Layer has its own startup routine */
		this._persistenceService.startup($monitor, $emergency, $forRestore);

		/* Create Preferences Service */
		if (_preferencesService == null)
			_preferencesService = new PreferenceServiceImpl();

		/* Flag as started */
		_started = true;
		_startLevel = StartLevel.STARTED;
	}

	public void setStartLevel(StartLevel $level) {
		if ($level.ordinal() > _startLevel.ordinal())
			_startLevel = $level;
	}

	public StartLevel getStartLevel() {
		return _startLevel;
	}

	public void shutdown(boolean $emergency) {
		_shuttingDown = true;

		if (!$emergency) {

		}

		if (_persistenceService != null) {
			_persistenceService.shutdown($emergency);
		}

		_startLevel = StartLevel.NOT_STARTED;
	}

	public boolean isShuttingDown() {
		return _shuttingDown;
	}

	/**
	 * Load the contributed persistence service
	 * 
	 * @return
	 */
	private IPersistenceService loadPersistenceService() {
		return (IPersistenceService) ExtensionUtils.loadSingletonExecutableExtension(PERSISTENCE_SERVICE_EXTENSION_POINT);
	}

	public IPersistenceService getPersistenceService() {
		return _persistenceService;
	}

	public IPreferenceService getPreferenceService() {
		return _preferencesService;
	}

}
