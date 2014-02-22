package net.newcapec.sca.customform.das.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.customform.CustomForm;
import net.newcapec.sca.customform.das.CustomFormDAS;
import net.newcapec.sca.dbconn.service.DefDBConnService;
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

/**
 *
 * @author huangxin
 *
 */
public class CustomFormDASImpl implements CustomFormDAS {

	private static final Logger _log = Logger
			.getLogger(CustomFormDASImpl.class);

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

	// 获得DAS
	public DAS getDAS() {
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(), null);
		return das;
	}

	public CustomForm getCustomFormById(Integer id) {
		String __sql = "select t.* from (select a.code,a.domain_code,a.unit_code,a.name,a.type,a.ds_code,to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.create_user_code,a.memo from p_form a) t where t.code=?";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("domain_code");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("unit_code");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_code");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("memo");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_form");

		CustomForm __form = null;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);

			__form = new CustomForm();

			__form.setCode(__data_3.getInt("code"));
			__form.setDomain_code(__data_3.getInt("domain_code"));
			__form.setUnit_code(__data_3.getInt("unit_code"));
			__form.setName(__data_3.getString("name"));
			__form.setType(Integer.parseInt(__data_3.getString("type")));

			__form.setDs_code(__data_3.getInt("ds_code"));
			__form.setCreate_date(__data_3.getString("create_date"));
			__form.setCreate_user_code(__data_3.getInt("create_user_code"));
			__form.setMemo(__data_3.getString("memo"));
		}

		return __form;
	}

	public List<CustomForm> findCustomFormList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit) {
		String __sql = "select t.* from (select rownum row_num, a.code,a.domain_code,a.unit_code,a.name,a.type,a.ds_code,to_char(a.create_date,'yyyy-mm-dd hh24:mi:ss') create_date,a.create_user_code,a.memo from p_form a where 1=1";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a."
						+ __param_3.getField() + " " + __param_3.getLogical()
						+ " " + __param_3.getValue();
			}
		}

		__sql += ") t where t.row_num between ? and ? ";

		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("row_num");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("domain_code");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("unit_code");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("type");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ds_code");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_code");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("memo");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, begin);
		__cmd.setParameter(2, begin + limit);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<CustomForm> __form = new ArrayList<CustomForm>();

		for (DataObject __data_3 : (List<DataObject>) __data.getList("p_form")) {
			CustomForm __form_4 = new CustomForm();
			__form_4.setCode(__data_3.getInt("code"));
			__form_4.setDomain_code(__data_3.getInt("domain_code"));
			__form_4.setUnit_code(__data_3.getInt("unit_code"));
			__form_4.setName(__data_3.getString("name"));
			__form_4.setType(Integer.parseInt(__data_3.getString("type")));

			__form_4.setDs_code(__data_3.getInt("ds_code"));
			__form_4.setCreate_date(__data_3.getString("create_date"));
			__form_4.setCreate_user_code(__data_3.getInt("create_user_code"));
			__form_4.setMemo(__data_3.getString("memo"));

			__form.add(__form_4);
		}
		return __form;
	}

	public Boolean insertCustomForm(CustomForm customForm) {
		String __sql = "insert into p_form (code, domain_code, unit_code, name, type, ds_code, create_date, create_user_code, memo) values "
				+ "(?, ?, ?, ?, ?, ?, to_date(?,'yyyy-mm-dd hh24:mi:ss') , ?, ?)";

		customForm.setCode(this.sequenceService
				.getNextValue("getMaxCustomFormID"));
		customForm.setCreate_date(_sdf.format(new Date()));
		customForm.setCreate_user_code(1);//zpf
		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, customForm.getCode());
		__cmd.setParameter(2, customForm.getDomain_code());
		__cmd.setParameter(3, customForm.getUnit_code());
		__cmd.setParameter(4, customForm.getName());
		__cmd.setParameter(5, customForm.getType());
		__cmd.setParameter(6, customForm.getDs_code());
		__cmd.setParameter(7, customForm.getCreate_date());
		__cmd.setParameter(8, customForm.getCreate_user_code());
		__cmd.setParameter(9, customForm.getMemo());

		_log.debug(__sql);

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			_log.error($ex);
			__result = false;
		} finally {
		}

		return __result;
	}

	public Boolean updateCustomForm(CustomForm customForm) {
		String __sql = "update p_form set domain_code = ?, unit_code = ?,  name = ?, type = ?, ds_code = ?, memo = ? where code = ?";
		customForm.setDomain_code(1);//zpf
		customForm.setUnit_code(1);//zpf
		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, customForm.getDomain_code());
		__cmd.setParameter(2, customForm.getUnit_code());
		__cmd.setParameter(3, customForm.getName());
		__cmd.setParameter(4, customForm.getType());
		__cmd.setParameter(5, customForm.getDs_code());

		__cmd.setParameter(6, customForm.getMemo());
		__cmd.setParameter(7, customForm.getCode());

		_log.debug(__sql);

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			_log.error($ex);
			__result = false;
		} finally {
		}

		return __result;
	}

	public Boolean delCustomFormById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean delCustomFormByIds(Integer[] ids) {
		if (ids == null || ids.length < 1)
			return false;
		Boolean  __delResult= false;
		__delResult = delFormItemByIds(ids);
		if (!__delResult)  return  false;


		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete p_form where code in (" + __appendSQL + ")";

		_log.info(__sql);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);

		for (int __i_3 = 1, __j_3 = ids.length; __i_3 <= __j_3; __i_3++) {
			__cmd.setParameter(__i_3, ids[__i_3 - 1]);
		}

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			_log.error($ex);
			__result = false;
		} finally {
		}

		return __result;
	}

	public Integer getCustomFormListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter) {
		String __sql = "select count(1) count from p_form where 1=1 ";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a."
						+ __param_3.getField() + " " + __param_3.getLogical()
						+ " " + __param_3.getValue();
			}
		}
		_log.debug(__sql);

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("count");
		__rdesc.setTableName("p_form");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		DAS das = this.getDAS();
		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("p_form");

		int __count = 0;

		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			__count = __data_3.getInt("count");
		}

		return __count;
	}

	/**
	 * @Title:delFormItemByIds
	 * @Description:根据ids删除p_form关联的其它表
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-12-18
	 * 修改日期： 修改人： 复审人：
	 */
	public Boolean delFormItemByIds(Integer[] ids) {

		if (ids == null || ids.length < 1)
			return false;
		Boolean __result = true;

		String __appendSQL = "";

		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);
		DAS das = this.getDAS();

		String[] itemTBNames = {"p_gridview","p_gridcondition","p_gridfield","p_gridstat","p_gridprint"};
		for(String itemTBName:itemTBNames){
			String __sql = "delete  "+itemTBName+"  where form_code in (" + __appendSQL + ")";
			_log.info(__sql);
			Command __cmd = das.createCommand(__sql);
			for (int __i_3 = 1, __j_3 = ids.length; __i_3 <= __j_3; __i_3++) {
				__cmd.setParameter(__i_3, ids[__i_3 - 1]);
			}
			try {
				__cmd.execute();
			} catch (Exception $ex) {
				_log.error($ex);
				__result = false;
			}
			if (!__result)  break;
		}

		return __result;
	}



}
