package net.newcapec.sca.reportdsparam.das;

import java.util.List;

import net.newcapec.sca.reportdsparam.ReportDsParam;

/**
 *
 * @author huangxin
 *
 */
public interface ReportDsParamDAS {

	public List<ReportDsParam> findReportDsParamList(Integer domainId, Integer orgId, Integer reportDsId, Integer dsId, String dsType);

	public List<ReportDsParam> findReportDsParamList2(Integer domainId, Integer orgId, Integer reportId);

	public ReportDsParam insertReportDsParam(ReportDsParam reportDsParam);

	public Boolean updateReportDsParam(ReportDsParam reportDsParam);

	public Boolean delReportDsParamByIds(Integer[] ids);

	public List<ReportDsParam> findReportDsParamList3(Integer domainId, Integer orgId, Integer reportDsId);

}
