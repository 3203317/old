package com.myfvn.core.internal.persist.dao;

import java.util.Collection;

import net.foreworld.utils.rcp.core.internal.persist.dao.AbstractEntityDAO;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

import com.myfvn.core.internal.persist.Attachment;
import com.myfvn.core.persist.IAttachment;
import com.myfvn.core.persist.dao.IAttachmentDAO;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class AttachmentDAOImpl extends AbstractEntityDAO<IAttachment>
		implements IAttachmentDAO {

	public AttachmentDAOImpl() {
		super(Attachment.class);
	}

	public boolean exists(String $uuid) throws PersistenceException {
		return false;
	}

	public IAttachment load(String $uuid) throws PersistenceException {
		return null;
	}

	public long countAll() throws PersistenceException {
		return 0;
	}

	public void delete(IAttachment $persistable) throws PersistenceException {

	}

	public void deleteAll(Collection<IAttachment> $persistables)
			throws PersistenceException {

	}

	public Class<? extends IAttachment> getEntityClass() {
		return null;
	}

	public Collection<IAttachment> loadAll() throws PersistenceException {
		return null;
	}

	public IAttachment save(IAttachment $persistable)
			throws PersistenceException {
		return null;
	}

	public void saveAll(Collection<IAttachment> $persistables)
			throws PersistenceException {

	}

}
