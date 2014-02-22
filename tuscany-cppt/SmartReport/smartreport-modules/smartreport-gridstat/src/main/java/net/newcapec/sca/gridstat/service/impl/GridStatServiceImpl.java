package net.newcapec.sca.gridstat.service.impl;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.gridstat.GridStat;
import net.newcapec.sca.gridstat.das.GridStatDAS;
import net.newcapec.sca.gridstat.service.GridStatService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 * @author huangxin
 *
 */
public class GridStatServiceImpl implements GridStatService {

	private static final Logger _log = Logger.getLogger(GridStatServiceImpl.class);

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private GridStatDAS gridStatDAS;

	@Reference(name = "gridStatDAS", required = true)
	public void setGridStatDAS(GridStatDAS gridStatDAS) {
		this.gridStatDAS = gridStatDAS;
	}

	public GridStat getGridStatById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		GridStat __gs = this.gridStatDAS.getGridStatById(id);
		return __gs;
	}

	public DojoListData findGridStatList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}

		List<GridStat> __list = this.gridStatDAS.findGridStatList(1, 1, filter, begin, limit);

		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		return __dld;
	}

	public GridStat insertGridStat(String sessionId, GridStat gridStat) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		this.gridStatDAS.insertGridStat(gridStat);
		return gridStat;
	}

	public GridStat updateGridStat(String sessionId, GridStat gridStat) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		this.gridStatDAS.updateGridStat(gridStat);
		return gridStat;
	}

	public GridStat delGridStatById(String sessionId, Integer id) {
		return null;
	}

	public GridStat delGridStatByIds(String sessionId, String ids) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		// 验证 sessionId 是否合法

		_log.info("参数：" + ids);

		GridStat __gs = new GridStat();
		ResultMsg __rm = new ResultMsg();

		if ("".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setErrorMsg("参数 ids 不能为空");
			__gs.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
			return __gs;
		}

		String[] __idsArr = ids.split(",");

		Integer[] __ids = new Integer[__idsArr.length];

		for (int __i_3 = 0, __j_3 = __idsArr.length; __i_3 < __j_3; __i_3++) {
			__ids[__i_3] = Integer.parseInt(__idsArr[__i_3]);
		}

		boolean __delResult = this.gridStatDAS.delGridStatByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除 customForm 失败");
			__gs.setResultMsg(__rm);
		}

		return __gs;
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
