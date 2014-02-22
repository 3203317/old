package net.newcapec.sca.reportdsfield.service.impl;

import java.util.List;

import net.newcapec.sca.reportds.service.impl.ReportDsServiceImpl;
import net.newcapec.sca.reportdsfield.ReportDsField;
import net.newcapec.sca.reportdsfield.das.ReportDsFieldDAS;
import net.newcapec.sca.reportdsfield.service.ReportDsFieldService;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class ReportDsFieldServiceImpl implements ReportDsFieldService {

	private static final Logger _log = Logger.getLogger(ReportDsServiceImpl.class);

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private ReportDsFieldDAS reportDsFieldDAS;

	@Reference(name = "reportDsFieldDAS", required = true)
	public void setReportDsFieldDAS(ReportDsFieldDAS reportDsFieldDAS) {
		this.reportDsFieldDAS = reportDsFieldDAS;
	}

	public List<ReportDsField> findReportDsFieldList(String sessionId, Integer reportDsId, Integer dsId) {
		List<ReportDsField> __list = this.reportDsFieldDAS.findReportDsFieldList(1, 1, reportDsId, dsId);
		return __list;
	}

	public ReportDsField updateReportDsField(String sessionId, ReportDsField reportDsField) {
		Boolean __b = this.reportDsFieldDAS.updateReportDsField(reportDsField);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("更新报表数据源信息失败");
			reportDsField.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return reportDsField;
	}

}
