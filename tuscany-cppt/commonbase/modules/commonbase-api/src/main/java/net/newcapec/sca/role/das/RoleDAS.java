package net.newcapec.sca.role.das;

import java.util.List;

import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.role.Role;
import net.newcapec.sca.role.RoleResPerOpt;
import net.newcapec.sca.role.RoleUIPermission;

/**
 * <p>
 * Title: RoleDAS
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
public interface RoleDAS {

	public Role getRoleById(Integer id);

	public List<Role> findRoleList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit);

	public Boolean insertRole(Role role);

	public Boolean updateRole(Role role);

	public Boolean delRoleById(Integer id);

	public Boolean delRoleByIds(Integer[] ids);

	public Integer getRoleListRowCount(String sessionId, Integer resourceId,
			List<FilterParam> filter);

	public List<RoleUIPermission> findRoleUIPermissionList(Integer roleId);

	public List<RoleResPerOpt> findRolePerOptList(Integer roleId, Integer resId);

}