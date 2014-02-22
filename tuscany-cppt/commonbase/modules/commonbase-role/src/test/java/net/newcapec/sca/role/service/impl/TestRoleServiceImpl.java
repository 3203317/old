package net.newcapec.sca.role.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.role.Role;
import net.newcapec.sca.role.service.RoleService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

/**
 * <p>
 * Title: TestRoleService
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
public class TestRoleServiceImpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestRoleServiceImpl.class);

	public static RoleService __service;
	Node node;
	public static String sessionId;
	public static SessionService sessionService;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("roleServiceTest.composite", "target/test-classes");
		__service = node.getService(RoleService.class, "RoleServiceComponent");
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

	public void testfindRoleDojoList() throws NoSuchServiceException {

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

		DojoListData d = __service.findRoleDojoList(__dlp);
		_log.debug(d.getItems().length);
	}

	public void testupdateRole() throws NoSuchServiceException {

		Role __form = new Role();
		__form.setCode(1);
		__form.setName("2");
		__form.setUser_unit_code(3);
		__form.setDomain_code(4);
		__form.setMax_user_number(5);
		__form.setMax_group_number(6);
		__form.setEnabled_user_number(7);
		__form.setEnabled_group_number(8);
		__form.setEnabled_inherit(9);
		__form.setCreate_user_account_id(10);
		__form.setCreate_date("11");
		__form.setVer("12");

		Role __new = __service.updateRole(sessionId, __form);
		_log.debug(__new.getCode());
	}

	public void testinsertRole() throws NoSuchServiceException {

		Random rnd = new Random();
		Role __form = new Role();
		__form.setCode(1);
		__form.setName(Integer.toString(rnd.nextInt()));
		__form.setUser_unit_code(3);
		__form.setDomain_code(4);
		__form.setMax_user_number(5);
		__form.setMax_group_number(6);
		__form.setEnabled_user_number(7);
		__form.setEnabled_group_number(8);
		__form.setEnabled_inherit(9);
		__form.setCreate_user_account_id(10);
		__form.setCreate_date("11");
		__form.setVer("12");

		Role __new = __service.insertRole(sessionId, __form);
		_log.info("code:" + __new.getCode());
	}

	public void testdelRoleByIds() throws NoSuchServiceException {

		Role __new = __service.delRoleByIds(sessionId, "0,0");
		_log.info("code:" + __new);
	}

	public void testgetRoleById() throws NoSuchServiceException {

		Role __new = __service.getRoleById(sessionId, 1);
		_log.info("code:" + __new.getName());
	}

	public void testgetRoleListRowCount() throws NoSuchServiceException {

		int __count = __service.getRoleListRowCount(sessionId, null, null);
		_log.info("count:" + __count);
	}

}
