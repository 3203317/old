package com.myfvn.ui.dialogs;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class StartupProgressDialog extends ProgressMonitorDialog {
	private LocalResourceManager _resource;

	public StartupProgressDialog() {
		super(null);
		this._resource = new LocalResourceManager(JFaceResources.getResources());
	}

	public boolean close() {
		this._resource.dispose();
		return super.close();
	}
}
