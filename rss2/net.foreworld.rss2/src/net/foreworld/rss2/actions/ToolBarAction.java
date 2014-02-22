package net.foreworld.rss2.actions;

import net.foreworld.rss2.utils.MyRcpUtil;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * 
 * @author »ÆöÎ
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 3:28:01 PM
 */
public class ToolBarAction extends Action {
	public static final String ID = ToolBarAction.class.getName();
	private IWorkbenchWindow window;
	
	public ToolBarAction(IWorkbenchWindow window) {
		this.setId(ID);
		this.setText("¹¤¾ßÀ¸(&T)");
		this.window = window;
		this.setChecked(true);
//		this.setImageDescriptor(Activator.getImageDescriptor("/icons/QuickReader_179.gif"));
	}
	
	public void run(){
		MyRcpUtil.showCoolBar(false);
	}
	
	public void dispose(){
		this.window = null;
	}

}
