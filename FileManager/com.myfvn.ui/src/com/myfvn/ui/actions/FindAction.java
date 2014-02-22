package com.myfvn.ui.actions;

import net.foreworld.utils.rcp.actions.BaseAction;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

import com.myfvn.ui.FvnUI;
import com.myfvn.ui.views.FileNavigatorView;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class FindAction extends BaseAction {

	public FindAction(IWorkbenchWindow $window) {
		super($window);
	}

	public void run() {
		IWorkbenchWindow __window = FvnUI.getWindow();
		if (__window != null) {
			IWorkbenchPage ___activePage = __window.getActivePage();
			if (___activePage != null) {
				IWorkbenchPart ____activePart = ___activePage.getActivePart();
				if (____activePart != null) {
					/* Find in FilNavigatorView Explorer */
					if (____activePart instanceof FileNavigatorView) {
						((FileNavigatorView) ____activePart).find();
					}
				}
			}
		}

		super.run();
	}

}
