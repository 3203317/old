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
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 11, 2008 9:02:14 AM
 */
public class MyRcpUtil {
	/**
	 * �ı�״̬����ʾ
	 * @param value
	 */
	public static final void setStatusLine(String value){
		WorkbenchWindow workbenchWindow = (WorkbenchWindow)PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IActionBars actionBars = workbenchWindow.getActionBars();
    	IStatusLineManager statusLine = actionBars.getStatusLineManager();
    	statusLine.setMessage(value);
	}
	
	/**
	 * ����������
	 * @param value
	 */
	public static final void lockCoolBar(boolean value){
		WorkbenchWindow workbenchWindow = (WorkbenchWindow)PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    	workbenchWindow.getCoolBarManager().setLockLayout(value);
	}
	
	/**
	 * ��ʾ���������������������û��д��о�
	 * @param value
	 */
	public static final void showCoolBar(boolean value){
		WorkbenchWindow workbenchWindow = (WorkbenchWindow)PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    	workbenchWindow.getActionBars().getCoolBarManager().setLockLayout(value);
	}
	
	/**
	 * ��ȡ����̨
	 * @return
	 */
	public static final IWorkbenchWindow getWorkbenchWindow(){
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	}
	
	/**
	 * ������ͼid��ȡ��ͼ����
	 * @param viewId
	 * @return
	 */
	public static final IViewPart getView(String viewId){
		IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		return workbenchPage.findView(viewId);
	}
	
	/**
	 * ���ie���򿪵�ַ
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
