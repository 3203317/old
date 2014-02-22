package net.newcapec.sca.fieldprep.service.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.newcapec.sca.fieldprep.FieldPrep;
import net.newcapec.sca.fieldprep.service.FieldPrepService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

public class TestFieldPrepServiceImpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestFieldPrepServiceImpl.class);

	public static Node node;
	public static String sessionId;
	public static SessionService sessionService;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("fieldPrepService.composite", "target/test-classes");
		sessionService = node.getService(SessionService.class, "SessionComponent");

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

	public void testinsertFieldPrep() throws NoSuchServiceException {
		FieldPrepService __service = node.getService(FieldPrepService.class, "FieldPrepServiceComponent");

		FieldPrep __ds = new FieldPrep();
		__ds.setAlias("11");
		__ds.setDs_code(22);
		__ds.setInput_type("33");
		__ds.setMemo("44");
		__ds.setName("55");
		__ds.setRegexp("66");
		__ds.setType("77");
		__ds.setCode(88);

		_log.debug(__service.insertFieldPrep(sessionId, __ds));
	}

	public void testfindFieldPrepList() throws NoSuchServiceException {
		FieldPrepService __service = node.getService(FieldPrepService.class, "FieldPrepServiceComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%NAME%'");

		__list.add(__param);

		List __dld = __service.findFieldPrepList(sessionId, 1, __list, 0, 5);

		_log.debug("================" + __dld.size());

	}

	public void testupdateFieldPrep() throws NoSuchServiceException {
		FieldPrepService __service = node.getService(FieldPrepService.class, "FieldPrepServiceComponent");

		FieldPrep __ds = new FieldPrep();

		__ds.setCode(3);
		__ds.setAlias("11");
		__ds.setDs_code(22);
		__ds.setInput_type("33");
		__ds.setMemo("44");
		__ds.setName("55");
		__ds.setRegexp("66");
		__ds.setType("77");

		_log.debug(__service.updateFieldPrep(sessionId, __ds));

	}

	public void testdelFieldPrepByIds() throws NoSuchServiceException {
		FieldPrepService __service = node.getService(FieldPrepService.class, "FieldPrepServiceComponent");

		__service.delFieldPrepByIds(sessionId, "0,0");

	}

	public void testgetFieldPrepById() throws NoSuchServiceException {
		FieldPrepService __service = node.getService(FieldPrepService.class, "FieldPrepServiceComponent");

		FieldPrep __fp = __service.getFieldPrepById(sessionId, 1);
		if (__fp != null)
			_log.info(__fp.getName());
	}
}
