package net.newcapec.sca.formlayout.das.impl;

/**
 * <p>Title: DAS测试 </p>
 * <p>Description:查询浏览列表表单布局定制数据访问构件测试</p>
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

import net.newcapec.sca.formlayout.FormLayout;
import net.newcapec.sca.formlayout.das.FormLayoutDAS;
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


public class TestFormLayoutDASImpl  {

	private static final Logger _log = Logger
	.getLogger(TestFormLayoutDASImpl.class);

	private static  Node node;
	private static  FormLayoutDAS __service;
	public  static  SequenceService  sequenceService;
	public  static   Integer  testFormLayoutId;

	@BeforeClass
	public  static void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("formLayoutDAS.composite",
				"target/test-classes");
	    __service = node.getService(FormLayoutDAS.class,
		"FormLayoutDASComponent");
	    sequenceService = node.getService(SequenceService.class, "SequenceServiceComponent");
	}

	@Test
	public void testGetFormLayoutById(){
		System.out.println("1111");
		FormLayout d = __service.getFormLayoutById(1);
		if ( d != null ){
			System.out.println(d.getForm_code().toString());
		}
	}

	@Test
    public void testFindFormLayoutList() throws NoSuchServiceException{
    	System.out.println("2222");
    	List<FilterParam> __list = new ArrayList<FilterParam>();
		FilterParam __param = new FilterParam();
		__param.setField("custom_content");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%1%'");
		__list.add(__param);
		List d = __service.findFormLayoutList(null, null, __list, 0, 5);
		if (d != null){
		    System.out.println(d.size());
		}
    }

	@Test
    public void testInsertFormLayout() throws NoSuchServiceException{

    	System.out.println("3333");
    	FormLayout _formLayout = new FormLayout();
    	_formLayout.setCustom_type(5);
    	_formLayout.setCustom_content("15");
    	_formLayout.setTemp_content("15");
    	_formLayout.setPrint_temp_code(5);
		__service.insertFormLayout(_formLayout);
		testFormLayoutId = sequenceService.getCurrentValue("getMaxFormLayoutID");

    }

	@Test
    public void testUpdateFormLayout() throws NoSuchServiceException{
    	System.out.println("4444");
    	FormLayout _formLayout = new FormLayout();
    	_formLayout.setCode(testFormLayoutId);
    	_formLayout.setForm_code(6);
    	_formLayout.setCustom_type(6);
    	_formLayout.setCustom_content("16");
    	_formLayout.setTemp_content("16");
    	_formLayout.setPrint_temp_code(6);
    	__service.updateFormLayout(_formLayout);
    }

	@Test
    public void testDelGridCustomByIds() throws NoSuchServiceException{
    	System.out.println("5555");
//    	Integer[] ids ={7,8};
    	Integer[] ids ={testFormLayoutId};
    	__service.delFormLayoutByIds(ids);

    }

	@Test
	public  void testGetFormLayoutListRowCount(){
		System.out.println("6666");
		 List<FilterParam> __list = new ArrayList<FilterParam>();
		 FilterParam __param = new FilterParam();
		 __param.setField("custom_content");
		 __param.setLogical("like");
		 __param.setRelation("and");
		 __param.setValue("'%1%'");
		 __list.add(__param);
		Integer _count =  __service.getFormLayoutListRowCount(null, null, null);
	}

	@AfterClass
	public  static  void tearDown()
	 {
	     node.stop();
	 }

}
