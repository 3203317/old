package net.foreworld.utils.rcp.core.internal.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.foreworld.utils.rcp.core.persist.IChild;
import net.foreworld.utils.rcp.core.persist.IParent;
import net.foreworld.utils.rcp.core.persist.IUser;
import net.foreworld.utils.rcp.core.persist.IWebSite;

import org.eclipse.core.runtime.Assert;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class WebSite extends AbstractEntity implements IWebSite {

	final Logger _logger = Logger.getLogger(WebSite.class.getName());

	protected List<IChild> _children;
	private IParent _parent;
	private String _parent_uuid;
	private IUser _last_loginUser = null;

	public WebSite() {
		this._children = new ArrayList<IChild>(5);
	}

	public WebSite(String $uuid, IParent $parent, String $name) {
		super($uuid);
		Assert.isNotNull($name, "The type WebSite requires a Name that is not NULL"); //$NON-NLS-1$
		this._parent = $parent;
		this.setName($name);
		this._children = new ArrayList<IChild>(5);
	}

	public synchronized void addUser(IUser $user, IUser $position, boolean $after) {
		Assert.isNotNull($user, "Exception adding NULL as User into WebSite"); //$NON-NLS-1$
		this.addChild($user, $position, $after);
	}

	public synchronized List<IUser> getUsers() {
		return this.extractTypes(IUser.class, this._children);
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

	public IUser getLastLoginUser() {
		return _last_loginUser;
	}

	public void setLastLoginUser(IUser $user) {
		this._last_loginUser = $user;
	}

}
