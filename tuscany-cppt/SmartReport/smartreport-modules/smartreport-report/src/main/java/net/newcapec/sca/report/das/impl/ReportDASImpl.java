package net.newcapec.sca.report.das.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.report.Report;
import net.newcapec.sca.report.das.ReportDAS;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

/**
 * 报表实现类
 *
 * @author huangxin
 *
 */
public class ReportDASImpl implements ReportDAS {

	private static final Logger _log = Logger.getLogger(ReportDASImpl.class);

	private TestDB testDB = new TestDB();

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

	public Report getReportById(Integer id) {
		String __sql = "select t.* from (select a.code,a.name,a.memo,a.templet,a.domain_code,a.unit_code,a.create_date,a.create_user_code,a.type,b.code as report_ds_code,b.ds_code,a.resource_code from p_report a,p_report_ds b where a.code=b.report_code and b.type=1) t where t.code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("memo");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("templet");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.Bytes");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("domain_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("unit_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("report_ds_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("resource_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_report");

		Report __report = null;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			__report = new Report();

			__report.setCode(__data_3.getInt("code"));
			__report.setName(__data_3.getString("name"));
			__report.setMemo(__data_3.getString("memo"));
			try {
				__report.setTemplet(new String(__data_3.getBytes("templet"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			__report.setDomain_code(__data_3.getInt("domain_code"));
			__report.setUnit_code(__data_3.getInt("unit_code"));
			__report.setCreate_date(__data_3.getString("create_date"));
			__report.setCreate_user_code(__data_3.getInt("create_user_code"));
			__report.setType(__data_3.getInt("type"));
			__report.setReport_ds_code(__data_3.getInt("report_ds_code"));
			__report.setDs_code(__data_3.getInt("ds_code"));

			__report.setResource_code(__data_3.getInt("resource_code"));

		}

		return __report;
	}

	public List<Report> findReportList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer begin, Integer limit) {
		String __sql = "select t.* from (select  rownum row_num, tt.* from (select a.code,a.name,a.memo,a.domain_code,a.unit_code,to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.create_user_code,c.name create_user_name,a.type,b.data_value type_name ,e.code ds_code,e.name ds_name,e.type ds_type,d.code report_ds_code,a.resource_code  from p_report a,z_code_dictionary_data b ,p_user c,p_report_ds d,p_datasource e where a.type=b.data_key and b.code_dictionary_name='REPORT_FUNTYPE' and a.create_user_code=c.account_id and a.code=d.report_code and d.type=1 and d.ds_code=e.code ";

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
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("memo");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("domain_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("unit_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_name");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type_name");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_name");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_type");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("report_ds_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("resource_code");
		__rdesc.setTableName("p_report");
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
		List<Report> __report = new ArrayList<Report>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_report")) {
			Report __report_4 = new Report();
			__report_4.setCode(__data_3.getInt("code"));
			__report_4.setName(__data_3.getString("name"));
			__report_4.setMemo(__data_3.getString("memo"));

			__report_4.setDomain_code(__data_3.getInt("domain_code"));
			__report_4.setUnit_code(__data_3.getInt("unit_code"));
			__report_4.setCreate_date(__data_3.getString("create_date"));
			__report_4.setCreate_user_code(__data_3.getInt("create_user_code"));

			__report_4.setCreate_user_name(__data_3.getString("create_user_name"));
			__report_4.setType(__data_3.getInt("type"));
			__report_4.setType_name(__data_3.getString("type_name"));

			__report_4.setDs_code(__data_3.getInt("ds_code"));
			__report_4.setDs_name(__data_3.getString("ds_name"));
			__report_4.setDs_type(__data_3.getString("ds_type").toUpperCase());
			__report_4.setReport_ds_code(__data_3.getInt("report_ds_code"));
			__report_4.setResource_code(__data_3.getInt("resource_code"));
			__report.add(__report_4);
		}
		return __report;
	}

	public Boolean insertReport(Report report) {
		String __sql = "insert into p_report (code, name, memo, templet, domain_code, unit_code, create_date, create_user_code, type) values (?,?, ?, ?, ?, ?, to_date(?,'yyyy-mm-dd hh24:mi:ss'), ?, ?)";

		report.setCode(this.sequenceService.getNextValue("getMaxReportID"));
		report.setCreate_date(_sdf.format(new Date()));

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, report.getCode());
		__cmd.setParameter(2, report.getName());
		__cmd.setParameter(3, report.getMemo());
		try {
			__cmd.setParameter(4, report.getTemplet().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		__cmd.setParameter(5, report.getDomain_code());
		__cmd.setParameter(6, report.getUnit_code());
		__cmd.setParameter(7, report.getCreate_date());
		__cmd.setParameter(8, report.getCreate_user_code());
		__cmd.setParameter(9, report.getType());

		_log.debug(__sql);

		Boolean __result = true;

		try {
			__cmd.execute();
			this.insertReportDs(report.getCode(), report.getDs_code(), report.getTemplet());
		} catch (Exception $ex) {
			System.out.println($ex);
			_log.error($ex);
			__result = false;
		} finally {

		}

		return __result;
	}

	/**
	 * 添加主数据源
	 *
	 * @param reportId
	 * @param dsId
	 * @return
	 */
	private Boolean insertReportDs(int reportId, int dsId, String xml) {
		String __sql = "insert into p_report_ds (code, report_code, ds_code, memo,type) values (?, ?, ?, ?, ?)";

		DAS das = this.getDAS();
		int __reportDsId = this.sequenceService.getNextValue("getMaxReportDsID");

		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, __reportDsId);
		__cmd.setParameter(2, reportId);
		__cmd.setParameter(3, dsId);
		__cmd.setParameter(4, "");
		__cmd.setParameter(5, 1);

		_log.debug(__sql);

		Boolean __result = true;

		try {
			__cmd.execute();
			this.insertReportDsField(__reportDsId, dsId, xml);
		} catch (Exception $ex) {
			_log.error($ex);
			__result = false;
		} finally {

		}

		return __result;
	}

	private Boolean insertReportDsField(Integer reportDsId, Integer dsId, String xml) {
		String __sql = "select a.name field_name,a.alias title,a.type field_type from p_fieldprep a where ds_code =?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_name");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("title");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_type");
		__rdesc.setTableName("p_fieldprep");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, dsId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		// List<ReportDsField> __reportds = new ArrayList<ReportDsField>();

		/**
		 * 获取报表模板的xml
		 */
		String __xml = xml.replaceAll("hr000hr", "ds" + dsId + "_" + reportDsId).replaceAll("dr000dr", "ds" + dsId + "_" + reportDsId);

		Document doc = null;

		try {
			doc = DocumentHelper.parseText(__xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Element ele = doc.getRootElement().element("DataSources");
		Element datasource = ele.addElement("DataSource");
		datasource.addAttribute("type", "4");

		Element data = datasource.addElement("Data");

		Element id = data.addElement("ID");
		id.setText("ds" + dsId + "_" + reportDsId);

		Element version = data.addElement("Version");
		version.setText("2");

		Element type = data.addElement("Type");
		type.setText("4");

		Element typeMeaning = data.addElement("TypeMeaning");
		typeMeaning.setText("JSON");

		Element memo = data.addElement("Memo");
		memo.setText("ds" + dsId + "_" + reportDsId);

		Element source = data.addElement("Source");
		source.setText("");

		Element xML_RecordAble_Nodes = data.addElement("XML_RecordAble_Nodes");
		Element node = xML_RecordAble_Nodes.addElement("Node");
		Element node_name = node.addElement("name");
		node_name.setText("items");

		Element columns = data.addElement("Columns");

		String __sql2 = "insert into p_report_ds_field (report_ds_code,field_name,title,field_type,sequence,ishow) values (?,?,?,?,1,1)";

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_fieldprep")) {

			Command __cmd2 = das.createCommand(__sql2);
			__cmd2.setParameter(1, reportDsId);
			__cmd2.setParameter(2, __data_3.getString("field_name"));
			__cmd2.setParameter(3, __data_3.getString("title"));
			__cmd2.setParameter(4, __data_3.getString("field_type"));

			_log.debug(__sql2);

			/* xml列元素 */
			Element column = columns.addElement("Column");
			Element name = column.addElement("name");
			name.setText("items\\" + __data_3.getString("field_name"));

			Element text = column.addElement("text");
			text.setText(__data_3.getString("title"));

			Element __type = column.addElement("type");
			__type.setText("string");

			Element visible = column.addElement("visible");
			visible.setText("true");

			Element sequence = column.addElement("sequence");
			sequence.setText("1");

			try {
				__cmd2.execute();
			} catch (Exception $ex) {
				System.out.println($ex);
				_log.error($ex);
			} finally {

			}

		}

		__xml = doc.asXML();

		System.out.println("--");
		System.out.println(__xml);
		System.out.println("--");

		this.updateReportTemplet(reportDsId, __xml);

		return true;

	}

	private Boolean updateReportTemplet(int reportDsId, String templet) {
		String __sql = "update p_report set templet = ? where code = (select a.code from p_report a,p_report_ds b where a.code=b.report_code and b.code=?)";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		try {
			__cmd.setParameter(1, templet.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		__cmd.setParameter(2, reportDsId);

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

	public Boolean updateReport(Report report) {
		String __sql = "update p_report set name = ?, memo = ?, type = ? where code = ?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, report.getName());
		__cmd.setParameter(2, report.getMemo());
		__cmd.setParameter(3, report.getType());
		__cmd.setParameter(4, report.getCode());

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

	public Boolean updateReportTemplet(Report report) {
		String __sql = "update p_report set templet = ? where code = ?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		try {
			__cmd.setParameter(1, report.getTemplet().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		__cmd.setParameter(2, report.getCode());

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

	public Boolean delReportById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean delReportByIds(Integer[] ids) {
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_report where code in (" + __appendSQL + ")";

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

	public Integer getReportListRowCount(List<FilterParam> filter) {
		String __sql = "select count(1) count from p_report a,z_code_dictionary_data b ,p_user c,p_report_ds d,p_datasource e where a.type=b.data_key and b.code_dictionary_name='REPORT_FUNTYPE' and a.create_user_code=c.account_id and a.code=d.report_code and d.type=1 and d.ds_code=e.code ";

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
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
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

	public JSONObject getReportData(Integer reportId) {
		String __sql = "select sql,ip,port,server_id,db_account,db_password,method,ds_type,code,report_code,ds_code,memo,type from v_report_sql  where report_code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("sql");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ip");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("port");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("server_id");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("db_account");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("db_password");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("method");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_type");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("report_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("memo");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportId);

		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		JSONObject __json = new JSONObject();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_report")) {
			Report __report_4 = new Report();
			__report_4.setCode(__data_3.getInt("code"));

			List<JSONObject> __jsons_4 = null;

			if ("proc".equals(__data_3.getString("ds_type").toLowerCase())) {
				__jsons_4 = testDB.callProc(__data_3.getString("db_account"), __data_3.getString("db_password"), __data_3.getString("ip"), __data_3.getString("port"), __data_3.getString("server_id"), __data_3.getString("sql"));
				if (__jsons_4 != null) {
					try {
						__json.put("ds" + __data_3.getString("ds_code") + "_" + __data_3.getString("code"), __jsons_4);
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
			} else {
				__jsons_4 = testDB.testSql(__data_3.getString("db_account"), __data_3.getString("db_password"), __data_3.getString("ip"), __data_3.getString("port"), __data_3.getString("server_id"), __data_3.getString("sql"));
				if (__jsons_4 != null) {
					try {
						__json.put("ds" + __data_3.getString("ds_code") + "_" + __data_3.getString("code"), __jsons_4);
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
			}
		}
		return __json;
	}

	public JSONObject getReportData2(Integer reportId, FilterParam[] params) {
		String __sql = "select sql,ip,port,server_id,db_account,db_password,method,ds_type,code,report_code,ds_code,memo,type from v_report_sql2  where report_code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("sql");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ip");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("port");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("server_id");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("db_account");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("db_password");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("method");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_type");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("report_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("memo");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportId);

		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		JSONObject __json = new JSONObject();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_report")) {
			Report __report_4 = new Report();
			__report_4.setCode(__data_3.getInt("code"));

			List<JSONObject> __jsons_4 = null;

			String __sql_4 = __data_3.getString("sql");

			// System.out.println(__sql_4);

			for (FilterParam fp_4 : params) {
				__sql_4 = __sql_4.replaceAll("@" + fp_4.getField().trim() + "@", fp_4.getValue().trim());
			}

			// System.out.println(__sql_4);

			if ("proc".equals(__data_3.getString("ds_type").toLowerCase())) {
				__jsons_4 = testDB.callProc(__data_3.getString("db_account"), __data_3.getString("db_password"), __data_3.getString("ip"), __data_3.getString("port"), __data_3.getString("server_id"), __sql_4);
				if (__jsons_4 != null) {
					try {
						__json.put("ds" + __data_3.getString("ds_code") + "_" + __data_3.getString("code"), __jsons_4);
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
			} else {
				__jsons_4 = testDB.testSql(__data_3.getString("db_account"), __data_3.getString("db_password"), __data_3.getString("ip"), __data_3.getString("port"), __data_3.getString("server_id"), __sql_4);
				if (__jsons_4 != null) {
					try {
						__json.put("ds" + __data_3.getString("ds_code") + "_" + __data_3.getString("code"), __jsons_4);
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
			}
		}
		return __json;
	}

}
