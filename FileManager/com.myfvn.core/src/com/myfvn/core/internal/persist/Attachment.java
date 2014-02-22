package com.myfvn.core.internal.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.foreworld.utils.rcp.core.internal.persist.AbstractEntity;
import net.foreworld.utils.rcp.core.persist.IChild;
import net.foreworld.utils.rcp.core.persist.IParent;

import com.myfvn.core.persist.IAttachment;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Attachment extends AbstractEntity implements IAttachment {
	final Logger _logger = Logger.getLogger(Attachment.class.getName());

	private List<IChild> _children;
	private IParent _parent;

	private String _icon;
	private String _file_name;
	private String _summary;
	private String _comment;
	private int _tab_mail_maill_id;
	private String _tab_mail_maill_uuid;
	private String _version;

	public Attachment() {
		this._children = new ArrayList<IChild>(5);
	}

	public synchronized boolean containsChild(IChild $child) {
		return this._children.contains($child);
	}

	public synchronized List<IChild> getChildren() {
		return this._children;
	}

	public synchronized boolean isEmpty() {
		return this._children.isEmpty();
	}

	public synchronized boolean removeChild(IChild $child) {
		return this._children.remove($child);
	}

	public synchronized void reorderChildren(List<? extends IChild> $children, IChild $position, boolean $after) {

	}

	public void sort() {

	}

	public synchronized IParent getParent() {
		return this._parent;
	}

	public synchronized void setParent(IParent $newparent) {
		this._parent = $newparent;
	}

	public void addChild(IChild $child, IChild $position, boolean $after) {

	}

	public String getComment() {
		return _comment;
	}

	public String getFile_name() {
		return _file_name;
	}

	public String getIcon() {
		return _icon;
	}

	public String getSummary() {
		return _summary;
	}

	public int getTab_mail_maill_id() {
		return _tab_mail_maill_id;
	}

	public String getTab_mail_maill_uuid() {
		return _tab_mail_maill_uuid;
	}

	public String getVersion() {
		return _version;
	}

	public void setComment(String $comment) {
		_comment = $comment;
	}

	public void setFile_name(String $file_name) {
		_file_name = $file_name;
	}

	public void setIcon(String $icon) {
		_icon = $icon;
	}

	public void setSummary(String $summary) {
		_summary = $summary;
	}

	public void setTab_mail_maill_id(int $tab_mail_maill_id) {
		_tab_mail_maill_id = $tab_mail_maill_id;
	}

	public void setTab_mail_maill_uuid(String $tab_mail_maill_uuid) {
		_tab_mail_maill_uuid = $tab_mail_maill_uuid;
	}

	public void setVersion(String $version) {
		_version = $version;
	}

}
