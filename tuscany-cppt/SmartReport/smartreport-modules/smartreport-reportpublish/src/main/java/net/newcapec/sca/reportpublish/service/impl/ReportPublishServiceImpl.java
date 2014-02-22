package net.newcapec.sca.reportpublish.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.reportpublish.ReportPublish;
import net.newcapec.sca.reportpublish.das.ReportPublishDAS;
import net.newcapec.sca.reportpublish.service.ReportPublishService;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

/**
 * <p>Title: Service实现类 </p>
 * <p>Description:报表发布业务构件实现类</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2013</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2013-01-15
 * 修改日期：
 * 修改人：
 * 复审人：
 */

public class ReportPublishServiceImpl  implements  ReportPublishService {

	private static final Logger _log = Logger
	.getLogger(ReportPublishServiceImpl.class);

	private   ReportPublishDAS   reportPublishDAS;
	@Reference(name = "reportPublishDAS", required = true)
	public void setReportPublishDAS(ReportPublishDAS  reportPublishDAS) {
		this.reportPublishDAS = reportPublishDAS;
	}

	private SessionService sessionService;
	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public DojoListData findUnpublishReportDojoList(DojoListParam param) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}

		DojoListData dld = new DojoListData();
		try{

			List<ReportPublish> list = reportPublishDAS.findUnpublishReportList(1,1,param.getFilter(), param.getBegin(), param.getLimit());

			int count = 0;
			count	= reportPublishDAS.getUnpublishReportListRowCount(null, param.getFilter());
			//结果封装成DojoListData
			if ( list!=null ){
				dld.setIdentifier("code");
				dld.setItems(list.toArray());
				dld.setLabel("name");
				dld.setNumRows(count);
			}
			return dld;
		} catch(Exception e){
			_log.info("ReportPublish:findUnpublishReportDojoList");
			_log.error("获得数据报错");
			return dld;
		}
	}

	public ReportPublish publishReport(String sessionId,ReportPublish reportPublish) {
//		System.out.println("menuname:"+reportPublish.getMenu_name());
//		System.out.println("memo:"+reportPublish.getMemo());
//		System.out.println("grant_permission:"+reportPublish.getGrant_permission());
//		System.out.println("code:"+reportPublish.getCode());
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("ReportPublish:session不可用");
			return null;
		}
		System.out.println( "usercode:"+session.getUser_code());
		reportPublish.setUser_account_id(Integer.parseInt(session.getUser_code()));

		ResultMsg  rm = new ResultMsg();
		Boolean success = false;
		success = reportPublishDAS.publishReport(reportPublish);
		if (!success) {
			rm.setStatus(0);
		}else{
			rm.setStatus(1);
		}
		reportPublish.setResultMsg(rm);
		return reportPublish;
	}

	public ReportPublish revokePublishReport(String sessionId,ReportPublish reportPublish) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("ReportPublish:session不可用");
			return null;
		}

		Boolean success = false;
		success = reportPublishDAS.revokePublishReport(reportPublish);
		ResultMsg  rm = new ResultMsg();
		if (!success) {
			rm.setStatus(0);
		}else{
			rm.setStatus(1);
		}
		reportPublish.setResultMsg(rm);
		return reportPublish;
	}

	/**
	 * @isSessionVaild
	 * @Description:验证session是否有效
	 * @return  boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-22
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
			_log.error("ReportPublish:session验证失败");
			e.printStackTrace();
			sessionVaild = false;
		}
		return sessionVaild;
	}


}
