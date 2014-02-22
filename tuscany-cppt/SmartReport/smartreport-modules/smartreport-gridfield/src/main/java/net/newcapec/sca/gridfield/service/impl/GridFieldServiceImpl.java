package net.newcapec.sca.gridfield.service.impl;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.gridfield.GridField;
import net.newcapec.sca.gridfield.das.GridFieldDAS;
import net.newcapec.sca.gridfield.service.GridFieldService;
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
public class GridFieldServiceImpl implements GridFieldService {

	private static final Logger _log = Logger.getLogger(GridFieldServiceImpl.class);

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private GridFieldDAS gridFieldDAS;

	@Reference(name = "gridFieldDAS", required = true)
	public void setGridFieldDAS(GridFieldDAS gridFieldDAS) {
		this.gridFieldDAS = gridFieldDAS;
	}

	public GridField getGridFieldById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		GridField __gf = this.gridFieldDAS.getGridFieldById(id);
		return __gf;
	}

	public DojoListData findGridFieldList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}

		List<GridField> __list = this.gridFieldDAS.findGridFieldList(1, 1, filter, begin, limit);

		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		return __dld;
	}

	public GridField insertGridField(String sessionId, GridField gridField) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		this.gridFieldDAS.insertGridField(gridField);
		return gridField;
	}

	public GridField updateGridField(String sessionId, GridField gridField) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		this.gridFieldDAS.updateGridField(gridField);
		return gridField;
	}

	public GridField delGridFieldById(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public GridField delGridFieldByIds(String sessionId, String ids) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		// 验证 sessionId 是否合法

		_log.info("参数：" + ids);

		GridField __gf = new GridField();
		ResultMsg __rm = new ResultMsg();

		if ("".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setErrorMsg("参数 ids 不能为空");
			__gf.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
			return __gf;
		}

		String[] __idsArr = ids.split(",");

		Integer[] __ids = new Integer[__idsArr.length];

		for (int __i_3 = 0, __j_3 = __idsArr.length; __i_3 < __j_3; __i_3++) {
			__ids[__i_3] = Integer.parseInt(__idsArr[__i_3]);
		}

		boolean __delResult = this.gridFieldDAS.delGridFieldByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除 customForm 失败");
			__gf.setResultMsg(__rm);
		}

		return __gf;
	}

	public GridField insertGridFields(String sessionId, List<GridField> gridFields) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		System.out.println("insertGridField   ****************************** **********");
		for (int __i_3 = 0, __j_3 = (gridFields == null ? 0 : gridFields.size()); __i_3 < __j_3; __i_3++) {
			GridField __gridField_4 = gridFields.get(__i_3);
			this.gridFieldDAS.insertGridField(__gridField_4);
		}
		GridField gridField = new GridField();
		return gridField;
	}

	public List<GridField> findGridFieldListByCondition(String sessionId, List<FilterParam> filter) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<GridField> __list = null;
		try {
			__list = gridFieldDAS.findGridFieldListByCondition(filter);
		} catch (Exception e) {
			System.out.println("获得数据失败");
		} finally {
			return __list;
		}

	}

	public Boolean updateGridFields(String sessionId, List<GridField> gridFields) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		System.out.println("gridFields size 大小"+gridFields.size());
		Boolean __allSuccess = true;
		for (int __i_3 = 0; __i_3 < gridFields.size(); __i_3++) {
			GridField __gridField = gridFields.get(__i_3);
			System.out.println("code =============== zpfupdateGridFields           " + __gridField.getCode());
			if (__gridField.getCode() == 0){
				System.out.println("code =============== zpf           " + __gridField.getCode());
				__allSuccess = gridFieldDAS.insertGridField(__gridField);
			}else{
				__allSuccess = gridFieldDAS.updateGridField(__gridField);
			}
		}
		return __allSuccess;

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
