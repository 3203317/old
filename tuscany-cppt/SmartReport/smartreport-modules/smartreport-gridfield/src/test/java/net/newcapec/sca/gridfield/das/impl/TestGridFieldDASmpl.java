package net.newcapec.sca.gridfield.das.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.newcapec.sca.gridfield.GridField;
import net.newcapec.sca.gridfield.das.GridFieldDAS;
import net.newcapec.sca.param.FilterParam;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

public class TestGridFieldDASmpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestGridFieldDASmpl.class);

	Node node;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("gridFieldDAS.composite", "target/test-classes");
	}

	public void testgetGridFieldById() throws NoSuchServiceException {
		GridFieldDAS __service = node.getService(GridFieldDAS.class, "GridFieldDASComponent");
		GridField d = __service.getGridFieldById(1);
		if (d != null)
			_log.debug(d.getName());
	}

	public void testfindGridFieldList() throws NoSuchServiceException {
		GridFieldDAS __service = node.getService(GridFieldDAS.class, "GridFieldDASComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%用户%'");

		__list.add(__param);

		List d = __service.findGridFieldList(null, null, __list, 0, 5);
		_log.debug(d.size());
	}

	public void testupdateGridField() throws NoSuchServiceException {
		GridFieldDAS __service = node.getService(GridFieldDAS.class, "GridFieldDASComponent");

		GridField __form = new GridField();
		__form.setCode(3);
		__form.setForm_code(11);
		__form.setName("22");
		__form.setFormat("33");

		__service.updateGridField(__form);

	}

	public void testdelGridFieldByIds() throws NoSuchServiceException {
		GridFieldDAS __service = node.getService(GridFieldDAS.class, "GridFieldDASComponent");

		Integer[] ss = { 2, 3 };

		__service.delGridFieldByIds(ss);
	}

	public void testinsertGridField() throws NoSuchServiceException {
		GridFieldDAS __service = node.getService(GridFieldDAS.class, "GridFieldDASComponent");

		GridField __form = new GridField();

		__form.setCode(111);
		__form.setName("666");
		__form.setForm_code(777);
		__form.setFormat("888");

		__service.insertGridField(__form);

		_log.info("code:" + __form.getCode());
	}

}
