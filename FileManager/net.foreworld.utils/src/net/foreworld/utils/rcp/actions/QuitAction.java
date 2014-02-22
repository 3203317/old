package net.foreworld.utils.rcp.actions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class QuitAction extends BaseAction {

	public QuitAction(IWorkbenchWindow $window) {
		super($window);
	}
	
	public void run(){
		boolean __result = MessageDialog.openConfirm(this._window.getShell(), 
    							"\u7cfb\u7edf\u9000\u51fa",	//系统退出 
								"\u60a8\u786e\u5b9a\u8981\u9000\u51fa\u7cfb\u7edf\u5417\uff1f");	//您确定要退出系统吗？
		
		if(__result) PlatformUI.getWorkbench().close();
	}

}
