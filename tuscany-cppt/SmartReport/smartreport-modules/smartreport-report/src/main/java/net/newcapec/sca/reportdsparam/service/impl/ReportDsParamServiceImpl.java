package net.newcapec.sca.reportdsparam.service.impl;

import java.util.List;

import net.newcapec.sca.reportdsparam.ReportDsParam;
import net.newcapec.sca.reportdsparam.das.ReportDsParamDAS;
import net.newcapec.sca.reportdsparam.service.ReportDsParamService;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 * @author huangxin
 *
 */
public class ReportDsParamServiceImpl implements ReportDsParamService {

	private static final Logger _log = Logger.getLogger(ReportDsParamServiceImpl.class);

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private ReportDsParamDAS reportDsParamDAS;

	@Reference(name = "reportDsParamDAS", required = true)
	public void setReportDsParamDAS(ReportDsParamDAS reportDsParamDAS) {
		this.reportDsParamDAS = reportDsParamDAS;
	}

	public List<ReportDsParam> findReportDsParamList(String sessionId, Integer reportDsId, Integer dsId, String dsType) {
		List<ReportDsParam> __list = this.reportDsParamDAS.findReportDsParamList(1, 1, reportDsId, dsId, dsType);
		return __list;
	}

	public List<ReportDsParam> findReportDsParamList2(String sessionId, Integer reportId) {
		List<ReportDsParam> __list = this.reportDsParamDAS.findReportDsParamList2(1, 1, reportId);
		return __list;
	}

	public ReportDsParam insertReportDsParam(String sessionId, ReportDsParam reportDsParam) {
		ReportDsParam __b = this.reportDsParamDAS.insertReportDsParam(reportDsParam);
		// if (!__b) {
		// ResultMsg __rm = new ResultMsg();
		// __rm.setErrorId(205);
		// __rm.setErrorMsg("添加新报表数据源参数失败");
		// reportDsParam.setResultMsg(__rm);
		// _log.info(__rm.getErrorMsg());
		// }
		return __b;
	}

	public ReportDsParam updateReportDsParam(String sessionId, ReportDsParam reportDsParam) {
		Boolean __b = this.reportDsParamDAS.updateReportDsParam(reportDsParam);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("更新报表数据源参数失败");
			reportDsParam.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return reportDsParam;
	}

	public ReportDsParam delReportDsParamByIds(String sessionId, String ids) {
		// 验证 sessionId 是否合法

		_log.info("参数：" + ids);

		ReportDsParam __rdp = new ReportDsParam();
		ResultMsg __rm = new ResultMsg();

		if ("".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setErrorMsg("参数 ids 不能为空");
			__rdp.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
			return __rdp;
		}

		String[] __idsArr = ids.split(",");

		Integer[] __ids = new Integer[__idsArr.length];

		for (int __i_3 = 0, __j_3 = __idsArr.length; __i_3 < __j_3; __i_3++) {
			__ids[__i_3] = Integer.parseInt(__idsArr[__i_3]);
		}

		boolean __delResult = this.reportDsParamDAS.delReportDsParamByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除 ReportDsParam 失败");
			__rdp.setResultMsg(__rm);
		}

		return __rdp;
	}

	public List<ReportDsParam> findReportDsParamByReportDsId(String sessionId,
			Integer reportDsId) {
		List<ReportDsParam> list = this.reportDsParamDAS.findReportDsParamList3(1, 1, reportDsId);
		return list;
	}

}
