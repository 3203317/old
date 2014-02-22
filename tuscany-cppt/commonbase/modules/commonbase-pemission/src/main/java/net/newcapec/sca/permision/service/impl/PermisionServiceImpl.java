package net.newcapec.sca.permision.service.impl;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.permision.Permision;
import net.newcapec.sca.permision.das.PermisionDAS;
import net.newcapec.sca.permision.service.PermisionService;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

/**
 * <p>
 * Title: PermisionService
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
public class PermisionServiceImpl implements PermisionService {

	private static final Logger _log = Logger.getLogger(PermisionServiceImpl.class);

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private PermisionDAS permisionDAS;

	@Reference(name = "permisionDAS", required = true)
	public void setPermisionDAS(PermisionDAS permisionDAS) {
		this.permisionDAS = permisionDAS;
	}

	public Permision getPermisionById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		Permision __form = this.permisionDAS.getPermisionById(id);
		return __form;
	}

	public Permision insertPermision(String sessionId, Permision permision) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		permision.setCreate_user_account_id(Integer.parseInt(session.getUser_code()));
		Boolean __b = this.permisionDAS.insertPermision(permision);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("add err");
			permision.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return permision;
	}

	public Permision updatePermision(String sessionId, Permision permision) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		this.permisionDAS.updatePermision(permision);
		return permision;
	}

	public Permision delPermisionById(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Permision delPermisionByIds(String sessionId, String ids) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		// 验证 sessionId 是否合法

		_log.info("参数：" + ids);

		Permision __form = new Permision();
		ResultMsg __rm = new ResultMsg();

		if ("".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setErrorMsg("参数 ids 不能为空");
			__form.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
			return __form;
		}

		String[] __idsArr = ids.split(",");

		Integer[] __ids = new Integer[__idsArr.length];

		for (int __i_3 = 0, __j_3 = __idsArr.length; __i_3 < __j_3; __i_3++) {
			__ids[__i_3] = Integer.parseInt(__idsArr[__i_3]);
		}

		boolean __delResult = this.permisionDAS.delPermisionByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除 permision 失败");
			__form.setResultMsg(__rm);
		}

		return __form;
	}

	public List<Permision> findPermisionList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<Permision> __list = this.permisionDAS.findPermisionList(1, 1, filter, begin, limit);
		return __list;
	}

	public DojoListData findPermisionDojoList(DojoListParam param) {
		Session session = sessionService.getSession(param.getSessionId());
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<Permision> __list = this.permisionDAS.findPermisionList(1, 1, param.getFilter(), param.getBegin(), param.getLimit());

		int __count = this.permisionDAS.getPermisionListRowCount(param.getSessionId(), param.getResourceId(), param.getFilter());

		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		__dld.setNumRows(__count);
		return __dld;
	}

	public Integer getPermisionListRowCount(String sessionId, Integer resourceId, List<FilterParam> filter) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		int __count = this.permisionDAS.getPermisionListRowCount(sessionId, resourceId, filter);
		return __count;
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
