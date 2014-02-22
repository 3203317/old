package net.newcapec.sca.diccode.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.diccode.DicItem;
import net.newcapec.sca.diccode.DicTable;
import net.newcapec.sca.diccode.service.DicCodeService;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDicCodeServiceImpl  {
	private static final Logger _log = Logger
	.getLogger(TestDicCodeServiceImpl.class);

    private  static   Node node;
    private  static   DicCodeService __service;
    private  static   SessionService sessionService;
    private  static   String sessionId;
    public   static   SequenceService  sequenceService;
    public   static   Integer  testTableId;
    public   static   Integer  testItemId;


    @BeforeClass
	public static  void  setUp()  throws Exception {
		 node = TuscanyRuntime.runComposite("dicCodeService.composite","target/test-classes");
		  __service = node.getService(DicCodeService.class,"DicCodeServiceComponent");
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
    public  void  testGetDicTableByTableId(){

        System.out.println("1111");
    	DicTable _dicTable ;
    	_dicTable =  __service.getDicTableByTableId(sessionId, 1);
    	if (_dicTable != null ){
    		System.out.println(_dicTable.getName());
    	}

    }

    @Test
    public  void  testGetDicItemByItemId(){

    	System.out.println("2222");
    	DicItem __dicItem;
    	__dicItem =  __service.getDicItemByItemId(sessionId, 1);
    	if (__dicItem != null ){
    		System.out.println(__dicItem.getCode());
    	}

    }

    @Test
    public void  testFindDicItemListByTableId(){

    	 System.out.println("3333");
    	 List<DicItem>  __list ;
    	 __list  = __service.findDicItemListByTableId(1);
    	 if ( __list != null ){
 			System.out.println(__list.size());
 		 }

    }

    @Test
    public void  testFindDicTableList(){

    	System.out.println("4444");
    	List<FilterParam> __list = new ArrayList<FilterParam>();
		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%O%'");
		__list.add(__param);
    	List<DicTable> d =  __service.findDicTableList(sessionId, null, __list, 0, 5);
    	if ( d !=null ){
    		System.out.println(d.size());
    	}
    }

    @Test
    public void testInsertDicTable(){

	    System.out.println("5555");
		DicTable dicTable = new DicTable();
		dicTable.setCreate_date("2012-10-31 16:10:32");
		dicTable.setCreate_user_account_id(1);
		dicTable.setName("11");
		dicTable.setInfo("11");
		dicTable.setName_alias("11");
		dicTable.setSortid(1);
		dicTable.setVer(1);
		__service.insertDicTable(sessionId, dicTable);
		testTableId = sequenceService.getCurrentValue("getMaxDicTableID");


    }

    @Test
    public void testInsertDicItem(){

    	System.out.println("6666");
		DicItem dicItem = new DicItem();
		dicItem.setCode_dictionary_name("SEX");
		dicItem.setCreate_date("2013-01-04 16:10:32");
		dicItem.setCreate_user_account_id(1);
		dicItem.setData_key("1");
		dicItem.setData_value("1");
		dicItem.setInfo("1");
		dicItem.setKey_code("1");
		dicItem.setSortid(1);
		dicItem.setVer(1);
		__service.insertDicItem(sessionId, dicItem);
		testItemId = sequenceService.getCurrentValue("getMaxDicItemID");

    }

    @Test
    public  void  testUpdateDicTable(){

		System.out.println("7777");
		DicTable dicTable = new DicTable();
		dicTable.setCode(testTableId);
		dicTable.setName("TEST");
		dicTable.setName_alias("TEST");
		dicTable.setInfo("TEST");
		__service.updateDicTable(sessionId, dicTable);
    }

    @Test
    public  void  testUpdateDicItem(){
		System.out.println("8888");
		DicItem dicItem = new DicItem();
		dicItem.setCode(testItemId);
		dicItem.setCode_dictionary_name("TEST");
		dicItem.setData_key("14");
		dicItem.setData_value("14");
		dicItem.setKey_code("14");
		dicItem.setInfo("TEST");
		__service.updateDicItem(sessionId, dicItem);
    }

    @Test
    public  void  testDelDicCodeByTableIds(){
    	System.out.println("9999");
    	DicTable dicTable = new DicTable();
//    	dicTable = __service.delDicCodeByTableIds(sessionId, "13,14");
    	dicTable = __service.delDicCodeByTableIds(sessionId, String.valueOf(testTableId));

    }

     @Test
    public void  testDelDicCodeByItemIds(){
    	System.out.println("10001");
//    	__service.delDicCodeByItemIds(sessionId, "42,43");
    	__service.delDicCodeByItemIds(sessionId, String.valueOf(testItemId));
    }

    @Test
    public void testFindDicTableDojoList(){
    	System.out.println("10002");
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
		__dlp.setLimit(5);
		__dlp.setSessionId(sessionId);

		DojoListData d = __service.findDicTableDojoList(__dlp);
        if  (d != null){
		   System.out.println(d.getItems().length);
		   System.out.println(d.getNumRows());
        }
    }

    @Test
	public void testFindDicItemDojoListByTableId(){
		System.out.println("10003");
		DojoListParam __dlp = new DojoListParam();
		__dlp.setBegin(0);
		__dlp.setLimit(5);
		__dlp.setSessionId(sessionId);
		DojoListData __dld = new DojoListData();
		__dld  =__service.findDicItemDojoListByTableId(__dlp);
		if  (__dld != null)
		   System.out.println(__dld.getItems().length);
	}

    @Test
	public void testFindDicItemListByCondition(){

		System.out.println("10004");

    	List<FilterParam> __listparam = new ArrayList<FilterParam>();
    	FilterParam __param = new FilterParam();
		__param.setField("code_dictionary_name");
		__param.setLogical("=");
		__param.setValue("'SEX'");
		__param.setRelation("and");
		__listparam.add(__param);
		List<DicItem>  __list  = __service.findDicItemListByCondition(sessionId,__listparam);
   	    if ( __list != null ){
			System.out.println(__list.size());
	    }
	}

    @Test
    public void testfindDicItemDojoListByCondition(){
    	System.out.println("10005");
    	List<FilterParam> __list = new ArrayList<FilterParam>();
    	FilterParam __param = new FilterParam();
		__param.setField("code_dictionary_name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%O%'");
		__list.add(__param);
		DojoListParam __dlp = new DojoListParam();
		__dlp.setFilter(__list);
		__dlp.setBegin(0);
		__dlp.setLimit(5);
		__dlp.setSessionId(sessionId);
		DojoListData d = __service.findDicItemDojoListByCondition(__dlp);
        if  (d!=null){
		   System.out.println(d.getItems().length);
		   System.out.println(d.getNumRows());
        }
    }

    @Test
    public void testGetDicTableDojoList(){
    	System.out.println("10006");
    	DojoListParam __dlp = new DojoListParam();
		__dlp.setBegin(0);
		__dlp.setLimit(5);
		__dlp.setSessionId(sessionId);
    	DojoListData  d = __service.getDicTableDojoList(__dlp);
    	if (d.getItems() !=null){
    		System.out.println(d.getItems().length);
    	}
    }


    @AfterClass
    public  static void tearDown()
	 {
    	 sessionService.invalid(sessionId);
	     node.stop();
	 }



}
