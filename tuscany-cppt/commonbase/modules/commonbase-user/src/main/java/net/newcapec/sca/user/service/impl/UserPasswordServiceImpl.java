package net.newcapec.sca.user.service.impl;

/**
 * <p>Title: DAS实现 </p>
 * <p>Description:查询浏览列表用户自定义配置数据访问构件实现</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-25
 * 修改日期：
 * 修改人：
 * 复审人：
 */

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;
import net.newcapec.sca.user.UserBaseInfo;
import net.newcapec.sca.user.das.UserPasswordDAS;
import net.newcapec.sca.user.service.UserPasswordService;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserPasswordServiceImpl implements UserPasswordService {

	private static final Logger _log = Logger
	.getLogger(UserPasswordServiceImpl.class);

	private UserPasswordDAS  userPasswordDAS;

	@Reference(name = "userPasswordDAS", required = true)
	public void setUserPasswordDAS(UserPasswordDAS userPasswordDAS) {
		this.userPasswordDAS = userPasswordDAS;
	}

	private SessionService sessionService;
	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	/**
	 * @Title:resetPassword
	 * @Description:重置用户密码
	 * @return  UserBaseInfo
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-25
	 * 修改日期： 修改人： 复审人：
	 */

	public UserBaseInfo resetPassword(String sessionId,
			UserBaseInfo userBaseInfo) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		//设置当前用户account_id
		userBaseInfo.setAccount_id(session.getUser_code());
		//重置用户密码
		userPasswordDAS.resetPassword(userBaseInfo);
		return userBaseInfo;
	}


	/**
	 * @Title:resetPassword
	 * @Description:修改用户密码
	 * @return  UserBaseInfo
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-25
	 * 修改日期： 修改人： 复审人：
	 */
	public UserBaseInfo updatePassword(String sessionId,
			UserBaseInfo userBaseInfo) {

		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("User:session不可用");
			return null;
		}
		//  获得原密码
		String _password = "";
		//设置当前用户account_id
		userBaseInfo.setAccount_id(session.getUser_code());

		UserBaseInfo _userBaseInfo = new UserBaseInfo();
		_userBaseInfo.setName(userBaseInfo.getName());
		_userBaseInfo.setAccount_id(session.getUser_code());

		userPasswordDAS.getPassword(_userBaseInfo);

	    _password = _userBaseInfo.getOpassword();

	    if (_userBaseInfo.getResultMsg().getStatus() == 0){//获取原密码失败
	    	return  _userBaseInfo;
	    }

	    ResultMsg __resultMsg = new ResultMsg();
	    Boolean  updateSuccess = false;
		//  判断原密码是否正确，不正确不能修改密码
		if  (!userBaseInfo.getOpassword().equals(_password)){
			 __resultMsg.setStatus(-1);
			 __resultMsg.setErrorId(102);
			 __resultMsg.setErrorMsg("原密码不正确，请重新输入！");
			 _log.info("user:updatePassword");
			 _log.error("原密码不正确，请重新输入！");

		}else{
			updateSuccess = userPasswordDAS.updatePassword(userBaseInfo);
			if (updateSuccess){
			    __resultMsg.setStatus(1);
			}else{
				__resultMsg.setStatus(0);
			}

		}
		userBaseInfo.setResultMsg(__resultMsg);
		return userBaseInfo;


	}


	public UserBaseInfo encodePassword(UserBaseInfo userBaseInfo) {
		String  rawOldPass,md5OldPass,rawNewPass,md5NewPass;
		rawOldPass = userBaseInfo.getOpassword() ;
		rawNewPass = userBaseInfo.getPassword();
		md5OldPass = md5EncodePassword(rawOldPass);
		md5NewPass = md5EncodePassword(rawNewPass);
		userBaseInfo.setOpassword(md5OldPass);
		userBaseInfo.setPassword(md5NewPass);
		return userBaseInfo;
	}

	public  String  md5EncodePassword(String  rawPass ){

		String  md5Pass;
		String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
		MessageDigest messageDigest = getMessageDigest();
		byte[] digest;
		try {
			digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 not supported!");
		}
		md5Pass =new String(Hex.encodeHex(digest));
		return md5Pass;

	}



	protected final MessageDigest getMessageDigest() {
		String algorithm = "MD5";
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("No such algorithm ["
					+ algorithm + "]");
		}
	}

	protected String mergePasswordAndSalt(String password, Object salt,
			boolean strict) {
		if (password == null) {
			password = "";
		}
		if (strict && (salt != null)) {
			if ((salt.toString().lastIndexOf("{") != -1)
					|| (salt.toString().lastIndexOf("}") != -1)) {
				throw new IllegalArgumentException(
						"Cannot use { or } in salt.toString()");
			}
		}
		if ((salt == null) || "".equals(salt)) {
			return password;
		} else {
			return password + "{" + salt.toString() + "}";
		}
	}

	private String salt = "PONY";

	/**
	 * @isSessionVaild
	 * @Description:验证session是否有效
	 * @return  boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-27
	 * 修改日期： 修改人： 复审人：
	 */
	protected boolean isSessionVaild(Session session)
	{
		boolean sessionVaild = true;

		try
		{
			if (null == session.getId() && session.getState() == 0)
			{
				sessionVaild = false;
			}
		}
		catch (Exception e)
		{
			_log.error("User:session验证失败");
			sessionVaild = false;
		}
		return sessionVaild;
	}


}
