package net.foreworld.rss2.utils;

import java.io.IOException;

import net.foreworld.rss2.Activator;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 11, 2008 9:02:14 AM
 */
public class MyRcpUtil {
	/**
	 * 改变状态栏提示
	 * @param value
	 */
	public static final void setStatusLine(String value){
		WorkbenchWindow workbenchWindow = (WorkbenchWindow)PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IActionBars actionBars = workbenchWindow.getActionBars();
    	IStatusLineManager statusLine = actionBars.getStatusLineManager();
    	statusLine.setMessage(value);
	}
	
	/**
	 * 锁定工具栏
	 * @param value
	 */
	public static final void lockCoolBar(boolean value){
		WorkbenchWindow workbenchWindow = (WorkbenchWindow)PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    	workbenchWindow.getCoolBarManager().setLockLayout(value);
	}
	
	/**
	 * 显示工具栏，这样做不起作用还有待研究
	 * @param value
	 */
	public static final void showCoolBar(boolean value){
		WorkbenchWindow workbenchWindow = (WorkbenchWindow)PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    	workbenchWindow.getActionBars().getCoolBarManager().setLockLayout(value);
	}
	
	/**
	 * 获取工作台
	 * @return
	 */
	public static final IWorkbenchWindow getWorkbenchWindow(){
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	}
	
	/**
	 * 根据视图id获取视图对象
	 * @param viewId
	 * @return
	 */
	public static final IViewPart getView(String viewId){
		IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		return workbenchPage.findView(viewId);
	}
	
	/**
	 * 浏览ie，打开地址
	 * @param url
	 */
	public static final void openIE(String url){
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
