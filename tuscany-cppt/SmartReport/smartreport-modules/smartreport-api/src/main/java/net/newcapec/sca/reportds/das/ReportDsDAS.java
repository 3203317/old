package net.newcapec.sca.reportds.das;

import java.util.List;

import net.newcapec.sca.reportds.ReportDs;

/**
 *
 * @author huangxin
 *
 */
public interface ReportDsDAS {

	public List<ReportDs> findReportDsList(Integer domainId, Integer orgId, Integer reportId);

	public ReportDs insertReportDs(ReportDs reportDs);

	public Boolean updateReportDs(ReportDs reportDs);

	public Boolean delReportDsById(Integer dsId, Integer reportDsId, String xml);
}
