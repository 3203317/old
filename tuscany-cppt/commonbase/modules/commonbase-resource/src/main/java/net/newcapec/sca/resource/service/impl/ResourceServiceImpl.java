package net.newcapec.sca.resource.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.resource.Resource;
import net.newcapec.sca.resource.bean.MenuItem;
import net.newcapec.sca.resource.bean.Tree;
import net.newcapec.sca.resource.das.ResourceDAS;
import net.newcapec.sca.resource.service.ResourceService;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class ResourceServiceImpl implements ResourceService {

	private static final Logger resourceServiceLogger = Logger.getLogger(ResourceServiceImpl.class);
	private static final SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}
	private SequenceService sequenceService;

	@Reference(name = "sequenceService", required = true)
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}

	private ResourceDAS resourceDAS;

	@Reference(name = "resourceDAS", required = true)
	public void setResourceDAS(ResourceDAS resourceDAS) {
		this.resourceDAS = resourceDAS;
	}

	public Integer getResourceListRowCount(String sessionId,List<FilterParam> filter)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		return this.resourceDAS.getResourceListRowCount(Integer.valueOf(session.getDomain_code()),Integer.valueOf(session.getUnit_code()),filter);
	}
	public DojoListData findResourceDojoList(DojoListParam param)
	{
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<Resource> list = this.resourceDAS.findResourceList(1, 1, param.getFilter(), param.getBegin(), param.getLimit());
		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(list.toArray());
		__dld.setLabel("name");
		return null;
	}
	public Resource getResourceById(String sessionId, Integer id) {

		Session session = sessionService.getSession(sessionId);
		Resource resource = new Resource();
		if(!this.isSessionVaild(session))
		{
			/* 设置操作结果消息 */
			ResultMsg getResourceError = new ResultMsg();
			getResourceError.setErrorId(102);
			getResourceError.setErrorMsg("session失效");
			resource.setResultMsg(getResourceError);
			return resource;
		}
		resource = this.resourceDAS.getResourceById(id);
		return resource;
	}

	public List<Resource> getMenuListByName(String sessionId,
			String resourceName, Integer limit) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<Resource> resourceList =
			this.resourceDAS.findResourceList
			(Integer.valueOf(session.getDomain_code()),Integer.valueOf(session.getUnit_code()), null, 1,limit);

		return resourceList;
	}

	public List<Resource> getResourceListByParentId(String sessionId,
			Integer resourceId, Integer parentId) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<Resource> resourceList = this.resourceDAS.getResourceListByParentId(
				Integer.valueOf(session.getDomain_code()),Integer.valueOf(session.getUnit_code()),parentId);

		return resourceList;
	}

	public List<Resource> findResourceList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limitId) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<Resource> resourceList =
			this.resourceDAS.findResourceList
			(Integer.valueOf(session.getDomain_code()),Integer.valueOf(session.getUnit_code()), filter, beginId,limitId);

		return resourceList;
	}

	public Resource insertResource(String sessionId, Resource resource) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}

		resource.setUser_unit_code(1L);
		resource.setDomain_code(1L);
		resource.setUuid("1");
		resource.setCreate_user_account_id(1L);
		resource.setDate(_sdf.format(new Date()));
		resource.setInfo("暂无");
		resource.setVer(1L);
		resource.setCode(Long.valueOf(sequenceService.getNextValue("getMaxResourceID")));
		resource.setSortid(resource.getCode());
		ResultMsg resultMsg = new ResultMsg();
		if(this.resourceDAS.insertResource(resource))
		{
			resultMsg.setStatus(1);
		}
		else
		{
			resultMsg.setStatus(0);
		}
		resource.setResultMsg(resultMsg);
		return resource;
	}

	public Resource updateResource(String sessionId, Resource resource) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}

		ResultMsg resultMsg = new ResultMsg();
		if(this.resourceDAS.updateResource(resource))
		{
			resultMsg.setStatus(1);
		}
		else
		{
			resultMsg.setStatus(0);
		}
		resource.setResultMsg(resultMsg);
		return resource;
	}

	public Resource delResourceById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Resource resource = new Resource();
		ResultMsg resultMsg = new ResultMsg();
		if(this.resourceDAS.delResourceById(id))
		{
			resultMsg.setStatus(1);
		}
		else
		{
			resultMsg.setStatus(0);
		}
		resource.setResultMsg(resultMsg);
		return resource;
	}
	/**
	 * true 有效， false 失效
	 * @param session
	 * @return
	 */
	protected boolean isSessionVaild(Session session)
	{
		boolean sessionVaild = true;
		try
		{
			if (null == session.getId() && session.getState() == 0)
			{
				sessionVaild = false;
			}
		}
		catch (Exception e)
		{
			resourceServiceLogger.debug(e.getMessage(),e);
			sessionVaild = false;
		}
		return sessionVaild;
	}

	public MenuItem[] searchSubItem(String sessionId ,String name)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		MenuItem[] menuItem =
			this.resourceDAS.searchSubItem(session.getUser_code(),session.getDomain_code(), session.getUnit_code(), name);
		return menuItem;
	}

	public Tree[] getTrees(String sessionId)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Tree[] treeItem =
			this.resourceDAS.getTrees(session.getUser_code(),session.getDomain_code(), session.getUnit_code());
		return treeItem;
	}
	public MenuItem[] getFilteringSelectList(List<FilterParam> filter)
	{

		List<Resource> list =
			this.resourceDAS.findResourceList(1, 1, filter, null, null);
		List<MenuItem> rtnList = new ArrayList<MenuItem>();
		for(Resource resource : list)
		{
			MenuItem menuItem = new MenuItem();
			menuItem.setId(resource.getCode().toString());
			menuItem.setName(resource.getName());
			rtnList.add(menuItem);
		}
		MenuItem[] rtnArray = new MenuItem[rtnList.size()];
		rtnList.toArray(rtnArray);
		return rtnArray;
	}

	public Resource delResourceByIds(String sessionId, String ids) {
		Resource r = new Resource();
		ResultMsg resultMsg = new ResultMsg();
		try
		{
			String[] allID = ids.split(",");
			for(String id : allID)
			{
				this.resourceDAS.delResourceById(Integer.valueOf(id));
			}
			resultMsg.setStatus(1);
		}
		catch (Exception e)
		{
			resultMsg.setStatus(0);
			resourceServiceLogger.debug(e.getMessage(),e);
		}
		r.setResultMsg(resultMsg);
		return r;
	}
}
