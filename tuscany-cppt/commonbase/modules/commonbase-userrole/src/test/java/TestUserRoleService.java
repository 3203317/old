import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;
import net.newcapec.sca.userrole.UserRole;
import net.newcapec.sca.userrole.service.UserRoleService;

import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.apache.tuscany.sca.runtime.ActivationException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;


public class TestUserRoleService {
	public static Node node;
	public static UserRoleService testUserRoleService;
	public static SessionService sessionService;
	public static String sessionId;
	public static SequenceService sequenceService;
	public static Long testUserRoleId;
	@BeforeClass
	public static void setUp() throws NoSuchServiceException,ActivationException
	{
		node = TuscanyRuntime.runComposite("userRoleService.composite","target/test-classes");
		testUserRoleService = node.getService(UserRoleService.class, "UserRoleServiceComponent");
		sessionService = node.getService(SessionService.class, "SessionComponent");
		sequenceService = node.getService(SequenceService.class, "SequenceServiceComponent");
		
		Session session = new Session();
		session.setAge("1800");
		session.setDomain_code("1");
		session.setUnit_code("1");
		session.setUser_code("1");
		session.setContent("dddddsdfds222222");
		session.setInvalid_date("");
		session = sessionService.createSession(session);
		sessionId = session.getId();
	}

	@Test
	public void insertUserRole()
	{
		UserRole userRole = new UserRole();

		userRole.setAccount_id("1");
		userRole.setCreate_date("2012-11-02 19:34:49");
		userRole.setCreate_user_account_id("1");
		userRole.setRole_code("1");
		userRole.setVer("1");

		testUserRoleService.insertUserRole(sessionId, userRole);
		
		testUserRoleId = Long.valueOf(sequenceService.getCurrentValue("getMaxUserRoleID"));
	}

	@Test
	public void findListUserRole()
	{
		DojoListParam doParam = new DojoListParam();
		doParam.setSessionId(sessionId);
		List<FilterParam> filterList = new ArrayList<FilterParam>();
		FilterParam filter = new FilterParam();
		filter.setField("dept_code");
		filter.setLogical("=");
		filter.setRelation("and");
		filter.setValue("19");
		filterList.add(filter);
		doParam.setFilter(filterList);
		doParam.setBegin(0);
		doParam.setLimit(3);
		DojoListData doListData = testUserRoleService.findUserAndRoles(doParam);
		Object[] allInfo = doListData.getItems();
		for(int i = 0 ; i < allInfo.length ; i++)
		{
			LinkedHashMap item = (LinkedHashMap)allInfo[i];
			System.out.println(item.get("account_id"));
		}
		System.out.println(testUserRoleService.findUserRoleByUserId(sessionId, 1).size());
		System.out.println(testUserRoleService.findUserRoleList(sessionId, null, null, null, null).size());
	}
	@Test
	public void updateUserRole()
	{
		UserRole userRole = new UserRole();
		userRole.setCode(testUserRoleId.toString());
		userRole.setCreate_date("2013-11-02 19:34:49");
		testUserRoleService.updateUserRole(sessionId, userRole);
	}
	@Test
	public void getRolesWhichWillBeTest()
	{
		SelectItem[] rtnList = testUserRoleService.getRolesWhichWillBe(sessionId,"2");

		for(SelectItem item : rtnList)
		{
			System.out.println("code : " + item.getId() + " name : " + item.getName());
		}
	}
	@Test
	public void deleteUserRoleBYCode()
	{
		testUserRoleService.delUserRoleById(sessionId, testUserRoleId.intValue());
	}
	@AfterClass
	public static void tearDown()
	{
		sessionService.invalid(sessionId);
		node.stop();
	}
}
