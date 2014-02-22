package net.foreworld.utils.rcp.core.persist.dao;

import net.foreworld.utils.rcp.core.persist.IUser;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IUserDAO extends IEntityDAO<IUser> {

	/**
	 * 
	 * @param $user
	 */
	void login(IUser $user);

	/**
	 * 
	 * @param $user
	 */
	void logout(IUser $user);

}
