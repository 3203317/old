package net.newcapec.sca.report.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.componentdataset.ComponentDataset;
import net.newcapec.sca.componentdataset.service.ComponentDatasetService;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.gridprint.GridPrint;
import net.newcapec.sca.gridprint.service.GridPrintService;
import net.newcapec.sca.gridview.GridView;
import net.newcapec.sca.gridview.service.GridViewService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.report.PrintSetInfo;
import net.newcapec.sca.report.Report;
import net.newcapec.sca.report.ReportGridConditionDataCollection;
import net.newcapec.sca.report.das.ReportDAS;
import net.newcapec.sca.report.service.ReportService;
import net.newcapec.sca.reportdsfield.ReportDsField;
import net.newcapec.sca.reportdsfield.service.ReportDsFieldService;
import net.newcapec.sca.reportdsparam.ReportDsParam;
import net.newcapec.sca.reportdsparam.service.ReportDsParamService;
import net.newcapec.sca.resource.bean.MenuItem;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.json.JSONObject;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 * @author huangxin
 *
 */
public class ReportServiceImpl implements ReportService {

	private static final Logger _log = Logger.getLogger(ReportServiceImpl.class);

	private SessionService sessionService;
	private GridPrintService gridPrintService;
	private ComponentDatasetService componentDatasetService;
	@Reference
	public void setComponentDatasetService(
			ComponentDatasetService componentDatasetService) {
		this.componentDatasetService = componentDatasetService;
	}

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private ReportDAS reportDAS;

	@Reference(name = "reportDAS", required = true)
	public void setReportDAS(ReportDAS reportDAS) {
		this.reportDAS = reportDAS;
	}

	@Reference
	public void setGridPrintService(GridPrintService gridPrintService) {
		this.gridPrintService = gridPrintService;
	}

	public Report getReportById(String sessionId, Integer id) {
		Report __report = this.reportDAS.getReportById(id);
		return __report;
	}

	public DojoListData findReportList(DojoListParam param) {
		List<Report> __list = this.reportDAS.findReportList(1, 1, param.getFilter(), param.getBegin(), param.getLimit());

		int __count = this.reportDAS.getReportListRowCount(param.getFilter());

		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		__dld.setNumRows(__count);

		return __dld;
	}

	public Report insertReport(String sessionId, Report report) {
//		String __templete = "<?xml version='1.0' encoding='UTF-8' ?><Report><WorkSheet name='演示表' isDefaultPrint='true'><Properties><BackGround bgColor='#F3F3F3' arrange='tile' alpha='255'/><DefaultTD><TD fontIndex='0' textColor='#000000' transparent='true' leftBorder='1' topBorder='0' leftBorderColor='#C0C0C0' leftBorderStyle='solid' topBorderColor='#C0C0C0' topBorderStyle='solid' decimal='2' align='left' vAlign='middle' isProtected='false' isThousandSeparat='true' isRound='true' isPrint='true'/></DefaultTD><Other isShowZero='true' isRefOriPrecision='true' LineDistance='0' isRowHeightAutoExtendAble='true'/></Properties><Fonts><Font faceName='Verdana' height='-13' weight='400'/><Font faceName='Verdana' height='-13' weight='700'/></Fonts><Table><Col width='102'/><Col width='13'/><TR height='23' sequence='0'><TD fontIndex='1' bgColor='#E0E0E0' topBorder='1' align='center' datatype='1' formula='=headrow(&apos;ds201\\items&apos;)'>用户名</TD><TD/></TR><TR height='23' sequence='1'><TD topBorder='1' datatype='1' formula='=datarow(&apos;ds201\\items&apos;)'/><TD/></TR><TR height='23' sequence='2'><TD leftBorder='0' topBorder='1'/><TD leftBorder='0'/></TR></Table><PrintPage><Paper><Margin left='19' top='25' right='19' bottom='25'/></Paper><Page align='center'><PageCode><Font faceName='宋体' charSet='134' height='-14' weight='400'/></PageCode></Page></PrintPage></WorkSheet><DataSources Version='255' isAutoCalculateWhenOpen='false' isSaveCalculateResult='false'><DataSource type='4'><Data><ID>ds201</ID><Version>2</Version><Type>4</Type><TypeMeaning>JSON</TypeMeaning><Memo>性别-男</Memo><XML_RecordAble_Nodes><Node><name alias='项'>items</name></Node></XML_RecordAble_Nodes><Columns><Column><name>items\\f_name</name><text>用户1名</text><type>string</type><visible>true</visible><sequence>1</sequence></Column></Columns></Data></DataSource></DataSources></Report>";
		String __templete = "<?xml version='1.0' encoding='UTF-8' ?><Report><WorkSheet name='演示表' isDefaultPrint='true'><Properties><BackGround bgColor='#F3F3F3' arrange='tile' alpha='255'/><DefaultTD><TD fontIndex='0' textColor='#000000' transparent='true' leftBorder='1' topBorder='0' leftBorderColor='#C0C0C0' leftBorderStyle='solid' topBorderColor='#C0C0C0' topBorderStyle='solid' decimal='2' align='left' vAlign='middle' isProtected='false' isThousandSeparat='true' isRound='true' isPrint='true'/></DefaultTD><Other isShowZero='true' isRefOriPrecision='true' LineDistance='0' isRowHeightAutoExtendAble='true'/></Properties><Fonts><Font faceName='Verdana' height='-13' weight='400'/><Font faceName='Verdana' height='-13' weight='700'/></Fonts><Table><Col width='102'/><Col width='13'/><TR height='23' sequence='0'><TD fontIndex='1' bgColor='#E0E0E0' topBorder='1' align='center' datatype='1' formula='=headrow(&apos;hr000hr&apos;)'></TD><TD/></TR><TR height='23' sequence='1'><TD topBorder='1' datatype='1' formula='=datarow(&apos;dr000dr&apos;)'/><TD/></TR><TR height='23' sequence='2'><TD leftBorder='0' topBorder='1'/><TD leftBorder='0'/></TR></Table><PrintPage><Paper><Margin left='19' top='25' right='19' bottom='25'/></Paper><Page align='center'><PageCode><Font faceName='宋体' charSet='134' height='-14' weight='400'/></PageCode></Page></PrintPage></WorkSheet><DataSources Version='255' isAutoCalculateWhenOpen='false' isSaveCalculateResult='false'><DataSource type='4'><Data><ID>ReportDic</ID><Version>2</Version><Type>4</Type><TypeMeaning>JSON</TypeMeaning><Memo>报表字典</Memo><XML_RecordAble_Nodes><Node><name>items</name></Node></XML_RecordAble_Nodes><Columns><Column><name>items\\corp</name><text>郑州新开普</text><type>string</type><visible>true</visible><sequence>1</sequence></Column><Column><name>items\\optuser</name><text>操作员</text><type>string</type><visible>true</visible><sequence>2</sequence></Column><Column><name>items\\printime</name><text>打印时间</text><type>string</type><visible>true</visible><sequence>3</sequence></Column></Columns></Data></DataSource></DataSources></Report>";
		report.setTemplet(__templete);
		report.setDomain_code(1);
		report.setUnit_code(1);
		report.setCreate_user_code(1);
		Boolean __b = this.reportDAS.insertReport(report);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("添加新报表失败");
			report.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return report;
	}

	public Report updateReport(String sessionId, Report report) {
		Boolean __b = this.reportDAS.updateReport(report);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("更新报表信息失败");
			report.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return report;
	}

	public Report delReportById(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Report delReportByIds(String sessionId, String ids) {
		// 验证 sessionId 是否合法

		_log.info("参数：" + ids);

		Report __report = new Report();
		ResultMsg __rm = new ResultMsg();

		if ("".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setErrorMsg("参数 ids 不能为空");
			__report.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
			return __report;
		}

		String[] __idsArr = ids.split(",");

		Integer[] __ids = new Integer[__idsArr.length];

		for (int __i_3 = 0, __j_3 = __idsArr.length; __i_3 < __j_3; __i_3++) {
			__ids[__i_3] = Integer.parseInt(__idsArr[__i_3]);
		}

		boolean __delResult = this.reportDAS.delReportByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除报表失败");
			__report.setResultMsg(__rm);
		}

		return __report;
	}

	public ReportGridConditionDataCollection getReportGridConditonDataCollection(String sessionId, Integer ds_code, Integer Form_code, Integer report_ds_code) {
		ReportGridConditionDataCollection collection = new ReportGridConditionDataCollection();
		GridView gridView = gridViewService.getGridViewById(sessionId, Form_code);
		List<ReportDsField> reportDsField = reportDsFieldService.findReportDsFieldList(sessionId, report_ds_code, ds_code);
		List<ReportDsParam> reportDsParam = reportDsParamService.findReportDsParamByReportDsId(sessionId, report_ds_code);
		GridPrint gridPrint = gridPrintService.getGridPrintById(sessionId, Form_code);
		ComponentDataset componentDataset = null;
		if(gridView.getCondition_trees() != "0"){
			componentDataset = componentDatasetService.getComponentDatasetById(sessionId, Integer.parseInt(gridView.getCondition_trees()));
		}

		collection.setGridView(gridView);
		collection.setGridPrint(gridPrint);
		collection.setReportDsField(reportDsField);
		collection.setReportDsParam(reportDsParam);
		collection.setComponentDataset(componentDataset);

		return collection;
	}

	private ReportDsFieldService reportDsFieldService;
	private ReportDsParamService reportDsParamService;
	private GridViewService gridViewService;

	@Reference
	public void setReportDsFieldService(ReportDsFieldService reportDsFieldService) {
		this.reportDsFieldService = reportDsFieldService;
	}

	@Reference
	public void setReportDsParamService(ReportDsParamService reportDsParamService) {
		this.reportDsParamService = reportDsParamService;
	}

	@Reference
	public void setGridViewService(GridViewService gridViewService) {
		this.gridViewService = gridViewService;
	}

	public String createReportXml(String sessionId, PrintSetInfo printSetInfo, List<ReportDsField> fieldList, List<JSONObject> griddata) {
		// List<ReportDsField> fieldList =
		// reportDsFieldService.findReportDsFieldList(sessionId, form_code,
		// ds_code);
		try {
			XMLWriter writer = null;// 声明写XML的对象
			Document _document = DocumentHelper.createDocument();
			Element _report = _document.addElement("Report");// 创建报表的根节点
			Element _workSheet = _report.addElement("WorkSheet");// 创建报表的工作区，workSheet
			_workSheet.addAttribute("name", "Sheet");
			_workSheet.addAttribute("isDefault", "true");
			_workSheet.addAttribute("isDefaultPrint", "true");
			Element properties = _workSheet.addElement("Properties");// 为workSheet创建节点Properties
			Element backGround = properties.addElement("BackGround");// 为properties创建默认背景
			backGround.addAttribute("bgColor", "#FFFFFF");
			backGround.addAttribute("arrange", "tile");
			backGround.addAttribute("alpha", "255");
			Element defaultTD = properties.addElement("DefaultTD");// 为properties创建DefaultTd节点
			Element _td = defaultTD.addElement("TD");// 为defaultTd创建td节点并且添加样式
			_td.addAttribute("fontIndex", "0");
			_td.addAttribute("textColor", "#333399");
			_td.addAttribute("transparent", "true");
			_td.addAttribute("leftBorder", "0");
			_td.addAttribute("topBorder", "0");
			_td.addAttribute("leftBorderColor", "#333399");
			_td.addAttribute("leftBorderStyle", "solid");
			_td.addAttribute("topBorderColor", "#333399");
			_td.addAttribute("topBorderStyle", "solid");
			_td.addAttribute("decimal", "2");
			_td.addAttribute("align", "left");
			_td.addAttribute("vAlign", "middle");
			_td.addAttribute("isProtected", "false");
			_td.addAttribute("isThousandSeparat", "true");
			_td.addAttribute("isRound", "true");
			_td.addAttribute("isPrint", "true");
			Element other = properties.addElement("Other");// 在properties节点常见other节点，并且添加属性
			other.addAttribute("isShowZero", "true");
			other.addAttribute("isRefOriPrecision", "true");
			other.addAttribute("LineDistance", "0");
			other.addAttribute("isRowHeightAutoExtendAble", "true");
			Element fonts = _workSheet.addElement("Fonts");// 在workSheet节点下创建fonts节点
			Element font = fonts.addElement("Font");
			font.addAttribute("faceName", "宋体");
			font.addAttribute("charSet", "134");
			font.addAttribute("height", "-14");
			font.addAttribute("weight", "400");
			Element font1 = fonts.addElement("Font");
			font1.addAttribute("faceName", "黑体");
			font1.addAttribute("charSet", "134");
			font1.addAttribute("height", "-21");
			font1.addAttribute("weight", "400");
			Element font2 = fonts.addElement("Font");
			font2.addAttribute("faceName", "宋体");
			font2.addAttribute("charSet", "134");
			font2.addAttribute("height", "-13");
			font2.addAttribute("weight", "400");
			Element font4 = fonts.addElement("Font");
			font4.addAttribute("faceName", "隶书");
			font4.addAttribute("charSet", "134");
			font4.addAttribute("height", "-21");
			font4.addAttribute("weight", "400");
			font4.addAttribute("underline", "1");
			boolean table = ReportServiceImpl.createReportTableByData(_workSheet, printSetInfo, fieldList, griddata);
			if (table == false) {
				return null;
			}
			Element merges = _workSheet.addElement("Merges");// 在workSheet下创建打印设置的相关节点
			for (int t = 0; t < 3; t++) {
				Element range = merges.addElement("Range");
				range.addAttribute("row1", t + "");
				range.addAttribute("col1", "1");
				range.addAttribute("row2", t + "");
				range.addAttribute("col2", fieldList.size() + "");
			}
			if (printSetInfo.isYema()) {
				Element graphicObjects = _workSheet.addElement("GraphicObjects");// 在workSheet下创建打印设置的相关节点
				Element textBox = graphicObjects.addElement("TextBox");
				textBox.addAttribute("fontIndex", "254");
				textBox.addAttribute("textColor", "10040115");
				textBox.addAttribute("LineDistance", "0");
				textBox.addAttribute("align", "right");
				textBox.addAttribute("vAlign", "bottom");
				textBox.addAttribute("formula", "=&quot;第&quot;+page()+&quot;页/共&quot;+pages()+&quot;页&quot;");
				textBox.addAttribute("text", "第1页/共1页");
				Element Rect = graphicObjects.addElement("Rect");
				String x2 = fieldList.size() * 140 + 4 + "";
				String x1 = (fieldList.size() - 1) * 140 + 4 + "";
				Rect.addAttribute("x1", x1);
				Rect.addAttribute("y1", "75");
				Rect.addAttribute("x2", x2);
				Rect.addAttribute("y2", "90");
			}
			Element printPage = _workSheet.addElement("PrintPage");// 在workSheet下创建打印设置的相关节点
			Element paper = printPage.addElement("Paper");
			paper.addAttribute("oriantation", "landscape");
			Element margin = paper.addElement("Margin");
			margin.addAttribute("left", "19");
			margin.addAttribute("top", "15");
			margin.addAttribute("right", "19");
			margin.addAttribute("bottom", "15");
			Element page = printPage.addElement("Page");
			page.addAttribute("align", "center");
			Element gridLine = page.addElement("GridLine");
			gridLine.addAttribute("isUseOriginalColor", "true");
			Element pageCode = page.addElement("PageCode");
			Element pageCodeFont = pageCode.addElement("Font");
			pageCodeFont.addAttribute("faceName", "宋体");
			pageCodeFont.addAttribute("charSet", "134");
			pageCodeFont.addAttribute("height", "-14");
			pageCodeFont.addAttribute("weight", "400");
			// writer = new XMLWriter(new FileWriter(file), format);
			return _document.asXML();
			// writer.write(_document);
			// writer.close();
			// }

			// b = true;
		} catch (Exception e) {
			// b = false;
			e.printStackTrace();
		}
		return null;
	}

	private static boolean createReportTableByData(Element _workSheet, PrintSetInfo printSetInfo, List<ReportDsField> fieldList, List<JSONObject> griddata) {
		boolean b = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Element table = _workSheet.addElement("Table");// 在workSheet节点下创建table节点，该table默认行25，列10
			Element col = table.addElement("Col");
			col.addAttribute("width", "4");
			for (int i = 0; i < fieldList.size(); i++) {
				Element col1 = table.addElement("Col");
				col1.addAttribute("width", "140");
			}
			Element col2 = table.addElement("Col");
			col2.addAttribute("width", "14");
			Element trBt0 = table.addElement("TR");
			trBt0.addAttribute("height", "34");
			trBt0.addAttribute("sequence", "0");

			Element trBt1 = table.addElement("TR");// 创建表头
			trBt1.addAttribute("height", "34");
			trBt1.addAttribute("sequence", "1");
			Element tdBt1 = trBt1.addElement("TD");
			tdBt1.addAttribute("col", "1");
			tdBt1.addAttribute("fontIndex", "3");
			tdBt1.addAttribute("align", "center");
			tdBt1.addText(printSetInfo.getTitle());

			Element trBt2 = table.addElement("TR");
			trBt2.addAttribute("height", "24");
			trBt2.addAttribute("sequence", "2");
			Element tdBt2 = trBt2.addElement("TD");
			tdBt2.addAttribute("col", "1");
			if (printSetInfo.isPrintdate()) {
				tdBt2.addText("打印时间:" + sdf.format(new Date()));
			}

			Element trBt = table.addElement("TR");
			trBt.addAttribute("height", "24");
			trBt.addAttribute("sequence", "3");
			for (int j = 1; j <= fieldList.size(); j++) {
				ReportDsField field = fieldList.get(j - 1);
				String field_label = field.getTitle();
				if (j == 1) {
					Element tdBt = trBt.addElement("TD");
					tdBt.addAttribute("col", "" + j + "");
					tdBt.addAttribute("fontIndex", "2");
					tdBt.addAttribute("leftBorder", "2");
					tdBt.addAttribute("topBorder", "2");
					tdBt.addAttribute("align", "left");
					tdBt.setText(field_label);
				} else if (j == fieldList.size()) {
					Element tdBt = trBt.addElement("TD");
					tdBt.addAttribute("col", "" + j + "");
					tdBt.addAttribute("fontIndex", "2");
					tdBt.addAttribute("leftBorder", "1");
					tdBt.addAttribute("topBorder", "2");
					tdBt.addAttribute("align", "left");
					tdBt.setText(field_label);
					Element td = trBt.addElement("TD");
					td.addAttribute("col", "" + (j + 1) + "");
					td.addAttribute("leftBorder", "2");
				} else {
					Element tdBt = trBt.addElement("TD");
					tdBt.addAttribute("col", "" + j + "");
					tdBt.addAttribute("fontIndex", "2");
					tdBt.addAttribute("leftBorder", "1");
					tdBt.addAttribute("topBorder", "2");
					tdBt.addAttribute("align", "left");
					tdBt.setText(field_label);
				}
			}
			int num = 4;
			for (JSONObject object : griddata) {
				Element trtd = table.addElement("TR");
				trtd.addAttribute("height", "24");
				trtd.addAttribute("sequence", "" + num + "");
				for (int m = 1; m <= fieldList.size(); m++) {
					ReportDsField field = fieldList.get(m - 1);
					String value = object.getString(field.getField_name());
					if (m == 1) {
						Element td = trtd.addElement("TD");
						td.addAttribute("col", "" + m + "");
						td.addAttribute("leftBorder", "2");
						td.addAttribute("topBorder", "1");
						td.addAttribute("fontIndex", "2");
						td.setText(value);
					} else if (m == fieldList.size()) {
						Element td = trtd.addElement("TD");
						td.addAttribute("col", "" + m + "");
						td.addAttribute("leftBorder", "1");
						td.addAttribute("topBorder", "1");
						td.addAttribute("fontIndex", "2");
						td.setText(value);
						Element td1 = trtd.addElement("TD");
						td1.addAttribute("col", "" + (m + 1) + "");
						td1.addAttribute("leftBorder", "2");
					} else {
						Element td = trtd.addElement("TD");
						td.addAttribute("col", "" + m + "");
						td.addAttribute("leftBorder", "1");
						td.addAttribute("topBorder", "1");
						td.addAttribute("fontIndex", "2");
						td.setText(value);
					}
				}
				num++;
			}
			Element tr = table.addElement("TR");
			tr.addAttribute("height", "30");
			tr.addAttribute("sequence", "" + num + "");
			for (int k = 1; k <= fieldList.size(); k++) {
				if (k == fieldList.size()) {
					Element td = tr.addElement("TD");
					td.addAttribute("col", k + "");
					td.addAttribute("topBorder", "2");
					td.addAttribute("align", "right");
					if (printSetInfo.isPrintperson()) {
						td.addText("打印人:");
					}
				} else {
					Element td = tr.addElement("TD");
					td.addAttribute("col", k + "");
					td.addAttribute("topBorder", "2");
				}
			}
			Element trPrintTime = table.addElement("TR");
			trPrintTime.addAttribute("height", "15");
			trPrintTime.addAttribute("sequence", (num + 1) + "");

			for (int n = 1; n <= fieldList.size(); n++) {
				Element td = trPrintTime.addElement("TD");
				td.addAttribute("col", n + "");
				// td.addAttribute("topBorder", "2");
			}
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public Report updateReportTemplet(String sessionId, Report report) {
		Boolean __b = this.reportDAS.updateReportTemplet(report);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("更新报表模板信息失败");
			report.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return report;
	}

	public MenuItem[] getFilteringSelectList(String sessionId) {
		// 根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			_log.error("session不可用");
			return null;
		}

		List<Report> list = this.reportDAS.findReportList(1, 1, null, 0, 30);
		List<MenuItem> rtnList = new ArrayList<MenuItem>();
		for (Report report : list) {
			MenuItem menuItem = new MenuItem();
			menuItem.setId(report.getCode().toString());
			menuItem.setName(report.getName());
			rtnList.add(menuItem);
		}
		MenuItem[] rtnArray = new MenuItem[rtnList.size()];
		rtnList.toArray(rtnArray);
		return rtnArray;
	}

	protected boolean isSessionVaild(Session session) {
		boolean sessionVaild = true;

		try {
			if (null == session.getId() && session.getState() == 0) {
				sessionVaild = false;
			}
		} catch (Exception e) {
			_log.error("CustomForm:session验证失败");
			e.printStackTrace();
			sessionVaild = false;
		}
		return sessionVaild;
	}

	public JSONObject getReportData(String sessionId, Integer id, FilterParam[] params) {
		// String __result =
		// "{'items':[{'e_no':'1','e_name':'A部门',e_deptno:'001',f_age:'2012-12-25','f_idcard':'410101010101010101'},{'e_no':'2','e_name':'B部门'}]}";

		JSONObject __jObj = null;
		if (params.length == 0) {
			__jObj = this.reportDAS.getReportData(id);
		} else {
			__jObj = this.reportDAS.getReportData2(id, params);
		}
		return __jObj;
	}
}
