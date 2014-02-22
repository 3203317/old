package net.newcapec.sca.userrole.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.user.UserBaseInfo;
import net.newcapec.sca.userrole.OneUserManyRoles;
import net.newcapec.sca.userrole.RoleInfo_Unit;
import net.newcapec.sca.userrole.UserRole;
import net.newcapec.sca.userrole.das.UserRoleDAS;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class UserRoleDASImpl implements UserRoleDAS
{
	private static final Logger userRoleDASLog = Logger.getLogger(UserRoleDASImpl.class);
	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
			this.defDBConnService = defDBConnService;
	}

	//默认的功能模块das配置文件
	private static String DAS_CONFIG = "/userRole.xml";
	public DAS getDAS()
	{
		String path = "";
		path = this.getClass().getResource(DAS_CONFIG).getPath();
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
		return das;
	}

	public UserRole getUserRoleById(Integer id)
	{
		DAS das = this.getDAS();
		Command command = das.getCommand("getUserRoleById");
		command.setParameter(1, id);
		DataObject root = command.executeQuery();
		List<DataObject> list = root.getList("p_user_role");
		DataObject p_user_roleDO = null;
		UserRole userrole = null;
		if(null != list && list.size() > 0)
		{
			p_user_roleDO = list.get(0);
			userrole = this.translateDOTOUserRole(p_user_roleDO);
		}

		return userrole;
	}
	private UserRole translateDOTOUserRole(DataObject p_user_roleDO)
	{
		UserRole userrole = new UserRole();
		userrole.setCode(null == p_user_roleDO.get("code")?"":p_user_roleDO.get("code").toString());
		userrole.setAccount_id(null == p_user_roleDO.get("account_id")?"":p_user_roleDO.get("account_id").toString());
		userrole.setRole_code(null == p_user_roleDO.get("role_code")?"":p_user_roleDO.get("role_code").toString());
		userrole.setCreate_user_account_id(null == p_user_roleDO.get("create_user_account_id")?"":p_user_roleDO.get("create_user_account_id").toString());
		userrole.setCreate_date(null == p_user_roleDO.get("create_date")?"":p_user_roleDO.get("create_date").toString());
		userrole.setVer(null == p_user_roleDO.get("ver")?"":p_user_roleDO.get("ver").toString());
		return userrole;
	}
	public List<RoleInfo_Unit> findRoleInfo_UnitList(List<FilterParam> filter, Integer begin , Integer limit) {
		String sql =
			"select userrole_code,role_code,user_name,account_id,role_name,norow from(\n" +
			"select rownum norow, t1.code userrole_code, t1.role_code role_code, t2.name user_name, t2.account_id account_id, t3.name role_name\n" +
			"  from p_user_role t1, p_user t2, p_role t3\n" +
			" where t1.account_id = t2.account_id\n" +
			"   and t3.code = t1.role_code" ;

		sql = sql +"  ) roleinfo_unit where 1=1\n";
		if (filter != null) {
			for (FilterParam paramItem : filter) {
				sql += " " + paramItem.getRelation() + " "
						+ paramItem.getField() + " " + paramItem.getLogical()
						+ " " + paramItem.getValue();
			}
		}
		if(null != begin)
		{
		sql = sql + " and norow > ? \n";
		}
		if(null != limit)
		{
		sql = sql + " and norow <= ? \n";
		}

		sql = sql +" order by userrole_code desc";
		userRoleDASLog.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List<ResultDescriptor>  list = this.getFilledResultDescriptionList_roleinfo_unit();
		command.setResultDescriptors(list);
		if(null != begin )
		{
			command.setParameter(1,begin );
		}
		if(null != limit)
		{
			command.setParameter(2,begin + limit);
		}
		DataObject root = null;
		try
		{
			root = command.executeQuery();
		}catch (Exception e)
		{
			userRoleDASLog.debug(e.getMessage(),e);
		}
		List<RoleInfo_Unit> roleinfo_unitList = new ArrayList<RoleInfo_Unit>();
		List<DataObject> doList = (List<DataObject>)root.getList("roleinfo_unit");
		if(doList != null && doList.size() >= 1)
		{
			for(DataObject dObj : doList)
			{
				RoleInfo_Unit roleinfo_unit = this.translateDOTORoleInfo_Unit(dObj);
				roleinfo_unitList.add(roleinfo_unit);
			}
		}

		return roleinfo_unitList;
	}

	public List<OneUserManyRoles> findUserAndRoles(List<UserBaseInfo> selectList)
	{
		List<OneUserManyRoles> rtnList = new ArrayList<OneUserManyRoles>();

		for(UserBaseInfo item : selectList)
		{
			OneUserManyRoles  oumr = new OneUserManyRoles();
			oumr.setAccount_id(item.getCode());
			oumr.setUser_name(item.getName());
			oumr.setDyn1(item.getSex());
			oumr.setDyn2(item.getAccount_id());
			oumr.setUserRoles(this.getRolesByAccount_id(item.getCode()));
			rtnList.add(oumr);
		}

		return rtnList;
	}

	public List<UserBaseInfo> getOrganizationEmployee(List<FilterParam> filter,Integer begin,Integer limit)
	{
		String sql =

			"select account_id,name,sex,code from (select\n" +
			"           account_id ,\n" +
			"           name,\n" +
			"            rownum norow,sex,code \n" +
			"      from p_user t\n" +
			"      where 1=1\n" ;

		if (filter != null) {
			for (FilterParam paramItem : filter) {
				sql += " " + paramItem.getRelation() + " "
						+ paramItem.getField() + " " + paramItem.getLogical()
						+ " " + paramItem.getValue();
			}
		}
		sql = sql +"  order by code) p_user where 1=1\n";
		if(null != begin)
		{
			sql = sql + " and norow > ? \n";
		}
		if(null != limit)
		{
			sql = sql + " and norow <= ? \n";
		}
		userRoleDASLog.debug(sql);
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
		ResultDescriptor sex = new ResultDescriptorImpl();
		sex.setColumnName("sex");
		sex.setTableName("p_user");
		sex.setColumnIndex(2);
		sex.setColumnType("commonj.sdo.String");
		list.add(sex);
		ResultDescriptor account_id = new ResultDescriptorImpl();
		account_id.setColumnName("account_id");
		account_id.setTableName("p_user");
		account_id.setColumnIndex(3);
		account_id.setColumnType("commonj.sdo.String");
		list.add(account_id);

		command.setResultDescriptors(list);
		command.setParameter(1,begin);
		command.setParameter(2,limit);
		DataObject root = command.executeQuery();
		List<DataObject> doList = (List<DataObject>)root.getList("p_user");

		List<UserBaseInfo> employeeList = new ArrayList<UserBaseInfo>();
		for(DataObject userDo : doList)
		{
			UserBaseInfo userBaseInfo = new UserBaseInfo();
			userBaseInfo.setCode(userDo.getString("code"));
			userBaseInfo.setName(userDo.getString("name"));
			userBaseInfo.setSex(userDo.getString("sex"));
			userBaseInfo.setAccount_id(userDo.getString("account_id"));
			employeeList.add(userBaseInfo);
		}

		return employeeList;
	}

	private String getRolesByAccount_id(String account_id)
	{
		List<FilterParam> paramList = new ArrayList<FilterParam>();
		FilterParam filter = new FilterParam();
		filter.setField("account_id");
		filter.setLogical("=");
		filter.setRelation("and");
		filter.setValue(account_id);
		paramList.add(filter);
		//list的role_code对应allRoleSelectItemList的中的id字段为角色id
		List<UserRole> list = this.findUserRoleList(1, 1, paramList, null, null);

		List<SelectItem> allRoleSelectItemList = this.findAllRoles();
		StringBuffer userRoleStr = new StringBuffer();
		for(UserRole userRole : list)
		{
			for(SelectItem item : allRoleSelectItemList)
			{
				if(item.getId().equals(userRole.getRole_code()))
				{
					userRoleStr.append(item.getName()+",");
				}
			}
		}
		String rtnStr = userRoleStr.toString();
		if(rtnStr.length() > 1)
		{
			rtnStr = rtnStr.substring(0, rtnStr.length()-1);
		}
		return rtnStr;
	}
	public List<SelectItem> getRolesWhichWillBe(String account_id,String domain_code,String orgCode)
	{
		List<SelectItem> rtnList = new ArrayList<SelectItem>();
		String sql =
			"select t1.code, t1.name\n" +
			"  from p_role t1\n" +
			" where t1.code not in\n" +
			"       (select t2.role_code from p_user_role t2 where t2.account_id = ?)\n" +
			"   and t1.domain_code = ?\n" +
			"   and t1.user_unit_code = ?";

		userRoleDASLog.debug(sql);
		List<ResultDescriptor> list = new ArrayList<ResultDescriptor>();

		ResultDescriptor rdesc = null;

		rdesc = new ResultDescriptorImpl();
		rdesc.setColumnName("code");
		rdesc.setColumnIndex(0);
		rdesc.setTableName("p_role");
		rdesc.setColumnType("commonj.sdo.String");
		list.add(rdesc);

		rdesc = new ResultDescriptorImpl();
		rdesc.setColumnName("name");
		rdesc.setColumnIndex(1);
		rdesc.setTableName("p_role");
		rdesc.setColumnType("commonj.sdo.String");
		list.add(rdesc);
		DAS das = this.getDAS();
		Command cmd = das.createCommand(sql);
		cmd.setResultDescriptors(list);
		cmd.setParameter(1,account_id);
		cmd.setParameter(2, domain_code);
		cmd.setParameter(3,orgCode);

		DataObject root = cmd.executeQuery();

		List<DataObject> rootList = root.getList("p_role");

		for(DataObject item : rootList)
		{
			SelectItem sobj = new SelectItem();
			sobj.setId(item.getString(0));
			sobj.setName(item.getString(1));
			rtnList.add(sobj);
		}

		return rtnList;
	}
	private List<SelectItem> findAllRoles()
	{
		List<SelectItem> rtnList = new ArrayList<SelectItem>();
		String sql =
			"select code,name from p_role t";
		userRoleDASLog.debug(sql);
		List<ResultDescriptor> list = new ArrayList<ResultDescriptor>();

		ResultDescriptor rdesc = null;

		rdesc = new ResultDescriptorImpl();
		rdesc.setColumnName("code");
		rdesc.setTableName("p_role");
		rdesc.setColumnType("commonj.sdo.String");
		list.add(rdesc);

		rdesc = new ResultDescriptorImpl();
		rdesc.setColumnName("name");
		rdesc.setTableName("p_role");
		rdesc.setColumnType("commonj.sdo.String");
		list.add(rdesc);
		DAS das = this.getDAS();
		Command cmd = das.createCommand(sql);
		cmd.setResultDescriptors(list);

		DataObject root = cmd.executeQuery();

		List<DataObject> rootList = root.getList("p_role");

		for(DataObject item : rootList)
		{
			SelectItem sobj = new SelectItem();
			sobj.setId(item.getString("code"));
			sobj.setName(item.getString("name"));
			rtnList.add(sobj);
		}

		return rtnList;
	}

	public List<UserRole> findUserRoleList(Integer domainId, Integer orgId,
				List<FilterParam> filter, Integer beginId, Integer limitId) {
		String sql =
			"select * " +
			"  from (select code,account_id,role_code,create_user_account_id,create_date,ver, " +
			"       rownum testnum " +
			"       from p_user_role " +
			"       where  1 = 1   ";
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
		userRoleDASLog.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List list = this.getFilledResultDescriptionList_p_user_role();
		command.setResultDescriptors(list);
		if(null != beginId)
		{
		command.setParameter(1,beginId);
		}
		if(null != limitId)
		{
		command.setParameter(2,beginId+limitId);
		}
		DataObject root = command.executeQuery();
		List<UserRole> userroleList = new ArrayList<UserRole>();
		List<DataObject> doList = (List<DataObject>)root.getList("p_user_role");
		for(DataObject dObj : doList)
		{
		UserRole userrole = this.translateDOTOUserRole(dObj);
		userroleList.add(userrole);
		}

		return userroleList;
	}
	public Boolean insertUserRole(UserRole userrole) {
		Boolean rtn = true;
		String sql =
		"  insert into p_user_role " +
		"  (code,account_id,role_code,create_user_account_id,create_date,ver) " +
		"  values(?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss') ,?)";
		userRoleDASLog.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);

		command.setParameter(1, null ==userrole.getCode()?"":userrole.getCode());
		command.setParameter(2, null ==userrole.getAccount_id()?"":userrole.getAccount_id());
		command.setParameter(3, null ==userrole.getRole_code()?"":userrole.getRole_code());
		command.setParameter(4, null ==userrole.getCreate_user_account_id()?"":userrole.getCreate_user_account_id());
		command.setParameter(5, null ==userrole.getCreate_date()?"":userrole.getCreate_date());
		command.setParameter(6, null ==userrole.getVer()?"":userrole.getVer());
		try
		{
		command.execute();
		}
		catch (Exception e)
		{
		userRoleDASLog.debug(e.getMessage(),e);
		rtn = false;
		}
		return rtn;
	}


	public Boolean updateUserRole(UserRole userrole) {
		Boolean rtn = true;
		DAS das = this.getDAS();
		Command command = das.getCommand("getUserRoleById");
		command.setParameter(1, userrole.getCode());
		DataObject root = command.executeQuery();
		DataObject userroleDO = root.getDataObject("p_user_role[1]");
		if(null != userrole.getCode())
		{
			userroleDO.set("code",userrole.getCode().toString());
		}
		if(null != userrole.getAccount_id())
		{
			userroleDO.set("account_id",userrole.getAccount_id().toString());
		}
		if(null != userrole.getRole_code())
		{
			userroleDO.set("role_code",userrole.getRole_code().toString());
		}
		if(null != userrole.getCreate_user_account_id())
		{
			userroleDO.set("create_user_account_id",userrole.getCreate_user_account_id().toString());
		}
		if(null != userrole.getCreate_date())
		{
			userroleDO.set("create_date",userrole.getCreate_date().toString());
		}
		if(null != userrole.getVer())
		{
			userroleDO.set("ver",userrole.getVer().toString());
		}

		try
		{
			das.applyChanges(root);
		}
		catch (Exception e)
		{
			rtn = false;
			userRoleDASLog.debug(e.getMessage(),e);
		}
		return rtn;
	}
public Boolean delUserRoleById(Integer id) {
	Boolean rtn = true;
	DAS das = this.getDAS();
	String sql =
	"  delete p_user_role where code = ?";
	userRoleDASLog.debug(sql);
	Command command = das.createCommand(sql);
	command.setParameter(1, id);
	try
	{
		command.execute();
	}
	catch (Exception e)
	{
		userRoleDASLog.debug(e.getMessage(),e);
		rtn = false;
	}

	return rtn;
}
private RoleInfo_Unit translateDOTORoleInfo_Unit(DataObject roleinfo_unitDO)
{
	RoleInfo_Unit roleinfo_unit = new RoleInfo_Unit();
	roleinfo_unit.setUserrole_code(null == roleinfo_unitDO.get("userrole_code")?"":roleinfo_unitDO.get("userrole_code").toString());
	roleinfo_unit.setRole_code(null == roleinfo_unitDO.get("role_code")?"":roleinfo_unitDO.get("role_code").toString());
	roleinfo_unit.setUser_name(null == roleinfo_unitDO.get("user_name")?"":roleinfo_unitDO.get("user_name").toString());
	roleinfo_unit.setAccount_id(null == roleinfo_unitDO.get("account_id")?"":roleinfo_unitDO.get("account_id").toString());
	roleinfo_unit.setRole_name(null == roleinfo_unitDO.get("role_name")?"":roleinfo_unitDO.get("role_name").toString());
	return roleinfo_unit;
}
public List<ResultDescriptor> getFilledResultDescriptionList_roleinfo_unit()
{
	List<ResultDescriptor> list = new ArrayList<ResultDescriptor>();

	ResultDescriptor userrole_code = new ResultDescriptorImpl();
	userrole_code.setColumnName("userrole_code");
	userrole_code.setTableName("roleinfo_unit");
	userrole_code.setColumnIndex(0);
	userrole_code.setColumnType("commonj.sdo.String");
	list.add(userrole_code);
	ResultDescriptor role_code = new ResultDescriptorImpl();
	role_code.setColumnName("role_code");
	role_code.setTableName("roleinfo_unit");
	role_code.setColumnIndex(1);
	role_code.setColumnType("commonj.sdo.String");
	list.add(role_code);
	ResultDescriptor user_name = new ResultDescriptorImpl();
	user_name.setColumnName("user_name");
	user_name.setTableName("roleinfo_unit");
	user_name.setColumnIndex(2);
	user_name.setColumnType("commonj.sdo.String");
	list.add(user_name);
	ResultDescriptor account_id = new ResultDescriptorImpl();
	account_id.setColumnName("account_id");
	account_id.setTableName("roleinfo_unit");
	account_id.setColumnIndex(3);
	account_id.setColumnType("commonj.sdo.String");
	list.add(account_id);
	ResultDescriptor role_name = new ResultDescriptorImpl();
	role_name.setColumnName("role_name");
	role_name.setTableName("roleinfo_unit");
	role_name.setColumnIndex(4);
	role_name.setColumnType("commonj.sdo.String");
	list.add(role_name);
	ResultDescriptor norow = new ResultDescriptorImpl();
	norow.setColumnName("norow");
	norow.setTableName("roleinfo_unit");
	norow.setColumnIndex(5);
	norow.setColumnType("commonj.sdo.String");
	list.add(norow);
	return list;
}
public List getFilledResultDescriptionList_p_user_role()
{
	List list = new ArrayList();
	ResultDescriptor code = new ResultDescriptorImpl();
	code.setColumnName("code");
	code.setTableName("p_user_role");
	code.setColumnIndex(0);
	code.setColumnType("commonj.sdo.String");
	list.add(code);
	ResultDescriptor account_id = new ResultDescriptorImpl();
	account_id.setColumnName("account_id");
	account_id.setTableName("p_user_role");
	account_id.setColumnIndex(1);
	account_id.setColumnType("commonj.sdo.String");
	list.add(account_id);
	ResultDescriptor role_code = new ResultDescriptorImpl();
	role_code.setColumnName("role_code");
	role_code.setTableName("p_user_role");
	role_code.setColumnIndex(2);
	role_code.setColumnType("commonj.sdo.String");
	list.add(role_code);
	ResultDescriptor create_user_account_id = new ResultDescriptorImpl();
	create_user_account_id.setColumnName("create_user_account_id");
	create_user_account_id.setTableName("p_user_role");
	create_user_account_id.setColumnIndex(3);
	create_user_account_id.setColumnType("commonj.sdo.String");
	list.add(create_user_account_id);
	ResultDescriptor create_date = new ResultDescriptorImpl();
	create_date.setColumnName("create_date");
	create_date.setTableName("p_user_role");
	create_date.setColumnIndex(4);
	create_date.setColumnType("commonj.sdo.String");
	list.add(create_date);
	ResultDescriptor ver = new ResultDescriptorImpl();
	ver.setColumnName("ver");
	ver.setTableName("p_user_role");
	ver.setColumnIndex(5);
	ver.setColumnType("commonj.sdo.String");
	list.add(ver);
	return list;
}
}
