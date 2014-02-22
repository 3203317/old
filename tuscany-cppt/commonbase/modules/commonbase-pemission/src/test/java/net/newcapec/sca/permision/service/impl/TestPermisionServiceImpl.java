package net.newcapec.sca.permision.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.permision.Permision;
import net.newcapec.sca.permision.service.PermisionService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

/**
 * <p>
 * Title: TestPermisionService
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright(c) 2011 郑州新开普电子股份有限公司
 * </p>
 *
 * @author 黄鑫 (huangxin)
 * @version
 * @data 创建日期：2011-11-11 修改日期： 修改人： 复审人：
 * @generated
 */
public class TestPermisionServiceImpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestPermisionServiceImpl.class);

	Node node;
	public static String sessionId;
	public static SessionService sessionService;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("permisionServiceTest.composite", "target/test-classes");
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

	public void testfindPermisionDojoList() throws NoSuchServiceException {
		PermisionService __service = node.getService(PermisionService.class, "PermisionServiceComponent");

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

		DojoListData d = __service.findPermisionDojoList(__dlp);
		_log.debug(d.getItems().length);
	}

	public void testupdatePermision() throws NoSuchServiceException {
		PermisionService __service = node.getService(PermisionService.class, "PermisionServiceComponent");

		Permision __form = new Permision();
		Random rnd = new Random();
		__form.setCode(1);
		__form.setName(String.valueOf(rnd.nextInt()));
		__form.setUser_unit_code(rnd.nextInt());
		__form.setInfo(String.valueOf(rnd.nextInt()));
		__form.setSys_code(rnd.nextInt());
		__form.setOperate_code(rnd.nextInt());
		__form.setResource_code(rnd.nextInt());
		__form.setIs_enabled(rnd.nextInt());
		__form.setIs_visible(rnd.nextInt());
		__form.setCreate_user_account_id(rnd.nextInt());
		__form.setCreate_date(String.valueOf(rnd.nextInt()));
		__form.setVer(String.valueOf(rnd.nextInt()));

		Permision __new = __service.updatePermision(sessionId, __form);
		_log.debug(__new.getCode());
	}

	public void testinsertPermision() throws NoSuchServiceException {
		PermisionService __service = node.getService(PermisionService.class, "PermisionServiceComponent");

		Permision __form = new Permision();
		Random rnd = new Random();
		__form.setCode(rnd.nextInt());
		__form.setName(String.valueOf(rnd.nextInt()));
		__form.setUser_unit_code(rnd.nextInt());
		__form.setInfo(String.valueOf(rnd.nextInt()));
		__form.setSys_code(rnd.nextInt());
		__form.setOperate_code(rnd.nextInt());
		__form.setResource_code(rnd.nextInt());
		__form.setIs_enabled(rnd.nextInt());
		__form.setIs_visible(rnd.nextInt());
		__form.setCreate_user_account_id(rnd.nextInt());
		__form.setCreate_date(String.valueOf(rnd.nextInt()));
		__form.setVer(String.valueOf(rnd.nextInt()));

		Permision __new = __service.insertPermision(sessionId, __form);
		_log.info("code:" + __new.getCode());
	}

	public void testdelPermisionByIds() throws NoSuchServiceException {
		PermisionService __service = node.getService(PermisionService.class, "PermisionServiceComponent");

		Permision __new = __service.delPermisionByIds(sessionId, "40,41,42");
		_log.info("code:" + __new);
	}

	public void testgetPermisionById() throws NoSuchServiceException {
		PermisionService __service = node.getService(PermisionService.class, "PermisionServiceComponent");

		Permision __new = __service.getPermisionById(sessionId, 1);
		if (__new != null)
			_log.info("code:" + __new.getName());
	}

	public void testgetPermisionListRowCount() throws NoSuchServiceException {
		PermisionService __service = node.getService(PermisionService.class, "PermisionServiceComponent");

		int __count = __service.getPermisionListRowCount(sessionId, null, null);
		_log.info("count:" + __count);
	}

}
