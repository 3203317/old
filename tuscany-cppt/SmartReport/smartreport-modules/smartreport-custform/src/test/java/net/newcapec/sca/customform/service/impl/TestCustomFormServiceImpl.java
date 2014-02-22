package net.newcapec.sca.customform.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.customform.CustomForm;
import net.newcapec.sca.customform.service.CustomFormService;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

public class TestCustomFormServiceImpl {

	private static final Logger _log = Logger
			.getLogger(TestCustomFormServiceImpl.class);

	private static Node node;

	private static CustomFormService __service;
	private static  SessionService sessionService;
	private static  String sessionId;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("customFormService.composite",
				"target/test-classes");
		__service = node.getService(CustomFormService.class,
		"CustomFormServiceComponent");
		 sessionService = node.getService(SessionService.class, "SessionComponent");//added pxx
		 Session session = new Session();
		 session.setAge("123401111");
		 session.setDomain_code("1");
		 session.setUnit_code("1");
		 session.setUser_code("1");
		 session.setContent("dddddsdfds222222");
		 session.setInvalid_date("");
		 session = sessionService.createSession(session);
		 sessionId = session.getId();
	}
	@Test
	public void testfindCustomFormDojoList() throws NoSuchServiceException {

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%66%'");

		__list.add(__param);

		DojoListParam __dlp = new DojoListParam();
		__dlp.setBegin(0);
		__dlp.setLimit(5);
		__dlp.setSessionId(sessionId);

		DojoListData d = __service.findCustomFormDojoList(__dlp);
		_log.debug(d.getItems().length);
	}
	@Test
	public void testupdateCustomForm() throws NoSuchServiceException {

		CustomForm __form = new CustomForm();
		__form.setCode(3);
		__form.setCreate_user_code(11);
		__form.setDomain_code(22);
		__form.setDs_code(33);
		__form.setMemo("44");
		__form.setName("55");
		__form.setType(66);
		__form.setUnit_code(77);

		CustomForm __new = __service.updateCustomForm(sessionId, __form);
		_log.debug(__new.getCode());
	}
	@Test
	public void testinsertCustomForm() throws NoSuchServiceException {

		CustomForm __form = new CustomForm();
		__form.setCode(111);
		__form.setCreate_user_code(222);
		__form.setDomain_code(333);
		__form.setDs_code(444);
		__form.setMemo("555");
		__form.setName("666");
		__form.setType(777);
		__form.setUnit_code(888);

		CustomForm __new = __service.insertCustomForm(sessionId, __form);
		_log.info("code:" + __new.getCode());
	}
	@Test
	public void testdelCustomFormByIds() throws NoSuchServiceException {
		CustomForm __new = __service.delCustomFormByIds(sessionId, "75,50");
		_log.info("code:" + __new);
	}
	@Test
	public void testgetCustomFormById() throws NoSuchServiceException {

		CustomForm __new = __service.getCustomFormById(sessionId, 1);
		if(null != __new)
		{
			_log.info("code:" + __new.getName());
		}
	}
	@Test
	public void testgetCustomFormListRowCount() throws NoSuchServiceException {


		//int __count = __service.getCustomFormListRowCount(null, null, null);
		//_log.info("count:" + __count);
	}
	@After
	public void tearDown()
	{
		 sessionService.invalid(sessionId);
	     node.stop();
	}

}
