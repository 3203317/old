package net.newcapec.sca.datasource.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import net.newcapec.sca.datasource.DataSource;
import net.newcapec.sca.datasource.service.DataSourceService;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

public class TestDataSourceServiceImpl extends TestCase {
	private static final Logger _log = Logger.getLogger(TestDataSourceServiceImpl.class);

	Node node;
	public static String sessionId;
	public static SessionService sessionService;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("dataSourceService.composite", "target/test-classes");
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

	public void testinsertDataSource() throws NoSuchServiceException {
		DataSourceService __service = node.getService(DataSourceService.class, "DataSourceServiceComponent");

		DataSource __ds = new DataSource();

		__ds.setBind_type(11);
		__ds.setCode(22);
		__ds.setDbconn_code(33);
		__ds.setDomain_code(44);
		__ds.setMemo("55");
		__ds.setMethod("select * from p_user");
		__ds.setName("77");
		__ds.setParam("88");
		__ds.setService("99");
		__ds.setType("10");
		__ds.setUnit_code(11);

		_log.debug(__service.insertDataSource(sessionId, __ds));

	}

	public void testupdateDataSource() throws NoSuchServiceException {
		DataSourceService __service = node.getService(DataSourceService.class, "DataSourceServiceComponent");

		DataSource __ds = new DataSource();

		__ds.setCode(121);
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

		DataSource __new = __service.updateDataSource(sessionId, __ds);
		if (__new != null)
			_log.debug(__new.getCode());
	}

	public void testfindDataSourceDojoList() throws NoSuchServiceException {
		DataSourceService __service = node.getService(DataSourceService.class, "DataSourceServiceComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%用户%'");

		__list.add(__param);

		DojoListParam __dlp = new DojoListParam();
		__dlp.setSessionId(sessionId);
		__dlp.setBegin(0);
		__dlp.setLimit(5);

		DojoListData d = __service.findDataSourceDojoList(__dlp);
		_log.debug(d.getItems().length);

	}

	public void testdelDataSourceByIds() throws NoSuchServiceException {
		DataSourceService __service = node.getService(DataSourceService.class, "DataSourceServiceComponent");

		DataSource __new = __service.delDataSourceByIds(sessionId, "0,0");
		_log.info("code:" + __new);
	}

	public void testgetCustomFormById() throws NoSuchServiceException {
		DataSourceService __service = node.getService(DataSourceService.class, "DataSourceServiceComponent");

		DataSource __new = __service.getDataSourceById(sessionId, 1);
		_log.info("code:" + __new.getName());
	}

	public void testgetDataSourceListRowCount() throws NoSuchServiceException {
		DataSourceService __service = node.getService(DataSourceService.class, "DataSourceServiceComponent");

		int __count = __service.getDataSourceListRowCount(sessionId, 1, null);
		_log.info("count:" + __count);
	}

	public void testFindUndefinedDojoListByCondition() throws NoSuchServiceException {
		DataSourceService __service = node.getService(DataSourceService.class, "DataSourceServiceComponent");
		List<FilterParam> __list = new ArrayList<FilterParam>();
		FilterParam __param = new FilterParam();
		__param.setField("ds_code");
		__param.setLogical("=");
		__param.setRelation("and");
		__param.setValue("151");
		__list.add(__param);

//		FilterParam  __param = new FilterParam();
//		__param.setField("ds_code");
//		__param.setLogical("=");
//		__param.setRelation("and");
//		__param.setValue("24");
//		__list.add(__param);
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

		DojoListParam __dlp = new DojoListParam();
		__dlp.setFilter(__list);
		__dlp.setBegin(0);
		__dlp.setLimit(5);
		__dlp.setSessionId(sessionId);
//		DojoListData d = __service.findUndefinedDojoListByCondition(__dlp);
	}
	public void tearDown() {
		sessionService.invalid(sessionId);
		node.stop();
	}
}
