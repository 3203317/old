package net.newcapec.sca.gridview.service.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.gridview.GridView;
import net.newcapec.sca.gridview.service.GridViewService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

public class TestGridViewServiceImpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestGridViewServiceImpl.class);

	public static GridViewService __service;
	public static Node node;
	public static String sessionId;
	public static SessionService sessionService;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("gridViewService.composite", "target/test-classes");
		__service = node.getService(GridViewService.class, "GridViewServiceComponent");
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

	public void testgetGridViewById() throws NoSuchServiceException {
		GridView d = __service.getGridViewById(sessionId, 1);
		if (d != null)
			_log.debug(d.getFields());
	}

	public void testfindGridViewList() throws NoSuchServiceException {

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("fields");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%1%'");

		__list.add(__param);

		DojoListData d = __service.findGridViewList(sessionId, null, __list, 0, 5);

		_log.debug(d.getItems().length);
	}

	public void testupdateGridView() throws NoSuchServiceException {

		GridView __form = new GridView();
		__form.setCode(11);
		__form.setCondition_groups("22");
		__form.setCondition_trees("33");
		__form.setFields("44");
		__form.setForm_code(55);
		__form.setToolbuttons("66");

		__service.updateGridView(sessionId, __form);

	}

	public void testdelGridViewByIds() throws NoSuchServiceException {

		Integer[] ss = { 4, 5 };

		__service.delGridViewByIds(sessionId, "4,5");
	}

	public void testinsertGridView() throws NoSuchServiceException {

		GridView __form = new GridView();

		__form.setCode(11);
		__form.setCondition_groups("22");
		__form.setCondition_trees("33");
		__form.setFields("44");
		__form.setForm_code(55);
		__form.setToolbuttons("66");

		__service.insertGridView(sessionId, __form);

		_log.info("code:" + __form.getCode());
	}

	public void tearDown() {
		sessionService.invalid(sessionId);
		node.stop();
	}
}
