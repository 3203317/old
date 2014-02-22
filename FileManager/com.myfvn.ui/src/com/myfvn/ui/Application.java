package com.myfvn.ui;

import java.io.IOException;

import net.foreworld.utils.CustomApplication;
import net.foreworld.utils.rcp.core.persist.IUser;
import net.foreworld.utils.rcp.core.persist.IWebSite;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.myfvn.core.persist.reference.WebSiteReference;
import com.myfvn.ui.dialogs.LoginDialog;

/**
 * This class controls all aspects of the application's execution
 * 
 * @author huangxin (huangxin@foreworld.net)
 */
public class Application extends CustomApplication {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	public Object start(IApplicationContext $context) {
		Display _display_2 = PlatformUI.createDisplay();

		if (this.isAreadyRunning()) {
			Platform.endSplash();
			return IApplication.EXIT_OK;
		}

		Shell _shell_2 = Display.getCurrent().getActiveShell();

		if (this.autoLogin(_display_2)) {
			try {
				if (!this.isLogin(_shell_2)) {
					Platform.endSplash();
					return IApplication.EXIT_OK;
				}
			} finally {
				if (_shell_2 != null)
					_shell_2.dispose();
			}
		}

		try {
			int _returnCode_3 = PlatformUI.createAndRunWorkbench(_display_2, new ApplicationWorkbenchAdvisor());
			if (_returnCode_3 == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		} finally {
			_display_2.dispose();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		final IWorkbench _workbench_2 = PlatformUI.getWorkbench();
		if (_workbench_2 == null)
			return;
		final Display _display_2 = _workbench_2.getDisplay();
		_display_2.syncExec(new Runnable() {
			public void run() {
				if (!_display_2.isDisposed())
					_workbench_2.close();
			}
		});
	}

	private boolean isLogin(Shell $shell) {
		LoginDialog _dialog_2 = new LoginDialog($shell);
		return _dialog_2.open() == Dialog.OK;
	}

	private boolean autoLogin(Display $display) {
		IWebSite _webSite_2 = new WebSiteReference().resolve();
		final IUser _user_2 = _webSite_2.getLastLoginUser();

		if (_user_2 != null && _user_2.getIsrememberpassword() == 1 && _user_2.getIsautologin() == 1) {
			/* auto login */
			BusyIndicator.showWhile($display, new Runnable() {
				public void run() {
					ApplicationContext.getDefault().setUser_uuid(_user_2.getUuid());
					_user_2.login();
				}
			});
			return false;
		}
		return true;
	}

	private boolean isAreadyRunning() {
		boolean _locked_2 = true;
		Location _instanceLoc_2 = Platform.getInstanceLocation();
		if (!_instanceLoc_2.isSet()) {
			try {
				_instanceLoc_2.set(_instanceLoc_2.getDefault(), false);
			} catch (IllegalStateException $ex) {
				$ex.printStackTrace();
			} catch (IOException $ex) {
				$ex.printStackTrace();
			}
		}
		try {
			if (_instanceLoc_2.lock()) {
				_locked_2 = false;
			} else {
				_locked_2 = true;
				// MessageDialog.openError(null,"错误提示","程序已经运行");
			}
		} catch (IOException $ex) {
			$ex.printStackTrace();
		}
		return _locked_2;
	}
}
