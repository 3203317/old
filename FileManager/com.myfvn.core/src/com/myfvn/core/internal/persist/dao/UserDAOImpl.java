package com.myfvn.core.internal.persist.dao;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import net.foreworld.java.exceptions.ServiceException;
import net.foreworld.java.services.IService;
import net.foreworld.java.services.ServiceFactory;
import net.foreworld.java.sysmanage.sysmanage_user.Sysmanage_user;
import net.foreworld.utils.rcp.core.internal.persist.dao.AbstractEntityDAO;
import net.foreworld.utils.rcp.core.persist.EntityState;
import net.foreworld.utils.rcp.core.persist.IUser;
import net.foreworld.utils.rcp.core.persist.dao.IUserDAO;
import net.foreworld.utils.rcp.core.persist.events.UserEvent;
import net.foreworld.utils.rcp.core.persist.events.runnables.UserEventType;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

import org.eclipse.core.runtime.Assert;

import com.myfvn.core.internal.ApplicationServer;
import com.myfvn.core.internal.persist.User;
import com.myfvn.core.internal.persist.service.DBHelper;
import com.myfvn.core.persist.event.UserEventManager;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class UserDAOImpl extends AbstractEntityDAO<IUser> implements IUserDAO {
	final Logger _logger = Logger.getLogger(UserDAOImpl.class.getName());

	public UserDAOImpl() {
		super(User.class);
	}

	public void login(IUser $user) {
		Assert.isNotNull($user, "Exception User is Null"); //$NON-NLS-1$

		if (ApplicationServer.getDefault().getLoginMode() == ApplicationServer.LoginMode.REMOTE) {
			remoteLogin($user);
		} else {
			localLogin($user);
		}
	}

	private void localLogin(IUser $user) {
		UserEvent __event = new UserEvent();

		IUser __user = DBHelper.getDefault().findLocalUser($user.getName(), $user.getLPassword());

		/* 本地不存在该用户 */
		if (__user == null) {
			__event.setMessage(Messages.UserDAOImpl_USER_LOGIN_FAIL);
			__event.setUserEventType(UserEventType.LOGIN_FAIL);
		} else {/* 本地存在该用户 */
			__user.setIsautologin($user.getIsautologin());
			__user.setIsrememberpassword($user.getIsrememberpassword());
			this.loginSettings(__user);
			__event.setUser_uuid(__user.getUuid());
			__event.setUserEventType(UserEventType.LOGIN_SUCCESS);
		}

		UserEventManager.getDefault().fireEvents(__event);
	}

	private void remoteLogin(IUser $user) {
		UserEvent __event = new UserEvent();
		String __user_info = DBHelper.getDefault().findRemoteUser($user.getName(), $user.getPassword());

		/* 网络问题 */
		if ("".equals(__user_info)) {
			__event.setMessage(Messages.UserDAOImpl_USER_LOGIN_FAIL_NETWORK_ERROR);
			__event.setUserEventType(UserEventType.LOGIN_FAIL_NETWORK_ERROR);
			UserEventManager.getDefault().fireEvents(__event);
			return;
		}

		/* 未注册 或注册了 邮箱未激活 */
		if (!DBHelper.getDefault().findRemoteOpt(__user_info)) {
			__event.setMessage(DBHelper.getDefault().findRemoteMsg(__user_info));
			__event.setUserEventType(UserEventType.LOGIN_FAIL_NETWORK);
			UserEventManager.getDefault().fireEvents(__event);
			return;
		}

		/* 根据用户名获取本地用户对象 */
		IUser __user = DBHelper.getDefault().findLocalUser($user.getName());
		Sysmanage_user __sysmanage_user = DBHelper.getDefault().findRemoteUser(__user_info);

		/* 本地已经存在该用户 ,并更新本地用户表相关数据 */
		if (__user != null) {
			__user.setIsautologin($user.getIsautologin());
			__user.setIsrememberpassword($user.getIsrememberpassword());
		} else {/* 本地不存在该用户 ，用户写入本地用户表 */
			/* 写入本地数据库 */
			DBHelper.getDefault().addSysmanage_user(__sysmanage_user);

			/* 用户对象写入内存 */
			__user = new User();
			__user.setName(__sysmanage_user.getUser_name());
			__user.setState(EntityState.NORMAL);
			__user.setUuid(__sysmanage_user.getUuid());
			__user.setParent_uuid("ff196109-e12a-4904-966d-a30cc35110ae");//$NON-NLS-1$
			__user.setPassword(__sysmanage_user.getPassword());
			__user.setLPassword(__sysmanage_user.getLpassword());
			__user.setRealname(__sysmanage_user.getRealname());
			__user.setLast_logintime(__sysmanage_user.getLast_logintime());
			__user.setLast_logouttime(__sysmanage_user.getLast_logouttime());
			__user.setIsrememberpassword(__sysmanage_user.getIsrememberpassword());
			__user.setIsautologin(__sysmanage_user.getIsautologin());
			DBHelper.getDefault().addUser((User) __user);
		}

		this.loginSettings(__user);
		__event.setUser_uuid(__user.getUuid());
		__event.setUserEventType(UserEventType.LOGIN_SUCCESS);
		UserEventManager.getDefault().fireEvents(__event);
	}

	public boolean exists(String $uuid) throws PersistenceException {
		return false;
	}

	public IUser load(String $uuid) throws PersistenceException {
		return null;
	}

	public long countAll() throws PersistenceException {
		return 0;
	}

	public void delete(IUser $persistable) throws PersistenceException {

	}

	public void deleteAll(Collection<IUser> $persistables) throws PersistenceException {

	}

	public Class<? extends IUser> getEntityClass() {
		return null;
	}

	public Collection<IUser> loadAll() throws PersistenceException {
		return null;
	}

	public IUser save(IUser $persistable) throws PersistenceException {
		return null;
	}

	public void saveAll(Collection<IUser> $persistables) throws PersistenceException {

	}

	public void logout(IUser $user) {

		this.logoutSettings($user);
	}

	private void logoutSettings(IUser $user) {

	}

	/**
	 * 设置最后一个登陆系统的用户
	 * 
	 * @param $user
	 */
	private void loginSettings(IUser $user) {
		IService __service = ServiceFactory.getInstance().getService(ServiceFactory.SYSMANAGE_USERSERVICE);

		String __last_logintime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

		Sysmanage_user __sysmanage_user = new Sysmanage_user();
		__sysmanage_user.setId($user.getId());
		__sysmanage_user.setLast_logintime(__last_logintime);//$NON-NLS-1$
		__sysmanage_user.setIsautologin($user.getIsautologin());
		__sysmanage_user.setIsrememberpassword($user.getIsrememberpassword());

		$user.setLast_logintime(__last_logintime);
		DBHelper.getDefault().setLastLoginUser();

		try {
			__service.update(__sysmanage_user, null);
		} catch (ServiceException $ex) {
			_logger.warning($ex.getMessage());
		}
	}
}
