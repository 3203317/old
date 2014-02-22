package net.newcapec.sca.diccode.service.impl;

/**
 * <p>Title: service实现 </p>
 * <p>Description:自定义字典代码业务构件实现</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-31
 * 修改日期：
 * 修改人：
 * 复审人：
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.oasisopen.sca.annotation.Reference;

import net.newcapec.sca.diccode.DicItem;
import net.newcapec.sca.diccode.DicTable;
import net.newcapec.sca.diccode.das.DicCodeDAS;
import net.newcapec.sca.diccode.service.DicCodeService;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

public class DicCodeServiceImpl implements DicCodeService {

	private static final Logger _log = Logger
	.getLogger(DicCodeServiceImpl.class);
	private SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //日期格式化
	private  DicCodeDAS  dicCodeDAS;

	//注入DAS
	@Reference(name = "dicCodeDAS", required = true)
	public void setDicCodeDAS(DicCodeDAS dicCodeDAS) {
		this.dicCodeDAS = dicCodeDAS;
	}
    //注入session
	private SessionService sessionService;
	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	/**
	 * @Title:getDicTableByTableId
	 * @Description:根据tableid获得DicTable
	 * @return  DicTable
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public DicTable getDicTableByTableId(String sessionId, Integer tableId) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		//调用das层方法
		DicTable  __dicTable =  dicCodeDAS.getDicTableByTableId(tableId);
		return __dicTable;
	}

	/**
	 * @Title:getDicItemByItemId
	 * @Description:根据itemid获得DicItem
	 * @return  DicItem
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public DicItem getDicItemByItemId(String sessionId, Integer itemId) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		//调用das层方法
		DicItem  dicItem = dicCodeDAS.getDicItemByItemId(itemId) ;
		return dicItem;
	}

	/**
	 * @findDicItemListByTableId
	 * @Description:根据tableId获得DicItem列表
	 * @return  List<DicItem>
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public List<DicItem> findDicItemListByTableId(Integer tableId) {

		List<DicItem> __list =  dicCodeDAS.findDicItemListByTableId(tableId);
		return __list;

	}

	/**
	 * @findDicTableList
	 * @Description:根据条件获得DicTable列表
	 * @return  List<DicTable>
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public List<DicTable> findDicTableList(String sessionId,
			Integer resourceId,List<FilterParam> filter, Integer begin, Integer limit) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		//调用das层方法
		List<DicTable>  __list =  dicCodeDAS.findDicTableList(Integer.valueOf(session.getDomain_code()),
				Integer.valueOf(session.getUnit_code()), filter, begin, limit) ;
		return  __list;

	}


	/**
	 * @insertDicTable
	 * @Description:添加DicTable
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public DicTable insertDicTable(String sessionId, DicTable dicTable) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		//判断name是否为空，为空不执行das层插入方法
		if (dicTable.getName() == null){
			_log.info("DicCode:insertDicTable");
			_log.error("name为空，添加字典代码失败");
            return null;

		}
		dicTable.setName(dicTable.getName().toUpperCase());//转化为大写
		dicTable.setCreate_user_account_id(Integer.parseInt(session.getUser_code()));//设置登录用户account_id
		dicTable.setCreate_date(sdf.format(new Date()));//设置创建日期
		dicTable.setSortid(0);
		dicTable.setVer(0);

		Boolean __b = dicCodeDAS.insertDicTable(dicTable);
		//封装返回的结果，添加插入失败日志
		ResultMsg __rm = new ResultMsg();
		if (!__b) {
			__rm.setStatus(0);
			__rm.setErrorId(205);
			__rm.setErrorMsg("添加字典代码失败");
			_log.info("DicCode:insertDicTable");
			_log.error(__rm.getErrorMsg());
		}else{
			__rm.setStatus(1);
		}
		dicTable.setResultMsg(__rm);
		return dicTable;

	}

	 /**
	 * @insertDicItem
	 * @Description:添加DicItem
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
     */
	public DicItem insertDicItem(String sessionId, DicItem dicItem) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		dicItem.setCode_dictionary_name(dicItem.getCode_dictionary_name().toUpperCase());//转化为大写
		dicItem.setCreate_user_account_id(Integer.parseInt(session.getUser_code()));//设置登录用户account_id
		dicItem.setCreate_date(sdf.format(new Date()));//设置创建日期
		dicItem.setSortid(0);
		dicItem.setVer(0);
		Boolean __b = dicCodeDAS.insertDicItem(dicItem);
		//封装返回的结果，添加插入失败日志
		ResultMsg __rm = new ResultMsg();
		if (!__b) {
			__rm.setErrorId(205);
			__rm.setStatus(0);
			__rm.setErrorMsg("添加字典代码明细失败");
			_log.info("DicCode:insertDicItem");
			_log.error(__rm.getErrorMsg());
		}else{
			__rm.setStatus(1);
		}
		dicItem.setResultMsg(__rm);
		return dicItem;

	}

	/**
	 * @updateDicTable
	 * @Description:修改DicTable
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public DicTable updateDicTable(String sessionId, DicTable dicTable) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		dicTable.setName(dicTable.getName().toUpperCase());//转化为大写
		Boolean __b = dicCodeDAS.updateDicTable(dicTable);
		ResultMsg __rm = new ResultMsg();
	    //封装返回的结果，添加更新失败日志
		if (!__b) {
			__rm.setErrorId(205);
			__rm.setStatus(0);
			__rm.setErrorMsg("修改字典代码信息失败");
			_log.info("DicCode:updateDicTable");
			_log.error(__rm.getErrorMsg());
		}else{
			__rm.setStatus(1);
		}
		dicTable.setResultMsg(__rm);
		return dicTable;
	}


	/**
	 * @insertDicItem
	 * @Description:添加DicItem
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
     */
	public DicItem updateDicItem(String sessionId, DicItem dicItem) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		dicItem.setCode_dictionary_name(dicItem.getCode_dictionary_name().toUpperCase());//转化为大写
		Boolean __b = dicCodeDAS.updateDicItem(dicItem);
		ResultMsg __rm = new ResultMsg();
		//封装返回的结果，添加更新失败日志
		if (!__b) {
			__rm.setErrorId(205);
			__rm.setStatus(0);
			__rm.setErrorMsg("修改字典代码属性信息失败");
			_log.info("DicCode:updateDicItem");
			_log.error(__rm.getErrorMsg());
		}else{
			__rm.setStatus(1);
		}
		dicItem.setResultMsg(__rm);
		return  dicItem;
	}

	public DicTable delDicCodeByTableId(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public DicItem delDicCodeByItemId(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @delDicCodeByTableIds
	 * @Description:根据ids删除DicTable
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public DicTable delDicCodeByTableIds(String sessionId, String ids) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}


		DicTable __form = new DicTable();
		ResultMsg __rm = new ResultMsg();//封装删除成功失败的结果
	    //判断ids是否为空，为空直接返回结果不执行das层方法
		if ("".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setStatus(0);
			__rm.setErrorMsg("参数 ids 不能为空");
			__form.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
			return __form;
		}

		//字符串转化字符数组
		String[] __idsArr = ids.split(",");
		//字符数组转化为整数数组
		Integer[] __ids = new Integer[__idsArr.length];
		for (int __i_3 = 0, __j_3 = __idsArr.length; __i_3 < __j_3; __i_3++) {
			__ids[__i_3] = Integer.parseInt(__idsArr[__i_3]);
		}

		//__ids中每个id对应的dictable都拥有字典明细时不执行删除
		Integer  count  = dicCodeDAS.judgedelDicCode(__ids);
		if  (count == __ids.length)  {
			__rm.setStatus(-1);
		}else{

			boolean __delResult =  dicCodeDAS.delDicCodeByTableIds(__ids);
			 //封装返回的结果，添加删除失败日志
			if (!__delResult) {
				__rm.setErrorId(203);
				__rm.setStatus(0);
				__rm.setErrorMsg("删除字典代码失败");
				_log.info("DicCode:delDicCodeByTableIds");
				_log.error("删除字典代码失败");

			}else{
				__rm.setStatus(1);
			}
		}
		__form.setResultMsg(__rm);

		return __form;
	}

	/**
	 * @delDicCodeByItemIds
	 * @Description:根据ids删除DicItem
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public DicItem delDicCodeByItemIds(String sessionId, String ids) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		_log.info("参数：" + ids);

		DicItem __form = new  DicItem();
		ResultMsg __rm = new  ResultMsg();
        //判断ids是否为空，为空直接返回结果不执行das层方法
		if (ids==null || "".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setStatus(0);
			__rm.setErrorMsg("参数 ids 不能为空");
			__form.setResultMsg(__rm);
			_log.info("DicCode:delDicCodeByItemIds");
			_log.info(__rm.getErrorMsg());
			return __form;
		}
        //字符串转化字符数组
		String[] __idsArr = ids.split(",");
		//字符数组转化为整数数组
		Integer[] __ids = new Integer[__idsArr.length];
		for (int __i_3 = 0, __j_3 = __idsArr.length; __i_3 < __j_3; __i_3++) {
			__ids[__i_3] = Integer.parseInt(__idsArr[__i_3]);
		}
        //执行das层方法
		boolean __delResult =  dicCodeDAS.delDicCodeByItemIds(__ids);
        //封装返回的结果，添加删除失败日志
		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setStatus(0);
			__rm.setErrorMsg("删除字典代码明细失败");
			_log.info("DicCode:delDicCodeByItemIds");
			_log.error("删除字典代码明细失败");
		}else{
			__rm.setStatus(1);
		}
		__form.setResultMsg(__rm);
		return __form;
	}

	/**
	 * @getDicTableListRowCount
	 * @Description:根据条件获得DicTable的数量
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public Integer getDicTableListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @findDicTableDojoList
	 * @Description:根据条件获得list并转化为DojoListData
	 * @return  DojoListData
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public DojoListData findDicTableDojoList(DojoListParam param) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}

		DojoListData __dld = new DojoListData();
		try{
			//调用das层方法
			List<DicTable> __list = dicCodeDAS.findDicTableList(Integer.valueOf(session.getDomain_code()),
					Integer.valueOf(session.getUnit_code()),param.getFilter(), param.getBegin(), param.getLimit());
			int __count = 0;
			__count	= dicCodeDAS.getDicTableListRowCount(null,
					null, param.getFilter());
			//结果封装成DojoListData
			if ( __list!=null ){
				__dld.setIdentifier("code");
				__dld.setItems(__list.toArray());
				__dld.setLabel("name");
				__dld.setNumRows(__count);
			}
			return __dld;
		} catch(Exception e){
			_log.info("DicCode:findDicTableDojoList");
			_log.error("获得数据报错");
			return __dld;

		}

	}

	/**
	 * @findDicTableDojoList
	 * @Description:根据条件获得list并转化为DojoListData
	 * @return  DojoListData
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-15
	 * 修改日期： 修改人： 复审人：
	 */
	public DojoListData findDicItemDojoListByTableId(DojoListParam param) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		DojoListData __dld = new DojoListData();
		try{
			//调用das层方法
			List<DicItem> __list = dicCodeDAS.findDicItemListByTableId(1);
			int __count = 9;
			//结果封装成DojoListData
			if ( __list !=null ){
				__dld.setIdentifier("code");
				__dld.setItems(__list.toArray());
				__dld.setLabel("name");
				__dld.setNumRows(__count);
			}
			return __dld;
		} catch(Exception e){
			_log.info("DicCode:findDicItemDojoListByTableId");
			_log.error("获得数据报错");
			return __dld;
		}
	}


	/**
	 * @findDicItemListByCondition
	 * @Description:根据条件获得List<DicItem>
	 * @return  DojoListData
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-15
	 * 修改日期： 修改人： 复审人：
	 */
	public List<DicItem> findDicItemListByCondition(String sessionId,List<FilterParam> filter) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		//调用das层方法
		List<DicItem> __list = null;
		try{
			 __list = dicCodeDAS.findDicItemListByCondition(filter);
			 return  __list;
		} catch(Exception e){
			_log.info("DicCode:findDicItemListByCondition");
			_log.error("获得数据失败");
			return  __list;
		}

	}

	/**
	 * @findDicItemListByCondition
	 * @Description:根据条件获得list并转化为DojoListData
	 * @return  DojoListData
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-21
	 * 修改日期： 修改人： 复审人：
	 */
	public  DojoListData  findDicItemDojoListByCondition(DojoListParam param) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}
		DojoListData __dld = new DojoListData();

		try{
			//调用das层方法
			 int __count = 0;
		     List<DicItem> __list = dicCodeDAS.findDicItemListByCondition(param.getFilter());

		     __count = dicCodeDAS.getDicItemListByConditionRowCount(null,null,param.getFilter());
		     //结果封装成DojoListData
		     if (__list != null){
				 __dld.setIdentifier("code");
				 __dld.setItems(__list.toArray());
				 __dld.setLabel("name");
				 __dld.setNumRows(__count);
			 }
			 return __dld;

		} catch(Exception e){
			 _log.info("DicCode:findDicItemDojoListByCondition");
			 _log.error("获得数据报错");
			 return __dld;
		}


	}

	/**
	 * @getDicTableDojoList
	 * @Description:根据条件获得DicTable list并转化为DojoListData
	 * @return  DojoListData
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-21
	 * 修改日期： 修改人： 复审人：
	 */

	public DojoListData getDicTableDojoList(DojoListParam param) {
		//根据sessionId获得session，判断session是否存在
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			_log.error("DicCode:session不可用");
			return null;
		}

		DojoListData  dld = new DojoListData();
		try {
			//调用das层方法
			  List<JSONObject> queryResult = dicCodeDAS.getDicTableList();

			  if (queryResult != null){
				  //结果封装成DojoListData
				  dld.setIdentifier("name");
				  dld.setItems(queryResult.toArray());
				  dld.setLabel("name");
			  }
			  return  dld;

		} catch (Exception e) {
			_log.info("DicCode:getDicTableDojoList");
			_log.error("获得数据报错");
			return  dld;
		}


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
