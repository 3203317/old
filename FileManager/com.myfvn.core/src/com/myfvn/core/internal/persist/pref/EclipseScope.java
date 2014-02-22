package com.myfvn.core.internal.persist.pref;

import java.util.logging.Logger;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.myfvn.core.persist.pref.IPreferenceScope;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class EclipseScope implements IPreferenceScope {
	final Logger _logger = Logger.getLogger(EclipseScope.class.getName());

	private static final String NODE_SEPARATOR = "/"; //$NON-NLS-1$
	private static final String ROOT_NAME = NODE_SEPARATOR;

	private final IPreferenceScope _parent;
	private final IPreferencesService _prefService;
	private final IEclipsePreferences _rootNode;

	public EclipseScope(IPreferenceScope $parent) {
		_parent = $parent;
		_prefService = Platform.getPreferencesService();
		_rootNode = _prefService.getRootNode();
	}

	public void delete(String $key) {
		throw new UnsupportedOperationException();
	}

	public void flush() {
		try {
			_rootNode.flush();
		} catch (BackingStoreException $ex) {
			_logger.warning($ex.getMessage());
		}
	}

	public boolean getBoolean(String $key) {
		return _prefService.getBoolean(ROOT_NAME, $key, _parent.getBoolean($key), null);
	}

	public int getInteger(String $key) {
		return _prefService.getInt(ROOT_NAME, $key, _parent.getInteger($key), null);
	}

	public int[] getIntegers(String $key) {
		throw new UnsupportedOperationException();
	}

	public long getLong(String $key) {
		return _prefService.getLong(ROOT_NAME, $key, _parent.getLong($key), null);
	}

	public long[] getLongs(String $key) {
		throw new UnsupportedOperationException();
	}

	public IPreferenceScope getParent() {
		return _parent;
	}

	public String getString(String $key) {
		return _prefService.getString(ROOT_NAME, $key, _parent.getString($key), null);
	}

	public String[] getStrings(String $key) {
		throw new UnsupportedOperationException();
	}

	public boolean hasKey(String $key) {
		throw new UnsupportedOperationException();
	}

	public void putBoolean(String $key, boolean $value) {
		Assert.isTrue($key.contains(NODE_SEPARATOR), "Invalid Eclipse Preferences Key!"); //$NON-NLS-1$

		String __nodePath = getNodePath($key);
		$key = $key.substring($key.lastIndexOf(NODE_SEPARATOR) + 1);

		Preferences __prefNode = _rootNode.node(__nodePath);
		__prefNode.putBoolean($key, $value);
	}

	public void putInteger(String $key, int $value) {
		Assert.isTrue($key.contains(NODE_SEPARATOR), "Invalid Eclipse Preferences Key!"); //$NON-NLS-1$

		String __nodePath = getNodePath($key);
		$key = $key.substring($key.lastIndexOf(NODE_SEPARATOR) + 1);

		Preferences __prefNode = _rootNode.node(__nodePath);
		__prefNode.putInt($key, $value);
	}

	public void putIntegers(String $key, int[] $values) {
		throw new UnsupportedOperationException();
	}

	public void putLong(String $key, long $value) {
		Assert.isTrue($key.contains(NODE_SEPARATOR), "Invalid Eclipse Preferences Key!"); //$NON-NLS-1$

		String __nodePath = getNodePath($key);
		$key = $key.substring($key.lastIndexOf(NODE_SEPARATOR) + 1);

		Preferences __prefNode = _rootNode.node(__nodePath);
		__prefNode.putLong($key, $value);
	}

	public void putLongs(String $key, long[] $values) {
		throw new UnsupportedOperationException();
	}

	public void putString(String $key, String $value) {
		Assert.isTrue($key.contains(NODE_SEPARATOR), "Invalid Eclipse Preferences Key!"); //$NON-NLS-1$

		String __nodePath = getNodePath($key);
		$key = $key.substring($key.lastIndexOf(NODE_SEPARATOR) + 1);

		Preferences __prefNode = _rootNode.node(__nodePath);
		__prefNode.put($key, $value);
	}

	public void putStrings(String $key, String[] $values) {
		throw new UnsupportedOperationException();
	}

	private String getNodePath(String $key) {
		if ($key.startsWith("/")) //$NON-NLS-1$
			return $key.substring(0, $key.lastIndexOf(NODE_SEPARATOR));

		return ROOT_NAME + $key.substring(0, $key.lastIndexOf(NODE_SEPARATOR));
	}

	public void save() {

	}

}
