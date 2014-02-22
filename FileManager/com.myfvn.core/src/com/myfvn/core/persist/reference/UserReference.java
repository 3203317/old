package com.myfvn.core.persist.reference;

import net.foreworld.utils.rcp.core.persist.reference.AbstractModelReference;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

import com.myfvn.core.internal.persist.service.DBHelper;
import com.myfvn.core.persist.IUser;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class UserReference extends AbstractModelReference {

	public UserReference(String $uuid) {
		super($uuid, IUser.class);
	}

	public IUser resolve() throws PersistenceException {
		return DBHelper.getDefault().getUser(this.getUuid());
	}
}
