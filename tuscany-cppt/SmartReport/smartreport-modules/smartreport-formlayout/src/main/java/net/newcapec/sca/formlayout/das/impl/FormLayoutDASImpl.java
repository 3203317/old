package net.newcapec.sca.formlayout.das.impl;
/**
 * <p>Title:DAS实现 </p>
 * <p>Description:查询浏览列表表单布局定制业务构件实现</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-29
 * 修改日期：
 * 修改人：
 * 复审人：
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.formlayout.FormLayout;
import net.newcapec.sca.formlayout.das.FormLayoutDAS;
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

public class FormLayoutDASImpl implements FormLayoutDAS {

	private static final Logger _log = Logger
	.getLogger(FormLayoutDASImpl.class);

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
	 * @Title:getFormLayoutById
	 * @Description:根据id获得FormLayout
	 * @return  FormLayout
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-29
	 * 修改日期： 修改人： 复审人：
	 */
	public FormLayout getFormLayoutById(Integer id) {
		// TODO Auto-generated method stub
		DAS das = this.getDAS();
		String __sql = " select code, form_code, custom_type, custom_content, temp_content, print_temp_code from p_formlayout t where t.code = ? ";
		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		ResultDescriptor __rdesc = null;
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("form_code");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("custom_type");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("custom_content");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("temp_content");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("print_temp_code");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_formlayout");
		FormLayout  _formLayout =  null;
		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			_formLayout = new FormLayout();

			_formLayout.setCode(__data_3.getInt("code"));
			_formLayout.setForm_code(__data_3.getInt("form_code"));
			_formLayout.setCustom_type(__data_3.getInt("custom_type"));
			_formLayout.setCustom_content(__data_3.getString("custom_content"));
			_formLayout.setTemp_content(__data_3.getString("temp_content"));
			_formLayout.setPrint_temp_code(__data_3.getInt("print_temp_code"));

		}

		return _formLayout;
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
	public List<FormLayout> findFormLayoutList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit) {
		// TODO Auto-generated method stub

		DAS das = this.getDAS();
		String __sql = "select t.* from (select rownum row_num,code,form_code,custom_type,custom_content,temp_content,print_temp_code from p_formlayout  a  where  1=1 ";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a."
						+ __param_3.getField() + " " + __param_3.getLogical()
						+ " " + __param_3.getValue();
			}
		}

		__sql += "  order by a.code desc ) t ";


		if (begin > -1 && limit > -1)
		   __sql += " where t.row_num between ? and ? ";

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("row_num");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("form_code");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("custom_type");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("custom_content");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("temp_content");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("print_temp_code");
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		Command __cmd = das.createCommand(__sql);

		if (begin > -1 && limit > -1){
			__cmd.setParameter(1, begin);
			__cmd.setParameter(2, begin + limit);
		}
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<FormLayout> __form = new ArrayList<FormLayout>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_formlayout")) {
			FormLayout ___formLayout = new FormLayout();
			___formLayout.setCode(__data_3.getInt("code"));
			___formLayout.setForm_code(__data_3.getInt("form_code"));
			___formLayout.setCustom_type(__data_3.getInt("custom_type"));
			___formLayout.setCustom_content(__data_3.getString("custom_content"));
			___formLayout.setTemp_content(__data_3.getString("temp_content"));
			___formLayout.setPrint_temp_code(__data_3.getInt("print_temp_code"));

			__form.add(___formLayout);
		}
		return __form;
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
	public Boolean insertFormLayout(FormLayout formLayout) {
		// TODO Auto-generated method stub

		DAS das = this.getDAS();
		String __sql = " insert into p_formlayout (code,form_code,custom_type,custom_content,temp_content,print_temp_code) values(?, ?, ?, ?,?,?) ";

		formLayout.setCode(this.sequenceService.getNextValue("getMaxFormLayoutID"));

		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, formLayout.getCode());
		__cmd.setParameter(2, formLayout.getForm_code());
		__cmd.setParameter(3, formLayout.getCustom_type());
		__cmd.setParameter(4, formLayout.getCustom_content());
		__cmd.setParameter(5, formLayout.getTemp_content());
		__cmd.setParameter(6, formLayout.getPrint_temp_code());

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception e) {
			__result = false;
		}

		return __result;
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
	public Boolean updateFormLayout(FormLayout formLayout) {
		// TODO Auto-generated method stub

		DAS das = this.getDAS();
		String __sql = " update  p_formlayout  set form_code = ?,custom_type = ?,custom_content = ?,temp_content = ?,print_temp_code = ?  where code = ? ";

		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, formLayout.getForm_code());
		__cmd.setParameter(2, formLayout.getCustom_type());
		__cmd.setParameter(3, formLayout.getCustom_content());
		__cmd.setParameter(4, formLayout.getTemp_content());
		__cmd.setParameter(5, formLayout.getPrint_temp_code());
		__cmd.setParameter(6, formLayout.getCode());

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			__result = false;
		}

		return __result;
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
	public Boolean delFormLayoutByIds(Integer[] ids) {
		// TODO Auto-generated method stub

		DAS das = this.getDAS();
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_formlayout where code in (" + __appendSQL + ")";

		Command __cmd = das.createCommand(__sql);

		for (int __i_3 = 1, __j_3 = ids.length; __i_3 <= __j_3; __i_3++) {
			__cmd.setParameter(__i_3, ids[__i_3 - 1]);
		}

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			__result = false;
		}

		return __result;

	}

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

		DAS das = this.getDAS();
		String __sql = " select count(1) count  from  p_formlayout  a  where 1=1 ";

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
		__rdesc.setTableName("p_formlayout");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_formlayout");

		int __count = 0;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			__count = __data_3.getInt("count");
		}

		return __count;

	}

}
