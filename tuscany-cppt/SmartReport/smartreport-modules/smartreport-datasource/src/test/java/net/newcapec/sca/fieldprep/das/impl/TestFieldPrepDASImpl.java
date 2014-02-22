package net.newcapec.sca.fieldprep.das.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.newcapec.sca.fieldprep.FieldPrep;
import net.newcapec.sca.fieldprep.das.FieldPrepDAS;
import net.newcapec.sca.param.FilterParam;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

public class TestFieldPrepDASImpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestFieldPrepDASImpl.class);

	Node node;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("fieldPrepDAS.composite", "target/test-classes");
	}

	public void testgetFieldPrepById() throws NoSuchServiceException {
		FieldPrepDAS __service = node.getService(FieldPrepDAS.class, "FieldPrepDASComponent");

		FieldPrep __fp = __service.getFieldPrepById(2);
		if (__fp != null)
			_log.info(__fp.getName());
	}

	public void testfindFieldPrepList() throws NoSuchServiceException {
		FieldPrepDAS __service = node.getService(FieldPrepDAS.class, "FieldPrepDASComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%NAME%'");

		__list.add(__param);

		List<FieldPrep> list = __service.findFieldPrepList(null, null, __list, 1, 5);
		_log.debug(list.size());

	}

	public void testinsertFieldPrep() throws NoSuchServiceException {
		FieldPrepDAS __service = node.getService(FieldPrepDAS.class, "FieldPrepDASComponent");

		FieldPrep __ds = new FieldPrep();
		__ds.setAlias("11");
		__ds.setDs_code(22);
		__ds.setInput_type("33");
		__ds.setMemo("44");
		__ds.setName("55");
		__ds.setRegexp("66");
		__ds.setType("77");
		__ds.setCode(88);

		__service.insertFieldPrep(__ds);
		_log.info("id:" + __ds.getCode());
	}

	public void testupdateFieldPrep() throws NoSuchServiceException {
		FieldPrepDAS __service = node.getService(FieldPrepDAS.class, "FieldPrepDASComponent");

		FieldPrep __ds = new FieldPrep();

		__ds.setCode(1);
		__ds.setAlias("11");
		__ds.setDs_code(22);
		__ds.setInput_type("33");
		__ds.setMemo("44");
		__ds.setName("55");
		__ds.setRegexp("66");
		__ds.setType("77");

		__service.updateFieldPrep(__ds);
	}

	public void testdelFieldPrepByIds() throws NoSuchServiceException {
		FieldPrepDAS __service = node.getService(FieldPrepDAS.class, "FieldPrepDASComponent");

		Integer[] ss = { 17, 18 };

		__service.delFieldPrepByIds(ss);
	}
}
