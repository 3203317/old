package com.myfvn.ui.views.editors.email;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.myfvn.core.persist.IEmail;
import com.myfvn.ui.FvnUI;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class EmailViewInput implements IEditorInput {

	private final IEmail _email;
	private PerformAfterInputSet _performOnInputSet;
	private boolean _isDeleted;

	public EmailViewInput(IEmail $email) {
		this($email, null);
	}

	public EmailViewInput(IEmail $email, PerformAfterInputSet $performOnInputSet) {
		Assert.isNotNull($email);
		this._email = $email;
		this._performOnInputSet = $performOnInputSet;
	}

	public boolean exists() {
		return this._isDeleted;
	}

	/** Email this Input as Deleted (exists = false) */
	public void setDeleted() {
		this._isDeleted = true;
	}

	/**
	 * @return Returns the action that is to be done automatically once the
	 *         input has been set.
	 */
	public PerformAfterInputSet getPerformOnInputSet() {
		return _performOnInputSet;
	}

	public ImageDescriptor getImageDescriptor() {
		return FvnUI.UNKNOWN;
	}

	public String getName() {
		return _email.getName();
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return _email.getName();
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class $adapter) {
		return Platform.getAdapterManager().getAdapter(this, $adapter);
	}

	public IEmail getEmail() {
		return _email;
	}

	public boolean equals(Object $object) {
		if (this == $object)
			return true;

		if (($object == null) || ($object.getClass() != getClass()))
			return false;

		EmailViewInput ___type = (EmailViewInput) $object;
		return _email.equals(___type._email);
	}

}
