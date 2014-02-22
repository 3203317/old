package net.foreworld.rss2.utils;

import net.foreworld.rss2.Activator;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.StatusLineLayoutData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;

public class StatusBarContribution extends ContributionItem {

	public void fill(Composite parent){
		CLabel label = new CLabel(parent, SWT.LEFT | SWT.SEPARATOR); 
		StatusLineLayoutData statusLineLayoutData = new StatusLineLayoutData(); 
		statusLineLayoutData.widthHint = 170; 
		label.setLayoutData(statusLineLayoutData); 
		label.setText(Platform.getProduct().getName()); 
		label.setImage(Activator.getImageDescriptor("icons/ieframe_31064.gif").createImage());
	}
}
