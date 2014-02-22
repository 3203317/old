package net.newcapec.sca.formlayout.service.impl;

/**
 * <p>Title: service实现 </p>
 * <p>Description:查询浏览列表表单布局定制数据访问构件实现</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-29
 * 修改日期：
 * 修改人：
 * 复审人：
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.formlayout.FormLayout;
import net.newcapec.sca.formlayout.das.FormLayoutDAS;
import net.newcapec.sca.formlayout.service.FormLayoutService;

import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

public class FormLayoutServiceImpl implements FormLayoutService {

	private static final Logger _log = Logger
	.getLogger(FormLayoutServiceImpl.class);

	private  FormLayoutDAS  formLayoutDAS;

	@Reference(name = "formLayoutDAS", required = true)
	public void setFormLayoutDAS(FormLayoutDAS formLayoutDAS) {
		this.formLayoutDAS = formLayoutDAS;
	}

	private SessionService sessionService;
	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	/**
	 * @Title:getFormLayoutById
	 * @Description:根据id获得FormLayout
	 * @return  DicTable
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-29
	 * 修改日期： 修改人： 复审人：
	 */
	public FormLayout getFormLayoutById(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		FormLayout  _formLayout ;
		_formLayout = formLayoutDAS.getFormLayoutById(id);
		return  _formLayout;
	}

	/**
	 * @findFormLayoutList
	 * @Description:根据条件获得FormLayout列表
	 * @return  List<FormLayout>
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-29
	 * 修改日期： 修改人： 复审人：
	 */
	public List<FormLayout> findFormLayoutList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit) {
		// TODO Auto-generated method stub
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<FormLayout> _list ;
		_list = formLayoutDAS.findFormLayoutList(null, null, filter, begin, limit);
		return _list;
	}

	/**
	 * @insertFormLayout
	 * @Description:添加FormLayout
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-29
	 * 修改日期： 修改人： 复审人：
	 */
	public FormLayout insertFormLayout(String sessionId, FormLayout formLayout) {
		// TODO Auto-generated method stub
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Boolean __b = formLayoutDAS.insertFormLayout(formLayout);
		ResultMsg __rm = new ResultMsg();
		if (!__b) {
			__rm.setStatus(0);
			__rm.setErrorId(205);
			__rm.setErrorMsg("添加表单布局信息失败");
			_log.info(__rm.getErrorMsg());
		}else{
			__rm.setStatus(1);

		}
		formLayout.setResultMsg(__rm);
		return formLayout;
	}


	/**
	 * @updateFormLayout
	 * @Description:修改FormLayout
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-29
	 * 修改日期： 修改人： 复审人：
	 */
	public FormLayout updateFormLayout(String sessionId, FormLayout formLayout) {
		// TODO Auto-generated method stub
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Boolean __b = formLayoutDAS.updateFormLayout(formLayout);
		ResultMsg __rm = new ResultMsg();
		if (!__b) {
			__rm.setErrorId(205);
			__rm.setStatus(0);
			__rm.setErrorMsg("修改表单布局信息失败");
			_log.info(__rm.getErrorMsg());
		}else{
			__rm.setStatus(1);

		}
		formLayout.setResultMsg(__rm);
		return formLayout;
	}

	public FormLayout delFormLayoutById(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @delFormLayoutByIds
	 * @Description:根据ids删除FormLayout
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-29
	 * 修改日期： 修改人： 复审人：
	 */
	public FormLayout delFormLayoutByIds(String sessionId, String ids) {
		// TODO Auto-generated method stub
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}

		FormLayout __form = new FormLayout();
		ResultMsg __rm = new ResultMsg();

		if ("".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setStatus(0);
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

		boolean __delResult =  formLayoutDAS.delFormLayoutByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setStatus(0);
			__rm.setErrorMsg("删除表单布局信息失败");

		}else{
			__rm.setStatus(1);
		}
		__form.setResultMsg(__rm);

		return __form;
	}


	/**
	 * @findFormLayoutDojoList
	 * @Description:根据条件获得FormLayout的列表并转化为DojoListData
	 * @return  DojoListData
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-29
	 * 修改日期： 修改人： 复审人：
	 */
	public  DojoListData findFormLayoutDojoList(DojoListParam param){
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<FormLayout> __list = formLayoutDAS.findFormLayoutList(null, null,
				param.getFilter(), param.getBegin(), param.getLimit());

		int __count = formLayoutDAS.getFormLayoutListRowCount(null,
				null, param.getFilter());
		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		__dld.setNumRows(__count);

		return __dld;
	};

	/**
	 * @getFormLayoutListRowCount
	 * @Description:根据条件获得FormLayout的数量
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-29
	 * 修改日期： 修改人： 复审人：
	 */
	public  Integer  getFormLayoutListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter){
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Integer _count =  formLayoutDAS.getFormLayoutListRowCount(sessionId, resourceId, filter);
		return  _count;
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
