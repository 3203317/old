package com.myfvn.ui.dialogs.emailaccount;

import net.foreworld.utils.rcp.CacheImage;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.myfvn.ui.Activator;

public class EmailOKDefinitionPage extends WizardPage {

	protected EmailOKDefinitionPage(String $pageName, String $title) {
		super($pageName, $title, CacheImage.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, "icons/emailname64.png"));

	}

	public void createControl(Composite $parent) {

		Composite __container = new Composite($parent, SWT.NONE);

		this.setControl($parent);
	}

}
