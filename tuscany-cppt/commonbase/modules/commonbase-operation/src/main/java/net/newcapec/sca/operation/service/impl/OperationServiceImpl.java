package net.newcapec.sca.operation.service.impl;

import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.operation.Operation;
import net.newcapec.sca.operation.das.OperationDAS;
import net.newcapec.sca.operation.service.OperationService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class OperationServiceImpl implements OperationService {

	private static final Logger operationServiceLogger = Logger.getLogger(OperationServiceImpl.class);
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

	private OperationDAS operationDAS;

	@Reference(name = "operationDAS", required = true)
	public void setOperationDAS(OperationDAS operationDAS) {
		this.operationDAS = operationDAS;
	}

	public Operation getOperationById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Operation operation = operationDAS.getOperationById(id);
		return operation;
	}

	public List<Operation> findOperationListByType(String sessionId,
			Integer typeId) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}

		List<Operation> list = operationDAS.findOperationListByType(typeId);
		return list;
	}

	public List<Operation> findOperationList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limit) {
//		Session session = sessionService.getSession(sessionId);
//		if(!this.isSessionVaild(session))
//		{
//			return null;
//		}
		List<Operation> list = operationDAS.findOperationList(1,
				1, filter, beginId, limit);
		return list;
	}

	public List<SelectItem> findSelectItemForResourceType(String sessionId)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		return this.operationDAS.findSelectItemForResourceType();
	}
	public DojoListData findOperationDojoList(DojoListParam param)
	{
		List<Operation> list = this.operationDAS.findDetailOperationList(1, 1, param.getFilter(), param.getBegin(), param.getLimit());
		DojoListData dld = new DojoListData();
		dld.setIdentifier("code");
		dld.setItems(list.toArray());
		dld.setLabel("name");
		dld.setNumRows(this.operationDAS.getOperationListRowCount(1, 1, param.getFilter()));
		return dld;
	}
	public Operation insertOperation(String sessionId, Operation operation) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		ResultMsg resultMsg = new ResultMsg();
		try
		{
			operation.setCode(this.sequenceService.getNextValue("getMaxOperationID").toString());
			operation.setUser_unit_code(session.getUnit_code());
			operation.setUuid(operation.getCode());
			operation.setSortid(operation.getCode());
			operation.setVer(operation.getCode());
			operation.setCreate_user_account_id(session.getUser_code());
			boolean sign = this.operationDAS.insertOperation(operation);
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
			operationServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		operation.setResultMsg(resultMsg);
		return operation;
	}

	public Operation updateOperation(String sessionId, Operation operation) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		ResultMsg resultMsg = new ResultMsg();
		try
		{
			boolean sign = this.operationDAS.updateOperation(operation);
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
			operationServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		operation.setResultMsg(resultMsg);
		return operation;
	}

	public Operation delOperationById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Operation operation = new Operation();
		ResultMsg resultMsg = new ResultMsg();
		try
		{
			boolean sign = this.operationDAS.delOperationById(id);
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
			operationServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		operation.setResultMsg(resultMsg);
		return operation;
	}

	public Boolean delOperationByIds(String sessionId,String ids)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		Boolean rtnSign = false;
		try
		{
			String[] allID = ids.split(",");
			for(String id : allID)
			{
				this.operationDAS.delOperationById(Integer.valueOf(id));
			}
			rtnSign = true;
		}
		catch (Exception e)
		{
			operationServiceLogger.debug(e.getMessage(),e);
		}
		return rtnSign;
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
			operationServiceLogger.debug(e.getMessage(),e);
			sessionVaild = false;
		}
		return sessionVaild;
	}
}
