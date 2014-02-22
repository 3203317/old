package net.newcapec.sca.reportds.service.impl;

import java.util.List;

import net.newcapec.sca.reportds.ReportDs;
import net.newcapec.sca.reportds.das.ReportDsDAS;
import net.newcapec.sca.reportds.service.ReportDsService;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 * @author huangxin
 *
 */
public class ReportDsServiceImpl implements ReportDsService {

	private static final Logger _log = Logger.getLogger(ReportDsServiceImpl.class);

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private ReportDsDAS reportDsDAS;

	@Reference(name = "reportDsDAS", required = true)
	public void setReportDsDAS(ReportDsDAS reportDsDAS) {
		this.reportDsDAS = reportDsDAS;
	}

	public List<ReportDs> findReportDsList(String sessionId, Integer reportId) {
		List<ReportDs> __list = this.reportDsDAS.findReportDsList(1, 1, reportId);
		return __list;
	}

	public ReportDs insertReportDs(String sessionId, ReportDs reportDs) {
		reportDs.setType(2);
		reportDs = this.reportDsDAS.insertReportDs(reportDs);
		if (reportDs == null) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("添加新报表数据源失败");
			ReportDs __reportDs_3 = new ReportDs();
			__reportDs_3.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return reportDs;
	}

	public ReportDs updateReportDs(String sessionId, ReportDs reportDs) {
		Boolean __b = this.reportDsDAS.updateReportDs(reportDs);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("更新报表数据源信息失败");
			reportDs.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return reportDs;
	}

	public ReportDs delReportDsById(String sessionId, Integer dsId, Integer reportDsId, String xml) {
		// 验证 sessionId 是否合法

		ReportDs __reportDs = new ReportDs();
		ResultMsg __rm = new ResultMsg();

		boolean __delResult = this.reportDsDAS.delReportDsById(dsId, reportDsId, xml);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除报表数据源失败");
			__reportDs.setResultMsg(__rm);
		}

		return __reportDs;
	}

}
