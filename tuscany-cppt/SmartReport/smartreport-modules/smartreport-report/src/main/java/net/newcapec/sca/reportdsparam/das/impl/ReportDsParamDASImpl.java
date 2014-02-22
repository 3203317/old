package net.newcapec.sca.reportdsparam.das.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.datasource.DataSource;
import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.reportdsparam.ReportDsParam;
import net.newcapec.sca.reportdsparam.das.ReportDsParamDAS;
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
public class ReportDsParamDASImpl implements ReportDsParamDAS {

	private static final Logger _log = Logger.getLogger(ReportDsParamDASImpl.class);

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

	public List<ReportDsParam> findReportDsParamList(Integer domainId, Integer orgId, Integer reportDsId, Integer dsId, String dsType) {
		if ("SQL".equals(dsType)) {
			return this.findReportDsParamList3(domainId, orgId, reportDsId);
		} else {
			int __count_3 = this.getReportDsParamListCount(reportDsId);

			if (__count_3 == 0) {
				this.insertReportDsParam(reportDsId, dsId);
			}

			return this.findReportDsParamList3(domainId, orgId, reportDsId);
		}
	}

	public List<ReportDsParam> findReportDsParamList3(Integer domainId, Integer orgId, Integer reportDsId) {
		String __sql = "select a.*,t.component_type,t.component_datasource,t.dataset_name,t.text_field,t.value_field," +
				"t.parent_field,t.top_default,a.field_name||'_'||a.code as field_name_alias from  p_report_ds_param a left join p_componentdataset t on t.code = a.widget_ds_code "+
						"where   a.report_ds_code = ? order by a.code";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_type");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("report_ds_code");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("logical");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_name");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("relation");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_value");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_type");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("label");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("input_type");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("widget_ds_code");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("component_type");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("component_datasource");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("dataset_name");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("text_field");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("value_field");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("parent_type");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("top_default");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_name_alias");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);



		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportDsId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<ReportDsParam> __reportdsparam = new ArrayList<ReportDsParam>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_report_ds_param")) {
			ReportDsParam __reportdsparam_4 = new ReportDsParam();
			__reportdsparam_4.setCode(__data_3.getInt("code"));
			__reportdsparam_4.setDs_type(__data_3.getString("ds_type"));
			__reportdsparam_4.setReport_ds_code(__data_3.getInt("report_ds_code"));

			__reportdsparam_4.setLogical(__data_3.getString("logical"));
			__reportdsparam_4.setField_name(__data_3.getString("field_name"));
			__reportdsparam_4.setRelation(__data_3.getString("relation"));
			__reportdsparam_4.setField_value(__data_3.getString("field_value"));
			__reportdsparam_4.setField_type(__data_3.getString("field_type"));

			__reportdsparam_4.setLabel(__data_3.getString("label"));
			__reportdsparam_4.setInput_type(__data_3.getString("input_type"));
			__reportdsparam_4.setWidget_ds_code(__data_3.getInt("widget_ds_code"));

			__reportdsparam_4.setWidget_ds_label(__data_3.getInt("widget_ds_code") == 0?"":__data_3.getString("dataset_name"));
			__reportdsparam_4.setWidget_datasource_code(__data_3.getInt("widget_ds_code") == 0?0:__data_3.getInt("component_datasource"));
			__reportdsparam_4.setWidget_key(__data_3.getString("value_field"));
			__reportdsparam_4.setWidget_value(__data_3.getString("text_field"));
			__reportdsparam_4.setWidget_parent_field(__data_3.getString("parent_field"));
			__reportdsparam_4.setWidget_top_default(__data_3.getString("top_default"));
			__reportdsparam_4.setWidget_type(__data_3.getInt("widget_ds_code") == 0?"":__data_3.getString("component_type"));

			__reportdsparam_4.setField_name_alias(__data_3.getString("field_name_alias"));
			__reportdsparam.add(__reportdsparam_4);
		}
		return __reportdsparam;
	}

	public List<ReportDsParam> findReportDsParamList2(Integer domainId, Integer orgId, Integer reportId) {
		String __sql = "select a.*,a.field_name||'_'||a.code as field_name_alias from p_report_ds_param a where a.report_ds_code=(select t.code from p_report_ds t where t.report_code=? and t.type=1) order by a.code";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_type");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("report_ds_code");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("logical");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_name");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("relation");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_value");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_type");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("label");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("input_type");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("widget_ds_code");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_name_alias");
		__rdesc.setTableName("p_report_ds_param");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<ReportDsParam> __reportdsparam = new ArrayList<ReportDsParam>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_report_ds_param")) {
			ReportDsParam __reportdsparam_4 = new ReportDsParam();
			__reportdsparam_4.setCode(__data_3.getInt("code"));
			__reportdsparam_4.setDs_type(__data_3.getString("ds_type"));
			__reportdsparam_4.setReport_ds_code(__data_3.getInt("report_ds_code"));

			__reportdsparam_4.setLogical(__data_3.getString("logical"));
			__reportdsparam_4.setField_name(__data_3.getString("field_name"));
			__reportdsparam_4.setRelation(__data_3.getString("relation"));
			__reportdsparam_4.setField_value(__data_3.getString("field_value"));
			__reportdsparam_4.setField_type(__data_3.getString("field_type"));

			__reportdsparam_4.setLabel(__data_3.getString("label") == null ? "" : __data_3.getString("label"));
			__reportdsparam_4.setInput_type(__data_3.getString("input_type") == null ? "" : __data_3.getString("input_type"));
			__reportdsparam_4.setWidget_ds_code(__data_3.getInt("widget_ds_code"));

			__reportdsparam_4.setField_name_alias(__data_3.getString("field_name_alias"));
			__reportdsparam.add(__reportdsparam_4);
		}
		return __reportdsparam;
	}

	public ReportDsParam insertReportDsParam(ReportDsParam reportDsParam) {
		String __sql = "insert into p_report_ds_param (code, ds_type, report_ds_code, logical, field_name, relation, field_value, field_type,label,input_type,widget_ds_code) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		reportDsParam.setCode(this.sequenceService.getNextValue("getMaxReportDsParamID"));

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportDsParam.getCode());
		__cmd.setParameter(2, reportDsParam.getDs_type());
		__cmd.setParameter(3, reportDsParam.getReport_ds_code());
		__cmd.setParameter(4, reportDsParam.getLogical());
		__cmd.setParameter(5, reportDsParam.getField_name());
		__cmd.setParameter(6, reportDsParam.getRelation());
		__cmd.setParameter(7, reportDsParam.getField_value());
		__cmd.setParameter(8, reportDsParam.getField_type());

		__cmd.setParameter(9, reportDsParam.getLabel());
		__cmd.setParameter(10, reportDsParam.getInput_type());
		__cmd.setParameter(11, reportDsParam.getWidget_ds_code());

		_log.debug(__sql);

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			_log.error($ex);
			$ex.printStackTrace();
		} finally {

		}

		return reportDsParam;
	}

	private Boolean insertReportDsParam(Integer reportDsId, Integer dsId) {
		DataSource __ds = this.getDataSourceById(dsId);

		this.getDbProcInParamByUser(__ds.getDb_account(), __ds.getDb_password(), __ds.getIp(), __ds.getPort(), __ds.getServer_id(), __ds.getMethod(), reportDsId);

		return true;
	}

	public Boolean updateReportDsParam(ReportDsParam reportDsParam) {
		String __sql = "update p_report_ds_param set logical=?, relation = ?, field_value=?, label=?, input_type=?, widget_ds_code=? where code = ?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportDsParam.getLogical());
		__cmd.setParameter(2, reportDsParam.getRelation());
		__cmd.setParameter(3, reportDsParam.getField_value());
		__cmd.setParameter(4, reportDsParam.getLabel());
		__cmd.setParameter(5, reportDsParam.getInput_type());
		__cmd.setParameter(6, reportDsParam.getWidget_ds_code());
		__cmd.setParameter(7, reportDsParam.getCode());

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

	public Boolean delReportDsParamByIds(Integer[] ids) {
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_report_ds_param where code in (" + __appendSQL + ")";

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

	private int getReportDsParamListCount(Integer reportDsId) {
		String __sql = "select count(1) count from p_report_ds_param a where a.report_ds_code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("count");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportDsId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_report");

		int __count = 0;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			__count = __data_3.getInt("count");
		}

		return __count;
	}

	private DataSource getDataSourceById(Integer id) {
		String __sql = "select a.method,b.db_account,b.db_password,b.ip,b.port,b.server_id from p_datasource a,p_dbconn b where a.dbconn_code=b.code and a.code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("method");
		__rdesc.setTableName("p_datasource");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("db_account");
		__rdesc.setTableName("p_datasource");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("db_password");
		__rdesc.setTableName("p_datasource");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		//

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ip");
		__rdesc.setTableName("p_datasource");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("port");
		__rdesc.setTableName("p_datasource");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("server_id");
		__rdesc.setTableName("p_datasource");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_datasource");

		DataSource __dataSource = null;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			__dataSource = new DataSource();

			__dataSource.setMethod(__data_3.getString("method"));
			__dataSource.setDb_account(__data_3.getString("db_account"));
			__dataSource.setDb_password(__data_3.getString("db_password"));
			__dataSource.setIp(__data_3.getString("ip"));
			__dataSource.setPort(__data_3.getString("port"));

			__dataSource.setServer_id(__data_3.getString("server_id"));
		}

		return __dataSource;
	}

	private void getDbProcInParamByUser(String user, String password, String ip, String port, String server, String procName, Integer reportDsId) {
		Connection conn = null;
		Statement stm = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
			conn = DriverManager.getConnection(url, user, password);

			stm = conn.createStatement();
			ResultSet __set = stm.executeQuery("select t.* from all_arguments t where t.object_name='" + procName + "' and t.sequence>1 order by t.sequence");

			while (__set.next()) {
				ReportDsParam __rdp_3 = new ReportDsParam();

				__rdp_3.setDs_type("PROC");
				__rdp_3.setReport_ds_code(reportDsId);
				__rdp_3.setField_name(__set.getString("ARGUMENT_NAME"));
				__rdp_3.setField_value("1");

				__rdp_3.setField_type("string");
				__rdp_3.setLabel(__set.getString("ARGUMENT_NAME"));
				__rdp_3.setInput_type("input");
				__rdp_3.setWidget_ds_code(0);

				__rdp_3.setLogical("");
				__rdp_3.setRelation("");

				this.insertReportDsParam(__rdp_3);
			}
		} catch (Exception e) {
			_log.debug(e.getMessage());
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
