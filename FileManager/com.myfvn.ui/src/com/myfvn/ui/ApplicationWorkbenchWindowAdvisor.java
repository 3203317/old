package com.myfvn.ui;

import java.util.logging.Logger;

import net.foreworld.utils.rcp.CacheImage;

import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	final static Logger _logger = Logger.getLogger(ApplicationWorkbenchWindowAdvisor.class.getName());

	/** Key for Data-Slot in Controls that support this Hook */
	public static final String FOCUSLESS_SCROLL_HOOK = "com.myfvn.ui.FocuslessScrollHook"; //$NON-NLS-1$

	private HookSysTray _hookSysTray;

	private void createSystemTray() {
		this._hookSysTray = new HookSysTray();
		this._hookSysTray.createSysTray(getWindowConfigurer().getWindow());
	}

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer $configurer) {
		super($configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer $configurer) {
		return new ApplicationActionBarAdvisor($configurer);
	}

	public void preWindowOpen() {
		super.preWindowOpen();

		IWorkbenchWindowConfigurer __configurer = this.getWindowConfigurer();

		__configurer.setInitialSize(new Point(600, 400));
		__configurer.setShowCoolBar(true);
		__configurer.setShowFastViewBars(true);
		__configurer.setShowStatusLine(true);
		__configurer.setShowPerspectiveBar(true);
		__configurer.setShowProgressIndicator(true);
		__configurer.setShowMenuBar(true);

		/* Set Window Size to match monitor size (only on single monitor) */
		Point __size = FvnUI.getFirstMonitorSize();
		if (__size != null)
			__configurer.setInitialSize(__size);

		/* Apply DND Support for Editor Area */
		__configurer.addEditorAreaTransfer(LocalSelectionTransfer.getTransfer());

		PreferenceManager __preferenceManager = PlatformUI.getWorkbench().getPreferenceManager();

		__preferenceManager.remove("org.eclipse.help.ui.browsersPreferencePage");//$NON-NLS-1$
		__preferenceManager.remove("org.eclipse.update.internal.ui.preferences.MainPreferencePage");//$NON-NLS-1$
	}

	public void postWindowOpen() {
		super.postWindowOpen();
		this.createSystemTray();

		PreferenceManager __preferenceManager = PlatformUI.getWorkbench().getPreferenceManager();

		Shell __shell = this.getWindowConfigurer().getWindow().getShell();

		__shell.setMaximized(true);

		PlatformUI.getWorkbench().getDisplay().addFilter(SWT.MouseUp, new Listener() {
			public void handleEvent(Event $evt) {
				if ($evt.button == 3) {
					// int ___hwndCursor = OS.GetCapture();
					// OS.PostMessage(___hwndCursor, OS.WM_LBUTTONDOWN,
					// ___hwndCursor, OS.HTCLIENT | (OS.WM_MOUSEMOVE <<
					// 16));
				}
			}
		});
	}

	public boolean preWindowShellClose() {
		this._hookSysTray.windowMinimized(this.getWindowConfigurer().getWindow().getShell());
		return false;
	}

	public void dispose() {
		this._hookSysTray.dispose();
		CacheImage.getInstance().dispose();
		super.dispose();
	}

	void setToolBarVisible(boolean $visible, boolean $layout) {
		getWindowConfigurer().setShowCoolBar($visible);
		if ($layout)
			getWindowConfigurer().getWindow().getShell().layout();
	}

	public void setStatusVisible(boolean $visible, boolean $layout) {
		getWindowConfigurer().setShowStatusLine($visible);
		getWindowConfigurer().setShowProgressIndicator($visible);

		/* Hack: To avoid cheese, update ToolBar Too */
		boolean __showsToolBar = getWindowConfigurer().getShowCoolBar();
		getWindowConfigurer().setShowCoolBar(!__showsToolBar);
		getWindowConfigurer().setShowCoolBar(__showsToolBar);

		if ($layout)
			getWindowConfigurer().getWindow().getShell().layout();
	}

}
