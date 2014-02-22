package com.myfvn.ui;

import net.foreworld.utils.rcp.core.persist.IEntity;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.osgi.util.NLS;

import com.myfvn.core.persist.IAttachment;
import com.myfvn.core.persist.IEmail;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class StatusLineUpdater implements ISelectionChangedListener {

	private IStatusLineManager _statusLineManager;

	public StatusLineUpdater(IStatusLineManager $statusLineManager) {
		_statusLineManager = $statusLineManager;
	}

	public void selectionChanged(SelectionChangedEvent $event) {
		IStructuredSelection __selection = (IStructuredSelection) $event.getSelection();
		String __text = formatElements(__selection.toArray());
		/* Show Message */
		_statusLineManager.setMessage(__text);
	}

	private String formatElements(Object $elements[]) {

		/* No Element selected */
		if ($elements.length == 0)
			return ""; //$NON-NLS-1$

		/* Only 1 Element selected */
		if ($elements.length == 1) {
			Object __element = $elements[0];
			if (__element instanceof IEntity) {
				return ((IEntity) __element).getName();
			}
			return Messages.StatusLineUpdater_ITEM_SELECTED;
		}

		/* More than 1 Element selected */
		int __emailCount = 0;
		int __attachmentCount = 0;

		for (Object ___element : $elements) {
			if (___element instanceof IEmail) {
				__emailCount++;
			} else if (___element instanceof IAttachment) {
				__attachmentCount++;
			}
		}

		StringBuilder __itemsBuf = new StringBuilder();
		if (__emailCount > 0) {
			__itemsBuf.append(__emailCount == 1 ? NLS.bind(Messages.StatusLineUpdater_N_EMAIL, __emailCount) : NLS.bind(Messages.StatusLineUpdater_N_EMAILS, __emailCount)).append(", ");//$NON-NLS-1$
		}

		if (__attachmentCount > 0) {
			__itemsBuf.append(__attachmentCount == 1 ? NLS.bind(Messages.StatusLineUpdater_N_ATTACHMENT, __attachmentCount) : NLS.bind(Messages.StatusLineUpdater_N_ATTACHMENTS, __attachmentCount)).append(", ");//$NON-NLS-1$
		}

		if (__itemsBuf.length() > 0) {
			__itemsBuf.delete(__itemsBuf.length() - 2, __itemsBuf.length());
		}

		StringBuilder __buf = new StringBuilder();
		__buf.append(NLS.bind(Messages.StatusLineUpdater_N_ITEMS_SELECTED, $elements.length, __itemsBuf.toString()));

		return __buf.toString();
	}

}
