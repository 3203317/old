package net.foreworld.utils.rcp.core.persist;

/**
 * 
 * @author huangxin
 * @email huangxin@foreworld.net
 * 
 */
public interface IFilterAction extends IPersistable {

	/**
	 * @return the ID of an action to be performed.
	 */
	String getActionId();

	/**
	 * @param $data
	 *            arbitrary data for the action being performed.
	 */
	void setData(Object $data);

	/**
	 * @return the arbitrary data for the action being performed.
	 */
	Object getData();
}
