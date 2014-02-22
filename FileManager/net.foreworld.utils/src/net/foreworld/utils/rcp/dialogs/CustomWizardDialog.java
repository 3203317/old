package net.foreworld.utils.rcp.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class CustomWizardDialog extends WizardDialog {

	public CustomWizardDialog(Shell $parentShell, IWizard $newWizard) {
		super($parentShell, $newWizard);
	}
	
	public Button getButton(int $id){
		return super.getButton($id);
	}
	
	protected Control createContents(Composite $parent){
		
		Control __control = super.createContents($parent);

		Button __button = null;
		
		__button = this.getButton(IDialogConstants.CANCEL_ID);
		if(__button != null) __button.setText(Messages.CustomWizardDialog_CANCEL_TEXT);

		__button = this.getButton(IDialogConstants.OK_ID);
		if(__button != null) __button.setText(Messages.CustomWizardDialog_OK_TEXT);
		
		__button = this.getButton(IDialogConstants.FINISH_ID);
		if(__button != null) __button.setText(Messages.CustomWizardDialog_FINISH_TEXT);
		
		__button = this.getButton(IDialogConstants.BACK_ID);
		if(__button != null) __button.setText(Messages.CustomWizardDialog_BACK_TEXT);
		
		__button = this.getButton(IDialogConstants.NEXT_ID);
		if(__button != null) __button.setText(Messages.CustomWizardDialog_NEXT_TEXT);
		
		return __control;
	}
}
