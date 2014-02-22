package net.newcapec.sca.darule.das.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.darule.DataAccessRule;
import net.newcapec.sca.darule.das.DataAccessRuleDAS;
import net.newcapec.sca.dbconn.service.DefDBConnService;
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
 * Title: DataaccessruleDAS
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
public class DataAccessRuleDASImpl implements DataAccessRuleDAS {

	private static final Logger _log = Logger.getLogger(DataAccessRuleDASImpl.class);

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

	public DataAccessRule getDataAccessRuleByResId(Integer id) {
		String __sql = "select t.* from (select a.code,a.name,a.resource_code,a.type_code,a.content,to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.encryption_info,a.encryption_timestamp,a.ver from p_resource_rule a) t where t.resource_code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("dataaccessrule");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("dataaccessrule");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("resource_code");
		__rdesc.setTableName("dataaccessrule");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type_code");
		__rdesc.setTableName("dataaccessrule");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("content");
		__rdesc.setTableName("dataaccessrule");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("dataaccessrule");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("encryption_info");
		__rdesc.setTableName("dataaccessrule");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("encryption_timestamp");
		__rdesc.setTableName("dataaccessrule");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ver");
		__rdesc.setTableName("dataaccessrule");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("dataaccessrule");

		DataAccessRule __form = null;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			__form = new DataAccessRule();

			__form.setCode(__data_3.getInt("code"));
			__form.setName(__data_3.getString("name"));
			__form.setResource_code(__data_3.getInt("resource_code"));
			__form.setType_code(__data_3.getInt("type_code"));
			__form.setContent(__data_3.getString("content"));
			__form.setCreate_date(__data_3.getString("create_date"));
			__form.setEncryption_info(__data_3.getString("encryption_info"));
			__form.setEncryption_timestamp(__data_3.getString("encryption_timestamp"));
			__form.setVer(__data_3.getString("ver"));
		}

		return __form;
	}

	// public List<DataAccessRule> findDataAccessRuleList(Integer domainId,
	// Integer orgId, List<FilterParam> filter, Integer begin, Integer limit) {
	// String __sql =
	// "select t.* from (select rownum row_num, a.code,a.name,a.resource_code,a.type_code,a.content,to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.encryption_info,a.encryption_timestamp,a.ver from p_resource_rule a where 1=1";
	//
	// if (filter != null) {
	// for (FilterParam __param_3 : filter) {
	// __sql += " " + __param_3.getRelation() + " a." + __param_3.getField() +
	// " " + __param_3.getLogical() + " " + __param_3.getValue();
	// }
	// }
	//
	// __sql += ") t where t.row_num between ? and ? ";
	//
	// _log.debug(__sql);
	//
	// List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
	//
	// ResultDescriptor __rdesc = null;
	//
	// __rdesc = new ResultDescriptorImpl();
	// __rdesc.setColumnName("row_num");
	// __rdesc.setTableName("dataaccessrule");
	// __rdesc.setColumnType("commonj.sdo.String");
	// __list.add(__rdesc);
	//
	// __rdesc = new ResultDescriptorImpl();
	// __rdesc.setColumnName("code");
	// __rdesc.setTableName("dataaccessrule");
	// __rdesc.setColumnType("commonj.sdo.String");
	// __list.add(__rdesc);
	//
	// __rdesc = new ResultDescriptorImpl();
	// __rdesc.setColumnName("name");
	// __rdesc.setTableName("dataaccessrule");
	// __rdesc.setColumnType("commonj.sdo.String");
	// __list.add(__rdesc);
	//
	// __rdesc = new ResultDescriptorImpl();
	// __rdesc.setColumnName("resource_code");
	// __rdesc.setTableName("dataaccessrule");
	// __rdesc.setColumnType("commonj.sdo.String");
	// __list.add(__rdesc);
	//
	// __rdesc = new ResultDescriptorImpl();
	// __rdesc.setColumnName("type_code");
	// __rdesc.setTableName("dataaccessrule");
	// __rdesc.setColumnType("commonj.sdo.String");
	// __list.add(__rdesc);
	//
	// __rdesc = new ResultDescriptorImpl();
	// __rdesc.setColumnName("content");
	// __rdesc.setTableName("dataaccessrule");
	// __rdesc.setColumnType("commonj.sdo.String");
	// __list.add(__rdesc);
	//
	// __rdesc = new ResultDescriptorImpl();
	// __rdesc.setColumnName("create_date");
	// __rdesc.setTableName("dataaccessrule");
	// __rdesc.setColumnType("commonj.sdo.String");
	// __list.add(__rdesc);
	//
	// __rdesc = new ResultDescriptorImpl();
	// __rdesc.setColumnName("encryption_info");
	// __rdesc.setTableName("dataaccessrule");
	// __rdesc.setColumnType("commonj.sdo.String");
	// __list.add(__rdesc);
	//
	// __rdesc = new ResultDescriptorImpl();
	// __rdesc.setColumnName("encryption_timestamp");
	// __rdesc.setTableName("dataaccessrule");
	// __rdesc.setColumnType("commonj.sdo.String");
	// __list.add(__rdesc);
	//
	// __rdesc = new ResultDescriptorImpl();
	// __rdesc.setColumnName("ver");
	// __rdesc.setTableName("dataaccessrule");
	// __rdesc.setColumnType("commonj.sdo.String");
	// __list.add(__rdesc);
	//
	// DAS das = this.getDAS();
	// Command __cmd = das.createCommand(__sql);
	// __cmd.setParameter(1, begin);
	// __cmd.setParameter(2, begin + limit);
	// __cmd.setResultDescriptors(__list);
	//
	// DataObject __data = __cmd.executeQuery();
	// List<DataAccessRule> __form = new ArrayList<DataAccessRule>();
	//
	// for (DataObject __data_3 : (List<DataObject>)
	// __data.getList("dataaccessrule")) {
	// DataAccessRule __form_4 = new DataAccessRule();
	// __form_4.setCode(__data_3.getInt("code"));
	// __form_4.setName(__data_3.getString("name"));
	// __form_4.setResource_code(__data_3.getInt("resource_code"));
	// __form_4.setType_code(__data_3.getInt("type_code"));
	// __form_4.setContent(__data_3.getString("content"));
	// __form_4.setCreate_date(__data_3.getString("create_date"));
	// __form_4.setEncryption_info(__data_3.getString("encryption_info"));
	// __form_4.setEncryption_timestamp(__data_3.getString("encryption_timestamp"));
	// __form_4.setVer(__data_3.getString("ver"));
	//
	// __form.add(__form_4);
	// }
	// return __form;
	// }

	public Boolean insertDataAccessRule(DataAccessRule dataAccessRule) {

		String __sql = "insert into p_resource_rule (code,name,resource_code,type_code,content,create_date,encryption_info,encryption_timestamp,ver) values " + "(?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";

		dataAccessRule.setCode(this.sequenceService.getNextValue("getMaxDataAccessRuleID"));

		dataAccessRule.setCreate_date(_sdf.format(new Date()));
		dataAccessRule.setEncryption_info("1");
		dataAccessRule.setEncryption_timestamp("1");
		dataAccessRule.setVer("1");
		dataAccessRule.setName("A");

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, dataAccessRule.getCode());
		__cmd.setParameter(2, dataAccessRule.getName());
		__cmd.setParameter(3, dataAccessRule.getResource_code());
		__cmd.setParameter(4, dataAccessRule.getType_code());
		__cmd.setParameter(5, dataAccessRule.getContent());
		__cmd.setParameter(6, dataAccessRule.getCreate_date());
		__cmd.setParameter(7, dataAccessRule.getEncryption_info());
		__cmd.setParameter(8, dataAccessRule.getEncryption_timestamp());
		__cmd.setParameter(9, dataAccessRule.getVer());
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

	/**
	 * 通过资源id进行更新
	 */
	public Boolean updateDataAccessRule(DataAccessRule dataAccessRule) {
		String __sql = "update p_resource_rule set  type_code = ?,content = ? where resource_code=?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);

		__cmd.setParameter(1, dataAccessRule.getType_code());
		__cmd.setParameter(2, dataAccessRule.getContent());
		__cmd.setParameter(3, dataAccessRule.getResource_code());

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

	public Boolean delDataAccessRuleByResId(Integer id) {
		String __sql = "delete p_resource_rule where resource_code = ?";

		_log.info(__sql);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);

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

	// public Boolean delDataAccessRuleByIds(Integer[] ids) {
	// if (ids == null || ids.length < 1)
	// return false;
	//
	// String __appendSQL = "";
	//
	// for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
	// __appendSQL += ",?";
	// }
	//
	// __appendSQL = __appendSQL.substring(1);
	//
	// String __sql = "delete p_resource_rule where code in (" + __appendSQL +
	// ")";
	//
	// _log.info(__sql);
	//
	// DAS das = this.getDAS();
	// Command __cmd = das.createCommand(__sql);
	//
	// for (int __i_3 = 1, __j_3 = ids.length; __i_3 <= __j_3; __i_3++) {
	// __cmd.setParameter(__i_3, ids[__i_3 - 1]);
	// }
	//
	// Boolean __result = true;
	//
	// try {
	// __cmd.execute();
	// } catch (Exception $ex) {
	// _log.error($ex);
	// __result = false;
	// } finally {
	//
	// }
	//
	// return __result;
	// }

	// public Integer getDataAccessRuleListRowCount(String sessionId, Integer
	// resourceId, List<FilterParam> filter) {
	// String __sql = "select count(1) count from p_resource_rule where 1=1 ";
	//
	// if (filter != null) {
	// for (FilterParam __param_3 : filter) {
	// __sql += " " + __param_3.getRelation() + " a." + __param_3.getField() +
	// " " + __param_3.getLogical() + " " + __param_3.getValue();
	// }
	// }
	// _log.debug(__sql);
	//
	// List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
	//
	// ResultDescriptor __rdesc = null;
	//
	// __rdesc = new ResultDescriptorImpl();
	// __rdesc.setColumnName("count");
	// __rdesc.setTableName("dataaccessrule");
	// __rdesc.setColumnType("commonj.sdo.String");
	// __list.add(__rdesc);
	//
	// DAS das = this.getDAS();
	// Command __cmd = das.createCommand(__sql);
	// __cmd.setResultDescriptors(__list);
	//
	// DataObject __data = __cmd.executeQuery();
	//
	// List<DataObject> __dataList = __data.getList("dataaccessrule");
	//
	// int __count = 0;
	//
	// if (__dataList != null && __dataList.size() > 0) {
	// DataObject __data_3 = __dataList.get(0);
	// __count = __data_3.getInt("count");
	// }
	//
	// return __count;
	// }

}
