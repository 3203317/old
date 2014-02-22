package net.newcapec.sca.resource.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.resource.Resource;
import net.newcapec.sca.resource.bean.MenuItem;
import net.newcapec.sca.resource.bean.Tree;
import net.newcapec.sca.resource.das.ResourceDAS;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class ResourceDASImpl implements ResourceDAS {

	private static final Logger resourceDASLogger = Logger.getLogger(ResourceDASImpl.class);
	public final static String TREEMENUITEM = "1";
	public final static String TREEFUNCTIONITEM = "6";

	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
			this.defDBConnService = defDBConnService;
	}

	//默认的功能模块das配置文件
	private static String DAS_CONFIG = "/resource.xml";
	public DAS getDAS()
	{
		String path = "";
		path = this.getClass().getResource(DAS_CONFIG).getPath();
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
		return das;
	}


	public Resource getResourceById(Integer id) {
		DAS das = this.getDAS();
		Command command = das.getCommand("getResourceById");
		command.setParameter(1, id);
		DataObject root = command.executeQuery();
		DataObject resourceDO = ((DataObject)root.getList("p_resource_info").get(0));
		Resource resource = this.translateDOTOResource(resourceDO);

		return resource;
	}

	public List<Resource> getResourceListByParentId(Integer domainId,
			Integer orgId, Integer parentId) {
		DAS das = this.getDAS();
		Command command = das.getCommand("getResourceListByParentId");
		command.setParameter(1,   domainId);
		command.setParameter(2,  orgId);
		command.setParameter(3,   parentId);
		DataObject root = command.executeQuery();
		List<Resource> resourceList = new ArrayList<Resource>();
		List<DataObject> doResourceList = (List<DataObject>)root.getList("p_resource_info");
		if (null != doResourceList && doResourceList.size() > 0)
		{
			for(DataObject dObj : doResourceList)
			{
				Resource resource = this.translateDOTOResource(dObj);
				resourceList.add(resource);
			}
		}

		return resourceList;
	}
	public Integer getResourceListRowCount(Integer domainId, Integer orgId, List<FilterParam> filter)
	{
		String sql = "select count(1) code from p_resource_info " +
					"  where domain_code = ? \n" +
					" and user_unit_code = ? \n";
		if (filter != null) {
			for (FilterParam paramItem : filter) {
				sql += " " + paramItem.getRelation() + " "
						+ paramItem.getField() + " " + paramItem.getLogical()
						+ " " + paramItem.getValue();
			}
		}
		resourceDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List<ResultDescriptor> list = new ArrayList<ResultDescriptor>();
		ResultDescriptor code = new ResultDescriptorImpl();
		code.setColumnName("code");
		code.setTableName("p_resource_info");
		code.setColumnIndex(0);
		code.setColumnType("commonj.sdo.String");
		list.add(code);
		command.setResultDescriptors(list);

		command.setParameter(1,domainId);
		command.setParameter(2,orgId);

		Integer rowCount = Integer.valueOf(command.executeQuery().getDataObject("p_resource_info[1]").getString("code"));

		return rowCount;
	}
	public List<Resource> findResourceList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer beginId, Integer limitId) {
		String sql =
			"select *\n" +
			"  from (select code,\n" +
			"               user_unit_code,\n" +
			"               resource_type_code,\n" +
			"               name,\n" +
			"               status,\n" +
			"               info,\n" +
			"               domain_code,\n" +
			"               sys_code,\n" +
			"               super_code,\n" +
			"               is_have_child,\n" +
			"               is_enabled,\n" +
			"               small_icon_code,\n" +
			"               large_icon_code,\n" +
			"               url,\n" +
			"               uuid,\n" +
			"               create_user_account_id,\n" +
			"               to_char(create_date, 'yyyy-mm-dd hh24:mi:ss') create_date,\n" +
			"               sortid,\n" +
			"               ver,\n" +
			"               rownum testnum\n" +
			"          from p_resource_info\n" +
			"			where	domain_code = ? \n" +
			"			and user_unit_code = ? \n";
		if (filter != null) {
			for (FilterParam paramItem : filter) {
				sql += " " + paramItem.getRelation() + " "
						+ paramItem.getField() + " " + paramItem.getLogical()
						+ " " + paramItem.getValue();
			}
		}
		sql = sql +") where 1=1\n";
		if(null != beginId)
		{
			sql = sql + " and testnum > ? \n";
		}
		if(null != limitId)
		{
			sql = sql + " and testnum <= ? \n";
		}
		sql = sql + " order by code asc ";
		resourceDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List list = this.getFilledResultDescriptionList_p_resource_info();
		command.setResultDescriptors(list);
		command.setParameter(1,domainId);
		command.setParameter(2,orgId);
		if(null != beginId)
		{
			command.setParameter(3,beginId);
		}
		if(null != limitId)
		{
			command.setParameter(4,beginId+limitId);
		}
		DataObject root = command.executeQuery();
		List<Resource> resourceList = new ArrayList<Resource>();
		for(DataObject dObj : (List<DataObject>)root.getList("p_resource_info"))
		{
			Resource resource = this.translateDOTOResource(dObj);
			resourceList.add(resource);
		}

		return resourceList;
	}

	public Boolean insertResource(Resource resource) {
		Boolean rtn = true;
		String sql =
			"	insert into p_resource_info \n" +
			"	(code, user_unit_code, resource_type_code, name, status, info, domain_code, sys_code, super_code, \n" +
			"	is_have_child, is_enabled, small_icon_code, large_icon_code, url, uuid, create_user_account_id, \n" +
			"	create_date, sortid, ver) \n" +
			"	values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?)\n";
		resourceDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		command.setParameter(1, null ==resource.getCode()?"":resource.getCode());
		command.setParameter(2, null ==resource.getUser_unit_code()?"":resource.getUser_unit_code());
		command.setParameter(3, null ==resource.getResource_type_code()?"":resource.getResource_type_code());
		command.setParameter(4, null ==resource.getName()?"":resource.getName());
		command.setParameter(5, null ==resource.getStatus()?"":resource.getStatus());
		command.setParameter(6, null ==resource.getInfo()?"":resource.getInfo());
		command.setParameter(7, null ==resource.getDomain_code()?"":resource.getDomain_code());
		command.setParameter(8, null ==resource.getSys_code()?"":resource.getSys_code());
		command.setParameter(9, null ==resource.getSuper_code()?"":resource.getSuper_code());
		command.setParameter(10, null ==resource.getIs_have_child()?"":resource.getIs_have_child());
		command.setParameter(11, null ==resource.getIs_enabled()?"":resource.getIs_enabled());
		command.setParameter(12, null ==resource.getSmall_icon_code()?"":resource.getSmall_icon_code());
		command.setParameter(13, null ==resource.getLarge_icon_code()?"":resource.getLarge_icon_code());
		command.setParameter(14, null ==resource.getUrl()?"":resource.getUrl());
		command.setParameter(15, null ==resource.getUuid()?"":resource.getUuid());
		command.setParameter(16, null ==resource.getCreate_user_account_id()?"":resource.getCreate_user_account_id());
		command.setParameter(17, null ==resource.getSortid()?"":resource.getSortid());
		command.setParameter(18, null ==resource.getVer()?"":resource.getVer());
		try
		{
			command.execute();
		}
		catch (Exception e)
		{
			resourceDASLogger.debug(e.getMessage(),e);
			rtn = false;
		}
		return rtn;
	}

	public Boolean updateResource(Resource resource) {
		Boolean rtn = true;
		DAS das = this.getDAS();
		Command command = das.getCommand("getResourceById");
		command.setParameter(1, resource.getCode());
		DataObject root = command.executeQuery();
		DataObject resourceDO = root.getDataObject("p_resource_info[1]");
		if(null != resource.getCode())
		{
		resourceDO.set("code",resource.getCode().toString());
		}
		if(null != resource.getUser_unit_code())
		{
		resourceDO.set("user_unit_code",resource.getUser_unit_code().toString());
		}
		if(null != resource.getResource_type_code())
		{
		resourceDO.set("resource_type_code",resource.getResource_type_code().toString());
		}
		if(null != resource.getName())
		{
		resourceDO.set("name",resource.getName().toString());
		}
		if(null != resource.getStatus())
		{
		resourceDO.set("status",resource.getStatus().toString());
		}
		if(null != resource.getInfo())
		{
		resourceDO.set("info",resource.getInfo().toString());
		}
		if(null != resource.getDomain_code())
		{
		resourceDO.set("domain_code",resource.getDomain_code().toString());
		}
		if(null != resource.getSys_code())
		{
		resourceDO.set("sys_code",resource.getSys_code().toString());
		}
		if(null != resource.getSuper_code())
		{
		resourceDO.set("super_code",resource.getSuper_code().toString());
		}
		if(null != resource.getIs_have_child())
		{
		resourceDO.set("is_have_child",resource.getIs_have_child().toString());
		}
		if(null != resource.getIs_enabled())
		{
		resourceDO.set("is_enabled",resource.getIs_enabled().toString());
		}
		if(null != resource.getSmall_icon_code())
		{
		resourceDO.set("small_icon_code",resource.getSmall_icon_code().toString());
		}
		if(null != resource.getLarge_icon_code())
		{
		resourceDO.set("large_icon_code",resource.getLarge_icon_code().toString());
		}
		if(null != resource.getUrl())
		{
		resourceDO.set("url",resource.getUrl().toString());
		}
		if(null != resource.getUuid())
		{
		resourceDO.set("uuid",resource.getUuid().toString());
		}
		if(null != resource.getCreate_user_account_id())
		{
		resourceDO.set("create_user_account_id",resource.getCreate_user_account_id().toString());
		}
		if(null != resource.getSortid())
		{
		resourceDO.set("sortid",resource.getSortid().toString());
		}
		if(null != resource.getVer())
		{
		resourceDO.set("ver",resource.getVer().toString());
		}

		try
		{
			das.applyChanges(root);
		}
		catch (Exception e)
		{
			rtn = false;
			resourceDASLogger.debug(e.getMessage(),e);
		}
		return rtn;
	}

	public Boolean delResourceById(Integer id) {
		Boolean rtn = true;
		DAS das = this.getDAS();
		String sql =
			"	delete p_resource_info where code = ?";
		resourceDASLogger.debug(sql);
		Command command = das.createCommand(sql);
		command.setParameter(1, id);
		try
		{
			command.execute();
		}
		catch (Exception e)
		{
			rtn = false;
		}

		return rtn;
	}

	private Resource translateDOTOResource(DataObject resourceDO)
	{
		Resource resource = new Resource();
		resource.setDate(resourceDO.get("create_date").toString());
		resource.setDomain_code(Long.valueOf(resourceDO.get("domain_code").toString()));
		resource.setInfo(null ==resourceDO.get("info")?"":resourceDO.get("info").toString());
		resource.setIs_enabled(Long.valueOf(resourceDO.get("is_enabled").toString()));
		resource.setIs_have_child(Long.valueOf(resourceDO.get("is_have_child").toString()));
		resource.setCode(Long.valueOf(resourceDO.get("code").toString()));
		resource.setCreate_user_account_id(Long.valueOf(resourceDO.get("create_user_account_id").toString()));

		resource.setLarge_icon_code(null == resourceDO.get("large_icon_code")?"":resourceDO.get("large_icon_code").toString());
		resource.setName(resourceDO.get("name").toString());
		resource.setResource_type_code(Long.valueOf(resourceDO.get("resource_type_code").toString()));
		resource.setSmall_icon_code(null == resourceDO.get("small_icon_code")?"":resourceDO.get("small_icon_code").toString());
		resource.setSortid(Long.valueOf(resourceDO.get("sortid").toString()));
		resource.setStatus(Long.valueOf(resourceDO.get("status").toString()));
		resource.setSuper_code(Long.valueOf(resourceDO.get("super_code").toString()));
		resource.setSys_code(Long.valueOf(resourceDO.get("sys_code").toString()));

		resource.setUrl(null == resourceDO.get("url")?"":resourceDO.get("url").toString());
		resource.setUuid(null == resourceDO.get("uuid")?"":resourceDO.get("uuid").toString());
		resource.setUser_unit_code(Long.valueOf(resourceDO.get("user_unit_code").toString()));
		resource.setVer(Long.valueOf(resourceDO.get("ver").toString()));

		return resource;
	}

	public List getFilledResultDescriptionList_p_resource_info()
	{
		List list = new ArrayList();
		ResultDescriptor code = new ResultDescriptorImpl();
		code.setColumnName("code");
		code.setTableName("p_resource_info");
		code.setColumnIndex(0);
		code.setColumnType("commonj.sdo.String");
		list.add(code);
		ResultDescriptor user_unit_code = new ResultDescriptorImpl();
		user_unit_code.setColumnName("user_unit_code");
		user_unit_code.setTableName("p_resource_info");
		user_unit_code.setColumnIndex(1);
		user_unit_code.setColumnType("commonj.sdo.String");
		list.add(user_unit_code);
		ResultDescriptor resource_type_code = new ResultDescriptorImpl();
		resource_type_code.setColumnName("resource_type_code");
		resource_type_code.setTableName("p_resource_info");
		resource_type_code.setColumnIndex(2);
		resource_type_code.setColumnType("commonj.sdo.String");
		list.add(resource_type_code);
		ResultDescriptor name = new ResultDescriptorImpl();
		name.setColumnName("name");
		name.setTableName("p_resource_info");
		name.setColumnIndex(3);
		name.setColumnType("commonj.sdo.String");
		list.add(name);
		ResultDescriptor status = new ResultDescriptorImpl();
		status.setColumnName("status");
		status.setTableName("p_resource_info");
		status.setColumnIndex(4);
		status.setColumnType("commonj.sdo.String");
		list.add(status);
		ResultDescriptor info = new ResultDescriptorImpl();
		info.setColumnName("info");
		info.setTableName("p_resource_info");
		info.setColumnIndex(5);
		info.setColumnType("commonj.sdo.String");
		list.add(info);
		ResultDescriptor domain_code = new ResultDescriptorImpl();
		domain_code.setColumnName("domain_code");
		domain_code.setTableName("p_resource_info");
		domain_code.setColumnIndex(6);
		domain_code.setColumnType("commonj.sdo.String");
		list.add(domain_code);
		ResultDescriptor sys_code = new ResultDescriptorImpl();
		sys_code.setColumnName("sys_code");
		sys_code.setTableName("p_resource_info");
		sys_code.setColumnIndex(7);
		sys_code.setColumnType("commonj.sdo.String");
		list.add(sys_code);
		ResultDescriptor super_code = new ResultDescriptorImpl();
		super_code.setColumnName("super_code");
		super_code.setTableName("p_resource_info");
		super_code.setColumnIndex(8);
		super_code.setColumnType("commonj.sdo.String");
		list.add(super_code);
		ResultDescriptor is_have_child = new ResultDescriptorImpl();
		is_have_child.setColumnName("is_have_child");
		is_have_child.setTableName("p_resource_info");
		is_have_child.setColumnIndex(9);
		is_have_child.setColumnType("commonj.sdo.String");
		list.add(is_have_child);
		ResultDescriptor is_enabled = new ResultDescriptorImpl();
		is_enabled.setColumnName("is_enabled");
		is_enabled.setTableName("p_resource_info");
		is_enabled.setColumnIndex(10);
		is_enabled.setColumnType("commonj.sdo.String");
		list.add(is_enabled);
		ResultDescriptor small_icon_code = new ResultDescriptorImpl();
		small_icon_code.setColumnName("small_icon_code");
		small_icon_code.setTableName("p_resource_info");
		small_icon_code.setColumnIndex(11);
		small_icon_code.setColumnType("commonj.sdo.String");
		list.add(small_icon_code);
		ResultDescriptor large_icon_code = new ResultDescriptorImpl();
		large_icon_code.setColumnName("large_icon_code");
		large_icon_code.setTableName("p_resource_info");
		large_icon_code.setColumnIndex(12);
		large_icon_code.setColumnType("commonj.sdo.String");
		list.add(large_icon_code);
		ResultDescriptor url = new ResultDescriptorImpl();
		url.setColumnName("url");
		url.setTableName("p_resource_info");
		url.setColumnIndex(13);
		url.setColumnType("commonj.sdo.String");
		list.add(url);
		ResultDescriptor uuid = new ResultDescriptorImpl();
		uuid.setColumnName("uuid");
		uuid.setTableName("p_resource_info");
		uuid.setColumnIndex(14);
		uuid.setColumnType("commonj.sdo.String");
		list.add(uuid);
		ResultDescriptor create_user_account_id = new ResultDescriptorImpl();
		create_user_account_id.setColumnName("create_user_account_id");
		create_user_account_id.setTableName("p_resource_info");
		create_user_account_id.setColumnIndex(15);
		create_user_account_id.setColumnType("commonj.sdo.String");
		list.add(create_user_account_id);
		ResultDescriptor createdate = new ResultDescriptorImpl();
		createdate.setColumnName("create_date");
		createdate.setTableName("p_resource_info");
		createdate.setColumnIndex(16);
		createdate.setColumnType("commonj.sdo.String");
		list.add(createdate);
		ResultDescriptor sortid = new ResultDescriptorImpl();
		sortid.setColumnName("sortid");
		sortid.setTableName("p_resource_info");
		sortid.setColumnIndex(17);
		sortid.setColumnType("commonj.sdo.String");
		list.add(sortid);
		ResultDescriptor ver = new ResultDescriptorImpl();
		ver.setColumnName("ver");
		ver.setTableName("p_resource_info");
		ver.setColumnIndex(18);
		ver.setColumnType("commonj.sdo.String");
		list.add(ver);
		ResultDescriptor testnum = new ResultDescriptorImpl();
		testnum.setColumnName("testnum");
		testnum.setTableName("p_resource_info");
		testnum.setColumnIndex(19);
		testnum.setColumnType("commonj.sdo.String");
		list.add(testnum);
		return list;
	}

	public MenuItem[] searchSubItem(String account_id,String domainId, String orgId,String name)
	{

		List<MenuItem> list = this.searchMenuItem(account_id,domainId, orgId, name);
		MenuItem[] menuArray = new MenuItem[list.size()];
		list.toArray(menuArray);
		return menuArray;
	}
	public List<MenuItem> searchMenuItem(String account_id,String domainId, String orgId,String name)
	{
		String treeId = name.split("_")[0];
		String parentId = name.split("_")[1];
		List<Resource> list =
			this.getTreeResourceListByParentIdWithRole(account_id,domainId, orgId, parentId);
		List<MenuItem> menuList = new ArrayList<MenuItem>();
		for(Resource resource : list)
		{
			MenuItem menu = new MenuItem();
			menu.setId(treeId + "_" + String.valueOf(resource.getCode()));
			menu.setName(resource.getName());
			menu.setHref(resource.getUrl());
			menu.setParent(name);
			if(TREEFUNCTIONITEM.equals(resource.getResource_type_code().toString()))
			{
				menu.setType("menu");
			}
			if(TREEMENUITEM.equals(resource.getResource_type_code().toString()))
			{
				menu.setType("pmenu");
			}
			menuList.add(menu);
		}
		return menuList;
	}
	private List<Resource> getTreeResourceListByParentIdWithRole
					(String account_id,String domainId, String orgId,String parentId)
	{
		String sql =
			"select p1.*\n" +
			"  from p_resource_info p1\n" +
			" where p1.domain_code = ?\n" +
			"       and p1.user_unit_code = ?\n" +
			"       and p1.super_code =?\n" +
			"   and p1.code not in\n" +
			"       (select distinct (t3.resource_code) code\n" +
			"          from p_role_permission t2, p_user_role t1, p_permission t3\n" +
			"         where t2.role_code = t1.role_code\n" +
			"           and t3.code = t2.permission_code\n" +
			"			and t3.is_enabled = 1\n" +
			"           and t3.operate_code in (3, 4)\n" +
			"           and t1.account_id = ?) order by sortid";
		resourceDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List list = this.getFilledResultDescriptionList_p_resource_info();
		command.setResultDescriptors(list);

		command.setParameter(1,domainId);
		command.setParameter(2,orgId);
		command.setParameter(3,parentId);
		command.setParameter(4,account_id);


		DataObject root = command.executeQuery();
		List<Resource> resourceList = new ArrayList<Resource>();
		for(DataObject dObj : (List<DataObject>)root.getList("p_resource_info"))
		{
			Resource resource = this.translateDOTOResource(dObj);
			resourceList.add(resource);
		}

		return resourceList;
	}
	public List<MenuItem> searchTreeItem(String account_id,String domainId, String orgId,String parentId)
	{

		List<Resource> list =
			this.getTreeResourceListByParentIdWithRole(account_id,domainId, orgId,parentId);
		List<MenuItem> menuList = new ArrayList<MenuItem>();
		for(Resource resource : list)
		{
			MenuItem menu = new MenuItem();
			menu.setId("tree"+parentId + "_" + String.valueOf(resource.getCode()));
			menu.setName(resource.getName());
			menu.setHref(resource.getUrl());
			menu.setParent("tree"+parentId+"_"+parentId);
			//if("0".equals(resource.getIs_have_child().toString()))
			if(TREEFUNCTIONITEM.equals(resource.getResource_type_code().toString()))
			{
				menu.setType("menu");
			}
			if(TREEMENUITEM.equals(resource.getResource_type_code().toString()))
			{
				menu.setType("pmenu");
			}
			menuList.add(menu);
		}
		return menuList;
	}
	/**
	 * 加载菜单树根节点专用
	 * @param account_id
	 * @param domainId
	 * @param orgId
	 * @return
	 */
	private List<Resource> getTreeRootItemWithRole(String account_id,String domainId, String orgId)
	{
		//不加菜单类型1,6是因为权限已经限定了1,7为可访问的菜单权限，3和4为不可访问的菜单权限
		String sql =
			" select * from ("+
			" select p1.*\n" +
			"  from p_resource_info p1,\n" +
			"       (select distinct (t3.resource_code) code\n" +
			"          from p_role_permission t2, p_user_role t1, p_permission t3\n" +
			"         where t2.role_code = t1.role_code\n" +
			"           and t3.code = t2.permission_code\n" +
			"			and t3.is_enabled = 1\n" +
			"           and t3.operate_code in (7, 1)\n" +
			"           and t1.account_id =?) p2\n" +
			" where p1.code = p2.code\n" +
			"		and p1.domain_code = ?\n" +
			"       and p1.user_unit_code = ?\n"+
			"       and p1.super_code = 0\n"+
			"   	and p1.code not in\n" +
			"       (select distinct (t3.resource_code) code\n" +
			"          from p_role_permission t2, p_user_role t1, p_permission t3\n" +
			"         where t2.role_code = t1.role_code\n" +
			"           and t3.code = t2.permission_code\n" +
			"			and t3.is_enabled = 1\n" +
			"           and t3.operate_code in (3, 4)\n" +
			"           and t1.account_id = ?)) p_resource_info order by sortid";

		resourceDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List list = this.getFilledResultDescriptionList_p_resource_info();
		command.setResultDescriptors(list);

		command.setParameter(1,account_id);
		command.setParameter(2,domainId);
		command.setParameter(3,orgId);
		command.setParameter(4,account_id);


		DataObject root = command.executeQuery();
		List<Resource> resourceList = new ArrayList<Resource>();
		for(DataObject dObj : (List<DataObject>)root.getList("p_resource_info"))
		{
			Resource resource = this.translateDOTOResource(dObj);
			resourceList.add(resource);
		}

		return resourceList;
	}
	public Tree[] getTrees(String account_id,String domainId, String orgId)
	{
		List<Resource> resourceList =
			this.getTreeRootItemWithRole(account_id,domainId, orgId);
		List<Tree> treeList = new ArrayList<Tree>();
		for(Resource treeItem : resourceList)
		{
			Tree tree = new Tree();
			tree.setTreeId("tree" + treeItem.getCode() );
			tree.setPaneId("pane"+treeItem.getCode());
			tree.setIconClass(treeItem.getSmall_icon_code());
			tree.setTreeName(treeItem.getName());
			tree.setQueryName(tree.getTreeId() + "_"+ +treeItem.getCode());
			List<MenuItem> list = this.searchTreeItem(account_id,domainId, orgId, treeItem.getCode().toString());
			MenuItem menuItem = new MenuItem();
			menuItem.setId(tree.getTreeId() + "_" +treeItem.getCode());
			menuItem.setType("root");
			menuItem.setName(tree.getTreeName());
			list.add(menuItem);
			MenuItem[] menuArray = new MenuItem[list.size()];
			list.toArray(menuArray);
			tree.setItems(menuArray);
			treeList.add(tree);
		}
		Tree[] treeArray = new Tree[resourceList.size()];
		treeList.toArray(treeArray);

		return treeArray;
	}

}
