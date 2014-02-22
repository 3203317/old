package net.foreworld.utils.rcp.core.internal.persist.dao;

import net.foreworld.utils.rcp.core.persist.IEntity;
import net.foreworld.utils.rcp.core.persist.dao.IEntityDAO;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 * @param <T>
 */
public abstract class AbstractEntityDAO<T extends IEntity> extends
		AbstractPersistableDAO<T> implements IEntityDAO<T> {

	/**
	 * 
	 * @param $entityClass
	 */
	public AbstractEntityDAO(Class<? extends T> $entityClass) {
		super($entityClass);
	}

}
