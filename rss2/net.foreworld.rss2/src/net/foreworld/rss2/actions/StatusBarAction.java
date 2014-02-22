package net.foreworld.rss2.actions;

import org.eclipse.ui.IWorkbenchWindow;

/**
 * 
 * @author »ÆöÎ
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 3:28:32 PM
 */
public class StatusBarAction extends BaseAction {
	public static final String ID = StatusBarAction.class.getName();
	
	public StatusBarAction(IWorkbenchWindow window) {
		super(window);
	}
	
	public void run(){
		System.out.println(ID);
	}
	

}
