package net.newcapec.sca.gridprint.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.gridcondition.GridCondition;
import net.newcapec.sca.gridcondition.service.GridConditionService;
import net.newcapec.sca.gridprint.GridFormDataCollection;
import net.newcapec.sca.gridprint.GridPrint;
import net.newcapec.sca.gridprint.das.GridPrintDAS;
import net.newcapec.sca.gridprint.service.GridPrintService;
import net.newcapec.sca.gridview.GridView;
import net.newcapec.sca.gridview.service.GridViewService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class GridPrintServiceImpl implements GridPrintService {
	private static final Logger gridPrintServiceLogger = Logger.getLogger(GridPrintServiceImpl.class);
	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private SequenceService sequenceService;

	@Reference(name = "sequenceService", required = true)
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}
	private GridPrintDAS gridPrintDAS;

	@Reference(name = "gridPrintDAS", required = true)
	public void setGridPrintDAS(GridPrintDAS gridPrintDAS) {
		this.gridPrintDAS = gridPrintDAS;
	}

	private  GridViewService gridViewService;
	@Reference(name = "gridViewService", required = true)
	public void setGridViewService(GridViewService gridViewService) {
		this.gridViewService = gridViewService;
	}
	private GridConditionService  gridConditionService;
	@Reference(name = "gridConditionService", required = true)
	public void setGridConditionService(GridConditionService gridConditionService) {
		this.gridConditionService =  gridConditionService;
	}

	public GridPrint getGridPrintById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		GridPrint gridPrint = gridPrintDAS.getGridPrintById(id);
		return gridPrint;
	}

	public List<GridPrint> findGridPrintList(String sessionId,
			Integer resourceId, List<FilterParam> filter,  Integer beginId, Integer limitId) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<GridPrint> list= gridPrintDAS.findGridPrintList(1, 1, filter, beginId, limitId);
		return list;
	}
	public GridPrint insertGridPrint(String sessionId, GridPrint gridPrint) {
		Session session = sessionService.getSession(sessionId);
		ResultMsg resultMsg = new ResultMsg();
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		try
		{
			gridPrint.setCode(sequenceService.getNextValue("getMaxGridPrintID").toString());
			boolean sign = gridPrintDAS.insertGridPrint(gridPrint);
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
			resultMsg.setStatus(0);
			gridPrintServiceLogger.debug(e.getMessage(),e);
		}
		gridPrint.setResultMsg(resultMsg);
		return gridPrint;
	}

	public GridPrint updateGridPrint(String sessionId, GridPrint gridPrint) {
		Session session = sessionService.getSession(sessionId);
		ResultMsg resultMsg = new ResultMsg();
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		try
		{
			boolean sign = gridPrintDAS.updateGridPrint(gridPrint);
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
			resultMsg.setStatus(0);
			gridPrintServiceLogger.debug(e.getMessage(),e);
		}
		gridPrint.setResultMsg(resultMsg);
		return gridPrint;
	}

	public GridPrint delGridPrintById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		GridPrint gridPrint = new GridPrint();
		ResultMsg resultMsg = new ResultMsg();

		if(!this.isSessionVaild(session))
		{
			return null;
		}
		try
		{
			boolean sign = gridPrintDAS.delGridPrintById(id);
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
			resultMsg.setStatus(0);
			gridPrintServiceLogger.debug(e.getMessage(),e);
		}
		gridPrint.setResultMsg(resultMsg);
		return gridPrint;
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
			gridPrintServiceLogger.debug(e.getMessage(),e);
			sessionVaild = false;
		}
		return sessionVaild;
	}

	public GridFormDataCollection getGridFormDataCollection(String sessionId,
			Integer form_code) {
		// TODO Auto-generated method stub
		GridFormDataCollection gridFormDataCollection = new GridFormDataCollection();

		GridView gridView  = gridViewService.getGridViewById(sessionId,form_code);
		List<GridCondition> gridConditionList = new ArrayList<GridCondition>();
		gridConditionList = gridConditionService.getGridConditionByFormCode(sessionId,form_code);
		if (gridView != null)
			gridFormDataCollection.setGridView(gridView);
		if (gridConditionList != null)
		gridFormDataCollection.setGridConditionList(gridConditionList);

		return gridFormDataCollection;
	}
}
