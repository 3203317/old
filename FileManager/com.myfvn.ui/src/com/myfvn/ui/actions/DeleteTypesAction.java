package com.myfvn.ui.actions;

import java.util.List;

import net.foreworld.utils.rcp.core.persist.IEntity;
import net.foreworld.utils.rcp.core.persist.IUser;
import net.foreworld.utils.rcp.core.persist.IWebSite;
import net.foreworld.utils.rcp.core.utils.ModelUtils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.myfvn.core.persist.IEmailbox;
import com.myfvn.ui.dialogs.ConfirmDialog;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class DeleteTypesAction extends Action implements IObjectActionDelegate {

	private Shell _shell = null;

	private IStructuredSelection _selection;
	private boolean _confirmed;

	public DeleteTypesAction() {
		this(null, StructuredSelection.EMPTY);
	}

	public DeleteTypesAction(Shell $shell, IStructuredSelection $selection) {
		this._shell = $shell;
		this._selection = $selection;
	}

	public void setActivePart(IAction $action, IWorkbenchPart $targetPart) {
		this._shell = $targetPart.getSite().getShell();
	}

	public void run(IAction $action) {
		if (!this._selection.isEmpty() && this.confirmed()) {
			BusyIndicator.showWhile(PlatformUI.getWorkbench().getDisplay(), new Runnable() {
				public void run() {
					internalRun();
				}
			});
		}
	}

	public void run() {
		if (!this._selection.isEmpty() && this.confirmed()) {
			BusyIndicator.showWhile(PlatformUI.getWorkbench().getDisplay(), new Runnable() {
				public void run() {
					internalRun();
				}
			});
		}
	}

	private void internalRun() {
		_confirmed = true;
		final List<IEntity> __entities = ModelUtils.getEntities(this._selection);

		System.out.println(__entities.size());
	}

	private boolean confirmed() {
		/* Ignore when deleting News since this operation can be undone */
		List<?> __elements = _selection.toList();
		for (Object ___element : __elements) {
			if (___element instanceof IUser) {
				return true;
			} else if (___element instanceof IWebSite) {
				return true;
			}
		}

		/* Check if the Archive is being deleted */
		boolean __includesArchive = includesArchive(_selection);

		/* Create Dialog and open if confirmation required */
		ConfirmDialog __dialog = new ConfirmDialog(_shell, Messages.DeleteTypesAction_CONFIRM_DELETE, Messages.DeleteTypesAction_NO_UNDO, getMessage(__elements, __includesArchive), null);
		return __dialog.open() == IDialogConstants.OK_ID;
	}

	public boolean isConfirmed() {
		return _confirmed;
	}

	public void selectionChanged(IAction $action, ISelection $selection) {
		if ($selection instanceof IStructuredSelection) {
			this._selection = (IStructuredSelection) $selection;
		}
	}

	private boolean includesArchive(IStructuredSelection $selection) {
		return false;
	}

	private String getMessage(List<?> $elements, boolean $includesArchive) {

		/* One Element */
		if ($elements.size() == 1) {
			Object ___element = $elements.get(0);

			if (___element instanceof IEmailbox) {
				return NLS.bind(Messages.DeleteTypesAction_CONFIRM_DELETE_EMAILBOX, ((IEmailbox) ___element).getName());
			}

		}

		return Messages.DeleteTypesAction_CONFIRM_DELETE_ELEMENTS;
	}

}
