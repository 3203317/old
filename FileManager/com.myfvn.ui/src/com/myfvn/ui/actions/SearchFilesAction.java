package com.myfvn.ui.actions;

import net.foreworld.utils.rcp.CacheImage;
import net.foreworld.utils.rcp.actions.BaseAction;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IWorkbenchWindow;

import com.myfvn.ui.Activator;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class SearchFilesAction extends BaseAction {

	private IWorkbenchWindow _window;

	private ISelectionProvider _selectionProvider;

	public SearchFilesAction(IWorkbenchWindow $window, ISelectionProvider $selectionProvider) {
		super($window);

		this._window = $window;

		this._selectionProvider = $selectionProvider;

		this.setText(Messages.SearchFilesAction_SEARCH_FILES);

		this.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/obj16/searchmark.gif"));//$NON-NLS-1$
	}

}
