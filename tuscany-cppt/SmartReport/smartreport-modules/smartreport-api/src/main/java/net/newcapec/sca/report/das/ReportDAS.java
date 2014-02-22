package net.newcapec.sca.report.das;

import java.util.List;

import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.report.Report;

import org.json.JSONObject;

/**
 *
 * @author huangxin
 *
 */
public interface ReportDAS {

	public Report getReportById(Integer id);

	public List<Report> findReportList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer begin, Integer limit);

	public Boolean insertReport(Report report);

	public Boolean updateReport(Report report);

	public Boolean updateReportTemplet(Report report);

	public Boolean delReportById(Integer id);

	public Boolean delReportByIds(Integer[] ids);

	public Integer getReportListRowCount(List<FilterParam> filter);

	/**
	 * 设计时
	 *
	 * @param reportId
	 * @return
	 */
	public JSONObject getReportData(Integer reportId);

	/**
	 * 正式使用
	 *
	 * @param reportId
	 * @param params
	 * @return
	 */
	public JSONObject getReportData2(Integer reportId, FilterParam[] params);
}
