package net.newcapec.sca.login.service;

import net.newcapec.sca.login.Token;
import net.newcapec.sca.user.UserBaseInfo;

import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
@Remotable
public interface LoginService {
	Token loginVerify(UserBaseInfo userBaseInfo);
}
