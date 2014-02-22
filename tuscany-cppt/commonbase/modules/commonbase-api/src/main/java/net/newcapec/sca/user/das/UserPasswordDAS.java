package net.newcapec.sca.user.das;

import net.newcapec.sca.user.UserBaseInfo;

import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
public interface UserPasswordDAS {

	UserBaseInfo resetPassword(UserBaseInfo userBaseInfo);

	Boolean updatePassword(UserBaseInfo userBaseInfo);

	UserBaseInfo getPassword(UserBaseInfo userBaseInfo);
	public UserBaseInfo getLoginPassword(UserBaseInfo userBaseInfo);

}
