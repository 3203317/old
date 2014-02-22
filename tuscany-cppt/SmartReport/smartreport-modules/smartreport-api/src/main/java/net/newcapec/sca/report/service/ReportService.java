package net.newcapec.sca.report.service;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.report.PrintSetInfo;
import net.newcapec.sca.report.Report;
import net.newcapec.sca.report.ReportGridConditionDataCollection;
import net.newcapec.sca.reportdsfield.ReportDsField;
import net.newcapec.sca.resource.bean.MenuItem;

import org.json.JSONObject;
import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
@Remotable
public interface ReportService {
	public Report getReportById(String sessionId, Integer id);

	public DojoListData findReportList(DojoListParam param);

	public Report insertReport(String sessionId, Report report);

	public Report updateReport(String sessionId, Report report);

	public Report delReportById(String sessionId, Integer id);

	public Report delReportByIds(String sessionId, String ids);

	public Report updateReportTemplet(String sessionId, Report report);

	public JSONObject getReportData(String sessionId, Integer id, FilterParam[] params);

	/**
	 *
	 * @author 赵鹏飞
	 *         描述：根据sessionId和表单id,数据源id和查询条件，查询field,view,condition,dicitem的相关信息
	 *         时间： 2012-12-5 ReportService.java
	 * @param sessionId
	 * @param form_code
	 * @return
	 */
	public ReportGridConditionDataCollection getReportGridConditonDataCollection(String sessionId, Integer ds_code, Integer form_code, Integer report_ds_code);

	/**
	 *
	 * @author Administrator 描述： 时间： 2013-1-15 ReportService.java
	 * @param sessionId
	 * @param form_code
	 * @param griddata
	 * @return
	 */
	public String createReportXml(String sessionId, PrintSetInfo printSetInfo,List<ReportDsField> fieldList, List<JSONObject> griddata);

	public MenuItem[] getFilteringSelectList(String sessionId);
}
