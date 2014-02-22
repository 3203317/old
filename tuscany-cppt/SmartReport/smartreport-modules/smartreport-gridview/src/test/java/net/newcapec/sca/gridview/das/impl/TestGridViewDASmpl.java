package net.newcapec.sca.gridview.das.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.newcapec.sca.gridview.GridView;
import net.newcapec.sca.gridview.das.GridViewDAS;
import net.newcapec.sca.param.FilterParam;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

public class TestGridViewDASmpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestGridViewDASmpl.class);

	Node node;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("gridViewDAS.composite", "target/test-classes");
	}

	public void testgetGridViewById() throws NoSuchServiceException {
		GridViewDAS __service = node.getService(GridViewDAS.class, "GridViewDASComponent");
		GridView d = __service.getGridViewById(1);
		if (d != null)
			_log.debug(d.getFields());
	}

	public void testfindGridViewList() throws NoSuchServiceException {
		GridViewDAS __service = node.getService(GridViewDAS.class, "GridViewDASComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("fields");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%1%'");

		__list.add(__param);

		List d = __service.findGridViewList(null, null, __list, 0, 5);
		_log.debug(d.size());
	}

	public void testupdateGridView() throws NoSuchServiceException {
		GridViewDAS __service = node.getService(GridViewDAS.class, "GridViewDASComponent");

		GridView __form = new GridView();
		__form.setCode(11);
		__form.setCondition_groups("22");
		__form.setCondition_trees("33");
		__form.setFields("44");
		__form.setForm_code(55);
		__form.setToolbuttons("66");

		__service.updateGridView(__form);

	}

	public void testdelGridViewByIds() throws NoSuchServiceException {
		GridViewDAS __service = node.getService(GridViewDAS.class, "GridViewDASComponent");

		Integer[] ss = { 4, 5 };

		__service.delGridViewByIds(ss);
	}

	public void testinsertGridView() throws NoSuchServiceException {
		GridViewDAS __service = node.getService(GridViewDAS.class, "GridViewDASComponent");

		GridView __form = new GridView();

		__form.setCode(11);
		__form.setCondition_groups("22");
		__form.setCondition_trees("33");
		__form.setFields("44");
		__form.setForm_code(55);
		__form.setToolbuttons("66");

		__service.insertGridView(__form);

		_log.info("code:" + __form.getCode());
	}

}
