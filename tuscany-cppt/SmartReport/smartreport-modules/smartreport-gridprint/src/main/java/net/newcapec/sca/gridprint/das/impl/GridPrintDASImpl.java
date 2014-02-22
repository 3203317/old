package net.newcapec.sca.gridprint.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.gridprint.GridPrint;
import net.newcapec.sca.gridprint.das.GridPrintDAS;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class GridPrintDASImpl implements GridPrintDAS
{
	private static final Logger gridPrintDASLogger = Logger.getLogger(GridPrintDASImpl.class);
	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
			this.defDBConnService = defDBConnService;
	}

	//默认的功能模块das配置文件
	private static String DAS_CONFIG = "/gridprint.xml";
	public DAS getDAS()
	{
		String path = "";
		path = this.getClass().getResource(DAS_CONFIG).getPath();
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
		return das;
	}

	public GridPrint getGridPrintById(Integer id) {
		DAS das = this.getDAS();
		Command command = das.getCommand("getGridPrintById");
		command.setParameter(1, id);
		DataObject root = command.executeQuery();
		List list = root.getList("p_gridprint");
		GridPrint gridprint = null;
		if(null != list && list.size() > 0 )
		{
			DataObject p_gridprintDO = ((DataObject)list.get(0));
			gridprint = this.translateDOTOGridPrint(p_gridprintDO);
		}

		return gridprint;
		}
	private GridPrint translateDOTOGridPrint(DataObject p_gridprintDO)
	{
		GridPrint gridprint = new GridPrint();
		gridprint.setCode(p_gridprintDO.get("code").toString());
		gridprint.setForm_code(p_gridprintDO.get("form_code").toString());
		gridprint.setTemp_code(p_gridprintDO.get("temp_code").toString());
		gridprint.setPrint_parm(p_gridprintDO.get("print_parm").toString());
			return gridprint;
		}
	public List<GridPrint> findGridPrintList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer beginId, Integer limitId) {

		String sql =
				"select * " +
				"  from (select code,form_code,temp_code,print_parm, " +
				"       rownum testnum " +
				"       from p_gridprint where 1=1 " ;
		if (filter != null)
		{
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
		sql = sql + " order by code asc ";
		gridPrintDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List list = this.getFilledResultDescriptionList_p_gridprint();
		command.setResultDescriptors(list);
//	    command.setParameter(1,domainId);
//	    command.setParameter(2,orgId);
		if(null != beginId)
		{
		command.setParameter(1,beginId);
		}
		if(null != limitId)
		{
		command.setParameter(2,beginId+limitId);
		}
		DataObject root = command.executeQuery();
		List<GridPrint> gridprintList = new ArrayList<GridPrint>();
		for(DataObject dObj : (List<DataObject>)root.getList("p_gridprint"))
		{
		GridPrint gridprint = this.translateDOTOGridPrint(dObj);
		gridprintList.add(gridprint);
		}

			return gridprintList;
		}
	public Boolean insertGridPrint(GridPrint gridprint) {
		Boolean rtn = true;
		String sql =
		"  insert into p_gridprint " +
		"  (code,form_code,temp_code,print_parm) " +
		"  values(?,?,?,?)";
		gridPrintDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);

		command.setParameter(1, gridprint.getCode());
		command.setParameter(2, gridprint.getForm_code());
		command.setParameter(3, gridprint.getTemp_code());
		command.setParameter(4, gridprint.getPrint_parm());
		try
		{
		command.execute();
		}
		catch (Exception e)
		{
		gridPrintDASLogger.debug(e.getMessage(),e);
		rtn = false;
		}
			return rtn;
	}
	public Boolean updateGridPrint(GridPrint gridprint) {
		Boolean rtn = true;
		DAS das = this.getDAS();
		Command command = das.getCommand("getGridPrintById");
		command.setParameter(1, gridprint.getCode());
		DataObject root = command.executeQuery();
		DataObject gridprintDO = root.getDataObject("p_gridprint[1]");
		if(null != gridprint.getCode())
		{
		gridprintDO.set("code",gridprint.getCode().toString());
		}
		if(null != gridprint.getForm_code())
		{
		gridprintDO.set("form_code",gridprint.getForm_code().toString());
		}
		if(null != gridprint.getTemp_code())
		{
		gridprintDO.set("temp_code",gridprint.getTemp_code().toString());
		}
		if(null != gridprint.getPrint_parm())
		{
		gridprintDO.set("print_parm",gridprint.getPrint_parm().toString());
		}

		try
		{
			das.applyChanges(root);
		}
		catch (Exception e)
		{
			rtn = false;
			gridPrintDASLogger.debug(e.getMessage(),e);
		}
		return rtn;
	}
	public Boolean delGridPrintById(Integer id) {
		Boolean rtn = true;
		String sql =
		"  delete p_gridprint where code = ?";
		gridPrintDASLogger.debug(sql);
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
			gridPrintDASLogger.debug(e.getMessage(),e);
		}
		return rtn;
	}
	public List getFilledResultDescriptionList_p_gridprint()
	{
		List list = new ArrayList();
		ResultDescriptor code = new ResultDescriptorImpl();
		code.setColumnName("code");
		code.setTableName("p_gridprint");
		code.setColumnIndex(0);
		code.setColumnType("commonj.sdo.String");
		list.add(code);
		ResultDescriptor form_code = new ResultDescriptorImpl();
		form_code.setColumnName("form_code");
		form_code.setTableName("p_gridprint");
		form_code.setColumnIndex(1);
		form_code.setColumnType("commonj.sdo.String");
		list.add(form_code);
		ResultDescriptor temp_code = new ResultDescriptorImpl();
		temp_code.setColumnName("temp_code");
		temp_code.setTableName("p_gridprint");
		temp_code.setColumnIndex(2);
		temp_code.setColumnType("commonj.sdo.String");
		list.add(temp_code);
		ResultDescriptor print_parm = new ResultDescriptorImpl();
		print_parm.setColumnName("print_parm");
		print_parm.setTableName("p_gridprint");
		print_parm.setColumnIndex(3);
		print_parm.setColumnType("commonj.sdo.String");
		list.add(print_parm);
		return list;
	}
	}
