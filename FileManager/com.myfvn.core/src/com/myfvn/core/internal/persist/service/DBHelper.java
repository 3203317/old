package com.myfvn.core.internal.persist.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import net.foreworld.java.exceptions.ServiceException;
import net.foreworld.java.mail.mail_mailaccountt.Mail_mailaccountt;
import net.foreworld.java.mail.mail_mailfilee.Mail_mailfilee;
import net.foreworld.java.mail.mail_maill.Mail_maill;
import net.foreworld.java.models.MapResultModel;
import net.foreworld.java.services.IService;
import net.foreworld.java.services.ServiceFactory;
import net.foreworld.java.sysmanage.sysmanage_user.Sysmanage_user;
import net.foreworld.util.md5.MD5;
import net.foreworld.utils.HttpRequestProxy;
import net.foreworld.utils.Utils;
import net.foreworld.utils.rcp.core.persist.EntityState;
import net.foreworld.utils.rcp.core.persist.IChild;
import net.foreworld.utils.rcp.core.persist.IEntity;
import net.foreworld.utils.rcp.core.persist.IParent;
import net.foreworld.utils.rcp.core.persist.IUser;
import net.foreworld.utils.rcp.core.persist.IWebSite;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.eclipse.core.runtime.Assert;

import com.myfvn.core.internal.persist.Attachment;
import com.myfvn.core.internal.persist.Email;
import com.myfvn.core.internal.persist.EmailBox;
import com.myfvn.core.internal.persist.User;
import com.myfvn.core.internal.persist.WebSite;
import com.myfvn.core.persist.IAttachment;
import com.myfvn.core.persist.IEmail;
import com.myfvn.core.persist.reference.UserReference;
import com.myfvn.core.persist.reference.WebSiteReference;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class DBHelper {
	final Logger _logger = Logger.getLogger(DBHelper.class.getName());

	private static DBHelper _INSTANCE = new DBHelper();
	private HashMap<String, IEntity> _objectIndex = new HashMap<String, IEntity>();

	private DBHelper() {
	}

	public static DBHelper getDefault() {
		return _INSTANCE;
	}

	private volatile boolean _started = false;

	public void init() {
		if (!this._started) {
			this._started = true;

			this.loadWebSite();
			this.loadAllUser();
			this.setLastLoginUser();
		}
	}

	public synchronized void loadRestAll() {

		this.loadAllMailboxs();

		this.loadAllEmails();

		this.loadAllAttachments();
	}

	private void loadWebSite() {
		WebSite __webSite = this.createWebSite();
		this._objectIndex.put("ff196109-e12a-4904-966d-a30cc35110ae", __webSite);//$NON-NLS-1$
	}

	@SuppressWarnings("unchecked")
	private void loadAllUser() {
		IService __service = ServiceFactory.getInstance().getService(ServiceFactory.SYSMANAGE_USERSERVICE);
		Sysmanage_user __sysmanage_user = new Sysmanage_user();
		List<MapResultModel> __list = null;

		try {
			__list = __service.select(__sysmanage_user, null);
		} catch (ServiceException $ex) {
			this._logger.warning($ex.getMessage());
		}

		for (MapResultModel ___model : __list) {
			User ____user = this.createUser(___model);
			this.addUser(____user);
		}
	}

	public void addUser(User $user) {
		this._objectIndex.put($user.getUuid(), $user);
		this.relation((IParent) this.getEntity("ff196109-e12a-4904-966d-a30cc35110ae"), $user);
	}

	/**
	 * 往数据库中添加一条 Sysmanage_user
	 * 
	 * @param $sysmanage_user
	 */
	public void addSysmanage_user(Sysmanage_user $sysmanage_user) {
		IService __service = ServiceFactory.getInstance().getService(ServiceFactory.SYSMANAGE_USERSERVICE);
		try {
			__service.insert($sysmanage_user, null);
		} catch (ServiceException $ex) {
			$ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadAllMailboxs() {
		IService __service = ServiceFactory.getInstance().getService(ServiceFactory.MAIL_MAILACCOUNTTSERVICE);
		IUser __user = new WebSiteReference().resolve().getLastLoginUser();
		Mail_mailaccountt __mail_mailaccountt = new Mail_mailaccountt();
		__mail_mailaccountt.setTab_sysmanage_user_id(__user.getId());

		List<MapResultModel> __list = null;

		try {
			__list = __service.select(__mail_mailaccountt, null);
		} catch (ServiceException $ex) {
			_logger.warning($ex.getMessage());
		}

		for (MapResultModel ___model : __list) {
			EmailBox ____emailbox = this.createEmailbox(___model);
			this._objectIndex.put(____emailbox.getUuid(), ____emailbox);
			this.relation((IParent) this.getEntity(__user.getUuid()), ____emailbox);
		}
	}

	@SuppressWarnings("unchecked")
	private void loadAllEmails() {
		IService __service = ServiceFactory.getInstance().getService(ServiceFactory.MAIL_MAILLSERVICE);
		Mail_maill __mail_maill = new Mail_maill();

		List<MapResultModel> __list = null;

		try {
			__list = __service.select(__mail_maill, null);
		} catch (ServiceException $ex) {
			_logger.warning($ex.getMessage());
		}

		for (MapResultModel ___model : __list) {
			Email ____email = this.createEmail(___model);
			this._objectIndex.put(____email.getUuid(), ____email);
		}
		for (MapResultModel ___model : __list) {
			IEmail ____email = (IEmail) this.getEntity(___model.get("uuid").toString().trim());//$NON-NLS-1$
			this.relation((IParent) this.getEntity(____email.getParent_uuid()), ____email);
		}

	}

	@SuppressWarnings("unchecked")
	private void loadAllAttachments() {
		IService __service = ServiceFactory.getInstance().getService(ServiceFactory.MAIL_MAILFILEESERVICE);
		Mail_mailfilee __mail_mailfilee = new Mail_mailfilee();

		List<MapResultModel> __list = null;

		try {
			__list = __service.select(__mail_mailfilee, null);
		} catch (ServiceException $ex) {
			_logger.warning($ex.getMessage());
		}

		for (MapResultModel ___model : __list) {
			Attachment ____attachment = this.createAttachment(___model);
			this._objectIndex.put(____attachment.getUuid(), ____attachment);
		}
		for (MapResultModel ___model : __list) {
			IAttachment ____attachment = (IAttachment) this.getEntity(___model.get("uuid").toString().trim());//$NON-NLS-1$
			this.relation((IParent) this.getEntity(____attachment.getParent_uuid()), ____attachment);
		}

	}

	private WebSite createWebSite() {
		WebSite __webSite = new WebSite();
		__webSite.setId(1);
		__webSite.setName("http://www.foreworld.net");//$NON-NLS-1$
		__webSite.setParent(null);
		__webSite.setState(EntityState.NORMAL);
		__webSite.setUuid("ff196109-e12a-4904-966d-a30cc35110ae");//$NON-NLS-1$
		return __webSite;
	}

	public synchronized IEntity getEntity(String $uuid) {
		return this._objectIndex.get($uuid);
	}

	public synchronized WebSite getWebSite(String $uuid) {
		IEntity __entity = this._objectIndex.get($uuid);
		if (__entity instanceof WebSite) {
			return (WebSite) __entity;
		}
		return null;
	}

	public synchronized User getUser(String $uuid) {
		IEntity __entity = this._objectIndex.get($uuid);
		if (__entity instanceof User) {
			return (User) __entity;
		}
		return null;
	}

	/**
	 * 查找本地用户
	 * 
	 * @param $user_name
	 *            用户名
	 * @return 用户对象
	 */
	@SuppressWarnings("unchecked")
	public synchronized IUser findLocalUser(String $user_name) {
		IService __service = ServiceFactory.getInstance().getService(ServiceFactory.SYSMANAGE_USERSERVICE);
		Sysmanage_user __sysmanage_user = new Sysmanage_user();
		__sysmanage_user.setUser_name($user_name);

		List<MapResultModel> __list = null;

		try {
			__list = __service.select(__sysmanage_user, null);
		} catch (ServiceException $ex) {
			$ex.printStackTrace();
			return null;
		}

		/* 没有找到该用户名 则返回 */
		if (__list.size() == 1) {
			MapResultModel __model_3 = __list.get(0);
			return this.getUser(__model_3.get("uuid").toString().trim());
		}
		return null;
	}

	/**
	 * 查找本地库用户
	 * 
	 * @param $user_name
	 *            用户名
	 * @param $password
	 *            密码
	 * @return IUser
	 */
	@SuppressWarnings("unchecked")
	public synchronized IUser findLocalUser(String $user_name, String $lpassword) {
		IService __service = ServiceFactory.getInstance().getService(ServiceFactory.SYSMANAGE_USERSERVICE);
		Sysmanage_user __sysmanage_user = new Sysmanage_user();
		__sysmanage_user.setUser_name($user_name);
		__sysmanage_user.setLpassword($lpassword);

		List<MapResultModel> __list = null;

		try {
			__list = __service.select(__sysmanage_user, null);
		} catch (ServiceException $ex) {
			$ex.printStackTrace();
			return null;
		}

		/* 没有找到该用户名 则返回 */
		if (__list.size() == 1) {
			MapResultModel __model_3 = __list.get(0);
			return this.getUser(__model_3.get("uuid").toString().trim());
		}
		return null;
	}

	/**
	 * 查找远程服务器用户
	 * 
	 * @param $user_name
	 *            用户名
	 * @param $password
	 *            密码
	 * @return json
	 */
	public String findRemoteUser(String $user_name, String $password) {
		Map<String, String> __params = new HashMap<String, String>();
		__params.put("model.user_name", $user_name);
		__params.put("model.password", MD5.getMD5($password));
		return HttpRequestProxy.doPost("http://www.foreworld.net/ext/fvnservice/fvn_login.asp", __params, "UTF-8");
	}

	/**
	 * 获取远程服务器用户对象
	 * 
	 * @param $jsonStr
	 * @return Sysmanage_user
	 */
	public Sysmanage_user findRemoteUser(String $jsonStr) {
		JSONObject __jsonObject = JSONObject.fromObject($jsonStr);
		JSONArray __jsonArray = JSONArray.fromObject(__jsonObject.get("rows"));
		Object[] __arrays = __jsonArray.toArray();
		JSONObject __jsonObject_1 = JSONObject.fromObject(__arrays[0].toString());
		JSONArray __jsonArray_1 = JSONArray.fromObject(__jsonObject_1.get("data"));

		Sysmanage_user __sysmanage_user = new Sysmanage_user();
		__sysmanage_user.setUser_name(__jsonArray_1.getString(3));
		__sysmanage_user.setPassword(__jsonArray_1.getString(4));
		__sysmanage_user.setUuid(__jsonArray_1.getString(1));
		__sysmanage_user.setLpassword(__jsonArray_1.getString(5));
		__sysmanage_user.setRealname(__jsonArray_1.getString(6));
		__sysmanage_user.setLast_logintime(__jsonArray_1.getString(15));
		__sysmanage_user.setLast_logouttime(__jsonArray_1.getString(16));
		__sysmanage_user.setIsrememberpassword(Integer.parseInt(__jsonArray_1.getString(17)));
		__sysmanage_user.setIsautologin(Integer.parseInt(__jsonArray_1.getString(18)));

		return __sysmanage_user;
	}

	/**
	 * 返回远程操作标记
	 * 
	 * @param $jsonStr
	 *            字符串
	 * @return 操作成功true 操作失败false
	 */
	public boolean findRemoteOpt(String $jsonStr) {
		JSONObject __jsonObject = JSONObject.fromObject($jsonStr);
		return "s".equals(__jsonObject.getString("opt"));
	}

	/**
	 * 返回远程操作消息
	 * 
	 * @param $jsonStr
	 *            字符串
	 * @return 字符串
	 */
	public String findRemoteMsg(String $jsonStr) {
		JSONObject __jsonObject = JSONObject.fromObject($jsonStr);
		return __jsonObject.getString("msg");
	}

	private User createUser(MapResultModel $model) {
		User __user = new User();
		__user.setId(Integer.parseInt($model.get("id").toString()));//$NON-NLS-1$
		__user.setName($model.get("user_name").toString());//$NON-NLS-1$
		__user.setState(EntityState.NORMAL);
		__user.setUuid($model.get("uuid").toString().trim());//$NON-NLS-1$
		__user.setParent_uuid("ff196109-e12a-4904-966d-a30cc35110ae");//$NON-NLS-1$
		__user.setPassword($model.get("password") == null ? "" : $model.get("password").toString().trim());//$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
		__user.setLPassword($model.get("lpassword") == null ? "" : $model.get("lpassword").toString().trim());//$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
		__user.setRealname($model.get("realname") == null ? "" : $model.get("realname").toString().trim());//$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
		__user.setLast_logintime($model.get("last_logintime").toString());//$NON-NLS-1$
		__user.setLast_logouttime($model.get("last_logouttime").toString());//$NON-NLS-1$
		__user.setIsrememberpassword(Integer.parseInt($model.get("isrememberpassword").toString()));//$NON-NLS-1$
		__user.setIsautologin(Integer.parseInt($model.get("isautologin").toString()));//$NON-NLS-1$

		return __user;
	}

	public synchronized Attachment getAttachment(String $uuid) {
		IEntity __entity = this._objectIndex.get($uuid);
		if (__entity instanceof Attachment) {
			return (Attachment) __entity;
		}
		return null;
	}

	private Attachment createAttachment(MapResultModel $model) {
		Attachment __attachment = new Attachment();
		__attachment.setId(Integer.parseInt($model.get("id").toString()));//$NON-NLS-1$
		__attachment.setName($model.get("file_name").toString());//$NON-NLS-1$
		__attachment.setState(EntityState.NORMAL);
		__attachment.setUuid($model.get("uuid").toString().trim());//$NON-NLS-1$
		__attachment.setParent_uuid($model.get("tab_mail_maill_uuid").toString().trim());//$NON-NLS-1$

		/** 附加数据 */
		__attachment.setIcon($model.get("icon") == null ? "" : $model.get("icon").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__attachment.setFile_name($model.get("file_name") == null ? "" : $model.get("file_name").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__attachment.setSummary($model.get("summary") == null ? "" : $model.get("summary").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__attachment.setComment($model.get("comment") == null ? "" : $model.get("comment").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__attachment.setTab_mail_maill_id(Integer.parseInt($model.get("tab_mail_maill_id").toString()));//$NON-NLS-1$
		__attachment.setTab_mail_maill_uuid($model.get("tab_mail_maill_uuid") == null ? "" : $model.get("tab_mail_maill_uuid").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__attachment.setVersion($model.get("version") == null ? "" : $model.get("version").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$

		return __attachment;
	}

	public synchronized Email getEmail(String $uuid) {
		IEntity __entity = this._objectIndex.get($uuid);
		if (__entity instanceof Email) {
			return (Email) __entity;
		}
		return null;
	}

	private Email createEmail(MapResultModel $model) {
		Email __email = new Email();
		__email.setId(Integer.parseInt($model.get("id").toString()));//$NON-NLS-1$
		__email.setName($model.get("subject").toString());//$NON-NLS-1$
		__email.setState(EntityState.NORMAL);
		__email.setUuid($model.get("uuid").toString().trim());//$NON-NLS-1$

		if ($model.get("tab_mail_maill_uuid") == null || "".equals($model.get("tab_mail_maill_uuid").toString().trim())) {//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
			__email.setParent_uuid($model.get("tab_mail_mailaccountt_uuid").toString().trim());//$NON-NLS-1$
		} else {
			__email.setParent_uuid($model.get("tab_mail_maill_uuid").toString().trim());//$NON-NLS-1$
		}

		__email.setFrom1($model.get("from1") == null ? "" : $model.get("from1").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__email.setTo1($model.get("to1") == null ? "" : $model.get("to1").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__email.setCc($model.get("cc") == null ? "" : $model.get("cc").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__email.setBcc($model.get("bcc") == null ? "" : $model.get("bcc").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__email.setIcon($model.get("icon") == null ? "" : $model.get("icon").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__email.setSubject($model.get("subject") == null ? "" : $model.get("subject").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__email.setSummary($model.get("summary") == null ? "" : $model.get("summary").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__email.setContent($model.get("content") == null ? "" : $model.get("content").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__email.setTab_mail_mailaccountt_id(Integer.parseInt($model.get("tab_mail_mailaccountt_id").toString()));//$NON-NLS-1$
		__email.setTab_mail_mailaccountt_uuid($model.get("tab_mail_mailaccountt_uuid") == null ? "" : $model.get("tab_mail_mailaccountt_uuid").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__email.setVersion($model.get("version") == null ? "" : $model.get("version").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__email.setTab_mail_maill_id(Integer.parseInt($model.get("tab_mail_maill_id").toString()));//$NON-NLS-1$
		__email.setTab_mail_maill_uuid($model.get("tab_mail_maill_uuid") == null ? "" : $model.get("tab_mail_maill_uuid").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$

		return __email;
	}

	public synchronized EmailBox getEmailbox(String $uuid) {
		IEntity __entity = this._objectIndex.get($uuid);
		if (__entity instanceof EmailBox) {
			return (EmailBox) __entity;
		}
		return null;
	}

	private EmailBox createEmailbox(MapResultModel $model) {
		EmailBox __emailbox = new EmailBox();
		__emailbox.setId(Integer.parseInt($model.get("id").toString()));//$NON-NLS-1$
		__emailbox.setName($model.get("mail_user").toString());//$NON-NLS-1$
		__emailbox.setState(EntityState.NORMAL);
		__emailbox.setUuid($model.get("uuid").toString().trim());//$NON-NLS-1$
		__emailbox.setParent_uuid($model.get("tab_sysmanage_user_uuid").toString().trim());//$NON-NLS-1$

		__emailbox.setMail_user($model.get("mail_user").toString());//$NON-NLS-1$
		__emailbox.setMail_pass($model.get("mail_pass") == null ? "" : $model.get("mail_pass").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__emailbox.setMail_smtp_host($model.get("mail_smtp_host") == null ? "" : $model.get("mail_smtp_host").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__emailbox.setMail_smtp_auth($model.get("mail_smtp_auth") == null ? "" : $model.get("mail_smtp_auth").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__emailbox.setVersion($model.get("mail_smtp_auth") == null ? "" : $model.get("mail_smtp_auth").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__emailbox.setTab_sysmanage_user_id(Integer.parseInt($model.get("tab_sysmanage_user_id").toString()));//$NON-NLS-1$
		__emailbox.setTab_sysmanage_user_uuid($model.get("tab_sysmanage_user_uuid") == null ? "" : $model.get("tab_sysmanage_user_uuid").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$

		return __emailbox;
	}

	private void relation(IParent $newparent, IChild $child) {
		Assert.isNotNull($child, "Child is null");//$NON-NLS-1$

		IParent __oldParent = $child.getParent();
		if ($newparent == __oldParent)
			return;

		if (__oldParent != null)
			__oldParent.removeChild($child);

		if ($newparent == null) {
			$child.setParent(null);
			$child.setParent_uuid(null);
		} else {
			$newparent.addChild($child, null, false);
			$child.setParent($newparent);
			$child.setParent_uuid($newparent.getUuid());
		}
	}

	public void setLastLoginUser() {
		IWebSite __webSite = new WebSiteReference().resolve();
		List<IUser> __users = __webSite.getUsers();

		if (__users != null && __users.size() > 0) {
			HashMap<String, String> __map = new HashMap<String, String>();

			for (IUser ___user : __users) {
				__map.put(___user.getUuid(), ___user.getLast_logintime());
			}

			String __last_uuid = Utils.lastDateTime(__map);
			IUser __user = new UserReference(__last_uuid).resolve();

			__webSite.setLastLoginUser(__user);
		}
	}

}
