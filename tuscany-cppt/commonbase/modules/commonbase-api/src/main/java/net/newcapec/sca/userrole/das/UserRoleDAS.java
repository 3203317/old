package net.newcapec.sca.userrole.das;

import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.user.UserBaseInfo;
import net.newcapec.sca.userrole.OneUserManyRoles;
import net.newcapec.sca.userrole.RoleInfo_Unit;
import net.newcapec.sca.userrole.UserRole;

public interface UserRoleDAS
{
	public UserRole getUserRoleById(Integer id);
	public List<UserRole> findUserRoleList(Integer domainId, Integer orgId,List<FilterParam> filter, Integer beginId, Integer limitId);
	public Boolean insertUserRole(UserRole org);
	public Boolean updateUserRole(UserRole org);
	public Boolean delUserRoleById(Integer id);
	public List<RoleInfo_Unit> findRoleInfo_UnitList(List<FilterParam> filter, Integer begin , Integer limit);
	public List<OneUserManyRoles> findUserAndRoles(List<UserBaseInfo> selectList);
	public List<UserBaseInfo> getOrganizationEmployee(List<FilterParam> filter,Integer begin,Integer limit);
	public List<SelectItem> getRolesWhichWillBe(String account_id,String domain_code,String orgCode);
}
