package net.foreworld.utils.rcp.core.persist;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IReparentable<T> {

	/**
	 * Set the new parent to the Object. Some implementations of this interface
	 * may be stricter and not accept <code>null</code>. If that's the case,
	 * they should specify that in their documentation.
	 * 
	 * @param $newparent
	 *            the new parent of the Object to set.
	 */
	public void setParent(T $newparent);
}
