package net.foreworld.utils.rcp.core.persist;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IPreference extends IParent {

	void setTab_sysmanage_user_id(int $tab_sysmanage_user_id);

	int getTab_sysmanage_user_id();

	void setTab_sysmanage_user_uuid(String $tab_sysmanage_user_uuid);

	String getTab_sysmanage_user_uuid();

	void setKeyy(String $keyy);

	String getKeyy();

	void setValuee(String $valuee);

	String getValuee();
}
