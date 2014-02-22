package com.myfvn.core.internal.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.foreworld.utils.rcp.core.internal.persist.AbstractEntity;
import net.foreworld.utils.rcp.core.persist.IChild;
import net.foreworld.utils.rcp.core.persist.IParent;

import org.eclipse.core.runtime.Assert;

import com.myfvn.core.persist.IAttachment;
import com.myfvn.core.persist.IEmail;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Email extends AbstractEntity implements IEmail {
	final Logger _logger = Logger.getLogger(Email.class.getName());

	private List<IChild> _children;
	private IParent _parent;

	private String _from1;
	private String _to1;
	private String _cc;
	private String _bcc;
	private String _icon;
	private String _subject;
	private String _summary;
	private String _content;
	private int _tab_mail_mailaccountt_id;
	private String _tab_mail_mailaccountt_uuid;
	private String _version;
	private int _tab_mail_maill_id;
	private String _tab_mail_maill_uuid;

	public Email() {
		this._children = new ArrayList<IChild>(5);
	}

	public synchronized void addAttachment(IAttachment $attachment, IAttachment $position, boolean $after) {
		Assert.isNotNull($attachment, "Exception adding NULL as Attachment into Email"); //$NON-NLS-1$
		this.addChild($attachment, $position, $after);
	}

	public synchronized void addEmail(IEmail $email, IEmail $position, boolean $after) {
		Assert.isNotNull($email, "Exception adding NULL as Email into Email"); //$NON-NLS-1$
		this.addChild($email, $position, $after);
	}

	public synchronized List<IAttachment> getAttachments() {
		return this.extractTypes(IAttachment.class, this._children);
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

	public String getBcc() {
		return _bcc;
	}

	public String getCc() {
		return _cc;
	}

	public String getContent() {
		return _content;
	}

	public String getFrom1() {
		return _from1;
	}

	public String getIcon() {
		return _icon;
	}

	public String getSubject() {
		return _subject;
	}

	public String getSummary() {
		return _summary;
	}

	public int getTab_mail_mailaccountt_id() {
		return _tab_mail_mailaccountt_id;
	}

	public String getTab_mail_mailaccountt_uuid() {
		return _tab_mail_mailaccountt_uuid;
	}

	public int getTab_mail_maill_id() {
		return _tab_mail_maill_id;
	}

	public String getTab_mail_maill_uuid() {
		return _tab_mail_maill_uuid;
	}

	public String getTo1() {
		return _to1;
	}

	public String getVersion() {
		return _version;
	}

	public void setBcc(String $bcc) {
		_bcc = $bcc;
	}

	public void setCc(String $cc) {
		_cc = $cc;
	}

	public void setContent(String $content) {
		_content = $content;
	}

	public void setFrom1(String $from1) {
		_from1 = $from1;
	}

	public void setIcon(String $icon) {
		_icon = $icon;
	}

	public void setSubject(String $subject) {
		_subject = $subject;
	}

	public void setSummary(String $summary) {
		_summary = $summary;
	}

	public void setTab_mail_mailaccountt_id(int $tab_mail_mailaccountt_id) {
		_tab_mail_mailaccountt_id = $tab_mail_mailaccountt_id;
	}

	public void setTab_mail_mailaccountt_uuid(String $tab_mail_mailaccountt_uuid) {
		_tab_mail_mailaccountt_uuid = $tab_mail_mailaccountt_uuid;
	}

	public void setTab_mail_maill_id(int $tab_mail_maill_id) {
		_tab_mail_maill_id = $tab_mail_maill_id;
	}

	public void setTab_mail_maill_uuid(String $tab_mail_maill_uuid) {
		_tab_mail_maill_uuid = $tab_mail_maill_uuid;
	}

	public void setTo1(String $to1) {
		_to1 = $to1;
	}

	public void setVersion(String $version) {
		_version = $version;
	}

}
