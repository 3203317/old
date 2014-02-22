package net.newcapec.sca.permision.das.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;
import net.newcapec.sca.darule.DataAccessRule;
import net.newcapec.sca.darule.das.DataAccessRuleDAS;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.permision.Permision;
import net.newcapec.sca.permision.das.PermisionDAS;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

/**
 * <p>
 * Title: TestPermisionDAS
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
public class TestPermisionDASImpl extends TestCase {

	private static final Logger _log = Logger.getLogger(TestPermisionDASImpl.class);

	Node node;

	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("permisionDASTest.composite", "target/test-classes");
	}

	public void testgetPermisionById() throws NoSuchServiceException {
		PermisionDAS __service = node.getService(PermisionDAS.class, "PermisionDASComponent");
		Permision d = __service.getPermisionById(1);
		if (d == null) {
			_log.debug("不存在该条记录");
		} else {
			_log.debug(d.getName());
			_log.debug(d.getCreate_date());
		}
	}

	public void testfindPermisionList() throws NoSuchServiceException {
		PermisionDAS __service = node.getService(PermisionDAS.class, "PermisionDASComponent");

		List<FilterParam> __list = new ArrayList<FilterParam>();

		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%用户%'");

		// __list.add(__param);

		List d = __service.findPermisionList(null, null, __list, 0, 5);
		_log.debug(d.size());
	}

	public void testupdatePermision() throws NoSuchServiceException {
		PermisionDAS __service = node.getService(PermisionDAS.class, "PermisionDASComponent");

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
		__service.updatePermision(__form);

	}

	public void testdelPermisionByIds() throws NoSuchServiceException {
		PermisionDAS __service = node.getService(PermisionDAS.class, "PermisionDASComponent");

		Integer[] ss = { -1 };

		__service.delPermisionByIds(ss);
	}

	public void testinsertPermision() throws NoSuchServiceException {
		PermisionDAS __service = node.getService(PermisionDAS.class, "PermisionDASComponent");

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

		__service.insertPermision(__form);

		_log.info("code:" + __form.getCode());
	}

}
