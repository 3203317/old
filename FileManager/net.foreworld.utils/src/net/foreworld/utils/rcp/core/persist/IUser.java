package net.foreworld.utils.rcp.core.persist;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IUser extends IParent {

	/**
	 * 
	 * @param $user_code
	 */
	void setUser_code(String $user_code);

	/**
	 * 
	 * @return
	 */
	String getUser_code();

	/**
	 * 
	 * @param $password
	 */
	void setPassword(String $password);

	/**
	 * 
	 * @return
	 */
	String getPassword();

	/**
	 * 
	 * @param $lpassword
	 */
	void setLPassword(String $lpassword);

	/**
	 * 
	 * @return
	 */
	String getLPassword();

	/**
	 * 
	 * @param $realname
	 */
	void setRealname(String $realname);

	/**
	 * 
	 * @return
	 */
	String getRealname();

	/**
	 * 
	 * @param $sex
	 */
	void setSex(int $sex);

	/**
	 * 
	 * @return
	 */
	int getSex();

	/**
	 * 
	 */
	void login();

	/**
	 * 
	 */
	void setRememberPassword();

	/**
	 * 
	 */
	void setAutoLogin();

	/**
	 * 
	 */
	void logout();

	/**
	 * 
	 */
	void changePassword();

	/**
	 * 
	 * @param $last_logintime
	 */
	void setLast_logintime(String $last_logintime);

	/**
	 * 
	 * @return
	 */
	String getLast_logintime();

	/**
	 * 
	 * @param $last_logouttime
	 */
	void setLast_logouttime(String $last_logouttime);

	/**
	 * 
	 * @return
	 */
	String getLast_logouttime();

	/**
	 * 
	 * @param $isrememberpassword
	 */
	void setIsrememberpassword(int $isrememberpassword);

	/**
	 * 
	 * @return
	 */
	int getIsrememberpassword();

	/**
	 * 
	 * @param $isautologin
	 */
	void setIsautologin(int $isautologin);

	/**
	 * 
	 * @return
	 */
	int getIsautologin();

}
