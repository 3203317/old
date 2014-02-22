package com.myfvn.core.persist.reference;

import net.foreworld.utils.rcp.core.persist.reference.AbstractModelReference;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

import com.myfvn.core.internal.persist.service.DBHelper;
import com.myfvn.core.persist.IEmailbox;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class EmailboxReference extends AbstractModelReference {

	protected EmailboxReference(String $uuid) {
		super($uuid, IEmailbox.class);
	}

	public IEmailbox resolve() throws PersistenceException {
		return DBHelper.getDefault().getEmailbox(this.getUuid());
	}

}
