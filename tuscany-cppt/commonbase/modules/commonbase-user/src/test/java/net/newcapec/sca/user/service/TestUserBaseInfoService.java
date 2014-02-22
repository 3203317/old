package net.newcapec.sca.user.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;
import net.newcapec.sca.user.UserBaseInfo;

import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestUserBaseInfoService {

	private static Node node;
	private static UserBaseInfoService userBaseInfoService;
	public static SessionService sessionService;
	public static SequenceService sequenceService;
	public static Long testUserId;
	public static String sessionId;


	@BeforeClass
	public static void  setUp()  throws Exception {

		node = TuscanyRuntime.runComposite("userBaseInfoService.composite","target/test-classes");
		userBaseInfoService = node.getService(UserBaseInfoService.class,"UserBaseInfoServiceComponent");
		sessionService = node.getService(SessionService.class, "SessionComponent");
		sequenceService = node.getService(SequenceService.class, "SequenceServiceComponent");
		
		Session session = new Session();
		session.setAge("1800");
		session.setDomain_code("1");
		session.setUnit_code("1");
		session.setUser_code("9");
		session.setContent("dddddsdfds222222");
		session.setInvalid_date("");
		session = sessionService.createSession(session);
		sessionId = session.getId();
	}

	@Test
	public  void  testInsertUserBaseInfo() throws Exception{

		UserBaseInfo userBaseInfo = new UserBaseInfo();
		userBaseInfo.setName("admin");
		userBaseInfo.setCode(new Date().toString());
		userBaseInfo.setPassword("123456");
		userBaseInfo.setActivation_date("2012-12-14 1:00:00");
		userBaseInfo.setActivation_type_code("1");
		userBaseInfo.setCreate_date("2012-12-14 1:00:00");
		userBaseInfo.setCur_use_date("2012-12-14 1:00:00");
		userBaseInfo.setCust_skin("1");
		userBaseInfo.setDept_code("1");
		userBaseInfo.setEncryption_info("1");
		userBaseInfo.setEncryption_timestamp("1");
		userBaseInfo.setLogin_alias("2");
		userBaseInfo.setNo_use_date("2012-12-14 1:00:00");
		userBaseInfo.setOpen_date("2012-12-14 1:00:00");
		userBaseInfo.setPassword("1");
		userBaseInfo.setRecord_status("2");
		userBaseInfo.setSex("1");
		userBaseInfo.setStatus_code("1");
		userBaseInfo.setUser_type_code("1");
		userBaseInfo.setUser_unit_code("1");
		userBaseInfo.setUuid("2");
		userBaseInfo.setVer("1");
		userBaseInfo.setCreate_user_account_id("1");
		System.out.println(userBaseInfoService.insertUserBaseInfo(sessionId, userBaseInfo).getResultMsg().getStatus());
		testUserId = Long.valueOf(sequenceService.getCurrentValue("getMaxUserBaseInfoID"));
	}

	@Test
	public  void  testUpdateUserBaseInfo() {
		UserBaseInfo userBaseInfo = new UserBaseInfo();
		userBaseInfo.setAccount_id(testUserId.toString());
		userBaseInfo.setName("2012-12-14");
		userBaseInfo.setOpen_date("2012-12-14 00:00:00");
		userBaseInfo.setNo_use_date("2013-12-14 00:00:00");
		userBaseInfoService.updateUserBaseInfo(sessionId, userBaseInfo);
	}
	@Test
	public void testGetList()
	{
		List<FilterParam> listFilter = new ArrayList<FilterParam>();
		FilterParam filter = new FilterParam();
		filter.setField("dept_code");
		filter.setLogical("=");
		filter.setRelation("and");
		filter.setValue("1");
		listFilter.add(filter);
		System.out.println(userBaseInfoService.findUserBaseInfoList(sessionId, null, listFilter, null, null).size());
	}
	@Test
	public void testDeleteUserBaseInfo()
	{
		userBaseInfoService.delUserBaseInfoById(sessionId, testUserId.intValue());
	}
	@AfterClass
	public static void tearDown()
	{
		sessionService.invalid(sessionId);
		node.stop();
	}
}
