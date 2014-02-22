package net.foreworld.utils.rcp.core.internal.persist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.foreworld.utils.rcp.core.persist.EntityState;
import net.foreworld.utils.rcp.core.persist.IEntity;

import org.eclipse.core.runtime.Assert;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public abstract class AbstractEntity extends AbstractPersistable implements IEntity {
	private int _id;
	private String _uuid;
	private String _name;
	private EntityState _state;
	private HashMap<String, Serializable> _properties;
	protected String _parent_uuid;

	/**
	 * Default constructor provided for deserialization purposes.
	 */
	protected AbstractEntity() {
	}

	/**
	 * @param $uuid
	 *            the unique identifier of this entity
	 */
	protected AbstractEntity(String $uuid) {
		_uuid = $uuid;
	}

	/**
	 * Copy constructor.
	 * 
	 * @param $uuid
	 *            id for the copy.
	 * @param $entity
	 *            to copy from.
	 */
	protected AbstractEntity(String $uuid, AbstractEntity $entity) {
		_uuid = $uuid;
		synchronized ($entity) {
			if ($entity._properties != null)
				_properties = new HashMap<String, Serializable>($entity._properties);
		}
	}

	public final String getUuid() {
		return _uuid;
	}

	public void setUuid(String $uuid) {
		Assert.isNotNull($uuid, "uuid cannot be null"); //$NON-NLS-1$
		if ($uuid.equals(_uuid))
			return;

		if (_uuid != null)
			throw new IllegalStateException("Cannot change id after it's been set."); //$NON-NLS-1$

		_uuid = $uuid;
	}

	public final int getId() {
		return _id;
	}

	public void setId(int $id) {
		Assert.isNotNull($id, "id cannot be null"); //$NON-NLS-1$
		if ($id == _id)
			return;

		_id = $id;
	}

	public synchronized String getName() {
		return _name;
	}

	public void setName(String $name) {
		Assert.isNotNull($name, "name cannot be null"); //$NON-NLS-1$
		if ($name.equals(_name))
			return;

		if (_name != null)
			throw new IllegalStateException("Cannot change name after it's been set."); //$NON-NLS-1$

		_name = $name;
	}

	public synchronized void setProperty(String $key, Serializable $value) {
		Assert.isNotNull($key, "Using NULL as Key is not permitted!"); //$NON-NLS-1$
		if (_properties == null)
			_properties = new HashMap<String, Serializable>();

		_properties.put($key, $value);
	}

	public synchronized Object getProperty(String $key) {
		Assert.isNotNull($key, "Using NULL as Key is not permitted!"); //$NON-NLS-1$
		if (_properties == null)
			return null;

		return _properties.get($key);
	}

	public synchronized Object removeProperty(String $key) {
		Assert.isNotNull($key, "Using NULL as Key is not permitted!"); //$NON-NLS-1$
		if (_properties == null)
			return null;

		return _properties.remove($key);
	}

	public synchronized Map<String, Serializable> getProperties() {
		if (_properties == null)
			return Collections.emptyMap();

		return new HashMap<String, Serializable>(_properties);
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object $object) {
		if (this == $object)
			return true;

		if (($object == null) || ($object.getClass() != getClass()))
			return false;

		IEntity __type = (IEntity) $object;
		if (_uuid == null)
			return false;

		return _uuid.equals(__type.getId());
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (_uuid == null)
			return super.hashCode();

		return _uuid.hashCode();
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public synchronized String toString() {
		String __name = super.toString();
		int __index = __name.lastIndexOf('.');
		if (__index != -1)
			__name = __name.substring(__index + 1, __name.length());

		return __name + " (uuid = " + _uuid + ", Properties = " + getProperties() + ", "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * 
	 * @param <T>
	 * @param $type
	 * @param $list
	 * @return
	 */
	protected <T> List<T> extractTypes(Class<T> $type, List<? super T> $list) {
		List<T> __types = new ArrayList<T>($list.size());
		for (Object __object : $list) {
			if ($type.isInstance(__object))
				__types.add($type.cast(__object));
		}
		return __types;
	}

	public EntityState getState() {
		return this._state;
	}

	public void setState(EntityState $state) {
		this._state = $state;
	}

	public String getParent_uuid() {
		return _parent_uuid;
	}

	public void setParent_uuid(String $parent_uuid) {
		_parent_uuid = $parent_uuid;
	}
}
