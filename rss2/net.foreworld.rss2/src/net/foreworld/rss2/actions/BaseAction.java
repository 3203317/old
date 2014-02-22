package net.foreworld.rss2.actions;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public abstract class BaseAction extends Action implements ISelectionListener,IWorkbenchAction,Observer {
	protected IWorkbenchWindow window;
	
	public BaseAction(IWorkbenchWindow window){
		this.window = window;		
	}

	public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
	}

	public void update(Observable o, Object arg) {
	}
	
	public void dispose() {
		this.window.getSelectionService().removeSelectionListener(this);
	}
}
