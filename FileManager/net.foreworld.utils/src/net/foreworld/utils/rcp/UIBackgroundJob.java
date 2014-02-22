package net.foreworld.utils.rcp;

import net.foreworld.utils.rcp.core.utils.AbstractLoggingSafeRunnable;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.progress.UIJob;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * @allc true
 * 
 */
public abstract class UIBackgroundJob extends Job {
	private static final String _NAME = "UI-Updater"; //$NON-NLS-1$
	private final Control _control;
	private final Object _family;

	/**
	 * Creates a new instance of this kind. Use it like any other instance of
	 * <code>Job</code>. Asks the given Control for its disposed state as
	 * soon as the UI-Job is running, in order to cancel the Job when the
	 * Control is disposed.
	 * 
	 * @param $control
	 *            Used to cancel the UI-Job in case the given Control is
	 *            disposed at that time.
	 */
	public UIBackgroundJob(Control $control) {
		this($control, _NAME);
	}

	/**
	 * Creates a new instance of this kind. Use it like any other instance of
	 * <code>Job</code>. Asks the given Control for its disposed state as
	 * soon as the UI-Job is running, in order to cancel the Job when the
	 * Control is disposed.
	 * 
	 * @param $control
	 *            Used to cancel the UI-Job in case the given Control is
	 *            disposed at that time.
	 * @param $name
	 *            the name of the {@link Job} or <code>null</code> if none.
	 */
	public UIBackgroundJob(Control $control, String $name) {
		this($control, $name, null);
	}

	/**
	 * Creates a new instance of this kind. Use it like any other instance of
	 * <code>Job</code>. Asks the given Control for its disposed state as
	 * soon as the UI-Job is running, in order to cancel the Job when the
	 * Control is disposed.
	 * 
	 * @param $control
	 *            Used to cancel the UI-Job in case the given Control is
	 *            disposed at that time.
	 * @param $name
	 *            the name of the {@link Job} or <code>null</code> if none.
	 * @param $family
	 *            the family this {@link Job} belongs to or <code>null</code>
	 *            if none.
	 */
	public UIBackgroundJob(Control $control, String $name, Object $family) {
		super($name);
		_control = $control;
		_family = $family;
	}

	/**
	 * The task that is to run in the Background-Thread.
	 * 
	 * @param $monitor
	 *            Progress-Monitor of the Job performing this task.
	 */
	protected abstract void runInBackground(IProgressMonitor $monitor);

	/**
	 * The task that is to run in the UI-Thread.
	 * 
	 * @param $monitor
	 *            Progress-Monitor of the Job performing this task.
	 */
	protected abstract void runInUI(IProgressMonitor $monitor);

	/*
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus run(final IProgressMonitor $monitor) {

		/* If Control is provided, check disposed State */
		if (_control != null && _control.isDisposed())
			return Status.OK_STATUS;

		/* Only run if not canceld yet */
		if (!$monitor.isCanceled())
			synchronizedSafeRunInBackground($monitor);

		/* Schdule UIJob now */
		UIJob __uiJob = new UIJob(_NAME) {
			@Override
			public IStatus runInUIThread(IProgressMonitor $monitor) {

				/* If Control is provided, check disposed State */
				if (_control != null && _control.isDisposed())
					return Status.OK_STATUS;

				/* Run UI-Task */
				synchronizedSafeRunInUI($monitor);

				return Status.OK_STATUS;
			}

			/*
			 * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
			 */
			@Override
			public boolean belongsTo(Object $family) {
				if (_family == null)
					return super.belongsTo($family);

				return _family.equals($family);
			}
		};

		__uiJob.setSystem(true);
		__uiJob.setUser(false);

		/* Only run if not canceld yet */
		if (!$monitor.isCanceled())
			__uiJob.schedule();

		$monitor.done();

		return Status.OK_STATUS;
	}

	/*
	 * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
	 */
	@Override
	public boolean belongsTo(Object $family) {
		if (_family == null)
			return super.belongsTo($family);

		return _family.equals($family);
	}

	private synchronized void synchronizedSafeRunInBackground(final IProgressMonitor $monitor) {
		SafeRunner.run(new AbstractLoggingSafeRunnable() {
			public void run() throws Exception {
				runInBackground($monitor);
			}
		});
	}

	private synchronized void synchronizedSafeRunInUI(final IProgressMonitor $monitor) {
		SafeRunner.run(new AbstractLoggingSafeRunnable() {
			public void run() throws Exception {
				runInUI($monitor);
			}
		});
	}
}
