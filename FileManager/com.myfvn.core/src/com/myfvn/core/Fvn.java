package com.myfvn.core;

import net.foreworld.utils.rcp.core.utils.AbstractLongOperationMonitor;

import org.eclipse.core.runtime.Assert;

import com.myfvn.core.internal.InternalFvn;
import com.myfvn.core.persist.service.IPreferenceService;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Fvn {

	public enum StartLevel {
		NOT_STARTED, STARTING, DB_OPENED, SEARCH_INDEX_OPENED, STARTED;
	}

	public static StartLevel getStartLevel() {
		return InternalFvn.getDefault().getStartLevel();
	}

	public static void startup(AbstractLongOperationMonitor $monitor) {
		if (!InternalFvn.getDefault().isStarted()) {
			InternalFvn.getDefault().startup($monitor, false, false);
		}
	}

	public static boolean isStarted() {
		return InternalFvn.getDefault().isStarted();
	}

	public static IPreferenceService getPreferenceService() {
		Assert.isTrue(InternalFvn.getDefault().isStarted(), "The Fvn facade has not yet finished initialization"); //$NON-NLS-1$
		return InternalFvn.getDefault().getPreferenceService();
	}

	public static void shutdown(boolean $emergency) {
		InternalFvn.getDefault().shutdown($emergency);
	}
}
