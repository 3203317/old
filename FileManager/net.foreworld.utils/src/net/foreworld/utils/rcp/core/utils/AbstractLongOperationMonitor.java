package net.foreworld.utils.rcp.core.utils;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public abstract class AbstractLongOperationMonitor implements IProgressMonitor {
	private final IProgressMonitor _monitor;
	private boolean _isLongOperationRunning;

	/**
	 * @param $monitor
	 *            The progress monitor to wrap around.
	 */
	public AbstractLongOperationMonitor(IProgressMonitor $monitor) {
		_monitor = $monitor;
	}

	/**
	 * Indicates that a long operation is about to start. Implementors can then
	 * decide to show a progress dialog for instance.
	 * 
	 * @param $isCancelable
	 *            set to <code>true</code> in case the operation can be
	 *            cancelled and <code>false</code> otherwise.
	 */
	public void beginLongOperation(boolean $isCancelable) {
		_isLongOperationRunning = true;
	}

	/**
	 * @return <code>true</code> if
	 *         {@link LongOperationMonitor#beginLongOperation(boolean)} has been
	 *         called and <code>false</code> otherwise.
	 */
	public boolean isLongOperationRunning() {
		return _isLongOperationRunning;
	}

	/*
	 * @see org.eclipse.core.runtime.IProgressMonitor#beginTask(java.lang.String,
	 *      int)
	 */
	public void beginTask(String $name, int $totalWork) {
		_monitor.beginTask($name, $totalWork);
	}

	/*
	 * @see org.eclipse.core.runtime.IProgressMonitor#done()
	 */
	public void done() {
		_monitor.done();
	}

	/*
	 * @see org.eclipse.core.runtime.IProgressMonitor#internalWorked(double)
	 */
	public void internalWorked(double $work) {
		_monitor.internalWorked($work);
	}

	/*
	 * @see org.eclipse.core.runtime.IProgressMonitor#isCanceled()
	 */
	public boolean isCanceled() {
		return _monitor.isCanceled();
	}

	/*
	 * @see org.eclipse.core.runtime.IProgressMonitor#setCanceled(boolean)
	 */
	public void setCanceled(boolean $value) {
		_monitor.setCanceled($value);
	}

	/*
	 * @see org.eclipse.core.runtime.IProgressMonitor#setTaskName(java.lang.String)
	 */
	public void setTaskName(String $name) {
		_monitor.setTaskName($name);
	}

	/*
	 * @see org.eclipse.core.runtime.IProgressMonitor#subTask(java.lang.String)
	 */
	public void subTask(String $name) {
		_monitor.subTask($name);
	}

	/*
	 * @see org.eclipse.core.runtime.IProgressMonitor#worked(int)
	 */
	public void worked(int $work) {
		_monitor.worked($work);
	}
}
