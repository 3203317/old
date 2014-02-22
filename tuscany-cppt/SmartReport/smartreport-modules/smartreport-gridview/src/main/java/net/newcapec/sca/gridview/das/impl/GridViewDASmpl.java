package net.newcapec.sca.gridview.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.gridview.GridView;
import net.newcapec.sca.gridview.das.GridViewDAS;
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
public class GridViewDASmpl implements GridViewDAS {

	private static final Logger _log = Logger.getLogger(GridViewDASmpl.class);

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

	public GridView getGridViewById(Integer id) {// zpf
													// 把code修改为form_code并且把a.*修改为*
		String __sql = "select t.* from (select a.code,a.form_code,a.toolbuttons,a.condition_groups,a.condition_trees,a.fields from p_gridview a) t where t.form_code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("form_code");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("toolbuttons");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("condition_groups");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("condition_trees");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("fields");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_gridview");

		GridView __gv = null;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			__gv = new GridView();

			__gv.setCode(__data_3.getInt("code"));
			__gv.setCondition_groups(__data_3.getString("condition_groups"));
			__gv.setCondition_trees(__data_3.getString("condition_trees"));
			__gv.setFields(__data_3.getString("fields"));
			__gv.setForm_code(__data_3.getInt("form_code"));

			__gv.setToolbuttons(__data_3.getString("toolbuttons"));
		}

		return __gv;
	}

	public List<GridView> findGridViewList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer begin, Integer limit) {
		String __sql = "select t.* from (select rownum row_num,a.code,a.form_code,a.toolbuttons,a.condition_groups,a.condition_trees,a.fields from p_gridview a where 1=1";

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
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("form_code");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("toolbuttons");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("condition_groups");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("condition_trees");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("fields");
		__rdesc.setTableName("p_gridview");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, begin);
		__cmd.setParameter(2, limit);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<GridView> __gridview = new ArrayList<GridView>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_gridview")) {
			GridView __gridview_4 = new GridView();
			__gridview_4.setCode(__data_3.getInt("code"));
			__gridview_4.setForm_code(__data_3.getInt("form_code"));
			__gridview_4.setToolbuttons(__data_3.getString("toolbuttons"));
			__gridview_4.setCondition_groups(__data_3.getString("condition_groups"));

			__gridview_4.setCondition_trees(__data_3.getString("condition_trees"));
			__gridview_4.setFields(__data_3.getString("fields"));

			__gridview.add(__gridview_4);
		}
		return __gridview;
	}

	public Boolean insertGridView(GridView gridview) {
		// System.out.println(g);
		String __sql = "insert into p_gridview (code, form_code, toolbuttons, condition_groups, condition_trees, fields) values " + "(?, ?, ?, ?, ?, ?)";

		gridview.setCode(this.sequenceService.getNextValue("getMaxGridViewID"));

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, gridview.getCode());
		__cmd.setParameter(2, gridview.getForm_code());
		__cmd.setParameter(3, gridview.getToolbuttons());
		__cmd.setParameter(4, gridview.getCondition_groups());
		__cmd.setParameter(5, gridview.getCondition_trees());
		__cmd.setParameter(6, gridview.getFields());

		_log.debug(__sql);

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			$ex.printStackTrace();
			_log.error($ex);
			__result = false;
		} finally {

		}

		return __result;
	}

	public Boolean updateGridView(GridView gridview) {
		String __sql = "update p_gridview set form_code = ?, toolbuttons = ?, condition_groups = ?, condition_trees = ?, fields = ? where code = ?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, gridview.getForm_code());
		__cmd.setParameter(2, gridview.getToolbuttons());
		__cmd.setParameter(3, gridview.getCondition_groups());
		__cmd.setParameter(4, gridview.getCondition_trees());

		__cmd.setParameter(5, gridview.getFields());
		__cmd.setParameter(6, gridview.getCode());

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

	public Boolean delGridViewById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean delGridViewByIds(Integer[] ids) {
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_gridview where code in (" + __appendSQL + ")";

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
