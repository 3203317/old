package net.foreworld.utils.rcp.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class CustomTitleAreaDialog extends TitleAreaDialog {

	public CustomTitleAreaDialog(Shell $parentShell) {
		super($parentShell);
	}
	
	protected Control createContents(Composite $parent){
		
		Control __control = super.createContents($parent);
		
		Button __button = null;
		
		__button = this.getButton(IDialogConstants.CANCEL_ID);
		if(__button != null) __button.setText(Messages.CustomTitleAreaDialog_CANCEL_TEXT);

		__button = this.getButton(IDialogConstants.OK_ID);
		if(__button != null) __button.setText(Messages.CustomTitleAreaDialog_OK_TEXT);
		
		
		return __control;
	}

}
