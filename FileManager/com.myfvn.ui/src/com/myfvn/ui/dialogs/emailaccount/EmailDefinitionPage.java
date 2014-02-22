package com.myfvn.ui.dialogs.emailaccount;

import net.foreworld.utils.rcp.CacheImage;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.myfvn.ui.Activator;

public class EmailDefinitionPage extends WizardPage {

	protected EmailDefinitionPage(String $pageName, String $title) {
		super($pageName, $title, CacheImage.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, "icons/emailname64.png"));

		this.setMessage(Messages.EmailDefinitionPage_CREATE_EMAILACCOUNT);
	}

	public void createControl(Composite $parent) {

		Composite __container = new Composite($parent, SWT.NONE);

		this.setControl($parent);
	}

}
