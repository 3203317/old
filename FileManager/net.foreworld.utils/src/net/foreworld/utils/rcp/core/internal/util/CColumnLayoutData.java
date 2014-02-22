package net.foreworld.utils.rcp.core.internal.util;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class CColumnLayoutData {

	/** Size hint for Columns */
	public enum Size {

		/** Column should Fill */
		FILL,

		/** Column has fixed Width */
		FIXED;
	}

	/** The Default Width-Hint */
	public static final int DEFAULT = -1;

	private Size _size;
	private int _wHint;
	private boolean _hidden;

	/**
	 * @param $size
	 * @param $wHint
	 */
	public CColumnLayoutData(Size $size, int $wHint) {
		_size = $size;
		_wHint = $wHint;
	}

	/**
	 * @return Returns the align.
	 */
	public Size getSize() {
		return _size;
	}

	/**
	 * @return Returns the wHint.
	 */
	public int getWidthHint() {
		return _wHint;
	}

	/**
	 * @param $hidden
	 *            TRUE if the column should be hidden.
	 */
	public void setHidden(boolean $hidden) {
		_hidden = $hidden;
	}

	/**
	 * @return Returns TRUE if the column should be hidden.
	 */
	public boolean isHidden() {
		return _hidden;
	}
}
