package net.newcapec.sca.role.service.impl;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.role.Role;
import net.newcapec.sca.role.RoleResPerOpt;
import net.newcapec.sca.role.RoleUIPermission;
import net.newcapec.sca.role.das.RoleDAS;
import net.newcapec.sca.role.service.RoleService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

/**
 * <p>
 * Title: RoleService
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright(c) 2011 郑州新开普电子股份有限公司
 * </p>
 *
 * @author 黄鑫 (huangxin)
 * @version
 * @data 创建日期：2011-11-11 修改日期： 修改人： 复审人：
 * @generated
 */
public class RoleServiceImpl implements RoleService {

	private static final Logger _log = Logger.getLogger(RoleServiceImpl.class);

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private RoleDAS roleDAS;

	@Reference(name = "roleDAS", required = true)
	public void setRoleDAS(RoleDAS roleDAS) {
		this.roleDAS = roleDAS;
	}

	public Role getRoleById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		Role __form = this.roleDAS.getRoleById(id);
		return __form;
	}

	public Role insertRole(String sessionId, Role role) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		role.setCreate_user_account_id(Integer.parseInt(session.getUser_code()));
		Boolean __b = this.roleDAS.insertRole(role);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("添加新角色失败");
			role.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return role;
	}

	public Role updateRole(String sessionId, Role role) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		this.roleDAS.updateRole(role);
		return role;
	}

	public Role delRoleById(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Role delRoleByIds(String sessionId, String ids) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		// 验证 sessionId 是否合法

		_log.info("参数：" + ids);

		Role __form = new Role();
		ResultMsg __rm = new ResultMsg();

		if ("".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setErrorMsg("参数 ids 不能为空");
			__form.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
			return __form;
		}

		String[] __idsArr = ids.split(",");

		Integer[] __ids = new Integer[__idsArr.length];

		for (int __i_3 = 0, __j_3 = __idsArr.length; __i_3 < __j_3; __i_3++) {
			__ids[__i_3] = Integer.parseInt(__idsArr[__i_3]);
		}

		boolean __delResult = this.roleDAS.delRoleByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除 role 失败");
			__form.setResultMsg(__rm);
		}

		return __form;
	}

	public List<Role> findRoleList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<Role> __list = this.roleDAS.findRoleList(1, 1, filter, begin, limit);
		return __list;
	}

	public DojoListData findRoleDojoList(DojoListParam param) {
		Session session = sessionService.getSession(param.getSessionId());
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<Role> __list = this.roleDAS.findRoleList(1, 1, param.getFilter(), param.getBegin(), param.getLimit());

		int __count = this.roleDAS.getRoleListRowCount(param.getSessionId(), param.getResourceId(), param.getFilter());

		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		__dld.setNumRows(__count);
		return __dld;
	}

	public Integer getRoleListRowCount(String sessionId, Integer resourceId, List<FilterParam> filter) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		int __count = this.roleDAS.getRoleListRowCount(sessionId, resourceId, filter);
		return __count;
	}

	public List<RoleUIPermission> findRoleUIPermissionList(String sessionId, Integer resourceId, Integer roleId) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<RoleUIPermission> __list = this.roleDAS.findRoleUIPermissionList(roleId);
		return __list;
	}

	public List<RoleResPerOpt> findRolePerOptList(String sessionId, Integer resourceId, Integer roleId, Integer resId) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<RoleResPerOpt> __list = this.roleDAS.findRolePerOptList(roleId, resId);
		return __list;
	}

	/**
	 * true 有效， false 失效
	 *
	 * @param session
	 * @return
	 */
	protected boolean isSessionVaild(Session session) {
		boolean sessionVaild = true;
		try {
			if (null == session.getId() && session.getState() == 0) {
				sessionVaild = false;
			}
		} catch (Exception e) {
			sessionVaild = false;
			_log.debug(e.getMessage());
		}
		return sessionVaild;
	}
}
