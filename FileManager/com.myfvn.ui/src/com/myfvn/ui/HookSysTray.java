package com.myfvn.ui;

import java.util.Timer;
import java.util.TimerTask;

import net.foreworld.utils.rcp.CacheImage;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.IWorkbenchWindow;

import com.myfvn.ui.actions.FVNActionFactory;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class HookSysTray {
	private TrayItem _trayItem;

	public HookSysTray() {
		//
	}

	/**
	 * 
	 * @param $window
	 */
	public void createSysTray(final IWorkbenchWindow $window) {
		this._trayItem = initTrayItem($window);
		if (_trayItem != null) {
			trayPopupMenu($window);
			trayMinimize($window);
		}
	}

	/**
	 * 最小化程序窗口
	 * 
	 * @param $shell
	 */
	public void windowMinimized(final Shell $shell) {
		$shell.setMinimized(true);
		$shell.setVisible(false);
	}

	/**
	 * 最小化程序到托盘
	 * 
	 * @param $window
	 */
	private void trayMinimize(final IWorkbenchWindow $window) {
		$window.getShell().addShellListener(new ShellAdapter() {
			public void shellIconified(ShellEvent e) {
				$window.getShell().setVisible(false);
			}
		});
		_trayItem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				Shell ___shell = $window.getShell();
				if (!___shell.isVisible()) {
					___shell.setVisible(true);
					$window.getShell().setMinimized(false);
				}
			}
		});
	}

	/**
	 * 托盘弹出菜单
	 * 
	 * @param $window
	 */
	private void trayPopupMenu(final IWorkbenchWindow $window) {
		_trayItem.addListener(SWT.MenuDetect, new Listener() {
			public void handleEvent(Event $event) {
				MenuManager ___trayMenu = new MenuManager();
				Menu ___menu = ___trayMenu.createContextMenu($window.getShell());
				fillTrayItem(___trayMenu, $window);
				___menu.setVisible(true);
			}
		});
	}

	/**
	 * 构造托盘菜单项
	 * 
	 * @param $trayItem
	 * @param $window
	 */
	private void fillTrayItem(IMenuManager $trayItem, final IWorkbenchWindow $window) {
		$trayItem.add(FVNActionFactory.HELP_CONTENTS.create($window));
		$trayItem.add(new Separator());
		$trayItem.add(FVNActionFactory.QUIT.create($window));
	}

	/**
	 * 初始化托盘项目的文字和图标
	 * 
	 * @param $window
	 * @return
	 */
	private TrayItem initTrayItem(final IWorkbenchWindow $window) {
		final Tray __tray = $window.getShell().getDisplay().getSystemTray();
		if (__tray == null)
			return null;
		_trayItem = new TrayItem(__tray, SWT.NONE);
		_trayItem.setImage(CacheImage.getInstance().getImage(Activator.PLUGIN_ID, "icons/product/logo16.png"));//$NON-NLS-1$
		// 定时显示气泡提示文本
		Timer __timer = new Timer();
		__timer.schedule(new TimerTask() {
			public void run() {
				$window.getShell().getDisplay().syncExec(new Runnable() {
					public void run() {
						ToolTip ___tip = new ToolTip($window.getShell(), SWT.BALLOON | SWT.ICON_INFORMATION);
						___tip.setAutoHide(true);
						___tip.setMessage(Platform.getProduct().getName());
						___tip.setText(Messages.HookSysTray_TITLE);
						_trayItem.setToolTipText(___tip.getMessage());
						_trayItem.setToolTip(___tip);
						___tip.setVisible(true);
					}
				});
			}
		}, 0, 30 * 60 * 1000);
		return _trayItem;
	}

	/**
	 * 释放
	 */
	public void dispose() {
		if (_trayItem != null)
			_trayItem.dispose();
	}
}
