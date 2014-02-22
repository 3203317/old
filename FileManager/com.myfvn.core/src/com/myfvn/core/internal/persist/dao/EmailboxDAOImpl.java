package com.myfvn.core.internal.persist.dao;

import java.util.Collection;

import net.foreworld.utils.rcp.core.internal.persist.dao.AbstractEntityDAO;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

import com.myfvn.core.internal.persist.EmailBox;
import com.myfvn.core.persist.IEmailbox;
import com.myfvn.core.persist.dao.IEmailboxDAO;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class EmailboxDAOImpl extends AbstractEntityDAO<IEmailbox>
		implements IEmailboxDAO {

	public EmailboxDAOImpl() {
		super(EmailBox.class);
	}

	public boolean exists(String $uuid) throws PersistenceException {
		return false;
	}

	public IEmailbox load(String $uuid) throws PersistenceException {
		return null;
	}

	public long countAll() throws PersistenceException {
		return 0;
	}

	public void delete(IEmailbox $persistable) throws PersistenceException {

	}

	public void deleteAll(Collection<IEmailbox> $persistables)
			throws PersistenceException {

	}

	public Class<? extends IEmailbox> getEntityClass() {
		return null;
	}

	public Collection<IEmailbox> loadAll() throws PersistenceException {
		return null;
	}

	public IEmailbox save(IEmailbox $persistable) throws PersistenceException {
		return null;
	}

	public void saveAll(Collection<IEmailbox> $persistables)
			throws PersistenceException {

	}

}
