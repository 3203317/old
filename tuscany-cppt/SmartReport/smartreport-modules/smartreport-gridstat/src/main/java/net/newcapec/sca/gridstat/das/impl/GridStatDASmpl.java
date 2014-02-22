package net.newcapec.sca.gridstat.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.gridstat.GridStat;
import net.newcapec.sca.gridstat.das.GridStatDAS;
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
public class GridStatDASmpl implements GridStatDAS {

	private static final Logger _log = Logger.getLogger(GridStatDASmpl.class);

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

	public GridStat getGridStatById(Integer id) {// zpf code-----form_code
		String __sql = "select t.* from (select a.code,a.form_code,a.type,a.field_type_list from p_gridstat a) t where t.form_code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_gridstat");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("form_code");
		__rdesc.setTableName("p_gridstat");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_gridstat");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_type_list");
		__rdesc.setTableName("p_gridstat");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_gridstat");

		GridStat __gs = null;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			__gs = new GridStat();

			__gs.setCode(__data_3.getInt("code"));
			__gs.setField_type_list(__data_3.getString("field_type_list"));
			__gs.setForm_code(__data_3.getInt("form_code"));
			__gs.setType(__data_3.getInt("type"));
		}

		return __gs;
	}

	public List<GridStat> findGridStatList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer begin, Integer limit) {
		String __sql = "select t.* from (select rownum row_num,a.code,a.form_code,a.type,a.field_type_list from p_gridstat a where 1=1";

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
		__rdesc.setTableName("p_gridstat");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("form_code");
		__rdesc.setTableName("p_gridstat");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_gridstat");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_type_list");
		__rdesc.setTableName("p_gridstat");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, begin);
		__cmd.setParameter(2, limit);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<GridStat> __gridstat = new ArrayList<GridStat>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_gridstat")) {
			GridStat __gridstat_4 = new GridStat();
			__gridstat_4.setCode(__data_3.getInt("code"));
			__gridstat_4.setForm_code(__data_3.getInt("form_code"));
			__gridstat_4.setType(__data_3.getInt("type"));
			__gridstat_4.setField_type_list(__data_3.getString("field_type_list"));

			__gridstat.add(__gridstat_4);
		}
		return __gridstat;
	}

	public Boolean insertGridStat(GridStat gridStat) {

		String __sql = "insert into p_gridstat (code, form_code, type, field_type_list) values (?, ?, ?, ?)";

		gridStat.setCode(this.sequenceService.getNextValue("getMaxGridStatID"));

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, gridStat.getCode());
		__cmd.setParameter(2, gridStat.getForm_code());
		__cmd.setParameter(3, gridStat.getType());
		__cmd.setParameter(4, gridStat.getField_type_list());

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

	public Boolean updateGridStat(GridStat gridStat) {
		String __sql = "update p_gridstat set form_code = ?, type = ?, field_type_list = ? where code = ?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, gridStat.getForm_code());
		__cmd.setParameter(2, gridStat.getType());
		__cmd.setParameter(3, gridStat.getField_type_list());
		__cmd.setParameter(4, gridStat.getCode());

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

	public Boolean delGridStatById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean delGridStatByIds(Integer[] ids) {
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_gridstat where code in (" + __appendSQL + ")";

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
}
