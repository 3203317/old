package net.foreworld.utils.rcp.core.persist.dao;

import java.util.Collection;

import net.foreworld.utils.rcp.core.persist.IPersistable;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 * @param <T>
 */
public interface IPersistableDAO<T extends IPersistable> {

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	Collection<T> loadAll() throws PersistenceException;

	/**
	 * 
	 * @param $persistable
	 * @return
	 * @throws PersistenceException
	 */
	T save(T $persistable) throws PersistenceException;

	/**
	 * 
	 * @param $persistables
	 * @throws PersistenceException
	 */
	void saveAll(Collection<T> $persistables) throws PersistenceException;

	/**
	 * 
	 * @param $persistable
	 * @throws PersistenceException
	 */
	void delete(T $persistable) throws PersistenceException;

	/**
	 * 
	 * @param $persistables
	 * @throws PersistenceException
	 */
	void deleteAll(Collection<T> $persistables) throws PersistenceException;

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	long countAll() throws PersistenceException;

	/**
	 * 
	 * @return
	 */
	Class<? extends T> getEntityClass();
}
