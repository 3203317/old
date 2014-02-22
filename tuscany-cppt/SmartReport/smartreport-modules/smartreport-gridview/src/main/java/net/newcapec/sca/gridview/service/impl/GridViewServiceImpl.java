package net.newcapec.sca.gridview.service.impl;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.gridview.GridView;
import net.newcapec.sca.gridview.das.GridViewDAS;
import net.newcapec.sca.gridview.service.GridViewService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.reportds.ReportDs;
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
public class GridViewServiceImpl implements GridViewService {

	private static final Logger _log = Logger.getLogger(GridViewServiceImpl.class);

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private GridViewDAS gridViewDAS;

	@Reference(name = "gridViewDAS", required = true)
	public void setGridViewDAS(GridViewDAS gridViewDAS) {
		this.gridViewDAS = gridViewDAS;
	}

	public GridView getGridViewById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		GridView __gv = this.gridViewDAS.getGridViewById(id);
		return __gv;
	}

	public DojoListData findGridViewList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}

		List<GridView> __list = this.gridViewDAS.findGridViewList(1, 1, filter, begin, limit);

		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		return __dld;
	}

	public GridView insertGridView(String sessionId, GridView gridview) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		Boolean b = this.gridViewDAS.insertGridView(gridview);
		ResultMsg __rm = new ResultMsg();
		if (b == false) {
			__rm.setErrorId(205);
			__rm.setErrorMsg("添加失败");
			gridview.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return gridview;
	}

	public GridView updateGridView(String sessionId, GridView gridview) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		Boolean b = this.gridViewDAS.updateGridView(gridview);
		ResultMsg __rm = new ResultMsg();
		if (b == false) {
			__rm.setErrorId(205);
			__rm.setErrorMsg("添加失败");
			gridview.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return gridview;
	}

	public GridView delGridViewById(String sessionId, Integer id) {
		return null;
	}

	public GridView delGridViewByIds(String sessionId, String ids) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		// 验证 sessionId 是否合法

		_log.info("参数：" + ids);

		GridView __gv = new GridView();
		ResultMsg __rm = new ResultMsg();

		if ("".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setErrorMsg("参数 ids 不能为空");
			__gv.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
			return __gv;
		}

		String[] __idsArr = ids.split(",");

		Integer[] __ids = new Integer[__idsArr.length];

		for (int __i_3 = 0, __j_3 = __idsArr.length; __i_3 < __j_3; __i_3++) {
			__ids[__i_3] = Integer.parseInt(__idsArr[__i_3]);
		}

		boolean __delResult = this.gridViewDAS.delGridViewByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除 customForm 失败");
			__gv.setResultMsg(__rm);
		}

		return __gv;
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
