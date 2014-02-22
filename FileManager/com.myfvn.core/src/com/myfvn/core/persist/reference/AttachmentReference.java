package com.myfvn.core.persist.reference;

import net.foreworld.utils.rcp.core.persist.reference.AbstractModelReference;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

import com.myfvn.core.internal.persist.service.DBHelper;
import com.myfvn.core.persist.IAttachment;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class AttachmentReference extends AbstractModelReference {

	protected AttachmentReference(String $uuid) {
		super($uuid, IAttachment.class);
	}

	public IAttachment resolve() throws PersistenceException {
		return DBHelper.getDefault().getAttachment(this.getUuid());
	}

}
