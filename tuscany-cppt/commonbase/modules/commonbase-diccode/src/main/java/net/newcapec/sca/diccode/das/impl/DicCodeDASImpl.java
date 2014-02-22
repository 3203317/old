package net.newcapec.sca.diccode.das.impl;

/**
 * <p>Title: service实现 </p>
 * <p>Description:自定义字典代码数据访问构件实现</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-31
 * 修改日期：
 * 修改人：
 * 复审人：
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.json.JSONObject;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.diccode.DicItem;
import net.newcapec.sca.diccode.DicTable;
import net.newcapec.sca.diccode.das.DicCodeDAS;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.util.DASFactory;

public class DicCodeDASImpl implements DicCodeDAS {

	private static final Logger _log = Logger
	.getLogger(DicCodeDASImpl.class);

	private SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日期格式化

	private SequenceService sequenceService;
    //注入sequenceService
	@Reference(name = "sequenceService", required = true)
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}

	private DefDBConnService defDBConnService;

	//注入defDBConnService
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
	 * @Title:getDicTableByTableId
	 * @Description:根据tableid获得DicTable
	 * @return  DicTable
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public DicTable getDicTableByTableId(Integer id) {
		DAS das = this.getDAS();
		String __sql = " select code, name, name_alias, info, create_user_account_id, create_date, "+
		               " sortid, ver from z_code_dictionary t where t.code = ? ";
		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		//所有列转化为字符类型
		ResultDescriptor __rdesc = null;
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("z_code_dictionary");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("z_code_dictionary");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name_alias");
		__rdesc.setTableName("z_code_dictionary");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("info");
		__rdesc.setTableName("z_code_dictionary");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_account_id");
		__rdesc.setTableName("z_code_dictionary");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("z_code_dictionary");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("sortid");
		__rdesc.setTableName("z_code_dictionary");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ver");
		__rdesc.setTableName("z_code_dictionary");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);
        //创建command，设置参数和ResultDescriptor
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, id);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
        //获得查询结果，封装成DicTable
		List<DataObject> __dataList = __data.getList("z_code_dictionary");
		DicTable  _dicTable =  null;
		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			_dicTable = new DicTable();
			_dicTable.setCode(__data_3.getInt("code"));
			_dicTable.setName(__data_3.getString("name"));
			_dicTable.setName_alias(__data_3.getString("name_alias"));
			_dicTable.setInfo(__data_3.getString("info"));
			_dicTable.setCreate_user_account_id(__data_3.getInt("create_user_account_id"));
			_dicTable.setCreate_date(__data_3.getString("create_date"));
			_dicTable.setSortid(__data_3.getInt("sortid"));

		}

		return _dicTable;
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
	public DicItem getDicItemByItemId(Integer itemId) {

		DAS das = this.getDAS();
		String __sql = " select code, code_dictionary_name, data_key, data_value, key_code,"+
		               " info, create_user_account_id, create_date, sortid, ver from z_code_dictionary_data t where t.code = ? ";
		//所有列转化为字符类型
		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		ResultDescriptor __rdesc = null;
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code_dictionary_name");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("data_key");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("data_value");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("key_code");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("info");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_user_account_id");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("create_date");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("sortid");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("ver");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);
		//创建command，设置参数和ResultDescriptor
		Command __cmd = das.createCommand(__sql);
		__cmd.setParameter(1, itemId);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		 //获得查询结果，封装成DicItem
		List<DataObject> __dataList = __data.getList("z_code_dictionary");
		DicItem  _dicItem =  null;
		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			_dicItem = new DicItem();
			_dicItem.setCode(__data_3.getInt("code"));
			_dicItem.setCode_dictionary_name(__data_3.getString("code_dictionary_name"));
			_dicItem.setData_key(__data_3.getString("data_key"));
			_dicItem.setData_value(__data_3.getString("data_value"));
			_dicItem.setKey_code(__data_3.getString("key_code"));
			_dicItem.setInfo(__data_3.getString("info"));
			_dicItem.setCreate_user_account_id(__data_3.getInt("create_user_account_id"));
			_dicItem.setCreate_date(__data_3.getString("create_date"));
			_dicItem.setSortid(__data_3.getInt("sortid"));
			_dicItem.setVer(__data_3.getInt("ver"));
		}

		return _dicItem;

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
	public List<DicTable> findDicTableList(Integer domainId, Integer orgId,
			 List<FilterParam> filter, Integer beginId, Integer limitId) {
		   DAS das = this.getDAS();
		   //组合sql语句
		   String sql =
				"select * " +
				"  from (select code,name,name_alias,info,create_user_account_id,to_char(create_date,'yyyy-mm-dd hh24:mi:ss') create_date,sortid,ver, " +
				"       rownum testnum " +
				"       from z_code_dictionary " +
				"       where  1 = 1 " ;
			if (filter != null) {
			     for (FilterParam paramItem : filter) {
			       sql += " " + paramItem.getRelation() + " "
			           + paramItem.getField() + " " + paramItem.getLogical()
			     			+ " " + paramItem.getValue();
					}
			}
		   sql = sql +") where 1=1\n";
		   if(null != beginId)
		   {
		      sql = sql + " and testnum > ? ";
		   }
		   if(null != limitId)
		   {
		      sql = sql + " and testnum <= ? ";
		   }
		   sql = sql + " order by code asc ";
		   System.out.println(sql.toString());
		   //创建command,设置参数
		   Command command = das.createCommand(sql);
		   List list = this.getFilledResultDescriptionList_z_code_dictionary();
		   command.setResultDescriptors(list);
		   if(null != beginId)
		   {
		     command.setParameter(1,beginId);
		   }
		   if(null != limitId)
		   {
		     command.setParameter(2,beginId+limitId);
		   }
		   DataObject root = command.executeQuery();
		   //获得查询结果，封装成List<DataObject>
		   List<DicTable> dictableList = new ArrayList<DicTable>();
		   List<DataObject> doList = (List<DataObject>)root.getList("z_code_dictionary");
		   for(DataObject dObj : doList)
		   {
		     DicTable dictable = this.translateDOTODicTable(dObj);
		     dictableList.add(dictable);
		   }
		   return dictableList;
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

		    DAS das = this.getDAS();
		    String __sql = " select code, code_dictionary_name, data_key, data_value, key_code,info, create_user_account_id,"+
                           " to_char(create_date,'yyyy-mm-dd hh24:mi:ss') create_date, sortid, ver from z_code_dictionary_data " +
                           " where code_dictionary_name = ? ";

            //所有列转化为字符类型
			List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
			ResultDescriptor __rdesc = null;

			__rdesc = new ResultDescriptorImpl();
			__rdesc = new ResultDescriptorImpl();

			__rdesc.setColumnName("code");
			__rdesc.setTableName("z_code_dictionary_data");
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);

			__rdesc = new ResultDescriptorImpl();
			__rdesc.setColumnName("code_dictionary_name");
			__rdesc.setTableName("z_code_dictionary_data");
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);

			__rdesc = new ResultDescriptorImpl();
			__rdesc.setColumnName("data_key");
			__rdesc.setTableName("z_code_dictionary_data");
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);

			__rdesc = new ResultDescriptorImpl();
			__rdesc.setColumnName("data_value");
			__rdesc.setTableName("z_code_dictionary_data");
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);

			__rdesc = new ResultDescriptorImpl();
			__rdesc.setColumnName("key_code");
			__rdesc.setTableName("z_code_dictionary_data");
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);

			__rdesc = new ResultDescriptorImpl();
			__rdesc.setColumnName("info");
			__rdesc.setTableName("z_code_dictionary_data");
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);

			__rdesc = new ResultDescriptorImpl();
			__rdesc.setColumnName("create_user_account_id");
			__rdesc.setTableName("z_code_dictionary_data");
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);

			__rdesc = new ResultDescriptorImpl();
			__rdesc.setColumnName("create_date");
			__rdesc.setTableName("z_code_dictionary_data");
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);

			__rdesc = new ResultDescriptorImpl();
			__rdesc.setColumnName("sortid");
			__rdesc.setTableName("z_code_dictionary_data");
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);

			__rdesc = new ResultDescriptorImpl();
			__rdesc.setColumnName("ver");
			__rdesc.setTableName("z_code_dictionary_data");
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);
            //创建command，设置参数
			Command __cmd = das.createCommand(__sql);
			__cmd.setParameter(1, "SEX");
//			__cmd.setParameter(1, String.valueOf(tableId));
			__cmd.setResultDescriptors(__list);

			DataObject __data = __cmd.executeQuery();
			List<DicItem> __form = new ArrayList<DicItem>();
            //结果转化为List<DicItem>
			for (DataObject __data_3 : (List<DataObject>) __data.getList("z_code_dictionary_data")) {
				DicItem _dicItem = new DicItem();

				_dicItem.setCode(__data_3.getInt("code"));
				_dicItem.setCode_dictionary_name(__data_3.getString("code_dictionary_name"));
				_dicItem.setData_key(__data_3.getString("data_key"));
				_dicItem.setData_value(__data_3.getString("data_value"));
				_dicItem.setKey_code(__data_3.getString("key_code"));
				_dicItem.setInfo(__data_3.getString("info"));
				_dicItem.setCreate_user_account_id(__data_3.getInt("create_user_account_id"));
				_dicItem.setCreate_date(__data_3.getString("create_date"));
				_dicItem.setSortid(__data_3.getInt("sortid"));
				_dicItem.setVer(__data_3.getInt("ver"));

				__form.add(_dicItem);
			}
			return __form;
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
	public Boolean insertDicTable(DicTable dictable) {
		DAS das = this.getDAS();
	    Boolean rtn = true;
	    String sql =
	      "  insert into z_code_dictionary " +
	      "  (code,name,name_alias,info,create_user_account_id,create_date,sortid,ver) " +
	      "  values(?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";
	    Command command = das.createCommand(sql);
	    //设置sql语句中的参数值
	    dictable.setCode(this.sequenceService.getNextValue("getMaxDicTableID"));

	    command.setParameter(1, dictable.getCode());
	    command.setParameter(2, dictable.getName());
	    command.setParameter(3, dictable.getName_alias());
	    command.setParameter(4, dictable.getInfo());
	    command.setParameter(5, dictable.getCreate_user_account_id());
	    command.setParameter(6, dictable.getCreate_date());
	    command.setParameter(7, dictable.getSortid());
	    command.setParameter(8, dictable.getVer());
	    try
	    {
	      command.execute();
	    }
	    catch (Exception e)
	    {
	      rtn = false;
	    }
		return rtn;
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
	 public Boolean updateDicTable(DicTable dictable) {
		    DAS das = this.getDAS();

		    Boolean  temp = true;
		    //更新字典代码明细
		    temp = updateDicItemByDicTableName(dictable);
		    if (!temp)  return  false;
		    //更新字典代码
		    String __sql = " update  z_code_dictionary  set name = ?,name_alias = ?,info = ?"+
		                   "  where code = ? ";
			Command __cmd = das.createCommand(__sql);
			 //设置sql语句中的参数值
			__cmd.setParameter(1, dictable.getName());
			__cmd.setParameter(2, dictable.getName_alias());
			__cmd.setParameter(3, dictable.getInfo());
			__cmd.setParameter(4, dictable.getCode());

			Boolean __result = true;

			try {
				__cmd.execute();
			} catch (Exception $ex) {
				__result = false;
			}
			return __result;
	  }

	 /**
		 * @updateDicItemByDicTableName
		 * @Description:修改DicItem
		 * @return  Boolean
		 * @throws
		 * @author  普秀霞
		 * @date   2012-11-27
		 * 修改日期： 修改人： 复审人：
		 */
	 public  Boolean  updateDicItemByDicTableName(DicTable  dictable){
			 DAS das = this.getDAS();
			 //组合更新sql语句
			 StringBuffer sql = new  StringBuffer();
			 sql.append("  update  z_code_dictionary_data  detail  set   detail.code_dictionary_name = ? ");
			 sql.append("    where exists( select  code  from  z_code_dictionary  main  ");
			 sql.append("                  where   main.name = detail.code_dictionary_name ");
			 sql.append("                  and  main.code = ? )");

		     Command cmd = das.createCommand(sql.toString());
		     //设置sql语句中的参数
		     cmd.setParameter(1, dictable.getName());
			 cmd.setParameter(2, dictable.getCode());

			 Boolean  result = true;

			 try {
				 cmd.execute();
			 } catch (Exception $ex) {
				 result = false;
			 }
			return  result;
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
	public Boolean insertDicItem(DicItem dicitem) {
		DAS das = this.getDAS();
	    Boolean rtn = true;
	    String sql =
	      "  insert into z_code_dictionary_data " +
	      "  (code,code_dictionary_name,data_key,data_value,key_code,info,create_user_account_id,create_date,sortid,ver) " +
	      "  values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";
	    Command command = das.createCommand(sql);
	  //设置sql语句中的参数
	    dicitem.setCode(this.sequenceService.getNextValue("getMaxDicItemID"));
	    command.setParameter(1, dicitem.getCode());
	    command.setParameter(2, dicitem.getCode_dictionary_name());
	    command.setParameter(3, dicitem.getData_key());
	    command.setParameter(4, dicitem.getData_value());
	    command.setParameter(5, dicitem.getKey_code());
	    command.setParameter(6, dicitem.getInfo());
	    command.setParameter(7, dicitem.getCreate_user_account_id());
	    command.setParameter(8, dicitem.getCreate_date());
	    command.setParameter(9, dicitem.getSortid());
	    command.setParameter(10, dicitem.getVer());
	    try
	    {
	        command.execute();
	    }
	    catch (Exception e)
	    {
	        rtn = false;
	    }
			return rtn;
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
	public Boolean updateDicItem(DicItem dicitem) {
		DAS das = this.getDAS();
		String __sql = " update  z_code_dictionary_data  set code_dictionary_name = ?,data_key = ?,data_value = ? , "+
		               " key_code = ? , info = ?  where  code = ? ";

		Command __cmd = das.createCommand(__sql);
		//设置sql语句中的参数
		__cmd.setParameter(1, dicitem.getCode_dictionary_name());
		__cmd.setParameter(2, dicitem.getData_key());
		__cmd.setParameter(3, dicitem.getData_value());
		__cmd.setParameter(4, dicitem.getKey_code());
		__cmd.setParameter(5, dicitem.getInfo());
		__cmd.setParameter(6, dicitem.getCode());

		Boolean __result = true;

		try {
			__cmd.execute();
		} catch (Exception $ex) {
			__result = false;
		}

		return __result;
	}

	public Boolean delDicCodeByTableId(Integer id) {
	    return null;
	}
	public Boolean delDicCodeByItemId(Integer id) {
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
	public Boolean delDicCodeByTableIds(Integer[] ids){
		DAS das = this.getDAS();
		//判断ids是否为空,为空不执行删除
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";
        //根据ids组合删除语句
		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		StringBuffer sbSql = new  StringBuffer();
		sbSql.append(" delete  z_code_dictionary  main ");
		sbSql.append(" where code in(  ");
		sbSql.append(  __appendSQL );
		sbSql.append(" ) ");
		sbSql.append("  and not exists ( select code from  z_code_dictionary_data detail " );
		sbSql.append("                      where main.name = detail.code_dictionary_name " );
		sbSql.append("                  ) ");


		Command __cmd = das.createCommand(sbSql.toString());
        //设置sql语句中的变量
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
	 * @judgedelDicCode
	 * @Description:判断ids中不能删除的个数
	 * @return  Integer
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-29
	 * 修改日期： 修改人： 复审人：
	 */
	public  Integer  judgedelDicCode(Integer[] ids){
		DAS das = this.getDAS();
		String  appendSQL = "";
        //组合sql语句
		for (int  i = 0,  j  = ids.length;  i  < j; i++) {
			appendSQL += "," + String.valueOf(ids[i]);
		}

		appendSQL =  appendSQL.substring(1);

		StringBuffer sbSql = new  StringBuffer();
		sbSql.append("  select  count(1) code  from   z_code_dictionary   ");
		sbSql.append("  where  code in( ");
		sbSql.append(appendSQL);
		sbSql.append("                 ) ");
		sbSql.append(" and  exists(  select code from  z_code_dictionary_data  ");
		sbSql.append("               where z_code_dictionary.name = z_code_dictionary_data.code_dictionary_name ");
		sbSql.append("             )  ");

		List<ResultDescriptor>  rdList = new ArrayList<ResultDescriptor>();

		ResultDescriptor  rdesc = null;
        //设置列
		rdesc = new ResultDescriptorImpl();
		rdesc.setColumnName("code");
		rdesc.setTableName("z_code_dictionary");
		rdesc.setColumnType("commonj.sdo.String");
		rdList.add(rdesc);

		Command   cmd = das.createCommand(sbSql.toString());
		cmd.setResultDescriptors(rdList);

		DataObject dataObject = cmd.executeQuery();

		List<DataObject>  dataList = dataObject.getList("z_code_dictionary");
        //获得数量
		int  count = 0;

		if ( dataList != null && dataList.size() > 0) {
			DataObject  data = dataList.get(0);
		    count =  data.getInt("code");
		}

		return  count;

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
	public Boolean delDicCodeByItemIds(Integer[] ids){

		DAS das = this.getDAS();
		//判断ids是否为空
		if (ids == null || ids.length < 1)
			return false;

		String __appendSQL = "";
        //组合删除语句
		for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
			__appendSQL += ",?";
		}

		__appendSQL = __appendSQL.substring(1);

		String __sql = "delete  z_code_dictionary_data  where code in (" + __appendSQL + ")";

		Command __cmd = das.createCommand(__sql);
        //设置sql语句中的参数
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
	 * @getDicTableListRowCount
	 * @Description:根据查询条件获得DicTable的行数
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-10-31
	 * 修改日期： 修改人： 复审人：
	 */
	public  Integer  getDicTableListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter){
		DAS das = this.getDAS();
		String __sql = " select count(1)  code  from   z_code_dictionary  a  where 1=1 ";
        //组合sql语句条件
		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a."
						+ __param_3.getField() + " " + __param_3.getLogical()
						+ " " + __param_3.getValue();
			}
		}

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

		ResultDescriptor __rdesc = null;

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("z_code_dictionary");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("z_code_dictionary");

		int __count = 0;
        //获得数量
		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			__count = __data_3.getInt("code");
		}

		return __count;
	}

	/**
	 * @translateDOTODicTable
	 * @Description:查询结果由DataObject转化为DicTable
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-27
	 * 修改日期： 修改人： 复审人：
	 */

	private DicTable translateDOTODicTable(DataObject z_code_dictionaryDO)
	{
	    DicTable dictable = new DicTable();
	    if (z_code_dictionaryDO.get("code") != null)
	       dictable.setCode(Integer.parseInt((String) z_code_dictionaryDO.get("code")));
	    dictable.setName(null == z_code_dictionaryDO.get("name")?"":z_code_dictionaryDO.get("name").toString());
	    dictable.setName_alias(null == z_code_dictionaryDO.get("name_alias")?"":z_code_dictionaryDO.get("name_alias").toString());
	    dictable.setInfo(null == z_code_dictionaryDO.get("info")?"":z_code_dictionaryDO.get("info").toString());
        if (z_code_dictionaryDO.get("create_user_account_id") !=null)
	      dictable.setCreate_user_account_id(Integer.parseInt((String) z_code_dictionaryDO.get("create_user_account_id")));
	    dictable.setCreate_date(null == z_code_dictionaryDO.get("create_date")?"":z_code_dictionaryDO.get("create_date").toString());
	    if (z_code_dictionaryDO.get("sortid") != null)
	       dictable.setSortid(Integer.parseInt((String) z_code_dictionaryDO.get("sortid")));
	    if (z_code_dictionaryDO.get("ver") != null)
	       dictable.setVer(Integer.parseInt((String) z_code_dictionaryDO.get("ver")));
		return dictable;
	}



	/**
	 * @getFilledResultDescriptionList_z_code_dictionary
	 * @Description:自定义代码列转化字符串，添加到list中返回
	 * @return  List
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-27
	 * 修改日期： 修改人： 复审人：
	 */
	public List getFilledResultDescriptionList_z_code_dictionary()
	{
	    List list = new ArrayList();
	    ResultDescriptor code = new ResultDescriptorImpl();
	    code.setColumnName("code");
	    code.setTableName("z_code_dictionary");
	    code.setColumnIndex(0);
	    code.setColumnType("commonj.sdo.String");
	    list.add(code);
	    ResultDescriptor name = new ResultDescriptorImpl();
	    name.setColumnName("name");
	    name.setTableName("z_code_dictionary");
	    name.setColumnIndex(1);
	    name.setColumnType("commonj.sdo.String");
	    list.add(name);
	    ResultDescriptor name_alias = new ResultDescriptorImpl();
	    name_alias.setColumnName("name_alias");
	    name_alias.setTableName("z_code_dictionary");
	    name_alias.setColumnIndex(2);
	    name_alias.setColumnType("commonj.sdo.String");
	    list.add(name_alias);
	    ResultDescriptor info = new ResultDescriptorImpl();
	    info.setColumnName("info");
	    info.setTableName("z_code_dictionary");
	    info.setColumnIndex(3);
	    info.setColumnType("commonj.sdo.String");
	    list.add(info);
	    ResultDescriptor create_user_account_id = new ResultDescriptorImpl();
	    create_user_account_id.setColumnName("create_user_account_id");
	    create_user_account_id.setTableName("z_code_dictionary");
	    create_user_account_id.setColumnIndex(4);
	    create_user_account_id.setColumnType("commonj.sdo.String");
	    list.add(create_user_account_id);
	    ResultDescriptor create_date = new ResultDescriptorImpl();
	    create_date.setColumnName("create_date");
	    create_date.setTableName("z_code_dictionary");
	    create_date.setColumnIndex(5);
	    create_date.setColumnType("commonj.sdo.String");
	    list.add(create_date);
	    ResultDescriptor sortid = new ResultDescriptorImpl();
	    sortid.setColumnName("sortid");
	    sortid.setTableName("z_code_dictionary");
	    sortid.setColumnIndex(6);
	    sortid.setColumnType("commonj.sdo.String");
	    list.add(sortid);
	    ResultDescriptor ver = new ResultDescriptorImpl();
	    ver.setColumnName("ver");
	    ver.setTableName("z_code_dictionary");
	    ver.setColumnIndex(7);
	    ver.setColumnType("commonj.sdo.String");
	    list.add(ver);
	    return list;
	}
	/**
	 * @translateDOTODicItem
	 * @Description:查询结果封装为DicItem
	 * @return  DicItem
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-27
	 * 修改日期： 修改人： 复审人：
	 */
	private DicItem translateDOTODicItem(DataObject z_code_dictionary_dataDO)
	{
	    DicItem dicitem = new DicItem();
	    dicitem.setCode((Integer) (null == z_code_dictionary_dataDO.get("code")?null:z_code_dictionary_dataDO.get("code")));
	    dicitem.setCode_dictionary_name(null == z_code_dictionary_dataDO.get("code_dictionary_name")?"":z_code_dictionary_dataDO.get("code_dictionary_name").toString());
	    dicitem.setData_key(null == z_code_dictionary_dataDO.get("data_key")?"":z_code_dictionary_dataDO.get("data_key").toString());
	    dicitem.setData_value(null == z_code_dictionary_dataDO.get("data_value")?"":z_code_dictionary_dataDO.get("data_value").toString());
	    dicitem.setKey_code(null == z_code_dictionary_dataDO.get("key_code")?"":z_code_dictionary_dataDO.get("key_code").toString());
	    dicitem.setInfo(null == z_code_dictionary_dataDO.get("info")?"":z_code_dictionary_dataDO.get("info").toString());
	    dicitem.setCreate_user_account_id((Integer) (null == z_code_dictionary_dataDO.get("create_user_account_id")?"":z_code_dictionary_dataDO.get("create_user_account_id")));
	    dicitem.setCreate_date(null == z_code_dictionary_dataDO.get("create_date")?"":z_code_dictionary_dataDO.get("create_date").toString());
	    dicitem.setSortid((Integer) (null == z_code_dictionary_dataDO.get("sortid")?null:z_code_dictionary_dataDO.get("sortid")));
	    dicitem.setVer((Integer) (null == z_code_dictionary_dataDO.get("ver")?null:z_code_dictionary_dataDO.get("ver")));
	    return dicitem;
    }


	/**
	 * @getFilledResultDescriptionList_z_code_dictionary_data
	 * @Description:自定义代码列转化字符串，添加到list中返回
	 * @return  List
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-27
	 * 修改日期： 修改人： 复审人：
	 */
	public List getFilledResultDescriptionList_z_code_dictionary_data()
	{
	    List list = new ArrayList();
	    ResultDescriptor code = new ResultDescriptorImpl();
	    code.setColumnName("code");
	    code.setTableName("z_code_dictionary_data");
	    code.setColumnIndex(0);
	    code.setColumnType("commonj.sdo.String");
	    list.add(code);
	    ResultDescriptor code_dictionary_name = new ResultDescriptorImpl();
	    code_dictionary_name.setColumnName("code_dictionary_name");
	    code_dictionary_name.setTableName("z_code_dictionary_data");
	    code_dictionary_name.setColumnIndex(1);
	    code_dictionary_name.setColumnType("commonj.sdo.String");
	    list.add(code_dictionary_name);
	    ResultDescriptor data_key = new ResultDescriptorImpl();
	    data_key.setColumnName("data_key");
	    data_key.setTableName("z_code_dictionary_data");
	    data_key.setColumnIndex(2);
	    data_key.setColumnType("commonj.sdo.String");
	    list.add(data_key);
	    ResultDescriptor data_value = new ResultDescriptorImpl();
	    data_value.setColumnName("data_value");
	    data_value.setTableName("z_code_dictionary_data");
	    data_value.setColumnIndex(3);
	    data_value.setColumnType("commonj.sdo.String");
	    list.add(data_value);
	    ResultDescriptor key_code = new ResultDescriptorImpl();
	    key_code.setColumnName("key_code");
	    key_code.setTableName("z_code_dictionary_data");
	    key_code.setColumnIndex(4);
	    key_code.setColumnType("commonj.sdo.String");
	    list.add(key_code);
	    ResultDescriptor info = new ResultDescriptorImpl();
	    info.setColumnName("info");
	    info.setTableName("z_code_dictionary_data");
	    info.setColumnIndex(5);
	    info.setColumnType("commonj.sdo.String");
	    list.add(info);
	    ResultDescriptor create_user_account_id = new ResultDescriptorImpl();
	    create_user_account_id.setColumnName("create_user_account_id");
	    create_user_account_id.setTableName("z_code_dictionary_data");
	    create_user_account_id.setColumnIndex(6);
	    create_user_account_id.setColumnType("commonj.sdo.String");
	    list.add(create_user_account_id);
	    ResultDescriptor create_date = new ResultDescriptorImpl();
	    create_date.setColumnName("create_date");
	    create_date.setTableName("z_code_dictionary_data");
	    create_date.setColumnIndex(7);
	    create_date.setColumnType("commonj.sdo.String");
	    list.add(create_date);
	    ResultDescriptor sortid = new ResultDescriptorImpl();
	    sortid.setColumnName("sortid");
	    sortid.setTableName("z_code_dictionary_data");
	    sortid.setColumnIndex(8);
	    sortid.setColumnType("commonj.sdo.String");
	    list.add(sortid);
	    ResultDescriptor ver = new ResultDescriptorImpl();
	    ver.setColumnName("ver");
	    ver.setTableName("z_code_dictionary_data");
	    ver.setColumnIndex(9);
	    ver.setColumnType("commonj.sdo.String");
	    list.add(ver);
	    return list;
	}

	/**
	 * @findDicItemListByCondition
	 * @Description:根据查询条件获得List<DicItem>
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-27
	 * 修改日期： 修改人： 复审人：
	 */
	public List<DicItem> findDicItemListByCondition(List<FilterParam> filter){

		 DAS das = this.getDAS();
		 //组合查询条件
		 String __sql = " select code, code_dictionary_name, data_key, data_value, key_code,  info "+
                        "   from z_code_dictionary_data a  where 1=1 ";
		 if (filter != null) {
				for (FilterParam __param_3 : filter) {
					__sql += " " + __param_3.getRelation() + " a."
							+ __param_3.getField() + " " + __param_3.getLogical()
							+ " " + __param_3.getValue();
				}
		}

		 __sql += "  order by code  asc";

		//设置列，列全部转化为字符串
		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		ResultDescriptor __rdesc = null;
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code_dictionary_name");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("data_key");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("data_value");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("key_code");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("info");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();
		List<DicItem> __form = new ArrayList<DicItem>();
        //查询结果封装为List<DicItem>
		for (DataObject __data_3 : (List<DataObject>) __data.getList("z_code_dictionary_data")) {
			DicItem _dicItem = new DicItem();

			_dicItem.setCode(__data_3.getInt("code"));
			_dicItem.setCode_dictionary_name(__data_3.getString("code_dictionary_name"));
			_dicItem.setData_key(__data_3.getString("data_key"));
			_dicItem.setData_value(__data_3.getString("data_value"));
			_dicItem.setKey_code(__data_3.getString("key_code"));
			_dicItem.setInfo(__data_3.getString("info"));
			__form.add(_dicItem);
		}
		return __form;
    }


	/**
	 * @getDicItemListByConditionRowCount
	 * @Description:根据查询条获得DicItem行数
	 * @return  Boolean
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-27
	 * 修改日期： 修改人： 复审人：
	 */
	public  Integer  getDicItemListByConditionRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter){
		DAS das = this.getDAS();
		//根据查询条件封装语句
		String __sql = " select count(1)  code  from   z_code_dictionary_data  a  where 1=1 ";

		if (filter != null) {
			for (FilterParam __param_3 : filter) {
				__sql += " " + __param_3.getRelation() + " a."
						+ __param_3.getField() + " " + __param_3.getLogical()
						+ " " + __param_3.getValue();
			}
		}

		//设置列
		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		ResultDescriptor __rdesc = null;
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("z_code_dictionary_data");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject __data = __cmd.executeQuery();

		List<DataObject> __dataList = __data.getList("z_code_dictionary_data");

		int __count = 0;
        //获得数量
		if (__dataList != null && __dataList.size() > 0) {
			DataObject __data_3 = __dataList.get(0);
			__count = __data_3.getInt("code");
		}

		return __count;
	}

	/**
	 * @getDicTableList
	 * @Description:获得DicTable列表，封装为List<JSONObject>
	 * @return  List<JSONObject>
	 * @throws
	 * @author  普秀霞
	 * @date   2012-11-27
	 * 修改日期： 修改人： 复审人：
	 */
	public List<JSONObject> getDicTableList(){

		 DAS das = this.getDAS();
		 String __sql = "  select name, code  from  z_code_dictionary  ";

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		//设置列
		ResultDescriptor __rdesc = null;
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("name");
		__rdesc.setTableName("z_code_dictionary");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);
		__rdesc = new ResultDescriptorImpl();
		__rdesc.setColumnName("code");
		__rdesc.setTableName("z_code_dictionary");
		__rdesc.setColumnType("commonj.sdo.String");
		__list.add(__rdesc);

		Command __cmd = das.createCommand(__sql);
		__cmd.setResultDescriptors(__list);

		DataObject  dataObject = __cmd.executeQuery();
		List<JSONObject>  list = new ArrayList<JSONObject>();
        //查询结果封装为List<JSONObject>
		try{
			for (DataObject   data : (List<DataObject>) dataObject.getList("z_code_dictionary")) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", data.getString("name"));
				jsonObject.put("name", data.getString("name"));
				list.add(jsonObject);
			}
		}catch(Exception e){
			return null;
		}
		return list;
   }






}
