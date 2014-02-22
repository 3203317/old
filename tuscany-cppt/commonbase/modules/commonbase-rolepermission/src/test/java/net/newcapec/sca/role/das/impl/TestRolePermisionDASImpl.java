package net.newcapec.sca.role.das.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;
import net.newcapec.sca.role.RolePermision;
import net.newcapec.sca.role.das.RolePermisionDAS;
import net.newcapec.sca.param.FilterParam;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

/**
 * <p>
 * Title: TestRolepermisionDAS
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
public class TestRolePermisionDASImpl extends TestCase {

	private static final Logger _log = Logger
			.getLogger(TestRolePermisionDASImpl.class);

	Node node;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("rolePermisionDASTest.composite",
				"target/test-classes");
	}

	public void testgetRolePermisionById() throws NoSuchServiceException {
		RolePermisionDAS __service = node.getService(RolePermisionDAS.class,
				"RolePermisionDASComponent");
		RolePermision d = __service.getRolePermisionById(1);
		//_log.debug(d.getCreate_date());
	}

	public void testfindRolePermisionList() throws NoSuchServiceException {
		RolePermisionDAS __service = node.getService(RolePermisionDAS.class,
				"RolePermisionDASComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%用户%'");

//		__list.add(__param);

		List d = __service.findRolePermisionList(null, null, __list, 0, 5);
		_log.debug(d.size());
	}

	public void testupdateRolePermision() throws NoSuchServiceException {
		RolePermisionDAS __service = node.getService(RolePermisionDAS.class,
				"RolePermisionDASComponent");

		RolePermision __form = new RolePermision();
		Random rnd = new Random();
		__form.setCode(rnd.nextInt());
		__form.setRole_code(rnd.nextInt());
		__form.setPermission_code(rnd.nextInt());
		__form.setCreate_user_account_id(rnd.nextInt());
		__form.setCreate_date(String.valueOf(rnd.nextInt()));
		__form.setVer(String.valueOf(rnd.nextInt()));
		__service.updateRolePermision(__form);

	}

	public void testdelRolePermisionByIds() throws NoSuchServiceException {
		RolePermisionDAS __service = node.getService(RolePermisionDAS.class,
				"RolePermisionDASComponent");

		Integer[] ss = { 10, 11, 12 };

		__service.delRolePermisionByIds(ss);
	}

	public void testinsertRolePermision() throws NoSuchServiceException {
		RolePermisionDAS __service = node.getService(RolePermisionDAS.class,
				"RolePermisionDASComponent");

		RolePermision __form = new RolePermision();

		Random rnd = new Random();

		__form.setCode(rnd.nextInt());
		__form.setRole_code(rnd.nextInt());
		__form.setPermission_code(rnd.nextInt());
		__form.setCreate_user_account_id(rnd.nextInt());
		__form.setCreate_date(String.valueOf(rnd.nextInt()));
		__form.setVer(String.valueOf(rnd.nextInt()));

		__service.insertRolePermision(__form);

		_log.info("code:" + __form.getCode());
	}

}
