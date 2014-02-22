package net.newcapec.sca.resource.das;

import java.util.List;

import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.resource.Resource;
import net.newcapec.sca.resource.bean.MenuItem;
import net.newcapec.sca.resource.bean.Tree;

public interface ResourceDAS {
	public Resource getResourceById(Integer id);
	public List<Resource> getResourceListByParentId(Integer domainId, Integer orgId, Integer parentId);
	public List<Resource> findResourceList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer beginId, Integer limitId);
	public Boolean insertResource(Resource resource);
	public Boolean updateResource(Resource resource);
	public Boolean delResourceById(Integer id);
	public Integer getResourceListRowCount(Integer domainId, Integer orgId,  List<FilterParam> filter);
	public MenuItem[] searchSubItem(String account_id,String domainId, String orgId,String name);

	public Tree[] getTrees(String account_id,String domainId, String orgId);
}
