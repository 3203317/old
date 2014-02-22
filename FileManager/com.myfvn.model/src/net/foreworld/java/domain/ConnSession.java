package net.foreworld.java.domain;

import java.util.Date;
import java.util.Map;

/**
 * 
 * @author 	huangxin
 * @email  	huangxin@foreworld.net
 * @qq		3203317
 * @generated
 */
public abstract interface ConnSession{

	public abstract Long getId();

	public abstract String getUserId();

	public abstract Object getUser();

	public abstract String getSecurityLevel();

	public abstract String getProductId();

	public abstract String getIndustryId();

	public abstract String getAccount();

	public abstract Object getModuleId();

	public abstract void setModuleId(Object $paramObject);

	public abstract Date getCreateTime();

	public abstract Date getUpdateTime();

	public abstract long getTimeOut();

	public abstract void setAttribute(Object $paramObject1, Object $paramObject2);

	public abstract Map getAttributes();

	public abstract Object getAttribute(Object $paramObject);
	
}