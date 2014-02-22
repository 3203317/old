package com.myfvn.core.persist;

import java.util.List;

import net.foreworld.utils.rcp.core.persist.IParent;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IEmailbox extends IParent {

	/**
	 * 
	 * @param $email
	 * @param $position
	 * @param $after
	 */
	void addEmail(IEmail $email, IEmail $position, boolean $after);

	/**
	 * 
	 * @return
	 */
	List<IEmail> getEmails();

	void setMail_user(String $mail_user);

	String getMail_user();

	void setMail_pass(String $mail_pass);

	String getMail_pass();

	void setMail_smtp_host(String $mail_smtp_host);

	String getMail_smtp_host();

	void setMail_smtp_auth(String $mail_smtp_auth);

	String getMail_smtp_auth();

	void setVersion(String $version);

	String getVersion();

	void setTab_sysmanage_user_id(int $tab_sysmanage_user_id);

	int getTab_sysmanage_user_id();

	void setTab_sysmanage_user_uuid(String $tab_sysmanage_user_uuid);

	String getTab_sysmanage_user_uuid();
}
