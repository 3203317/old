package net.newcapec.sca.org.das;

import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.org.Organization;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.resource.bean.MenuItem;

public interface OrganizationDAS {
	public Organization getOrgById(Integer id);
	public List<Organization> findOrgList(Integer domainId, Integer orgId,List<FilterParam> filter, Integer beginId, Integer limit);
	public Boolean insertOrg(Organization org);
	public Boolean updateOrg(Organization org);
	public Boolean delOrgById(Integer id);
	public Integer getOrgListRowCount(Integer domainId, Integer orgId,List<FilterParam> filter);
	public List<SelectItem> getOrganizationEmployee(String dept_code);
	public Boolean delOrgBySuper_code(Integer super_code);


}
