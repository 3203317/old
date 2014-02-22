package net.newcapec.sca.login.service.impl;

import net.newcapec.sca.login.Token;
import net.newcapec.sca.login.service.LoginService;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;
import net.newcapec.sca.user.UserBaseInfo;
import net.newcapec.sca.user.das.UserPasswordDAS;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class LoginServiceImpl implements LoginService {

	private static final Logger _log = Logger.getLogger(LoginServiceImpl.class);

	private UserPasswordDAS userPasswordDAS;

	@Reference(name = "userPasswordDAS", required = true)
	public void setUserPasswordDAS(UserPasswordDAS userPasswordDAS) {
		this.userPasswordDAS = userPasswordDAS;
	}

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public Token loginVerify(UserBaseInfo userBaseInfo) {
		_log.info("start...");

		String __password = userBaseInfo.getPassword();
		System.out.println("inputpwd:"+__password);
		userBaseInfo.setPassword("");

		UserBaseInfo __userBaseInfo = null;
		Token __token = new Token();
		ResultMsg __resultMsg = new ResultMsg();
		try
		{
		__userBaseInfo = this.userPasswordDAS.getLoginPassword(userBaseInfo);
		}
		catch (Exception e)
		{
			_log.debug(e.getMessage(),e);
			if(e.getMessage().indexOf("The Network Adapter could not establish the connection")>0||e.getMessage().indexOf("No route to host: connect")>0)
			{
				__resultMsg.setErrorMsg("无法连接到数据库！请联系管理员。");
			}
			__token.setResultMsg(__resultMsg);
			return __token;
		}

		/* 登陆不成功 */
		if (__userBaseInfo.getResultMsg() != null) {
			__token.setResultMsg(__userBaseInfo.getResultMsg());
			return __token;
		}
		System.out.println("obtain:"+__userBaseInfo.getPassword());

		/* 密码不正确 */
		if(!__password.equals(__userBaseInfo.getPassword())){
			/* 设置操作结果消息 */

			__resultMsg.setErrorId(103);
//			__resultMsg.setErrorMsg("user password is err");
			__resultMsg.setErrorMsg("用户名或密码不正确！");
			userBaseInfo.setResultMsg(__resultMsg);

			__token.setResultMsg(userBaseInfo.getResultMsg());
			return __token;
		}

		_log.debug("+++"+ __userBaseInfo.getCode());

		/* 创建Session */
		Session __session = new Session();
		__session.setUser_code(__userBaseInfo.getAccount_id());
		__session.setDomain_code("1");
		__session.setUnit_code("1");

		/* 服务返回session */
		__session = this.sessionService.createSession(__session);
		__token.setSessionId(__session.getId());

		_log.debug("session: " + __token.getSessionId());

		return __token;
	}

}
