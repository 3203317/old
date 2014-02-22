package net.foreworld.utils.rcp.core.persist;

import java.util.List;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IWebSite extends IParent {

	/**
	 * 
	 * @param $user
	 * @param $position
	 * @param $after
	 */
	void addUser(IUser $user, IUser $position, boolean $after);

	/**
	 * 
	 * @return
	 */
	List<IUser> getUsers();

	/**
	 * 
	 * @return
	 */
	IUser getLastLoginUser();

	/**
	 * 
	 * @param $user
	 */
	void setLastLoginUser(IUser $user);
}
