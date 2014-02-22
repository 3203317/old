package com.myfvn.core.internal.persist;

import java.util.List;
import java.util.logging.Logger;

import net.foreworld.utils.rcp.core.persist.dao.IUserDAO;

import org.eclipse.core.runtime.Assert;

import com.myfvn.core.internal.persist.dao.UserDAOImpl;
import com.myfvn.core.persist.IEmailbox;
import com.myfvn.core.persist.IPreference;
import com.myfvn.core.persist.IUser;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class User extends net.foreworld.utils.rcp.core.internal.persist.User implements IUser {
	final Logger _logger = Logger.getLogger(User.class.getName());
	final IUserDAO _userDAO = new UserDAOImpl();

	public void addEmailbox(IEmailbox $emailbox, IEmailbox $position, boolean $after) {
		Assert.isNotNull($emailbox, "Exception adding NULL as Emailbox into User"); //$NON-NLS-1$
		this.addChild($emailbox, $position, $after);
	}

	public List<IEmailbox> getEmailboxs() {
		return this.extractTypes(IEmailbox.class, this._children);
	}

	public void login() {
		this._userDAO.login(this);
	}

	public void logout() {
		this._userDAO.logout(this);
	}

	public void addPreference(IPreference $preference, IPreference $position, boolean $after) {
		Assert.isNotNull($preference, "Exception adding NULL as Preference into User"); //$NON-NLS-1$
		this.addChild($preference, $position, $after);
	}

	public List<IPreference> getPreference() {
		return this.extractTypes(IPreference.class, this._children);
	}

}
