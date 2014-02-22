package net.newcapec.sca.reportpublish.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.reportpublish.ReportPublish;
import net.newcapec.sca.reportpublish.service.ReportPublishService;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestReportPublishServiceImpl {
	private static final Logger _log = Logger
	.getLogger(TestReportPublishServiceImpl.class);

    private  static   Node node;
    private  static   ReportPublishService  reportPublishService;
    private  static   SessionService sessionService;
    private  static   String sessionId;
    public   static   SequenceService  sequenceService;
    public   static   Integer  testComponentDatasetId;
    @BeforeClass
	public static  void  setUp()  throws Exception {
		 node = TuscanyRuntime.runComposite("reportPublishService.composite","target/test-classes");
		 reportPublishService = node.getService(ReportPublishService.class,"ReportPublishServiceComponent");
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
    public void testFindUnpublishReportDojoList(){
    	List<FilterParam> __list = new ArrayList<FilterParam>();
		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%%'");
		__list.add(__param);
		DojoListParam __dlp = new DojoListParam();
		__dlp.setFilter(__list);
		__dlp.setBegin(0);
		__dlp.setLimit(20);
		__dlp.setSessionId(sessionId);
		reportPublishService.findUnpublishReportDojoList(__dlp);

    }

    @Test
    public  void testPublishReport(){
    	ReportPublish  reportPublish = new ReportPublish();
    	reportPublish.setCode(3);
    	reportPublish.setMenu_name("报表发布测试");
    	reportPublish.setMemo("报表发布测试");
    	reportPublish.setGrant_permission(1);
    	reportPublish.setType(1);
    	//reportPublishService.publishReport(sessionId,reportPublish);
    }

    @Test
    public  void testRevokePublishReport(){
    	ReportPublish  reportPublish = new ReportPublish();
    	reportPublish.setCode(3);
    	reportPublish.setResource_code(29);
    	//reportPublishService.revokePublishReport(sessionId,reportPublish);
    }

    @AfterClass
    public  static void tearDown()
	 {
    	 sessionService.invalid(sessionId);
	     node.stop();
	 }

}
