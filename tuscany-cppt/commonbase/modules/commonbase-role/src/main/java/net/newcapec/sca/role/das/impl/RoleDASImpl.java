package net.newcapec.sca.role.das.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.role.Role;
import net.newcapec.sca.role.RoleResPerOpt;
import net.newcapec.sca.role.RoleUIPermission;
import net.newcapec.sca.role.das.RoleDAS;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

/**
 * <p>
 * Title: RoleDAS
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
public class RoleDASImpl implements RoleDAS {

	private static final Logger _log = Logger.getLogger(RoleDASImpl.class);

	private SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private SequenceService sequenceService;

	@Reference(name = "sequenceService", required = true)
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}

	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
		this.defDBConnService = defDBConnService;
	}

	// 获得DAS
	public DAS getDAS() {
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(), null);
		return das;
	}

	public Role getRoleById(Integer id) {
		DAS das = this.getDAS();
		String __sql = "select t.* from (select a.code,a.name,a.user_unit_code,a.domain_code,a.max_user_number,a.max_group_number,a.enabled_user_number,a.enabled_group_number,a.enabled_inherit,a.create_user_account_id,to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.ver from p_role a) t where t.code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("user_unit_code");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("domain_code");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("max_user_number");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("max_group_number");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("enabled_user_number");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("enabled_group_number");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("enabled_inherit");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_account_id");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ver");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("role");

		Role __form = null;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			__form = new Role();

			__form.setCode(__data_3.getInt("code"));
			__form.setName(__data_3.getString("name"));
			__form.setUser_unit_code(__data_3.getInt("user_unit_code"));
			__form.setDomain_code(__data_3.getInt("domain_code"));
			__form.setMax_user_number(__data_3.getInt("max_user_number"));
			__form.setMax_group_number(__data_3.getInt("max_group_number"));
			__form.setEnabled_user_number(__data_3.getInt("enabled_user_number"));
			__form.setEnabled_group_number(__data_3.getInt("enabled_group_number"));
			__form.setEnabled_inherit(__data_3.getInt("enabled_inherit"));
			__form.setCreate_user_account_id(__data_3.getInt("create_user_account_id"));
			__form.setCreate_date(__data_3.getString("create_date"));
			__form.setVer(__data_3.getString("ver"));
		}

		return __form;
	}

	public List<Role> findRoleList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer begin, Integer limit) {
		String __sql = "select t.* from (select  rownum row_num, tt.* from (select a.code,a.name,a.user_unit_code,a.domain_code,a.max_user_number,a.max_group_number,a.enabled_user_number,a.enabled_group_number,a.enabled_inherit,a.create_user_account_id,to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.ver" + ",b.name domain_code_text,c.name user_unit_code_text,d.data_value enabled_inherit_text,e.name create_user_account_id_text," + "f.data_value enabled_user_number_text,g.data_value enabled_group_number_text " + "from " + "p_role a, z_domain b,p_unit c,z_code_dictionary_data d,p_user e,z_code_dictionary_data f,z_code_dictionary_data g " + "where " + "a.domain_code=b.code and a.user_unit_code=c.code and a.enabled_inherit=d.data_key and d.code_dictionary_name='BOOLEAN' " + "and a.create_user_account_id=e.account_id "
				+ "and a.enabled_user_number=f.data_key and f.code_dictionary_name='USING' " + "and a.enabled_group_number=g.data_key and g.code_dictionary_name='USING' ";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a." + __param_3.getField() + " " + __param_3.getLogical() + " " + __param_3.getValue();
			}
		}

		__sql += " order by a.code desc) tt) t ";

		if (begin > -1 && limit > -1)
			__sql += "where t.row_num between ? and ? ";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("row_num");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("user_unit_code");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("domain_code");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("max_user_number");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("max_group_number");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("enabled_user_number");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("enabled_group_number");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("enabled_inherit");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_account_id");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ver");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("domain_code_text");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("user_unit_code_text");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("enabled_inherit_text");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_account_id_text");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("enabled_user_number_text");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("enabled_group_number_text");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		if (begin > -1 && limit > -1) {
			__cmd.setParameter(1, begin + 1);
			__cmd.setParameter(2, begin + limit);
		}
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<Role> __form = new ArrayList<Role>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("role")) {
			Role __form_4 = new Role();
			__form_4.setCode(__data_3.getInt("code"));
			__form_4.setName(__data_3.getString("name"));
			__form_4.setUser_unit_code(__data_3.getInt("user_unit_code"));
			__form_4.setDomain_code(__data_3.getInt("domain_code"));
			__form_4.setMax_user_number(__data_3.getInt("max_user_number"));
			__form_4.setMax_group_number(__data_3.getInt("max_group_number"));
			__form_4.setEnabled_user_number(__data_3.getInt("enabled_user_number"));
			__form_4.setEnabled_group_number(__data_3.getInt("enabled_group_number"));
			__form_4.setEnabled_inherit(__data_3.getInt("enabled_inherit"));
			__form_4.setCreate_user_account_id(__data_3.getInt("create_user_account_id"));
			__form_4.setCreate_date(__data_3.getString("create_date"));
			__form_4.setVer(__data_3.getString("ver"));

			__form_4.setDomain_code_text(__data_3.getString("domain_code_text"));
			__form_4.setCreate_user_account_id_text(__data_3.getString("create_user_account_id_text"));
			__form_4.setEnabled_inherit_text(__data_3.getString("enabled_inherit_text"));
			__form_4.setUser_unit_code_text(__data_3.getString("user_unit_code_text"));

			__form_4.setEnabled_group_number_text(__data_3.getString("enabled_group_number_text"));
			__form_4.setEnabled_user_number_text(__data_3.getString("enabled_user_number_text"));

			__form.add(__form_4);
		}
		return __form;
	}

	public Boolean insertRole(Role role) {

		String __sql = "insert into p_role (code,name,user_unit_code,domain_code,max_user_number,max_group_number,enabled_user_number,enabled_group_number,enabled_inherit,create_user_account_id,create_date,ver) values " + "(?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		role.setCode(this.sequenceService.getNextValue("getMaxRoleID"));

		role.setCreate_date(_sdf.format(new Date()));
		role.setVer("1");

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, role.getCode());
		__cmd.setParameter(2, role.getName());
		__cmd.setParameter(3, role.getUser_unit_code());
		__cmd.setParameter(4, role.getDomain_code());
		__cmd.setParameter(5, role.getMax_user_number());
		__cmd.setParameter(6, role.getMax_group_number());
		__cmd.setParameter(7, role.getEnabled_user_number());
		__cmd.setParameter(8, role.getEnabled_group_number());
		__cmd.setParameter(9, role.getEnabled_inherit());
		__cmd.setParameter(10, role.getCreate_user_account_id());
		__cmd.setParameter(11, role.getCreate_date());
		__cmd.setParameter(12, role.getVer());
		_log.debug(__sql);

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			_log.error($ex);
			__result = false;
		} finally {

		}

		return __result;
	}

	public Boolean updateRole(Role role) {
		String __sql = "update p_role set name = ?,user_unit_code = ?,domain_code = ?,max_user_number = ?,max_group_number = ?,enabled_user_number = ?,enabled_group_number = ?,enabled_inherit = ?,ver = ? where code = ?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);

		role.setCreate_user_account_id(1);
		role.setVer("1");

		__cmd.setParameter(1, role.getName());
		__cmd.setParameter(2, role.getUser_unit_code());
		__cmd.setParameter(3, role.getDomain_code());
		__cmd.setParameter(4, role.getMax_user_number());
		__cmd.setParameter(5, role.getMax_group_number());
		__cmd.setParameter(6, role.getEnabled_user_number());
		__cmd.setParameter(7, role.getEnabled_group_number());
		__cmd.setParameter(8, role.getEnabled_inherit());
		__cmd.setParameter(9, role.getVer());
		__cmd.setParameter(10, role.getCode());

		_log.debug(__sql);

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			_log.error($ex);
			__result = false;
		} finally {

		}

		return __result;
	}

	public Boolean delRoleById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean delRoleByIds(Integer[] ids) {
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_role where code in (" + __appendSQL + ")";

		_log.info(__sql);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);

		for (int __i_3 = 1, __j_3 = ids.length; __i_3 <= __j_3; __i_3++) {
			__cmd.setParameter(__i_3, ids[__i_3 - 1]);
		}

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			_log.error($ex);
			__result = false;
		} finally {

		}

		return __result;
	}

	public Integer getRoleListRowCount(String sessionId, Integer resourceId, List<FilterParam> filter) {
		String __sql = "select count(1) count " + "from " + "p_role a, z_domain b,p_unit c,z_code_dictionary_data d,p_user e,z_code_dictionary_data f,z_code_dictionary_data g " + "where " + "a.domain_code=b.code and a.user_unit_code=c.code and a.enabled_inherit=d.data_key and d.code_dictionary_name='BOOLEAN' " + "and a.create_user_account_id=e.account_id " + "and a.enabled_user_number=f.data_key and f.code_dictionary_name='USING' " + "and a.enabled_group_number=g.data_key and g.code_dictionary_name='USING' ";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a." + __param_3.getField() + " " + __param_3.getLogical() + " " + __param_3.getValue();
			}
		}

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("count");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("role");

		int __count = 0;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			__count = __data_3.getInt("count");
		}

		return __count;
	}

	public List<RoleUIPermission> findRoleUIPermissionList(Integer roleId) {
		String __sql = "select a.code resourceId, a.name resourceName, a.super_code resourcePId,? roleId, f_getRoleUiPerByResId(?,a.code) uiPermission from p_resource_info a order by a.sortid";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("resourceId");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("resourceName");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("resourcePId");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("roleId");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("uiPermission");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, roleId);
		__cmd.setParameter(2, roleId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<RoleUIPermission> __form = new ArrayList<RoleUIPermission>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("role")) {
			RoleUIPermission __form_4 = new RoleUIPermission();
			__form_4.setRoleId(roleId);
			__form_4.setResourceId(__data_3.getInt("resourceId"));
			__form_4.setResourceName(__data_3.getString("resourceName"));
			__form_4.setUiPermission(__data_3.getString("uiPermission") == null ? "" : __data_3.getString("uiPermission"));
			__form_4.setResourcePId(__data_3.getInt("resourcePId"));
			__form.add(__form_4);
		}
		return __form;
	}

	public List<RoleResPerOpt> findRolePerOptList(Integer roleId, Integer resId) {
		String __sql = "select t.perId,t.perName,t.resId,t.resName,(select count(1) from p_role_permission b where b.role_code=? and b.permission_code=t.perid) optflag " + "from " + "(select " + "a.code perId,a.name perName, b.code resId,b.name resName " + "from " + "p_permission a,p_datasource b " + "where " + "a.resource_code=b.code and b.code=?) t";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("perId");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("perName");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("resId");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("resName");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("optFlag");
		__rdesc.setTableName("role");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, roleId);
		__cmd.setParameter(2, resId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<RoleResPerOpt> __form = new ArrayList<RoleResPerOpt>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("role")) {
			RoleResPerOpt __form_4 = new RoleResPerOpt();
			__form_4.setPerId(__data_3.getInt("perId"));
			__form_4.setPerName(__data_3.getString("perName"));
			__form_4.setOptFlag(__data_3.getInt("optFlag") == 1);
			__form_4.setResId(__data_3.getInt("resId"));
			__form_4.setResName(__data_3.getString("resName"));
			__form.add(__form_4);
		}
		return __form;
	}
}