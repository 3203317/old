package com.myfvn.core.persist.pref;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IPreferenceScope {

	/** Supported Scopes for Preferences */
	public enum Kind {

		/** Application Global */
		GLOBAL,

		/** Eclipse */
		ECLIPSE,

		/** Entity */
		ENTITY
	}

	public void save();

	/**
	 * Returns the parent scope to lookup Preferences in case the actual scope
	 * is missing preference-values.
	 * 
	 * @return The parent scope or <code>NULL</code> if none.
	 */
	public IPreferenceScope getParent();

	/**
	 * Writes the made changes to the underlying persistence layer.
	 */
	public void flush();

	/**
	 * Deletes a preference from this Scope.
	 * 
	 * @param $key
	 *            The Key under which the value is stored.
	 */
	void delete(String $key);

	/**
	 * Retrieves a <code>Boolean</code> value from this Scope. Asks the parent
	 * Scope for the Value in case it is not present at this Scope.
	 * 
	 * @param $key
	 *            The Key under which the value is stored.
	 * @return The <code>Boolean</code> value from this Scope or the parent
	 *         Scope.
	 */
	boolean getBoolean(String $key);

	/**
	 * Retrieves a <code>Integer</code> value from this Scope. Asks the parent
	 * Scope for the Value in case it is not present at this Scope.
	 * 
	 * @param $key
	 *            The Key under which the value is stored.
	 * @return The <code>Integer</code> value from this Scope or the parent
	 *         Scope.
	 */
	int getInteger(String $key);

	/**
	 * Retrieves a <code>int</code> array from this Scope. Asks the parent
	 * Scope for the Value in case it is not present at this Scope.
	 * 
	 * @param $key
	 *            The Key under which the value is stored.
	 * @return The <code>int</code> array from this Scope or the parent Scope.
	 */
	int[] getIntegers(String $key);

	/**
	 * Retrieves a <code>Long</code> value from this Scope. Asks the parent
	 * Scope for the Value in case it is not present at this Scope.
	 * 
	 * @param $key
	 *            The Key under which the value is stored.
	 * @return The <code>Long</code> value from this Scope or the parent
	 *         Scope.
	 */
	long getLong(String $key);

	/**
	 * Retrieves a <code>long</code> array from this Scope. Asks the parent
	 * Scope for the Value in case it is not present at this Scope.
	 * <p>
	 * Note: The underlying persistence solution is making sure to keep the
	 * order of Items inside the Array when saving and loading.
	 * </p>
	 * 
	 * @param $key
	 *            The Key under which the value is stored.
	 * @return The <code>long</code> array from this Scope or the parent
	 *         Scope.
	 */
	long[] getLongs(String $key);

	/**
	 * Retrieves a <code>String</code> value from this Scope. Asks the parent
	 * Scope for the Value in case it is not present at this Scope.
	 * 
	 * @param $key
	 *            The Key under which the value is stored.
	 * @return The <code>String</code> value from this Scope or the parent
	 *         Scope.
	 */
	String getString(String $key);

	/**
	 * Retrieves a <code>String</code> array from this Scope. Asks the parent
	 * Scope for the Value in case it is not present at this Scope.
	 * <p>
	 * Note: The underlying persistence solution is making sure to keep the
	 * order of Items inside the Array when saving and loading.
	 * </p>
	 * 
	 * @param $key
	 *            The Key under which the value is stored.
	 * @return The <code>String</code> array from this Scope or the parent
	 *         Scope.
	 */
	String[] getStrings(String $key);

	/**
	 * Stores a <code>boolean</code> value under the given key into the
	 * persistance layer or updates it, if it is already present.
	 * 
	 * @param $key
	 *            The key under which the value is stored.
	 * @param $value
	 *            The <code>boolean</code> value that is to be stored.
	 */
	void putBoolean(String $key, boolean $value);

	/**
	 * Stores a <code>int</code> value under the given key into the
	 * persistance layer or updates it, if it is already present.
	 * 
	 * @param $key
	 *            The key under which the value is stored.
	 * @param $value
	 *            The <code>int</code> value that is to be stored.
	 */
	void putInteger(String $key, int $value);

	/**
	 * Stores a <code>int</code> array under the given key into the
	 * persistance layer or updates it, if it is already present.
	 * 
	 * @param $key
	 *            The key under which the value is stored.
	 * @param $values
	 *            The <code>int</code> array that is to be stored.
	 */
	void putIntegers(String $key, int $values[]);

	/**
	 * Stores a <code>long</code> value under the given key into the
	 * persistance layer or updates it, if it is already present.
	 * 
	 * @param $key
	 *            The key under which the value is stored.
	 * @param $value
	 *            The <code>long</code> value that is to be stored.
	 */
	void putLong(String $key, long $value);

	/**
	 * Stores a <code>long</code> array under the given key into the
	 * persistance layer or updates it, if it is already present.
	 * <p>
	 * Note: The underlying persistence solution is making sure to keep the
	 * order of Items inside the Array when saving and loading.
	 * </p>
	 * 
	 * @param $key
	 *            The key under which the value is stored.
	 * @param $values
	 *            The <code>long</code> array that is to be stored.
	 */
	void putLongs(String $key, long $values[]);

	/**
	 * Stores a <code>String</code> value under the given key into the
	 * persistance layer or updates it, if it is already present.
	 * 
	 * @param $key
	 *            The key under which the value is stored.
	 * @param $value
	 *            The <code>String</code> value that is to be stored.
	 */
	void putString(String $key, String $value);

	/**
	 * Stores a <code>String</code> array under the given key into the
	 * persistance layer or updates it, if it is already present.
	 * <p>
	 * Note: The underlying persistence solution is making sure to keep the
	 * order of Items inside the Array when saving and loading.
	 * </p>
	 * 
	 * @param $key
	 *            The key under which the value is stored.
	 * @param $values
	 *            The <code>String</code> array that is to be stored.
	 */
	void putStrings(String $key, String $values[]);

	/**
	 * Returns <code>true</code> if this scope provides a value for the given
	 * key or <code>false</code> otherwise.
	 * 
	 * @param $key
	 *            the ID of the key to look for.
	 * @return <code>true</code> if this scope provides a value for the given
	 *         key or <code>false</code> otherwise.
	 */
	boolean hasKey(String $key);
}
