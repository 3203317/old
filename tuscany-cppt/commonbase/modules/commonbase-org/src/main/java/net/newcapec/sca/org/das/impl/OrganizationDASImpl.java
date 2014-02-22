package net.newcapec.sca.org.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.org.Organization;
import net.newcapec.sca.org.das.OrganizationDAS;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class OrganizationDASImpl implements OrganizationDAS{
	private static final Logger organizationDASLogger = Logger.getLogger(OrganizationDASImpl.class);
	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
			this.defDBConnService = defDBConnService;
	}

	//默认的功能模块das配置文件
	private static String DAS_CONFIG = "/org.xml";
	public DAS getDAS()
	{
		String path = "";
		path = this.getClass().getResource(DAS_CONFIG).getPath();
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
		return das;
	}

	public Organization getOrgById(Integer id)
	{
		DAS das = this.getDAS();
		Command command = das.getCommand("getOrganizationById");
		command.setParameter(1, id);
		DataObject root = command.executeQuery();
		List<DataObject> list = root.getList("p_department");
		DataObject p_departmentDO = null;
		Organization organization = null;
		if(null != list && list.size() > 0)
		{
			p_departmentDO = list.get(0);
			organization = this.translateDOTOOrganization(p_departmentDO);
		}
		return organization;
	}

public List<SelectItem> getOrganizationEmployee(String dept_code)
{
	String sql =
		"select\n" +
		"       account_id code,\n" +
		"       name\n" +
		"  from p_user t\n" +
		"  where dept_code = ?\n" +
		"  order by code";

	organizationDASLogger.debug(sql);
	DAS das = this.getDAS();
	Command command = das.createCommand(sql);

	List list = new ArrayList();
	ResultDescriptor code = new ResultDescriptorImpl();
	code.setColumnName("code");
	code.setTableName("p_user");
	code.setColumnIndex(0);
	code.setColumnType("commonj.sdo.String");
	list.add(code);
	ResultDescriptor name = new ResultDescriptorImpl();
	name.setColumnName("name");
	name.setTableName("p_user");
	name.setColumnIndex(1);
	name.setColumnType("commonj.sdo.String");
	list.add(name);

	command.setResultDescriptors(list);
	command.setParameter(1,dept_code);
	DataObject root = command.executeQuery();
	List<DataObject> doList = (List<DataObject>)root.getList("p_user");

	List<SelectItem> employeeList = new ArrayList<SelectItem>();
	for(DataObject userDo : doList)
	{
		SelectItem menu = new SelectItem();
		menu.setId(userDo.getString("code"));
		menu.setName(userDo.getString("name"));
		employeeList.add(menu);
	}

	return employeeList;
}
private Organization translateDOTOOrganization(DataObject p_departmentDO)
{
	Organization organization = new Organization();
	organization.setCode(null == p_departmentDO.get("code")?"":p_departmentDO.get("code").toString());
	organization.setUser_unit_code(null == p_departmentDO.get("user_unit_code")?"":p_departmentDO.get("user_unit_code").toString());
	organization.setName(null == p_departmentDO.get("name")?"":p_departmentDO.get("name").toString());
	organization.setAlias(null == p_departmentDO.get("alias")?"":p_departmentDO.get("alias").toString());
	organization.setShort_hand(null == p_departmentDO.get("short_hand")?"":p_departmentDO.get("short_hand").toString());
	organization.setStatus(null == p_departmentDO.get("status")?"":p_departmentDO.get("status").toString());
	organization.setSuper_code(null == p_departmentDO.get("super_code")?"":p_departmentDO.get("super_code").toString());
	organization.setType_code(null == p_departmentDO.get("type_code")?"":p_departmentDO.get("type_code").toString());
	organization.setAddress(null == p_departmentDO.get("address")?"":p_departmentDO.get("address").toString());
	organization.setIs_have_child(null == p_departmentDO.get("is_have_child")?"":p_departmentDO.get("is_have_child").toString());
	organization.setDepartment_level(null == p_departmentDO.get("department_level")?"":p_departmentDO.get("department_level").toString());
	organization.setCode_path(null == p_departmentDO.get("code_path")?"":p_departmentDO.get("code_path").toString());
	organization.setBatch_code(null == p_departmentDO.get("batch_code")?"":p_departmentDO.get("batch_code").toString());
	organization.setNo_use_date(null == p_departmentDO.get("no_use_date")?"":p_departmentDO.get("no_use_date").toString());
	organization.setOpen_date(null == p_departmentDO.get("open_date")?"":p_departmentDO.get("open_date").toString());
	organization.setCur_use_date(null == p_departmentDO.get("cur_use_date")?"":p_departmentDO.get("cur_use_date").toString());
	organization.setUuid(null == p_departmentDO.get("uuid")?"":p_departmentDO.get("uuid").toString());
	organization.setCreate_user_account_id(null == p_departmentDO.get("create_user_account_id")?"":p_departmentDO.get("create_user_account_id").toString());
	organization.setCreate_date(null == p_departmentDO.get("create_date")?"":p_departmentDO.get("create_date").toString());
	organization.setSortid(null == p_departmentDO.get("sortid")?"":p_departmentDO.get("sortid").toString());
	organization.setVer(null == p_departmentDO.get("ver")?"":p_departmentDO.get("ver").toString());
	return organization;
	}
public Integer getOrgListRowCount(Integer domainId, Integer orgId,List<FilterParam> filter)
{
	String sql = "select count(1) code from p_department " +
	"  where 1 =1  \n" +
	" and user_unit_code = ? \n";
	if (filter != null) {
		for (FilterParam paramItem : filter) {
			sql += " " + paramItem.getRelation() + " "
					+ paramItem.getField() + " " + paramItem.getLogical()
					+ " " + paramItem.getValue();
		}
	}
	organizationDASLogger.debug(sql);
	DAS das = this.getDAS();
	Command command = das.createCommand(sql);
	List<ResultDescriptor> list = new ArrayList<ResultDescriptor>();
	ResultDescriptor code = new ResultDescriptorImpl();
	code.setColumnName("code");
	code.setTableName("p_department");
	code.setColumnIndex(0);
	code.setColumnType("commonj.sdo.String");
	list.add(code);
	command.setResultDescriptors(list);

	command.setParameter(1,orgId);
	Integer rowCount = Integer.valueOf(command.executeQuery().getDataObject("p_department[1]").getString("code"));

	return rowCount;
}
public List<Organization> findOrgList(Integer domainId, Integer orgId,
		List<FilterParam> filter, Integer beginId, Integer limitId) {
		String sql =
			"select * \n" +
			"  from (select code,user_unit_code,name,alias,short_hand,\n" +
			"	status,super_code,type_code,address,\n" +
			"	is_have_child,department_level,code_path,batch_code, \n" +
			"to_char(no_use_date,'yyyy-mm-dd hh24:mi:ss') no_use_date,to_char(open_date,'yyyy-mm-dd hh24:mi:ss') open_date,\n" +
			"to_char(cur_use_date,'yyyy-mm-dd hh24:mi:ss') cur_use_date,uuid," +
			"create_user_account_id,to_char(create_date,'yyyy-mm-dd hh24:mi:ss') create_date,sortid,ver, \n" +
			"       rownum testnum \n" +
			"       from p_department where 1 = 1 \n" +
			"		and user_unit_code = ? \n";
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
			sql = sql + " and testnum > ? ";
		}
		if(null != limitId)
		{
			sql = sql + " and testnum <= ? ";
		}
		sql = sql + " order by code asc ";
		organizationDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List list = this.getFilledResultDescriptionList_p_department();
		command.setResultDescriptors(list);
	//	command.setParameter(1,domainId);
		command.setParameter(1,orgId);
		if(null != beginId)
		{
			command.setParameter(2,beginId);
		}
		if(null != limitId)
		{
			command.setParameter(3,beginId+limitId);
		}
		DataObject root = command.executeQuery();
		List<Organization> organizationList = new ArrayList<Organization>();
		List<DataObject> doList = (List<DataObject>)root.getList("p_department");
		for(DataObject dObj : doList)
		{
			Organization organization = this.translateDOTOOrganization(dObj);
			organizationList.add(organization);
		}
		return organizationList;
	}
	public Boolean insertOrg(Organization organization)
	{
		Boolean rtn = true;
		String sql =
		"  insert into p_department " +
		"  (code,user_unit_code,name,alias,short_hand,status,super_code,type_code,address,is_have_child,department_level,code_path,batch_code,no_use_date,open_date,cur_use_date,uuid,create_user_account_id,create_date,sortid,ver) " +
		"  values(?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss') ,to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,sysdate,?,?)";
		organizationDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);

		command.setParameter(1, null ==organization.getCode()?"":organization.getCode());
		command.setParameter(2, null ==organization.getUser_unit_code()?"":organization.getUser_unit_code());
		command.setParameter(3, null ==organization.getName()?"":organization.getName());
		command.setParameter(4, null ==organization.getAlias()?"":organization.getAlias());
		command.setParameter(5, null ==organization.getShort_hand()?"":organization.getShort_hand());
		command.setParameter(6, null ==organization.getStatus()?"":organization.getStatus());
		command.setParameter(7, null ==organization.getSuper_code()?"":organization.getSuper_code());
		command.setParameter(8, null ==organization.getType_code()?"":organization.getType_code());
		command.setParameter(9, null ==organization.getAddress()?"":organization.getAddress());
		command.setParameter(10, null ==organization.getIs_have_child()?"":organization.getIs_have_child());
		command.setParameter(11, null ==organization.getDepartment_level()?"":organization.getDepartment_level());
		command.setParameter(12, null ==organization.getCode_path()?"":organization.getCode_path());
		command.setParameter(13, null ==organization.getBatch_code()?"":organization.getBatch_code());
		command.setParameter(14, null ==organization.getNo_use_date()?"":organization.getNo_use_date());
		command.setParameter(15, null ==organization.getOpen_date()?"":organization.getOpen_date());
		command.setParameter(16, null ==organization.getCur_use_date()?"":organization.getCur_use_date());
		command.setParameter(17, null ==organization.getUuid()?"":organization.getUuid());
		command.setParameter(18, null ==organization.getCreate_user_account_id()?"":organization.getCreate_user_account_id());
		command.setParameter(19, null ==organization.getSortid()?"":organization.getSortid());
		command.setParameter(20, null ==organization.getVer()?"":organization.getVer());
		try
		{
			command.execute();
		}
		catch (Exception e)
		{
			organizationDASLogger.debug(e.getMessage(),e);
			rtn = false;
		}
			return rtn;
	}
public Boolean updateOrg(Organization organization) {
		Boolean rtn = true;
		DAS das = this.getDAS();
		Command command = das.getCommand("getOrganizationById");
		command.setParameter(1, organization.getCode());
		DataObject root = command.executeQuery();
		DataObject organizationDO = root.getDataObject("p_department[1]");
		if(null != organization.getCode())
		{
		organizationDO.set("code",organization.getCode().toString());
		}
		if(null != organization.getUser_unit_code())
		{
		organizationDO.set("user_unit_code",organization.getUser_unit_code().toString());
		}
		if(null != organization.getName())
		{
		organizationDO.set("name",organization.getName().toString());
		}
		if(null != organization.getAlias())
		{
		organizationDO.set("alias",organization.getAlias().toString());
		}
		if(null != organization.getShort_hand())
		{
		organizationDO.set("short_hand",organization.getShort_hand().toString());
		}
		if(null != organization.getStatus())
		{
		organizationDO.set("status",organization.getStatus().toString());
		}
		if(null != organization.getSuper_code())
		{
		organizationDO.set("super_code",organization.getSuper_code().toString());
		}
		if(null != organization.getType_code())
		{
		organizationDO.set("type_code",organization.getType_code().toString());
		}
		if(null != organization.getAddress())
		{
		organizationDO.set("address",organization.getAddress().toString());
		}
		if(null != organization.getIs_have_child())
		{
		organizationDO.set("is_have_child",organization.getIs_have_child().toString());
		}
		if(null != organization.getDepartment_level())
		{
		organizationDO.set("department_level",organization.getDepartment_level().toString());
		}
		if(null != organization.getCode_path())
		{
		organizationDO.set("code_path",organization.getCode_path().toString());
		}
		if(null != organization.getBatch_code())
		{
		organizationDO.set("batch_code",organization.getBatch_code().toString());
		}
		if(null != organization.getNo_use_date())
		{
		organizationDO.set("no_use_date",organization.getNo_use_date().toString());
		}
		if(null != organization.getOpen_date())
		{
		organizationDO.set("open_date",organization.getOpen_date().toString());
		}
		if(null != organization.getCur_use_date())
		{
		organizationDO.set("cur_use_date",organization.getCur_use_date().toString());
		}
		if(null != organization.getUuid())
		{
		organizationDO.set("uuid",organization.getUuid().toString());
		}
		if(null != organization.getCreate_user_account_id())
		{
		organizationDO.set("create_user_account_id",organization.getCreate_user_account_id().toString());
		}
		if(null != organization.getCreate_date())
		{
		organizationDO.set("create_date",organization.getCreate_date().toString());
		}
		if(null != organization.getSortid())
		{
		organizationDO.set("sortid",organization.getSortid().toString());
		}
		if(null != organization.getVer())
		{
		organizationDO.set("ver",organization.getVer().toString());
		}

		try
		{
			das.applyChanges(root);
		}
		catch (Exception e)
		{
			rtn = false;
			organizationDASLogger.debug(e.getMessage(),e);
		}
		return rtn;
	}
public Boolean delOrgById(Integer id) {
	Boolean rtn = true;
	String sql =
	"  delete p_department where code = ?";
	organizationDASLogger.debug(sql);
	DAS das = this.getDAS();
	Command command = das.createCommand(sql);
	command.setParameter(1, id);
	try
	{
		command.execute();
	}
	catch (Exception e)
	{
		rtn = false;
		organizationDASLogger.debug(e.getMessage(),e);
	}
	return rtn;
	}
public Boolean delOrgBySuper_code(Integer super_code) {
	Boolean rtn = true;
	String sql =
	"  delete p_department where super_code = ?";
	organizationDASLogger.debug(sql);
	DAS das = this.getDAS();
	Command command = das.createCommand(sql);
	command.setParameter(1, super_code);
	try
	{
		command.execute();
	}
	catch (Exception e)
	{
		rtn = false;
		organizationDASLogger.debug(e.getMessage(),e);
	}
	return rtn;
	}

public List getFilledResultDescriptionList_p_department()
{
	List list = new ArrayList();
	ResultDescriptor code = new ResultDescriptorImpl();
	code.setColumnName("code");
	code.setTableName("p_department");
	code.setColumnIndex(0);
	code.setColumnType("commonj.sdo.String");
	list.add(code);
	ResultDescriptor user_unit_code = new ResultDescriptorImpl();
	user_unit_code.setColumnName("user_unit_code");
	user_unit_code.setTableName("p_department");
	user_unit_code.setColumnIndex(1);
	user_unit_code.setColumnType("commonj.sdo.String");
	list.add(user_unit_code);
	ResultDescriptor name = new ResultDescriptorImpl();
	name.setColumnName("name");
	name.setTableName("p_department");
	name.setColumnIndex(2);
	name.setColumnType("commonj.sdo.String");
	list.add(name);
	ResultDescriptor alias = new ResultDescriptorImpl();
	alias.setColumnName("alias");
	alias.setTableName("p_department");
	alias.setColumnIndex(3);
	alias.setColumnType("commonj.sdo.String");
	list.add(alias);
	ResultDescriptor short_hand = new ResultDescriptorImpl();
	short_hand.setColumnName("short_hand");
	short_hand.setTableName("p_department");
	short_hand.setColumnIndex(4);
	short_hand.setColumnType("commonj.sdo.String");
	list.add(short_hand);
	ResultDescriptor status = new ResultDescriptorImpl();
	status.setColumnName("status");
	status.setTableName("p_department");
	status.setColumnIndex(5);
	status.setColumnType("commonj.sdo.String");
	list.add(status);
	ResultDescriptor super_code = new ResultDescriptorImpl();
	super_code.setColumnName("super_code");
	super_code.setTableName("p_department");
	super_code.setColumnIndex(6);
	super_code.setColumnType("commonj.sdo.String");
	list.add(super_code);
	ResultDescriptor type_code = new ResultDescriptorImpl();
	type_code.setColumnName("type_code");
	type_code.setTableName("p_department");
	type_code.setColumnIndex(7);
	type_code.setColumnType("commonj.sdo.String");
	list.add(type_code);
	ResultDescriptor address = new ResultDescriptorImpl();
	address.setColumnName("address");
	address.setTableName("p_department");
	address.setColumnIndex(8);
	address.setColumnType("commonj.sdo.String");
	list.add(address);
	ResultDescriptor is_have_child = new ResultDescriptorImpl();
	is_have_child.setColumnName("is_have_child");
	is_have_child.setTableName("p_department");
	is_have_child.setColumnIndex(9);
	is_have_child.setColumnType("commonj.sdo.String");
	list.add(is_have_child);
	ResultDescriptor department_level = new ResultDescriptorImpl();
	department_level.setColumnName("department_level");
	department_level.setTableName("p_department");
	department_level.setColumnIndex(10);
	department_level.setColumnType("commonj.sdo.String");
	list.add(department_level);
	ResultDescriptor code_path = new ResultDescriptorImpl();
	code_path.setColumnName("code_path");
	code_path.setTableName("p_department");
	code_path.setColumnIndex(11);
	code_path.setColumnType("commonj.sdo.String");
	list.add(code_path);
	ResultDescriptor batch_code = new ResultDescriptorImpl();
	batch_code.setColumnName("batch_code");
	batch_code.setTableName("p_department");
	batch_code.setColumnIndex(12);
	batch_code.setColumnType("commonj.sdo.String");
	list.add(batch_code);
	ResultDescriptor no_use_date = new ResultDescriptorImpl();
	no_use_date.setColumnName("no_use_date");
	no_use_date.setTableName("p_department");
	no_use_date.setColumnIndex(13);
	no_use_date.setColumnType("commonj.sdo.String");
	list.add(no_use_date);
	ResultDescriptor open_date = new ResultDescriptorImpl();
	open_date.setColumnName("open_date");
	open_date.setTableName("p_department");
	open_date.setColumnIndex(14);
	open_date.setColumnType("commonj.sdo.String");
	list.add(open_date);
	ResultDescriptor cur_use_date = new ResultDescriptorImpl();
	cur_use_date.setColumnName("cur_use_date");
	cur_use_date.setTableName("p_department");
	cur_use_date.setColumnIndex(15);
	cur_use_date.setColumnType("commonj.sdo.String");
	list.add(cur_use_date);
	ResultDescriptor uuid = new ResultDescriptorImpl();
	uuid.setColumnName("uuid");
	uuid.setTableName("p_department");
	uuid.setColumnIndex(16);
	uuid.setColumnType("commonj.sdo.String");
	list.add(uuid);
	ResultDescriptor create_user_account_id = new ResultDescriptorImpl();
	create_user_account_id.setColumnName("create_user_account_id");
	create_user_account_id.setTableName("p_department");
	create_user_account_id.setColumnIndex(17);
	create_user_account_id.setColumnType("commonj.sdo.String");
	list.add(create_user_account_id);
	ResultDescriptor create_date = new ResultDescriptorImpl();
	create_date.setColumnName("create_date");
	create_date.setTableName("p_department");
	create_date.setColumnIndex(18);
	create_date.setColumnType("commonj.sdo.String");
	list.add(create_date);
	ResultDescriptor sortid = new ResultDescriptorImpl();
	sortid.setColumnName("sortid");
	sortid.setTableName("p_department");
	sortid.setColumnIndex(19);
	sortid.setColumnType("commonj.sdo.String");
	list.add(sortid);
	ResultDescriptor ver = new ResultDescriptorImpl();
	ver.setColumnName("ver");
	ver.setTableName("p_department");
	ver.setColumnIndex(20);
	ver.setColumnType("commonj.sdo.String");
	list.add(ver);
	return list;
}
}
