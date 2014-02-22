package com.myfvn.core.persist.reference;

import net.foreworld.utils.rcp.core.persist.reference.AbstractModelReference;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

import com.myfvn.core.internal.persist.service.DBHelper;
import com.myfvn.core.persist.IEmail;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class EmailReference extends AbstractModelReference {

	protected EmailReference(String $uuid) {
		super($uuid, IEmail.class);
	}

	public IEmail resolve() throws PersistenceException {
		return DBHelper.getDefault().getEmail(this.getUuid());
	}

}
