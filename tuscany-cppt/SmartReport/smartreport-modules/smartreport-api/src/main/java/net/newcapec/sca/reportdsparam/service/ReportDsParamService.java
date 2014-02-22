package net.newcapec.sca.reportdsparam.service;

import java.util.List;

import net.newcapec.sca.reportdsparam.ReportDsParam;

import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
@Remotable
public interface ReportDsParamService {

	public List<ReportDsParam> findReportDsParamList(String sessionId, Integer reportDsId, Integer dsId, String dsType);

	public List<ReportDsParam> findReportDsParamList2(String sessionId, Integer reportId);

	public ReportDsParam insertReportDsParam(String sessionId, ReportDsParam reportDsParam);

	public ReportDsParam updateReportDsParam(String sessionId, ReportDsParam reportDsParam);

	public ReportDsParam delReportDsParamByIds(String sessionId, String ids);
	/**
	 *
	 * @author zpf
	 * 描述：通过该方法可以获取参数信息以及相对应的控件数据信息
	 * 时间： 2013-1-16
	 * ReportDsParamService.java
	 * @param domainId
	 * @param orgId
	 * @param reportId
	 * @return
	 */
	public List<ReportDsParam> findReportDsParamByReportDsId(String sessionId, Integer reportDsId);

}
