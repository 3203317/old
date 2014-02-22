package net.newcapec.sca.gridstat.service.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.gridstat.GridStat;
import net.newcapec.sca.gridstat.service.GridStatService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

public class TestGridStatServiceImpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestGridStatServiceImpl.class);

	Node node;
	public static String sessionId;
	public static SessionService sessionService;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("gridStatService.composite", "target/test-classes");
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

	public void testgetGridStatById() throws NoSuchServiceException {
		GridStatService __service = node.getService(GridStatService.class, "GridStatServiceComponent");
		GridStat d = __service.getGridStatById(sessionId, 1);
		if (d != null)
			_log.debug(d.getField_type_list());
	}

	public void testfindGridStatList() throws NoSuchServiceException {
		GridStatService __service = node.getService(GridStatService.class, "GridStatServiceComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%用户%'");

		__list.add(__param);

		DojoListData d = __service.findGridStatList(sessionId, null, null, 0, 5);
		_log.debug(d.getItems().length);
	}

	public void testupdateGridStat() throws NoSuchServiceException {
		GridStatService __service = node.getService(GridStatService.class, "GridStatServiceComponent");

		GridStat __form = new GridStat();
		__form.setCode(3);
		__form.setField_type_list("222");
		__form.setForm_code(333);
		__form.setType(444);

		__service.updateGridStat(sessionId, __form);

	}

	public void testdelGridStatByIds() throws NoSuchServiceException {
		GridStatService __service = node.getService(GridStatService.class, "GridStatServiceComponent");

		Integer[] ss = { 10, 11, 12, 13, 14, 15, 16, 17 };

		__service.delGridStatByIds(sessionId, "0,0");
	}

	public void testinsertGridStat() throws NoSuchServiceException {
		GridStatService __service = node.getService(GridStatService.class, "GridStatServiceComponent");

		GridStat __form = new GridStat();

		__form.setCode(11);
		__form.setField_type_list("22");
		__form.setForm_code(33);
		__form.setType(44);

		__service.insertGridStat(sessionId, __form);

		_log.info("code:" + __form.getCode());
	}

}
