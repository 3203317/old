package net.newcapec.sca.gridcondition.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.gridcondition.FieldPrep;
import net.newcapec.sca.gridcondition.GridCondition;
import net.newcapec.sca.gridcondition.das.GridConditionDAS;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class GridConditionDASImpl implements GridConditionDAS{

	private static final Logger gridConditionDASLogger = Logger.getLogger(GridConditionDASImpl.class);
	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
			this.defDBConnService = defDBConnService;
	}

	//默认的功能模块das配置文件
	private static String DAS_CONFIG = "/gridcondition.xml";
	public DAS getDAS()
	{
		String path = "";
		path = this.getClass().getResource(DAS_CONFIG).getPath();
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
		return das;
	}

	public GridCondition getGridConditionById(Integer id) {
		DAS das = this.getDAS();
		Command command = das.getCommand("getGridConditionById");
		command.setParameter(1, id);
		DataObject root = command.executeQuery();
		List<DataObject> list = root.getList("p_gridcondition");
		DataObject p_gridconditionDO = null;
		GridCondition gridcondition = null;
		if(null != list && list.size() > 0)
		{
			p_gridconditionDO = list.get(0);
			gridcondition = this.translateDOTOGridCondition(p_gridconditionDO);
		}

			return gridcondition;
	}
	/**
	 * 根据formcode 查询condition
	 */
	public List<GridCondition> getByFormCode(Integer domainId, Integer orgId, List<FilterParam> filter, Integer beginId, Integer limitId) {

		List<GridCondition> originalList = this.findGridConditionList(domainId, orgId, filter, beginId, limitId);

		for(GridCondition gridCondition : originalList)
		{
			FieldPrep fieldPrep = this.getFieldPrepById(Integer.valueOf(gridCondition.getField_name()));
			if(fieldPrep != null){
				gridCondition.setField_name_value(fieldPrep.getAlias());
				gridCondition.setField_name_name(fieldPrep.getName());
				gridCondition.setField_input_type(fieldPrep.getInput_type());
			}
			if(!"".equals(gridCondition.getParent_condition()))
			{
				FieldPrep parentFieldPrep = this.getFieldPrepById(Integer.valueOf(gridCondition.getParent_condition()));
				gridCondition.setParent_condition_value(parentFieldPrep.getAlias());
			}else{
				gridCondition.setParent_condition_value("");
			}
		}
		return originalList;
	}
	private GridCondition translateDOTOGridCondition(DataObject p_gridconditionDO)
	{
		GridCondition gridcondition = new GridCondition();
		gridcondition.setCode(p_gridconditionDO.get("code").toString());
		gridcondition.setForm_code(p_gridconditionDO.get("form_code").toString());
		gridcondition.setField_name(p_gridconditionDO.get("field_name").toString());
		gridcondition.setRelation(p_gridconditionDO.get("relation").toString());
		gridcondition.setParent_condition(null == p_gridconditionDO.get("parent_condition")?"":p_gridconditionDO.get("parent_condition").toString());
		gridcondition.setGroup_name(p_gridconditionDO.get("group_name").toString());
		gridcondition.setType(p_gridconditionDO.get("type").toString());
		gridcondition.setDefault_value(null == p_gridconditionDO.get("default_value")?"":p_gridconditionDO.get("default_value").toString());
		gridcondition.setCondition_name(null == p_gridconditionDO.get("condition_name")?"":p_gridconditionDO.get("condition_name").toString());
			return gridcondition;
		}
	public List<GridCondition> findGridConditionList(Integer domainId, Integer orgId,
				List<FilterParam> filter, Integer beginId, Integer limitId) {
//	    String sql =
//	 		"select * " +
//			"  from (select code,form_code,field_name,relation,parent_condition,group_name,type,default_value, " +
//			"       rownum testnum " +
//			"       from p_gridcondition) " +
//			"       where  domain_code = ?  " +
//			"       and user_unit_code = ? ";
		String sql =
				"select * \n" +
				"  from (select code,form_code,field_name,relation,parent_condition,group_name,type,default_value, condition_name,\n" +
				"       rownum testnum \n" +
				"       from p_gridcondition  where 1=1\n" ;
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
		sql = sql + " where testnum > ? \n";
		}
		if(null != limitId)
		{
		sql = sql + " and testnum <= ? \n";
		}
		sql = sql + " order by code asc \n";
		gridConditionDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List list = this.getFilledResultDescriptionList_p_gridcondition();
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
		List<GridCondition> gridconditionList = new ArrayList<GridCondition>();
		for(DataObject dObj : (List<DataObject>)root.getList("p_gridcondition"))
		{
		GridCondition gridcondition = this.translateDOTOGridCondition(dObj);
		gridconditionList.add(gridcondition);
		}

			return gridconditionList;
		}

	public Boolean insertGridCondition(GridCondition gridcondition) {
		Boolean rtn = true;
		String sql =
		"  insert into p_gridcondition " +
		"  (code,form_code,field_name,relation,parent_condition,group_name,type,default_value,condition_name) " +
		"  values(?,?,?,?,?,?,?,?,?)";
		gridConditionDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);

		command.setParameter(1, gridcondition.getCode());
		command.setParameter(2, gridcondition.getForm_code());
		command.setParameter(3, gridcondition.getField_name());
		command.setParameter(4, gridcondition.getRelation());
		command.setParameter(5, gridcondition.getParent_condition());
		command.setParameter(6, gridcondition.getGroup_name());
		command.setParameter(7, gridcondition.getType());
		command.setParameter(8, gridcondition.getDefault_value());
		command.setParameter(9, gridcondition.getCondition_name());
		try
		{
			command.execute();
		}
		catch (Exception e)
		{
			gridConditionDASLogger.debug(e.getMessage(),e);
			rtn = false;
		}
		return rtn;
	}
	public Boolean updateGridCondition(GridCondition gridcondition) {
			Boolean rtn = true;
			DAS das = this.getDAS();
			Command command = das.getCommand("getGridConditionById");
			command.setParameter(1, gridcondition.getCode());
			DataObject root = command.executeQuery();
			DataObject gridconditionDO = root.getDataObject("p_gridcondition[1]");
			if(null != gridcondition.getCode())
			{
			gridconditionDO.set("code",gridcondition.getCode().toString());
			}
			if(null != gridcondition.getForm_code())
			{
			gridconditionDO.set("form_code",gridcondition.getForm_code().toString());
			}
			if(null != gridcondition.getField_name())
			{
			gridconditionDO.set("field_name",gridcondition.getField_name().toString());
			}
			if(null != gridcondition.getRelation())
			{
			gridconditionDO.set("relation",gridcondition.getRelation().toString());
			}
			if(null != gridcondition.getParent_condition())
			{
			gridconditionDO.set("parent_condition",gridcondition.getParent_condition().toString());
			}
			if(null != gridcondition.getGroup_name())
			{
			gridconditionDO.set("group_name",gridcondition.getGroup_name().toString());
			}
			if(null != gridcondition.getType())
			{
			gridconditionDO.set("type",gridcondition.getType().toString());
			}
			if(null != gridcondition.getDefault_value())
			{
			gridconditionDO.set("default_value",gridcondition.getDefault_value().toString());
			}
			if(null != gridcondition.getCondition_name())
			{
			gridconditionDO.set("condition_name",gridcondition.getCondition_name().toString());
			}

			try
			{
				das.applyChanges(root);
			}
			catch (Exception e)
			{
				rtn = false;
				gridConditionDASLogger.debug(e.getMessage(),e);
			}
			return rtn;
		}
	public Boolean delGridConditionById(Integer id) {
		Boolean rtn = true;
		String sql =
		"  delete p_gridcondition where code = ?";
		gridConditionDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		command.setParameter(1, id);
		try
		{
			command.execute();
		}
		catch (Exception e)
		{
			rtn = false;
			gridConditionDASLogger.debug(e.getMessage(),e);
		}
		return rtn;
	}
	public FieldPrep getFieldPrepById(Integer id)
	{
		DAS das = this.getDAS();
		gridConditionDASLogger.debug("exe getFieldPrepById : "  + id);
		Command command = das.getCommand("getFieldPrepById");
		command.setParameter(1, id);
		DataObject root = command.executeQuery();
		List<DataObject> list = root.getList("p_fieldprep");
		DataObject p_fieldprepDO = null;
		FieldPrep fieldprep = null;
		if(null != list && list.size() > 0)
		{
			p_fieldprepDO = list.get(0);
			fieldprep = this.translateDOTOFieldPrep(p_fieldprepDO);
		}

		return fieldprep;
	}
	private FieldPrep translateDOTOFieldPrep(DataObject p_fieldprepDO)
	{
		FieldPrep fieldprep = new FieldPrep();
		fieldprep.setCode(null == p_fieldprepDO.get("code")?"":p_fieldprepDO.get("code").toString());
		fieldprep.setDs_code(null == p_fieldprepDO.get("ds_code")?"":p_fieldprepDO.get("ds_code").toString());
		fieldprep.setName(null == p_fieldprepDO.get("name")?"":p_fieldprepDO.get("name").toString());
		fieldprep.setAlias(null == p_fieldprepDO.get("alias")?"":p_fieldprepDO.get("alias").toString());
		fieldprep.setType(null == p_fieldprepDO.get("type")?"":p_fieldprepDO.get("type").toString());
		fieldprep.setInput_type(null == p_fieldprepDO.get("input_type")?"":p_fieldprepDO.get("input_type").toString());
		fieldprep.setRegexp(null == p_fieldprepDO.get("regexp")?"":p_fieldprepDO.get("regexp").toString());
		fieldprep.setMemo(null == p_fieldprepDO.get("memo")?"":p_fieldprepDO.get("memo").toString());
		return fieldprep;
	}

	public List getFilledResultDescriptionList_p_gridcondition()
	{
		List list = new ArrayList();
		ResultDescriptor code = new ResultDescriptorImpl();
		code.setColumnName("code");
		code.setTableName("p_gridcondition");
		code.setColumnIndex(0);
		code.setColumnType("commonj.sdo.String");
		list.add(code);
		ResultDescriptor form_code = new ResultDescriptorImpl();
		form_code.setColumnName("form_code");
		form_code.setTableName("p_gridcondition");
		form_code.setColumnIndex(1);
		form_code.setColumnType("commonj.sdo.String");
		list.add(form_code);
		ResultDescriptor field_name = new ResultDescriptorImpl();
		field_name.setColumnName("field_name");
		field_name.setTableName("p_gridcondition");
		field_name.setColumnIndex(2);
		field_name.setColumnType("commonj.sdo.String");
		list.add(field_name);
		ResultDescriptor relation = new ResultDescriptorImpl();
		relation.setColumnName("relation");
		relation.setTableName("p_gridcondition");
		relation.setColumnIndex(3);
		relation.setColumnType("commonj.sdo.String");
		list.add(relation);
		ResultDescriptor parent_condition = new ResultDescriptorImpl();
		parent_condition.setColumnName("parent_condition");
		parent_condition.setTableName("p_gridcondition");
		parent_condition.setColumnIndex(4);
		parent_condition.setColumnType("commonj.sdo.String");
		list.add(parent_condition);
		ResultDescriptor group_name = new ResultDescriptorImpl();
		group_name.setColumnName("group_name");
		group_name.setTableName("p_gridcondition");
		group_name.setColumnIndex(5);
		group_name.setColumnType("commonj.sdo.String");
		list.add(group_name);
		ResultDescriptor type = new ResultDescriptorImpl();
		type.setColumnName("type");
		type.setTableName("p_gridcondition");
		type.setColumnIndex(6);
		type.setColumnType("commonj.sdo.String");
		list.add(type);
		ResultDescriptor default_value = new ResultDescriptorImpl();
		default_value.setColumnName("default_value");
		default_value.setTableName("p_gridcondition");
		default_value.setColumnIndex(7);
		default_value.setColumnType("commonj.sdo.String");
		list.add(default_value);
		ResultDescriptor condition_name = new ResultDescriptorImpl();
		condition_name.setColumnName("condition_name");
		condition_name.setTableName("p_gridcondition");
		condition_name.setColumnIndex(8);
		condition_name.setColumnType("commonj.sdo.String");
		list.add(condition_name);

		return list;
	}
}
