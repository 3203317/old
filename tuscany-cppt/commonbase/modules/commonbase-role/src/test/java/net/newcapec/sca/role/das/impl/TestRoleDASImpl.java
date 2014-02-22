package net.newcapec.sca.role.das.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.role.Role;
import net.newcapec.sca.role.das.RoleDAS;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

/**
 * <p>
 * Title: TestRoleDAS
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
public class TestRoleDASImpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestRoleDASImpl.class);

	Node node;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("roleDASTest.composite", "target/test-classes");
	}

	public void testgetRoleById() throws NoSuchServiceException {
		RoleDAS __service = node.getService(RoleDAS.class, "RoleDASComponent");
		Role d = __service.getRoleById(1);
		_log.debug(d.getName());
		_log.debug(d.getCreate_date());
	}

	public void testfindRoleList() throws NoSuchServiceException {
		RoleDAS __service = node.getService(RoleDAS.class, "RoleDASComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%用户%'");

		// __list.add(__param);

		List d = __service.findRoleList(null, null, __list, 0, 5);
		_log.debug(d.size());
	}

	public void testupdateRole() throws NoSuchServiceException {
		RoleDAS __service = node.getService(RoleDAS.class, "RoleDASComponent");

		Role __form = new Role();
		__form.setCode(11);
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
		__service.updateRole(__form);

	}

	public void testdelRoleByIds() throws NoSuchServiceException {
		RoleDAS __service = node.getService(RoleDAS.class, "RoleDASComponent");

		Integer[] ss = { 10, 11, 12 };

		__service.delRoleByIds(ss);
	}

	public void testinsertRole() throws NoSuchServiceException {
		RoleDAS __service = node.getService(RoleDAS.class, "RoleDASComponent");

		Role __form = new Role();

		Random rnd = new Random();

		__form.setCode(11);
		__form.setName(Integer.toString(rnd.nextInt()));
		__form.setUser_unit_code(rnd.nextInt());
		__form.setDomain_code(4);
		__form.setMax_user_number(5);
		__form.setMax_group_number(6);
		__form.setEnabled_user_number(7);
		__form.setEnabled_group_number(8);
		__form.setEnabled_inherit(9);
		__form.setCreate_user_account_id(10);
		__form.setCreate_date("11");
		__form.setVer("12");

		__service.insertRole(__form);

		_log.info("code:" + __form.getCode());
	}

}
