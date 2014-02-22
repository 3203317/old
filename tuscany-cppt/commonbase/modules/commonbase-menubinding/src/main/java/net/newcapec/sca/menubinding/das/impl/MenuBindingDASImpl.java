package net.newcapec.sca.menubinding.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.menubinding.MenuBinding;
import net.newcapec.sca.menubinding.das.MenuBindingDAS;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class MenuBindingDASImpl implements MenuBindingDAS{

	private static final Logger menuBindingDASLogger = Logger.getLogger(MenuBindingDASImpl.class);
	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
			this.defDBConnService = defDBConnService;
	}

	//默认的功能模块das配置文件
	private static String DAS_CONFIG = "/menubind.xml";
	public DAS getDAS()
	{
		String path = "";
		path = this.getClass().getResource(DAS_CONFIG).getPath();
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
		return das;
	}

	public MenuBinding getMenuBindingById(Integer id) {

		DAS das = this.getDAS();
		Command command = das.getCommand("getMenuBindingById");
		command.setParameter(1, id);
		DataObject root = command.executeQuery();
		List<DataObject> list = root.getList("p_menubind");
		DataObject p_menubindDO = null;
		MenuBinding menubinding = null;
		if(null != list && list.size() > 0)
		{
		p_menubindDO = list.get(0);
		menubinding = this.translateDOTOMenuBinding(p_menubindDO);
		}

			return menubinding;
		}
	private MenuBinding translateDOTOMenuBinding(DataObject p_menubindDO)
	{
		MenuBinding menubinding = new MenuBinding();
		menubinding.setCode(p_menubindDO.get("code").toString());
		menubinding.setMenu_code(p_menubindDO.get("menu_code").toString());
		menubinding.setType(p_menubindDO.get("type").toString());
		menubinding.setForm_code(p_menubindDO.get("form_code").toString());
		menubinding.setLimit(p_menubindDO.get("limit").toString());
		menubinding.setMemo(p_menubindDO.get("memo").toString());
			return menubinding;
		}
	public List<MenuBinding> findMenuBindingList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer beginId, Integer limitId) {
		String sql =
			"select * " +
			"  from (select code,menu_code,type,form_code,limit,memo, " +
			"       rownum testnum " +
			"       from p_menubind " +
			"       where  1=1  " ;
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
		menuBindingDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List list = this.getFilledResultDescriptionList_p_menubind();
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
		List<MenuBinding> menubindingList = new ArrayList<MenuBinding>();
		List<DataObject> doList = (List<DataObject>)root.getList("p_menubind");
		for(DataObject dObj : doList)
		{
			MenuBinding menubinding = this.translateDOTOMenuBinding(dObj);
			menubindingList.add(menubinding);
		}

			return menubindingList;
		}
	public Boolean insertMenuBinding(MenuBinding menubinding) {
		Boolean rtn = true;
		String sql =
		"  insert into p_menubind " +
		"  (code,menu_code,type,form_code,limit,memo) " +
		"  values(?,?,?,?,?,?)";
		menuBindingDASLogger.debug(sql);
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);

		command.setParameter(1, menubinding.getCode());
		command.setParameter(2, menubinding.getMenu_code());
		command.setParameter(3, menubinding.getType());
		command.setParameter(4, menubinding.getForm_code());
		command.setParameter(5, menubinding.getLimit());
		command.setParameter(6, menubinding.getMemo());
		try
		{
			command.execute();
		}
		catch (Exception e)
		{
			menuBindingDASLogger.debug(e.getMessage(),e);
			rtn = false;
		}
			return rtn;
	}
	public Boolean updateMenuBinding(MenuBinding menubinding)
	{
		Boolean rtn = true;
		DAS das = this.getDAS();
		Command command = das.getCommand("getMenuBindingById");
		command.setParameter(1, menubinding.getCode());
		DataObject root = command.executeQuery();
		DataObject menubindingDO = root.getDataObject("p_menubind[1]");
		if(null != menubinding.getCode())
		{
		menubindingDO.set("code",menubinding.getCode().toString());
		}
		if(null != menubinding.getMenu_code())
		{
		menubindingDO.set("menu_code",menubinding.getMenu_code().toString());
		}
		if(null != menubinding.getType())
		{
		menubindingDO.set("type",menubinding.getType().toString());
		}
		if(null != menubinding.getForm_code())
		{
		menubindingDO.set("form_code",menubinding.getForm_code().toString());
		}
		if(null != menubinding.getLimit())
		{
		menubindingDO.set("limit",menubinding.getLimit().toString());
		}
		if(null != menubinding.getMemo())
		{
		menubindingDO.set("memo",menubinding.getMemo().toString());
		}

		try
		{
			das.applyChanges(root);
		}
		catch (Exception e)
		{
			rtn = false;
			menuBindingDASLogger.debug(e.getMessage(),e);
		}
		finally
		{

		}
		return rtn;
	}
	public Boolean delMenuBindingById(Integer id)
	{
		Boolean rtn = true;
		String sql =
		"  delete p_menubind where code = ?";

		menuBindingDASLogger.debug(sql);
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
			menuBindingDASLogger.debug(e.getMessage(),e);
		}
			return rtn;
		}
	public List getFilledResultDescriptionList_p_menubind()
	{
		List list = new ArrayList();
		ResultDescriptor code = new ResultDescriptorImpl();
		code.setColumnName("code");
		code.setTableName("p_menubind");
		code.setColumnIndex(0);
		code.setColumnType("commonj.sdo.String");
		list.add(code);
		ResultDescriptor menu_code = new ResultDescriptorImpl();
		menu_code.setColumnName("menu_code");
		menu_code.setTableName("p_menubind");
		menu_code.setColumnIndex(1);
		menu_code.setColumnType("commonj.sdo.String");
		list.add(menu_code);
		ResultDescriptor type = new ResultDescriptorImpl();
		type.setColumnName("type");
		type.setTableName("p_menubind");
		type.setColumnIndex(2);
		type.setColumnType("commonj.sdo.String");
		list.add(type);
		ResultDescriptor form_code = new ResultDescriptorImpl();
		form_code.setColumnName("form_code");
		form_code.setTableName("p_menubind");
		form_code.setColumnIndex(3);
		form_code.setColumnType("commonj.sdo.String");
		list.add(form_code);
		ResultDescriptor limit = new ResultDescriptorImpl();
		limit.setColumnName("limit");
		limit.setTableName("p_menubind");
		limit.setColumnIndex(4);
		limit.setColumnType("commonj.sdo.String");
		list.add(limit);
		ResultDescriptor memo = new ResultDescriptorImpl();
		memo.setColumnName("memo");
		memo.setTableName("p_menubind");
		memo.setColumnIndex(5);
		memo.setColumnType("commonj.sdo.String");
		list.add(memo);
		return list;
	}
	public Boolean delMenuBindingByIds(Integer[] ids)
	{
		Boolean rtnSign = true;
		try
		{
			for(Integer id : ids)
			{
				this.delMenuBindingById(Integer.valueOf(id));
			}
		}
		catch (Exception e)
		{
			rtnSign = false;
			menuBindingDASLogger.debug(e.getMessage(),e);
		}
		return rtnSign;
	}

	public DojoListData findMenuBindingList (DojoListParam param)
	{

		List<MenuBinding> list = this.findMenuBindingList(1, 1, param.getFilter(), param.getBegin(), param.getLimit());
		DojoListData dld = new DojoListData();
		dld.setIdentifier("code");
		dld.setItems(list.toArray());
		dld.setLabel("name");
		dld.setNumRows(this.getMenuBindingListRowCount(1, 1, param.getFilter()));
		return dld;
	}

	public Integer getMenuBindingListRowCount(Integer domainId, Integer orgId, List<FilterParam> filter)
	{
		String sql = "select count(1) code from p_menubind " +
		"  where 1 =1  \n" ;
		if (filter != null) {
			for (FilterParam paramItem : filter) {
				sql += " " + paramItem.getRelation() + " "
						+ paramItem.getField() + " " + paramItem.getLogical()
						+ " " + paramItem.getValue();
			}
		}
		DAS das = this.getDAS();
		Command command = das.createCommand(sql);
		List<ResultDescriptor> list = new ArrayList<ResultDescriptor>();
		ResultDescriptor code = new ResultDescriptorImpl();
		code.setColumnName("code");
		code.setTableName("p_menubind");
		code.setColumnIndex(0);
		code.setColumnType("commonj.sdo.String");
		list.add(code);
		command.setResultDescriptors(list);

		Integer rowCount = Integer.valueOf(command.executeQuery().getDataObject("p_menubind[1]").getString("code"));

		return rowCount;
	}



}
