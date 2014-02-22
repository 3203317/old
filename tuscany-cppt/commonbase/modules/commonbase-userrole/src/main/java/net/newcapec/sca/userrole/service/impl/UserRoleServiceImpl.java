package net.newcapec.sca.userrole.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;
import net.newcapec.sca.user.UserBaseInfo;
import net.newcapec.sca.userrole.OneUserManyRoles;
import net.newcapec.sca.userrole.UserRole;
import net.newcapec.sca.userrole.das.UserRoleDAS;
import net.newcapec.sca.userrole.service.UserRoleService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class UserRoleServiceImpl implements UserRoleService {
	private static final Logger userRoleServiceLogger = Logger.getLogger(UserRoleServiceImpl.class);
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

	private UserRoleDAS userRoleDAS;

	@Reference(name = "userRoleDAS", required = true)
	public void setUserRoleDAS(UserRoleDAS userRoleDAS) {
		this.userRoleDAS = userRoleDAS;
	}

	public UserRole getUserRoleById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		UserRole userRole = this.userRoleDAS.getUserRoleById(id);
		return userRole;
	}
	public DojoListData findRoleInfo_UnitDojoList (DojoListParam param)
	{
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			return null;
		}

		List list = this.userRoleDAS.findRoleInfo_UnitList(param.getFilter(), param.getBegin(), param.getLimit());
		DojoListData dld = new DojoListData();
		dld.setIdentifier("userrole_code");
		dld.setItems(list.toArray());
		dld.setLabel("user_name");
		dld.setNumRows(this.userRoleDAS.findUserRoleList(1, 1, param.getFilter(), null, null).size());
		return dld;
	}
	public List<UserRole> findUserRoleByUserId(String sessionId, Integer userId) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}

		List<FilterParam> filterList = new ArrayList<FilterParam>();
		FilterParam filter = new FilterParam();
		filter.setField("account_id");
		filter.setLogical("=");
		filter.setRelation("and");
		filter.setValue(userId.toString());

		List<UserRole> list = this.userRoleDAS.findUserRoleList(null, null, filterList, null, null);
		return list;
	}

	public DojoListData findUserAndRoles(DojoListParam param)
	{
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		//根据部门id取的人员列表 account_id(别名code),name
		List<UserBaseInfo> selectList = this.userRoleDAS.getOrganizationEmployee( param.getFilter(), param.getBegin(), param.getLimit());

		List<OneUserManyRoles> list = this.userRoleDAS.findUserAndRoles(selectList);

		DojoListData dld = new DojoListData();
		dld.setIdentifier("account_id");
		dld.setItems(list.toArray());
		dld.setLabel("user_name");
		Integer userRolesNum = selectList.size();
		dld.setNumRows(userRolesNum);
		return dld;
	}

	public List<UserRole> findUserRoleList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limit) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<UserRole> list = this.userRoleDAS.findUserRoleList(Integer.valueOf(session.getDomain_code()),
				Integer.valueOf(session.getUnit_code()), filter, beginId, limit);
		return list;
	}

	public UserRole insertUserRole(String sessionId, UserRole userRole) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		ResultMsg resultMsg = new ResultMsg();
		userRole.setCode(this.sequenceService.getNextValue("getMaxUserRoleID").toString());
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		userRole.setCreate_date(formatter.format(date));
		userRole.setCreate_user_account_id(session.getUser_code());
		userRole.setVer("1");
		try
		{
			boolean sign = this.userRoleDAS.insertUserRole(userRole);
			if(sign)
			{
				resultMsg.setStatus(1);
			}
			else
			{
				resultMsg.setStatus(0);
			}
		}
		catch(Exception e)
		{
			userRoleServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		userRole.setResultMsg(resultMsg);
		return userRole;
	}

	public UserRole updateUserRole(String sessionId, UserRole userRole) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		ResultMsg resultMsg = new ResultMsg();
		try
		{
			boolean sign = this.userRoleDAS.updateUserRole(userRole);
			if(sign)
			{
				resultMsg.setStatus(1);
			}
			else
			{
				resultMsg.setStatus(0);
			}
		}
		catch(Exception e)
		{
			userRoleServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		userRole.setResultMsg(resultMsg);
		return userRole;
	}
	public Boolean delUserRoleByIds(String sessionId, String ids)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Boolean rtnSign = false;
		try
		{
			String[] allID = ids.split(",");
			for(String id : allID)
			{
				this.userRoleDAS.delUserRoleById(Integer.valueOf(id));
			}
			rtnSign = true;
		}
		catch (Exception e)
		{
			userRoleServiceLogger.debug(e.getMessage(),e);
		}
		return rtnSign;
	}
	public UserRole delUserRoleById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		ResultMsg resultMsg = new ResultMsg();
		UserRole userRole = new UserRole();
		try
		{
			boolean sign = this.userRoleDAS.delUserRoleById(id);
			if(sign)
			{
				resultMsg.setStatus(1);
			}
			else
			{
				resultMsg.setStatus(0);
			}
		}
		catch(Exception e)
		{
			userRoleServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		userRole.setResultMsg(resultMsg);
		return userRole;
	}
	/**
	 *根据用户的account_code和domain_code返回角色的code的id和name
	 */
	public SelectItem[] getRolesWhichWillBe(String sessionId, String account_id)
	{

		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<SelectItem> rtnList = this.userRoleDAS.getRolesWhichWillBe(account_id,session.getDomain_code(),session.getUnit_code());

		SelectItem[] rtnArray = new SelectItem[rtnList.size()];

		rtnList.toArray(rtnArray);
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
			userRoleServiceLogger.debug(e.getMessage(),e);
			sessionVaild = false;
		}
		return sessionVaild;
	}
}
