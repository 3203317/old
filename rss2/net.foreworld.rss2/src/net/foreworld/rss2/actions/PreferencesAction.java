package net.foreworld.rss2.actions;

import net.foreworld.rss2.Activator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * 
 * @author ª∆ˆŒ
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 3:29:09 PM
 */
public class PreferencesAction extends Action {
	public static final String ID = PreferencesAction.class.getName();
	private IWorkbenchWindow window;
	
	public PreferencesAction(IWorkbenchWindow window) {
		this.setId(ID);
		this.setText("…Ë÷√(&C)");
		this.window = window;
		this.setImageDescriptor(Activator.getImageDescriptor("/icons/QuickReader_151.gif"));
	}
	
	public void run(){
		PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(null, null, null, null);
		dialog.open();
	}
	
	public void dispose(){
		this.window = null;
	}

}
