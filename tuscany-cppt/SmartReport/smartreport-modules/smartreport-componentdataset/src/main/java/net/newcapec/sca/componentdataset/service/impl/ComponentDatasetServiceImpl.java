package net.newcapec.sca.componentdataset.service.impl;
/**
 * <p>Title: Service实现类 </p>
 * <p>Description:控件绑定数据集业务构件实现类</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2013</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2013-01-08
 * 修改日期：
 * 修改人：
 * 复审人：
 */

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.oasisopen.sca.annotation.Reference;

import net.newcapec.sca.componentdataset.ComponentDataset;
import net.newcapec.sca.componentdataset.das.ComponentDatasetDAS;
import net.newcapec.sca.componentdataset.service.ComponentDatasetService;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;



public class ComponentDatasetServiceImpl implements ComponentDatasetService {
	private static final Logger _log = Logger
	.getLogger(ComponentDatasetServiceImpl.class);

	private  ComponentDatasetDAS  componentDatasetDAS;
	@Reference(name = "componentDatasetDAS", required = true)
	public void setComponentDatasetDAS(ComponentDatasetDAS  componentDatasetDAS) {
		this.componentDatasetDAS = componentDatasetDAS;
	}

	private SessionService sessionService;
	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}


	public ComponentDataset getComponentDatasetById(String sessionId, Integer id) {

		ComponentDataset  componentDataset =  componentDatasetDAS.getComponentDatasetById(id);
		return  componentDataset;

	}


	public List<ComponentDataset> findComponentDatasetList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer begin,
			Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}


	public DojoListData findComponentDatasetDojoList(DojoListParam param) {
		//根据sessionId获得session，判断session是否存在
//		Session session = sessionService.getSession(param.getSessionId());
//		if(!this.isSessionVaild(session))
//		{
//			_log.error("DicCode:session不可用");
//			return null;
//		}
		DojoListData dld = new DojoListData();
		try{
			List<ComponentDataset> list = componentDatasetDAS.findComponentDatasetList(1,1,param.getFilter(), param.getBegin(), param.getLimit());
			if (list != null){
				System.out.println("size:"+list.size());
			}else{
				System.out.println("list为空");
			}

			int count = 0;
			count	= componentDatasetDAS.getComponentDatasetListRowCount(null, param.getFilter());
			System.out.println("count:"+count);
			//结果封装成DojoListData
			if ( list!=null ){
				dld.setIdentifier("code");
				dld.setItems(list.toArray());
				dld.setLabel("dataset_name");
				dld.setNumRows(count);
			}
			return dld;
		} catch(Exception e){
			_log.info("ComponentDataset:findComponentDatasetDojoList");
			_log.error("获得数据报错");
			return dld;
		}

	}


	public Integer getComponentDatasetListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter) {
		// TODO Auto-generated method stub
		return null;
	}


	public ComponentDataset insertComponentDataset(String sessionId,
			ComponentDataset componentDataset) {
		//根据sessionId获得session，判断session是否存在
//		Session session = sessionService.getSession(sessionId);
//		if(!this.isSessionVaild(session))
//		{
//			_log.error("DicCode:session不可用");
//			return null;
//		}
		//判断name是否为空，为空不执行das层插入方法
		if (componentDataset.getDataset_name() == null){
			_log.info("ComponentDataset:insertComponentDataset");
			_log.error("name为空，添加控件绑定数据集失败");
            return null;
		}
		componentDataset.setDomain_code(1);
		componentDataset.setUnit_code(1);
		Boolean  updateResult = componentDatasetDAS.insertComponentDataset(componentDataset);
		//封装返回的结果，添加插入失败日志
		ResultMsg  rm = new ResultMsg();
		if (!updateResult) {
			rm.setStatus(0);
			rm.setErrorId(205);
			rm.setErrorMsg("添加控件绑定数据集失败");
			_log.info("ComponentDataset:insertComponentDataset");
			_log.error(rm.getErrorMsg());
		}else{
			rm.setStatus(1);
		}

		componentDataset.setResultMsg(rm);
		return componentDataset;

	}


	public ComponentDataset updateComponentDataset(String sessionId,
			ComponentDataset componentDataset) {
		//根据sessionId获得session，判断session是否存在
//		Session session = sessionService.getSession(sessionId);
//		if(!this.isSessionVaild(session))
//		{
//			_log.error("DicCode:session不可用");
//			return null;
//		}
		Boolean  updateResult = componentDatasetDAS.updateComponentDataset(componentDataset);
		ResultMsg  rm = new ResultMsg();
	    //封装返回的结果，添加更新失败日志
		if (!updateResult) {
			rm.setErrorId(205);
			rm.setStatus(0);
			rm.setErrorMsg("修改控件绑定数据集失败");
			_log.info("ComponentDataset:updateComponentDataset");
			_log.error(rm.getErrorMsg());
		}else{
			rm.setStatus(1);
		}
		componentDataset.setResultMsg(rm);
		return componentDataset;
	}

	public ComponentDataset delComponentDatasetByIds(String sessionId, String ids) {
		//根据sessionId获得session，判断session是否存在
//		Session session = sessionService.getSession(sessionId);
//		if(!this.isSessionVaild(session))
//		{
//			_log.error("DicCode:session不可用");
//			return null;
//		}
		ComponentDataset  componentDataset = new  ComponentDataset();
		ResultMsg  rm = new  ResultMsg();
        //判断ids是否为空，为空直接返回结果不执行das层方法
		if (ids==null || "".equals(ids)) {
			rm.setStatus(0);

			componentDataset.setResultMsg(rm);
			_log.info("DicCode:delDicCodeByItemIds");
			_log.info("ids参数为空");
			return componentDataset;
		}
        //字符串转化字符数组，字符数组转化为整数数组
		String[]  idsArr = ids.split(",");
		Integer[]  idsInArr = new Integer[idsArr.length];
		for (int  i = 0, j = idsArr.length; i < j; i++) {
			idsInArr[i] = Integer.parseInt(idsArr[i]);
		}
		boolean  delResult =  componentDatasetDAS.delComponentDatasetByIds(idsInArr);
        //封装返回的结果，添加删除失败日志
		if (! delResult) {
			rm.setErrorId(203);
			rm.setStatus(0);
			rm.setErrorMsg("删除控件绑定数据集失败");
			_log.info("ComponentDataset:delComponentDatasetByIds");
			_log.error("删除控件绑定数据集失败");
		}else{
			rm.setStatus(1);
		}
		componentDataset.setResultMsg(rm);
		return componentDataset;

	}


	public DojoListData obtainComponentDatasource(String sessionId) {
		//根据sessionId获得session，判断session是否存在
//		Session session = sessionService.getSession(sessionId);
//		if(!this.isSessionVaild(session))
//		{
//			_log.error("DicCode:session不可用");
//			return null;
//		}
		DojoListData dld = new DojoListData();
		List<JSONObject> listResult = componentDatasetDAS.obtainComponentDatasource();
		//结果封装成DojoListData
		if (listResult !=null ){
			dld.setIdentifier("code");
			dld.setItems(listResult.toArray());
			dld.setLabel("name");
		}
		return dld;
	}


	public  DojoListData obtainFieldByDatasource(String sessionId,
			String dsCode) {
		//根据sessionId获得session，判断session是否存在
//		Session session = sessionService.getSession(sessionId);
//		if(!this.isSessionVaild(session))
//		{
//			_log.error("DicCode:session不可用");
//			return null;
//		}
		DojoListData dld = new DojoListData();
		List<JSONObject> listResult = null;
		listResult  = componentDatasetDAS.obtainFieldByDatasource(dsCode);
		if (listResult !=null ){
			dld.setIdentifier("code");
			dld.setItems(listResult.toArray());
			dld.setLabel("name");
		}
		return  dld;
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
			_log.error("DicCode:session验证失败");
			e.printStackTrace();
			sessionVaild = false;
		}
		return sessionVaild;
	}

}
