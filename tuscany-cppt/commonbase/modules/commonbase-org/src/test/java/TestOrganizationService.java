

import java.util.Date;

import net.newcapec.sca.org.Organization;
import net.newcapec.sca.org.service.OrganizationService;
import net.newcapec.sca.resource.bean.MenuItem;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

public class TestOrganizationService {
	public static Node node;
	public static OrganizationService organizationService;
	public static SessionService sessionService;
	public static String sessionId;
	public static SequenceService sequenceService;
	public static Long testOrgId;
	@BeforeClass
	public static void setUp() throws NoSuchServiceException
	{
		node = TuscanyRuntime.runComposite("organizationService.composite","target/test-classes");
		organizationService = node.getService(OrganizationService.class, "OrganizationServiceComponent");
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
	public void TestinsertOrg()
	{
		Organization org = new Organization();
		org.setUser_unit_code("1");
		org.setName(new Date().toString());
		org.setStatus("1");
		org.setSuper_code("1");
		org.setType_code("1");
		org.setIs_have_child("1");
		org.setDepartment_level("1");
		org.setCode_path("1");
		org.setBatch_code("1");
		org.setNo_use_date("2012-10-31 10:58:11");
		org.setOpen_date("2012-10-31 10:58:12");
		org.setCur_use_date("2012-10-31 10:58:13");
		org.setUuid("1");
		org.setCreate_user_account_id("1");
		org.setCreate_date("2012-10-31 10:58:13");
		org.setVer("1");
		organizationService.insertOrg(sessionId,org);
		
		testOrgId = Long.valueOf(sequenceService.getCurrentValue("getMaxOrganizationID"));
	}
	@Test
	public void testGetOrganizationById()
	{

		Organization organization = organizationService.getOrgById(sessionId,testOrgId.intValue());
		System.out.println(organization.getName());
	}

	@Test
	public void TestFindOrgList()
	{
		MenuItem[] array = organizationService.getOrganizationTree(sessionId,"");
		System.out.println(array.length);
	}
	

	@Test
	public void TestUpdateOrg()
	{
		Organization org = new Organization();
		org.setCode(testOrgId.toString());
		org.setName("zpf");
		organizationService.updateOrg(sessionId, org);
	}
	@Test
	public void testgetOrganizationTree()
	{
		MenuItem[] rtnArray = organizationService.getOrganizationTree(sessionId,"19");
		for(MenuItem item : rtnArray)
		{
			System.out.println(item.getId());
		}
	}
	@Test
	public void testDeleteOrg()
	{
		organizationService.delOrgById(sessionId, testOrgId.intValue());
	}
	@AfterClass
	public static void tearDown()
	{
		sessionService.invalid(sessionId);
		node.stop();
	}

}
