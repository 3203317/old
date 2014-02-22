package net.newcapec.sca.role.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.role.RolePermision;
import net.newcapec.sca.role.das.RolePermisionDAS;
import net.newcapec.sca.role.service.RolePermisionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

/**
 * <p>
 * Title: RolepermisionService
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
public class RolePermisionServiceImpl implements RolePermisionService {

	private static final Logger _log = Logger
			.getLogger(RolePermisionServiceImpl.class);

	private RolePermisionDAS rolePermisionDAS;

	@Reference(name = "rolePermisionDAS", required = true)
	public void setRolePermisionDAS(RolePermisionDAS rolePermisionDAS) {
		this.rolePermisionDAS = rolePermisionDAS;
	}

	public RolePermision getRolePermisionById(String sessionId, Integer id) {
		RolePermision __form = this.rolePermisionDAS.getRolePermisionById(id);
		return __form;
	}

	public RolePermision insertRolePermision(String sessionId,
			RolePermision rolePermision) {
		Boolean __b = this.rolePermisionDAS.insertRolePermision(rolePermision);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("添加新用户失败");
			rolePermision.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return rolePermision;
	}

	public RolePermision updateRolePermision(String sessionId,
			RolePermision rolePermision) {
		this.rolePermisionDAS.updateRolePermision(rolePermision);
		return rolePermision;
	}

	public RolePermision delRolePermisionById(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public RolePermision delRolePermisionByIds(String sessionId, String ids) {
		// 验证 sessionId 是否合法

		_log.info("参数：" + ids);

		RolePermision __form = new RolePermision();
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

		boolean __delResult = this.rolePermisionDAS
				.delRolePermisionByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除 rolePermision 失败");
			__form.setResultMsg(__rm);
		}

		return __form;
	}

	public List<RolePermision> findRolePermisionList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer begin,
			Integer limit) {
		List<RolePermision> __list = this.rolePermisionDAS
				.findRolePermisionList(1, 1, filter, begin, limit);
		return __list;
	}

	public DojoListData findRolePermisionDojoList(DojoListParam param) {
		List<RolePermision> __list = this.rolePermisionDAS
				.findRolePermisionList(1, 1, param.getFilter(),
						param.getBegin(), param.getLimit());

		int __count = this.rolePermisionDAS.getRolePermisionListRowCount(
				param.getSessionId(), param.getResourceId(), param.getFilter());

		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		__dld.setNumRows(__count);
		return __dld;
	}

	public Integer getRolePermisionListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter) {
		int __count = this.rolePermisionDAS.getRolePermisionListRowCount(
				sessionId, resourceId, filter);
		return __count;
	}

	public List<RolePermision> findRolePermisionListByRoleId(String sessionId,
			Integer resourceId, Integer roleId) {
		List<FilterParam> filter = new ArrayList<FilterParam>();
		FilterParam fp = new FilterParam();
		fp.setRelation("and");
		fp.setField("role_code");
		fp.setLogical("=");
		fp.setValue(roleId.toString());
		filter.add(fp);

		List<RolePermision> __list = this.rolePermisionDAS
				.findRolePermisionList(1, 1, filter, -1, -1);
		return __list;
	}

	public List<RolePermision> findUnRolePermisionListByRoleId(
			String sessionId, Integer resourceId, Integer roleId) {
		List<RolePermision> __list = this.rolePermisionDAS
				.findUnRolePermisionList(1, 1, roleId, -1, -1);
		return __list;
	}

	public RolePermision saveRolePermision(String sessionId, Integer roleId,
			String perIds) {
		this.rolePermisionDAS.delRolePermisionByRoleId(roleId);

		String[] __ids = perIds.split(",");

		for (int __i_3 = 0, __j_3 = __ids.length; __i_3 < __j_3; __i_3++) {
			RolePermision __rp_4 = new RolePermision();
			__rp_4.setRole_code(roleId);
			__rp_4.setPermission_code(Integer.valueOf(__ids[__i_3]));
			__rp_4.setCreate_user_account_id(1);
			__rp_4.setVer("1");
			this.rolePermisionDAS.insertRolePermision(__rp_4);
		}

		RolePermision __rp = new RolePermision();
		return __rp;
	}

	public RolePermision delRolePermisionByRoldPerId(String sessionId,
			RolePermision rolePermision) {
		RolePermision __rp = new RolePermision();

		boolean __delResult = this.rolePermisionDAS
				.delRolePermisionByRoldPerId(rolePermision.getRole_code(),
						rolePermision.getPermission_code());

		ResultMsg __rm = new ResultMsg();
		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除 rolePermision 失败");
			__rp.setResultMsg(__rm);
		}

		return __rp;
	}
}
