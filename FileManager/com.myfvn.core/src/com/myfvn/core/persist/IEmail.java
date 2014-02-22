package com.myfvn.core.persist;

import java.util.List;

import net.foreworld.utils.rcp.core.persist.IParent;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IEmail extends IParent {

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

	/**
	 * 
	 * @param $attachment
	 * @param $position
	 * @param $after
	 */
	void addAttachment(IAttachment $attachment, IAttachment $position, boolean $after);

	/**
	 * 
	 * @return
	 */
	List<IAttachment> getAttachments();

	void setFrom1(String $from1);

	String getFrom1();

	void setTo1(String $to1);

	String getTo1();

	void setCc(String $cc);

	String getCc();

	void setBcc(String $bcc);

	String getBcc();

	void setIcon(String $icon);

	String getIcon();

	void setSubject(String $subject);

	String getSubject();

	void setSummary(String $summary);

	String getSummary();

	void setContent(String $content);

	String getContent();

	void setTab_mail_mailaccountt_id(int $tab_mail_mailaccountt_id);

	int getTab_mail_mailaccountt_id();

	void setTab_mail_mailaccountt_uuid(String $tab_mail_mailaccountt_uuid);

	String getTab_mail_mailaccountt_uuid();

	void setVersion(String $version);

	String getVersion();

	void setTab_mail_maill_id(int $tab_mail_maill_id);

	int getTab_mail_maill_id();

	void setTab_mail_maill_uuid(String $tab_mail_maill_uuid);

	String getTab_mail_maill_uuid();
}
