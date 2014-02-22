package com.myfvn.core.internal.persist.dao;

import java.util.Collection;

import net.foreworld.utils.rcp.core.internal.persist.dao.AbstractEntityDAO;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

import com.myfvn.core.internal.persist.Email;
import com.myfvn.core.persist.IEmail;
import com.myfvn.core.persist.dao.IEmailDAO;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class EmailDAOImpl extends AbstractEntityDAO<IEmail> implements
		IEmailDAO {

	public EmailDAOImpl() {
		super(Email.class);
	}

	public boolean exists(String $uuid) throws PersistenceException {
		return false;
	}

	public IEmail load(String $uuid) throws PersistenceException {
		return null;
	}

	public long countAll() throws PersistenceException {
		return 0;
	}

	public void delete(IEmail $persistable) throws PersistenceException {

	}

	public void deleteAll(Collection<IEmail> $persistables)
			throws PersistenceException {

	}

	public Class<? extends IEmail> getEntityClass() {
		return null;
	}

	public Collection<IEmail> loadAll() throws PersistenceException {
		return null;
	}

	public IEmail save(IEmail $persistable) throws PersistenceException {
		return null;
	}

	public void saveAll(Collection<IEmail> $persistables)
			throws PersistenceException {

	}

}
