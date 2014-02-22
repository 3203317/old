package com.myfvn.ui.dialogs.emailaccount;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class CreateEmailAccountWizard extends Wizard implements INewWizard {

	private EmailNameDefinitionPage _emailNameDefinitionPage;

	private EmailDefinitionPage _emailDefinitionPage;

	private EmailServerDefinitionPage _emailServerDefinitionPage;
	
	private EmailLoginDefinitionPage _emailLoginDefinitionPage;
	
	private EmailOKDefinitionPage _emailOKDefinitionPage;

	public CreateEmailAccountWizard() {

		this.setWindowTitle(Messages.CreateEmailAccountWizard_NEW_EMAILACCOUNT);
	}

	public boolean performFinish() {
		return false;
	}

	public void init(IWorkbench $workbench, IStructuredSelection $selection) {

	}

	// ****************************************************************

	public void addPages() {

		this._emailNameDefinitionPage = new EmailNameDefinitionPage(
				Messages.CreateEmailAccountWizard_EMAILACCOUNT,
				Messages.CreateEmailAccountWizard_EMAILACCOUNT);
		this.addPage(this._emailNameDefinitionPage);

		this._emailDefinitionPage = new EmailDefinitionPage(
				Messages.CreateEmailAccountWizard_EMAIL,
				Messages.CreateEmailAccountWizard_EMAIL);
		this.addPage(this._emailDefinitionPage);

		this._emailServerDefinitionPage = new EmailServerDefinitionPage(
				Messages.CreateEmailAccountWizard_EMAILSERVER,
				Messages.CreateEmailAccountWizard_EMAILSERVER);
		this.addPage(this._emailServerDefinitionPage);
		
		this._emailLoginDefinitionPage = new EmailLoginDefinitionPage(
				Messages.CreateEmailAccountWizard_EMAILLOGIN,
				Messages.CreateEmailAccountWizard_EMAILLOGIN);
		this.addPage(this._emailLoginDefinitionPage);
		
		this._emailOKDefinitionPage = new EmailOKDefinitionPage(
				Messages.CreateEmailAccountWizard_EMAILOK,
				Messages.CreateEmailAccountWizard_EMAILOK);
		this.addPage(this._emailOKDefinitionPage);
	}

	public boolean needsProgressMonitor() {
		return true;
	}

}
