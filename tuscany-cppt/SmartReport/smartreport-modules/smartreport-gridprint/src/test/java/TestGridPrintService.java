import net.newcapec.sca.gridprint.GridPrint;
import net.newcapec.sca.gridprint.service.GridPrintService;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;



public class TestGridPrintService {
	public static Node node;
	public static GridPrintService gridprintService;
	public static SessionService sessionService;
	public static String sessionId;
	public static SequenceService sequenceService;
	public static Long testGridPrintId;
	@BeforeClass
	public static void setUp()throws NoSuchServiceException
	{
		try
		{
			node = TuscanyRuntime.runComposite("GridPrintService.composite","target/test-classes");
			gridprintService = node.getService(GridPrintService.class, "GridPrintServiceComponent");
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
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test
	public void testInsertGridPrint()
	{
		GridPrint gridPrint = new GridPrint();
		gridPrint.setForm_code("3");
		gridPrint.setPrint_parm("2");
		gridPrint.setTemp_code("3");
		gridprintService.insertGridPrint(sessionId,gridPrint);
		this.testGridPrintId = Long.valueOf(this.sequenceService.getCurrentValue("getMaxGridPrintID"));
	}
	@Test
	public void testGetGridPrintById()
	{
		GridPrint gridPrint = gridprintService.getGridPrintById(sessionId,testGridPrintId.intValue());
		if(null != gridPrint)
		{
			System.out.println(gridPrint.getCode());
		}
		else
		{
			System.out.println("查找的实体不存在");
		}
	}
	@Test
	public void testUpdateGridPrint()
	{
		GridPrint gridPrint = new GridPrint();
		gridPrint.setCode(this.testGridPrintId.toString());
		gridPrint.setForm_code("5");
		System.out.println(gridprintService.updateGridPrint(sessionId,gridPrint));
	}
	@Test
	public void testFindDOList()
	{
		System.out.println(gridprintService.findGridPrintList(sessionId,null, null, null, null).size());
	}
	@Test
	public void testDeleteGirdPrint()
	{
		gridprintService.delGridPrintById(sessionId, testGridPrintId.intValue());
	}

	@AfterClass
	public static void tearDown()
	{
		sessionService.invalid(sessionId);
		node.stop();
	}


}
