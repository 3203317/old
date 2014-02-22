package net.newcapec.sca.gridfield.service.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.gridfield.GridField;
import net.newcapec.sca.gridfield.service.GridFieldService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

public class TestGridFieldServiceImpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestGridFieldServiceImpl.class);

	Node node;
	public static String sessionId;
	public static SessionService sessionService;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("gridFieldService.composite", "target/test-classes");
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

	public void testfindGridFieldList() throws NoSuchServiceException {
		GridFieldService __service = node.getService(GridFieldService.class, "GridFieldServiceComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%用户%'");

		__list.add(__param);

		DojoListData d = __service.findGridFieldList(sessionId, null, null, 0, 5);
		_log.debug(d.getItems().length);
	}

	public void testupdateGridField() throws NoSuchServiceException {
		GridFieldService __service = node.getService(GridFieldService.class, "GridFieldServiceComponent");

		GridField __form = new GridField();
		__form.setCode(3);
		__form.setName("55");
		__form.setForm_code(66);
		__form.setFormat("77");

		GridField __new = __service.updateGridField(sessionId, __form);
		_log.debug(__new.getCode());
	}

	public void testinsertGridField() throws NoSuchServiceException {
		GridFieldService __service = node.getService(GridFieldService.class, "GridFieldServiceComponent");

		GridField __form = new GridField();
		__form.setCode(111);
		__form.setName("666");
		__form.setForm_code(777);
		__form.setFormat("777");

		GridField __new = __service.insertGridField(sessionId, __form);
		_log.info("code:" + __new.getCode());
	}

	public void testdelGridFieldByIds() throws NoSuchServiceException {
		GridFieldService __service = node.getService(GridFieldService.class, "GridFieldServiceComponent");

		GridField __new = __service.delGridFieldByIds(sessionId, "0,0");
		_log.info("code:" + __new);
	}

	public void testgetGridFieldById() throws NoSuchServiceException {
		GridFieldService __service = node.getService(GridFieldService.class, "GridFieldServiceComponent");

		GridField __new = __service.getGridFieldById(sessionId, 1);
		if (__new != null)
			_log.info("code:" + __new.getName());
	}

	public void testFindGridFieldListByCondition() throws NoSuchServiceException {
		GridFieldService __service = node.getService(GridFieldService.class, "GridFieldServiceComponent");
		List<FilterParam> __listparam = new ArrayList<FilterParam>();
		FilterParam __param = new FilterParam();
		__param.setField("form_code");
		__param.setLogical("=");
		__param.setValue("17");
		__param.setRelation("and");
		__listparam.add(__param);
		List<GridField> __list = __service.findGridFieldListByCondition(sessionId, __listparam);
		if (__list != null) {
			System.out.println(__list.size());
		}

	}

	public void testUpdateGridFields() throws NoSuchServiceException {
		GridFieldService __service = node.getService(GridFieldService.class, "GridFieldServiceComponent");
		List<GridField> __gridFields = new ArrayList<GridField>();
		GridField __gridField1 = new GridField();
		__gridField1.setCode(0);
		__gridField1.setName("666");
		__gridField1.setForm_code(777);
		__gridField1.setFormat("777");
		__gridFields.add(__gridField1);
		GridField __gridField2 = new GridField();
		__gridField2.setCode(7);
		__gridField2.setName("888");
		__gridField2.setForm_code(888);
		__gridField2.setFormat("888");
		__gridFields.add(__gridField2);
		__service.updateGridFields(sessionId, __gridFields);
	}

}
