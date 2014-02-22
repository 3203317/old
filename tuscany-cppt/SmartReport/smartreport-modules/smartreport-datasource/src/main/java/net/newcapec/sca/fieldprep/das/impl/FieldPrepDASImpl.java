package net.newcapec.sca.fieldprep.das.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.fieldprep.FieldPrep;
import net.newcapec.sca.fieldprep.das.FieldPrepDAS;
import net.newcapec.sca.param.FilterParam;
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
 *
 * @author huangxin
 *
 */
public class FieldPrepDASImpl implements FieldPrepDAS {

	private static final Logger _log = Logger.getLogger(FieldPrepDASImpl.class);

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

	public FieldPrep getFieldPrepById(Integer id) {
		String __sql = "select t.* from (select a.code,a.ds_code,a.name,a.alias,a.type,a.input_type,a.regexp,a.memo from p_fieldprep a) t where t.code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("alias");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("input_type");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("regexp");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("memo");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_fieldprep");

		FieldPrep __res_4 = null;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			__res_4 = new FieldPrep();

			__res_4.setCode(__data_3.getInt("code"));
			__res_4.setDs_code(__data_3.getInt("ds_code"));
			__res_4.setName(__data_3.getString("name"));

			__res_4.setAlias(__data_3.getString("alias"));
			__res_4.setType(__data_3.getString("type"));
			__res_4.setInput_type(__data_3.getString("input_type"));

			__res_4.setRegexp(__data_3.getString("regexp"));
			__res_4.setMemo(__data_3.getString("memo"));
		}

		return __res_4;
	}

	public List<FieldPrep> findFieldPrepList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit) {
		String __sql = "select t.* from ("
				+ "select rownum row_num,a.code,a.ds_code,a.name,a.alias,a.type,a.input_type,a.regexp,a.memo from p_fieldprep a where 1=1";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a."
						+ __param_3.getField() + " " + __param_3.getLogical()
						+ " " + __param_3.getValue();
			}
		}
		__sql += ") t where t.row_num between ? and ? order by t.code asc";
		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("row_num");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("alias");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("input_type");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("regexp");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("memo");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, begin);
		__cmd.setParameter(2, limit);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<FieldPrep> __source = new ArrayList<FieldPrep>();

		for (DataObject __data_3 : (List<DataObject>) __data
				.getList("p_fieldprep")) {
			FieldPrep __res_4 = new FieldPrep();
			__res_4.setCode(__data_3.getInt("code"));
			__res_4.setDs_code(__data_3.getInt("ds_code"));
			__res_4.setName(__data_3.getString("name"));

			__res_4.setAlias(__data_3.getString("alias"));
			__res_4.setType(__data_3.getString("type"));
			__res_4.setInput_type(__data_3.getString("input_type"));

			__res_4.setRegexp(__data_3.getString("regexp"));
			__res_4.setMemo(__data_3.getString("memo"));

			__source.add(__res_4);
		}
		return __source;
	}

	public Boolean insertFieldPrep(FieldPrep fieldPrep) {

		String __sql = "insert into p_fieldprep (code, ds_code, name, alias, type, input_type, regexp, memo) values "
				+ "(?, ?, ?, ?, ?, ?, ?, ?)";

		fieldPrep.setCode(this.sequenceService
				.getNextValue("getMaxFieldPrepID"));

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, fieldPrep.getCode());
		__cmd.setParameter(2, fieldPrep.getDs_code());
		__cmd.setParameter(3, fieldPrep.getName());
		__cmd.setParameter(4, fieldPrep.getAlias());
		__cmd.setParameter(5, fieldPrep.getType());
		__cmd.setParameter(6, fieldPrep.getInput_type());
		__cmd.setParameter(7, fieldPrep.getRegexp());
		__cmd.setParameter(8, fieldPrep.getMemo());

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

	public Boolean updateFieldPrep(FieldPrep fieldPrep) {
		String __sql = "update p_fieldprep set ds_code = ?, name = ?, alias = ?, type = ?, input_type = ?, regexp = ?, memo = ? where code = ?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, fieldPrep.getDs_code());
		__cmd.setParameter(2, fieldPrep.getName());
		__cmd.setParameter(3, fieldPrep.getAlias());
		__cmd.setParameter(4, fieldPrep.getType());
		__cmd.setParameter(5, fieldPrep.getInput_type());
		__cmd.setParameter(6, fieldPrep.getRegexp());
		__cmd.setParameter(7, fieldPrep.getMemo());
		__cmd.setParameter(8, fieldPrep.getCode());

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

	public Boolean delFieldPrepById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean delFieldPrepByIds(Integer[] ids) {
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_fieldprep where code in (" + __appendSQL + ")";

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

	public Integer getFieldPrepListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter) {
		String __sql = "select t.* from ("
				+ "select count(1) count from p_fieldprep a where 1=1";

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
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_fieldprep");

		int __count = 0;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			__count = __data_3.getInt("count");
		}

		return __count;
	}

	public List<FieldPrep> findFieldByDscode(Integer ds_code) {

		String __sql = "select t.* from (select a.code,a.ds_code,a.name,a.alias,a.type,a.input_type,a.regexp,a.memo from p_fieldprep a) t where t.ds_code=?";

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("alias");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("input_type");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("regexp");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("memo");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, ds_code);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<FieldPrep> __source = new ArrayList<FieldPrep>();
		List<DataObject> rootList = (List<DataObject>)__data.getList("p_fieldprep");
		//先判断list是否为空否则在for循环中会报错
		if(rootList == null && rootList.size() == 0)
		{
			return null;
		}
		else
		{
			for (DataObject __data_3 :rootList ) {
				FieldPrep __res_4 = new FieldPrep();
				__res_4.setCode(__data_3.getInt("code"));
				__res_4.setDs_code(__data_3.getInt("ds_code"));
				__res_4.setName(__data_3.getString("name"));

				__res_4.setAlias(__data_3.getString("alias"));
				__res_4.setType(__data_3.getString("type"));
				__res_4.setInput_type(__data_3.getString("input_type"));

				__res_4.setRegexp(__data_3.getString("regexp"));
				__res_4.setMemo(__data_3.getString("memo"));
				__source.add(__res_4);
			}
			return __source;
		}
	}

	public Boolean delFieldPrepByDsId(Integer id) {
		String __sql = "delete p_fieldprep where ds_code = ?";
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

}
