package net.newcapec.sca.role.das;

import java.util.List;

import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.role.RolePermision;

/**
 * <p>
 * Title: RolepermisionDAS
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
public interface RolePermisionDAS {

	public RolePermision getRolePermisionById(Integer id);

	public List<RolePermision> findRolePermisionList(Integer domainId,
			Integer orgId, List<FilterParam> filter, Integer begin,
			Integer limit);

	public List<RolePermision> findUnRolePermisionList(Integer domainId,
			Integer orgId, Integer roleId, Integer begin, Integer limit);

	public Boolean insertRolePermision(RolePermision rolePermision);

	public Boolean updateRolePermision(RolePermision rolePermision);

	public Boolean delRolePermisionById(Integer id);

	public Boolean delRolePermisionByRoleId(Integer roleId);

	public Boolean delRolePermisionByRoldPerId(Integer roleId, Integer perId);

	public Boolean delRolePermisionByIds(Integer[] ids);

	public Integer getRolePermisionListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);

}