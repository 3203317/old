package net.newcapec.sca.dbconn.service.impl;

import java.util.List;

import net.newcapec.sca.dbconn.DBConnection;
import net.newcapec.sca.dbconn.das.DBConnDAS;
import net.newcapec.sca.dbconn.service.DBConnService;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;
/**
 *
 *
 */
public class DBConnServiceImpl implements DBConnService {

	private static final Logger dbConnServiceImplLogger = Logger.getLogger(DBConnServiceImpl.class);

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private DBConnDAS dbConnDAS;

	@Reference(name = "dbConnDAS", required = true)
	public void setDBConnDAS(DBConnDAS dbConnDAS) {
		this.dbConnDAS = dbConnDAS;
	}
	private SequenceService sequenceService;

	@Reference(name = "sequenceService", required = true)
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}

	public DBConnection getDBConnById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		DBConnection db = new DBConnection();
		ResultMsg getResourceError = new ResultMsg();
		if(!this.isSessionVaild(session))
		{
			/* 设置操作结果消息 */
			getResourceError.setStatus(0);
			getResourceError.setErrorId(102);
			getResourceError.setErrorMsg("session失效");
			db.setResultMsg(getResourceError);
			return db;
		}
		getResourceError.setStatus(1);
		db = dbConnDAS.getDBConnById(id);
		return db;
	}

	public List<DBConnection> findDBConnList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limit) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		DBConnection db = new DBConnection();

		List<DBConnection> list =
			this.dbConnDAS.findDBConnList
			(Integer.valueOf(session.getDomain_code()), Integer.valueOf(session.getUnit_code()), filter, beginId, limit);

		return list;

	}
	public Boolean getDBConfig2Connection(String sessionId ,DBConnection db)
	{
		return this.dbConnDAS.getDBConfig2Connection(db);
	}
	public DojoListData findDBConnDojoList(DojoListParam param)
	{
		Session session = sessionService.getSession(param.getSessionId());
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<DBConnection> list = this.dbConnDAS.findDBConnList(1, 1, param.getFilter(), param.getBegin(), param.getLimit());
		DojoListData dld = new DojoListData();
		dld.setIdentifier("code");
		dld.setItems(list.toArray());
		dld.setLabel("name");
		dld.setNumRows(this.dbConnDAS.findDBConnList(1, 1, param.getFilter(), null, null).size());
		return dld;
	}
	public Integer getDBConnListRowCount(String sessionId,List<FilterParam> filter)
	{
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}

		return this.dbConnDAS.getDBConnListRowCount(1, 1, filter);
	}
	public Boolean insertDBConn(String sessionId, DBConnection org) {
		Session session = sessionService.getSession(sessionId);
		DBConnection db = new DBConnection();
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		org.setCode(Long.valueOf(this.sequenceService.getNextValue("getMaxDBConnectionID")));
		return this.dbConnDAS.insertDBConn(org);
	}

	public Boolean updateDBConn(String sessionId, DBConnection org) {
		Session session = sessionService.getSession(sessionId);
		DBConnection db = new DBConnection();
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		return this.dbConnDAS.updateDBConn(org);
	}
	public Boolean delDBConnByIds(String sessionId, String ids)
	{
		Boolean rtnSign = false;
		try
		{
			String[] allID = ids.split(",");
			for(String id : allID)
			{
				this.dbConnDAS.delDBConnById(Integer.valueOf(id));
			}
			rtnSign = true;
		}
		catch (Exception e)
		{
			dbConnServiceImplLogger.debug(e.getMessage(),e);
		}
		return rtnSign;
	}
	public Boolean delDBConnById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		DBConnection db = new DBConnection();
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		return this.dbConnDAS.delDBConnById(id);
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
			dbConnServiceImplLogger.debug(e.getMessage(),e);
			sessionVaild = false;
		}
		return sessionVaild;
	}

}
