package net.foreworld.rss2.actions;


import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * 
 * @author »ÆöÎ
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 1:51:43 PM
 */
public class ImportFavoritesAction implements IWorkbenchWindowActionDelegate{

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void init(IWorkbenchWindow arg0) {
		// TODO Auto-generated method stub
		
	}

	public void run(IAction arg0) {
		System.out.println(arg0.getText());
	}

	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub
		
	}

}
