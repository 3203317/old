package net.foreworld.rss2.actions;

import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.update.ui.UpdateManagerUI;

public class ManageConfigurationAction extends BaseAction {
	
	public static final String ID = ManageConfigurationAction.class.getName();
	private IWorkbenchWindow window;

	public ManageConfigurationAction(IWorkbenchWindow window) {
		super(window);
		this.window = window;
	}
	
	public void run(){
		BusyIndicator.showWhile(this.window.getShell().getDisplay(), new Runnable(){
			public void run() {
				UpdateManagerUI.openConfigurationManager(window.getShell());
			}
		});
	}

}
