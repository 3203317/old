package net.foreworld.utils.rcp.core.internal.persist.dao;

import net.foreworld.utils.rcp.core.persist.IPersistable;
import net.foreworld.utils.rcp.core.persist.dao.IPersistableDAO;

import org.eclipse.core.runtime.Assert;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 * @param <T>
 */
public abstract class AbstractPersistableDAO<T extends IPersistable> implements
		IPersistableDAO<T> {
	protected final Class<? extends T> _entityClass;

	/**
	 * 
	 * @param $entityClass
	 */
	public AbstractPersistableDAO(Class<? extends T> $entityClass) {
		Assert.isNotNull($entityClass, "entityClass"); //$NON-NLS-1$
		_entityClass = $entityClass;
	}
}
