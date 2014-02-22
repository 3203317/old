package net.newcapec.sca.diccode.das.impl;

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

import net.newcapec.sca.diccode.DicItem;
import net.newcapec.sca.diccode.DicTable;
import net.newcapec.sca.diccode.das.DicCodeDAS;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

import junit.framework.TestCase;

public class TestDicCodeDASImpl  {

	private static final Logger _log = Logger
	.getLogger(TestDicCodeDASImpl.class);

	private static Node node;
	private static DicCodeDAS __service;
    public  static  SequenceService  sequenceService;
    public  static   Integer  testTableId;
    public  static   Integer  testItemId;

	@BeforeClass
	public  static  void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("dicCodeDAS.composite",
				"target/test-classes");
	    __service = node.getService(DicCodeDAS.class,
		"DicCodeDASComponent");
	    sequenceService = node.getService(SequenceService.class, "SequenceServiceComponent");
	}

	@Test
	public void testGetDicTableByTableId()
	{
		System.out.println("1111");
		DicTable  __dicTable  = __service.getDicTableByTableId(1);
		if (__dicTable !=null ){
			System.out.println(__dicTable.getName());
		}

	}

	@Test
	public void testGetDicItemByItemId()
	{
		System.out.println("2222");
		DicItem dicItem =  __service.getDicItemByItemId(1);
		if (dicItem !=null){
			System.out.println(dicItem.getCode_dictionary_name());
		}

	}

	@Test
	public void testFindDicTableList()
	{
		System.out.println("3333");
		List<FilterParam> __list = new ArrayList<FilterParam>();
		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%O%'");
		__list.add(__param);
		List<DicTable> d =  __service.findDicTableList(null,null,__list,0,5) ;
		if (d != null) {

			System.out.println(d.size());

		}
	}


	@Test
	public void testFindDicItemListByTableId()
	{
		System.out.println("4444");
		List<DicItem>  __list = __service.findDicItemListByTableId(1) ;
		if ( __list != null ){
			System.out.println(__list.size());
		}
	}

	@Test
	public void testInsertDicTable()
	{
	    System.out.println("5555");
		DicTable dicTable = new DicTable();
		dicTable.setCreate_date("2013-01-04 9:51:32");
		dicTable.setCreate_user_account_id(1);
		dicTable.setInfo("1");
		dicTable.setName("11");
		dicTable.setName_alias("1");
		dicTable.setSortid(1);
		dicTable.setVer(1);
		__service.insertDicTable(dicTable);
		testTableId = sequenceService.getCurrentValue("getMaxDicTableID");
	}

    @Test
	public void testInsertDicItem()
	{
	    System.out.println("6666");
		DicItem dicItem = new DicItem();
		dicItem.setCode_dictionary_name("11");
		dicItem.setCreate_date("2013-01-04 10:00:32");
		dicItem.setCreate_user_account_id(1);
		dicItem.setData_key("1");
		dicItem.setData_value("1");
		dicItem.setInfo("1");
		dicItem.setKey_code("1");
		dicItem.setSortid(1);
		dicItem.setVer(1);
		__service.insertDicItem(dicItem);
		testItemId = sequenceService.getCurrentValue("getMaxDicItemID");
	}

	@Test
	public void  testUpdateDicTable()
	{
		System.out.println("7777");
		DicTable dicTable = new DicTable();
		dicTable.setCode(testTableId);
		dicTable.setName("TEST11");
		dicTable.setName_alias("TEST11");
		dicTable.setInfo("TEST11");
		__service.updateDicTable(dicTable);
	}

    @Test
	public void testUpdateDicItem()
	{
		System.out.println("8888");
		DicItem dicItem = new DicItem();
		dicItem.setCode(testItemId);
		dicItem.setCode_dictionary_name("TEST");
		dicItem.setData_key("14");
		dicItem.setData_value("14");
		dicItem.setKey_code("14");
		dicItem.setInfo("测试");

		__service.updateDicItem(dicItem);
	}

	@Test
	public void  testDelDicCodeByTableIds(){
		System.out.println("9999");
//		Integer[] ids ={13,14};
		Integer[] ids ={testTableId};
		__service.delDicCodeByTableIds(ids);
	}

	@Test
	public void  testDelDicCodeByItemIds(){
		System.out.println("10001");
//		Integer[] ids = {42,43};
		Integer[] ids = {testItemId};
		__service.delDicCodeByItemIds(ids);
	}

	@Test
    public void testGetDicTableListRowCount(){
    	System.out.println("10002");
    	List<FilterParam> __list = new ArrayList<FilterParam>();
		FilterParam __param = new FilterParam();
		__param.setField("name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%O%'");
		__list.add(__param);
		Integer _count = __service.getDicTableListRowCount(null, null, null);
		System.out.println(_count);
    }

	@Test
    public void testGetDicItemListByConditionRowCount(){
    	System.out.println("10003");
    	List<FilterParam> __list = new ArrayList<FilterParam>();
		FilterParam __param = new FilterParam();
		__param.setField("code_dictionary_name");
		__param.setLogical("like");
		__param.setRelation("and");
		__param.setValue("'%O%'");
		__list.add(__param);
		Integer _count = __service.getDicItemListByConditionRowCount(null, null, __list);
		System.out.println(_count);
    }

	@Test
    public void testGetDicTableDojoList(){
    	System.out.println("10006");
    	List<JSONObject> list = new ArrayList<JSONObject>();
		try {
			list = __service.getDicTableList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if (list !=null){
    		System.out.println(list.size());
    	}
    }

	@AfterClass
	public  static  void tearDown()
	 {
	     node.stop();
	 }

}
