package net.newcapec.sca.menubinding.service.impl;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.menubinding.MenuBinding;
import net.newcapec.sca.menubinding.das.MenuBindingDAS;
import net.newcapec.sca.menubinding.service.MenuBindingService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class MenuBindingServiceImpl implements MenuBindingService {

	private static final Logger menuBindingServiceLogger = Logger.getLogger(MenuBindingServiceImpl.class);
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

	private MenuBindingDAS menuBindingDAS;

	@Reference(name = "menuBindingDAS", required = true)
	public void setMenuBindingDAS(MenuBindingDAS menuBindingDAS) {
		this.menuBindingDAS = menuBindingDAS;
	}
	public MenuBinding getMenuBindingById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		return menuBindingDAS.getMenuBindingById(id);
	}

	public List<MenuBinding> findMenuBindingList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limit) {
		Session session = sessionService.getSession(sessionId);
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		List<MenuBinding> list = menuBindingDAS.findMenuBindingList(null, null, filter, beginId, limit);
		return list;
	}

	public MenuBinding insertMenuBinding(String sessionId,
			MenuBinding menuBinding) {
		Session session = sessionService.getSession(sessionId);
		ResultMsg resultMsg = new ResultMsg();
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		try
		{
			menuBinding.setCode(sequenceService.getNextValue("getMaxMenuBindingID").toString());
			boolean sign = menuBindingDAS.insertMenuBinding(menuBinding);
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
			menuBindingServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		menuBinding.setResultMsg(resultMsg);
		return menuBinding;
	}

	public MenuBinding updateMenuBinding(String sessionId,
			MenuBinding menuBinding) {
		Session session = sessionService.getSession(sessionId);
		ResultMsg resultMsg = new ResultMsg();
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		try
		{
			boolean sign = menuBindingDAS.updateMenuBinding(menuBinding);
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
			menuBindingServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		menuBinding.setResultMsg(resultMsg);
		return menuBinding;
	}

	public MenuBinding delMenuBindingById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		ResultMsg resultMsg = new ResultMsg();
		MenuBinding menuBinding = new MenuBinding();
		if(!this.isSessionVaild(session))
		{
			return null;
		}
		try
		{
			boolean sign = menuBindingDAS.delMenuBindingById(id);
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
			menuBindingServiceLogger.debug(e.getMessage(),e);
			resultMsg.setStatus(0);
		}
		menuBinding.setResultMsg(resultMsg);
		return menuBinding;
	}
	public DojoListData findMenuBindingDojoList (DojoListParam param)
	{
		return this.menuBindingDAS.findMenuBindingList(param);
	}
	public Integer getMenuBindingListRowCount(Integer domainId, Integer orgId, List<FilterParam> filter)
	{
		return this.menuBindingDAS.getMenuBindingListRowCount(domainId, orgId, filter);
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
			menuBindingServiceLogger.debug(e.getMessage(),e);
			sessionVaild = false;
		}
		return sessionVaild;
	}
}
