package net.newcapec.sca.permision.das.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.permision.Permision;
import net.newcapec.sca.permision.das.PermisionDAS;
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
 * Title: PermisionDAS
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
public class PermisionDASImpl implements PermisionDAS {

	private static final Logger _log = Logger.getLogger(PermisionDASImpl.class);

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

	public Permision getPermisionById(Integer id) {
		String __sql = "select t.* from (select a.code,a.name,a.user_unit_code,a.info,a.sys_code,a.operate_code,a.resource_code,a.is_enabled,a.is_visible,a.create_user_account_id,to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.ver from p_permission a) t where t.code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("user_unit_code");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("info");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("sys_code");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("operate_code");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("resource_code");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("is_enabled");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("is_visible");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_account_id");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ver");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("permision");

		Permision __form = null;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			__form = new Permision();

			__form.setCode(__data_3.getInt("code"));
			__form.setName(__data_3.getString("name"));
			__form.setUser_unit_code(__data_3.getInt("user_unit_code"));
			__form.setInfo(__data_3.getString("info"));
			__form.setSys_code(__data_3.getInt("sys_code"));
			__form.setOperate_code(__data_3.getInt("operate_code"));
			__form.setResource_code(__data_3.getInt("resource_code"));
			__form.setIs_enabled(__data_3.getInt("is_enabled"));
			__form.setIs_visible(__data_3.getInt("is_visible"));
			__form.setCreate_user_account_id(__data_3.getInt("create_user_account_id"));
			__form.setCreate_date(__data_3.getString("create_date"));
			__form.setVer(__data_3.getString("ver"));
		}

		return __form;
	}

	public List<Permision> findPermisionList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer begin, Integer limit) {
		String __sql = "select t.* from (select  rownum row_num, tt.* from (select a.code,a.name,a.user_unit_code,a.info,a.sys_code,a.operate_code," + "a.resource_code,a.is_enabled,a.is_visible,a.create_user_account_id," + "to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.ver," + "b.name user_unit_code_text,c.name sys_code_text,d.name operate_code_text," + "e.name resource_code_text,f.data_value is_enabled_text,g.data_value is_visible_text," + "h.name create_user_account_id_text " + "from " + "p_permission a,p_unit b,p_app_system c,p_resource_operate d,p_resource_info e," + "z_code_dictionary_data f,z_code_dictionary_data g,p_user h " + "where " + "a.user_unit_code=b.code " + "and a.sys_code=c.code " + "and a.operate_code =d.code " + "and a.resource_code=e.code " + "and a.is_enabled=f.data_key and f.code_dictionary_name='USING' "
				+ "and a.is_Visible=g.data_key and g.code_dictionary_name='VISIBLE' " + "and a.create_user_account_id=h.account_id ";

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
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("user_unit_code");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("info");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("sys_code");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("operate_code");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("resource_code");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("is_enabled");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("is_visible");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_account_id");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ver");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		/* ********************************************* */

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("user_unit_code_text");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("sys_code_text");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("operate_code_text");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("resource_code_text");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("is_enabled_text");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("is_visible_text");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_account_id_text");
		__rdesc.setTableName("permision");
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
		List<Permision> __form = new ArrayList<Permision>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("permision")) {
			Permision __form_4 = new Permision();
			__form_4.setCode(__data_3.getInt("code"));
			__form_4.setName(__data_3.getString("name"));
			__form_4.setUser_unit_code(__data_3.getInt("user_unit_code"));
			__form_4.setInfo(__data_3.getString("info"));
			__form_4.setSys_code(__data_3.getInt("sys_code"));
			__form_4.setOperate_code(__data_3.getInt("operate_code"));
			__form_4.setResource_code(__data_3.getInt("resource_code"));
			__form_4.setIs_enabled(__data_3.getInt("is_enabled"));
			__form_4.setIs_visible(__data_3.getInt("is_visible"));
			__form_4.setCreate_user_account_id(__data_3.getInt("create_user_account_id"));
			__form_4.setCreate_date(__data_3.getString("create_date"));
			__form_4.setVer(__data_3.getString("ver"));

			__form_4.setCreate_user_account_id_text(__data_3.getString("create_user_account_id_text"));
			__form_4.setIs_enabled_text(__data_3.getString("is_enabled_text"));
			__form_4.setIs_visible_text(__data_3.getString("is_visible_text"));
			__form_4.setOperate_code_text(__data_3.getString("operate_code_text"));
			__form_4.setResource_code_text(__data_3.getString("resource_code_text"));
			__form_4.setSys_code_text(__data_3.getString("sys_code_text"));
			__form_4.setUser_unit_code_text(__data_3.getString("user_unit_code_text"));

			__form.add(__form_4);
		}
		return __form;
	}

	public Boolean insertPermision(Permision permision) {

		String __sql = "insert into p_permission (code,name,user_unit_code,info,sys_code,operate_code,resource_code,is_enabled,is_visible,create_user_account_id,create_date,ver) values " + "(?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		permision.setCode(this.sequenceService.getNextValue("getMaxPermisionID"));

		permision.setCreate_date(_sdf.format(new Date()));
		permision.setVer("1");

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, permision.getCode());
		__cmd.setParameter(2, permision.getName().trim());
		__cmd.setParameter(3, permision.getUser_unit_code());
		__cmd.setParameter(4, permision.getInfo() == null ? "" : permision.getInfo().trim());
		__cmd.setParameter(5, permision.getSys_code());
		__cmd.setParameter(6, permision.getOperate_code());
		__cmd.setParameter(7, permision.getResource_code());
		__cmd.setParameter(8, permision.getIs_enabled());
		__cmd.setParameter(9, permision.getIs_visible());
		__cmd.setParameter(10, permision.getCreate_user_account_id());
		__cmd.setParameter(11, permision.getCreate_date());
		__cmd.setParameter(12, permision.getVer());
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

	public Boolean updatePermision(Permision permision) {
		String __sql = "update p_permission set name = ?,user_unit_code = ?,info = ?,sys_code = ?,operate_code = ?,resource_code = ?,is_enabled = ?,is_visible = ?,ver = ? where code=?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		permision.setVer("1");

		__cmd.setParameter(1, permision.getName());
		__cmd.setParameter(2, permision.getUser_unit_code());
		__cmd.setParameter(3, permision.getInfo());
		__cmd.setParameter(4, permision.getSys_code());
		__cmd.setParameter(5, permision.getOperate_code());
		__cmd.setParameter(6, permision.getResource_code());
		__cmd.setParameter(7, permision.getIs_enabled());
		__cmd.setParameter(8, permision.getIs_visible());
		__cmd.setParameter(9, permision.getVer());
		__cmd.setParameter(10, permision.getCode());

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

	public Boolean delPermisionById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean delPermisionByIds(Integer[] ids) {
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_permission where code in (" + __appendSQL + ")";

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

	public Integer getPermisionListRowCount(String sessionId, Integer resourceId, List<FilterParam> filter) {
		String __sql = "select count(1) count " + "from " + "p_permission a,p_unit b,p_app_system c,p_resource_operate d,p_resource_info e," + "z_code_dictionary_data f,z_code_dictionary_data g,p_user h " + "where " + "a.user_unit_code=b.code " + "and a.sys_code=c.code " + "and a.operate_code =d.code " + "and a.resource_code=e.code " + "and a.is_enabled=f.data_key and f.code_dictionary_name='USING' " + "and a.is_Visible=g.data_key and g.code_dictionary_name='VISIBLE' " + "and a.create_user_account_id=h.account_id ";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a." + __param_3.getField() + " " + __param_3.getLogical() + " " + __param_3.getValue();
			}
		}

		_log.info(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("count");
		__rdesc.setTableName("permision");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("permision");

		int __count = 0;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			__count = __data_3.getInt("count");
		}

		return __count;
	}
}