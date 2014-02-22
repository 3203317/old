package net.newcapec.sca.gridcust.das.impl;

/**
 * <p>Title: service实现 </p>
 * <p>Description:查询浏览列表用户自定义配置业务构件 实现</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-25
 * 修改日期：
 * 修改人：
 * 复审人：
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.gridcust.GridCustom;
import net.newcapec.sca.gridcust.das.GridCustomDAS;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class GridCustomDASImpl implements GridCustomDAS {

	private static final Logger _log = Logger
	.getLogger(GridCustomDASImpl.class);

	private static final Integer __data_3 = null;

	private SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	private SequenceService sequenceService;

	@Reference(name = "sequenceService", required = true)
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}

	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
			this.defDBConnService = defDBConnService;
	}

    //获得DAS
	public DAS getDAS()
	{
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),null);
		return das;
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
	public GridCustom getGridCustomById(Integer id) {
		// TODO Auto-generated method stub
		DAS das = this.getDAS();
		String __sql = " select  code ,user_code,form_code,custom_content from p_gridcustom t where t.code = ? ";
		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		ResultDescriptor __rdesc = null;
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_gridcustom");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("user_code");
		__rdesc.setTableName("p_gridcustom");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("form_code");
		__rdesc.setTableName("p_gridcustom");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("custom_content");
		__rdesc.setTableName("p_gridcustom");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_gridcustom");
		GridCustom  _gridCustom =  null;
		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			_gridCustom = new GridCustom();

			_gridCustom.setCode(__data_3.getInt("code"));
			_gridCustom.setUser_code(__data_3.getInt("user_code"));
			_gridCustom.setForm_code(__data_3.getInt("form_code"));
			_gridCustom.setCustom_content(__data_3.getString("custom_content"));

		}

		return   _gridCustom;
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
	public List<GridCustom> findGridCustomList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit) {

		DAS das = this.getDAS();
		String __sql = "select t.* from (select rownum row_num, code ,user_code,form_code,custom_content  from p_gridcustom  a  where  1=1 ";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a."
						+ __param_3.getField() + " " + __param_3.getLogical()
						+ " " + __param_3.getValue();
			}
		}

		__sql += " order by a.code desc ) t ";

		if (begin > -1 && limit > -1)
		    __sql += " where t.row_num between ? and ? ";

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("row_num");
		__rdesc.setTableName("p_gridcustom");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_gridcustom");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("user_code");
		__rdesc.setTableName("p_gridcustom");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("form_code");
		__rdesc.setTableName("p_gridcustom");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("custom_content");
		__rdesc.setTableName("p_gridcustom");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		Command __cmd = das.createCommand(__sql);
		if (begin > -1 && limit > -1){
			__cmd.setParameter(1, begin+1);
			__cmd.setParameter(2, begin + limit);
		}
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<GridCustom> __form = new ArrayList<GridCustom>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_gridcustom")) {
			GridCustom ___gridCustom = new GridCustom();
			___gridCustom.setCode(__data_3.getInt("code"));
			___gridCustom.setUser_code(__data_3.getInt("user_code"));
			___gridCustom.setForm_code(__data_3.getInt("form_code"));
			___gridCustom.setCustom_content(__data_3.getString("custom_content"));

			__form.add(___gridCustom);
		}

		return __form;
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
	public Boolean insertGridCustom(GridCustom gridCustom) {
		DAS das = this.getDAS();
		String __sql = " insert  into  p_gridcustom (code ,user_code,form_code,custom_content) values(?, ?, ?, ?) ";

		gridCustom.setCode(this.sequenceService.getNextValue("getMaxGridCustomID"));

		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, gridCustom.getCode());
		__cmd.setParameter(2, gridCustom.getUser_code());
		__cmd.setParameter(3, gridCustom.getForm_code());
		__cmd.setParameter(4, gridCustom.getCustom_content());

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception e) {
			__result = false;
		}

		return __result;
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
	public  Boolean  updateGridCustom(GridCustom gridCustom) {
		DAS das = this.getDAS();

		String __sql = " update  p_gridcustom set user_code = ?,form_code = ?,custom_content = ? where code = ? ";

		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, gridCustom.getUser_code());
		__cmd.setParameter(2, gridCustom.getForm_code());
		__cmd.setParameter(3, gridCustom.getCustom_content());
		__cmd.setParameter(4, gridCustom.getCode());

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			__result = false;
		}

		return __result;
	}

	public Boolean delGridCustomById(Integer id) {
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
	public Boolean delGridCustomByIds(Integer[] ids) {
		DAS das = this.getDAS();
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_gridcustom where code in (" + __appendSQL + ")";

		Command __cmd = das.createCommand(__sql);

		for (int __i_3 = 1, __j_3 = ids.length; __i_3 <= __j_3; __i_3++) {
			__cmd.setParameter(__i_3, ids[__i_3 - 1]);
		}

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			__result = false;
		} finally {

		}

		return __result;

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

		String __sql = " select count(1) count  from  p_gridcustom  a  where 1=1 ";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a."
						+ __param_3.getField() + " " + __param_3.getLogical()
						+ " " + __param_3.getValue();
			}
		}
		__sql += " order by a.code desc";

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("count");
		__rdesc.setTableName("p_gridcustom");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_gridcustom");

		int __count = 0;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			__count = __data_3.getInt("count");
		}

		return __count;

	};

}
