package com.myfvn.core.internal.persist.dao;

import java.util.Collection;

import net.foreworld.utils.rcp.core.internal.persist.WebSite;
import net.foreworld.utils.rcp.core.internal.persist.dao.AbstractEntityDAO;
import net.foreworld.utils.rcp.core.persist.IWebSite;
import net.foreworld.utils.rcp.core.persist.dao.IWebSiteDAO;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class WebSiteDAOImpl extends AbstractEntityDAO<IWebSite> implements
		IWebSiteDAO {

	public WebSiteDAOImpl() {
		super(WebSite.class);
	}

	public boolean exists(String $uuid) throws PersistenceException {
		return false;
	}

	public IWebSite load(String $uuid) throws PersistenceException {
		return null;
	}

	public long countAll() throws PersistenceException {
		return 0;
	}

	public void delete(IWebSite $persistable) throws PersistenceException {

	}

	public void deleteAll(Collection<IWebSite> $persistables)
			throws PersistenceException {

	}

	public Class<? extends IWebSite> getEntityClass() {
		return null;
	}

	public Collection<IWebSite> loadAll() throws PersistenceException {
		return null;
	}

	public IWebSite save(IWebSite $persistable) throws PersistenceException {
		return null;
	}

	public void saveAll(Collection<IWebSite> $persistables)
			throws PersistenceException {

	}

}
