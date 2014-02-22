import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.gridcondition.GridCondition;
import net.newcapec.sca.gridcondition.GridConditonDataCollection;
import net.newcapec.sca.gridcondition.service.GridConditionService;
import net.newcapec.sca.gridview.GridView;
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


public class TestGridConditionService {
	public static Node node;
	public static GridConditionService gridConditionService;
	public static SessionService sessionService;
	public static String sessionId;
	public static SequenceService sequenceService;
	public static Long testGridConditionId;
	@BeforeClass
	public static void setUp()throws NoSuchServiceException
	{
		try
		{
			node = TuscanyRuntime.runComposite("GridConditionService.composite","target/test-classes");
			gridConditionService = node.getService(GridConditionService.class, "GridConditionServiceComponent");
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
	public void insertGridCondition()
	{
		GridCondition gridCondition = new GridCondition();
		gridCondition.setDefault_value("1");
		gridCondition.setField_name("1");
		gridCondition.setForm_code("1");
		gridCondition.setGroup_name("111");
		gridCondition.setParent_condition("1");
		gridCondition.setRelation("1");
		gridCondition.setType("1");

		gridConditionService.insertGridCondition(sessionId, gridCondition);
		testGridConditionId = Long.valueOf(this.sequenceService.getCurrentValue("getMaxGridConditionID"));
	}
	@Test
	public void updateGridCondition()
	{
		GridCondition gridCondition = new GridCondition();
		gridCondition.setCode(testGridConditionId.toString());
		gridCondition.setDefault_value("朱祝元");
		gridConditionService.updateGridCondition(sessionId, gridCondition);
		List list = gridConditionService.findGridConditionList(sessionId, null, null, null, null);
		if(null != list && list.size() >0)
		{
			System.out.println(list.size());
		}
		else
		{
			System.out.println("数据为空");
		}
	}

	@Test
	public void testGetGridConditionByFormCode()
	{
		List<GridCondition> list = gridConditionService.getGridConditionByFormCode(sessionId, 24);
		for(GridCondition gridItem : list)
		{
			System.out.println("code = " + gridItem.getCode() +
					", form_code = " + gridItem.getForm_code() +
					", field_name = " + gridItem.getField_name() +
					", relation = " + gridItem.getRelation() +
					", parent_condition = " + gridItem.getParent_condition() +
					", group_name = " + gridItem.getGroup_name() +
					", type = " + gridItem.getType() +
					", default_value = " + gridItem.getDefault_value() +
					", condition_name = " + gridItem.getCondition_name());
		}
	}
	@Test
	public void testgetGridConditonDataCollection(){
		List<FilterParam> filter = new ArrayList<FilterParam>();
		FilterParam param = new FilterParam();
		param.setField("code_dictionary_name");
		param.setLogical("=");
		param.setRelation("and");
		param.setValue("'RELATION'");
		filter.add(param);
		GridConditonDataCollection gridConditonDataCollection=gridConditionService.getGridConditonDataCollection(sessionId, 16, 17, filter);
		System.out.println("dis   ==== " +gridConditonDataCollection.getDis().size());
		System.out.println("field   ==== " +gridConditonDataCollection.getField().size());
		GridView gridView= gridConditonDataCollection.getGridView();
		if(null != gridView)
		{
			System.out.println("gridview ===== "+gridView.getCode());
		}
	}
	@Test
	public void deleteTestData()
	{
		gridConditionService.delGridConditionById(sessionId, this.testGridConditionId.intValue());
	}
	@AfterClass
	public static void tearDown()
	{
		sessionService.invalid(sessionId);
		node.stop();
	}
}
