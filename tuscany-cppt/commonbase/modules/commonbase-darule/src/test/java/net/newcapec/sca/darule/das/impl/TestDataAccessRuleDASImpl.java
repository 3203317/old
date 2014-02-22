package net.newcapec.sca.darule.das.impl;

import junit.framework.TestCase;
import net.newcapec.sca.darule.DataAccessRule;
import net.newcapec.sca.darule.das.DataAccessRuleDAS;
import net.newcapec.sca.darule.service.DataAccessRuleService;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

/**
 * <p>
 * Title: TestDataaccessruleDAS
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
public class TestDataAccessRuleDASImpl   {

	private static final Logger _log = Logger.getLogger(TestDataAccessRuleDASImpl.class);


	public static DataAccessRuleDAS __service;
	public static Node node;

	@BeforeClass
	public static void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("dataAccessRuleDASTest.composite", "target/test-classes");
		__service = node.getService(DataAccessRuleDAS.class, "DataAccessRuleDASComponent");
	}
	@Test
	public void testgetDataAccessRuleByResId() throws NoSuchServiceException {
		DataAccessRule d = __service.getDataAccessRuleByResId(3);
		if (d == null) {
			_log.debug("不存在该条记录");
		} else {
			_log.debug(d.getName());
			_log.debug(d.getCreate_date());
		}
	}
	@Test
	public void testupdateDataAccessRule() throws NoSuchServiceException {

		DataAccessRule __form = new DataAccessRule();
		__form.setCode(1);
		__form.setName("2");
		__form.setResource_code(3);
		__form.setType_code(4);
		__form.setContent("5");
		__form.setCreate_date("6");
		__form.setEncryption_info("7");
		__form.setEncryption_timestamp("8");
		__form.setVer("9");
		__service.updateDataAccessRule(__form);

	}
	@Test
	public void testdelDataAccessRuleByResId() throws NoSuchServiceException {

		__service.delDataAccessRuleByResId(13);
	}
	@Test
	public void testinsertDataAccessRule() throws NoSuchServiceException {

		DataAccessRule __form = new DataAccessRule();

		__form.setCode(1);
		__form.setName("2");
		__form.setResource_code(3);
		__form.setType_code(4);
		__form.setContent("5");
		__form.setCreate_date("6");
		__form.setEncryption_info("7");
		__form.setEncryption_timestamp("8");
		__form.setVer("9");

		__service.insertDataAccessRule(__form);

		_log.info("code:" + __form.getCode());
	}
	@AfterClass
	public static void tearDownClass()
	{

		node.stop();
	}
}
