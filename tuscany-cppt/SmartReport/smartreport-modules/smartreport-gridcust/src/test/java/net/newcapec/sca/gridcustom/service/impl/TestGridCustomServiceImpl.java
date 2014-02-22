package net.newcapec.sca.gridcustom.service.impl;
/**
 * <p>Title: Service构件测试 </p>
 * <p>Description:查询浏览列表用户自定义配置服务构件测试</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-25
 * 修改日期：
 * 修改人：
 * 复审人：
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.gridcust.GridCustom;
import net.newcapec.sca.gridcust.service.GridCustomService;
import net.newcapec.sca.gridcustom.das.impl.TestGridCustomDASImpl;
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

public class TestGridCustomServiceImpl {

	private static final Logger _log = Logger
	.getLogger(TestGridCustomDASImpl.class);

    private  static   Node node;
    private  static   GridCustomService __service;
    private  static   SessionService sessionService;
    private  static   String sessionId;
    public   static   SequenceService  sequenceService;
    public   static   Integer  testCustomId;

    @BeforeClass
	public  static  void  setUp()  throws Exception {
		node = TuscanyRuntime.runComposite("gridCustomService.composite",
				"target/test-classes");
		 __service = node.getService(GridCustomService.class,
		 "GridCustomServiceComponent");
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
    public  void  testGetGridCustomById()  throws Exception {
    	System.out.println("1111");
    	GridCustom _gridCustom = new GridCustom();
    	_gridCustom = __service.getGridCustomById(sessionId, 4);
    	if (_gridCustom != null){
    	    System.out.println(_gridCustom.getCustom_content());
    	}

    }

    @Test
    public  void  testFindGridCustomList() throws Exception {
    	 System.out.println("2222");
    	 List<FilterParam> __list = new ArrayList<FilterParam>();
		 FilterParam __param = new FilterParam();
		 __param.setField("custom_content");
		 __param.setLogical("like");
		 __param.setRelation("and");
		 __param.setValue("'%1%'");
		 __list.add(__param);

		 List<GridCustom> d =  __service.findGridCustomList(sessionId, null, __list, 0, 5);
		 if (d !=null){
    	     System.out.println(d.size());
		 }

    }

    @Test
    public  void  testInsertGridCustom() throws Exception{

    	System.out.println("33333");
		GridCustom _gridCustom = new GridCustom();
		_gridCustom.setCode(9);
		_gridCustom.setUser_code(9);
		_gridCustom.setForm_code(9);
		_gridCustom.setCustom_content("19");
		__service.insertGridCustom(sessionId,_gridCustom);
		testCustomId = sequenceService.getCurrentValue("getMaxGridCustomID");

    }

    @Test
    public  void  testUpdateGridCustom() throws Exception{

    	System.out.println("4444");
        GridCustom _gridCustom = new GridCustom();
        _gridCustom.setCode(testCustomId);
        _gridCustom.setUser_code(8);
        _gridCustom.setForm_code(8);
        _gridCustom.setCustom_content("18");
        __service.updateGridCustom(sessionId,_gridCustom);

    }

    @Test
    public  void  testDelGridCustomByIds() throws Exception{
    	System.out.println("5555");
//    	__service.delGridCustomByIds(sessionId,"1,2,3");
    	__service.delGridCustomByIds(sessionId,String.valueOf(testCustomId));
    }

    @Test
    public void  testFindGridCustomDojoList() throws Exception{
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
		DojoListData d = __service.findGridCustomDojoList(__dlp);
    }

    @Test
    public  void  testGetGridCustomListRowCount() throws Exception{
    	System.out.println("7777");
    	int __count = __service.getGridCustomListRowCount(sessionId, null, null);
    }

    @AfterClass
    public  static  void tearDown()
	 {
    	 sessionService.invalid(sessionId);
	     node.stop();
	 }



}
