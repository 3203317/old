package net.newcapec.sca.customform.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.customform.CustomForm;
import net.newcapec.sca.customform.das.CustomFormDAS;
import net.newcapec.sca.param.FilterParam;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

public class TestCustomFormDASImpl {

	private static final Logger _log = Logger
			.getLogger(TestCustomFormDASImpl.class);

	private static Node node;

	private static CustomFormDAS __service;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("customFormDAS.composite",
				"target/test-classes");
		__service = node.getService(CustomFormDAS.class,
		"CustomFormDASComponent");
	}
	@Test
	public void testgetCustomFormById() throws NoSuchServiceException {
		CustomForm d = __service.getCustomFormById(1);
		if(null != d)
		{
			_log.debug(d.getName());
			_log.debug(d.getCreate_date());
		}
	}
	@Test
	public void testfindCustomFormList() throws NoSuchServiceException {

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%用户%'");

		__list.add(__param);

		List d = __service.findCustomFormList(null, null, __list, 0, 5);
		_log.debug(d.size());
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

		__service.updateCustomForm(__form);

	}
	@Test
	public void testdelCustomFormByIds() throws NoSuchServiceException {

		Integer[] ss = { 10, 11, 12, 13, 14, 15, 16, 17 };

		__service.delCustomFormByIds(ss);
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

		__service.insertCustomForm(__form);

		_log.info("code:" + __form.getCode());
	}

	@After
	public void tearDown()
	{
		node.stop();
	}

}
