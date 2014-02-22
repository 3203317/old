package net.newcapec.sca.datasource.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.newcapec.sca.datasource.DataSource;
import net.newcapec.sca.datasource.ProtoDBField;
import net.newcapec.sca.datasource.das.DataSourceDAS;
import net.newcapec.sca.datasource.service.DataSourceService;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.gridfield.GridField;
import net.newcapec.sca.gridfield.service.GridFieldService;
import net.newcapec.sca.param.FilterParam;
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
public class DataSourceServiceImpl implements DataSourceService {

	private static final Logger _log = Logger.getLogger(DataSourceServiceImpl.class);

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private ConnDB _db = new ConnDB();

	private DataSourceDAS dataSourceDAS;

	private GridFieldService gridFieldService;

	@Reference(name = "dataSourceDAS", required = true)
	public void setDataSourceDAS(DataSourceDAS dataSourceDAS) {
		this.dataSourceDAS = dataSourceDAS;
	}

	@Reference
	public void setGridFieldService(GridFieldService gridFieldService) {
		this.gridFieldService = gridFieldService;
	}

	public DataSource getDataSourceById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		DataSource __dataSource = this.dataSourceDAS.getDataSourceById(id);
		return __dataSource;
	}

	public List<DataSource> findDataSourceList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<DataSource> __list = this.dataSourceDAS.findDataSourceList(1, 1, filter, begin, limit);
		return __list;
	}

	public DataSource insertDataSource(String sessionId, DataSource datasource) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		String __method = datasource.getMethod().trim();
		String __opt = __method.substring(0, 6).toLowerCase();

		if ("sql".equals(datasource.getType())) {
			if (!"select".equals(__opt)) {
				ResultMsg __rm = new ResultMsg();
				__rm.setErrorId(205);
				__rm.setErrorMsg("添加新数据源失败");
				datasource.setResultMsg(__rm);
				_log.info(__rm.getErrorMsg());
				return datasource;
			}
		}

		Boolean __b = this.dataSourceDAS.insertDataSource(datasource);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("添加新数据源失败");
			datasource.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return datasource;
	}

	public DataSource updateDataSource(String sessionId, DataSource datasource) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		Boolean __b = this.dataSourceDAS.updateDataSource(datasource);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("更新新数据源失败");
			datasource.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return datasource;
	}

	public DataSource delDataSourceById(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataSource delDataSourceByIds(String sessionId, String ids) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		// 验证 sessionId 是否合法

		_log.info("参数：" + ids);

		DataSource __dataSource = new DataSource();
		ResultMsg __rm = new ResultMsg();

		if ("".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setErrorMsg("参数 ids 不能为空");
			__dataSource.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
			return __dataSource;
		}

		String[] __idsArr = ids.split(",");

		Integer[] __ids = new Integer[__idsArr.length];

		for (int __i_3 = 0, __j_3 = __idsArr.length; __i_3 < __j_3; __i_3++) {
			__ids[__i_3] = Integer.parseInt(__idsArr[__i_3]);
		}

		boolean __delResult = this.dataSourceDAS.delDataSourceByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除 dataSource 失败");
			__dataSource.setResultMsg(__rm);
		}

		return __dataSource;
	}

	public DojoListData findDataSourceDojoList(DojoListParam param) {
		Session session = sessionService.getSession(param.getSessionId());
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<DataSource> __list = this.dataSourceDAS.findDataSourceList(1, 1, param.getFilter(), param.getBegin(), param.getLimit());

		int __count = this.dataSourceDAS.getDataSourceListRowCount(param.getSessionId(), param.getResourceId(), param.getFilter());

		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		__dld.setNumRows(__count);

		return __dld;
	}

	public Integer getDataSourceListRowCount(String sessionId, Integer resourceId, List<FilterParam> filter) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		int __count = this.dataSourceDAS.getDataSourceListRowCount(sessionId, resourceId, filter);
		return __count;
	}

	public DojoListData findUndefinedDojoListByCondition(DojoListParam param) {
		Session session = sessionService.getSession(param.getSessionId());
		if (!this.isSessionVaild(session)) {
			return null;
		}
		DojoListData __dld = new DojoListData();
		Map<String, Object> mapResults = new HashMap<String, Object>();
		List<JSONObject> queryResult = new ArrayList<JSONObject>();
		int __count = 0;
		try {

			mapResults = dataSourceDAS.findUndefinedListByCondition(null, null, param.getFilter(), param.getBegin(), param.getLimit());
			if (mapResults != null) {
				queryResult = (List<JSONObject>) mapResults.get("listResult");
				__count = (Integer) mapResults.get("totalCount");
				;
				__dld.setIdentifier("row_num");
				if (queryResult == null) {
					System.out.println("为空");
				}
				__dld.setItems(queryResult.toArray());
				__dld.setLabel("name");
				__dld.setNumRows(__count);
			} else {
				__dld.setIdentifier("row_num");
				__dld.setItems(null);
				__dld.setLabel("name");
				__dld.setNumRows(0);
			}
		} catch (Exception e) {
			System.out.println("获得数据报错");
		}
		return __dld;
	}

	/**
	 * 获取用户的数据库表列表 返回字符串
	 */
	public List<String> getDbAllTablesByUser(String sessionId, String user, String password, String ip, String port, String server) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<String> __list = _db.getDbAllTablesByUser(user, password, ip, port, server);
		return __list;
	}

	/**
	 * 获取用户的数据库视图列表 返回字符串
	 */
	public List<String> getDbAllViewsByUser(String sessionId, String user, String password, String ip, String port, String server) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<String> __list = _db.getDbAllViewsByUser(user, password, ip, port, server);
		return __list;
	}

	public List<ProtoDBField> getDbAllTableFieldsByUser(String sessionId, String user, String password, String ip, String port, String server, String tableName) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<ProtoDBField> __list = _db.getDbAllTableFieldsByUser(user, password, ip, port, server, tableName);
		return __list;
	}

	/**
	 * 获取存储过程名称
	 */
	public List<String> getDbAllProcByUser(String sessionId, String user, String password, String ip, String port, String server) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<String> __list = _db.getDbAllProcByUser(user, password, ip, port, server);
		return __list;
	}

	public String createReportXml(String sessionId, Integer form_code, List<JSONObject> griddata) {
		// boolean b = false;
		FilterParam filter = new FilterParam();
		filter.setField("form_code");
		filter.setLogical("=");
		filter.setRelation("and");
		filter.setValue(form_code.toString());
		List<FilterParam> list = new ArrayList<FilterParam>();
		list.add(filter);
		List<GridField> fieldList = gridFieldService.findGridFieldListByCondition(sessionId, list);
		try {
			XMLWriter writer = null;// 声明写XML的对象
			// SAXReader reader = new SAXReader();
			// OutputFormat format = OutputFormat.createPrettyPrint();
			// format.setEncoding("GBK");// 设置XML文件的编码格式
			// String filePath = "d:\\student.xml";
			// File file = new File(filePath);
			// if (file.exists()) {
			// Document document = reader.read(file);// 读取XML文件
			// Element root = document.getRootElement();// 得到根节点
			// Element _workSheet = root.element("WorkSheet");
			// boolean bl = false;
			// Element table = _workSheet.element("Table");
			// boolean c = _workSheet.remove(table);
			// if (c) {
			// DataSourceServiceImpl.createReportTableByData(_workSheet,
			// fieldList, griddata);
			// }
			// writer = new XMLWriter(new FileWriter(filePath), format);
			// writer.write(document);
			// writer.close();
			// bl = true;
			// return bl;
			// } else {
			// 新建student.xml文件并新增内容
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
			boolean table = DataSourceServiceImpl.createReportTableByData(_workSheet, fieldList, griddata);
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

	private static boolean createReportTableByData(Element _workSheet, List<GridField> fieldList, List<JSONObject> griddata) {
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
			tdBt1.addText("***通用报表");

			Element trBt2 = table.addElement("TR");
			trBt2.addAttribute("height", "24");
			trBt2.addAttribute("sequence", "2");
			Element tdBt2 = trBt2.addElement("TD");
			tdBt2.addAttribute("col", "1");
			tdBt2.addText("打印时间:" + sdf.format(new Date()));

			Element trBt = table.addElement("TR");
			trBt.addAttribute("height", "24");
			trBt.addAttribute("sequence", "3");
			for (int j = 1; j <= fieldList.size(); j++) {
				GridField field = fieldList.get(j - 1);
				String formatString = field.getFormat();
				JSONObject formatJson = new JSONObject(formatString);
				String field_label = formatJson.getString("field_value");
				if (j == 1) {
					Element tdBt = trBt.addElement("TD");
					tdBt.addAttribute("col", "" + j + "");
					tdBt.addAttribute("fontIndex", "2");
					tdBt.addAttribute("leftBorder", "2");
					tdBt.addAttribute("topBorder", "2");
					tdBt.addAttribute("align", "center");
					tdBt.setText(field_label);
				} else if (j == fieldList.size()) {
					Element tdBt = trBt.addElement("TD");
					tdBt.addAttribute("col", "" + j + "");
					tdBt.addAttribute("fontIndex", "2");
					tdBt.addAttribute("leftBorder", "1");
					tdBt.addAttribute("topBorder", "2");
					tdBt.addAttribute("align", "center");
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
					tdBt.addAttribute("align", "center");
					tdBt.setText(field_label);
				}
			}
			int num = 4;
			for (JSONObject object : griddata) {
				Element trtd = table.addElement("TR");
				trtd.addAttribute("height", "24");
				trtd.addAttribute("sequence", "" + num + "");
				for (int m = 1; m <= fieldList.size(); m++) {
					GridField field = fieldList.get(m - 1);
					String value = object.getString(field.getName());
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
			tr.addAttribute("height", "15");
			tr.addAttribute("sequence", "" + num + "");
			for (int k = 1; k <= fieldList.size(); k++) {
				Element td = tr.addElement("TD");
				td.addAttribute("col", k + "");
				td.addAttribute("topBorder", "2");
			}
			Element trPrintTime = table.addElement("TR");
			trPrintTime.addAttribute("height", "30");
			trPrintTime.addAttribute("sequence", "6");

			for (int n = 1; n <= fieldList.size(); n++) {
				Element td = tr.addElement("TD");
				td.addAttribute("col", n + "");
				td.addAttribute("topBorder", "2");
			}
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;

	}

	/**
	 * 判断存储过程有效性
	 */
	public Boolean testProc(String sessionId, String user, String password, String ip, String port, String server, String procName) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<String[]> __list = _db.getDbProcFieldsByUser(user, password, ip, port, server, procName);
		return __list != null;
	}

	/**
	 * 判断SQL语句有效性
	 */
	public Boolean testSql(String sessionId, String user, String password, String ip, String port, String server, String sql) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<String[]> __list = _db.getDbTableFieldsByUser(user, password, ip, port, server, sql);
		return __list != null;
	}

	/**
	 * true 有效， false 失效
	 *
	 * @param session
	 * @return
	 */
	protected boolean isSessionVaild(Session session) {
		boolean sessionVaild = true;
		try {
			if (null == session.getId() && session.getState() == 0) {
				sessionVaild = false;
			}
		} catch (Exception e) {
			sessionVaild = false;
			_log.debug(e.getMessage());
		}
		return sessionVaild;
	}
}
