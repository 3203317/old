package net.newcapec.sca.role.service;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.role.Role;
import net.newcapec.sca.role.RoleResPerOpt;
import net.newcapec.sca.role.RoleUIPermission;

import org.oasisopen.sca.annotation.Remotable;

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
@Remotable
public interface RoleService {

	public Role getRoleById(String sessionId, Integer id);

	public List<Role> findRoleList(String sessionId, Integer resourceId,
			List<FilterParam> filter, Integer begin, Integer limit);

	public DojoListData findRoleDojoList(DojoListParam param);

	public Role insertRole(String sessionId, Role role);

	public Role updateRole(String sessionId, Role role);

	public Role delRoleById(String sessionId, Integer id);

	public Role delRoleByIds(String sessionId, String ids);

	public Integer getRoleListRowCount(String sessionId, Integer resourceId,
			List<FilterParam> filter);

	public List<RoleUIPermission> findRoleUIPermissionList(String sessionId,
			Integer resourceId, Integer roleId);

	public List<RoleResPerOpt> findRolePerOptList(String sessionId,
			Integer resourceId, Integer roleId, Integer resId);

	// public List findRolePermission(String sessionId);

}