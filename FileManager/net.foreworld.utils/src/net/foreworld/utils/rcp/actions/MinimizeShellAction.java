package net.foreworld.utils.rcp.actions;

import org.eclipse.ui.IWorkbenchWindow;

public class MinimizeShellAction extends BaseAction {

	private IWorkbenchWindow _window;
	
	public MinimizeShellAction(IWorkbenchWindow $window) {
		super($window);
		
		this._window = $window;
	}
	
	public void run(){
		
		this._window.getShell().setMinimized(true);
	}

}
