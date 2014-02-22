package net.foreworld.utils.rcp.core.persist.dao;

import net.foreworld.utils.rcp.core.persist.IEntity;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 * @param <T>
 */
public interface IEntityDAO<T extends IEntity> extends IPersistableDAO<T> {

	/**
	 * 
	 * @param $uuid
	 * @return
	 * @throws PersistenceException
	 */
	boolean exists(String $uuid) throws PersistenceException;

	/**
	 * 
	 * @param $uuid
	 * @return
	 * @throws PersistenceException
	 */
	T load(String $uuid) throws PersistenceException;

}
