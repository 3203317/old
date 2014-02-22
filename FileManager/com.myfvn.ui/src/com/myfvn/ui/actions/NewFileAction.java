package com.myfvn.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class NewFileAction extends Action implements IWorkbenchWindowActionDelegate, IObjectActionDelegate {

	private Shell _shell;

	public NewFileAction() {
		//
	}

	public NewFileAction(Shell $shell) {
		this._shell = $shell;
	}

	public void dispose() {

	}

	public void init(IWorkbenchWindow $window) {

	}

	public void run(IAction $action) {
		System.out.println($action);
	}

	public void selectionChanged(IAction $action, ISelection $selection) {

	}

	public void setActivePart(IAction $action, IWorkbenchPart $targetPart) {

	}

}
