package net.newcapec.sca.customform.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.customform.CustomForm;
import net.newcapec.sca.customform.das.CustomFormDAS;
import net.newcapec.sca.customform.service.CustomFormService;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.resource.Resource;
import net.newcapec.sca.resource.bean.MenuItem;
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
public class CustomFormServiceImpl implements CustomFormService {

	private static final Logger _log = Logger
			.getLogger(CustomFormServiceImpl.class);

	private CustomFormDAS customFormDAS;

	@Reference(name = "customFormDAS", required = true)
	public void setCustomFormDAS(CustomFormDAS customFormDAS) {
		this.customFormDAS = customFormDAS;
	}

	//注入session  added pxx
	private SessionService sessionService;
	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public CustomForm getCustomFormById(String sessionId, Integer id) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		CustomForm __form = this.customFormDAS.getCustomFormById(id);
		return __form;
	}

	public CustomForm insertCustomForm(String sessionId, CustomForm customForm) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		customForm.setDomain_code(1);//zpf
		customForm.setUnit_code(1);//zpf
		Boolean __b = this.customFormDAS.insertCustomForm(customForm);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("添加新用户失败");
			customForm.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return customForm;
	}

	public CustomForm updateCustomForm(String sessionId, CustomForm customForm) {
		this.customFormDAS.updateCustomForm(customForm);
		return customForm;
	}

	public CustomForm delCustomFormById(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomForm delCustomFormByIds(String sessionId, String ids) {
		// 验证 sessionId 是否合法
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}

		_log.info("参数：" + ids);

		CustomForm __form = new CustomForm();
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

		boolean __delResult =  false;

		__delResult = this.customFormDAS.delCustomFormByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除 customForm 失败");
			__form.setResultMsg(__rm);
		}

		return __form;
	}

	public List<CustomForm> findCustomFormList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer begin,
			Integer limit) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		List<CustomForm> __list = this.customFormDAS.findCustomFormList(1, 1,
				filter, begin, limit);
		return __list;
	}

	public DojoListData findCustomFormDojoList(DojoListParam param) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		List<CustomForm> __list = this.customFormDAS.findCustomFormList(1, 1,
				param.getFilter(), param.getBegin(), param.getLimit());
		int __count = 0;
		__count = this.customFormDAS.getCustomFormListRowCount(param.getSessionId(),
				1, param.getFilter());
		_log.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~findCustomFormDojoList");
		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		__dld.setNumRows(__count);
		_log.info("*************************** rowNum :" + __dld.getNumRows());
		return __dld;
	}

	public Integer getCustomFormListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter) {
		int __count = this.customFormDAS.getCustomFormListRowCount(sessionId,
				resourceId, filter);
		return __count;
	}
	public MenuItem[] getFilteringSelectList(String sessionId)
	{
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}

		List<CustomForm> list =
			this.customFormDAS.findCustomFormList(1, 1, null, 0, 30);
		List<MenuItem> rtnList = new ArrayList<MenuItem>();
		for(CustomForm customform : list)
		{
			MenuItem menuItem = new MenuItem();
			menuItem.setId(customform.getCode().toString());
			menuItem.setName(customform.getName());
			rtnList.add(menuItem);
		}
		MenuItem[] rtnArray = new MenuItem[rtnList.size()];
		rtnList.toArray(rtnArray);
		return rtnArray;
	}

	/**
	 * @isSessionVaild
	 * @Description:验证session是否有效
	 * @return  boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-12-18
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
			_log.error("CustomForm:session验证失败");
			e.printStackTrace();
			sessionVaild = false;
		}
		return sessionVaild;
	}

}
