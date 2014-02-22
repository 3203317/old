package net.foreworld.utils.rcp.actions;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class ShowKeyAssistAction extends BaseAction {

	private IBindingService _bindingService;

	public ShowKeyAssistAction(IWorkbenchWindow $window) {
		super($window);
		this._bindingService = (IBindingService) PlatformUI.getWorkbench().getService(IBindingService.class);
	}

	public void run() {
		this._bindingService.openKeyAssistDialog();
	}

}
