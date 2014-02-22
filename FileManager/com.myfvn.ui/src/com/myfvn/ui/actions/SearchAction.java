package com.myfvn.ui.actions;

import net.foreworld.utils.rcp.actions.BaseAction;

import org.eclipse.ui.IWorkbenchWindow;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class SearchAction extends BaseAction {

	public static final String ID = "com.myfvn.ui.SearchMailsAction";//$NON-NLS-1$

	public SearchAction(IWorkbenchWindow $window) {
		super($window);
	}

	public void run() {
		System.out.println(this.getId());
	}
}
