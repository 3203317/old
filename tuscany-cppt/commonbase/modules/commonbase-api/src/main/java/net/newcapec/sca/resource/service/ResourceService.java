
package net.newcapec.sca.resource.service;

import java.util.List;

import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.resource.Resource;
import net.newcapec.sca.resource.bean.MenuItem;
import net.newcapec.sca.resource.bean.Tree;

@Remotable
public interface ResourceService {
	/**
	 * 先判断sessionID是否存在，如果存在判断是否失效。session不合法直接返回
	 * @param sessionId
	 * @param id
	 * @return
	 */
	public Resource getResourceById(String sessionId,Integer id);

	public List<Resource>getMenuListByName(String sessionId,String resourceName, Integer limit);

	public List<Resource>getResourceListByParentId(String sessionId, Integer resourceId,Integer parentId);

	public List<Resource> findResourceList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limitId);

	public Resource insertResource(String sessionId,Resource resource);

	public Resource updateResource(String sessionId,Resource resource);

	public Resource delResourceById(String sessionId,Integer id);

	public Resource delResourceByIds(String sessionId,String ids);

	public Integer getResourceListRowCount(String sessionId, List<FilterParam> filter);
	public MenuItem[] searchSubItem(String sessionId , String name);

	public Tree[] getTrees(String sessionId);

	public MenuItem[] getFilteringSelectList(List<FilterParam> filter);

	public DojoListData findResourceDojoList(DojoListParam param);

}
