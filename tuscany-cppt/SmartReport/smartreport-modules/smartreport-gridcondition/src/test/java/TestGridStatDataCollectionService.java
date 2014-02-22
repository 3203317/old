import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.gridcondition.GridStatDataCollection;
import net.newcapec.sca.gridcondition.service.GridStatDataCollectionService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;


public class TestGridStatDataCollectionService {

	public static Node node;
	public static GridStatDataCollectionService gridStatDataCollectionService;
	public static SessionService sessionService;
	public static String sessionId;
	@BeforeClass
	public static void setUp()throws NoSuchServiceException
	{
		try
		{
			node = TuscanyRuntime.runComposite("GridStatDataCollectionService.composite","target/test-classes");
			gridStatDataCollectionService = node.getService(GridStatDataCollectionService.class, "GridStatDataCollectionServiceComponent");
			sessionService = node.getService(SessionService.class, "SessionComponent");

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
	public void testFunc()
	{
		List<FilterParam> filter = new ArrayList<FilterParam>();
		FilterParam param = new FilterParam();
		param.setField("code_dictionary_name");
		param.setLogical("=");
		param.setRelation("and");
		param.setValue("'tjlx'");
		filter.add(param);
		GridStatDataCollection gsdc = gridStatDataCollectionService.getGridStatDataCollection(sessionId, 16, filter);

		System.out.println("gsdc.getDicItemList" + gsdc.getDicItemList().size());
		System.out.println("gsdc.getFieldPrepList" + gsdc.getFieldPrepList().size());
	}

	@AfterClass
	public static void tearDown()
	{
		sessionService.invalid(sessionId);
		node.stop();
	}
}
