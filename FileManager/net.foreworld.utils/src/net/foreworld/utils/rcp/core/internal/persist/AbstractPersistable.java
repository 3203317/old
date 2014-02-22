package net.foreworld.utils.rcp.core.internal.persist;

import net.foreworld.utils.rcp.core.persist.IPersistable;

import org.eclipse.core.runtime.Platform;

/**
 * 
 * @author huangxin
 * @email huangxin@foreworld.net
 * 
 */
public abstract class AbstractPersistable implements IPersistable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class $adapter) {
		return Platform.getAdapterManager().getAdapter(this, $adapter);
	}

}
