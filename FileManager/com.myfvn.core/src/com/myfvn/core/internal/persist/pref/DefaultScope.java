package com.myfvn.core.internal.persist.pref;

import java.util.Properties;

import com.myfvn.core.persist.pref.IPreferenceScope;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class DefaultScope implements IPreferenceScope {

	/* Default Values */
	private static final boolean BOOLEAN_DEFAULT = false;
	private static final int INT_DEFAULT = 0;
	private static final long LONG_DEFAULT = 0L;

	private final Properties _defaults = new Properties();

	public void delete(String $key) {
		_defaults.remove($key);
	}

	public void flush() {
		/* Nothing to do here (DefaultScope only kept in Memory) */
	}

	public boolean getBoolean(String $key) {
		Object __res = _defaults.get($key);
		if (__res != null)
			return (Boolean) __res;

		return BOOLEAN_DEFAULT;
	}

	public int getInteger(String $key) {
		Object __res = _defaults.get($key);
		if (__res != null)
			return (Integer) __res;

		return INT_DEFAULT;
	}

	public int[] getIntegers(String $key) {
		Object __res = _defaults.get($key);
		if (__res != null)
			return (int[]) __res;

		return null;
	}

	public long getLong(String $key) {
		Object __res = _defaults.get($key);
		if (__res != null)
			return (Long) __res;

		return LONG_DEFAULT;
	}

	public long[] getLongs(String $key) {
		Object __res = _defaults.get($key);
		if (__res != null)
			return (long[]) __res;

		return null;
	}

	public IPreferenceScope getParent() {
		throw new RuntimeException("There can be no parent of the DefaultScope"); //$NON-NLS-1$
	}

	public String getString(String $key) {
		Object __res = _defaults.get($key);
		if (__res != null)
			return (String) __res;

		return null;
	}

	public String[] getStrings(String $key) {
		Object __res = _defaults.get($key);
		if (__res != null)
			return (String[]) __res;

		return null;
	}

	public boolean hasKey(String $key) {
		return _defaults.containsKey($key);
	}

	public void putBoolean(String $key, boolean $value) {
		if ($value == BOOLEAN_DEFAULT)
			delete($key);
		else
			_defaults.put($key, $value);

	}

	public void putInteger(String $key, int $value) {
		if ($value == INT_DEFAULT)
			delete($key);
		else
			_defaults.put($key, $value);
	}

	public void putIntegers(String $key, int[] $values) {
		if ($values == null)
			delete($key);
		else
			_defaults.put($key, $values);
	}

	public void putLong(String $key, long $value) {
		if ($value == LONG_DEFAULT)
			delete($key);
		else
			_defaults.put($key, $value);
	}

	public void putLongs(String $key, long[] $values) {
		if ($values == null)
			delete($key);
		else
			_defaults.put($key, $values);
	}

	public void putString(String $key, String $value) {
		if ($value == null)
			delete($key);
		else
			_defaults.put($key, $value);
	}

	public void putStrings(String $key, String[] $values) {
		if ($values == null)
			delete($key);
		else
			_defaults.put($key, $values);
	}

	public void save() {

	}

}
