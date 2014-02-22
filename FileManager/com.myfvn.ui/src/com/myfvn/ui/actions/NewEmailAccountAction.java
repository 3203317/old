package com.myfvn.ui.actions;

import java.util.logging.Logger;

import net.foreworld.utils.rcp.RcpUI;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.myfvn.ui.Activator;
import com.myfvn.ui.Application;
import com.myfvn.ui.dialogs.emailaccount.CreateEmailAccountWizard;

public class NewEmailAccountAction extends Action implements IWorkbenchWindowActionDelegate,
		IObjectActionDelegate {

	final static Logger _logger = Logger.getLogger(NewEmailAccountAction.class
			.getName());

	final static String SETTINGS_SECTION = CreateEmailAccountWizard.class
			.getName();

	private Shell _shell;

	public NewEmailAccountAction() {
		//
	}
	
	public NewEmailAccountAction(Shell $shell){
		this._shell = $shell;
	}
	
	//*****************************************************************

	public void dispose() {}

	public void init(IWorkbenchWindow $window) {
		this._shell = $window.getShell();
	}
	
	public void run(){
		this.run(null);
	}

	public void run(IAction $action) {
		_logger.info("Create Email Account Wizard ...");

		CreateEmailAccountWizard __wizard = new CreateEmailAccountWizard();

		RcpUI.openWizard(this._shell, __wizard, true, true, SETTINGS_SECTION,
				Activator.getDefault().getDialogSettings(),
				Application.IS_WINDOWS, Application.IS_LINUX);
	}

	public void selectionChanged(IAction $action, ISelection $select) {

	}

	public void setActivePart(IAction $action, IWorkbenchPart $targetPart) {
		this._shell = $targetPart.getSite().getShell();
	}

}
