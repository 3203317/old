package com.myfvn.ui;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class ShareProvider {
	private static final String URL_INPUT_TOKEN = "[L]"; //$NON-NLS-1$
	private static final String TITLE_INPUT_TOKEN = "[T]"; //$NON-NLS-1$

	private final String _id;
	private final String _pluginId;
	private final int _index;
	private final String _name;
	private final String _iconPath;
	private final String _url;
	private final int _maxTitleLength;
	private boolean _enabled;

	public ShareProvider(String $id, String $pluginId, int $index, String $name, String $iconPath, String $url, String $maxTitleLength, boolean $enabled) {
		_id = $id;
		_pluginId = $pluginId;
		_index = $index;
		_name = $name;
		_iconPath = $iconPath;
		_url = $url;
		_enabled = $enabled;

		if ($maxTitleLength != null)
			_maxTitleLength = Integer.parseInt($maxTitleLength);
		else
			_maxTitleLength = Integer.MAX_VALUE;
	}

	/**
	 * @return the unique id of the contributed provider.
	 */
	public String getId() {
		return _id;
	}

	/**
	 * @return the id of the plugin that contributes this provider.
	 */
	public String getPluginId() {
		return _pluginId;
	}

	/**
	 * @return the index of the provider used for sorting.
	 */
	public int getIndex() {
		return _index;
	}

	/**
	 * @return the name of the provider.
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @return the path to an icon of the provider.
	 */
	public String getIconPath() {
		return _iconPath;
	}

	/**
	 * @param $enabled
	 *            <code>true</code> if this provider is enabled and
	 *            <code>false</code> otherwise.
	 */
	public void setEnabled(boolean $enabled) {
		_enabled = $enabled;
	}

	/**
	 * @return <code>true</code> if this provider is enabled and
	 *         <code>false</code> otherwise.
	 */
	public boolean isEnabled() {
		return _enabled;
	}

	@Override
	public int hashCode() {
		final int __prime = 31;
		int __result = 1;
		__result = __prime * __result + ((_id == null) ? 0 : _id.hashCode());
		return __result;
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object $object) {
		if (this == $object)
			return true;

		if ($object == null)
			return false;

		if (getClass() != $object.getClass())
			return false;

		ShareProvider __other = (ShareProvider) $object;
		if (_id == null) {
			if (__other._id != null)
				return false;
		} else if (!_id.equals(__other._id))
			return false;

		return true;
	}
}
