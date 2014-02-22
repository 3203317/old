package net.newcapec.sca.userrole.service;

import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.userrole.UserRole;

import org.oasisopen.sca.annotation.Remotable;
@Remotable
public interface UserRoleService {
	public UserRole getUserRoleById(String sessionId,Integer id);
	public List<UserRole> findUserRoleByUserId(String sessionId,Integer userId);
	public List<UserRole> findUserRoleList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limit);
	public UserRole insertUserRole(String sessionId,UserRole userRole);
	public UserRole updateUserRole(String sessionId,UserRole userRole);
	public UserRole delUserRoleById(String sessionId,Integer id);
	public DojoListData findRoleInfo_UnitDojoList (DojoListParam param);
	public DojoListData findUserAndRoles(DojoListParam param);
	public Boolean delUserRoleByIds(String sessionId, String ids);
	public SelectItem[] getRolesWhichWillBe(String sessionId, String account_id);

}
