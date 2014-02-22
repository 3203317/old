package net.foreworld.utils.rcp;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.progress.UIJob;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * @allc true
 * 
 */
public class JobRunner {

	/* Copied from BusyIndicator */
	private static int _nextBusyId = 1;
	private static final String BUSYID_NAME = "SWT BusyIndicator"; //$NON-NLS-1$

	/* Default Delay */
	private static final int DELAY = 800;

	/**
	 * @param $runnable
	 * @param $widget
	 */
	public static void runInUIThread(final Widget $widget, final Runnable $runnable) {
		runInUIThread(0, $widget, $runnable);
	}

	/**
	 * @param $runnable
	 * @param $widget
	 */
	public static void runDelayedInUIThread(final Widget $widget, final Runnable $runnable) {
		runInUIThread(DELAY, $widget, $runnable);
	}

	/**
	 * @param $delay
	 * @param $runnable
	 * @param $widget
	 */
	public static void runInUIThread(int $delay, final Widget $widget, final Runnable $runnable) {
		runInUIThread($delay, false, $widget, $runnable);
	}

	/**
	 * @param $delay
	 * @param $forceAsync
	 * @param $runnable
	 * @param $widget
	 */
	public static void runInUIThread(int $delay, boolean $forceAsync, final Widget $widget, final Runnable $runnable) {
		Assert.isNotNull($runnable);

		/* Run directly if already in UI Thread */
		if (!$forceAsync && $delay == 0 && ($widget == null || !$widget.isDisposed()) && Display.getCurrent() != null)
			$runnable.run();

		/* Otherwise use UI Job */
		else {
			UIJob ___uiJob = new UIJob("") { //$NON-NLS-1$
				@Override
				public IStatus runInUIThread(IProgressMonitor $monitor) {
					if ($widget == null || !$widget.isDisposed())
						$runnable.run();
					return Status.OK_STATUS;
				}
			};

			___uiJob.setSystem(true);
			___uiJob.setUser(false);
			___uiJob.schedule($delay);
		}
	}

	/**
	 * @param $runnable
	 * @param $widget
	 */
	public static void runSyncedInUIThread(final Widget $widget, final Runnable $runnable) {
		Assert.isNotNull($runnable);
		Assert.isNotNull($widget);

		if (!$widget.isDisposed()) {
			$widget.getDisplay().syncExec(new Runnable() {
				public void run() {
					if (!$widget.isDisposed())
						$runnable.run();
				}
			});
		}
	}

	/**
	 * @param $runnable
	 */
	public static void runSyncedInUIThread(final Runnable $runnable) {
		Assert.isNotNull($runnable);

		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				$runnable.run();
			}
		});
	}

	/**
	 * @param $runnable
	 */
	public static void runInBackgroundThread(final Runnable $runnable) {
		runInBackgroundThread(0, $runnable);
	}

	/**
	 * @param $runnable
	 */
	public static void runDelayedInBackgroundThread(final Runnable $runnable) {
		runInBackgroundThread(DELAY, $runnable);
	}

	/**
	 * @param $delay
	 * @param $runnable
	 */
	public static void runInBackgroundThread(int $delay, final Runnable $runnable) {
		Assert.isNotNull($runnable);
		Job __job = new Job("") { //$NON-NLS-1$
			@Override
			public IStatus run(IProgressMonitor $monitor) {
				$runnable.run();
				return Status.OK_STATUS;
			}
		};

		__job.setSystem(true);
		__job.setUser(false);
		__job.schedule($delay);
	}

	/**
	 * @param $job
	 */
	public static void runUIUpdater(UIBackgroundJob $job) {
		runUIUpdater($job, false);
	}

	/**
	 * @param $job
	 * @param $showProgress
	 *            if <code>true</code> will show progress from the background
	 *            operation to the user.
	 */
	public static void runUIUpdater(UIBackgroundJob $job, boolean $showProgress) {
		Assert.isNotNull($job);
		if (!$showProgress) {
			$job.setSystem(true);
			$job.setUser(false);
		}
		$job.schedule();
	}

	/**
	 * @param $runnable
	 */
	public static void runInBackgroundWithBusyIndicator(final Runnable $runnable) {
		final Display __display = Display.getCurrent();
		final Integer __busyId = Integer.valueOf(_nextBusyId++);

		/* Guard against Illegal-Thread-Access */
		if (__display == null)
			throw new IllegalStateException("Method was not called from the UI-Thread!"); //$NON-NLS-1$

		/* Set the Cursor */
		Cursor __cursor = __display.getSystemCursor(SWT.CURSOR_APPSTARTING);
		Shell[] __shells = __display.getShells();
		for (Shell ___shell : __shells) {
			Integer ____id = (Integer) ___shell.getData(BUSYID_NAME);
			if (____id == null) {
				___shell.setCursor(__cursor);
				___shell.setData(BUSYID_NAME, __busyId);
			}
		}

		/* Run the Runnable and update cursor afterwards */
		runUIUpdater(new UIBackgroundJob(null) {

			@Override
			protected void runInBackground(IProgressMonitor $monitor) {
				$runnable.run();
			}

			@Override
			protected void runInUI(IProgressMonitor $monitor) {
				if (!__display.isDisposed()) {
					Shell[] ___shells = __display.getShells();
					for (Shell ____shell : ___shells) {
						Integer _____id = (Integer) ____shell.getData(BUSYID_NAME);
						if (__busyId.equals(_____id)) {
							____shell.setCursor(null);
							____shell.setData(BUSYID_NAME, null);
						}
					}
				}
			}
		});
	}

	/**
	 * @param $delay
	 * @param $flag
	 */
	public static void runDelayedFlagInversion(int $delay, final AtomicBoolean $flag) {
		$flag.set(!$flag.get());
		runInBackgroundThread($delay, new Runnable() {
			public void run() {
				$flag.set(!$flag.get());
			}
		});
	}
}
