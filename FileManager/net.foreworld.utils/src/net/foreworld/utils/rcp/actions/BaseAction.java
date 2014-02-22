package net.foreworld.utils.rcp.actions;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public abstract class BaseAction extends Action implements ISelectionListener,
		IWorkbenchAction, Observer {
	
	protected IWorkbenchWindow _window;
	
	public BaseAction(IWorkbenchWindow $window){
		this._window = $window;
	}

	public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		this._window.getSelectionService().removeSelectionListener(this);
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
