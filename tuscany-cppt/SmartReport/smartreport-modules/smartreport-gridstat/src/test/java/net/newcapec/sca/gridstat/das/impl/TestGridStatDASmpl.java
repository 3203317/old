package net.newcapec.sca.gridstat.das.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.newcapec.sca.gridstat.GridStat;
import net.newcapec.sca.gridstat.das.GridStatDAS;
import net.newcapec.sca.param.FilterParam;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

public class TestGridStatDASmpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestGridStatDASmpl.class);

	Node node;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("gridStatDAS.composite", "target/test-classes");
	}

	public void testgetGridStatById() throws NoSuchServiceException {
		GridStatDAS __service = node.getService(GridStatDAS.class, "GridStatDASComponent");
		GridStat d = __service.getGridStatById(1);
		if (d != null)
			_log.debug(d.getField_type_list());
	}

	public void testfindGridStatList() throws NoSuchServiceException {
		GridStatDAS __service = node.getService(GridStatDAS.class, "GridStatDASComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%用户%'");

		__list.add(__param);

		List d = __service.findGridStatList(null, null, null, 0, 5);
		_log.debug(d.size());
	}

	public void testupdateGridStat() throws NoSuchServiceException {
		GridStatDAS __service = node.getService(GridStatDAS.class, "GridStatDASComponent");

		GridStat __form = new GridStat();
		__form.setCode(3);
		__form.setField_type_list("222");
		__form.setForm_code(333);
		__form.setType(444);

		__service.updateGridStat(__form);

	}

	public void testdelGridStatByIds() throws NoSuchServiceException {
		GridStatDAS __service = node.getService(GridStatDAS.class, "GridStatDASComponent");

		Integer[] ss = { 10, 11, 12, 13, 14, 15, 16, 17 };

		__service.delGridStatByIds(ss);
	}

	public void testinsertGridStat() throws NoSuchServiceException {
		GridStatDAS __service = node.getService(GridStatDAS.class, "GridStatDASComponent");

		GridStat __form = new GridStat();

		__form.setCode(11);
		__form.setField_type_list("22");
		__form.setForm_code(33);
		__form.setType(44);

		__service.insertGridStat(__form);

		_log.info("code:" + __form.getCode());
	}

}
