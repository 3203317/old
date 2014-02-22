package net.newcapec.sca.darule.service.impl;

import net.newcapec.sca.darule.DataAccessRule;
import net.newcapec.sca.darule.service.DataAccessRuleService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

/**
 * <p>
 * Title: TestDataaccessruleService
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright(c) 2011 郑州新开普电子股份有限公司
 * </p>
 *
 * @author 黄鑫 (huangxin)
 * @version
 * @data 创建日期：2011-11-11 修改日期： 修改人： 复审人：
 * @generated
 */
public class TestDataAccessRuleServiceImpl {

	private static final Logger _log = Logger.getLogger(TestDataAccessRuleServiceImpl.class);

	public static Node node;
	public static String sessionId;
	public static SessionService sessionService;
	public static DataAccessRuleService __service;

	@BeforeClass
	public static void setUpClass() throws Exception {
		node = TuscanyRuntime.runComposite("dataAccessRuleServiceTest.composite", "target/test-classes");
		sessionService = node.getService(SessionService.class, "SessionComponent");
		__service = node.getService(DataAccessRuleService.class, "DataAccessRuleServiceComponent");
		Session session = new Session();
		session.setAge("123401111");
		session.setDomain_code("1111");
		session.setUnit_code("224");
		session.setUser_code("12312312");
		session.setContent("dddddsdfds222222");
		session.setInvalid_date("");
		session = sessionService.createSession(session);
		sessionId = session.getId();
	}
	@Test
	public void testsetDataAccessRule() throws NoSuchServiceException {


		DataAccessRule __form = new DataAccessRule();
		__form.setCode(1);
		__form.setName("2");
		__form.setResource_code(41);
		__form.setType_code(4);
		__form.setContent("5");
		__form.setCreate_date("6");
		__form.setEncryption_info("7");
		__form.setEncryption_timestamp("8");
		__form.setVer("9");

		DataAccessRule __new = __service.setDataAccessRule(sessionId, __form);
		_log.info("code:" + __new.getCode());
	}
	@Test
	public void testdelDataAccessRuleByResId() throws NoSuchServiceException {
		DataAccessRule __new = __service.delDataAccessRuleByResId(sessionId, 1);
		_log.info("code:" + __new);
	}
	@Test
	public void testgetDataAccessRuleByResId() throws NoSuchServiceException {

		DataAccessRule __new = __service.getDataAccessRuleByResId(sessionId, 41);
		//_log.info("code:" + __new.getName());
	}
	@AfterClass
	public static void tearDownClass()
	{
		sessionService.invalid(sessionId);
		node.stop();
	}
}
