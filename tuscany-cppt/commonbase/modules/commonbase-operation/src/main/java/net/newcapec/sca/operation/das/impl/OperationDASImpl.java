package net.newcapec.sca.operation.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.operation.Operation;
import net.newcapec.sca.operation.das.OperationDAS;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class OperationDASImpl implements OperationDAS{

	private static final Logger operationDASLogger = Logger.getLogger(OperationDASImpl.class);
	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
			this.defDBConnService = defDBConnService;
	}

	//默认的功能模块das配置文件
	private static String DAS_CONFIG = "/operation.xml";
	public DAS getDAS()
	{
		String path = "";
		path = this.getClass().getResource(DAS_CONFIG).getPath();
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
		return das;
	}


	public List<Operation> findOperationListByType(Integer typeId)
	{
		String sql =
			"select \n"  +
			"	code,\n"  +
			"	user_unit_code,\n"  +
			"	uuid,\n"  +
			"	name,\n"  +
			"	resource_type_code,\n"  +
			"	info,\n"  +
			"	create_user_account_id,\n"  +
			"	create_date,sortid,\n"  +
			"	ver\n" +
			"       from p_resource_operate \n"  +
			"       where  resource_type_code = ?  " ;
		operationDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List list = this.getFilledResultDescriptionList_p_resource_operate();
		command.setResultDescriptors(list);
		command.setParameter(1, typeId);
		DataObject root = command.executeQuery();
		List<Operation> operationList = new ArrayList<Operation>();
		List<DataObject> doList = (List<DataObject>)root.getList("p_resource_operate");
		for(DataObject dObj : doList)
		{
			Operation operation = this.translateDOTOOperation(dObj);
			operationList.add(operation);
		}

		return operationList;
	}
	public Operation getOperationById(Integer id)
	{
		DAS das = this.getDAS();
		Command command = das.getCommand("getOperationById");
		command.setParameter(1, id);
		DataObject root = command.executeQuery();
		List<DataObject> list = root.getList("p_resource_operate");
		DataObject p_resource_operateDO = null;
		Operation operation = null;
		if(null != list && list.size() > 0)
		{
			p_resource_operateDO = list.get(0);
			operation = this.translateDOTOOperation(p_resource_operateDO);
		}

		return operation;
	}
	private Operation translateDOTOOperation(DataObject p_resource_operateDO)
	{
		Operation operation = new Operation();
		operation.setCode(null == p_resource_operateDO.get("code")?"":p_resource_operateDO.get("code").toString());
		operation.setUser_unit_code(null == p_resource_operateDO.get("user_unit_code")?"":p_resource_operateDO.get("user_unit_code").toString());
		operation.setUuid(null == p_resource_operateDO.get("uuid")?"":p_resource_operateDO.get("uuid").toString());
		operation.setName(null == p_resource_operateDO.get("name")?"":p_resource_operateDO.get("name").toString());
		operation.setResource_type_code(null == p_resource_operateDO.get("resource_type_code")?"":p_resource_operateDO.get("resource_type_code").toString());
		operation.setInfo(null == p_resource_operateDO.get("info")?"":p_resource_operateDO.get("info").toString());
		operation.setCreate_user_account_id(null == p_resource_operateDO.get("create_user_account_id")?"":p_resource_operateDO.get("create_user_account_id").toString());
		operation.setCreate_date(null == p_resource_operateDO.get("create_date")?"":p_resource_operateDO.get("create_date").toString());
		operation.setSortid(null == p_resource_operateDO.get("sortid")?"":p_resource_operateDO.get("sortid").toString());
		operation.setVer(null == p_resource_operateDO.get("ver")?"":p_resource_operateDO.get("ver").toString());
		return operation;
	}
	public Integer getOperationListRowCount(Integer domainId, Integer orgId, List<FilterParam> filter)
	{
		String sql = "select count(1) code from p_resource_operate " +
					"  where 1 =1  \n" +
					" and user_unit_code = ? \n";
		if (filter != null) {
			for (FilterParam paramItem : filter) {
				sql += " " + paramItem.getRelation() + " "
						+ paramItem.getField() + " " + paramItem.getLogical()
						+ " " + paramItem.getValue();
			}
		}
		operationDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List<ResultDescriptor> list = new ArrayList<ResultDescriptor>();
		ResultDescriptor code = new ResultDescriptorImpl();
		code.setColumnName("code");
		code.setTableName("p_resource_operate");
		code.setColumnIndex(0);
		code.setColumnType("commonj.sdo.String");
		list.add(code);
		command.setResultDescriptors(list);

		command.setParameter(1,orgId);

		return Integer.valueOf(command.executeQuery().getDataObject("p_resource_operate[1]").getString("code"));
	}

	public List<SelectItem> findSelectItemForResourceType()
	{
		String sql =
			" select code , name from p_resource_type";
		operationDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List<ResultDescriptor> list = new ArrayList<ResultDescriptor>();
		ResultDescriptor code = new ResultDescriptorImpl();
		code.setColumnName("code");
		code.setTableName("p_resource_type");
		code.setColumnIndex(0);
		code.setColumnType("commonj.sdo.String");
		list.add(code);

		ResultDescriptor name = new ResultDescriptorImpl();
		name.setColumnName("name");
		name.setTableName("p_resource_type");
		name.setColumnIndex(1);
		name.setColumnType("commonj.sdo.String");
		list.add(name);
		command.setResultDescriptors(list);

		List<DataObject> doList = (List<DataObject>)command.executeQuery().getList("p_resource_type");

		List<SelectItem> rtnList = new ArrayList<SelectItem>();

		if(doList != null && doList.size() >0)
		{
			for (DataObject doItem : doList)
			{
				SelectItem item = new SelectItem();
				item.setId(doItem.getString("code"));
				item.setName(doItem.getString("name"));
				rtnList.add(item);
			}
		}
		return rtnList;
	}
	public List<Operation> findOperationList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer beginId, Integer limitId) {
		String sql =
			"select * \n" +
			"  from (select code,user_unit_code,uuid,name,resource_type_code,info,create_user_account_id,to_char(create_date,'yyyy-mm-dd hh24:mi:ss') create_date ,sortid,ver, \n" +
			"       rownum testnum \n" +
			"       from p_resource_operate \n" +
			"       where  1 = 1  \n" +
			" 		and user_unit_code = ? \n";
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
		sql = sql + " and testnum > ? \n";
		}
		if(null != limitId)
		{
		sql = sql + " and testnum <= ? \n";
		}
		sql = sql + " order by code asc \n";
		DAS das = this.getDAS();
		operationDASLogger.debug(sql);
		Command command = das.createCommand(sql);
		List list = this.getFilledResultDescriptionList_p_resource_operate();
		command.setParameter(1,orgId);
		command.setResultDescriptors(list);
		if(null != beginId)
		{
		command.setParameter(2,beginId);
		}
		if(null != limitId)
		{
		command.setParameter(3,beginId+limitId);
		}
		DataObject root = command.executeQuery();
		List<Operation> operationList = new ArrayList<Operation>();
		List<DataObject> doList = (List<DataObject>)root.getList("p_resource_operate");
		for(DataObject dObj : doList)
		{
		Operation operation = this.translateDOTOOperation(dObj);
		operationList.add(operation);
		}

		return operationList;
	}

	public List<Operation> findDetailOperationList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer beginId, Integer limitId) {
		String sql =
			"select * \n" +
			"  from (select code,user_unit_code,uuid,name,(select name from p_resource_type where code = resource_type_code) resource_type_code,info,create_user_account_id,to_char(create_date,'yyyy-mm-dd hh24:mi:ss') create_date ,sortid,ver, \n" +
			"       rownum testnum \n" +
			"       from p_resource_operate \n" +
			"       where  1 = 1  \n" +
			" 		and user_unit_code = ? \n";
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
		sql = sql + " and testnum > ? \n";
		}
		if(null != limitId)
		{
		sql = sql + " and testnum <= ? \n";
		}
		sql = sql + " order by code asc \n";
		operationDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List list = this.getFilledResultDescriptionList_p_resource_operate();
		command.setParameter(1,orgId);
		command.setResultDescriptors(list);
		if(null != beginId)
		{
		command.setParameter(2,beginId);
		}
		if(null != limitId)
		{
		command.setParameter(3,beginId+limitId);
		}
		DataObject root = command.executeQuery();
		List<Operation> operationList = new ArrayList<Operation>();
		List<DataObject> doList = (List<DataObject>)root.getList("p_resource_operate");
		for(DataObject dObj : doList)
		{
		Operation operation = this.translateDOTOOperation(dObj);
		operationList.add(operation);
		}

		return operationList;
	}
	public Boolean insertOperation(Operation operation) {
		Boolean rtn = true;
		String sql =
		"  insert into p_resource_operate " +
		"  (code,user_unit_code,uuid,name,resource_type_code,info,create_user_account_id,create_date,sortid,ver) " +
		"  values(?,?,?,?,?,?,?,sysdate,?,?)";
		operationDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);

		command.setParameter(1, null ==operation.getCode()?"":operation.getCode());
		command.setParameter(2, null ==operation.getUser_unit_code()?"":operation.getUser_unit_code());
		command.setParameter(3, null ==operation.getUuid()?"":operation.getUuid());
		command.setParameter(4, null ==operation.getName()?"":operation.getName());
		command.setParameter(5, null ==operation.getResource_type_code()?"":operation.getResource_type_code());
		command.setParameter(6, null ==operation.getInfo()?"":operation.getInfo());
		command.setParameter(7, null ==operation.getCreate_user_account_id()?"":operation.getCreate_user_account_id());
		command.setParameter(8, null ==operation.getSortid()?"":operation.getSortid());
		command.setParameter(9, null ==operation.getVer()?"":operation.getVer());
		try
		{
			command.execute();
		}
		catch (Exception e)
		{
			operationDASLogger.debug(e.getMessage(),e);
			rtn = false;
		}
		finally
		{
			das.releaseResources();
		}
		return rtn;
	}
	public Boolean updateOperation(Operation operation) {
		Boolean rtn = true;
		DAS das = this.getDAS();
		Command command = das.getCommand("getOperationById");
		command.setParameter(1, operation.getCode());
		DataObject root = command.executeQuery();
		DataObject operationDO = root.getDataObject("p_resource_operate[1]");
		if(null != operation.getCode())
		{
			operationDO.set("code",operation.getCode().toString());
		}
		if(null != operation.getUser_unit_code())
		{
			operationDO.set("user_unit_code",operation.getUser_unit_code().toString());
		}
		if(null != operation.getUuid())
		{
			operationDO.set("uuid",operation.getUuid().toString());
		}
		if(null != operation.getName())
		{
			operationDO.set("name",operation.getName().toString());
		}
		if(null != operation.getResource_type_code())
		{
			operationDO.set("resource_type_code",operation.getResource_type_code().toString());
		}
		if(null != operation.getInfo())
		{
			operationDO.set("info",operation.getInfo().toString());
		}
		if(null != operation.getCreate_user_account_id())
		{
			operationDO.set("create_user_account_id",operation.getCreate_user_account_id().toString());
		}
		if(null != operation.getCreate_date())
		{
			operationDO.set("create_date",operation.getCreate_date().toString());
		}
		if(null != operation.getSortid())
		{
			operationDO.set("sortid",operation.getSortid().toString());
		}
		if(null != operation.getVer())
		{
			operationDO.set("ver",operation.getVer().toString());
		}

		try
		{
			das.applyChanges(root);
		}
		catch (Exception e)
		{
			rtn = false;
			operationDASLogger.debug(e.getMessage(),e);
		}
		return rtn;
	}
	public Boolean delOperationById(Integer id) {
		Boolean rtn = true;
		String sql =
		"  delete p_resource_operate where code = ?";
		DAS das = this.getDAS();
		operationDASLogger.debug(sql);
		Command command = das.createCommand(sql);
		command.setParameter(1, id);
		try
		{
			command.execute();
		}
		catch (Exception e)
		{
			operationDASLogger.debug(e.getMessage(),e);
			rtn = false;
		}
		return rtn;
	}
	public List getFilledResultDescriptionList_p_resource_operate()
	{
		List list = new ArrayList();
		ResultDescriptor code = new ResultDescriptorImpl();
		code.setColumnName("code");
		code.setTableName("p_resource_operate");
		code.setColumnIndex(0);
		code.setColumnType("commonj.sdo.String");
		list.add(code);
		ResultDescriptor user_unit_code = new ResultDescriptorImpl();
		user_unit_code.setColumnName("user_unit_code");
		user_unit_code.setTableName("p_resource_operate");
		user_unit_code.setColumnIndex(1);
		user_unit_code.setColumnType("commonj.sdo.String");
		list.add(user_unit_code);
		ResultDescriptor uuid = new ResultDescriptorImpl();
		uuid.setColumnName("uuid");
		uuid.setTableName("p_resource_operate");
		uuid.setColumnIndex(2);
		uuid.setColumnType("commonj.sdo.String");
		list.add(uuid);
		ResultDescriptor name = new ResultDescriptorImpl();
		name.setColumnName("name");
		name.setTableName("p_resource_operate");
		name.setColumnIndex(3);
		name.setColumnType("commonj.sdo.String");
		list.add(name);
		ResultDescriptor resource_type_code = new ResultDescriptorImpl();
		resource_type_code.setColumnName("resource_type_code");
		resource_type_code.setTableName("p_resource_operate");
		resource_type_code.setColumnIndex(4);
		resource_type_code.setColumnType("commonj.sdo.String");
		list.add(resource_type_code);
		ResultDescriptor info = new ResultDescriptorImpl();
		info.setColumnName("info");
		info.setTableName("p_resource_operate");
		info.setColumnIndex(5);
		info.setColumnType("commonj.sdo.String");
		list.add(info);
		ResultDescriptor create_user_account_id = new ResultDescriptorImpl();
		create_user_account_id.setColumnName("create_user_account_id");
		create_user_account_id.setTableName("p_resource_operate");
		create_user_account_id.setColumnIndex(6);
		create_user_account_id.setColumnType("commonj.sdo.String");
		list.add(create_user_account_id);
		ResultDescriptor create_date = new ResultDescriptorImpl();
		create_date.setColumnName("create_date");
		create_date.setTableName("p_resource_operate");
		create_date.setColumnIndex(7);
		create_date.setColumnType("commonj.sdo.String");
		list.add(create_date);
		ResultDescriptor sortid = new ResultDescriptorImpl();
		sortid.setColumnName("sortid");
		sortid.setTableName("p_resource_operate");
		sortid.setColumnIndex(8);
		sortid.setColumnType("commonj.sdo.String");
		list.add(sortid);
		ResultDescriptor ver = new ResultDescriptorImpl();
		ver.setColumnName("ver");
		ver.setTableName("p_resource_operate");
		ver.setColumnIndex(9);
		ver.setColumnType("commonj.sdo.String");
		list.add(ver);
		return list;
	}
}
