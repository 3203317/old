import net.newcapec.sca.operation.Operation;
import net.newcapec.sca.operation.service.OperationService;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;


public class TestOperateService {
	public static Node node;
	public static OperationService operationService;
	public static SessionService sessionService;
	public static String sessionId;
	public static SequenceService sequenceService;
	public static Long testOperatorId;
	@BeforeClass
	public static void setUp() throws NoSuchServiceException
	{
		node = TuscanyRuntime.runComposite("operateService.composite","target/test-classes");
		operationService = node.getService(OperationService.class, "OperationServiceComponent");
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
	public void insertOperation()
	{
//		if(null != operationService.getOperationById(sessionId,1))
//		{
//			
//		}

		Operation operation = new Operation();

		operation.setCreate_user_account_id("1");
		operation.setInfo("1");
		operation.setName("1");
		operation.setResource_type_code("1");
		operation.setSortid("1");
		operation.setUser_unit_code("1");
		operation.setUuid("1");
		operation.setVer("1");

		operationService.insertOperation(sessionId, operation);
		testOperatorId = Long.valueOf(sequenceService.getCurrentValue("getMaxOperationID"));
	}

	@Test
	public void updateOperation()
	{
		Operation operation = new Operation();
		operation.setCode(testOperatorId.toString());
		operation.setCreate_date("2013-11-1 15:31:43");
		operationService.updateOperation(sessionId, operation);

	}
	@Test
	public void findListOperation()
	{

		System.out.println(operationService.findOperationList(sessionId, null, null, 0, 5).size());
		System.out.println(operationService.findOperationListByType(sessionId, 1).size());
	}
	@Test
	public void deleteOperationById()
	{
		operationService.delOperationById(sessionId, testOperatorId.intValue());
	}
	@AfterClass
	public static void tearDown()
	{
		sessionService.invalid(sessionId);
		node.stop();
	}
}
