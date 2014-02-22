package net.newcapec.sca.org.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.org.Organization;
import net.newcapec.sca.org.das.OrganizationDAS;
import net.newcapec.sca.org.service.OrganizationService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.resource.bean.MenuItem;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class OrganizationServiceImpl implements OrganizationService
{
	private static final Logger organizationServiceLogger = Logger.getLogger(OrganizationServiceImpl.class);

	private static final String IS_HAVE_CHILD_TRUE = "1";
	private static final String IS_HAVE_CHILD_FALSE = "0";
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

	private OrganizationDAS organizationDAS;

	@Reference(name = "organizationDAS", required = true)
	public void setOrganizationDAS(OrganizationDAS organizationDAS) {
		this.organizationDAS = organizationDAS;
	}
	public Organization getOrgById(String sessionId,Integer id)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Organization org = organizationDAS.getOrgById(id);
		return org;
	}
	public List<Organization> findOrgList(String sessionId, Integer resourceId,
			List<FilterParam> filter, Integer beginId, Integer limit)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<Organization> list = organizationDAS.findOrgList(Integer.valueOf(session.getDomain_code()),
				Integer.valueOf(session.getUnit_code()),filter, beginId, limit);
		return list;
	}

	public DojoListData findOrgDojoList(DojoListParam param)
	{
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<Organization> list = this.organizationDAS.findOrgList(Integer.valueOf(session.getDomain_code()),
				Integer.valueOf(session.getUnit_code()), param.getFilter(), param.getBegin(), param.getLimit());
		DojoListData dld = new DojoListData();
		dld.setIdentifier("code");
		dld.setItems(list.toArray());
		dld.setLabel("name");
		dld.setNumRows(this.organizationDAS.getOrgListRowCount(1, 1, param.getFilter()));
		return dld;
	}
	public Organization insertOrg(String sessionId,Organization org)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		ResultMsg resultMsg = new ResultMsg();
		try
		{
			org.setCode(this.sequenceService.getNextValue("getMaxOrganizationID").toString());
			org.setUser_unit_code("1");
			org.setCode_path("1");
			org.setBatch_code("1");
			org.setUuid("1");
			org.setVer("0");
			org.setSortid("1");
			org.setCreate_user_account_id(session.getUser_code());
			org.setIs_have_child(IS_HAVE_CHILD_FALSE);
			boolean sign = this.organizationDAS.insertOrg(org);
			if(sign)
			{
				resultMsg.setStatus(1);
			}
			else
			{
				resultMsg.setStatus(0);
			}
		}
		catch (Exception e)
		{
			organizationServiceLogger.debug(e.getMessage(),e);;
			resultMsg.setStatus(0);
		}
		org.setResultMsg(resultMsg);
		return org;
	}
	public Organization[] insertOrgForOrgUI(String sessionId,Organization org)
	{
		Session session = sessionService.getSession(sessionId);
		List<Organization> rtnList = null;
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		try
		{
			org.setCode(this.sequenceService.getNextValue("getMaxOrganizationID").toString());
			org.setUser_unit_code("1");
			org.setCode_path("1");
			org.setBatch_code("1");
			org.setUuid("1");
			org.setVer("0");
			org.setSortid("1");
			org.setIs_have_child(IS_HAVE_CHILD_FALSE);
			//user_code就是Account_id
			org.setCreate_user_account_id(session.getUser_code());
			boolean sign = this.organizationDAS.insertOrg(org);
			if(sign)
			{
				Organization rtnOrg = this.updateSuperOrg_insert(session,org);
				rtnList = new ArrayList<Organization>();
				rtnList.add(org);
				rtnList.add(rtnOrg);
			}
		}
		catch (Exception e)
		{
			organizationServiceLogger.debug(e.getMessage(),e);;
		}
		Organization[] rtnArray = new Organization[rtnList.size()];
		rtnList.toArray(rtnArray);
		return rtnArray;
	}
	/**
	 * 添加时更新父节点的信息
	 * @param session
	 * @param org
	 * @return
	 */
	private Organization updateSuperOrg_insert(Session session,Organization org) {
		List<FilterParam> filter = new ArrayList<FilterParam>();
		FilterParam param = new FilterParam();
		param.setField("super_code");
		param.setLogical("=");
		param.setRelation("and");
		param.setValue(org.getSuper_code());
		filter.add(param);
		//得到父级子节点
		List<Organization> list =
			organizationDAS.findOrgList(Integer.valueOf(session.getDomain_code()), Integer.valueOf(session.getUnit_code()), filter, 0, 100);
		Organization rtnOrg = null;
		//如果父级没有子节点
		if(list.size() == 1)
		{
			Organization super_Org = new Organization();
			super_Org.setCode(org.getSuper_code());
			super_Org.setIs_have_child(IS_HAVE_CHILD_TRUE);
			this.organizationDAS.updateOrg(super_Org);
			rtnOrg = this.organizationDAS.getOrgById(Integer.valueOf(org.getSuper_code()));
		}
		return rtnOrg;
	}
	/**
	 * 删除时更新父节点的信息
	 * @param session
	 * @param org
	 * @return
	 */
	private Organization updateSuperOrg_delete(Session session,Organization org) {
		List<FilterParam> filter = new ArrayList<FilterParam>();
		FilterParam param = new FilterParam();
		param.setField("super_code");
		param.setLogical("=");
		param.setRelation("and");
		param.setValue(org.getSuper_code());
		filter.add(param);
		//得到父级子节点
		List<Organization> list =
			organizationDAS.findOrgList(Integer.valueOf(session.getDomain_code()), Integer.valueOf(session.getUnit_code()), filter, 0, 100);
		//如果父级没有子节点
		if(null == list || list.size() == 0)
		{
			Organization super_Org = new Organization();
			super_Org.setCode(org.getSuper_code());
			super_Org.setIs_have_child(IS_HAVE_CHILD_FALSE);
			this.organizationDAS.updateOrg(super_Org);
		}
		Organization rtnOrg = this.organizationDAS.getOrgById(Integer.valueOf(org.getSuper_code()));
		return rtnOrg;
	}
	public Organization updateOrg(String sessionId,Organization org)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		ResultMsg resultMsg = new ResultMsg();
		try
		{
			boolean sign = this.organizationDAS.updateOrg(org);
			if(sign)
			{
				resultMsg.setStatus(1);
			}
			else
			{
				resultMsg.setStatus(0);
			}
		}
		catch (Exception e)
		{
			organizationServiceLogger.debug(e.getMessage(),e);;
			resultMsg.setStatus(0);
		}
		org.setResultMsg(resultMsg);
		return org;
	}
	public Organization delOrgById(String sessionId,Integer id)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		ResultMsg resultMsg = new ResultMsg();
		Organization org = new Organization();
		try
		{
			boolean sign = this.organizationDAS.delOrgById(id);
			if(sign)
			{
				resultMsg.setStatus(1);
			}
			else
			{
				resultMsg.setStatus(0);
			}
		}
		catch (Exception e)
		{
			organizationServiceLogger.debug(e.getMessage(),e);;
			resultMsg.setStatus(0);
		}
		org.setResultMsg(resultMsg);
		return org;
	}

	public Organization delOrgByIdsForOrgUI(String sessionId,String ids)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Organization rtnOrg = null;
		try
		{
			String[] allID = ids.split(",");
			Organization org = this.organizationDAS.getOrgById(Integer.valueOf(allID[0]));
			for(String id : allID)
			{
				this.organizationDAS.delOrgById(Integer.valueOf(id));
			}
			rtnOrg = this.updateSuperOrg_delete(session, org);
		}
		catch (Exception e)
		{
			organizationServiceLogger.debug(e.getMessage(),e);;
		}
		return rtnOrg;
	}

	public Boolean delOrgByIds(String sessionId,String ids)
	{
		Boolean rtnSign = false;
		try
		{
			String[] allID = ids.split(",");
			for(String id : allID)
			{
				this.organizationDAS.delOrgById(Integer.valueOf(id));
			}
			rtnSign = true;

		}
		catch (Exception e)
		{
			organizationServiceLogger.debug(e.getMessage(),e);;
		}
		return rtnSign;
	}

	public List<SelectItem>  getOrganizationEmployee(String sessionId,String dept_code)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}

		return this.organizationDAS.getOrganizationEmployee(dept_code);
	}
	public MenuItem[] getOrganizationTree(String sessionId,String parentId)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<FilterParam> filterList = new ArrayList<FilterParam>();
		FilterParam filter = new FilterParam();

		List<MenuItem> menuList = new ArrayList<MenuItem>();


		//如果父id为空则表示要装配一棵树，否则就是要装配下级子节点
		if(null == parentId || parentId.trim().equals(""))
		{
			//装配一棵树所需的条件
			filter.setField("super_code");
			filter.setLogical("=");
			filter.setRelation("and");
			filter.setValue("0");
			filterList.add(filter);
			//添加根节点
			MenuItem root = new MenuItem();
			root.setId("0");
			root.setName("组织机构");
			root.setType("1");
			menuList.add(root);
		}
		else
		{
			filter.setField("super_code");
			filter.setLogical("=");
			filter.setRelation("and");
			filter.setValue(parentId);
			filterList.add(filter);
		}
		List<Organization> orgList = this.organizationDAS.findOrgList(Integer.valueOf(session.getDomain_code()), Integer.valueOf(session.getUnit_code()), filterList, null, null);


		for(Organization org : orgList)
		{
			MenuItem item = new MenuItem();
			item.setId(org.getCode());
			item.setName(org.getName());
			item.setParent(org.getSuper_code());
			item.setType(org.getIs_have_child());
			menuList.add(item);
		}

		MenuItem[] rtnArray = new MenuItem[menuList.size()];
		menuList.toArray(rtnArray);
		return rtnArray;
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
			organizationServiceLogger.debug(e.getMessage(),e);;
			sessionVaild = false;
		}
		return sessionVaild;
	}
}
