package net.newcapec.sca.role.das.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.role.RolePermision;
import net.newcapec.sca.role.das.RolePermisionDAS;
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
 * Title: RolepermisionDAS
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
public class RolePermisionDASImpl implements RolePermisionDAS {

	private static final Logger _log = Logger
			.getLogger(RolePermisionDASImpl.class);

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
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),
				null);
		return das;
	}

	public RolePermision getRolePermisionById(Integer id) {
		String __sql = "select t.* from (select a.code,a.role_code,a.permission_code,a.create_user_account_id,to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.ver from p_role_permission a) t where t.code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("role_code");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("permission_code");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_account_id");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ver");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);


		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("rolepermision");

		RolePermision __form = null;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			__form = new RolePermision();

			__form.setCode(__data_3.getInt("code"));
			__form.setRole_code(__data_3.getInt("role_code"));
			__form.setPermission_code(__data_3.getInt("permission_code"));
			__form.setCreate_user_account_id(__data_3
					.getInt("create_user_account_id"));
			__form.setCreate_date(__data_3.getString("create_date"));
			__form.setVer(__data_3.getString("ver"));
		}

		return __form;
	}

	public List<RolePermision> findRolePermisionList(Integer domainId,
			Integer orgId, List<FilterParam> filter, Integer begin,
			Integer limit) {
		// String __sql =
		// "select t.* from (select rownum row_num, a.code,a.role_code,a.permission_code,a.create_user_account_id,to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.ver from p_role_permission a where 1=1";

		String __sql = "select t.* from (select  rownum row_num, tt.* from (select a.*,b.name role_name,c.name per_name "
				+ "from p_role_permission a, p_role b,p_permission c "
				+ "where b.code=a.role_code and c.code=a.permission_code ";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a."
						+ __param_3.getField() + " " + __param_3.getLogical()
						+ " " + __param_3.getValue();
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
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("role_code");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("permission_code");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_account_id");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ver");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("role_name");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("per_name");
		__rdesc.setTableName("rolepermision");
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
		List<RolePermision> __form = new ArrayList<RolePermision>();

		for (DataObject __data_3 : (List<DataObject>) __data
				.getList("rolepermision")) {
			RolePermision __form_4 = new RolePermision();
			__form_4.setCode(__data_3.getInt("code"));
			__form_4.setRole_code(__data_3.getInt("role_code"));
			__form_4.setPermission_code(__data_3.getInt("permission_code"));
			__form_4.setCreate_user_account_id(__data_3
					.getInt("create_user_account_id"));
			__form_4.setCreate_date(__data_3.getString("create_date"));
			__form_4.setVer(__data_3.getString("ver"));

			__form_4.setRole_name(__data_3.getString("role_name"));
			__form_4.setPermission_name(__data_3.getString("per_name"));

			__form.add(__form_4);
		}
		return __form;
	}

	public Boolean insertRolePermision(RolePermision rolePermision) {

		String __sql = "insert into p_role_permission (code,role_code,permission_code,create_user_account_id,create_date,ver) values "
				+ "(?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		rolePermision.setCode(this.sequenceService
				.getNextValue("getMaxRolePermisionID"));

		rolePermision.setCreate_date(_sdf.format(new Date()));
		rolePermision.setVer("1");
		rolePermision.setCreate_user_account_id(1);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, rolePermision.getCode());
		__cmd.setParameter(2, rolePermision.getRole_code());
		__cmd.setParameter(3, rolePermision.getPermission_code());
		__cmd.setParameter(4, rolePermision.getCreate_user_account_id());
		__cmd.setParameter(5, rolePermision.getCreate_date());
		__cmd.setParameter(6, rolePermision.getVer());
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

	public Boolean updateRolePermision(RolePermision rolePermision) {
		String __sql = "update p_role_permission set role_code = ?,permission_code = ?,ver = ? where code = ?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);

		__cmd.setParameter(1, rolePermision.getRole_code());
		__cmd.setParameter(2, rolePermision.getPermission_code());
		__cmd.setParameter(3, rolePermision.getVer());
		__cmd.setParameter(4, rolePermision.getCode());

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

	public Boolean delRolePermisionById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean delRolePermisionByIds(Integer[] ids) {
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_role_permission where code in (" + __appendSQL
				+ ")";

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

	public Integer getRolePermisionListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter) {
		String __sql = "select count(1) count from p_role_permission where 1=1 ";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a."
						+ __param_3.getField() + " " + __param_3.getLogical()
						+ " " + __param_3.getValue();
			}
		}
		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("count");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("rolepermision");

		int __count = 0;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			__count = __data_3.getInt("count");
		}

		return __count;
	}

	public List<RolePermision> findUnRolePermisionList(Integer domainId,
			Integer orgId, Integer roleId, Integer begin, Integer limit) {
		// String __sql =
		// "select t.* from (select rownum row_num, a.code,a.role_code,a.permission_code,a.create_user_account_id,to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.ver from p_role_permission a where 1=1";

		String __sql = "select t.* from (select  rownum row_num, tt.* from (select c.code permission_code, c.name per_name "
				+ "from p_permission c "
				+ "where c.code not in (select a.permission_code from p_role_permission a where a.role_code=?) ";

		__sql += " order by c.code desc) tt) t ";

		if (begin > -1 && limit > -1)
			__sql += "where t.row_num between ? and ? ";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("row_num");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("permission_code");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("per_name");
		__rdesc.setTableName("rolepermision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, roleId);
		if (begin > -1 && limit > -1) {
			__cmd.setParameter(2, begin + 1);
			__cmd.setParameter(3, begin + limit);
		}
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<RolePermision> __form = new ArrayList<RolePermision>();

		for (DataObject __data_3 : (List<DataObject>) __data
				.getList("rolepermision")) {
			RolePermision __form_4 = new RolePermision();
			__form_4.setCode(0);
			__form_4.setRole_code(0);
			__form_4.setPermission_code(__data_3.getInt("permission_code"));
			__form_4.setCreate_user_account_id(0);
			__form_4.setCreate_date("");
			__form_4.setVer("0");

			__form_4.setRole_name("");
			__form_4.setPermission_name(__data_3.getString("per_name"));

			__form.add(__form_4);
		}
		return __form;
	}

	public Boolean delRolePermisionByRoleId(Integer roleId) {
		String __sql = "delete p_role_permission where role_code = ?";

		_log.info(__sql);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, roleId);

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

	public Boolean delRolePermisionByRoldPerId(Integer roleId, Integer perId) {
		String __sql = "delete p_role_permission where role_code = ? and permission_code = ?";

		_log.info(__sql);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, roleId);
		__cmd.setParameter(2, perId);

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
}