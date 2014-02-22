package net.newcapec.sca.org.service;

import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.org.Organization;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.resource.bean.MenuItem;

import org.oasisopen.sca.annotation.Remotable;
@Remotable
public interface OrganizationService {
	public Organization getOrgById(String sessionId,Integer id);
	public Organization insertOrg(String sessionId,Organization org);
	public Organization updateOrg(String sessionId,Organization org);
	public Organization delOrgById(String sessionId,Integer id);
	public List<Organization> findOrgList(String sessionId, Integer resourceId,
			List<FilterParam> filter, Integer beginId, Integer limit);
	public DojoListData findOrgDojoList(DojoListParam param);
	public MenuItem[] getOrganizationTree(String sessionId,String parentId);
	public Boolean delOrgByIds(String sessionId,String ids);
	public Organization[] insertOrgForOrgUI(String sessionId,Organization org);
	public Organization delOrgByIdsForOrgUI(String sessionId,String ids);
	public List<SelectItem>  getOrganizationEmployee(String sessionId,String dept_code);
}
