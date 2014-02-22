package net.foreworld.utils.rcp.core.internal.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.foreworld.utils.rcp.core.persist.IChild;
import net.foreworld.utils.rcp.core.persist.IParent;
import net.foreworld.utils.rcp.core.persist.IUser;

import org.eclipse.core.runtime.Assert;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class User extends AbstractEntity implements IUser {

	final Logger _logger = Logger.getLogger(User.class.getName());

	protected List<IChild> _children;
	private IParent _parent;
	private String _password;
	private String _lpassword;
	private String _realname;
	private int _sex;
	private String _user_code;
	private String _parent_uuid;
	private int _isautologin;
	private int _isrememberpassword;
	private String _last_logouttime;
	private String _last_logintime;

	public User() {

	}

	public User(String $uuid, IParent $parent, String $name) {
		super($uuid);
		Assert.isNotNull($name, "The type User requires a Name that is not NULL"); //$NON-NLS-1$
		this._parent = $parent;
		this.setName($name);
		this._children = new ArrayList<IChild>(5);
	}

	public synchronized String getPassword() {
		return this._password;
	}

	public synchronized String getRealname() {
		return this._realname;
	}

	public synchronized int getSex() {
		return this._sex;
	}

	public synchronized String getUser_code() {
		return this._user_code;
	}

	public synchronized void setPassword(String $password) {
		this._password = $password;
	}

	public synchronized void setRealname(String $realname) {
		this._realname = $realname;
	}

	public synchronized void setSex(int $sex) {
		this._sex = $sex;
	}

	public synchronized void setUser_code(String $user_code) {
		this._user_code = $user_code;
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

	public String getParent_uuid() {
		return this._parent_uuid;
	}

	public void setParent_uuid(String $parent_uuid) {
		this._parent_uuid = $parent_uuid;
	}

	public void login() {

	}

	public void setRememberPassword() {

	}

	public void setAutoLogin() {

	}

	public void logout() {

	}

	public void changePassword() {

	}

	public String getLPassword() {
		return this._lpassword;
	}

	public void setLPassword(String $lpassword) {
		this._lpassword = $lpassword;
	}

	public int getIsautologin() {
		return this._isautologin;
	}

	public int getIsrememberpassword() {
		return this._isrememberpassword;
	}

	public String getLast_logintime() {
		return this._last_logintime;
	}

	public String getLast_logouttime() {
		return this._last_logouttime;
	}

	public void setIsautologin(int $isautologin) {
		this._isautologin = $isautologin;
	}

	public void setIsrememberpassword(int $isrememberpassword) {
		this._isrememberpassword = $isrememberpassword;
	}

	public void setLast_logintime(String $last_logintime) {
		this._last_logintime = $last_logintime;
	}

	public void setLast_logouttime(String $last_logouttime) {
		this._last_logouttime = $last_logouttime;
	}

}
