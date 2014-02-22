package net.newcapec.sca.reportds.das.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.report.das.impl.ReportDASImpl;
import net.newcapec.sca.reportds.ReportDs;
import net.newcapec.sca.reportds.das.ReportDsDAS;
import net.newcapec.sca.reportdsfield.ReportDsField;
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
import org.dom4j.Node;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

/**
 *
 * @author huangxin
 *
 */
public class ReportDsDASImpl implements ReportDsDAS {

	private static final Logger _log = Logger.getLogger(ReportDASImpl.class);

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

	private ReportDs findReportDsById(Integer reportDsId) {
		String __sql = "select a.*,b.name ds_name,upper(b.type) ds_type,'ds'||b.code||'_'||a.code rds_id from p_report_ds a,p_datasource b where a.ds_code=b.code and a.code=? order by a.code";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("report_code");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("memo");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_name");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_type");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("rds_id");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportDsId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		ReportDs __reportds = null;

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_report_ds")) {
			__reportds = new ReportDs();
			__reportds.setCode(__data_3.getInt("code"));
			__reportds.setReport_code(__data_3.getInt("report_code"));
			__reportds.setDs_code(__data_3.getInt("ds_code"));
			__reportds.setMemo(__data_3.getString("memo"));
			__reportds.setType(__data_3.getInt("type"));

			__reportds.setDs_name(__data_3.getString("ds_name"));
			__reportds.setDs_type(__data_3.getString("ds_type"));
			__reportds.setRds_id(__data_3.getString("rds_id"));
		}
		return __reportds;
	}

	public List<ReportDs> findReportDsList(Integer domainId, Integer orgId, Integer reportId) {
		String __sql = "select a.*,b.name ds_name,upper(b.type) ds_type,'ds'||b.code||'_'||a.code rds_id from p_report_ds a,p_datasource b where a.ds_code=b.code and report_code=? order by a.type,a.code";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("report_code");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("memo");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_name");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_type");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("rds_id");
		__rdesc.setTableName("p_report_ds");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<ReportDs> __reportds = new ArrayList<ReportDs>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_report_ds")) {
			ReportDs __reportds_4 = new ReportDs();
			__reportds_4.setCode(__data_3.getInt("code"));
			__reportds_4.setReport_code(__data_3.getInt("report_code"));
			__reportds_4.setDs_code(__data_3.getInt("ds_code"));
			__reportds_4.setMemo(__data_3.getString("memo"));
			__reportds_4.setType(__data_3.getInt("type"));

			__reportds_4.setDs_name(__data_3.getString("ds_name"));
			__reportds_4.setDs_type(__data_3.getString("ds_type"));
			__reportds_4.setRds_id(__data_3.getString("rds_id"));
			__reportds.add(__reportds_4);
		}
		return __reportds;
	}

	@SuppressWarnings("finally")
	public ReportDs insertReportDs(ReportDs reportDs) {
		String __sql = "insert into p_report_ds (code, report_code, ds_code, memo,type) values (?, ?, ?, ?, ?)";

		reportDs.setCode(this.sequenceService.getNextValue("getMaxReportDsID"));

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportDs.getCode());
		__cmd.setParameter(2, reportDs.getReport_code());
		__cmd.setParameter(3, reportDs.getDs_code());
		__cmd.setParameter(4, reportDs.getMemo());
		__cmd.setParameter(5, reportDs.getType());

		_log.debug(__sql);

		ReportDs __reportDs = null;
		try {
			__cmd.execute();
			__reportDs = this.findReportDsById(reportDs.getCode());
			this.findReportDsFieldList(reportDs.getCode(), reportDs.getDs_code());
		} catch (Exception $ex) {
			_log.error($ex);
		} finally {
			return __reportDs;
		}
	}

	private List<ReportDsField> findReportDsFieldList(Integer reportDsId, Integer dsId) {
		int __count = this.getReportDsFieldListRowCount(reportDsId);
		// 循环插入
		if (__count == 0) {
			this.insertReportDsField(reportDsId, dsId);
		}
		List<ReportDsField> __list = this.findReportDsFieldList2(reportDsId);
		return __list;
	}

	private Integer getReportDsFieldListRowCount(Integer reportDsId) {
		String __sql = "select count(1) count from p_report_ds_field a where a.report_ds_code=?";

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

	public Boolean insertReportDsField(Integer reportDsId, Integer dsId) {
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
		String __xml = this.getReportTemp(reportDsId);

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

	private List<ReportDsField> findReportDsFieldList2(Integer reportDsId) {
		String __sql = "select a.*,b.ds_code from p_report_ds_field a,p_report_ds b where a.report_ds_code=b.code and a.report_ds_code=? order by a.sequence";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("report_ds_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_name");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("field_type");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("title");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("sequence");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ishow");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportDsId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<ReportDsField> __reportds = new ArrayList<ReportDsField>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_report")) {
			ReportDsField __reportds_4 = new ReportDsField();
			__reportds_4.setReport_ds_code(__data_3.getInt("report_ds_code"));

			__reportds_4.setField_name(__data_3.getString("field_name"));
			__reportds_4.setField_type(__data_3.getString("field_type"));
			__reportds_4.setTitle(__data_3.getString("title"));
			__reportds_4.setSequence(__data_3.getInt("sequence"));
			__reportds_4.setIshow(__data_3.getInt("ishow") == 1);

			__reportds_4.setDs_code(__data_3.getInt("ds_code"));

			__reportds.add(__reportds_4);
		}
		return __reportds;

	}

	private String getReportTemp(int reportDsId) {
		String __sql = "select a.templet from p_report a,p_report_ds b where a.code=b.report_code and b.code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("templet");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.Bytes");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportDsId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_report");

		String xml = "";

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			try {
				xml = new String(__data_3.getBytes("templet"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return xml;
	}

	public Boolean updateReportDs(ReportDs reportDs) {
		String __sql = "update p_report_ds set memo = ? where code = ?";

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportDs.getMemo());
		__cmd.setParameter(2, reportDs.getCode());

		_log.debug(__sql);

		Boolean __result = true;

		try {
			__cmd.execute();

			String __xml = this.getReportTemplete(reportDs.getCode());

			Document doc = null;

			try {
				doc = DocumentHelper.parseText(__xml);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			}

			Node node2 = doc.getRootElement().selectSingleNode("//Data[ID='ds" + reportDs.getDs_code() + "_" + reportDs.getCode() + "']");

			Node e = node2.selectSingleNode("Memo");
			e.setText(reportDs.getMemo());

			this.updateReportTemplet(reportDs.getCode(), doc.asXML());

		} catch (Exception $ex) {
			_log.error($ex);
			__result = false;
		} finally {

		}

		return __result;
	}

	public Boolean delReportDsById(Integer dsId, Integer reportDsId, String xml) {
		if (dsId == 0)
			return true;
		String __sql = "delete p_report_ds where code =? and type=2";

		_log.info(__sql);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportDsId);

		Boolean __result = true;

		try {
			Document doc = null;

			try {
				doc = DocumentHelper.parseText(xml);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			}

			Node node2 = doc.getRootElement().selectSingleNode("//Data[ID='ds" + dsId + "_" + reportDsId + "']");

			node2.getParent().getParent().remove(node2.getParent());

			System.out.println(doc.asXML());

			this.updateReportTemplet(reportDsId, doc.asXML());

			__cmd.execute();

		} catch (Exception $ex) {
			_log.error($ex);
			__result = false;
		} finally {

		}

		return __result;
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

	private String getReportTemplete(Integer reportDsId) {
		String __sql = "select templet from p_report where code = (select t.report_code from p_report_ds t where t.code=?)";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("templet");
		__rdesc.setTableName("p_report");
		__rdesc.setColumnType("commonj.sdo.Bytes");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, reportDsId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		String __result = "";

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_report")) {
			try {
				__result = new String(__data_3.getBytes("templet"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return __result;
	}

}
