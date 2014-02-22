package net.newcapec.sca.role.service;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.role.RolePermision;

import org.oasisopen.sca.annotation.Remotable;

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
@Remotable
public interface RolePermisionService {

	public RolePermision getRolePermisionById(String sessionId, Integer id);

	public List<RolePermision> findRolePermisionList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer begin,
			Integer limit);

	public List<RolePermision> findRolePermisionListByRoleId(String sessionId,
			Integer resourceId, Integer roleId);

	public List<RolePermision> findUnRolePermisionListByRoleId(
			String sessionId, Integer resourceId, Integer roleId);

	public DojoListData findRolePermisionDojoList(DojoListParam param);

	public RolePermision insertRolePermision(String sessionId,
			RolePermision rolePermision);

	public RolePermision saveRolePermision(String sessionId, Integer roleId,
			String perIds);

	public RolePermision updateRolePermision(String sessionId,
			RolePermision rolePermision);

	public RolePermision delRolePermisionById(String sessionId, Integer id);

	public RolePermision delRolePermisionByIds(String sessionId, String ids);

	public RolePermision delRolePermisionByRoldPerId(String sessionId,
			RolePermision rolePermision);

	public Integer getRolePermisionListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);

}