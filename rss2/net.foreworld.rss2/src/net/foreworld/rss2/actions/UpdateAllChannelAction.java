package net.foreworld.rss2.actions;

import org.eclipse.ui.IWorkbenchWindow;

/**
 * 
 * @author »ÆöÎ
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 3:24:48 PM
 */
public class UpdateAllChannelAction extends BaseAction {
	
	public static final String ID = UpdateAllChannelAction.class.getName();
	
	
	public UpdateAllChannelAction(IWorkbenchWindow window) {
		super(window);
	}
	
	public void run(){
		System.out.println(ID);
	}
}
