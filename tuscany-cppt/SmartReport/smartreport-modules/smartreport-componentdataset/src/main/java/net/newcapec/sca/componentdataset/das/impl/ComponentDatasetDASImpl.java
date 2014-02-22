package net.newcapec.sca.componentdataset.das.impl;

/**
 * <p>Title: DAS实现类 </p>
 * <p>Description:控件绑定数据集数据构件实现类</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2013</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2013-01-08
 * 修改日期：
 * 修改人：
 * 复审人：
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.oasisopen.sca.annotation.Reference;

import net.newcapec.sca.componentdataset.ComponentDataset;
import net.newcapec.sca.componentdataset.das.ComponentDatasetDAS;
import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.util.DASFactory;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;

import commonj.sdo.DataObject;

public class ComponentDatasetDASImpl implements ComponentDatasetDAS {

	private static final Logger _log = Logger
	.getLogger(ComponentDatasetDASImpl.class);
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
	 * @Title:getComponentDatasetById
	 * @Description:根据id获得ComponentDataset
	 * @return  ComponentDataset2013-01-08
	 * @throws
	 * @author  普秀霞
	 * @date
	 * 修改日期： 修改人： 复审人：
	 */

	public  ComponentDataset  getComponentDatasetById(Integer id) {
		DAS das = this.getDAS();
		StringBuilder sbSql = new StringBuilder();
		sbSql.append(" select  code, domain_code, unit_code, component_type, component_datasource, dataset_name,");
		sbSql.append("     text_field, value_field, parent_field, top_default  ");
		sbSql.append(" from   p_componentdataset  ");
		sbSql.append(" where  code = ?  ");
		List<ResultDescriptor> rdList = new ArrayList<ResultDescriptor>();
		String columns[] = {"code", "domain_code", "unit_code", "component_type", "component_datasource", "dataset_name", "text_field", "value_field", "parent_field", "top_default"};
		rdList = getResultDescriptorList(columns,"p_componentdataset");
		Command  cmd = das.createCommand(sbSql.toString());
		cmd.setParameter(1, id);
		cmd.setResultDescriptors(rdList);
		DataObject  dataObject =  cmd.executeQuery();

		List<DataObject>  dataList = dataObject.getList("p_componentdataset");
		ComponentDataset  componentDataset = null;
		if ( dataList != null && dataList.size() > 0) {
			DataObject data = dataList.get(0);
			componentDataset =  new ComponentDataset();
			componentDataset.setCode(data.getInt("code"));
			componentDataset.setDomain_code(data.getInt("domain_code"));
			componentDataset.setUnit_code(data.getInt("unit_code"));
			componentDataset.setComponent_type(data.getString("component_type"));
			componentDataset.setComponent_datasource(data.getString("component_datasource"));
			componentDataset.setDataset_name(data.getString("dataset_name"));
			componentDataset.setText_field(data.getString("text_field"));
			componentDataset.setValue_field(data.getString("value_field"));
			componentDataset.setParent_field(data.getString("parent_field"));
			componentDataset.setTop_default(data.getString("top_default"));
		}
		return componentDataset;
	}


	/**
	 * @findComponentDatasetList
	 * @Description:根据条件获得ComponentDataset列表
	 * @return  List<ComponentDataset>
	 * @throws
	 * @author  普秀霞
	 * @date   2013-01-08
	 * 修改日期： 修改人： 复审人：
	 */

	public List<ComponentDataset> findComponentDatasetList(Integer domainId,
			Integer orgId, List<FilterParam> filter, Integer begin,
			Integer limit) {
		DAS das = this.getDAS();
		StringBuilder   sbSql = new StringBuilder();

		sbSql.append(" select t.* from ( select c.code, c.domain_code, c.unit_code, c.component_type, ");
		sbSql.append("        d.code  component_datasource, d.name  datasource_name,c.dataset_name, c.text_field, ");
		sbSql.append("        c.value_field, c.parent_field, c.top_default ,rownum  row_num  ");
		sbSql.append(" from   p_componentdataset c , p_datasource  d   ");
		sbSql.append(" where  c.component_datasource = d.code   ");

		if (filter != null) {
			for (FilterParam  filterParam : filter) {
				sbSql.append(" ");
				sbSql.append(filterParam.getRelation());
				sbSql.append(" c.");
				sbSql.append(filterParam.getField());
				sbSql.append(" ");
				sbSql.append(filterParam.getLogical());
				sbSql.append(" ");
				sbSql.append(filterParam.getValue());
			}
		}

		sbSql.append(" order by c.code desc ) t ");

		if (begin > -1 && limit > -1)
			sbSql.append( " where t.row_num between ? and ? ");
		System.out.println(sbSql.toString());

		List<ResultDescriptor>  rdList = new ArrayList<ResultDescriptor>();
		String columns[] = {"code", "domain_code", "unit_code", "component_type", "component_datasource", "datasource_name","dataset_name", "text_field", "value_field", "parent_field", "top_default"};
		rdList = getResultDescriptorList(columns,"p_componentdataset");
		Command   cmd = das.createCommand(sbSql.toString());
		if (begin > -1 && limit > -1){
			 cmd.setParameter(1, begin+1);
			 cmd.setParameter(2, begin + limit);
		}
		cmd.setResultDescriptors(rdList);
		DataObject  dataObject = cmd.executeQuery();

		List<ComponentDataset>  cdList = new ArrayList<ComponentDataset>();

		for (DataObject  data : (List<DataObject>) dataObject.getList("p_componentdataset")) {
			ComponentDataset componentDataset =  new ComponentDataset();
			componentDataset.setCode(data.getInt("code"));
			componentDataset.setDomain_code(data.getInt("domain_code"));
			componentDataset.setUnit_code(data.getInt("unit_code"));
			componentDataset.setComponent_type(data.getString("component_type"));
			componentDataset.setComponent_datasource(data.getString("component_datasource"));
			componentDataset.setDatasource_name(data.getString("datasource_name"));
			componentDataset.setDataset_name(data.getString("dataset_name"));
			componentDataset.setText_field(data.getString("text_field"));
			componentDataset.setValue_field(data.getString("value_field"));
			componentDataset.setParent_field(data.getString("parent_field"));
			componentDataset.setTop_default(data.getString("top_default"));
			cdList.add(componentDataset);
		}

		return cdList;
	}

	/**
	 * @getResultDescriptorList
	 * @Description:根据 传入的列和表名获得List<ResultDescriptor>
	 * @return List<ResultDescriptor>
	 * @throws
	 * @author 普秀霞
	 * @date 2013-01-08  修改日期： 修改人： 复审人：
	 */
	public List<ResultDescriptor> getResultDescriptorList(String[] fields, String tableName) {

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		ResultDescriptor __rdesc = null;
		for (String field : fields) {
			__rdesc = new ResultDescriptorImpl();
			__rdesc.setColumnName(field);
			__rdesc.setTableName(tableName);
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);
		}
		return __list;
	}

	public Integer getComponentDatasetListRowCount(Integer resourceId,
			List<FilterParam> filter) {
		DAS das = this.getDAS();
		StringBuilder   sbSql = new StringBuilder();
		sbSql.append(" select count(1)  code  from   p_componentdataset  a  where 1=1 ");
        //组合sql语句条件
		if (filter != null) {
			for (FilterParam  filterParam : filter) {
				sbSql.append(" ");
				sbSql.append(filterParam.getRelation());
				sbSql.append(" a.");
				sbSql.append(filterParam.getField());
				sbSql.append(" ");
				sbSql.append(filterParam.getLogical());
				sbSql.append(" ");
				sbSql.append(filterParam.getValue());
			}
		}

		List<ResultDescriptor>  rdList = new ArrayList<ResultDescriptor>();
		ResultDescriptor   rdesc = null;
		rdesc = new ResultDescriptorImpl();
		rdesc.setColumnName("code");
		rdesc.setTableName("p_componentdataset");
		rdesc.setColumnType("commonj.sdo.String");
		rdList.add(rdesc);
		Command  cmd = das.createCommand(sbSql.toString());
		cmd.setResultDescriptors(rdList);
		DataObject  dataObject =  cmd.executeQuery();

		List<DataObject>  dataList = dataObject.getList("p_componentdataset");
		int  count = 0;
        //获得数量
		if (dataList != null && dataList.size() > 0) {
			DataObject  data = dataList.get(0);
			count = data.getInt("code");
		}

		return  count;
	}

	public Boolean insertComponentDataset(ComponentDataset componentDataset) {

		DAS das = this.getDAS();
	    Boolean rtn = true;
	    StringBuilder  sbSql =  new StringBuilder();
	    sbSql.append("  insert into  p_componentdataset ");
	    sbSql.append("  ( code, domain_code, unit_code, component_type, component_datasource, dataset_name," );
	    sbSql.append("    text_field, value_field, parent_field, top_default ) ");
	    sbSql.append("    values(?,?,?,?,?,?,?,?,?,?)");
	    Command command = das.createCommand(sbSql.toString());
	    //设置sql语句中的参数值
	    Integer  sequenceId = this.sequenceService.getNextValue("getMaxComponentDatasetID");
	    //System.out.println("sequenceId："+sequenceId);
	    componentDataset.setCode(sequenceId);

	    command.setParameter(1, componentDataset.getCode());
	    command.setParameter(2, componentDataset.getDomain_code());
	    command.setParameter(3, componentDataset.getUnit_code());
	    command.setParameter(4, componentDataset.getComponent_type());
	    command.setParameter(5, componentDataset.getComponent_datasource());
	    command.setParameter(6, componentDataset.getDataset_name());
	    command.setParameter(7, componentDataset.getText_field());
	    command.setParameter(8, componentDataset.getValue_field());
	    command.setParameter(9, componentDataset.getParent_field());
	    command.setParameter(10, componentDataset.getTop_default());
	    try
	    {
	       command.execute();
	       return  true;
	    }
	    catch (Exception e)
	    {
	       _log.error("ComponentDataset：控件数据集增加失败");
	       return  false;
	    }

	}

	public Boolean updateComponentDataset(ComponentDataset componentDataset) {
		DAS das = this.getDAS();
		StringBuilder  sbSql =  new StringBuilder();
		sbSql.append(" update  p_componentdataset  set  component_type = ?,component_datasource = ?,dataset_name = ? ,");
		sbSql.append(" text_field = ?, value_field = ?, parent_field = ?, top_default = ? ");
		sbSql.append(" where   code = ? ");
		Command  cmd = das.createCommand(sbSql.toString());
		 //设置sql语句中的参数值
		cmd.setParameter(1, componentDataset.getComponent_type());
		cmd.setParameter(2, componentDataset.getComponent_datasource());
		cmd.setParameter(3, componentDataset.getDataset_name());
		cmd.setParameter(4, componentDataset.getText_field());
		cmd.setParameter(5, componentDataset.getValue_field());
		cmd.setParameter(6, componentDataset.getParent_field());
		cmd.setParameter(7, componentDataset.getTop_default());
		cmd.setParameter(8, componentDataset.getCode());

		try {
			cmd.execute();
			return  true;
		} catch (Exception e) {
			_log.error("ComponentDataset：控件数据集修改失败");
			return  false;
		}

	}

	public Boolean delComponentDatasetByIds(Integer[] ids) {
		DAS das = this.getDAS();
		//判断ids是否为空,为空不执行删除
		if (ids == null || ids.length < 1)
			return false;

		String   appendSQL = "";
        //根据ids组合删除语句
		for (int i = 0, j = ids.length; i < j; i++) {
			appendSQL += ",?";
		}

		appendSQL = appendSQL.substring(1);

		StringBuffer sbSql = new  StringBuffer();
		sbSql.append(" delete  from p_componentdataset    ");
		sbSql.append(" where code in(  ");
		sbSql.append(appendSQL);
		sbSql.append(" ) ");

		Command  cmd = das.createCommand(sbSql.toString());
        //设置sql语句中的变量
		for (int  i = 1, j = ids.length; i <= j; i++) {
		    cmd.setParameter(i, ids[i - 1]);
		}

		try {
			cmd.execute();
			return  true;
		} catch (Exception $ex) {
			_log.error("ComponentDataset：控件数据集删除失败");
			return  false;
		}

	}

	public  List<JSONObject> obtainComponentDatasource() {
		DAS das = this.getDAS();
		String sql = " select code , name  from  p_datasource  where  use_scope_type = 2 ";
		List<ResultDescriptor>  rdList = new ArrayList<ResultDescriptor>();
		ResultDescriptor   rdesc = null;
		rdesc = new ResultDescriptorImpl();
		rdesc.setColumnName("code");
		rdesc.setTableName("p_datasource");
		rdesc.setColumnType("commonj.sdo.String");
		rdList.add(rdesc);
		rdesc = new ResultDescriptorImpl();
		rdesc.setColumnName("name");
		rdesc.setTableName("p_datasource");
		rdesc.setColumnType("commonj.sdo.String");
		rdList.add(rdesc);
		Command  cmd = das.createCommand(sql);
		cmd.setResultDescriptors(rdList);
		DataObject  dataObject =  cmd.executeQuery();

		List<DataObject>  dataList = dataObject.getList("p_datasource");
		List<JSONObject> listResult = null;
		listResult =  new ArrayList<JSONObject>();
		JSONObject  jsonResult = null;
		try {
			for (DataObject  data : (List<DataObject>) dataList) {
				jsonResult = new JSONObject();
			    jsonResult.put("code",data.getInt("code"));
				jsonResult.put("name",data.getString("name"));
				listResult.add(jsonResult);
			}
			return  listResult;
	    } catch (JSONException e) {
	    	_log.error("ComponentDataset：获得控件数据源失败");
	    	return  null;
	    }

	}

	public List<JSONObject>  obtainFieldByDatasource( String dsCode){
		Connection  conn = null;
		String  type,method;
		if (dsCode==null || "".equals(dsCode)) return null;

		//获得数据源类型、数据库连接、sql语句或存储过程名
		Map<String,Object> connTypeMethod = getDBConnByDSCode(dsCode);
		if (connTypeMethod == null) return null;
		conn = (Connection)connTypeMethod.get("conn");
		type = (String)connTypeMethod.get("type");
		method = (String)connTypeMethod.get("method") ;
		if (conn == null) return null;
		Statement  stm = null;
		ResultSet  rs = null;
		CallableStatement  proc = null;
		List<JSONObject> listResult = null;
		listResult =  new ArrayList<JSONObject>();
		JSONObject  jsonResult = null;
		String columnName;
        try{
			if ("sql".equals(type)){
				stm = conn.createStatement();
				rs = stm.executeQuery(method);
			}else if ("proc".equals(type)){
				String  procStatement = " { call " + method + "(?,?";
				procStatement +=  "," + "";
			}
			ResultSetMetaData metaData = rs.getMetaData();
			int  number = metaData.getColumnCount();
			for(int i=1,j=number; i<=j; i++ ){
				jsonResult = new JSONObject();
				columnName = metaData.getColumnName(i);
				jsonResult.put("code",columnName.toLowerCase());
				jsonResult.put("name",columnName.toLowerCase());
				listResult.add(jsonResult);
			}
            return  listResult;

		} catch(Exception e){
			_log.error("ComponentDataset：获得数据源关联的字段失败");
			return  null;
		}

	}

	//根据ds_code建立数据库连接
	public  Map<String,Object> getDBConnByDSCode(String dsCode){
		DAS das = this.getDAS();
		StringBuffer sbSql = new  StringBuffer();
		//获取根据dsCode建立数据库连接的信息、数据源的类型type、sql语句或存储过程的名称    method
		sbSql.append(" select  distinct   code,ip,port,server_id ,db_account,db_password,method,type  from( ");
		sbSql.append("   select    p_dbconn.code,p_dbconn.ip,p_dbconn.port,p_dbconn.server_id,p_dbconn.db_account, ");
		sbSql.append("             p_dbconn.db_password ,p_datasource.method,p_datasource.type ");
		sbSql.append("   from     p_datasource,p_dbconn   ");
		sbSql.append("   where    p_datasource.dbconn_code = p_dbconn.code  and  p_datasource.code = ? ");
		sbSql.append(" ) t ");
        System.out.println("dsCode："+dsCode);
		System.out.println(sbSql.toString());
		String columns[] = {"code","ip","port","server_id","db_account","db_password","method","type"};
		List<ResultDescriptor>  rdList = new ArrayList<ResultDescriptor>();
		rdList = getResultDescriptorList(columns,"t");
		Command   cmd = das.createCommand(sbSql.toString());
		cmd.setParameter(1 , dsCode);
		cmd.setResultDescriptors(rdList);
		DataObject dataObject = cmd.executeQuery();
		List<DataObject>  dataList = dataObject.getList("t");
		DataObject  data = null;
		if (dataList != null && dataList.size() > 0)
		   data = dataList.get(0);
        String  ip,port,server,user,password,method,type;
        Map<String,Object> mapResult = new HashMap<String,Object>();
	    try {
	    	if (data != null){
		    	ip = data.getString("ip");
		    	port = data.getString("port");
		    	server = data.getString("server_id");
		    	user = data.getString("db_account");
		    	password = data.getString("db_password");
		    	method  = data.getString("method");
		    	type = data.getString("type");
		    	System.out.println(type);
		    	System.out.println(method);
		    	Connection conn = null;
				//建立数据库连接
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
				conn = DriverManager.getConnection(url, user, password);
				if (conn==null) return null;
				mapResult.put("conn", conn);
				mapResult.put("type", type);
				mapResult.put("method", method);
				return  mapResult;
	    	}else{
	    		return null;
	    	}

		} catch ( Exception e) {
			_log.error("ComponentDataset：根据数据源建立数据库连接失败");
			return  null;
		}
	}

	//type为proc时获得存储过程参数
	public  List<JSONObject>  getProcParam(Connection conn,String procName){
		Statement  stm = null;
		ResultSet  rsSet = null;
		StringBuffer sbSql = new  StringBuffer();
		List<JSONObject> listResult =  new ArrayList<JSONObject>();
		sbSql.append(" select argument_name,data_type  from  user_arguments ");
		sbSql.append(" where  object_name = ");
		sbSql.append("'");
		sbSql.append(procName);
		sbSql.append("'");
		sbSql.append(" and  in_out = 'IN'");
		try {
			stm = conn.createStatement();
			rsSet = stm.executeQuery(sbSql.toString());
			JSONObject jsonTemp = null;
			while (rsSet.next()){
				jsonTemp = new JSONObject();
				System.out.println(rsSet.getString("argument_name"));
				System.out.println(rsSet.getString("data_type"));
				jsonTemp.put("argument_name",rsSet.getString("argument_name")) ;
				jsonTemp.put("data_type",rsSet.getString("data_type"));
				listResult.add(jsonTemp);
			}

			return  listResult;
		} catch (Exception e) {
			_log.error("ComponentDataset：获得存储过程参数失败");
			return null;
		}


	}

}
