package com.myfvn.core.internal.persist;

import java.util.List;

import net.foreworld.utils.rcp.core.persist.IUser;
import net.foreworld.utils.rcp.core.persist.IWebSite;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class WebSite extends net.foreworld.utils.rcp.core.internal.persist.WebSite implements IWebSite {

	public synchronized List<IUser> getUsers() {
		return super.getUsers();
	}
}
