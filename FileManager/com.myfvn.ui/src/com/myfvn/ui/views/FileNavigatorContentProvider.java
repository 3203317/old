package com.myfvn.ui.views;

import java.util.List;

import net.foreworld.utils.rcp.core.persist.IChild;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.myfvn.core.persist.IAttachment;
import com.myfvn.core.persist.IEmail;
import com.myfvn.core.persist.IEmailbox;
import com.myfvn.core.persist.IUser;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class FileNavigatorContentProvider implements ITreeContentProvider {

	public Object[] getChildren(Object $object) {
		List<IChild> __list = null;

		if ($object instanceof IUser) {
			IUser ___user = (IUser) $object;
			__list = ___user.getChildren();
		} else if ($object instanceof IEmailbox) {
			IEmailbox ___emailbox = (IEmailbox) $object;
			__list = ___emailbox.getChildren();
		} else if ($object instanceof IEmail) {
			IEmail ___email = (IEmail) $object;
			__list = ___email.getChildren();
		} else if ($object instanceof IAttachment) {
			IAttachment ___attachment = (IAttachment) $object;
			__list = ___attachment.getChildren();
		}

		if (__list != null && __list.size() > 0) {
			return __list.toArray();
		}
		return new Object[0];
	}

	public Object getParent(Object $object) {

		if ($object instanceof IUser) {
			IUser ___user = (IUser) $object;
			return ___user.getParent();
		} else if ($object instanceof IEmailbox) {
			IEmailbox ___emailbox = (IEmailbox) $object;
			return ___emailbox.getParent();
		} else if ($object instanceof IEmail) {
			IEmail ___email = (IEmail) $object;
			return ___email.getParent();
		} else if ($object instanceof IAttachment) {
			IAttachment ___attachment = (IAttachment) $object;
			return ___attachment.getParent();
		}
		return null;
	}

	public boolean hasChildren(Object $object) {
		List<IChild> __list = null;

		if ($object instanceof IUser) {
			IUser ___user = (IUser) $object;
			__list = ___user.getChildren();
		} else if ($object instanceof IEmailbox) {
			IEmailbox ___emailbox = (IEmailbox) $object;
			__list = ___emailbox.getChildren();
		} else if ($object instanceof IEmail) {
			IEmail ___email = (IEmail) $object;
			__list = ___email.getChildren();
		} else if ($object instanceof IAttachment) {
			IAttachment ___attachment = (IAttachment) $object;
			__list = ___attachment.getChildren();
		}
		return __list != null && __list.size() > 0;
	}

	public Object[] getElements(Object $object) {
		return this.getChildren($object);
	}

	public void dispose() {

	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {

	}

}
