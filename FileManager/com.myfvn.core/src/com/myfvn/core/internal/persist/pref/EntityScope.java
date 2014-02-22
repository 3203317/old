package com.myfvn.core.internal.persist.pref;

import java.util.Arrays;

import net.foreworld.utils.rcp.core.persist.IEntity;

import org.eclipse.core.runtime.Assert;

import com.myfvn.core.persist.pref.IPreferenceScope;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class EntityScope implements IPreferenceScope {
	private final IEntity _entity;
	private final IPreferenceScope _parent;

	public EntityScope(IEntity $entity, IPreferenceScope $parent) {
		Assert.isNotNull($entity, "entity cannot be null"); //$NON-NLS-1$
		_entity = $entity;
		_parent = $parent;
	}

	public void delete(String $key) {
		_entity.removeProperty($key);
	}

	public void flush() {

	}

	public boolean getBoolean(String $key) {
		/* Ask Entity */
		Object __prop = _entity.getProperty($key);
		if (__prop != null)
			return (Boolean) __prop;

		/* Ask Parent */
		return _parent.getBoolean($key);
	}

	public int getInteger(String $key) {
		/* Ask Entity */
		Object __prop = _entity.getProperty($key);
		if (__prop != null)
			return (Integer) __prop;

		/* Ask Parent */
		return _parent.getInteger($key);
	}

	public int[] getIntegers(String $key) {
		/* Ask Entity */
		Object __prop = _entity.getProperty($key);
		if (__prop != null)
			return (int[]) __prop;

		/* Ask Parent */
		return _parent.getIntegers($key);
	}

	public long getLong(String $key) {
		/* Ask Entity */
		Object __prop = _entity.getProperty($key);
		if (__prop != null)
			return (Long) __prop;

		/* Ask Parent */
		return _parent.getLong($key);
	}

	public long[] getLongs(String $key) {
		/* Ask Entity */
		Object __prop = _entity.getProperty($key);
		if (__prop != null)
			return (long[]) __prop;

		/* Ask Parent */
		return _parent.getLongs($key);
	}

	public IPreferenceScope getParent() {
		return _parent;
	}

	public String getString(String $key) {
		/* Ask Entity */
		Object __prop = _entity.getProperty($key);
		if (__prop != null)
			return (String) __prop;

		/* Ask Parent */
		return _parent.getString($key);
	}

	public String[] getStrings(String $key) {
		/* Ask Entity */
		Object __prop = _entity.getProperty($key);
		if (__prop != null)
			return (String[]) __prop;

		/* Ask Parent */
		return _parent.getStrings($key);
	}

	public boolean hasKey(String $key) {
		return _entity.getProperty($key) != null;
	}

	public void putBoolean(String $key, boolean $value) {
		if ($value != _parent.getBoolean($key))
			_entity.setProperty($key, $value);
		else
			delete($key);
	}

	public void putInteger(String $key, int $value) {
		if ($value != _parent.getInteger($key))
			_entity.setProperty($key, $value);
		else
			delete($key);
	}

	public void putIntegers(String $key, int[] $values) {
		Assert.isNotNull($values);

		if (!Arrays.equals($values, _parent.getIntegers($key)))
			_entity.setProperty($key, $values);
		else
			delete($key);
	}

	public void putLong(String $key, long $value) {
		if ($value != _parent.getLong($key))
			_entity.setProperty($key, $value);
		else
			delete($key);
	}

	public void putLongs(String $key, long[] $values) {
		Assert.isNotNull($values);

		if (!Arrays.equals($values, _parent.getLongs($key)))
			_entity.setProperty($key, $values);
		else
			delete($key);
	}

	public void putString(String $key, String $value) {
		Assert.isNotNull($value);

		if (!$value.equals(_parent.getString($key)))
			_entity.setProperty($key, $value);
		else
			delete($key);
	}

	public void putStrings(String $key, String[] $values) {
		Assert.isNotNull($values);

		if (!Arrays.equals($values, _parent.getStrings($key)))
			_entity.setProperty($key, $values);
		else
			delete($key);
	}

	public void save() {

	}

}
