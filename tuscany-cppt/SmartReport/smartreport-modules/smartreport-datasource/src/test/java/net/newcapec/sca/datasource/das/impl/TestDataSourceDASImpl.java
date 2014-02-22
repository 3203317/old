package net.newcapec.sca.datasource.das.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;
import net.newcapec.sca.datasource.DataSource;
import net.newcapec.sca.datasource.das.DataSourceDAS;
import net.newcapec.sca.param.FilterParam;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

public class TestDataSourceDASImpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestDataSourceDASImpl.class);

	Node node;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("dataSourceDAS.composite", "target/test-classes");
	}

	public void testgetDataSourceById() throws NoSuchServiceException {
		DataSourceDAS __service = node.getService(DataSourceDAS.class, "DataSourceDASComponent");
		DataSource d = __service.getDataSourceById(1);
		if (d != null)
			_log.debug(d.getName());
	}

	public void testinsertDataSource() throws NoSuchServiceException {
		DataSourceDAS __service = node.getService(DataSourceDAS.class, "DataSourceDASComponent");

		DataSource __form = new DataSource();

		__form.setBind_type(11);
		__form.setCode(22);
		__form.setDbconn_code(33);
		__form.setDomain_code(44);
		__form.setMemo("55");
		__form.setMethod("66");
		__form.setName("77");
		__form.setParam("88");
		__form.setService("99");
		__form.setType("10");
		__form.setUnit_code(11);

		__service.insertDataSource(__form);

		_log.info("code:" + __form.getCode());
	}

	public void testFindDataSourceList() throws NoSuchServiceException {
		DataSourceDAS __service = node.getService(DataSourceDAS.class, "DataSourceDASComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%用户%'");

		__list.add(__param);

		List<DataSource> list = __service.findDataSourceList(null, null, __list, 0, 5);
		_log.debug(list.size());
	}

	public void testdelDataSourceByIds() throws NoSuchServiceException {
		DataSourceDAS __service = node.getService(DataSourceDAS.class, "DataSourceDASComponent");

		Integer[] ss = { 121, 122, 123, 124 };

		__service.delDataSourceByIds(ss);
	}

	public void testupdateDataSource() throws NoSuchServiceException {
		DataSourceDAS __service = node.getService(DataSourceDAS.class, "DataSourceDASComponent");

		DataSource __ds = new DataSource();

		__ds.setCode(1);
		__ds.setBind_type(11);
		__ds.setDbconn_code(22);
		__ds.setDomain_code(33);
		__ds.setMemo("44");
		__ds.setMethod("55");
		__ds.setName("66");
		__ds.setParam("77");
		__ds.setService("88");
		__ds.setType("99");
		__ds.setUnit_code(1010);

		__service.updateDataSource(__ds);
	}


	public void testFindUndefinedListByCondition() throws Exception {

		DataSourceDAS __service = node.getService(DataSourceDAS.class, "DataSourceDASComponent");
		List<FilterParam> __list = new ArrayList<FilterParam>();
		FilterParam __param = new FilterParam();
		__param.setField("ds_code");
		__param.setLogical("=");
		__param.setRelation("and");
		__param.setValue("237");
		__list.add(__param);
		FilterParam  __param1 = new FilterParam();
		__param1.setField("NAME");
		__param1.setLogical("=");
		__param1.setRelation("and");
		__param1.setValue("'SEX'");
		__list.add(__param1);
//		FilterParam  __param = new FilterParam();
//		__param.setField("ds_code");
//		__param.setLogical("=");
//		__param.setRelation("and");
//		__param.setValue("24");
//		__list.add(__param);
//		FilterParam  __param1 = new FilterParam();
//		__param1.setField("create_date");
//		__param1.setLogical(">");
//		__param1.setRelation("and");
//		__param1.setValue("'2012-10-31 10:44:41'");
//		__param1.setType("type");
//		__list.add(__param1);
//		FilterParam  __param1 = new FilterParam();
//		__param1.setField("NAME");
//		__param1.setLogical("=");
//		__param1.setRelation("and");
//		__param1.setValue("'SEX'");
//		__list.add(__param1);
//		FilterParam  __param2 = new FilterParam();
//		__param2.setField("NAME_ALIAS");
//		__param2.setLogical("=");
//		__param2.setRelation("and");
//		__param2.setValue("'性别'");
//		__list.add(__param2);
		Map<String,Object> mapResults = new HashMap<String,Object>();
		mapResults = __service.findUndefinedListByCondition(null, null, __list, 1, 100);
		if (mapResults != null) {
			System.out.println(mapResults.size());
		}

	}

	@After
	public void tearDown() {
		node.stop();
	}

}
