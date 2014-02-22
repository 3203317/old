package net.newcapec.sca.gridcondition.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.diccode.DicItem;
import net.newcapec.sca.diccode.service.DicCodeService;
import net.newcapec.sca.fieldprep.FieldPrep;
import net.newcapec.sca.fieldprep.service.FieldPrepService;
import net.newcapec.sca.gridcondition.GridCondition;
import net.newcapec.sca.gridcondition.GridConditonDataCollection;
import net.newcapec.sca.gridcondition.das.GridConditionDAS;
import net.newcapec.sca.gridcondition.service.GridConditionService;
import net.newcapec.sca.gridview.GridView;
import net.newcapec.sca.gridview.service.GridViewService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class GridConditionServiceImpl implements GridConditionService {
	private static final Logger gridConditionServiceLogger = Logger.getLogger(GridConditionServiceImpl.class);
	private SessionService sessionService;
	private FieldPrepService fieldprepService;
	private DicCodeService dicCodeService;
	private GridViewService gridViewService;
	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private SequenceService sequenceService;

	@Reference(name = "sequenceService", required = true)
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}
	private GridConditionDAS gridConditionDAS;

	@Reference(name = "gridConditionDAS", required = true)
	public void setGridConditionDAS(GridConditionDAS gridConditionDAS) {
		this.gridConditionDAS = gridConditionDAS;
	}
	@Reference(name = "fieldprepService", required = true)
	public void setFieldprepService(FieldPrepService fieldprepService) {
		this.fieldprepService = fieldprepService;
	}
	@Reference(name = "dicCodeService", required = true)
	public void setDicCodeService(DicCodeService dicCodeService) {
		this.dicCodeService = dicCodeService;
	}
	@Reference
	public void setGridViewService(GridViewService gridViewService) {
		this.gridViewService = gridViewService;
	}

	public GridCondition getGridConditionById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		GridCondition gridCondition = gridConditionDAS.getGridConditionById(id);
		return gridCondition;
	}

	public List<GridCondition> findGridConditionList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limitId) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<GridCondition> list = gridConditionDAS.findGridConditionList(null, null, filter, beginId, limitId);
		return list;
	}

	public GridCondition insertGridCondition(String sessionId,
			GridCondition gridCondition) {
		Session session = sessionService.getSession(sessionId);
		ResultMsg resultMsg = new ResultMsg();
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		gridCondition.setCode(sequenceService.getNextValue("getMaxGridConditionID").toString());
		try
		{
			boolean sign = gridConditionDAS.insertGridCondition(gridCondition);
			if(sign)
			{
				resultMsg.setStatus(1);
			}
			else
			{
				resultMsg.setStatus(0);
			}
		}
		catch (Exception e)
		{
			gridConditionServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		gridCondition.setResultMsg(resultMsg);
		return gridCondition;
	}
	public Boolean insertGridConditions(String sessionId,
			List<GridCondition> gridConditions) {
		Session session = sessionService.getSession(sessionId);
		Boolean rtnSign = false;
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		try
		{
			for(GridCondition gridCondition : gridConditions)
			{
				gridCondition.setCode(sequenceService.getNextValue("getMaxGridConditionID").toString());
				gridConditionDAS.insertGridCondition(gridCondition);
			}
			rtnSign = true;
		}
		catch (Exception e)
		{
			gridConditionServiceLogger.debug(e.getMessage(),e);
		}
		return rtnSign;
	}
	/**
	 *根据前台回传信息更新gridCondition,如果有code则执行更新，如果没有执行插入
	 * @param sessionId
	 * @param gridConditions
	 * @return
	 */
	public Boolean updateGridConditions(String sessionId,List<GridCondition> gridConditions)
	{
		Session session = sessionService.getSession(sessionId);
		Boolean rtnSign = false;
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		try
		{
			for(GridCondition gridCondition : gridConditions)
			{
				if(0 != Integer.valueOf(gridCondition.getCode()))
				{
					gridConditionDAS.updateGridCondition(gridCondition);
				}
				else
				{
					gridCondition.setCode(sequenceService.getNextValue("getMaxGridConditionID").toString());
					gridConditionDAS.insertGridCondition(gridCondition);
				}
			}
			rtnSign = true;
		}
		catch (Exception e)
		{
			gridConditionServiceLogger.debug(e.getMessage(),e);
		}
		return rtnSign;
	}
	public GridCondition updateGridCondition(String sessionId,
			GridCondition gridCondition) {
		Session session = sessionService.getSession(sessionId);
		ResultMsg resultMsg = new ResultMsg();
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		try
		{
			boolean sign = gridConditionDAS.updateGridCondition(gridCondition);
			if(sign)
			{
				resultMsg.setStatus(1);
			}
			else
			{
				resultMsg.setStatus(0);
			}
		}
		catch (Exception e)
		{
			gridConditionServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		gridCondition.setResultMsg(resultMsg);
		return gridCondition;
	}

	public GridCondition delGridConditionById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		ResultMsg resultMsg = new ResultMsg();
		GridCondition gridCondition = new GridCondition();
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		try
		{
			boolean sign = gridConditionDAS.delGridConditionById(id);
			if(sign)
			{
				resultMsg.setStatus(1);
			}
			else
			{
				resultMsg.setStatus(0);
			}
		}
		catch (Exception e)
		{
			gridConditionServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		gridCondition.setResultMsg(resultMsg);
		return gridCondition;
	}
	/**
	 * true 有效， false 失效
	 * @param session
	 * @return
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
			gridConditionServiceLogger.debug(e.getMessage(),e);
			sessionVaild = false;
		}
		return sessionVaild;
	}
	public List<GridCondition> getGridConditionByFormCode(String sessionId,
			Integer form_code) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<FilterParam> filterList = new ArrayList<FilterParam>();
		FilterParam param = new FilterParam();
		param.setField("form_code");
		param.setLogical("=");
		param.setRelation("and");
		param.setValue(form_code.toString());
		filterList.add(param);
		List<GridCondition> list = this.gridConditionDAS.getByFormCode(1, 1, filterList, null, null);
		return list;
	}
	/**
	 *zpf add
	 *获取外观定制、字段、数据字段的数据集合
	 */
	public GridConditonDataCollection getGridConditonDataCollection(
			String sessionId, Integer ds_code, Integer form_code,List<FilterParam> filter) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		GridConditonDataCollection collection = new GridConditonDataCollection();
		gridConditionServiceLogger.debug("getGridConditonDataCollection begin");
		GridView gridView = gridViewService.getGridViewById(sessionId, form_code);
		String __sql = "select t.* from (select a.code,a.form_code,a.toolbuttons,a.condition_groups,a.condition_trees,a.fields from p_gridview a) t where t.form_code="+form_code;

		gridConditionServiceLogger.debug(__sql);

		List<FieldPrep> fieldPrepList = fieldprepService.findFieldListByDscode(sessionId, ds_code);
		__sql = "select t.* from (select a.code,a.ds_code,a.name,a.alias,a.type,a.input_type,a.regexp,a.memo from p_fieldprep a) t where t.ds_code="+ds_code;
		gridConditionServiceLogger.debug("fieldPrepList: "+__sql);
		if(filter.size() != 0){
			List<DicItem> dicList = dicCodeService.findDicItemListByCondition(sessionId,filter);
			__sql = " select code, code_dictionary_name, data_key, data_value, key_code,  info "+
			"   from z_code_dictionary_data a  where 1=1 ";
				if (filter != null) {
					for (FilterParam __param_3 : filter) {
						__sql += " " + __param_3.getRelation() + " a."
								+ __param_3.getField() + " " + __param_3.getLogical()
								+ " " + __param_3.getValue();
					}
				}

				__sql += "  order by code  asc";
				gridConditionServiceLogger.debug("dicList+ :" + __sql);
			collection.setDis(dicList);
		}
		List<GridCondition> conditon = this.getGridConditionByFormCode(sessionId, form_code);
		gridConditionServiceLogger.debug("getGridConditonDataCollection end");
		collection.setGridView(gridView);
		collection.setField(fieldPrepList);
		collection.setGridCondition(conditon);
		return collection;
	}
}
