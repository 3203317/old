package com.myfvn.core.persist;

import net.foreworld.utils.rcp.core.persist.IParent;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IAttachment extends IParent {

	void setIcon(String $icon);

	String getIcon();

	void setFile_name(String $file_name);

	String getFile_name();

	void setSummary(String $summary);

	String getSummary();

	void setComment(String $comment);

	String getComment();

	void setTab_mail_maill_id(int $tab_mail_maill_id);

	int getTab_mail_maill_id();

	void setTab_mail_maill_uuid(String $tab_mail_maill_uuid);

	String getTab_mail_maill_uuid();

	void setVersion(String $version);

	String getVersion();
}
