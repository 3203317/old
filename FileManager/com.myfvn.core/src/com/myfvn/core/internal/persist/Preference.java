package com.myfvn.core.internal.persist;

import java.util.List;
import java.util.logging.Logger;

import net.foreworld.utils.rcp.core.internal.persist.AbstractEntity;
import net.foreworld.utils.rcp.core.persist.IChild;
import net.foreworld.utils.rcp.core.persist.IParent;

import com.myfvn.core.persist.IPreference;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Preference extends AbstractEntity implements IPreference {
	final Logger _logger = Logger.getLogger(Preference.class.getName());

	private IParent _parent;

	private int _tab_sysmanage_user_id;
	private String _tab_sysmanage_user_uuid;
	private String _keyy;
	private String _valuee;

	public Preference() {

	}

	public synchronized String getKeyy() {
		return _keyy;
	}

	public synchronized int getTab_sysmanage_user_id() {
		return _tab_sysmanage_user_id;
	}

	public synchronized String getTab_sysmanage_user_uuid() {
		return _tab_sysmanage_user_uuid;
	}

	public synchronized String getValuee() {
		return _valuee;
	}

	public synchronized void setKeyy(String $keyy) {
		_keyy = $keyy;
	}

	public synchronized void setTab_sysmanage_user_id(int $tab_sysmanage_user_id) {
		_tab_sysmanage_user_id = $tab_sysmanage_user_id;
	}

	public synchronized void setTab_sysmanage_user_uuid(String $tab_sysmanage_user_uuid) {
		_tab_sysmanage_user_uuid = $tab_sysmanage_user_uuid;
	}

	public synchronized void setValuee(String $valuee) {
		_valuee = $valuee;
	}

	public void addChild(IChild $child, IChild $position, boolean $after) {

	}

	public boolean containsChild(IChild $child) {
		return false;
	}

	public List<IChild> getChildren() {
		return null;
	}

	public boolean isEmpty() {
		return false;
	}

	public boolean removeChild(IChild $child) {
		return false;
	}

	public void reorderChildren(List<? extends IChild> $children, IChild $position, boolean $after) {

	}

	public void sort() {

	}

	public synchronized IParent getParent() {
		return _parent;
	}

	public void setParent(IParent $newparent) {
		_parent = $newparent;
	}

}
