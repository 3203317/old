package net.foreworld.rss2;

import java.util.Timer;
import java.util.TimerTask;

import net.foreworld.rss2.actions.RSS2ActionFactory;

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

/**
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 */
public class HookSysTray {
	private TrayItem trayItem;

	public HookSysTray() {
		//
	}

	/**
	 * 
	 * @param window
	 */
	public void createSysTray(final IWorkbenchWindow window) {
		this.trayItem = initTrayItem(window);
		if(trayItem != null) {
			trayPopupMenu(window);
			trayMinimize(window);
		}
	}

	/**
	 * 最小化程序窗口
	 * @param shell
	 */
	public void windowMinimized(final Shell shell) {
		shell.setMinimized(true);
		shell.setVisible(false);
	}

	/**
	 * 最小化程序到托盘
	 * @param window
	 */
	private void trayMinimize(final IWorkbenchWindow window) {
		window.getShell().addShellListener(new ShellAdapter() {
			public void shellIconified(ShellEvent e) {
				window.getShell().setVisible(false);
			}
		});
		trayItem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				Shell shell = window.getShell();
				if (!shell.isVisible()) {
					shell.setVisible(true);
					window.getShell().setMinimized(false);
				}
			}
		});
	}

	/**
	 * 托盘弹出菜单
	 * @param window
	 */
	private void trayPopupMenu(final IWorkbenchWindow window) {
		trayItem.addListener(SWT.MenuDetect, new Listener() {
			public void handleEvent(Event event) {
				MenuManager trayMenu = new MenuManager();
				Menu menu = trayMenu.createContextMenu(window.getShell());
				fillTrayItem(trayMenu, window);
				menu.setVisible(true);
			}
		});
	}

	/**
	 * 构造托盘菜单项
	 * @param trayItem
	 * @param window
	 */
	private void fillTrayItem(IMenuManager trayItem,final IWorkbenchWindow window) {
		trayItem.add(RSS2ActionFactory.HELP_CONTENTS.create(window));
		trayItem.add(new Separator());
		trayItem.add(RSS2ActionFactory.QUIT.create(window));
	}

	/**
	 * 初始化托盘项目的文字和图标
	 * @param window
	 * @return
	 */
	private TrayItem initTrayItem(final IWorkbenchWindow window) {
		final Tray tray = window.getShell().getDisplay().getSystemTray();
		if (tray == null)
			return null;
		trayItem = new TrayItem(tray, SWT.NONE);
		trayItem.setImage(Activator.getImageDescriptor("icons/ieframe_31064.gif").createImage());
		// 定时显示气泡提示文本		
		Timer timer = new Timer();		
		timer.schedule(new TimerTask() {
			public void run() {
				window.getShell().getDisplay().syncExec(new Runnable() {
					public void run() {
						ToolTip tip = new ToolTip(window.getShell(),SWT.BALLOON | SWT.ICON_INFORMATION);
						tip.setAutoHide(true);
						tip.setMessage(Platform.getProduct().getName());
						tip.setText("欢迎使用");
						trayItem.setToolTipText(tip.getMessage());
						trayItem.setToolTip(tip);
						tip.setVisible(true);
					}
				});
			}
		}, 0, 30 * 60 * 1000);
		return trayItem;
	}

	/**
	 * 释放
	 */
	public void dispose() {
		if(trayItem != null)
			trayItem.dispose();
	}
}
