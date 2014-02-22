package net.newcapec.sca.gridcust.service.impl;

/**
 * <p>Title: 业务构件实现 </p>
 * <p>Description:用户密码修改业务构件实现</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-25
 * 修改日期：
 * 修改人：
 * 复审人：
 */


import java.util.List;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

import net.newcapec.sca.datasource.DataSource;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.gridcust.GridCustom;
import net.newcapec.sca.gridcust.das.GridCustomDAS;
import net.newcapec.sca.gridcust.service.GridCustomService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

public class GridCustomServiceImpl implements GridCustomService {

	private static final Logger _log = Logger
	.getLogger(GridCustomServiceImpl.class);

	private GridCustomDAS gridCustomDAS;

	@Reference(name = "gridCustomDAS", required = true)
	public void setGridCustomDAS(GridCustomDAS gridCustomDAS) {
		this.gridCustomDAS = gridCustomDAS;
	}

	private SessionService sessionService;
	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}


	/**
	 * @Title:getGridCustomById
	 * @Description:根据id获得GridCustom
	 * @return  GridCustom
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-25
	 * 修改日期： 修改人： 复审人：
	 */
	public GridCustom getGridCustomById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		GridCustom __form = gridCustomDAS.getGridCustomById(id);
		return __form;
	}

	/**
	 * @findGridCustomList
	 * @Description:根据条件获得GridCustom列表
	 * @return  List<GridCustom>
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-25
	 * 修改日期： 修改人： 复审人：
	 */
	public  List<GridCustom>  findGridCustomList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit) {

		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<GridCustom> __list =  gridCustomDAS.findGridCustomList(1, 1,
				filter, begin, limit);
		return __list;
	}


	/**
	 * @insertGridCustom
	 * @Description:添加GridCustom
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-25
	 * 修改日期： 修改人： 复审人：
	 */
	public GridCustom insertGridCustom(String sessionId, GridCustom gridCustom) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Boolean __b = gridCustomDAS.insertGridCustom(gridCustom);
		ResultMsg __rm = new ResultMsg();
		if (!__b) {
			__rm.setErrorId(205);
			__rm.setStatus(0);
			__rm.setErrorMsg("添加用户自定义配置失败");
			_log.info(__rm.getErrorMsg());
		}else{
			__rm.setStatus(1);
		}
		gridCustom.setResultMsg(__rm);
		return gridCustom;
	}


	/**
	 * updateGridCustom
	 * @Description:修改GridCustom
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-25
	 * 修改日期： 修改人： 复审人：
	 */
	public GridCustom updateGridCustom(String sessionId, GridCustom gridCustom) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Boolean __b = gridCustomDAS.updateGridCustom(gridCustom);
		ResultMsg __rm = new ResultMsg();
		if (!__b) {
			__rm.setErrorId(205);
			__rm.setStatus(0);
			__rm.setErrorMsg("修改用户自定义配置失败");
			_log.info(__rm.getErrorMsg());
		}else{
			__rm.setStatus(1);
		}
		gridCustom.setResultMsg(__rm);

		return gridCustom;
	}

	public GridCustom delGridCustomById(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @delGridCustomByIds
	 * @Description:根据ids删除GridCustom
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-25
	 * 修改日期： 修改人： 复审人：
	 */
	public GridCustom delGridCustomByIds(String sessionId, String ids) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		GridCustom __form = new GridCustom();

		ResultMsg __rm = new ResultMsg();
		if ("".equals(ids)) {
			__rm.setStatus(0);
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

		boolean __delResult =  gridCustomDAS.delGridCustomByIds(__ids);

		if (!__delResult) {

			__rm.setErrorId(203);
			__rm.setStatus(0);
			__rm.setErrorMsg("删除用户自定义配置 失败");

		}else{
			__rm.setStatus(1);

		}
		__form.setResultMsg(__rm);

		return __form;
	}


	/**
	 * @findGridCustomList
	 * @Description:根据条件获得GridCustom列表并转化为DojoListData
	 * @return  DojoListData
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-25
	 * 修改日期： 修改人： 复审人：
	 */
	public  DojoListData findGridCustomDojoList(DojoListParam param){
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<GridCustom> __list = gridCustomDAS.findGridCustomList(null, null,
				param.getFilter(), param.getBegin(), param.getLimit());

		int __count = gridCustomDAS.getGridCustomListRowCount(null,
				null, param.getFilter());
		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		__dld.setNumRows(__count);

		return __dld;
	}

	/**
	 * @getGridCustomListRowCount
	 * @Description:根据条件获得GridCustom的数量
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-25
	 * 修改日期： 修改人： 复审人：
	 */
	public  Integer  getGridCustomListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter){

		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		int __count = gridCustomDAS.getGridCustomListRowCount(null,
				null, filter);
		return __count;

	}

	/**
	 * @isSessionVaild
	 * @Description:验证session是否有效
	 * @return  boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-27
	 * 修改日期： 修改人： 复审人：
	 */
	protected boolean isSessionVaild(Session session)
	{
		boolean sessionVaild = true;

		try
		{
			if (null == session.getId() && session.getState() == 0)
			{
				sessionVaild = false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sessionVaild = false;
		}
		return sessionVaild;
	}

}
