package com.myfvn.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import net.foreworld.utils.rcp.core.utils.AbstractLoggingSafeRunnable;
import net.foreworld.utils.rcp.core.utils.AbstractLongOperationMonitor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.myfvn.core.Fvn;
import com.myfvn.core.internal.ApplicationServer;
import com.myfvn.ui.dialogs.StartupProgressDialog;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	final Logger _logger = Logger.getLogger(Activator.class.getName());

	// The plug-in ID
	public static final String PLUGIN_ID = "com.myfvn.ui";//$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private String _version;

	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
	}

	/**
	 * 
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);

		this._version = (String) plugin.getBundle().getHeaders().get("Bundle-Version");//$NON-NLS-1$

		/** Log Version Information */
		try {
			this._logger.info("FileManager Starting Up (" + this.getUserAgent() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception $ex) {
			/** Something seriously went wrong using the Platform Log */
		}

		SafeRunner.run(new ISafeRunnable() {
			public void run() throws Exception {
				startServer();
			}

			public void handleException(Throwable $ex) {
				if ($ex instanceof CoreException) {
					_logger.warning($ex.getMessage());
				}
			}
		});

		/** Activate the Core Bundle */
		SafeRunner.run(new AbstractLoggingSafeRunnable() {
			public void run() throws Exception {
				startCore();
			}
		});

		/** Propagate startup to Controller */
		SafeRunner.run(new AbstractLoggingSafeRunnable() {
			public void run() throws Exception {
				if (Fvn.isStarted()) {
					Controller.getDefault().startup();
				}
			}
		});

		/** Propagate post-ui startup to Controller (Eclipse Integration) */
		if (Application.IS_ECLIPSE) {
			SafeRunner.run(new AbstractLoggingSafeRunnable() {
				public void run() throws Exception {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							if (Fvn.isStarted()) {

							}
						}
					});
				}
			});
		}
	}

	public void stop(BundleContext context) throws Exception {
		/** Propagate shutdown to Controller */
		SafeRunner.run(new AbstractLoggingSafeRunnable() {
			public void run() throws Exception {
				if (Fvn.isStarted()) {
					Fvn.shutdown(true);
				}
			}
		});
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	private void startServer() {
		ApplicationServer.getDefault().startup();
	}

	private void startCore() {
		/** Dialog to show progress */
		Display.setAppName("FileManager");//$NON-NLS-1$
		Display.getDefault();
		final StartupProgressDialog __dialog = new StartupProgressDialog();
		__dialog.setOpenOnRun(false);

		/** Runnable to start core */
		IRunnableWithProgress __runnable = new IRunnableWithProgress() {

			public void run(IProgressMonitor $monitor) throws InvocationTargetException, InterruptedException {
				AbstractLongOperationMonitor ___callbackMonitor = new AbstractLongOperationMonitor($monitor) {
					private boolean ____updateUi = true;

					@Override
					public void beginLongOperation(boolean $isCancelable) {
						if (!isLongOperationRunning()) {
							super.beginLongOperation($isCancelable);
							__dialog.open();
						}
					}

					@Override
					public void worked(int $work) {
						super.worked($work);
						if (____updateUi)
							updateUi();
					}

					@Override
					public void subTask(String $name) {
						super.subTask($name);
						if (____updateUi)
							updateUi();
					}

					private void updateUi() {
						Display _____display = Display.getDefault();
						try {
							if (!isCanceled() && !_____display.isDisposed() && __dialog.getShell() != null && !__dialog.getShell().isDisposed()) {
								_____display.readAndDispatch();
								_____display.update();
							}
						}

						/*
						 * Ensure to catch any Exception here and disable the
						 * update of the UI given that the operation being
						 * performed can be a critical one.
						 */
						catch (Exception $ex) {
							____updateUi = false;
							_logger.warning($ex.getMessage());
						}
					}
				};

				/** Start Core */
				try {
					Fvn.startup(___callbackMonitor);
				} catch (Throwable $ex) {
					_logger.warning($ex.getMessage());
				}
			}
		};

		/** Execute the Runnable */
		try {
			__dialog.run(false, true, __runnable);
		} catch (InvocationTargetException $ex) {
			this._logger.warning($ex.getMessage());
		} catch (InterruptedException $ex) {
			this._logger.warning($ex.getMessage());
		}

	}

	private String getUserAgent() {
		String __os = Platform.getOS();
		if (Platform.OS_WIN32.equals(__os))
			return "FileManager/" + this._version + " (Windows; U; en)"; //$NON-NLS-1$ //$NON-NLS-2$
		else if (Platform.OS_LINUX.equals(__os))
			return "FileManager/" + this._version + " (X11; U; en)"; //$NON-NLS-1$//$NON-NLS-2$
		else if (Platform.OS_MACOSX.equals(__os))
			return "FileManager/" + this._version + " (Macintosh; U; en)"; //$NON-NLS-1$ //$NON-NLS-2$
		return "FileManager/" + this._version; //$NON-NLS-1$
	}
}
