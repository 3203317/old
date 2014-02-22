package net.foreworld.utils.rcp.core.utils;

import java.util.logging.Logger;

import org.eclipse.core.runtime.ISafeRunnable;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public abstract class AbstractLoggingSafeRunnable implements ISafeRunnable {

	final Logger _logger = Logger.getLogger(AbstractLoggingSafeRunnable.class
			.getName());

	/**
	 * 
	 */
	public void handleException(Throwable $ex) {
		this._logger.warning($ex.getMessage());
	}
}
