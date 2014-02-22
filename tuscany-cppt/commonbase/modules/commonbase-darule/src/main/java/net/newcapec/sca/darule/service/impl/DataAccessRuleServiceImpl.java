package net.newcapec.sca.darule.service.impl;

import net.newcapec.sca.darule.DataAccessRule;
import net.newcapec.sca.darule.das.DataAccessRuleDAS;
import net.newcapec.sca.darule.service.DataAccessRuleService;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

/**
 * <p>
 * Title: DataaccessruleService
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright(c) 2011 郑州新开普电子股份有限公司
 * </p>
 *
 * @author 黄鑫 (huangxin)
 * @version
 * @data 创建日期：2011-11-11 修改日期： 修改人： 复审人：
 * @generated
 */
public class DataAccessRuleServiceImpl implements DataAccessRuleService {

	private static final Logger _log = Logger.getLogger(DataAccessRuleServiceImpl.class);

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private DataAccessRuleDAS dataAccessRuleDAS;

	@Reference(name = "dataAccessRuleDAS", required = true)
	public void setDataAccessRuleDAS(DataAccessRuleDAS dataAccessRuleDAS) {
		this.dataAccessRuleDAS = dataAccessRuleDAS;
	}

	public DataAccessRule getDataAccessRuleByResId(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		DataAccessRule __form = this.dataAccessRuleDAS.getDataAccessRuleByResId(id);
		return __form;
	}

	public DataAccessRule setDataAccessRule(String sessionId, DataAccessRule dataAccessRule) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		// 查询资源code，是否存在
		DataAccessRule __dataAccessRule = this.dataAccessRuleDAS.getDataAccessRuleByResId(dataAccessRule.getResource_code());

		// 说明不存在，则需要insert
		if (__dataAccessRule == null) {
			Boolean __bool_3 = this.dataAccessRuleDAS.insertDataAccessRule(dataAccessRule);
			if (!__bool_3) {
				ResultMsg __rm = new ResultMsg();
				__rm.setErrorId(205);
				__rm.setErrorMsg("添加新资源规则失败");
				dataAccessRule.setResultMsg(__rm);
				_log.info(__rm.getErrorMsg());
			}
		} else {
			Boolean __bool_3 = this.dataAccessRuleDAS.updateDataAccessRule(dataAccessRule);
			if (!__bool_3) {
				ResultMsg __rm = new ResultMsg();
				__rm.setErrorId(205);
				__rm.setErrorMsg("更新资源规则失败");
				dataAccessRule.setResultMsg(__rm);
				_log.info(__rm.getErrorMsg());
			}
		}
		return dataAccessRule;
	}

	public DataAccessRule delDataAccessRuleByResId(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		Boolean __boolean = this.dataAccessRuleDAS.delDataAccessRuleByResId(id);
		DataAccessRule dataAccessRule = new DataAccessRule();
		if (!__boolean) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("删除资源规则失败");
			dataAccessRule.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return dataAccessRule;
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
