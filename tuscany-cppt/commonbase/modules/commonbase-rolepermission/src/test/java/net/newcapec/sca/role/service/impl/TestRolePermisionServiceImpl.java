package net.newcapec.sca.role.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;
import net.newcapec.sca.role.RolePermision;
import net.newcapec.sca.role.service.RolePermisionService;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

/**
 * <p>
 * Title: TestRolepermisionService
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
public class TestRolePermisionServiceImpl extends TestCase {

	private static final Logger _log = Logger
			.getLogger(TestRolePermisionServiceImpl.class);

	Node node;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("rolePermisionServiceTest.composite",
				"target/test-classes");
	}

	public void testfindRolePermisionDojoList() throws NoSuchServiceException {
		RolePermisionService __service = node.getService(RolePermisionService.class,
				"RolePermisionServiceComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%用户%'");

		__list.add(__param);

		DojoListParam __dlp = new DojoListParam();
		__dlp.setBegin(0);
		__dlp.setLimit(5);

		DojoListData d = __service.findRolePermisionDojoList(__dlp);
		_log.debug(d.getItems().length);
	}

	public void testupdateRolePermision() throws NoSuchServiceException {
		RolePermisionService __service = node.getService(RolePermisionService.class,
				"RolePermisionServiceComponent");

		RolePermision __form = new RolePermision();
		Random rnd = new Random();
		__form.setCode(rnd.nextInt());
		__form.setRole_code(rnd.nextInt());
		__form.setPermission_code(rnd.nextInt());
		__form.setCreate_user_account_id(rnd.nextInt());
		__form.setCreate_date(String.valueOf(rnd.nextInt()));
		__form.setVer(String.valueOf(rnd.nextInt()));

		RolePermision __new = __service.updateRolePermision(null, __form);
		_log.debug(__new.getCode());
	}

	public void testinsertRolePermision() throws NoSuchServiceException {
		RolePermisionService __service = node.getService(RolePermisionService.class,
				"RolePermisionServiceComponent");

		RolePermision __form = new RolePermision();
		Random rnd = new Random();
		__form.setCode(rnd.nextInt());
		__form.setRole_code(rnd.nextInt());
		__form.setPermission_code(rnd.nextInt());
		__form.setCreate_user_account_id(rnd.nextInt());
		__form.setCreate_date(String.valueOf(rnd.nextInt()));
		__form.setVer(String.valueOf(rnd.nextInt()));

		RolePermision __new = __service.insertRolePermision(null, __form);
		_log.info("code:" + __new.getCode());
	}

	public void testdelRolePermisionByIds() throws NoSuchServiceException {
		RolePermisionService __service = node.getService(RolePermisionService.class,
				"RolePermisionServiceComponent");

		RolePermision __new = __service.delRolePermisionByIds(null, "0,0");
		_log.info("code:" + __new);
	}

	public void testgetRolePermisionById() throws NoSuchServiceException {
		RolePermisionService __service = node.getService(RolePermisionService.class,
				"RolePermisionServiceComponent");

		RolePermision __new = __service.getRolePermisionById(null, 1);
		//_log.info("code:" + __new.getCreate_date());
	}

	public void testgetRolePermisionListRowCount() throws NoSuchServiceException {
		RolePermisionService __service = node.getService(RolePermisionService.class,
				"RolePermisionServiceComponent");

		int __count = __service.getRolePermisionListRowCount(null, null, null);
		_log.info("count:" + __count);
	}

}
