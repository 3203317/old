package net.newcapec.sca.formlayout.service.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.annotation.Reference;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.formlayout.FormLayout;
import net.newcapec.sca.formlayout.service.FormLayoutService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

public class TestFormLayoutServiceImpl {
	private static final Logger _log = Logger
	.getLogger(TestFormLayoutServiceImpl.class);

    private   static   Node node;
	private   static   FormLayoutService __service;
	private   static   SessionService sessionService;
	private   static   String sessionId;
	public    static   SequenceService  sequenceService;
    public    static   Integer  testFormLayoutId;

	@BeforeClass
	public static  void  setUp()  throws Exception {
		  node = TuscanyRuntime.runComposite("formLayoutService.composite",
				"target/test-classes");
		  __service = node.getService(FormLayoutService.class,
		  "FormLayoutServiceComponent");
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
	public  void  testGetFormLayoutById(){
		 System.out.println("1111");
		 FormLayout  _formLayout  = new FormLayout();
		 _formLayout = __service.getFormLayoutById(sessionId, 1);

	}

	@Test
	public  void  testFindFormLayoutList(){
		 System.out.println("2222");
		 List<FilterParam> __list = new ArrayList<FilterParam>();
		 FilterParam __param = new FilterParam();
		 __param.setField("custom_content");
		 __param.setLogical("like");
		 __param.setRelation("and");
		 __param.setValue("'%1%'");
		 __list.add(__param);
		 List<FormLayout> d =  __service.findFormLayoutList(sessionId, null, __list, 0, 5);
		 if (d != null ){
		     System.out.println(d.size());
		 }
	}

	@Test
	public  void  testInsertFormLayout(){
		System.out.println("3333");
    	FormLayout _formLayout = new FormLayout();
    	_formLayout.setForm_code(7);
    	_formLayout.setCustom_type(7);
    	_formLayout.setCustom_content("17");
    	_formLayout.setTemp_content("17");
    	_formLayout.setPrint_temp_code(7);
		__service.insertFormLayout(sessionId,_formLayout);
		testFormLayoutId = sequenceService.getCurrentValue("getMaxFormLayoutID");

	}

	@Test
	public  void testUpdateFormLayout(){
		System.out.println("4444");
    	FormLayout _formLayout = new FormLayout();
    	_formLayout.setCode(testFormLayoutId);
    	_formLayout.setForm_code(7);
    	_formLayout.setCustom_type(7);
    	_formLayout.setCustom_content("17");
    	_formLayout.setTemp_content("17");
    	_formLayout.setPrint_temp_code(7);
    	__service.updateFormLayout(sessionId,_formLayout);

	}

	@Test
	public  void testDelFormLayoutByIds(){
		System.out.println("5555");
//    	__service.delFormLayoutByIds(sessionId,"9,10");
		__service.delFormLayoutByIds(sessionId,String.valueOf(testFormLayoutId));

	}

	@Test
	public void testFindFormLayoutDojoList(){
		System.out.println("6666");
    	List<FilterParam> __list = new ArrayList<FilterParam>();
    	FilterParam __param = new FilterParam();
		__param.setField("custom_content");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%1%'");
		__list.add(__param);
		DojoListParam __dlp = new DojoListParam();
		__dlp.setFilter(__list);
		__dlp.setBegin(0);
		__dlp.setLimit(5);
		__dlp.setSessionId(sessionId);
		DojoListData d = __service.findFormLayoutDojoList(__dlp);

	}

	@Test
	public  void testGetFormLayoutListRowCount(){
		System.out.println("7777");
		 List<FilterParam> __list = new ArrayList<FilterParam>();
		 FilterParam __param = new FilterParam();
		 __param.setField("custom_content");
		 __param.setLogical("like");
		 __param.setRelation("and");
		 __param.setValue("'%1%'");
		 __list.add(__param);
		Integer _count =  __service.getFormLayoutListRowCount(sessionId, null, null);
	}


	@AfterClass
	public  static  void tearDown()
	 {
		 sessionService.invalid(sessionId);
	     node.stop();
	 }








}
