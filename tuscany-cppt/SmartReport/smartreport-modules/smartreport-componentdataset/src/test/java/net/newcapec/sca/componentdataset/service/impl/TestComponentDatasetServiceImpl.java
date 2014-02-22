package net.newcapec.sca.componentdataset.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.componentdataset.ComponentDataset;
import net.newcapec.sca.componentdataset.service.ComponentDatasetService;
import net.newcapec.sca.diccode.DicTable;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestComponentDatasetServiceImpl {
	private static final Logger _log = Logger
	.getLogger(TestComponentDatasetServiceImpl.class);

    private  static   Node node;
    private  static   ComponentDatasetService  componentDatasetService;
    private  static   SessionService sessionService;
    private  static   String sessionId;
    public   static   SequenceService  sequenceService;
    public   static   Integer  testComponentDatasetId;
    @BeforeClass
	public static  void  setUp()  throws Exception {
		 node = TuscanyRuntime.runComposite("componentDatasetService.composite","target/test-classes");
		 componentDatasetService = node.getService(ComponentDatasetService.class,"ComponentDatasetServiceComponent");
		 sessionService = node.getService(SessionService.class, "SessionComponent");
		 sequenceService = node.getService(SequenceService.class, "SequenceServiceComponent");
		 Session session = new Session();
		 session.setAge("123401111");
		 session.setDomain_code("1");
		 session.setUnit_code("1");
		 session.setUser_code("1");
		 session.setContent("dddddsdfds222222");
		 session.setInvalid_date("");
		 session = sessionService.createSession(session);
		 sessionId = session.getId();
	}
    @Test
    public void testGetComponentDatasetById(){
    	ComponentDataset componentDataset ;
    	componentDataset = componentDatasetService.getComponentDatasetById(sessionId, 1);
    }

    @Test
    public void testFindComponentDatasetDojoList(){
    	List<FilterParam> __list = new ArrayList<FilterParam>();
		FilterParam __param = new FilterParam();
		__param.setField("dataset_name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%%'");
		__list.add(__param);
		DojoListParam __dlp = new DojoListParam();
		__dlp.setFilter(__list);
		__dlp.setBegin(0);
		__dlp.setLimit(5);
		__dlp.setSessionId(sessionId);
    	componentDatasetService.findComponentDatasetDojoList(__dlp);

    }
    @Test
    public  void testInsertComponentDataset(){

    	ComponentDataset componentDataset = new  ComponentDataset();
    	componentDataset.setDomain_code(1);
    	componentDataset.setUnit_code(1);
    	componentDataset.setComponent_type("test");
    	componentDataset.setComponent_datasource("test");
    	componentDataset.setDataset_name("test");
    	componentDataset.setText_field("test");
    	componentDataset.setValue_field("test");
    	componentDataset.setParent_field("test");
    	componentDataset.setTop_default("test");
    	componentDatasetService.insertComponentDataset(sessionId, componentDataset);
    	testComponentDatasetId = sequenceService.getCurrentValue("getMaxComponentDatasetID");
//    	System.out.println("id:"+testComponentDatasetId);
    }

    @Test
    public  void testUpdateComponentDataset(){

    	ComponentDataset componentDataset = new  ComponentDataset();
    	componentDataset.setCode(testComponentDatasetId);
    	componentDataset.setComponent_type("testupdate");
    	componentDataset.setComponent_datasource("testupdate");
    	componentDataset.setDataset_name("test");
    	componentDataset.setText_field("test");
    	componentDataset.setValue_field("test");
    	componentDataset.setParent_field("test");
    	componentDataset.setTop_default("test");
    	componentDatasetService.updateComponentDataset(sessionId, componentDataset);

    }

    @Test
    public  void testDelComponentDatasetByIds(){
    	componentDatasetService.delComponentDatasetByIds(sessionId, String.valueOf(testComponentDatasetId));
    }

    @Test
    public void testObtainComponentDatasource(){
    	componentDatasetService.obtainComponentDatasource(sessionId);
    }

    @Test
    public void testObtainFieldByDatasource(){
    	componentDatasetService.obtainFieldByDatasource(sessionId, "150");

    }

    @AfterClass
    public  static void tearDown()
	 {
    	 sessionService.invalid(sessionId);
	     node.stop();
	 }

}
