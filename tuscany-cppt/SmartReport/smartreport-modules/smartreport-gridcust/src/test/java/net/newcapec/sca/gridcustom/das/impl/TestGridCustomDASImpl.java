package net.newcapec.sca.gridcustom.das.impl;

/**
 * <p>Title: DAS测试 </p>
 * <p>Description:查询浏览列表用户自定义配置数据访问构件测试</p>
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

import net.newcapec.sca.gridcust.GridCustom;
import net.newcapec.sca.gridcust.das.GridCustomDAS;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

public class TestGridCustomDASImpl {

	private  static final Logger _log = Logger
			.getLogger(TestGridCustomDASImpl.class);

	private  static   Node  node;
	private  static   GridCustomDAS   __service;
	public   static   SequenceService  sequenceService;
	public   static   Integer  testCustomId;

	@BeforeClass
	public  static  void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("gridCustomDAS.composite",
				"target/test-classes");
		 __service = node.getService(GridCustomDAS.class,
		 "GridCustomDASComponent");
		 sequenceService = node.getService(SequenceService.class, "SequenceServiceComponent");
	}

	@Test
	public void testGetGridCustomById() throws NoSuchServiceException {
		System.out.println("1111");
		GridCustom d = __service.getGridCustomById(4);
	}

	@Test
	public void testFindGridCustomList() throws NoSuchServiceException {

		 System.out.println("2222");

		 List<FilterParam> __list = new ArrayList<FilterParam>();
		 FilterParam __param = new FilterParam();
		 __param.setField("custom_content");
		 __param.setLogical("like");
		 __param.setRelation("and");
		 __param.setValue("'%1%'");
		 __list.add(__param);
		 List d = __service.findGridCustomList(null, null, __list, 0, 5);
		 if (d != null){
			 System.out.println(d.size());
		 }
	}

	@Test
	public void testInsertGridCustom() throws NoSuchServiceException {

		System.out.println("33333");
		GridCustom _gridCustom = new GridCustom();
		_gridCustom.setCode(10);
		_gridCustom.setUser_code(5);
		_gridCustom.setForm_code(5);
		_gridCustom.setCustom_content("15");
		__service.insertGridCustom(_gridCustom);
		testCustomId = sequenceService.getCurrentValue("getMaxGridCustomID");
	}

	@Test
    public void	testUpdateGridCustom() throws NoSuchServiceException {
        System.out.println("4444");
        GridCustom _gridCustom = new GridCustom();
        _gridCustom.setCode(testCustomId);
        _gridCustom.setUser_code(6);
        _gridCustom.setForm_code(6);
        _gridCustom.setCustom_content("16");
        __service.updateGridCustom(_gridCustom);

    }

	@Test
    public  void testDelGridCustomByIds(){
    	System.out.println("5555");
//    	Integer[] ids = {8,10};
    	Integer[] ids = {testCustomId};
    	__service.delGridCustomByIds(ids);
    }

	@Test
    public  void  testGetGridCustomListRowCount() throws NoSuchServiceException {
    	System.out.println("6666");
    	List<FilterParam> __list = new ArrayList<FilterParam>();
		FilterParam __param = new FilterParam();
		__param.setField("custom_content");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%1%'");
		__list.add(__param);
		Integer _count =  __service.getGridCustomListRowCount(null, null, __list);
    }

	 @AfterClass
	 public  static  void tearDown()
	 {
	     node.stop();
	 }

}
