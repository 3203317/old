package com.myfvn.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.update.ui.UpdateManagerUI;

public class ManageConfigurationAction extends Action implements IWorkbenchWindowActionDelegate{
	
	private Shell _shell;

	public void dispose() {
		
	}

	public void init(IWorkbenchWindow $window) {
		this._shell = $window.getShell();
	}

	public void run(IAction $action) {
		this.run();
	}
	
	public void run(){
		BusyIndicator.showWhile(this._shell.getDisplay(), new Runnable(){
			public void run() {
				UpdateManagerUI.openConfigurationManager(_shell);
			}
		});
	}

	public void selectionChanged(IAction arg0, ISelection arg1) {
		
	}

}
