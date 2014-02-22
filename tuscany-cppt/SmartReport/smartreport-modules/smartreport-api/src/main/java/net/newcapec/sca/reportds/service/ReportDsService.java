package net.newcapec.sca.reportds.service;

import java.util.List;

import net.newcapec.sca.reportds.ReportDs;

import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
@Remotable
public interface ReportDsService {

	public List<ReportDs> findReportDsList(String sessionId, Integer reportId);

	public ReportDs insertReportDs(String sessionId, ReportDs reportDs);

	public ReportDs updateReportDs(String sessionId, ReportDs reportDs);

	public ReportDs delReportDsById(String sessionId, Integer dsId, Integer reportDsId, String xml);

}
