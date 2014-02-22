package net.newcapec.sca.gridcondition.service.impl;

import java.util.List;

import net.newcapec.sca.diccode.DicItem;
import net.newcapec.sca.diccode.service.DicCodeService;
import net.newcapec.sca.fieldprep.FieldPrep;
import net.newcapec.sca.fieldprep.service.FieldPrepService;
import net.newcapec.sca.gridcondition.GridStatDataCollection;
import net.newcapec.sca.gridcondition.service.GridStatDataCollectionService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class GridStatDataCollectionServiceImpl implements
		GridStatDataCollectionService {
	private static final Logger gridStatDataCollectionServiceLogger = Logger.getLogger(GridStatDataCollectionServiceImpl.class);
	private DicCodeService dicCodeService;

	@Reference(name = "dicCodeService", required = true)
	public void setDicCodeService(DicCodeService dicCodeService) {
		this.dicCodeService = dicCodeService;
	}

	private FieldPrepService fieldPrepService;

	@Reference(name = "fieldPrepService", required = true)
	public void setFieldPrepService(FieldPrepService fieldPrepService) {
		this.fieldPrepService = fieldPrepService;
	}

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public GridStatDataCollection getGridStatDataCollection(String sessionId,
			Integer ds_code, List<FilterParam> filter) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<FieldPrep> fieldPrepList = fieldPrepService.findFieldListByDscode(sessionId, ds_code);
		List<DicItem> dicItemList = dicCodeService.findDicItemListByCondition(sessionId, filter);

		GridStatDataCollection gsdc = new GridStatDataCollection();
		gsdc.setDicItemList(dicItemList);
		gsdc.setFieldPrepList(fieldPrepList);
		return gsdc;
	}

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
			gridStatDataCollectionServiceLogger.debug(e.getMessage(),e);
			sessionVaild = false;
		}
		return sessionVaild;
	}
}
