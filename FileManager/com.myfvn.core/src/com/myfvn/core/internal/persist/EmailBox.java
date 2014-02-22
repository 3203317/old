package com.myfvn.core.internal.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.foreworld.utils.rcp.core.internal.persist.AbstractEntity;
import net.foreworld.utils.rcp.core.persist.IChild;
import net.foreworld.utils.rcp.core.persist.IParent;

import org.eclipse.core.runtime.Assert;

import com.myfvn.core.persist.IEmail;
import com.myfvn.core.persist.IEmailbox;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class EmailBox extends AbstractEntity implements IEmailbox {
	final Logger _logger = Logger.getLogger(Attachment.class.getName());

	private List<IChild> _children;
	private IParent _parent;

	private String _mail_user;
	private String _mail_pass;
	private String _mail_smtp_host;
	private String _mail_smtp_auth;
	private String _version;
	private int _tab_sysmanage_user_id;
	private String _tab_sysmanage_user_uuid;

	public EmailBox() {
		this._children = new ArrayList<IChild>(5);
	}

	public synchronized void addEmail(IEmail $email, IEmail $position, boolean $after) {
		Assert.isNotNull($email, "Exception adding NULL as Emailbox into User"); //$NON-NLS-1$
		this.addChild($email, $position, $after);
	}

	public synchronized List<IEmail> getEmails() {
		return this.extractTypes(IEmail.class, this._children);
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
		/* Add to end of List if Position is unknown */
		if ($position == null) {
			if (this._children == null) {
				this._children = new ArrayList<IChild>(5);
			}
			this._children.add($child);
		}
		/* Position is provided */
		else {
			int ___index = this._children.indexOf($position);

			/* Insert to end of List */
			if (___index < 0 || (___index == this._children.size() && $after)) {
				this._children.add($child);
			}
			/* Insert after Position */
			/* Insert before Position */
			else {
				this._children.add($after ? ___index + 1 : ___index, $child);
			}
		}
	}

	public synchronized String getMail_pass() {
		return _mail_pass;
	}

	public synchronized String getMail_smtp_auth() {
		return _mail_smtp_auth;
	}

	public synchronized String getMail_smtp_host() {
		return _mail_smtp_host;
	}

	public synchronized String getMail_user() {
		return _mail_user;
	}

	public synchronized int getTab_sysmanage_user_id() {
		return _tab_sysmanage_user_id;
	}

	public synchronized String getTab_sysmanage_user_uuid() {
		return _tab_sysmanage_user_uuid;
	}

	public synchronized String getVersion() {
		return _version;
	}

	public synchronized void setMail_pass(String $mail_pass) {
		_mail_pass = $mail_pass;
	}

	public synchronized void setMail_smtp_auth(String $mail_smtp_auth) {
		_mail_smtp_auth = $mail_smtp_auth;
	}

	public synchronized void setMail_smtp_host(String $mail_smtp_host) {
		_mail_smtp_host = $mail_smtp_host;
	}

	public synchronized void setMail_user(String $mail_user) {
		_mail_user = $mail_user;
	}

	public synchronized void setTab_sysmanage_user_id(int $tab_sysmanage_user_id) {
		_tab_sysmanage_user_id = $tab_sysmanage_user_id;
	}

	public synchronized void setTab_sysmanage_user_uuid(String $tab_sysmanage_user_uuid) {
		_tab_sysmanage_user_uuid = $tab_sysmanage_user_uuid;
	}

	public synchronized void setVersion(String $version) {
		_version = $version;
	}

}
