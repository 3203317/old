package com.myfvn.core.persist.reference;

import net.foreworld.utils.rcp.core.persist.IWebSite;
import net.foreworld.utils.rcp.core.persist.reference.AbstractModelReference;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;

import com.myfvn.core.internal.persist.service.DBHelper;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class WebSiteReference extends AbstractModelReference {

	public WebSiteReference() {
		super("ff196109-e12a-4904-966d-a30cc35110ae", IWebSite.class);//$NON-NLS-1$
	}

	public IWebSite resolve() throws PersistenceException {
		return DBHelper.getDefault().getWebSite(this.getUuid());
	}
}
