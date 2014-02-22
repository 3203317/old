package net.newcapec.sca.gridfield.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.gridfield.GridField;
import net.newcapec.sca.gridfield.das.GridFieldDAS;
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
public class GridFieldDASmpl implements GridFieldDAS {

	private static final Logger _log = Logger.getLogger(GridFieldDASmpl.class);

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

	public GridField getGridFieldById(Integer id) {// zpf code modify form_code
		String __sql = "select t.* from (select a.code,a.form_code,a.name,a.format from p_gridfield a) t where t.form_code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("form_code");
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("format");
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_gridfield");

		GridField __gf = null;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			__gf = new GridField();

			__gf.setCode(__data_3.getInt("code"));
			__gf.setForm_code(__data_3.getInt("form_code"));
			__gf.setFormat(__data_3.getString("format"));
			__gf.setName(__data_3.getString("name"));
		}

		return __gf;
	}

	public List<GridField> findGridFieldList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer begin, Integer limit) {
		String __sql = "select t.* from (select rownum row_num,a.code,a.form_code,a.name,a.format from p_gridfield a where 1=1";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a." + __param_3.getField() + " " + __param_3.getLogical() + " " + __param_3.getValue();
			}
		}

		__sql += ") t where t.row_num between ? and ? ";
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
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("form_code");
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("format");
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, begin);
		__cmd.setParameter(2, limit);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<GridField> __gridfield = new ArrayList<GridField>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_gridfield")) {
			GridField __gf_4 = new GridField();
			__gf_4.setCode(__data_3.getInt("code"));
			__gf_4.setForm_code(__data_3.getInt("form_code"));
			__gf_4.setName(__data_3.getString("name"));
			__gf_4.setFormat(__data_3.getString("format"));

			__gridfield.add(__gf_4);
		}
		return __gridfield;
	}

	public Boolean insertGridField(GridField gridField) {

		String __sql = "insert into p_gridfield (code, form_code, name, format) values (?, ?, ?, ?)";

		gridField.setCode(this.sequenceService.getNextValue("getMaxGridFieldID"));

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, gridField.getCode());
		__cmd.setParameter(2, gridField.getForm_code());
		__cmd.setParameter(3, gridField.getName());
		__cmd.setParameter(4, gridField.getFormat());

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

	public Boolean updateGridField(GridField gridField) {
		String __sql = "update p_gridfield set form_code = ?, name = ?, format = ? where code = ?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, gridField.getForm_code());
		System.out.println("updateGridField  ++++++++++++++++++++++++++++++++++++++ "+gridField.getName());
		__cmd.setParameter(2, gridField.getName());
		__cmd.setParameter(3, gridField.getFormat());
		__cmd.setParameter(4, gridField.getCode());

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

	public Boolean delGridFieldById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean delGridFieldByIds(Integer[] ids) {
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_gridfield where code in (" + __appendSQL + ")";

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

	public List<GridField> findGridFieldListByCondition(List<FilterParam> filter) {

		String __sql = "  select code, form_code, name, format  from  p_gridfield  a  where 1=1 ";
		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a." + __param_3.getField() + " " + __param_3.getLogical() + " " + __param_3.getValue();
			}
		}

		__sql += "  order by code  asc";
		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("form_code");
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("format");
		__rdesc.setTableName("p_gridfield");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<GridField> __form = new ArrayList<GridField>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_gridfield")) {
			GridField _gridField = new GridField();

			_gridField.setCode(__data_3.getInt("code"));
			_gridField.setForm_code(__data_3.getInt("form_code"));
			_gridField.setName(__data_3.getString("name"));
			_gridField.setFormat(__data_3.getString("format"));
			__form.add(_gridField);
		}
		return __form;

	}

}
