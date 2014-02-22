package net.foreworld.utils.rcp.core.persist.reference;

import net.foreworld.utils.rcp.core.persist.IEntity;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public abstract class AbstractModelReference {
	private final String _uuid;
	private final Class<? extends IEntity> _entityClass;

	/**
	 * 
	 * @param $uuid
	 * @param $entityClass
	 */
	protected AbstractModelReference(String $uuid,
			Class<? extends IEntity> $entityClass) {
		this._uuid = $uuid;
		this._entityClass = $entityClass;
	}

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public IEntity resolve() throws PersistenceException {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public final Class<? extends IEntity> getEntityClass() {
		return _entityClass;
	}

	/**
	 * 
	 * @return
	 */
	public final String getUuid() {
		return _uuid;
	}
}
