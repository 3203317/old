package com.myfvn.core.persist;

import java.util.List;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IUser extends net.foreworld.utils.rcp.core.persist.IUser {

	/**
	 * 
	 * @param $emailbox
	 * @param $position
	 * @param $after
	 */
	void addEmailbox(IEmailbox $emailbox, IEmailbox $position, boolean $after);

	/**
	 * 
	 * @return
	 */
	List<IEmailbox> getEmailboxs();

	/**
	 * 
	 * @param $preference
	 * @param $position
	 * @param $after
	 */
	void addPreference(IPreference $preference, IPreference $position, boolean $after);

	/**
	 * 
	 * @return
	 */
	List<IPreference> getPreference();

}
