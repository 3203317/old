package com.myfvn.ui.actions;

import java.util.EnumSet;

import net.foreworld.utils.rcp.core.persist.IParent;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.myfvn.ui.FvnUI;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class OpenInNewTabAction extends Action {

	private IWorkbenchPage _page;
	private IStructuredSelection _selection;
	private IParent _parent;

	public OpenInNewTabAction(IWorkbenchWindow $window, IWorkbenchPage $page, ISelectionProvider $selectionProvider) {
		this($window, $page, (IStructuredSelection) $selectionProvider.getSelection());
	}

	public OpenInNewTabAction(IWorkbenchWindow $window, IWorkbenchPage $page, IStructuredSelection $selection) {
		this._page = $page;
		this._selection = $selection;
		this.setText(this._selection.size() == 1 ? Messages.OpenInNewTabAction_OPEN_IN_NEW_TAB : Messages.OpenInNewTabAction_OPEN_ALL_IN_NEW_TABS);
	}

	public OpenInNewTabAction(IWorkbenchPage $page, IParent $parent) {
		this._page = $page;
		this._parent = $parent;
		this.setText(Messages.OpenInNewTabAction_OPEN_ALL_IN_NEW_TABS);
	}

	public void run() {
		/* Find Elements to Open */
		if (_selection == null && _parent != null) {
			_selection = new StructuredSelection(_parent.getChildren());
		}

		/* Open in View */
		BusyIndicator.showWhile(PlatformUI.getWorkbench().getDisplay(), new Runnable() {
			public void run() {
				FvnUI.openInEmailView(_page, _selection, EnumSet.of(FvnUI.ViewOpenMode.IGNORE_REUSE));
			}
		});

	}

}
